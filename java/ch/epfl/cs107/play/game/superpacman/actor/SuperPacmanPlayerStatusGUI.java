/* 
 * Author: Maxime Hilbig
 * Date: 04.12.2020
 */
package ch.epfl.cs107.play.game.superpacman.actor;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.TextAlign;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;


 class SuperPacmanPlayerStatusGUI implements Graphics {

	 @Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		float width = canvas.getScaledWidth();
		float height = canvas.getScaledHeight();
		Vector anchor = canvas.getTransform().getOrigin().sub(new Vector((width / 2), (height / 2)));
		
		
		for (int i = 0; i < 5; i++) {
			//profondeur de l'image
			int DEPTH = 0;
			if(i<SuperPacmanPlayer.getLife()) {
		ImageGraphics life = new
				ImageGraphics(ResourcePath.getSprite("superpacman/lifeDisplay"),
				1.f, 1.f, new RegionOfInterest(0, 0, 64, 64),
				anchor.add(new Vector(i+1, height - 1.375f)), 1, DEPTH);
				life.draw(canvas);
		} else {
			ImageGraphics life = new
					ImageGraphics(ResourcePath.getSprite("superpacman/lifeDisplay"),
					1.f, 1.f, new RegionOfInterest(64, 0, 64, 64),
					anchor.add(new Vector(i+1, height - 1.375f)), 1, DEPTH);
					life.draw(canvas);
			
		}
		}
		
		TextGraphics score = new TextGraphics("Score : " + SuperPacmanPlayer.getScore() , 1, Color.YELLOW, Color.BLACK, 0.01f, false, true, anchor.add(new Vector(6.5f, height -1.375f)) );
		score.draw(canvas);
	}




}

