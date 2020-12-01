/* 
 * Author: Maxime Hilbig
 * Date: 30.11.2020
 */
package ch.epfl.cs107.play.game.superpacman.handler;

import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.rpg.handler.RPGInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.SuperPacman;

public interface SuperPacmanInteractionVisitor extends RPGInteractionVisitor {
	 default void interactWith(SuperPacman player){
	        // by default the interaction is empty
	    }
}
