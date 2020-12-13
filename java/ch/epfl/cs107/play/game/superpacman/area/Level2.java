/* 
 * Author: Maxime Hilbig
 * Date: 27.11.2020
 */
package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.superpacman.actor.Gate;
import ch.epfl.cs107.play.game.superpacman.actor.Key;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.And;
import ch.epfl.cs107.play.signal.logic.Logic;

public class Level2 extends SuperPacmanArea {
	private final DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates(15, 29);
	private final DiscreteCoordinates KEY1 = new DiscreteCoordinates(3, 16);
	private final DiscreteCoordinates KEY2 = new DiscreteCoordinates(26, 16);
	private final DiscreteCoordinates KEY3 = new DiscreteCoordinates(2, 8);
	private final DiscreteCoordinates KEY4 = new DiscreteCoordinates(27, 8);
	//Coordinates of the 14 gates
	private final DiscreteCoordinates GATE1 = new DiscreteCoordinates(8, 14);
	private final DiscreteCoordinates GATE2 = new DiscreteCoordinates(5, 12); 
	private final DiscreteCoordinates GATE3 = new DiscreteCoordinates(8, 10);
	private final DiscreteCoordinates GATE4 = new DiscreteCoordinates(8, 8);
	private final DiscreteCoordinates GATE5 = new DiscreteCoordinates(21, 14);
	private final DiscreteCoordinates GATE6 = new DiscreteCoordinates(24, 12);
	private final DiscreteCoordinates GATE7 = new DiscreteCoordinates(21, 10);
	private final DiscreteCoordinates GATE8 = new DiscreteCoordinates(21, 8);
	private final DiscreteCoordinates GATE9 = new DiscreteCoordinates(10, 2);
	private final DiscreteCoordinates GATE10 = new DiscreteCoordinates(19, 2);
	private final DiscreteCoordinates GATE11 = new DiscreteCoordinates(12, 8);
	private final DiscreteCoordinates GATE12 = new DiscreteCoordinates(17, 8);
	private final DiscreteCoordinates GATE13 = new DiscreteCoordinates(14, 3);
	private final DiscreteCoordinates GATE14 = new DiscreteCoordinates(15, 3);
	
	
	
	
	@Override
	public String getTitle() { 
		
		return "superpacman/Level2";
	}

	@Override
	protected void createArea() {
		Key key1 = new Key(this, Orientation.UP, KEY1); //Création des 4 clés
		registerActor(key1);
		Key key2 = new Key(this, Orientation.UP, KEY2);
		registerActor(key2);
		Key key3 = new Key(this, Orientation.UP, KEY3);
		registerActor(key3);
		Key key4 = new Key(this, Orientation.UP, KEY4);
		registerActor(key4);
		
		And combination = new And(key3, key4);
		
		
		Gate gate1 = new Gate(this, Orientation.RIGHT, GATE1, key1);
		registerActor(gate1);
		Gate gate2 = new Gate(this, Orientation.DOWN, GATE2, key1);
		registerActor(gate2);
		Gate gate3 = new Gate(this, Orientation.RIGHT, GATE3, key1);
		registerActor(gate3);
		Gate gate4 = new Gate(this, Orientation.RIGHT, GATE4, key1);
		registerActor(gate4);
		Gate gate5 = new Gate(this, Orientation.RIGHT, GATE5, key2);
		registerActor(gate5);
		Gate gate6 = new Gate(this, Orientation.DOWN, GATE6, key2);
		registerActor(gate6);
		Gate gate7 = new Gate(this, Orientation.RIGHT, GATE7, key2);
		registerActor(gate7);
		Gate gate8 = new Gate(this, Orientation.RIGHT, GATE8, key2);
		registerActor(gate8);
		Gate gate9 = new Gate(this, Orientation.RIGHT, GATE9, combination);
		registerActor(gate9);
		Gate gate10 = new Gate(this, Orientation.RIGHT, GATE10, combination);
		registerActor(gate10);
		Gate gate11 = new Gate(this, Orientation.RIGHT, GATE11, combination);
		registerActor(gate11);
		Gate gate12 = new Gate(this, Orientation.RIGHT, GATE12, combination);
		registerActor(gate12);
		Gate gate13 = new Gate(this, Orientation.RIGHT, GATE13, this);
		registerActor(gate13);
		Gate gate14 = new Gate(this, Orientation.RIGHT, GATE14, this);
		registerActor(gate14);
		
		
	}

	@Override	
		public DiscreteCoordinates getPlayerSpawnPosition() {
			return PLAYER_SPAWN_POSITION;
	}

	@Override
	public float getIntensity() {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
