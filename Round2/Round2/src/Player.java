import processing.core.*;
public class Player {
	int pn;
	float x;
	float y;
	float spawnx;
	float spawny;
	float vx = 0;
	float vy = 0;
	int jump = 0;
	int jumpTimer = 50;
	float vxmax = 5;
	float ay = 0.2f;
	float ax = 1;
	float vymax = 8;
	int health = 200;
	int lives = 2;
	int hx;
	int hy;
	int[] c;
	int type;
	int damage;
	int ogDamage;
	int baseDmg;
	boolean contact = false;
	boolean gon = true;
	boolean fon = false;
	boolean direction = true;
	int radius = 60;
	PApplet p = new PApplet();
	char l;
	char r;
	char j;
	char a;
	boolean lon = false;
	boolean ron = false;
	public Player(PApplet pp, int ppn, int px, int py, char pl, char pr, char pj, char pa, int phx, int phy, int[] colour){
		pn = ppn;
		p = pp;
		x = px;
		y = py;
		spawnx = px;
		spawny = py;
		l = pl;
		r = pr;
		j = pj;
		a = pa;
		hx = phx;
		hy = phy;
		c = colour;
		type = 0;
		ogDamage = damage;
		baseDmg = damage;
		if (x < p.width/2) {
			direction = false;
		}
		else if (x > p.width/2) {
			direction = true;
		}
	}
	
	public void jump() { //jump
		if (jump < 2 & jumpTimer >= 30) {
			vy = -12;
			jump++;
			jumpTimer = 0;
			main.jump.play();
		}
	}
	
	public void left() { //move left
		vx = -vxmax;
		lon = true;
		direction = true;
	}
	
	public void right() { //move right
		vx = vxmax;
		ron = true;
		direction = false;
	}
	
	public void gravity() { //gravity
		if (gon == true & vy <= vymax) {
			vy += ay;
		}
	}
	
	public void animate() { //animation
		int red = c[0];
		int green = c[1];
		int blue = c[2];
		p.noStroke();
		p.fill(red, green, blue);
		p.ellipse(x, y, radius, radius );
	}
	
	public void friction() { //friction
		if (lon == false & ron == false & fon == true) {	
			if (vx < ax/5 & vx > -ax/5) {
				vx = 0;
			}
			else if (vx > 0) {
				vx -= ax;
			}
			else if (vx < 0) {
				vx += ax;
			}
		}
	}
	
	public void healthBar() { //health bar
		p.stroke(0);
		p.fill(255, 0, 0);
		p.rect(hx, hy, 200, 20);
		p.fill(0, 255, 0);
		p.rect(hx, hy, health, 20);
		p.textSize(20);
		p.fill(0);
		p.text(lives, hx, hy+40);
	}
	
	public void health() { //health
		if (health <= 0) {
			lives--;
			health = 200;
			x = Math.round(Math.random()*(p.width-400))+200;
			y = 360;
			vx = 0;
			vy = 0;
			damage = baseDmg;
			ogDamage = baseDmg;
			main.grabbed = false;
			main.buffed = null;
			main.pSpawn = 0;
			main.grabbedT = 0;
		}
	}
	
	public void boarders() { // screen boarders
		if (x < (radius/2)) {
			x = radius/2;
		}
		else if (x > p.width-(radius/2)) {
			x = p.width-(radius/2);
		}
		if (y < radius/2) {
			y = radius/2;
		}
		else if (y > p.height + 100) {
			health = 0;
		}
	}
	
	public void reset() { //resets players
		x = spawnx;
		y = spawny;
		vx = 0;
		lives = 2;
		health = 200;
		damage = ogDamage;
		if (x < p.width/2) {
			direction = false;
		}
		else if (x > p.width/2) {
			direction = true;
		}
	}
	
	public void end() { // goes to pause screen when player dies
		if (lives < 0) {
			main.screen = 3;
		}
	}
	
	public void attack() {
		
	}
	
	public void attackRelease() {
		
	}
	
	public void refresh(Player plyr) { //every frame refresh
		x += vx;
		y += vy;
		end();
		friction();
		gravity();
		boarders();
		health();
		healthBar();
		jumpTimer++;
	}
}
