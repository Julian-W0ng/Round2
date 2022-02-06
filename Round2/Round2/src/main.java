import java.util.ArrayList;
import processing.core.*;
import processing.event.KeyEvent;
import processing.sound.*;

public class main extends PApplet{

	public static void main(String[] args) {
		PApplet.main("main");
	}
	
	public void settings() { //sets resolution and makes the game full screen
		size(1920,1080);
		fullScreen();
	}
	
	int c = 0; // makes variable global
	int w;
	int h;
	static int pSpawn = 0;
	static int grabbedT = 0;
	static boolean grabbed = false;
	int pSTimer = 4000;
	int pTimer = 3000;
	static Player buffed;
	public static int screen;
	Block platform;
	Block wall;
	Melee m1;
	Melee m2;
	Ranged r1;
	Ranged r2;
	Button con;
	Button tom1;
	Button tom2;
	Button tor1;
	Button tor2;
	Button htom1;
	Button htom2;
	Button htor1;
	Button htor2;
	Button home;
	Button restart;
	Button play;
	Button tutorial;
	Button back;
	ArrayList<Block> blocks = new ArrayList<Block>(); 
	ArrayList<Player> players = new ArrayList<Player>();
	static ArrayList<Bullet> bullets = new ArrayList<Bullet>(); 
	ArrayList<Button> buttons = new ArrayList<Button>();
	ArrayList<PowerUp> PUs = new ArrayList<PowerUp>();
	PFont arial;
	PFont electric;
	public static SoundFile theme1;
	public static SoundFile theme2;
	public static SoundFile button;
	public static SoundFile CurrentTrack;
	public static SoundFile jump;
	public static SoundFile gun;
	public static SoundFile dash;
	int player1;
	int player2;
	boolean conOn;
	
	public void setup() { //sets up variables, frame rate and screen size
		background(255);
		frameRate(300);
		w = width;
		h = height;
		conOn = true;
		theme1 = new SoundFile(this, "Ultimate.wav");
		theme2 = new SoundFile(this, "Melee.wav");
		button = new SoundFile(this, "button.wav");
		jump = new SoundFile(this, "Jump.wav");
		dash = new SoundFile(this, "Dash.wav");
		gun = new SoundFile(this, "Gun.wav");
		arial = createFont("Arial.ttf", 100);
		electric = createFont("Electric.otf", 150);
		int[] green = {0,255,0};
		int[] blue = {0,0,255};
		platform = new Block(this, 100, h-100, w-200, 200);
		wall = new Block(this, 600, 400, 100, 300);
		m1 = new Melee(this, 1, 300, 360, 'a', 'd', 'w', 'f', 100, 80, green);
		m2 = new Melee(this, 2, width-300, 360, 'h', 'k', 'u', 'l' , w - 330, 80, blue);
		r1 = new Ranged(this, 1, 300, 360, 'a', 'd', 'w', 'f', 100, 80, green);
		r2 = new Ranged(this, 2, width-300, 360, 'h', 'k', 'u', 'l', w - 300, 80, blue);
		con = new Button(this, "Continue", (w/2-200), 200, 400, 100, 2, 3, 0);
		restart = new Button(this, "Restart", (w/2-200), 350, 400, 100, 2, 3, 1);
		tom1 = new Button(this, "M1", (w/4-100), 500, 200, 100, 3, 3, 2);
		tor1 = new Button(this, "R1", (w/4-100), 650, 200, 100, 3, 3, 3);
		tom2 = new Button(this, "M2", (3*(w/4)-100), 500, 200, 100, 3, 3, 4);
		tor2 = new Button(this, "R2", (3*(w/4)-100), 650, 200, 100, 3, 3, 5);
		htom1 = new Button(this, "M1", (w/4-100), 700, 200, 100, 0, 0, 2);
		htor1 = new Button(this, "R1", (w/4-100), 850, 200, 100, 0, 0, 3);
		htom2 = new Button(this, "M2", (3*(w/4)-100), 700, 200, 100, 0, 0, 4);
		htor2 = new Button(this, "R2", (3*(w/4)-100), 850, 200, 100, 0, 0, 5);
		home = new Button(this, "Home", (w/2-200), 700, 400, 100, 0, 3, 0);
		play = new Button(this, "Play", (w/2-200), 400, 400, 100, 2, 0, 1);
		tutorial = new Button(this, "Tutorial", (w/2-200), 550, 400, 100, 1, 0, 0);
		back = new Button(this, "Back", (w/2-200), 800, 400, 100, 0, 1, 0);
		buttons.add(con);
		buttons.add(restart);
		buttons.add(tom1);
		buttons.add(tor1);
		buttons.add(tom2);
		buttons.add(tor2);
		buttons.add(htom1);
		buttons.add(htor1);
		buttons.add(htom2);
		buttons.add(htor2);
		buttons.add(home);
		buttons.add(play);
		buttons.add(tutorial);
		buttons.add(back);
		blocks.add(platform);
		players.add(m1);
		players.add(m2);
		player1 = 1;
		player2 = 1;
		Player[][] playerz = {{r1, m1}, {r2, m2}};
		screen = 0;
	}
	
	
	
