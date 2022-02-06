import java.util.ArrayList;
import processing.core.PApplet;

public class Ranged extends Player{
	float BR = 300;
	float BT = 300;
	float r = 40;
	boolean aO = false;
	ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	public Ranged(PApplet pp, int ppn, int px, int py, char pl, char pr, char pj, char pa, int phx, int phy, int[] colour) {
		super(pp, ppn, px, py, pl, pr, pj, pa, phx, phy, colour);
		type = 2;
		damage = 40;
		ogDamage = damage;
		baseDmg = damage;
		// TODO Auto-generated constructor stub
	}
	public void animate() { //animates ranged player
		int red = c[0];
		int green = c[1];
		int blue = c[2];
		p.fill(red, green, blue);
		p.noStroke();
		p.ellipse(x, y, radius, radius);
		if (direction == false) {
			p.rect(x,y-10,radius,20);
			if (aO == true) {
				p.ellipse(x+radius+r/2, y, r, r);
			}
		}
		else if (direction == true) {
			p.rect(x-radius,y-10,radius,20);
			if (aO == true) {
				p.ellipse(x-radius-r/2, y, r, r);
			}
		}
	}
	
	public void attackRelease() { //fires bullet
		aO = false;
		if (BT >= BR) {
			damage = ogDamage;
			main.bullets.add(new Bullet(p, pn, x, y, direction, c, damage));
			BT = 0;
		}
	}
	
	public void attack() { //animates bullet while the attack button is held
		if (BT >= BR) {
			aO = true;
		}
	}
	public void refresh(Player plyr) { //refreshes the ranged player
		x += vx;
		y += vy;
		end();
		friction();
		gravity();
		boarders();
		health();
		healthBar();
		jumpTimer++;
		BT++;
	}
}
