package main;

import java.awt.Graphics;

import entities.Player;

public class Game implements Runnable{

	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private final long  FPS_SET = 120;
	private final long  UPDATE_SET = 200;
	private Thread gameThread;
	private Player player;
	
	public Game() {
		initClasses();
		gamePanel = new GamePanel(this);
		gameWindow = new GameWindow(gamePanel);
		gamePanel.requestFocus();
		startGameLoop();
	}
	
	private void initClasses() {
		player = new Player(100, 100);
		
	}

	public void  update() {
		player.update();
	}
	
	public void render(Graphics g) {
		player.render(g);
	}

	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	@Override
	public void run() {
		//tempo para a execução de um frame
		double timePerFrame = 1000000000 / FPS_SET;
		double timePerUpdate = 1000000000 / UPDATE_SET;
		long frames = 0;
		long lastCheck = System.currentTimeMillis();
		long previusTime = System.nanoTime();
		double deltaU =0,deltaF = 0;
		long updates = 0;
		while(true) {
			long currentTime = System.nanoTime();
			deltaU+= (currentTime - previusTime) / timePerUpdate;
			deltaF+= (currentTime - previusTime) / timePerFrame;
			previusTime = currentTime;
			/*
			 * Esse trecho de código apresenta um loop de atualização básico para um jogo. 
			 * O objetivo é manter a taxa de quadros (frames per second, ou FPS) consistente, 
			 * independentemente da velocidade do hardware que está executando o jogo.
			 * Se "deltaU" é maior ou igual a 1, isso significa que já passou o tempo esperado para uma atualização.
			 *  Nesse caso, a variável "updates" é incrementada e "deltaU" é decrementado em 1. 
			 *  Esse processo é repetido várias vezes por segundo, mantendo a taxa de atualização constante.
			 */
			if(deltaU >= 1) {
				update();
				updates++;
				deltaU--;
				
			}
			
			if(deltaF >= 1) {
				gamePanel.repaint();
				frames++;
				deltaF--;
			}
			
			
			//Se passou um segundo
			if(System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS: " +frames + " | Updates: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}
	
	public void lostWindowFocus() {
		player.resetDirBooleans();
	}
	
	public Player getPlayer() {
		return player;
	}
}
