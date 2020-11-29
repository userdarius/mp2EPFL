/* 
 * Author: Maxime Hilbig
 * Date: 27.11.2020
 */
package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Level1 extends SuperPacmanArea{
	private final DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates(15,6);
	@Override
	public String getTitle() {
		
		return "superpacman/Level1";
	}

	@Override
	protected void createArea() {
		// TODO Auto-generated method stub
		
	}

}
