import processing.core.PApplet;

public class Melee extends Player{
	int dashTimer = 200;
	int dashDelay = 200;
	int dashSpeed = 25;
	int dmgTimer = 200;
	int dmgTime = 150;
	int dashStop = 25;
	public Melee(PApplet pp, int ppn, int px, int py, char pl, char pr, char pj, char pa, int phx, int phy, int[] colour) {
		super(pp, ppn, px, py, pl, pr, pj, pa, phx, phy, colour);
		type = 1;
		damage = 50;
		ogDamage = damage;
		baseDmg = damage;
		// TODO Auto-generated constructor stub
	}
	public void animate() { //animates the Melee player
		int red = c[0];
		int green = c[1];
		int blue = c[2];
		p.fill(red, green, blue);
		p.noStroke();
		p.ellipse(x, y, radius, radius);
		if (direction == false) {
			p.rect(x,y-5,radius,10);
		}
		else if (direction == true) {
			p.rect(x-radius,y-5,radius,10);
		}
	}
	public void attack() { //melee attack
		if (direction == true & dashTimer >= dashDelay & fon == true) {
			vx -= dashSpeed;
			dashTimer = 0;
			dashStop = 50;
			main.dash.play();
		}
		else if(direction == false & dashTimer >= dashDelay & fon == true) {
			vx += dashSpeed;
			dashTimer = 0;
			dashStop = 50;
			main.dash.play();
		}
		else if (direction == true & dashTimer >= dashDelay & fon == false) {
			vx -= dashSpeed;
			dashTimer = 0;
			dashStop = 15;
			main.dash.play();
		}
		else if(direction == false & dashDelay >= dashDelay & fon == false) {
			vx += dashSpeed;
			dashTimer = 0;
			dashStop = 15;
			main.dash.play();
		}
	}
	
	public void dmg(Player plyr) { // damages other player
		if (dashTimer < dashStop) {
			damage = ogDamage*2;
		}
		if (direction == false) {
			float[][] points = {{x,y-5}, {x,y+5}, {x+radius, y-5}, {x+radius, y+5}};
			for (int i = 0; i < 4; i++) {
				if(p.dist(points[i][0], points[i][1], plyr.x, plyr.y) < plyr.radius & dmgTimer >= dmgTime) {
					plyr.health -= damage;
					plyr.vx = vxmax*2;
					plyr.vy = -plyr.vymax/2;
					dmgTimer = 0;
				}
			}
		}
		else if (direction == true) {
			float[][] points = {{x,y-5}, {x,y+5}, {x-radius, y-5}, {x-radius, y+5}};
			for (int i = 0; i < 4; i++) {
				if(p.dist(points[i][0], points[i][1], plyr.x, plyr.y) < plyr.radius & dmgTimer >= dmgTime) {
					plyr.health -= damage;
					plyr.vx = -vxmax*2;
					plyr.vy = -plyr.vymax/2;
					dmgTimer = 0;
				}
			}
		}
		dmgTimer++;
	}
	
	public void refresh(Player plyr) { //refreshes a melee player
		x += vx;
		y += vy;
		end();
		friction();
		gravity();
		boarders();
		dmg(plyr);
		health();
		healthBar();
		jumpTimer++;
		dashTimer++;
		if (dashTimer == dashStop) {
			vx = 0;
			damage = ogDamage;
		}
	}

}
