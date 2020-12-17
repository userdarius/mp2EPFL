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
	private Animation[] animations;
	private static final int MAX_DISTANCE_WHEN_SCARED = 5;
	private static final int MAX_DISTANCE_WHEN_NOT_SCARED = 10;
	public Path graphicPath;
	private boolean alreadyScared;
	private boolean alreadyNotAffraid;
	private boolean alreadySeesAPlayer;
	private boolean notSeenAPlayerBefore;
	protected Queue<Orientation> path;


	public Inky(Area area, Orientation orientation, DiscreteCoordinates position, String name) {
		super(area, orientation, position);
		extractsprites();
		findTargetPosition();
		path = ((SuperPacmanArea) getOwnerArea()).getGraph().shortestPath(getCurrentMainCellCoordinates(), targetPos);

	}

	public void update(float deltaTime) {
		super.update(deltaTime);
		animations[getOrientation().ordinal()].update(deltaTime);

		if (getCurrentMainCellCoordinates() == targetPos) {
			findTargetPosition();
			path = ((SuperPacmanArea) getOwnerArea()).getGraph().shortestPath(getCurrentMainCellCoordinates(), targetPos);
		}

		if (path.size() == 0) {
			findTargetPosition();
			path = ((SuperPacmanArea) getOwnerArea()).getGraph().shortestPath(getCurrentMainCellCoordinates(), targetPos);
		}

		if (affraid) {
			if (!alreadyScared) {
				findTargetPosition();
				path = ((SuperPacmanArea) getOwnerArea()).getGraph().shortestPath(getCurrentMainCellCoordinates(), targetPos);
				alreadyScared = true;
				alreadyNotAffraid = false;
			}
		}

		if (!affraid) {
			if (!alreadyNotAffraid) {
				findTargetPosition();
				path = ((SuperPacmanArea) getOwnerArea()).getGraph().shortestPath(getCurrentMainCellCoordinates(), targetPos);
				alreadyNotAffraid = true;
				alreadyScared = false;
			}
		}

		if (seesPlayer == null) {
			if(!alreadySeesAPlayer) {
				findTargetPosition();
				path = ((SuperPacmanArea) getOwnerArea()).getGraph().shortestPath(getCurrentMainCellCoordinates(), targetPos);
				alreadySeesAPlayer = true;
				notSeenAPlayerBefore = false;
			}
		}
		if (seesPlayer != null) {
			if (!notSeenAPlayerBefore) {
				findTargetPosition();
				path = ((SuperPacmanArea) getOwnerArea()).getGraph().shortestPath(getCurrentMainCellCoordinates(), targetPos);
				notSeenAPlayerBefore = true;
				alreadySeesAPlayer = false;
			}
		}

		if (path == null) {
			findTargetPosition();
			path = ((SuperPacmanArea) getOwnerArea()).getGraph().shortestPath(getCurrentMainCellCoordinates(), targetPos);

		}

		if (!isDisplacementOccurs()) {
			orientate(getNextOrientation());
			move(18);
		}
	}

	public void extractsprites() {
		Sprite[][] sprites = RPGSprite.extractSprites("superpacman/ghost.inky", 2, 1, 1, this, 16, 16,
				new Orientation[]{Orientation.UP, Orientation.RIGHT, Orientation.DOWN, Orientation.LEFT});
		animations = Animation.createAnimations(ANIMATION_DURATION / 4, sprites);
	}

	public void setAfraid() {
		int height = getOwnerArea().getHeight();
		int width = getOwnerArea().getWidth();
		float distance;
		DiscreteCoordinates randomCoordinates;

		do {
			int randomX = RandomGenerator.getInstance().nextInt(height);
			int randomY = RandomGenerator.getInstance().nextInt(width);
			randomCoordinates = new DiscreteCoordinates(randomX, randomY);
			distance = distanceBetween(refuge, randomCoordinates);
			this.targetPos = randomCoordinates;
		} while ((distance > MAX_DISTANCE_WHEN_SCARED) || ((SuperPacmanArea) getOwnerArea()).getGraph().shortestPath(getCurrentMainCellCoordinates(), targetPos)==null );

	}

	public void setNotAfraid() {
		int height = getOwnerArea().getHeight();
		int width = getOwnerArea().getWidth();
		float distance;
		DiscreteCoordinates randomCoordinates;

		do {
			int randomX = RandomGenerator.getInstance().nextInt(height);
			int randomY = RandomGenerator.getInstance().nextInt(width);
			randomCoordinates = new DiscreteCoordinates(randomX, randomY);
			distance = distanceBetween(refuge, randomCoordinates);
			this.targetPos = randomCoordinates;
		} while(distance > MAX_DISTANCE_WHEN_NOT_SCARED || ((SuperPacmanArea) getOwnerArea()).getGraph().shortestPath(getCurrentMainCellCoordinates(), targetPos)==null );
	}

	public void findTargetPosition() {


		if (!getAfraid()) {
			if (knowsPacman()) {
				this.targetPos = getSeesPlayer().getPacmanPos();
			} else {
				setNotAfraid();
			}
		}else {
			setAfraid();
		}




	}

	public static float distanceBetween(DiscreteCoordinates a, DiscreteCoordinates b) {
		return (float) Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
	}

	public Orientation getNextOrientation() {

		//graphicPath = new      if (((SuperPacmanArea) getOwnerArea()).getGraph Path(this.getPosition(), new LinkedList<Orientation>(path));
		//System.out.println("path computed");
		if(seesPlayer != null) {
			findTargetPosition();
			path = ((SuperPacmanArea) getOwnerArea()).getGraph().shortestPath(getCurrentMainCellCoordinates(), targetPos);
		}


		return path.poll();

	}

	@Override
	public void draw(Canvas canvas) {
		if (getAfraid()) {
			super.draw(canvas);
		} else {
			animations[getOrientation().ordinal()].draw(canvas);
		}
		if (graphicPath != null) {
			graphicPath.draw(canvas);
		}
	}
}


