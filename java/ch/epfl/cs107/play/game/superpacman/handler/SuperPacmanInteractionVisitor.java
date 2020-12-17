package ch.epfl.cs107.play.game.superpacman.handler;

import ch.epfl.cs107.play.game.areagame.actor.CollectableAreaEntity;
import ch.epfl.cs107.play.game.rpg.handler.RPGInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.SuperPacman;
import ch.epfl.cs107.play.game.superpacman.actor.Ghost;

public interface SuperPacmanInteractionVisitor extends RPGInteractionVisitor {
    default void interactWith(SuperPacman player){
        // by default the interaction is empty
    }
    default void interactWith(CollectableAreaEntity collectableObjets) {
        //by default the interaction is empty
    }

    default void interactWith(Ghost ghost) {

    }
}
