/* 
 * Author: Maxime Hilbig
 * Date: 07.12.2020
 */
package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;

public class Gate extends AreaEntity {
	
	private Logic signal;
	private Sprite gate;

	public Gate(Area area, Orientation orientation, DiscreteCoordinates position, Logic signal ) {
		super(area, orientation, position);
		

		this.signal = signal;
		if (orientation == Orientation.UP || orientation == Orientation.DOWN) {
			gate = new Sprite("superpacman/gate", 1.f, 1.f, this, new RegionOfInterest(0, 0, 64, 64));
		} else {
			gate = new Sprite("superpacman/gate", 1.f, 1.f, this, new RegionOfInterest(0, 64, 64, 64));
		}
	}
	
	
	
	
	
	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		// TODO Auto-generated method stub
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public boolean takeCellSpace() {  //Place deja occupém
		return signal.isOff();
	}

	@Override
	public boolean isCellInteractable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isViewInteractable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Canvas canvas) {
		if (signal.isOff()) {
		gate.draw(canvas);
		}
		
	}

}
