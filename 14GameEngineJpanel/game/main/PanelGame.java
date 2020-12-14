package main;


import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import graphics.Assets;
import input.InputHandler;
import sprites.Player;
import sprites.Laser;
import sprites.Meteorito;

@SuppressWarnings("serial")
public class PanelGame extends JPanel {
	// estos 4 atributos los tendrá cualquier game
	// los 3 primeros del engine (públicos) 
	// y el 4º para control teclas
	public GameLoop gameLoop;
	public boolean isRunning = true;
	public boolean isPause = false;
	
	private InputHandler input;
	// Actores del game
	private Player player;
	private Laser laser;
	private Meteorito meteorito;
	private ArrayList<Meteorito> meteoritos;
	// control de deltatimes
	private int movermeteorito=0;
	private int nacemeteorito=0;
	// score
	private int score=0;
	// sonidos
	private AudioClip clipShot;
	private AudioClip clipLose;
	private AudioClip clipHit;
	// nº de renderizados (deltatimes) para que sucesa
	private final static int VECESMOVERMETEORITO = 2;
	private final static int VECESNACEMETEORITO = 25;
	
	private final static int WIDTHSCREEN=800;

	
	// En el constructor creamos las instancias de los actores
	// que saldrán inicialmente en el juego
	// junto con el hilo (engine), input (teclas) y sonidos
	public PanelGame() {
		super();
		player = new Player(WIDTHSCREEN);
		laser = new Laser(WIDTHSCREEN, player);
		meteoritos = new ArrayList<Meteorito>();
		gameLoop = new GameLoop(this);
		input = new InputHandler(this);
		loadSound();
	}
	// En este método actualizamos el juego (público)
	// 1º Que se mueva lo que tenga que moverse
	// 2º Control de nacimientos y defunciones segun control de colisiones
	// 3ª Control de las input del jugador (teclas)
	public void update() {
		// MOVIMIENTOS (Mover los sprites)
		// Mover meteoritos. cada VECESMOVERMETEORITO muevo los meteoritos
		movermeteorito = ++movermeteorito % VECESMOVERMETEORITO;
		if (movermeteorito==0) {
			for (Meteorito meteorito : meteoritos) {
				meteorito.update();
			}
		}
		// Mover bola
		laser.update();
			//clipWall.play();
		
		
		// NACIMIENTOS
		// cada VECESNACEMETEORITO nace un meteorito
		nacemeteorito = ++nacemeteorito % VECESNACEMETEORITO;
		if (nacemeteorito==0 && meteoritos.size()<10) {
			meteorito = new Meteorito(WIDTHSCREEN);
			meteoritos.add(meteorito);
		}
		// CONTROL DE LAS INPUTS
		// handle inputs
		if (input.isKeyDown(KeyEvent.VK_RIGHT)) {
			player.right();
		}
		if (input.isKeyDown(KeyEvent.VK_UP)) {
			player.up();
		}
		if (input.isKeyDown(KeyEvent.VK_DOWN)) {
			player.down();
		}
		if (input.isKeyDown(KeyEvent.VK_LEFT)) {
			player.left();
		}
		if (input.isKeyDown(KeyEvent.VK_SPACE)) {
			laser.shot();
			clipShot.play();
		}

			
		// CONTROL DE COLISIONES
		// control colision bola con barra
		//if (laser.colision(player))
			//clipBall.play();
		// DEFUNCIONES
		// Defunciones en función de colisiones bola con meteoritos
		for (Meteorito meteorito : meteoritos) {
			if (meteorito.colision(laser)) {
				score+=10;
				meteoritos.remove(meteorito);
				clipHit.play();
				break;
			}
			if (meteorito.colisionPlayer(player))
				gameOver();
		}	
	}

	// Este es el draw del game (público). Pero como trabajamos con clase JPanel
	// hay un método que se encarga de ello (paint). Lo sobreescribimos.
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		
		// Fondo 
		g.drawImage(Assets.fondo, getX(), getY(), null);
		
		
		// draw score
		drawScore(g2d);
		// draws sprites
		player.draw(g2d);
		laser.draw(g2d);
		for (Meteorito meteorito : meteoritos) {
			meteorito.draw(g2d);
		}
	}
	
	private void drawScore(Graphics2D g) {
		g.setColor(Color.decode("#FFFFFF"));
		g.drawString("Score:"+score, 650, 20);
		
	}

	private void gameOver() {
		isRunning = false;
		clipLose.play();
		JOptionPane.showMessageDialog(this, "GameOver \nPuntuaci�n: "+ score);
	}
	
	private void loadSound() {
		try {
			clipShot = Applet.newAudioClip(new File("sfx_laser1.wav").toURI().toURL());
			clipHit = Applet.newAudioClip(new File("sfx_zap.wav").toURI().toURL());
			clipLose = Applet.newAudioClip(new File("sfx_lose.wav").toURI().toURL());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
