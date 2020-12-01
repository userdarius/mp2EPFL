/* 
 * Author: Maxime Hilbig
 * Date: 27.11.2020
 */
package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

public class Level1 extends SuperPacmanArea{
	
	private final DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates(15,6);
	
	private final DiscreteCoordinates LANDING_IN_NEW_AREA = new DiscreteCoordinates(15,29);
	private final DiscreteCoordinates PRINCIPAL_CELL = new DiscreteCoordinates(14,0);
	private final DiscreteCoordinates OTHER_CELL = new DiscreteCoordinates(15,0);
	
	@Override
	public String getTitle() {
		
		return "superpacman/Level1";
	}

	@Override
	protected void createArea() {
		// TODO Auto-generated method stub
		Door door = new Door("superpacman/Level2", LANDING_IN_NEW_AREA , Logic.TRUE, this , Orientation.UP, PRINCIPAL_CELL,OTHER_CELL);
		registerActor(door);
	}

	@Override
	
		public DiscreteCoordinates getPlayerSpawnPosition() {
			return PLAYER_SPAWN_POSITION;
		
	}

}
