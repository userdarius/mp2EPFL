/* 
 * Author: Maxime Hilbig
 * Date: 04.12.2020
 */
package ch.epfl.cs107.play.game.areagame.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public abstract class CollectableAreaEntity extends AreaEntity {

	protected int value;
	public CollectableAreaEntity(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		
	}
	
	public void isTaken() {
		getOwnerArea().unregisterActor(this);
		
		
	}
	public int getValue() {
		return value;
	}

}
