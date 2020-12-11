/* 
 * Author: Maxime Hilbig
 * Date: 27.11.2020
 */
package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.superpacman.SuperPacman;
import ch.epfl.cs107.play.game.tutosSolution.Tuto2Behavior;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Window;

public abstract class SuperPacmanArea extends Area implements Logic {
	private SuperPacmanBehavior behavior;
	private int numberOfDiamonds = 0;

	protected abstract void createArea();
	
	protected void addDiamonds() {
		numberOfDiamonds++;
	}
	public void removeDiamonds() {
		numberOfDiamonds--;
	}
	
	public int getNumberOfDiamonds() {
		return numberOfDiamonds;
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
