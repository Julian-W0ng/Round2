import processing.core.*;

public class Button {
	PApplet p;
	int x;
	int y;
	int w;
	int h;
	int s;
	int as;
	int type;
	Player[] players;
	String t;
	public Button(PApplet bp, String text, int xpos, int ypos, int width, int height, int screen, int bas, int kind) {
		p = bp;
		x = xpos;
		y = ypos;
		w = width;
		h = height;
		s = screen;
		as = bas;
		t = text;
		type = kind;
	}
	
	public void animate(){//animates button
		if (p.mouseX > x & p.mouseX < x + w & p.mouseY > y & p.mouseY < h + y) {
			p.fill(0);
			p.stroke(0);
			p.rect(x,y,w,h);
			p.fill(255);
			p.textAlign(3,3);
			p.textSize(50);
			p.text(t, x , y, w, h);
		}
		else {
			p.fill(255);
			p.stroke(0);
			p.rect(x,y,w,h);
			p.fill(0);
			p.textAlign(3,3);
			p.textSize(40);
			p.text(t, x , y, w, h);
		}
	}
	
	public int clicked() {//changes the screen when clicked
		main.screen = s;
		return type;
	}
}
