package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Path;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RandomGenerator;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Queue;

public class Pinky extends Ghost {

    private DiscreteCoordinates targetPos;
    private static final int ANIMATION_DURATION = 8;
    private Animation[] animations;
    private static final int MAX_DISTANCE_WHEN_SCARED = 5;
    public Path graphicPath;
    private boolean alreadyScared;
    private boolean alreadyNotAfraid;
    private boolean alreadySeesAPlayer;
    private boolean notSeenAPlayerBefore;
    protected Queue<Orientation> path;

    public Pinky(Area area, Orientation orientation, DiscreteCoordinates position, String name) {
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
                alreadyNotAfraid = false;
            }
        }
        if (!affraid) {
            if (!alreadyNotAfraid) {
                findTargetPosition();
                path = ((SuperPacmanArea) getOwnerArea()).getGraph().shortestPath(getCurrentMainCellCoordinates(), targetPos);
                alreadyNotAfraid = true;
                alreadyScared = false;
            }
        }
        if (seesPlayer == null) {
            if(!alreadySeesAPlayer) {
                findTargetPosition();
                path = ((SuperPacmanArea) getOwnerArea()).getGraph().shortestPath(getCurrentMainCellCoordinates(), targetPos);
                notSeenAPlayerBefore = false;
                alreadySeesAPlayer = true;
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
            move(12);
        }
    }

    public static float distanceBetween(DiscreteCoordinates a, DiscreteCoordinates b) {
        return (float) Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
    }

    public Orientation getNextOrientation() {
        if(seesPlayer != null) {
            findTargetPosition();
            path = ((SuperPacmanArea) getOwnerArea()).getGraph().shortestPath(getCurrentMainCellCoordinates(), targetPos);
        }
        return path.poll();
    }

    public void extractsprites() {
        Sprite[][] sprites = RPGSprite.extractSprites("superpacman/ghost.pinky", 2, 1, 1, this, 16, 16,
                new Orientation[]{Orientation.UP, Orientation.RIGHT, Orientation.DOWN, Orientation.LEFT});
        animations = Animation.createAnimations(ANIMATION_DURATION / 4, sprites);
    }

    public void setAfraid() {
        if(seesPlayer != null){
            int MAX_RANDOM_ATTEMPT = 200;
            for (int i = 0; i < MAX_RANDOM_ATTEMPT; i++) {
                int height = getOwnerArea().getHeight();
                int width = getOwnerArea().getWidth();
                float distance;
                DiscreteCoordinates randomCoordinates;

                do {
                    int randomX = RandomGenerator.getInstance().nextInt(height);
                    int randomY = RandomGenerator.getInstance().nextInt(width);
                    randomCoordinates = new DiscreteCoordinates(randomX, randomY);
                    distance = distanceBetween(seesPlayer.getPacmanPos(), randomCoordinates);
                    this.targetPos = randomCoordinates;
                } while ((distance < MAX_DISTANCE_WHEN_SCARED) || ((SuperPacmanArea) getOwnerArea()).getGraph().shortestPath(getCurrentMainCellCoordinates(), targetPos) == null);
            }
        } else {
            getNextOrientation();
        }
    }

    public void setNotAfraid() {
        int height = getOwnerArea().getHeight();
        int width = getOwnerArea().getWidth();
        DiscreteCoordinates randomCoordinates;
        do {
            int randomX = RandomGenerator.getInstance().nextInt(height);
            int randomY = RandomGenerator.getInstance().nextInt(width);
            randomCoordinates = new DiscreteCoordinates(randomX, randomY);
            this.targetPos = randomCoordinates;
        } while(((SuperPacmanArea) getOwnerArea()).getGraph().shortestPath(getCurrentMainCellCoordinates(), targetPos)==null );
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
