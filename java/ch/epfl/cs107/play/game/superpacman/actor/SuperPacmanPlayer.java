/* 
 * Author: Maxime Hilbig
 * Date: 27.11.2020
 */
package ch.epfl.cs107.play.game.superpacman.actor;

import java.awt.Color;
import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.Player;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class SuperPacmanPlayer extends Player {
	private float hp;
	private TextGraphics message;
	private Sprite sprite;
	private Orientation desiredOrientation;
	private final int SPEED = 6;
	

	public SuperPacmanPlayer(Area area, Orientation orientation, DiscreteCoordinates coordinates) {
		super(area, orientation, coordinates);
		this.hp = 10;
		message = new TextGraphics(Integer.toString((int)hp), 0.4f, Color.BLUE);
		message.setParent(this);
		message.setAnchor(new Vector(-0.3f, 0.1f));
		sprite = new Sprite("superpacman/bonus", 1.f, 1.f,this);

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
		return false;
	}

	@Override
	public boolean wantsViewInteraction() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void interactWith(Interactable other) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean takeCellSpace() {
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
		return false;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		
	}

}
