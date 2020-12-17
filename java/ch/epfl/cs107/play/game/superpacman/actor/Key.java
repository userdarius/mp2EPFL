package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.CollectableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class Key extends CollectableAreaEntity implements Logic{

	private boolean isCollected = false;
	
	public Key(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
	}
	
	private Sprite key = new Sprite("superpacman/key", 1.f, 1.f, this);

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public boolean takeCellSpace() {
		return false;
	}

	@Override
	public boolean isCellInteractable() {
		return true;
	}

	@Override
	public boolean isViewInteractable() {
		return false;
	}

	@Override
	public boolean isOn() {
		return isCollected;
	}

	@Override
	public boolean isOff() {
		return !isCollected;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((SuperPacmanInteractionVisitor)v).interactWith(this);
	}

	@Override
	public void draw(Canvas canvas) {
		key.draw(canvas);
	}

	@Override 
	public void isTaken() {
		super.isTaken();
		isCollected = true;
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
}
