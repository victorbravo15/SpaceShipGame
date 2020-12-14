package sprites;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import graphics.Assets;

public class Meteorito {
	private int x;
	private int y;
	private Image mario;
	private int rowFrame;
	private int ANCHO;
	private int ALTO;
	private final static int COLUMNAS = 8;
	private final static int FILAS = 2;
	private int columnframe;
	private int widthScreen;
	private int speed;

	public Meteorito(int witdhscreen) {
		this.widthScreen = witdhscreen;
		int random = (int)(Math.random())*2;
		if(random == 0)
		x = 0;
		else x= 800;
				
		y = (int)(Math.random()*450);
		if (((int)(Math.random()*2))==0) {
			speed = 4;
			rowFrame = 0;
		}
		else {
			speed = -4;
			rowFrame = 1;
		}
		columnframe = 0;
		ImageIcon ii = new ImageIcon(Assets.meteorito[7]);
		ANCHO = ii.getIconWidth() / COLUMNAS;
		ALTO = ii.getIconHeight() / FILAS;
		mario = ii.getImage();
	}

	public void move() {
		if (x<0) rowFrame = 0; // izquierda change a derecha;
		if (x > widthScreen-ANCHO) rowFrame = 1; // derecha change a izquierda
		if ((x<0) || (x > widthScreen-ANCHO)) speed*=-1;//  choca => change dir
		x += speed;
	}

	public void update() {
		move();
		columnframe = ++columnframe % 8;
	}

	public void draw(Graphics g) {
		g.drawImage(Assets.meteorito[7], x, y, null);
	}

	public boolean colision(Laser laser) {
		Rectangle recMario = new Rectangle(x , y, ANCHO, ALTO);
		Rectangle recLaser = new Rectangle(laser.x, laser.y, Laser.SIZE, Laser.SIZE );
		return recMario.intersects(recLaser);
	}
	public boolean colisionPlayer(Player player) {
		Rectangle recMario = new Rectangle(x , y, ANCHO, ALTO);
		Rectangle recPlayer = new Rectangle(player.x, player.y, Laser.SIZE, Laser.SIZE );
		return recMario.intersects(recPlayer);
	}
}
