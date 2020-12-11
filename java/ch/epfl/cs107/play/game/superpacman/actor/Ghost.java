/* 
 * Author: Maxime Hilbig
 * Date: 11.12.2020
 */
package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Ghost extends MovableAreaEntity implements Interactor {
	
	
	private static final int radius = 5;
	boolean affraid = false;
	private SuperPacmanPlayer memory;
	private GhostHandler GhostHandler;
	private List<DiscreteCoordinates> fieldOfView;
	
	public Ghost(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		memory = null;
		
		
	}
	public void update(float deltaTime) {
		super.update(deltaTime);
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}
	public int getValue() {
		return 500;
	}
	
	public void affraid() {
		affraid = true;
	}
	public void disaffraid() {
		affraid = false;
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
		
	}

	@Override
	public List<DiscreteCoordinates> getFieldOfViewCells() {
		fieldOfView = new ArrayList<DiscreteCoordinates>();
		for (int i = 5; i <= 5; i++) {
			for(int j = 5; j <= 5; j++)  {
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
