
package ch.epfl.cs107.play.game.superpacman.actor;

import java.awt.Color;
import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.CollectableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.rpg.actor.Player;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.area.Level1;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.game.superpacman.handler.GhostInteractionHandler;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class SuperPacmanPlayer extends Player {
	private float hp;
	private final TextGraphics message;
	private Orientation desiredOrientation;

	private final int SPEED = 8;
	private static final int ANIMATION_DURATION = 8;
	private Animation[] animations;
	private static float timer;
	
	
	private static int life = 3;
	private static int score = 0;

	SuperPacmanPlayerStatusGUI status = new SuperPacmanPlayerStatusGUI();

	public SuperPacmanPlayer(Area area, Orientation orientation, DiscreteCoordinates coordinates, String name) {
		super(area, orientation, coordinates);
		this.hp = 10;
		message = new TextGraphics(Integer.toString((int)hp), 0.4f, Color.BLUE);
		message.setParent(this);
		message.setAnchor(new Vector(-0.3f, 0.1f));
		extractsprites();
		desiredOrientation = getOrientation();
		

	}
	public boolean invulnerable() {
		return timer > 0;
	}
	
	public void update(float deltaTime) {
		if (timer > 0) {
			timer -= deltaTime;

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
		
		if(isDisplacementOccurs()) {
			animations[getOrientation().ordinal()].update(deltaTime);
		}

		if (!isDisplacementOccurs() && getOwnerArea().canEnterAreaCells(this, Collections.singletonList(getCurrentMainCellCoordinates().jump(desiredOrientation.toVector())))) {
			Orientation previousOrientation = getOrientation();
			orientate(desiredOrientation);// le faire tourner
			if (previousOrientation != getOrientation()) {
				animations[getOrientation().ordinal()].reset();	
				}

			int SPEED = 6;
			move(SPEED);
			
		}
		
		if(!isDisplacementOccurs()) {
			animations[getOrientation().ordinal()].reset();
		}

		super.update(deltaTime);
		
	}
	
	private class SuperPacmanPlayerHandler implements SuperPacmanInteractionVisitor {
		public void interactWith (Door door) {
			setIsPassingADoor(door);    //Objet pour qu'il puisse passer les portes
		}
		public void interactWith (CollectableAreaEntity collectableAreaEntity) {
			collectableAreaEntity.isTaken();
			score += collectableAreaEntity.getValue();
			if (collectableAreaEntity instanceof Diamond) {
				((SuperPacmanArea)getOwnerArea()).removeDiamonds();
				
			}
			if (collectableAreaEntity instanceof Bonus) {
				timer = 10;
				
			}

		}
		public void interactWith(Ghost ghost) {
			
			if (invulnerable()) {
				score += ghost.getGhostScore();
				ghost.respawnGhost();
				
			} else {
				pacmanGetsEaten();
			}
			
		}
	}

	public static int getLife() {
		return life;
	}
	public void loseLifePoint() {
		life = getLife() - 1;
	}

	public static int getScore() {
		return score;
	}
	
	public Area getOwnerArea() {
		return super.getOwnerArea();
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public List<DiscreteCoordinates> getFieldOfViewCells() {
		return null;
	}

	@Override
	public boolean wantsCellInteraction() {
		return true;
	}

	@Override
	public boolean wantsViewInteraction() {
		return false;
	}

	@Override
	public void interactWith(Interactable other) {
		other.acceptInteraction(new SuperPacmanPlayerHandler());  //Gestion d'interaction faite par Handler
		
	}

	@Override
	public boolean takeCellSpace() {  //Il sera traversable, donc ne prend pas toute la place de la cellule
		return false;
	}

	@Override
	public boolean isCellInteractable() {
		return false;
	}

	@Override
	public boolean isViewInteractable() {

		return true;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((GhostInteractionHandler)v).interactWith(this);
		
	}
	
	
	//Deuxieme point du 4.2
	public void pacmanGetsEaten() {
		loseLifePoint();
		getOwnerArea().leaveAreaCells(this, getEnteredCells());
		setCurrentPosition(((SuperPacmanArea)getOwnerArea()).getPlayerSpawnPosition().toVector());
		getOwnerArea().enterAreaCells(this, getCurrentCells() );
		((SuperPacmanArea) getOwnerArea()).BackToRefuge();
		resetMotion();
	}


	@Override
	public void draw(Canvas canvas) {
		animations[getOrientation().ordinal()].draw(canvas);
		status.draw(canvas);

	}
	
	
	public void extractsprites() {		
		Sprite[][] sprites = RPGSprite.extractSprites("superpacman/pacman", 4, 1, 1, this, 64, 64,
                new Orientation[] {Orientation.DOWN, Orientation.LEFT, Orientation.UP, Orientation.RIGHT});
        animations = Animation.createAnimations(ANIMATION_DURATION / 4, sprites);
	}
	
}
