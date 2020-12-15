
package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.awt.Color;
import java.util.Queue;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.*;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.SuperPacman;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanBehavior;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RandomGenerator;
import ch.epfl.cs107.play.window.Canvas;

public class Inky extends Ghost{
	private DiscreteCoordinates targetPos = new DiscreteCoordinates(0,0);
	private static final int ANIMATION_DURATION = 8;
	private final int SPEED = 6;
	private Animation[] animations;
	private Orientation desiredOrientation;
	private static final int MAX = 100;
	private static final int MAX_DISTANCE_WHEN_SCARED = 5;
	private static final int MAX_DISTANCE_WHEN_NOT_SCARED = 10;
	public Path graphicPath;



	public Inky(Area area, Orientation orientation, DiscreteCoordinates position, String name) {
		super(area, orientation, position);
		extractsprites();
		desiredOrientation = getOrientation();
	}

	public void update(float deltaTime) {
		super.update(deltaTime);

	}

	public void extractsprites() {
		Sprite[][] sprites = RPGSprite.extractSprites("superpacman/ghost.inky", 2, 1, 1, this, 16, 16,
				new Orientation[] {Orientation.UP, Orientation.RIGHT, Orientation.DOWN, Orientation.LEFT});
		animations = Animation.createAnimations(ANIMATION_DURATION / 4, sprites);
	}

	public static float distanceBetween(DiscreteCoordinates a, DiscreteCoordinates b){
		return (float) Math.sqrt((a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y));
	}


	public Orientation getNextOrientation() {
		Queue<Orientation> path;
		do {
			path = ((SuperPacmanArea) getOwnerArea()).getGraph().shortestPath(getCurrentMainCellCoordinates(), targetPos);
		} while (path == null);
		//this.graphicPath = new Path(this.getPosition(), new LinkedList<Orientation>(path));
		return path.poll();
	}


	public void setAfraid(){
		int height = getOwnerArea().getHeight();
		int width = getOwnerArea().getWidth();
		int randomX = RandomGenerator.getInstance().nextInt(width);
		int randomY = RandomGenerator.getInstance().nextInt(height);
		DiscreteCoordinates randomCoordinates = new DiscreteCoordinates(randomX,randomY);
		float distance = distanceBetween(refuge, randomCoordinates);
		if(distance <= MAX_DISTANCE_WHEN_SCARED){
			this.targetPos = randomCoordinates;
		}
	}

	public void setNotAfraid(){
		int height = getOwnerArea().getHeight();
		int width = getOwnerArea().getWidth();
		int randomX = RandomGenerator.getInstance().nextInt(width);
		int randomY = RandomGenerator.getInstance().nextInt(height);
		DiscreteCoordinates randomCoordinates = new DiscreteCoordinates(randomX,randomY);
		float distance = distanceBetween(refuge, randomCoordinates);
		if(distance <= MAX_DISTANCE_WHEN_NOT_SCARED){
			this.targetPos = randomCoordinates;
		}
	}

	public void setTargetPacman(){
		int height = getOwnerArea().getHeight();
		int width = getOwnerArea().getWidth();
		int randomX = RandomGenerator.getInstance().nextInt(width);
		int randomY = RandomGenerator.getInstance().nextInt(height);
		DiscreteCoordinates randomCoordinates = new DiscreteCoordinates(randomX,randomY);
		float distance = distanceBetween(refuge, randomCoordinates);
		this.targetPos = getPacmanPos();
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
		if (getAfraid()) {
			super.draw(canvas);
		} else {
			animations[getOrientation().ordinal()].draw(canvas);
		}
		//graphicPath.draw(canvas);
	}
}