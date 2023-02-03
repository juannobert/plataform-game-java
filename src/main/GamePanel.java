package main;

import java.awt.Graphics;

import javax.swing.JPanel;

import inputs.KeyBoardInputs;
import inputs.MouseInputs;

public class GamePanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private MouseInputs mouseInputs;
	private KeyBoardInputs keyBoardInputs;
	private int xDelta,yDelta = 0;
	public GamePanel() {
		keyBoardInputs = new KeyBoardInputs(this);
		mouseInputs = new MouseInputs();
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
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.fillRect(xDelta, yDelta, 200, 50);
	}
}
