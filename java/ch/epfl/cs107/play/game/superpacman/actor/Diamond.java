/* 
 * Author: Maxime Hilbig
 * Date: 04.12.2020
 */
package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.CollectableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class Diamond extends CollectableAreaEntity implements Logic{

	//private int numberOfDiamondsCollected = 0;
	
	public Diamond(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		super.value = 10;
		
	}
	
	private Sprite diamond = new Sprite ("superpacman/diamond", 1.f, 1.f, this);


	public int getValue() {
		return 10;
	}
	
	

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		// TODO Auto-generated method stub
		return Collections.singletonList(getCurrentMainCellCoordinates());
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
		diamond.draw(canvas);
		
		
	}
	
	@Override
	public void isTaken() {
		super.isTaken();
		//numberOfDiamondsCollected++;
		
		
		
	}
	
	//public int  getNumberOfDiamondsCollected() {
		//return numberOfDiamondsCollected;
	//}



	@Override
	public boolean isOn() {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean isOff() {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public float getIntensity() {
		// TODO Auto-generated method stub
		return 0;
	}

}
