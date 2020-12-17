package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.handler.GhostInteractionHandler;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Ghost extends MovableAreaEntity implements Interactor {


	private static final int radius = 5;
	protected boolean affraid;

	public final DiscreteCoordinates refuge;

	protected SuperPacmanPlayer seesPlayer;


	//private Animation[] animations = Animation.createAnimations(ANIMATION_DURATION / 4, sprites);
	private static final int ANIMATION_DURATION = 8;
	private GhostHandler GhostHandler;
	Sprite[] sprites = RPGSprite.extractSprites("superpacman/ghost.afraid", 2, 1, 1, this, 16, 16);

	private Animation GhostAfraid = new Animation(2, sprites );

	SuperPacmanPlayerStatusGUI status = new SuperPacmanPlayerStatusGUI();


	public Ghost(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);

		GhostHandler = new GhostHandler();
		this.refuge = position;
	}
	public void update(float deltaTime) {
		super.update(deltaTime);
		GhostAfraid.update(deltaTime);

	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	public int getGhostScore() {
		int GHOST_SCORE = 500;
		return GHOST_SCORE;
	}

	public DiscreteCoordinates getRefuge() {
		return refuge;
	}




	public void respawnGhost() {
		if(getEnteredCells() != null){
			getOwnerArea().leaveAreaCells(this, getEnteredCells());
		}
		setCurrentPosition(refuge.toVector());
		getOwnerArea().enterAreaCells(this, getCurrentCells());
		resetMotion();
	}

	public void affraid() {
		affraid = true;
	}

	public void disaffraid() {
		affraid = false;
	}

	public boolean getAfraid() {
		return affraid;
	}

	public SuperPacmanPlayer getSeesPlayer() {
		return seesPlayer;
	}
	public void setSeesPlayer(SuperPacmanPlayer player) {
		seesPlayer = player;

	}

	@Override
	public boolean takeCellSpace() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCellInteractable() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isViewInteractable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((SuperPacmanInteractionVisitor)v).interactWith(this);

	}

	@Override
	public List<DiscreteCoordinates> getFieldOfViewCells() {
		List<DiscreteCoordinates> fieldOfView = new ArrayList<DiscreteCoordinates>();
		for (int i = -radius + getCurrentMainCellCoordinates().x; i <= radius + getCurrentMainCellCoordinates().x; i++) {     //Doit commencer a sa cellule de départ
			for(int j = -radius + getCurrentMainCellCoordinates().y; j <= radius + getCurrentMainCellCoordinates().y; j++)  { //La ca ne marche pas je commence a 5
				if (cellExists(i, j)) {
					fieldOfView.add(new DiscreteCoordinates(i,j));
				}
			}
		}
		return fieldOfView;
	}

	protected boolean knowsPacman(){
		return (seesPlayer != null);


	}
	public class GhostHandler implements GhostInteractionHandler{
		public void interactWith(SuperPacmanPlayer player){
			seesPlayer = player;

		}
	}



	@Override
	public void draw(Canvas canvas) {
		GhostAfraid.draw(canvas);
	}

	private boolean cellExists(int x, int y) {    //Avoid to getCell that do not exist       //Because we do not use it outside of this class
		return ((x >= 0) && y >= 0 && x < getOwnerArea().getWidth() && y < getOwnerArea().getHeight());
	}


   /*public void positionPacman (SuperPacmanPlayer player) {
      List<DiscreteCoordinates> viewRadius = this.getFieldOfViewCells();
      for (DiscreteCoordinates discreteCoordinates : viewRadius) {
         if (discreteCoordinates.equals(player.getCurrentCells().get(0))) {
            positionPacman = player.getCurrentCells().get(0);
         }
      }
   }*/



	@Override
	public boolean wantsCellInteraction() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean wantsViewInteraction() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void interactWith(Interactable other) {
		other.acceptInteraction(GhostHandler);

	}
   /*public class GhostHandler implements SuperPacmanInteractionVisitor {
      public void interactWith(SuperPacmanPlayer player) {

         memory = player;
      }
   }*/
}

