package ch.epfl.cs107.play.game.superpacman.area;

import java.util.ArrayList;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.AreaGraph;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.actor.*;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class SuperPacmanBehavior extends AreaBehavior {


	public AreaGraph grille = new AreaGraph();
	private boolean hasLeftEdge(int x , int y){
		return ( x > 1 && (((SuperPacmanCell) getCell(x-1,y)).type != SuperPacman2CellType.WALL));
	}
	private boolean hasRightEdge (int x, int y){
		return (x < getWidth()-1 && (((SuperPacmanCell) getCell(x+1,y)).type != SuperPacman2CellType.WALL));
	}
	private boolean hasDownEdge (int x, int y){
		return (y >1 && (((SuperPacmanCell) getCell(x,y-1)).type != SuperPacman2CellType.WALL));
	}
	private boolean hasTopEdge (int x, int y){
		return ( y < getHeight()-1 && (((SuperPacmanCell) getCell(x,y+1)).type != SuperPacman2CellType.WALL));

	}

	public SuperPacmanBehavior(Window window, String name) {
		super(window, name);

		int height = getHeight();
		int width = getWidth();
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				SuperPacman2CellType color = SuperPacman2CellType.toType(getRGB(height - 1 - y, x));
				setCell(x, y, new SuperPacmanCell(x, y, color));
			}
		}
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if ((x > 0 && SuperPacman2CellType.toType(getRGB(getHeight() - 1 - y, x)) != SuperPacman2CellType.WALL)) {
					grille.addNode(new DiscreteCoordinates(x,y), hasLeftEdge(x,y), hasTopEdge(x,y), hasRightEdge(x,y), hasDownEdge(x,y));
				}
			}
		}
	}


	protected void registerActors(Area area) {

		for (int y = 0; y < getHeight(); ++y) {
			for (int x = 0; x < getWidth(); ++x) {
				if (SuperPacman2CellType.toType(getRGB(getHeight() - 1 - y, x)) == SuperPacman2CellType.WALL) {
					boolean[][] neighborhood = neighborhood(x, y);
					DiscreteCoordinates coordinates = new DiscreteCoordinates(x, y);
					Wall wall = new Wall(area, coordinates, (neighborhood));
					area.registerActor(wall);
				}
				if (SuperPacman2CellType.toType(getRGB(getHeight() - 1 - y, x)) == SuperPacman2CellType.FREE_WITH_DIAMOND) {
					DiscreteCoordinates coordinates = new DiscreteCoordinates(x, y);
					Diamond diamond = new Diamond(area, Orientation.DOWN, coordinates);
					area.registerActor(diamond);
					((SuperPacmanArea) area).addDiamonds();
				}
				if (SuperPacman2CellType.toType(getRGB(getHeight() - 1 - y, x)) == SuperPacman2CellType.FREE_WITH_CHERRY) {
					DiscreteCoordinates coordinates = new DiscreteCoordinates(x, y);
					Cherry cherry = new Cherry(area, Orientation.UP, coordinates);
					area.registerActor(cherry);
				}
				if (SuperPacman2CellType.toType(getRGB(getHeight() - 1 - y, x)) == SuperPacman2CellType.FREE_WITH_BONUS) {
					DiscreteCoordinates coordinates = new DiscreteCoordinates(x, y);
					Bonus bonus = new Bonus(area, Orientation.UP, coordinates);
					area.registerActor(bonus);
				}
				if (SuperPacman2CellType.toType(getRGB(getHeight() - 1 - y, x)) == SuperPacman2CellType.FREE_WITH_BLINKY) {
					DiscreteCoordinates coordinates = new DiscreteCoordinates(x, y);
					Blinky blinky = new Blinky(area, Orientation.DOWN, coordinates, "blinky");
					area.registerActor(blinky);
					AffraidGhost.add(blinky);
				}
				if (SuperPacman2CellType.toType(getRGB(getHeight() - 1 - y, x)) == SuperPacman2CellType.FREE_WITH_INKY) {
					DiscreteCoordinates coordinates = new DiscreteCoordinates(x, y);
					Inky inky = new Inky(area, Orientation.UP, coordinates, "inky");
					area.registerActor(inky);
					AffraidGhost.add(inky);
				}
            if (SuperPacman2CellType.toType(getRGB(getHeight() - 1 - y, x)) == SuperPacman2CellType.FREE_WITH_PINKY) {
               DiscreteCoordinates coordinates = new DiscreteCoordinates(x, y);
               Pinky pinky = new Pinky(area, Orientation.UP, coordinates, "pinky");
               area.registerActor(pinky);
               AffraidGhost.add(pinky);
            }
			}
		}
	}

	public boolean[][] neighborhood(int x, int y) {      // je verifie le voisinage du mur
		boolean[][] tab = new boolean[3][3];
		for (int m = x - 1, r = 0; m < x + 2; m++, r++) {
			for (int n = y + 1, s = 0; n > y - 2; n--, s++) {

				tab[r][s] = (cellExists(m, n) && SuperPacman2CellType.toType(getRGB(getHeight() - 1 - n, m)) == SuperPacman2CellType.WALL);
			}
		}

		return tab;

	}


	private boolean cellExists(int x, int y) {    //Avoid to getCell that do not exist       //Because we do not use it outside of this class
		return ((x >= 0) && y >= 0 && x < getWidth() && y < getHeight());

	}

	public ArrayList<Ghost> AffraidGhost = new ArrayList<Ghost>();

	public ArrayList<Ghost> GetAffraidGhost() {
		return AffraidGhost;

	}
	public void Affraid(Ghost g) {
		for (int i = 0; i < AffraidGhost.size(); i++) {
			AffraidGhost.get(i);
		}
	}



	enum SuperPacman2CellType {
		NONE(0), // never used as real content
		WALL(-16777216), //black
		FREE_WITH_DIAMOND(-1), //white
		FREE_WITH_BLINKY(-65536), //red
		FREE_WITH_PINKY(-157237), //pink
		FREE_WITH_INKY(-16724737), //cyan
		FREE_WITH_CHERRY(-36752), //light red
		FREE_WITH_BONUS(-16478723), //light blue
		FREE_EMPTY(-6118750); // sort of gray

		final int type;

		SuperPacman2CellType(int type) {
			this.type = type;

		}

		public static SuperPacman2CellType toType(int type) {
			for (SuperPacman2CellType ict : SuperPacman2CellType.values()) {
				if (ict.type == type)
					return ict;
			}
			// When you add a new color, you can print the int value here before assign it to a type
			System.out.println(type);
			return NONE;
		}
	}

	class SuperPacmanCell extends AreaBehavior.Cell {
		private final SuperPacman2CellType type;

		public SuperPacmanCell(int x, int y, SuperPacman2CellType type) {
			super(x, y);
			this.type = type;
		}

		@Override
		protected boolean canLeave(Interactable entity) {
			return true;
		}

		@Override
		protected boolean canEnter(Interactable entity) {
			return !hasNonTraversableContent();
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
		public void acceptInteraction(AreaInteractionVisitor v) {
		}
	}
}









