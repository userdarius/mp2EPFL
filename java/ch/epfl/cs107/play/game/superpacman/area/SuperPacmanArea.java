/* 
 * Author: Maxime Hilbig
 * Date: 27.11.2020
 */
package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.superpacman.SuperPacman;
import ch.epfl.cs107.play.game.tutosSolution.Tuto2Behavior;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

public abstract class SuperPacmanArea extends Area {
	private SuperPacmanBehavior behavior;

	protected abstract void createArea();
	
	@Override
	public final float getCameraScaleFactor() {
		return 15.f;
	}
	
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
