package main;

public class Game implements Runnable{

	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private final long  FPS_SET = 120;
	private Thread gameThread;
	
	public Game() {
		gamePanel = new GamePanel();
		gameWindow = new GameWindow(gamePanel);
		gamePanel.requestFocus();
		startGameLoop();
	}

	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	@Override
	public void run() {
		//tempo para a execução de um frame
		double timePerFrame = 1000000000 / FPS_SET;
		long lastTime = System.nanoTime();
		long now = System.nanoTime();
		long frames = 0;
		long lastCheck = System.currentTimeMillis();
		while(true) {
			now = System.nanoTime();
			if(now - lastTime > timePerFrame) {
				gamePanel.repaint();
				lastTime = now;
				frames++;
			}
			
			
			//Se passou um segundo
			if(System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS: " +frames);
				frames = 0;
			}
		}
		
		
	}
}
