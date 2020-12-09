/* 
 * Author: Maxime Hilbig
 * Date: 27.11.2020
 */
package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.superpacman.actor.Key;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

public class Level0 extends SuperPacmanArea{
	
	private final DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates(10,1);
	private final DiscreteCoordinates LANDING_IN_NEW_AREA = new DiscreteCoordinates(15,6);
	private final DiscreteCoordinates PRINCIPAL_CELL = new DiscreteCoordinates(5,9);
	private final DiscreteCoordinates OTHER_CELL = new DiscreteCoordinates(6,9);
	private final DiscreteCoordinates KEY = new DiscreteCoordinates(3,4);
	
	@Override
	public String getTitle() {
		return "superpacman/Level0";
	}

	@Override
	protected void createArea() {
		// TODO Auto-generated method stub
		Door door = new Door("superpacman/Level1", LANDING_IN_NEW_AREA , Logic.TRUE, this , Orientation.UP, PRINCIPAL_CELL,OTHER_CELL);
		registerActor(door);
		Key key = new Key(this, Orientation.UP, KEY);
		registerActor(key);
	}

	@Override
	public DiscreteCoordinates getPlayerSpawnPosition() {
		return PLAYER_SPAWN_POSITION;
		
		
	}

}
