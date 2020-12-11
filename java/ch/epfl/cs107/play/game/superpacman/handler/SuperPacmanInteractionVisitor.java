/* 
 * Author: Maxime Hilbig
 * Date: 30.11.2020
 */
package ch.epfl.cs107.play.game.superpacman.handler;

import ch.epfl.cs107.play.game.areagame.actor.CollectableAreaEntity;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.rpg.handler.RPGInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.SuperPacman;
import ch.epfl.cs107.play.game.superpacman.actor.Blinky;
import ch.epfl.cs107.play.game.superpacman.actor.Ghost;
import ch.epfl.cs107.play.game.superpacman.actor.Inky;
import ch.epfl.cs107.play.game.superpacman.actor.Pinky;

public interface SuperPacmanInteractionVisitor extends RPGInteractionVisitor {
	 default void interactWith(SuperPacman player){
	        // by default the interaction is empty
	    }
	 default void interactWith(CollectableAreaEntity collectableObjets) {
		 //by default the interaction is empty
	 }
	 
	 default void interactWith(Ghost ghost) {
		 
	 }
	 default void interactWith(Inky inky) {
		 
	 }
	 default void interactWith(Pinky pinky) {
		 
	 }
	 default void interactWith(Blinky blinky) {
		 
	 }
}
