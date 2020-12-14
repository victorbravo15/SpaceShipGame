package sprites;
import java.awt.Graphics;

import graphics.Assets;

public class Player {
	final static int SIZE=75;
	final static int Y=400;
	int x=300;
	int y=300;
	private int widthScreen;

	public Player(int witdhscreen ) {	
		this.widthScreen = witdhscreen;
		
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public void left(){
		x-=10;
		if (x < 0) x=0;
		
	}
	public void up(){
		y-=10;
		if (y < 0) y=0;
	}
	public void down(){
		y+=10;
		if (y > 480) y=480;
		
	}
	
	
	public void right(){
		x+=10;
		if (x > (widthScreen-SIZE)) x=widthScreen-SIZE;
		
	}
	
	public void draw(Graphics g) {
		
		g.drawImage(Assets.player, x, y, null);

	}

}