	public void draw() {
		background(255);
		for (int i = 0; i < buttons.size(); i++) { //animates all buttons
			if (buttons.get(i).as == screen){
				textFont(arial);
				buttons.get(i).animate();
			}
		}
		if (screen == 0) { // home screen
			fill(0, 0, 255);
			textFont(electric);
			text("Electric Boogaloo", 3*(w/4)-800, 80, 1200, 200);
			fill(0);
			textFont(arial);
			fill(0);
			text("Round 2", w/4-300, 100, 400, 150);
			fill(255, 0, 0);
			rect((w/4-100), 700, 20, 20);
			rect((w/4-100), 850, 20, 20);
			rect((3*(w/4)-100), 700, 20, 20);
			rect((3*(w/4)-100), 850, 20, 20);
			if (players.contains(m1)) {
				fill(0,255,0);
				rect((w/4-100), 700, 20, 20);
			}
			if (players.contains(r1)) {
				fill(0,255,0);
				rect((w/4-100), 850, 20, 20);
			}
			if (players.contains(m2)) {
				fill(0,255,0);
				rect((3*(w/4)-100), 700, 20, 20);
			}
			if (players.contains(r2)) {
				fill(0,255,0);
				rect((3*(w/4)-100), 850, 20, 20);
			}
			fill(0, 255, 0);
			noStroke();
			ellipse(w/4, 500, 60, 60);
			if (player1 == 1) {
				rect(w/4,495,60,10);
			}
			else if (player1 == 2) {
				rect(w/4,490,60,20);
			}
			fill(0, 0, 255);
			noStroke();
			ellipse(3*w/4, 500, 60, 60);
			if (player2 == 1) {
				rect(3*w/4-60,495,60,10);
			}
			else if (player2 == 2) {
				rect(3*w/4-60,490,60,20);
			}
		}
		else if (screen == 1) { // tutorial screen
			fill(0);
			textSize(50);
			textAlign(3,3);
			text("Player 1:", (w/4-200), 200, 400, 100);
			text("W = Jump", (w/4-200), 350, 400, 100);
			text("A = Left", (w/4-200), 500, 400, 100);
			text("D = Right", (w/4-200), 650, 400, 100);
			text("F = Attack", (w/4-200), 800, 400, 100);
			text("Player 2:", (3*(w/4)-200), 200, 400, 100);
			text("U = Jump", (3*(w/4)-200), 350, 400, 100);
			text("H = Left", (3*(w/4)-200), 500, 400, 100);
			text("K = Right", (3*(w/4)-200), 650, 400, 100);
			text("L = Attack", (3*(w/4)-200), 800, 400, 100);
			text("P = Pause", (w/2-200), 250, 400, 100);
			text("Power Up", (w/2-200), 500, 400, 100);
			int re = (int) Math.round(Math.random()*255);
			int bl = (int) Math.round(Math.random()*255);
			int gr = (int) Math.round(Math.random()*255);
			fill(re, bl, gr);
			ellipse((w/2), 450, 80, 80);
		}
		else if (screen == 2) { //game
			for (int i = 0; i < players.size(); i++) { 
				refresh();
			}
			for (int i = 0; i < blocks.size(); i++) {
				for(int j = 0; j < players.size(); j++) {	
					blocks.get(i).refresh(players.get(j));
				}
				blocks.get(i).animate();
			}
			if (pSpawn >= pSTimer) {
				PUs.add(new PowerUp(this));
				grabbedT = 0;
				pSpawn = 0;
			}
			if (PUs.size() > 0 & buffed == null) { //power up timing and animation
				outerloop:
				for (int j = 0; j < PUs.size(); j++) {
					PUs.get(j).animate();
					for (int i = 0; i < players.size(); i++) {
						buffed = PUs.get(j).buff(players.get(i));
						if (buffed != null) {
							PUs.remove(PUs.get(j));
							grabbed = true;
							break outerloop;
						}
					}
				}
			}
			if (buffed != null & grabbed == true & grabbedT > pTimer) {
				buffed.ogDamage = buffed.baseDmg;
				grabbed = false;
				buffed = null;
				pSpawn = 0;
				grabbedT = 0;
			}
			if(grabbed == false & pSpawn < pSTimer) {
				pSpawn++;
				
			}
			if (grabbed == true) {
				grabbedT++;
			}
			for (int i = 0; i < bullets.size(); i++) {
				bullets.get(i).refresh();
			}
			bulletCollision();
			bulletBlockCollision();
			for (int i = 0; i < players.size(); i++) {
				players.get(i).animate();
			}
		}
		else if (screen == 3) { // pause screen
			fill(255, 0, 0);
			rect((w/4-100), 500, 20, 20);
			rect((w/4-100), 650, 20, 20);
			rect((3*(w/4)-100), 500, 20, 20);
			rect((3*(w/4)-100), 650, 20, 20);
			if (players.contains(m1)) {
				fill(0,255,0);
				rect((w/4-100), 500, 20, 20);
			}
			if (players.contains(r1)) {
				fill(0,255,0);
				rect((w/4-100), 650, 20, 20);
			}
			if (players.contains(m2)) {
				fill(0,255,0);
				rect((3*(w/4)-100), 500, 20, 20);
			}
			if (players.contains(r2)) {
				fill(0,255,0);
				rect((3*(w/4)-100), 650, 20, 20);
			}
			int counter = 0;
			for (int i = 0; i < players.size(); i++) {
				if (players.get(i).lives < 0) {
					counter++;
				}
			}
			if (counter >= 1 || conOn == false) {
				noStroke();
				fill(255);
				rect((w/2-200), 200, 405, 105);
			}
		}
		if (CurrentTrack == null) { //back ground music
			CurrentTrack = theme1;
			CurrentTrack.loop();
		}
		else if (screen == 2 & CurrentTrack.equals(theme1)) {
			CurrentTrack.stop();
			CurrentTrack = theme2;
			CurrentTrack.loop();
		}
		else if (screen == 0 & CurrentTrack.equals(theme2)) {
			CurrentTrack.stop();
			CurrentTrack = theme1;
			CurrentTrack.loop();
		}
		else if (screen == 2 & !(CurrentTrack.isPlaying()) & CurrentTrack == theme2) {
			CurrentTrack.loop();
		}
		else if (screen == 0 & !(CurrentTrack.isPlaying()) & CurrentTrack == theme1) {
			CurrentTrack.loop();
		}
		else if (CurrentTrack.isPlaying() & screen == 3){
			CurrentTrack.stop();
		}
	}
	
