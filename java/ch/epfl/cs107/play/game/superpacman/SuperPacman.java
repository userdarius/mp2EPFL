package ch.epfl.cs107.play.game.superpacman;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.RPG;
import ch.epfl.cs107.play.game.superpacman.actor.SuperPacmanPlayer;
import ch.epfl.cs107.play.game.superpacman.area.Level0;
import ch.epfl.cs107.play.game.superpacman.area.Level1;
import ch.epfl.cs107.play.game.superpacman.area.Level2;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

public class SuperPacman extends RPG{
	private SuperPacmanPlayer pacman;
	
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
			SuperPacmanArea area = (SuperPacmanArea) (setCurrentArea("superpacman/Level0",true));
			pacman = new SuperPacmanPlayer(area, Orientation.UP, area.getPlayerSpawnPosition(),"pacman");
			initPlayer(pacman);
			return true;
		}
		return false;
	}
}
