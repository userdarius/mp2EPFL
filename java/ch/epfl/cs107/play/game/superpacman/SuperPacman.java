/* 
 * Author: Maxime Hilbig
 * Date: 27.11.2020
 */
package ch.epfl.cs107.play.game.superpacman;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.RPG;
import ch.epfl.cs107.play.game.superpacman.actor.Blinky;
import ch.epfl.cs107.play.game.superpacman.actor.SuperPacmanPlayer;
import ch.epfl.cs107.play.game.superpacman.area.Level0;
import ch.epfl.cs107.play.game.superpacman.area.Level1;
import ch.epfl.cs107.play.game.superpacman.area.Level2;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.game.tutosSolution.actor.GhostPlayer;
import ch.epfl.cs107.play.game.tutosSolution.area.tuto2.Ferme;
import ch.epfl.cs107.play.game.tutosSolution.area.tuto2.Village;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;


public class SuperPacman extends RPG{
	private SuperPacmanPlayer pacman;
	
	//private SuperPacmanPlayer player;
	//private final String[] areas = {"superpacman/Level0", "superpacman/Level1", "superpacman/Level2"};
	//private final DiscreteCoordinates[] startingPositions = {new DiscreteCoordinates(10, 1), 
			// new DiscreteCoordinates(15, 6), new DiscreteCoordinates(15, 29)};
	
	//private int areaIndex;
	@Override
	public String getTitle() {
		
		return "Super Pac-Man";
	}
	
	public void update(float deltaTime ) {
		super.update(deltaTime);
		if (pacman.invulnerable()) {
			((SuperPacmanArea) pacman.getOwnerArea()).affraid();
		} else {
			((SuperPacmanArea) pacman.getOwnerArea()).notAffraid();
		}
	
	}
	
	private void createAreas(){	
	addArea(new Level0());
	addArea(new Level1());
	addArea(new Level2());
	}

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {

 
		if (super.begin(window, fileSystem)) {

			createAreas();
			SuperPacmanArea area = (SuperPacmanArea) (setCurrentArea("superpacman/Level1",true));
			pacman = new SuperPacmanPlayer(area, Orientation.UP, area.getPlayerSpawnPosition(),"pacman");
			//areaIndex = 0;
			//Area area = setCurrentArea(areas[areaIndex], true);
			//player = new SuperPacmanPlayer(area, Orientation.DOWN, startingPositions[areaIndex]);
			
			initPlayer(pacman);
			return true;
		}
		return false;
	}
	
	
	

	

}
