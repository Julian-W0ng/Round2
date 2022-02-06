import processing.core.PApplet;

public class Block {
	PApplet p;
	int x;
	int y;
	int w;
	int h;
	public Block(PApplet bp, int xpos, int ypos, int width, int height) {
		p = bp;
		x = xpos;
		y = ypos;
		w = width;
		h = height;
	}

	public void collision(Player plyr) { // player block collision
		int radius =  plyr.radius/2;
		if ((plyr.y >= y - radius & plyr.y < y) & (plyr.x >= x & plyr.x <= x+w)) {
			plyr.y = y - radius;
			plyr.gon = false;
			plyr.jump = 0;
			plyr.jumpTimer = 30;
			plyr.contact = true;
			if (plyr.ron == false & plyr.lon == false) {
				plyr.fon = true;
			}
		}
		else if ((plyr.y <= y + h + radius & plyr.y > y+h) & (plyr.x >= x & plyr.x <= x+w)) {
			plyr.y = y - radius;
			plyr.gon = true;
			plyr.contact = false;
		}
		else if ((plyr.x >= x - radius & plyr.x < x) & (plyr.y >= y & plyr.y <= y+h)) {
			plyr.x = x - radius;
			plyr.gon = true;
			plyr.contact = false;
		}
		else if ((plyr.x <= x + w + radius & plyr.x > x + w) & (plyr.y >= y & plyr.y <= y+h)) {
			plyr.x = x + w + radius;
			plyr.gon = true;
			plyr.contact = false;
		}
		else{
			plyr.gon = true;
			plyr.fon = false;
			plyr.contact = false;
		}
	}
	
	public void animate() { //animates block
		p.fill(0);
		p.rect(x,y,w,h);
	}
	
	public void refresh(Player p){ //refreshes the block
		collision(p);
	}
}
