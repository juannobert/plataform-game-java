package main;

public class Game implements Runnable{

	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private final long  FPS_SET = 120;
	private final long  UPDATE_SET = 200;
	private Thread gameThread;
	
	public Game() {
		gamePanel = new GamePanel();
		gameWindow = new GameWindow(gamePanel);
		gamePanel.requestFocus();
		startGameLoop();
	}
	
	public void  update() {
		gamePanel.updateGame();
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
}
