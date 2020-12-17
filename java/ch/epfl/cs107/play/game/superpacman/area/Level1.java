package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.superpacman.actor.Gate;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

public class Level1 extends SuperPacmanArea{
	
	boolean collected = false;
	private final DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates(15,6);
	private final DiscreteCoordinates LANDING_IN_NEW_AREA = new DiscreteCoordinates(15,29);
	private final DiscreteCoordinates PRINCIPAL_CELL = new DiscreteCoordinates(14,0);
	private final DiscreteCoordinates OTHER_CELL = new DiscreteCoordinates(15,0);
	private final DiscreteCoordinates GATE1 = new DiscreteCoordinates(14,3);
	private final DiscreteCoordinates GATE2 = new DiscreteCoordinates(15,3);
	
	@Override
	public String getTitle() {
		return "superpacman/Level1";
	}

	@Override
	public DiscreteCoordinates getPlayerSpawnPosition() {
			return PLAYER_SPAWN_POSITION;
	}

	@Override
	public boolean isOff() {
		collected();
		return !collected;
	}

	public boolean isOn() {
		collected();
		return collected;
	}

	public void collected() {
		collected = (getNumberOfDiamonds() == 0);
	}

	protected void createArea() {
		Door door = new Door("superpacman/Level2", LANDING_IN_NEW_AREA , Logic.TRUE, this , Orientation.UP, PRINCIPAL_CELL,OTHER_CELL);
		registerActor(door);
		Gate gate1 = new Gate(this, Orientation.RIGHT, GATE1, this);
		registerActor(gate1);
		Gate gate2 = new Gate(this, Orientation.RIGHT, GATE2,  this);
		registerActor(gate2);
	}

	@Override
	public float getIntensity() {
		return 0;
	}
}
