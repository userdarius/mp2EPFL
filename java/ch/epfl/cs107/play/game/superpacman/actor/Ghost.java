/* 
 * Author: Maxime Hilbig
 * Date: 11.12.2020
 */
package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Ghost extends MovableAreaEntity implements Interactor {
	
	
	private static final int radius = 5;
	private boolean affraid;
	private final int GHOST_SCORE = 500; 
	private SuperPacmanPlayer memory;
	private DiscreteCoordinates refuge;
	
	//private Animation[] animations = Animation.createAnimations(ANIMATION_DURATION / 4, sprites);
	private static final int ANIMATION_DURATION = 8;
	private GhostHandler GhostHandler;
	private List<DiscreteCoordinates> fieldOfView;
	Sprite[] sprites = RPGSprite.extractSprites("superpacman/ghost.afraid", 2, 1, 1, this, 16, 16); 
	
	private Animation GhostAfraid = new Animation(2, sprites );
	
	
	
	SuperPacmanPlayerStatusGUI status = new SuperPacmanPlayerStatusGUI();

	
	public Ghost(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		memory = null;
		GhostHandler = new GhostHandler();
		this.refuge = position;
	}
	public void update(float deltaTime) {
		super.update(deltaTime);
		GhostAfraid.update(deltaTime);
		
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}
	/*public int getValue() { //?Value du Ghost???
		return 500;
	}*/
	
	public int getGhostScore() {
		return GHOST_SCORE;
		
	}
	public void respawnGhost() {
		getOwnerArea().leaveAreaCells(this, getEnteredCells());
		setCurrentPosition(refuge.toVector());
		getOwnerArea().enterAreaCells(this, getCurrentCells());
		resetMotion();
	}
	
	public void affraid() {
		affraid = true;
	}
	public void disaffraid() {
		affraid = false;
	}
	public boolean getAfraid() {
		return affraid;
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
		return false;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((SuperPacmanInteractionVisitor)v).interactWith(this);
		
	}

	@Override
	public void draw(Canvas canvas) {
		GhostAfraid.draw(canvas);
		status.draw(canvas);
	}

	@Override
	public List<DiscreteCoordinates> getFieldOfViewCells() {
		fieldOfView = new ArrayList<DiscreteCoordinates>();
		for (int i = 5; i <= 5; i++) {     //Doit commencer a sa cellule de départ
			for(int j = 5; j <= 5; j++)  {   //La ca ne marche pas je commence a 5
				fieldOfView.add(new DiscreteCoordinates(getCurrentCells().get(0).x+1,getCurrentCells().get(0).y+j));
			
		}
			
		} return fieldOfView;
	}
	 
	
	@Override
	public boolean wantsCellInteraction() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean wantsViewInteraction() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void interactWith(Interactable other) {
		other.acceptInteraction(GhostHandler);
		
	}
	public class GhostHandler implements SuperPacmanInteractionVisitor {
		public void interactWith(SuperPacmanPlayer player) {
			memory = player;
		}
	}
	

}
