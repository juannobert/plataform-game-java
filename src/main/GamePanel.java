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

public class GamePanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private MouseInputs mouseInputs;
	private KeyBoardInputs keyBoardInputs;
	private BufferedImage img,subImg;
	private float xDelta = 0,yDelta = 0;
	
	public GamePanel() {
		setPanelSize();
		importImg();
		keyBoardInputs = new KeyBoardInputs(this);
		mouseInputs = new MouseInputs(this);
		addKeyListener(keyBoardInputs);
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
	}
  

	public void changeXDelta(int value) {
		this.xDelta += value;
		repaint();
	}
	
	public void changeYDelta(int value) {
		this.yDelta += value;
		repaint();
	}
	
	public void setRectPost(int x,int y) {
		this.xDelta = x;
		this.yDelta = y;
		repaint();
	}
	
	private void importImg() {
		InputStream is = getClass().getResourceAsStream("/player_sprites.png");
		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}


	private void setPanelSize() {
		Dimension dimension = new Dimension(1280,800);
		setPreferredSize(dimension);
		setMinimumSize(dimension);
		setMaximumSize(dimension);
		
	}


	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//Pegando imagem na coluna 8 e linha 1
		subImg = img.getSubimage(1*64, 8*40, 64, 40);
		g.drawImage(subImg, (int)xDelta, (int) yDelta,128,80, null);
	}
}
