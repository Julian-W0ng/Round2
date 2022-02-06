import processing.core.*;

public class PowerUp {
	PApplet p;
	int x;
	int y;
	int radius = 80;
	boolean touched = false;
	int timer = 3000;
	public PowerUp(PApplet pp) {
		p = pp;
		x = p.width/2;
		y = 400;
	}
	
	public void animate() {// animates powerUp
		int red = (int) Math.round(Math.random()*255);
		int blue = (int) Math.round(Math.random()*255);
		int green = (int) Math.round(Math.random()*255);
		p.fill(red, blue, green);
		p.ellipse(x, y, radius, radius);
	}
	
	public Player buff(Player plyr) {//gives the buff of double damage to the player that touches the buff
		if (p.dist(plyr.x, plyr.y, x, y) < plyr.radius/2 + radius/2) {
			plyr.ogDamage = plyr.ogDamage*2;
			touched = true;
			return plyr;
		}
		return null;
	}
}
