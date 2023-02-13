package main;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import inputs.KeyBoardInputs;
import inputs.MouseInputs;

import static main.Game.GAME_WIDTH;
import static main.Game.GAME_HEIGHT;;

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
		Dimension dimension = new Dimension(GAME_WIDTH,GAME_WIDTH);
		setPreferredSize(dimension);
		System.out.println("Size : " + GAME_WIDTH + " | " + GAME_HEIGHT);
		
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
