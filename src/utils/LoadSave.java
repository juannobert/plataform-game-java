package utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.Game;

public class LoadSave {

	public static final String PLAYER_ALTLAS = "player_sprites.png";
	public static final String LEVEL_ALTLAS = "outside_sprites.png";
	public static final String LEVEL_ONE_DATA = "level_one_data.png";
	
	public static BufferedImage getSpriteAtltas(String filename) {
		BufferedImage img = null;
		InputStream is = LoadSave.class.getResourceAsStream("/"+filename);
		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return img;
	}
	
	
	public static int[][] getLevelData(){
		int [][] lvldate = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
		BufferedImage img = getSpriteAtltas(LEVEL_ONE_DATA);
		for(int j = 0;j < img.getHeight();j++) {
			for(int i = 0; i < img.getWidth();i++) {
				Color color = new Color(img.getRGB(i, j)); //Pegando cor da imagens nas posições
				int value = color.getRed();
				if(value >= 48) {
					value = 0;
				}
				lvldate[j][i] = value;
			}
		}
		return lvldate;
	}
}
