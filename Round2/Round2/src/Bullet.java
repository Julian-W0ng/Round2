import java.util.ArrayList;

import processing.core.*;

public class Bullet {
	PApplet p;
	float x;
	float y;
	float pr;
	int radius = 40;
	float vx = 15;
	int damage = 40;
	int pn;
	int c[];
	boolean d;
	public Bullet(PApplet pp, int bpn, float bx, float by, boolean direction, int colour[], int dmg) {
		d = direction;
		if (direction == false) {
			x = bx + pr + 2*radius;
		}
		if (direction == true) {
			x = bx - pr - 2*radius;
		}
		p = pp;
		y = by;
		d = direction;
		pn = bpn;
		c = colour;
		damage = dmg;
		main.gun.play();
	}
	
	public void travel() { //moves bullets
		if (d == true) {
			x -= vx;
		}
		else if (d == false) {
			x += vx;
		}
	}
	
	public void dmg(Player plyr){ //damages other player
		plyr.health -= damage;
		if (d == true) {
			plyr.vx = -20;
		}
		else if (d == false) {
			plyr.vx = 20;
		}
	}
	
	public void animate() {//animates the bullet
		int red = c[0];
		int blue = c[1];
		int green = c[2];
		p.stroke(0);
		p.fill(red, blue, green);
		p.ellipse(x, y, radius, radius);
	}
	
	public void refresh() { //refreshes bullet
		travel();
		animate();
	}
}
