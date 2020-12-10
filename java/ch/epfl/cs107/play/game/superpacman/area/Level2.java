/* 
 * Author: Maxime Hilbig
 * Date: 27.11.2020
 */
package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Level2 extends SuperPacmanArea {
	private final DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates(15, 29);
	@Override
	public String getTitle() {
		
		return "superpacman/Level2";
	}

	@Override
	protected void createArea() {
		// TODO Auto-generated method stub
		
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
