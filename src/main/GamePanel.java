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
	private Game game;
	
	public GamePanel(Game game) {
		this.game = game;
		setPanelSize();
		keyBoardInputs = new KeyBoardInputs(this);
		mouseInputs = new MouseInputs(this);
		addKeyListener(keyBoardInputs);
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
	}
  

	
	
	private void setPanelSize() {
		Dimension dimension = new Dimension(1280,800);
		setPreferredSize(dimension);
		setMinimumSize(dimension);
		setMaximumSize(dimension);
	}		
	
	//Cuida somente da parte lógica
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.render(g);
		
	}
	
	public Game getGame() {
		return game;
	}
	
}
