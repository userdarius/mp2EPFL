/* 
 * Author: Maxime Hilbig
 * Date: 27.11.2020
 */
package ch.epfl.cs107.play.game.superpacman.actor;

import java.awt.Color;
import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.actor.SuperPacmanPlayerStatusGUI;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.rpg.actor.Player;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class SuperPacmanPlayer extends Player {
	private float hp;
	private TextGraphics message;
	private Sprite pacman;
	private Orientation desiredOrientation;
	private final int SPEED = 6;
	private int score = 0;
	private static int life = 3;
	
	SuperPacmanPlayerStatusGUI status = new SuperPacmanPlayerStatusGUI();
	

	public SuperPacmanPlayer(Area area, Orientation orientation, DiscreteCoordinates coordinates, String name) {
		super(area, orientation, coordinates);
		this.hp = 10;
		message = new TextGraphics(Integer.toString((int)hp), 0.4f, Color.BLUE);
		message.setParent(this);
		message.setAnchor(new Vector(-0.3f, 0.1f));
		pacman = new Sprite("superpacman/bonus", 1.f, 1.f,this);
		desiredOrientation= getOrientation();

		resetMotion();
	}
	
	public void update(float deltaTime) {
		if (hp > 0) {
			hp -=deltaTime;
			message.setText(Integer.toString((int)hp));
		}
		if (hp < 0) hp = 0.f;
		Keyboard keyboard= getOwnerArea().getKeyboard();
		if (keyboard.get(Keyboard.LEFT).isDown()) {
			desiredOrientation = Orientation.LEFT;
		} else if (keyboard.get(Keyboard.RIGHT).isDown()) {
			desiredOrientation = Orientation.RIGHT;
		} else if (keyboard.get(Keyboard.UP).isDown()) {
			desiredOrientation = Orientation.UP;
		} else if (keyboard.get(Keyboard.DOWN).isDown()) {
			desiredOrientation = Orientation.DOWN;
		}
		
		if (!isDisplacementOccurs() && getOwnerArea().canEnterAreaCells(this, Collections.singletonList(getCurrentMainCellCoordinates().jump(desiredOrientation.toVector())))) {
			orientate(desiredOrientation); // le faire tourner
			move(SPEED);
			
		}
		
		
		
		
		super.update(deltaTime);
		
	}
	
	private class SuperPacmanPlayerHandler implements SuperPacmanInteractionVisitor {
		public void interactWith (Door door) {
			setIsPassingADoor(door);    //Objet pour qu'il puisse passer les portes
		}
	}
	
	
	public static int getLife() {
		return life;
	}
	
	
	
	
	
	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public List<DiscreteCoordinates> getFieldOfViewCells() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean wantsCellInteraction() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean wantsViewInteraction() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void interactWith(Interactable other) {
		other.acceptInteraction(new SuperPacmanPlayerHandler());  //Gestion d'interaction faite par Handler
		
	}

	@Override
	public boolean takeCellSpace() {  //Il sera traversable, donc ne prend pas toute la place de la cellule
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCellInteractable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isViewInteractable() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((SuperPacmanInteractionVisitor)v).interactWith(this);
		
	}

	@Override
	public void draw(Canvas canvas) {
		pacman.draw(canvas);
		status.draw(canvas);
	}

}