	public void refresh() { //refreshes players
		for(int i = 0; i < players.size(); i++) {
			for(int j = 0; j < players.size(); j++) {
				if(i != j) {
					players.get(i).refresh(players.get(j));
				}
			}
		}
	}
	
	public void bulletCollision() { // bullet player collision
		outerloop:
		for (int j = 0; j < bullets.size(); j++) {
			for (int i = 0; i < players.size(); i++) {
				if (players.get(i).pn != bullets.get(j).pn & dist(players.get(i).x, players.get(i).y, bullets.get(j).x, bullets.get(j).y) < players.get(i).radius/2 + bullets.get(j).radius/2) {
					bullets.get(j).dmg(players.get(i));
					bullets.remove(j);
					break outerloop;
				}
			}
		}
	}
	
	public void bulletBlockCollision() { //bullet block collision
		for (int i = 0; i < blocks.size(); i++) {
			outerloop:
			for (int j = 0; j < bullets.size(); j++) {
				int rad = bullets.get(j).radius/2;
				boolean colide = false;
				int[][] points = {{blocks.get(i).x, blocks.get(i).y}, 
						{blocks.get(i).x + blocks.get(i).w, blocks.get(i).y},
						{blocks.get(i).x, blocks.get(i).y + blocks.get(i).h},
						{blocks.get(i).x + blocks.get(i).w, blocks.get(i).y + blocks.get(i).h}};
				for (int k = 0; k < points.length; k++) {
					if (dist(points[k][0], points[k][1], bullets.get(j).x, bullets.get(j).y) < rad) {
						colide = true;
					}
				}
				if (bullets.get(j).x > blocks.get(i).x - rad & bullets.get(j).x < blocks.get(i).x & bullets.get(j).y > blocks.get(i).y & bullets.get(j).y < blocks.get(i).y + blocks.get(i).h) {
					colide = true;
					System.out.println(bullets.get(j).y + ", " + blocks.get(i).y);
				}
				else if (bullets.get(j).x > blocks.get(i).x + blocks.get(i).w & bullets.get(j).x < blocks.get(i).x + blocks.get(i).w + rad & bullets.get(j).y > blocks.get(i).y & bullets.get(j).y < blocks.get(i).y + blocks.get(i).h) {
					colide = true;
					System.out.println(bullets.get(j).y + ", " + blocks.get(i).y);
				}
				if (colide == true) {
					bullets.remove(j);
					break outerloop;
				}
			}
		}
	}
	
