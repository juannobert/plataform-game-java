package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utils.LoadSave;

public class LevelManager {

	private Game game;
	private BufferedImage[] levelSprites;
	private Level levelOne;

	public LevelManager(Game game) {
		this.game = game;
		importOutisideSprites();
		levelOne = new Level(LoadSave.getLevelData());
	}

	private void importOutisideSprites() {
		BufferedImage img = LoadSave.getSpriteAtltas(LoadSave.LEVEL_ALTLAS);
		levelSprites = new BufferedImage[48];
		// Altura será 4
		for (int j = 0; j < 4; j++) {
			// Largura será 12
			for (int i = 0; i < 12; i++) {
				// Quando j > 0 pegará as linhas abaixo
				int index = j * 12 + i;
				levelSprites[index] = img.getSubimage(i * 32, j * 32, 32, 32);
			}
		}

	}

	public void draw(Graphics g) {
		for (int j = 0; j < Game.TILES_IN_HEIGHT; j++) {
			for (int i = 0; i < Game.TILES_IN_WIDTH; i++) {
				int index = levelOne.getSpriteIndex(i, j);
				g.drawImage(levelSprites[index], Game.TILES_SIZE * i, Game.TILES_SIZE * j, Game.TILES_SIZE, Game.TILES_SIZE, null);
			}
		}
	}

	public void update() {
	}
}
