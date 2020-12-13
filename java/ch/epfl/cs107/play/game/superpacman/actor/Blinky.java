
package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.Collections;
import java.util.List;
import java.awt.Color;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.CollectableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RandomGenerator;
import ch.epfl.cs107.play.window.Canvas;

public class Blinky extends Ghost{
	
	private static final int ANIMATION_DURATION = 8;
	private final int SPEED = 6;
	private Animation[] animations;
	private Orientation desiredOrientation;
	private static final int MAX = 100;
	
	

	public Blinky(Area area, Orientation orientation, DiscreteCoordinates position, String name) {
		super(area, orientation, position);
		
		extractsprites();
		desiredOrientation = getOrientation();
	}
	
	public void update(float deltaTime) {
		int randomInt = RandomGenerator.getInstance().nextInt(4);
		if(isDisplacementOccurs()) {
			desiredOrientation = Orientation.fromInt(randomInt);
			animations[getOrientation().ordinal()].update(deltaTime);
		}
		desiredOrientation = Orientation.fromInt(randomInt);
		if (!isDisplacementOccurs() && getOwnerArea().canEnterAreaCells(this, Collections.singletonList(getCurrentMainCellCoordinates().jump(desiredOrientation.toVector())))) {
			Orientation previousOrientation = getOrientation();
			orientate(desiredOrientation);// le faire tourner
			if (previousOrientation != getOrientation()) {
				animations[getOrientation().ordinal()].reset();
			}
			int SPEED = 6;
			move(SPEED);
		}

		if(!isDisplacementOccurs()) {
			animations[getOrientation().ordinal()].reset();
		}

		super.update(deltaTime);
	}

	public void extractsprites() {
		Sprite[][] sprites = RPGSprite.extractSprites("superpacman/ghost.blinky", 2, 1, 1, this, 16, 16,
                new Orientation[] {Orientation.UP, Orientation.RIGHT, Orientation.DOWN, Orientation.LEFT});
        animations = Animation.createAnimations(ANIMATION_DURATION / 4, sprites);
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
		if (getAfraid()) {
			super.draw(canvas);
		} else {
			animations[getOrientation().ordinal()].draw(canvas);
		}
	}
}