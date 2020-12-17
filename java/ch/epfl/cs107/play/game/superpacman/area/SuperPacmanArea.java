package ch.epfl.cs107.play.game.superpacman.area;

import java.util.ArrayList;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGraph;
import ch.epfl.cs107.play.game.superpacman.SuperPacman;
import ch.epfl.cs107.play.game.superpacman.actor.Ghost;
import ch.epfl.cs107.play.game.tutosSolution.Tuto2Behavior;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Window;

public abstract class SuperPacmanArea extends Area implements Logic {
	private SuperPacmanBehavior behavior;
	private int numberOfDiamonds = 0;

	protected abstract void createArea();

	public AreaGraph getGraph(){
		return behavior.grille;
	}

	protected void addDiamonds() {
		numberOfDiamonds++;
	}

	public void removeDiamonds() {
		numberOfDiamonds--;
	}

	public int getNumberOfDiamonds() {
		return numberOfDiamonds;
	}

	public void affraid() {
		ArrayList<Ghost> tab = behavior.AffraidGhost;
		for (int i = 0; i < tab.size(); i++) {
			behavior.AffraidGhost.get(i).affraid();
			
		}
	}
	
	public void notAffraid() {
		ArrayList<Ghost> tab = behavior.AffraidGhost;
		for(int i = 0; i < tab.size(); i++) {
			behavior.AffraidGhost.get(i).disaffraid();
		}
	}

	public void BackToRefuge() {  //  Quand le ghost mange un pacman, il retourne dans son carré
		for(Ghost ghost : behavior.GetAffraidGhost()) {
		ghost.respawnGhost();
		}
	}

	@Override
	public boolean isOn() {
		return numberOfDiamonds == 0;
	}
	
	@Override
	public boolean isOff() {
		return !(numberOfDiamonds == 0);
	}

	@Override
	public float getIntensity() {
		if (isOn()) {
			return 1.f;
		}
		else {
			return 0.f;
		}
	}
	
	@Override
	public final float getCameraScaleFactor() {
		return 15.f;
	}

	public abstract DiscreteCoordinates getPlayerSpawnPosition();

	   public boolean begin(Window window, FileSystem fileSystem) {

		   if (super.begin(window, fileSystem)) {
	            // Set the behavior map

	        	behavior = new SuperPacmanBehavior(window, getTitle());
	            setBehavior(behavior);
	            behavior.registerActors(this);
	            createArea();
	            return true;
	        }
	        return false;
	    }

}
