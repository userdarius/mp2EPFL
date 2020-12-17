package ch.epfl.cs107.play.game.superpacman.handler;

import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.actor.SuperPacmanPlayer;

public interface GhostInteractionHandler extends AreaInteractionVisitor {
    default void interactWith(SuperPacmanPlayer mem) {
    }
}

