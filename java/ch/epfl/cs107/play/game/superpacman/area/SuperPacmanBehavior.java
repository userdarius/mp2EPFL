/* 
 * Author: Maxime Hilbig
 * Date: 27.11.2020
 */
package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.tutosSolution.Tuto2Behavior.Tuto2Cell;
import ch.epfl.cs107.play.game.tutosSolution.Tuto2Behavior.Tuto2CellType;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;
import ch.epfl.cs107.play.game.superpacman.actor.Wall;

public class SuperPacmanBehavior extends AreaBehavior{
	
	public SuperPacmanBehavior(Window window, String name) {
		super(window, name);
		 
		    int height = getHeight();
		    int width = getWidth();
		    for(int y = 0; y < height; y++) {
		        for (int x = 0; x < width ; x++) {
		            SuperPacman2CellType color = SuperPacman2CellType.toType(getRGB(height-1-y, x));
		            setCell(x,y, new SuperPacmanCell(x,y,color));
		        }
		    }
		}
	
	protected void registerActors(Area area) {
		for (int y = 0; y < getHeight(); ++y) {
			for (int x = 0; x < getWidth(); ++x) {
				if (SuperPacmanBehavior.SuperPacman2CellType.toType(getRGB(getHeight()-1-y, x)) == SuperPacman2CellType.WALL) {
					boolean[][] neighborhood = neighborhood(x,y);
					DiscreteCoordinates coordinates = new DiscreteCoordinates(x,y);
					Wall wall = new Wall(area, coordinates, (neighborhood));
					area.registerActor(wall);
				}
				
			}
		}
	}
	
	public boolean[][] neighborhood(int x, int y) {      // je verifie le voisinage du mur
		boolean [][] tab = new boolean[3][3];
		for (int m = x -1, r = 0; m < x+2 ; m++, r++) {
			for (int n = y+1, s = 0; n > y-2 ; n--, s++) {
			
				tab[r][s] = (cellexists(m,n) && SuperPacmanBehavior.SuperPacman2CellType.toType(getRGB(getHeight()-1-n, m )) == SuperPacman2CellType.WALL);
			}
		}
		
		return tab;
		
	}
	
	private boolean cellexists(int x, int y) {    //Avoid to getCell that do not exist       //Because we do not use it outside of this class
		return ((x > 0) && y > 0 && x < getWidth() && y < getHeight());
		
	}
	



		// TODO Auto-generated constructor stub


	enum SuperPacman2CellType{
		NONE(0), // never used as real content
		WALL ( -16777216), //black
		FREE_WITH_DIAMOND(-1), //white
		FREE_WITH_BLINKY (-65536), //red
		FREE_WITH_PINKY ( -157237), //pink
		FREE_WITH_INKY ( -16724737), //cyan
		FREE_WITH_CHERRY (-36752), //light red
		FREE_WITH_BONUS ( -16478723), //light blue
		FREE_EMPTY ( -6118750); // sort of gray
		
		final int type;
		
		
		SuperPacman2CellType(int type) {
			this.type = type;
			
		}
		
		public static SuperPacman2CellType toType(int type){
			for(SuperPacman2CellType ict : SuperPacman2CellType.values()){
				if(ict.type == type)
					return ict;
			}
			// When you add a new color, you can print the int value here before assign it to a type
			System.out.println(type);
			return NONE;
		}
		
	}
	
		
/**
 * Default Tuto2Behavior Constructor
 * @param window (Window), not null
 * @param name (String): Name of the Behavior, not null
 */
	
	


	
		class SuperPacmanCell extends AreaBehavior.Cell{
		private final SuperPacman2CellType type;
		
		/**
		 * Default Tuto2Cell Constructor
		 * @param x (int): x coordinate of the cell
		 * @param y (int): y coordinate of the cell
		 * @param type (EnigmeCellType), not null
		 */
		public  SuperPacmanCell(int x, int y, SuperPacman2CellType type){
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


	
	
