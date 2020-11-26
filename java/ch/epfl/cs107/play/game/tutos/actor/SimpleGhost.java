
/*package ch.epfl.cs107.play.game.tutos.actor;


import java.awt.Color;

import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class SimpleGhost extends Entity {
	private Sprite sprite;
	private float niveauEnergie;
	private TextGraphics hpText;
	
	public SimpleGhost (Vector position, String spriteName) {
		super(position);
		niveauEnergie = 10;
		sprite = new Sprite(spriteName, 1, 1.f, this);
		hpText = new TextGraphics(Integer.toString((int)niveauEnergie), 0.4f, Color.BLUE);   //pour afficher le niveau d'energie a cote du fantome
		hpText.setParent(this);
		this.hpText.setAnchor(new Vector(-0.3f, 0.1f));
	}
	
	public boolean isWeak() {
		if (niveauEnergie <= 0) {
			return true;
		}
		return false;
	}
	public void strengthen() {
		niveauEnergie = 10;
	}
	public void draw(Canvas canvas) { 
		sprite.draw(canvas);
		hpText.draw(canvas);
		
	}
	public void update(float deltaTime) {
		
			niveauEnergie -= deltaTime;
			niveauEnergie = Math.max(0, niveauEnergie);
			
		
		
		this.hpText.setText(String.format("%.2f", niveauEnergie));   //permet de formater une chaine de charactere   .2f c est deux chiffres apres la virgule
	}

	public void moveUp() {
		setCurrentPosition(getPosition().add(0.f, 0.05f));
	}
	public void moveDown() {
		setCurrentPosition(getPosition().add(0.f, 0.05f));
	}
	
	public void moveRight() {
		setCurrentPosition(getPosition().add(0.05f, 0.f));
		
	}
	public void moveLeft() {
		setCurrentPosition(getPosition().add(-0.05f, 0.f));
	}
	
	

}
*/