	public void keyPressed(KeyEvent event) {
		if (screen == 2) {
			for (int i = 0; i < players.size(); i++) { // player and pause keys
				if(Character.toLowerCase(key) == players.get(i).l) {
					players.get(i).left();
					players.get(i).lon = true;
					players.get(i).fon = false;
				}
				if(Character.toLowerCase(key) == players.get(i).r) {
					players.get(i).right();
					players.get(i).ron = true;
					players.get(i).fon = false;
				}
				if (Character.toLowerCase(key) == players.get(i).j) {
					players.get(i).jump();
				}
				if (Character.toLowerCase(key) == players.get(i).a) {
					players.get(i).attack();
				}
				if (Character.toLowerCase(key) == 'p') {
					screen = 3;
				}
			}
		}
	}
	
	public void keyReleased() { // player keys
		if (screen == 2) {
			for (int i = 0; i < players.size(); i++) {
				if(Character.toLowerCase(key) == players.get(i).l)  {
					players.get(i).lon = false;
				}
				if(Character.toLowerCase(key) == players.get(i).r) {
					players.get(i).ron = false;
				}
				if(Character.toLowerCase(key) == players.get(i).a & players.get(i).type == 2) {
					players.get(i).attackRelease();
				}
			}
		}
	}
	
	public void mousePressed() { //button detection and individual button actions
		for (int i = 0; i < buttons.size(); i++) {
			if (buttons.get(i).as == screen){
				if (mouseX > buttons.get(i).x & mouseX < buttons.get(i).x + buttons.get(i).w & mouseY > buttons.get(i).y & mouseY < buttons.get(i).h + buttons.get(i).y & (conOn == true || !buttons.get(i).equals(con))) {
					int type = buttons.get(i).clicked();
					button.play();
					if (type == 2) {
						if (players.contains(r1)) {
							players.remove(r1);
							players.add(m1);
							player1 = 1;
						}
					}
					else if (type == 3) {
						if (players.contains(m1)) {
							players.remove(m1);
							players.add(r1);
							player1 = 2;
						}
					}
					else if (type == 4) {
						if (players.contains(r2)) {
							players.remove(r2);
							players.add(m2);
							player2 = 1;
						}
					}
					else if (type == 5) {
						if (players.contains(m2)) {
							players.remove(m2);
							players.add(r2);
							player2 = 2;
						}
					}
					if (type == 1 || type == 2 || type == 3 || type == 4 || type == 5) {
						for (int j = 0; j < players.size(); j++) {
							players.get(j).reset();
							players.get(j).damage = players.get(j).baseDmg;
							players.get(j).ogDamage = players.get(j).baseDmg;
							grabbed = false;
							buffed = null;
							pSpawn = 0;
							grabbedT = 0;
						}
					}
					if ((type == 1 || type == 2 || type == 3 || type == 4 || type == 5) & screen == 3) {
						noStroke();
						fill(255);
						rect((w/2-200), 200, 405, 105);
						conOn = false;
					}
					if (type == 0 || type == 1) {
						conOn = true;
					}
				}
			}
		}
	}
}

