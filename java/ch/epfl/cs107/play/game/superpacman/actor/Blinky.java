
package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.Collections;
import java.util.List;
import java.awt.Color;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.CollectableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Blinky extends CollectableAreaEntity{
	
	private static final int ANIMATION_DURATION = 8;
	private final int SPEED = 6;
	private static final int GHOST_SCORE = 500;
	private Animation[] animations;
	SuperPacmanPlayerStatusGUI status = new SuperPacmanPlayerStatusGUI();

	//test intellij for git
	
	public Blinky(Area area, Orientation orientation, DiscreteCoordinates position, String name) {
		super(area, orientation, position);
		extractsprites();
		//need for orientation
	}
	
	public void update(float deltaTime) {
		
	}

	public void extractsprites() {		
		Sprite[][] sprites = RPGSprite.extractSprites("superpacman/ghost.blinky", 2, 1, 1, this, 32, 64,
                new Orientation[] {Orientation.DOWN, Orientation.LEFT, Orientation.UP, Orientation.RIGHT});
        animations = Animation.createAnimations(ANIMATION_DURATION / 4, sprites);
	}
	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		// TODO Auto-generated method stub
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}
	
	public List<DiscreteCoordinates> getFieldOfViewCells() {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public boolean takeCellSpace() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCellInteractable() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isViewInteractable() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((SuperPacmanInteractionVisitor)v).interactWith(this);
	}

	@Override
	public void draw(Canvas canvas) {
		animations[getOrientation().ordinal()].draw(canvas);
		status.draw(canvas);
	}
}