package main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

import inputs.KeyBoardInputs;
import inputs.MouseInputs;

public class GamePanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private MouseInputs mouseInputs;
	private KeyBoardInputs keyBoardInputs;
	private float xDelta = 100,yDelta = 100,dirX = 1f,dirY=1f;
	private long frames = 0,lastCheck = 0;
	private Color color;
	private Random random;
	public GamePanel() {
		random = new Random();
		color = getRandomColor();
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
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		updateRectangule();
		g.setColor(color);
		g.fillRect((int)xDelta,(int) yDelta, 200, 50);
		
		
	}
	
	private void updateRectangule() {
		xDelta += dirX;
		yDelta += dirY;
		if(xDelta > 400  || xDelta < 0) {
			dirX*=-1;
			color = getRandomColor();
		}
		if(yDelta > 400 || yDelta < 0) {
			dirY*=-1;
			color = getRandomColor();
		}
	}
	
	private Color getRandomColor() {
		int r = random.nextInt(255);
		int g = random.nextInt(255);
		int b = random.nextInt(255);
		return new Color(r,g,b);
	}
}
