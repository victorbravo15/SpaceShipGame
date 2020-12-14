package graphics;

import java.awt.image.BufferedImage;

public class Assets {
	
	public static BufferedImage player;
	public static BufferedImage[] meteorito = new     BufferedImage[20];
	public static BufferedImage laser;
	public static BufferedImage fondo;
	public static void init() {
		
		player =Loader.ImageLoader("/player.png");
		laser = Loader.ImageLoader("/laserBlue04.png");
		fondo = Loader.ImageLoader("/purple.png");
		for (int i = 0; i < 20; i++) {
			meteorito[i] = Loader.ImageLoader("/meteor"+i+".png");
			
		}
	}

}
