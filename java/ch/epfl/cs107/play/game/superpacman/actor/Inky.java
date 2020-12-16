
package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.*;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RandomGenerator;
import ch.epfl.cs107.play.window.Canvas;

public class Inky extends Ghost {
	private DiscreteCoordinates targetPos;
	private static final int ANIMATION_DURATION = 8;
	private final int SPEED = 6;
	private Animation[] animations;
	private static final int MAX = 100;
	private static final int MAX_DISTANCE_WHEN_SCARED = 5;
	private static final int MAX_DISTANCE_WHEN_NOT_SCARED = 10;
	public Path graphicPath;


	public Inky(Area area, Orientation orientation, DiscreteCoordinates position, String name) {
		super(area, orientation, position);
		extractsprites();
	}

	public void update(float deltaTime) {
		super.update(deltaTime);
		animations[getOrientation().ordinal()].update(deltaTime);

		if (!getAfraid()) {
			if (knowsPacman()) {
				System.out.println("knowspacman");
				this.targetPos = getPacmanPos();
			} else {
				setNotAfraid();
			}
		} else {
			setAfraid();
		}

		//Orientation desiredOrientation = getNextOrientation();
		if (!isDisplacementOccurs()) {
			Orientation previousOrientation = getNextOrientation();
			orientate(previousOrientation);
			move(SPEED);
		}
	}

	public void extractsprites() {
		Sprite[][] sprites = RPGSprite.extractSprites("superpacman/ghost.inky", 2, 1, 1, this, 16, 16,
				new Orientation[]{Orientation.UP, Orientation.RIGHT, Orientation.DOWN, Orientation.LEFT});
		animations = Animation.createAnimations(ANIMATION_DURATION / 4, sprites);
	}

	public static float distanceBetween(DiscreteCoordinates a, DiscreteCoordinates b) {
		return (float) Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
	}


	public Orientation getNextOrientation() {
		Queue<Orientation> path;
		if (((SuperPacmanArea) getOwnerArea()).getGraph().shortestPath(getCurrentMainCellCoordinates(), targetPos) != null) {
			System.out.println();
			path = ((SuperPacmanArea) getOwnerArea()).getGraph().shortestPath(getCurrentMainCellCoordinates(), targetPos);
			//graphicPath = new Path(this.getPosition(), new LinkedList<Orientation>(path));
			System.out.println("path computed");
			return path.poll();
		} else {
			int randomInt = RandomGenerator.getInstance().nextInt(4);
			System.out.println("random path commputed");
			return Orientation.fromInt(randomInt);
		}

	}

	public void setAfraid() {
		int height = getOwnerArea().getHeight();
		int width = getOwnerArea().getWidth();
		int randomX = RandomGenerator.getInstance().nextInt(height);
		int randomY = RandomGenerator.getInstance().nextInt(width);
		DiscreteCoordinates randomCoordinates = new DiscreteCoordinates(randomX, randomY);
		float distance = distanceBetween(refuge, randomCoordinates);
		if (distance <= MAX_DISTANCE_WHEN_SCARED) {
			this.targetPos = randomCoordinates;
		}
		else {
			setAfraid();
		}
	}


	public void setNotAfraid() {
		int height = getOwnerArea().getHeight();
		int width = getOwnerArea().getWidth();
		int randomX = RandomGenerator.getInstance().nextInt(height);
		int randomY = RandomGenerator.getInstance().nextInt(width);
		DiscreteCoordinates randomCoordinates = new DiscreteCoordinates(randomX, randomY);
		float distance = distanceBetween(refuge, randomCoordinates);
		if (distance <= MAX_DISTANCE_WHEN_NOT_SCARED) {
			this.targetPos = randomCoordinates;
		}
		else {
			setNotAfraid();
		}
	}


	@Override
	public void draw(Canvas canvas) {
		if (getAfraid()) {
			super.draw(canvas);
		} else {
			animations[getOrientation().ordinal()].draw(canvas);
		}
		/*if (graphicPath != null) {
			graphicPath.draw(canvas);
		}*/
	}
}