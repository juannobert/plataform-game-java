package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import inputs.KeyBoardInputs;
import inputs.MouseInputs;

import static utils.Constants.PlayerConstants.*; 
import static utils.Constants.Directions.*;

public class GamePanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private MouseInputs mouseInputs;
	private KeyBoardInputs keyBoardInputs;
	private BufferedImage img;
	private float xDelta = 0,yDelta = 0;
	private BufferedImage[][] animations;
	private int aniTick = 0,aniIndex = 0,aniSpeed = 15;
	private int playerAction = IDLE;
	private int direction = -1;
	private boolean moving;
	public GamePanel() {
		setPanelSize();
		importImg();
		loadAnimations();
		keyBoardInputs = new KeyBoardInputs(this);
		mouseInputs = new MouseInputs(this);
		addKeyListener(keyBoardInputs);
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
	}
  

	public void setDirection(int direction) {
		this.direction = direction;
		moving = true;
	}
	
	public void setMoving(boolean moving) {
		this.moving = moving;
	}
	


	
	
	private void importImg() {
		InputStream is = getClass().getResourceAsStream("/player_sprites.png");
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
		
	}
	
	
	private void setPanelSize() {
		Dimension dimension = new Dimension(1280,800);
		setPreferredSize(dimension);
		setMinimumSize(dimension);
		setMaximumSize(dimension);
		
	}
	
	private void loadAnimations() {
		animations = new BufferedImage[9][6];
		for(int j =0;j < animations.length;j++) {
			for(int i =0;i < animations[i].length;i++) {
			//Pegando imagens na primeira linha das sprites
			//quando o j é 0 estamos na primeira linha
				animations[j][i] = img.getSubimage(i*64, j*40, 64, 40);
			}
		}
	}
	
	private void updateAnimationTick() {
		aniTick++;
		if(aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if(aniIndex >= getSpritAmount(playerAction))
				aniIndex = 0;
		}
	}
	
	private void setAnimation() {
		if(moving)
			playerAction = RUNNING;
		else 
			playerAction = IDLE;
	}
	
	private void updatePos() {
		if(moving) {
			switch (direction) {
			case LEFT:
				xDelta-=5;
				break;
			case UP:
				yDelta-=5;
				break;
			case RIGHT:
				xDelta+=5;
				break;
			case DOWN:
				yDelta+=5;
				break;

			default:
				break;
			}
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		updateAnimationTick();
		setAnimation();
		updatePos();
		//Pegando imagem na coluna 8 e linha 1
		//subImg = img.getSubimage(1*64, 8*40, 64, 40);
		g.drawImage(animations[playerAction][aniIndex], (int)xDelta, (int) yDelta,128,80, null);
	}
	
}
