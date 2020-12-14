package sprites;

import java.awt.Graphics;

import graphics.Assets;

public class Laser {
	static final int SIZE = 20;
	public int x=0;
	public int y=0;
	private Player player;
	private int speedY=10;
	protected boolean isWall = false;
	private int widthscreen;
	
	public Laser(int widthscreen, Player player ) {
		this.widthscreen = widthscreen;
		x = player.getX()+10;
		y = player.getY();
		speedY*=-1;
		this.player = player;
	}

	public boolean update() {
		

		if (y < 0) {
			speedY = -10;	
			isWall = true;
		}

		
		y += speedY;
		return isWall;
	}
	
	public void draw(Graphics g) {
		g.drawImage(Assets.laser, x, y, null);
	}
	public void shot(){
		if(isWall) {
		x = player.getX()+20;
		y = player.getY();
		isWall=false;
		}
	}


	public boolean colision(Player player) {
		int region = -1;
		if ((y+SIZE)>Player.Y && x>=player.x && x<=player.x+Player.SIZE){
			for (int i=0; i<5; i++) {
				if (x>=(player.x+(i*SIZE)) && x<=player.x+((i+1)*SIZE)) {
					region = i;
					break;
				}
			}
			if (region != -1) {
				speedY*=-1;
			}	
			return true;
		}
		
		return false;
	}
	
	
	

}
