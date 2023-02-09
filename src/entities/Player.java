package entities;

import static utils.Constants.PlayerConstants.*;
import static utils.Constants.PlayerConstants.getSpritAmount;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Player extends Entity{
	
	private BufferedImage[][] animations;
	private int aniTick = 0,aniIndex = 0,aniSpeed = 15;
	private int playerAction = IDLE;
	private boolean moving,left,up,right,down,attacking;
	private float playerSpeed = 2.0f;

	public Player(float x, float y) {
		super(x, y);
		loadAnimations();
	}
	public void update() {
		setAnimation();
		updatePos();
		updateAnimationTick();
		
	}
	
	public void render(Graphics g) {
		//Pegando imagem na coluna 8 e linha 1
				//subImg = img.getSubimage(1*64, 8*40, 64, 40);
				g.drawImage(animations[playerAction][aniIndex], (int)x, (int) y,128,80, null);
		
	}
	
	
	private void updateAnimationTick() {
		aniTick++;
		if(aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if(aniIndex >= getSpritAmount(playerAction)) {
				aniIndex = 0;
				attacking = false;
			}
		}
	}
	
	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}
	
	private void setAnimation() {
		//Pegando animação atual
		int startAni = playerAction;
		if(moving)
			playerAction = RUNNING;
		else if(attacking)
			playerAction = ATTACK_1;
		else 
			playerAction = IDLE;
		//Se a animação mudar,resetar animationTick
		if(startAni != playerAction) {
			aniIndex = 0;
			aniTick = 0;
		}
		
	}
	
	private void updatePos() {
		
		moving = false;
		if(left && !right) {
			x-= playerSpeed;
			moving = true;
		}
		else if(right && !left) {
			x+= playerSpeed;
			moving = true;
		}
		else if(up && !down) {
			y-= playerSpeed;
			moving = true;
		}
		else if(down && !up) {
			y+=playerSpeed;
			moving = true;
		}
		
		
			
	}

	
	private void loadAnimations() {
		InputStream is = getClass().getResourceAsStream("/player_sprites.png");
		try {
			BufferedImage img = ImageIO.read(is);
			animations = new BufferedImage[9][6];
			for(int j =0;j < animations.length;j++) {
				for(int i =0;i < animations[i].length;i++) {
				//Pegando imagens na primeira linha das sprites
				//quando o j é 0 estamos na primeira linha
					animations[j][i] = img.getSubimage(i*64, j*40, 64, 40);
				}
			}
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
	
	public void resetDirBooleans() {
		left = false;
		right = false;
		up = false;
		down = false;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}
	
	
}
