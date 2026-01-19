package main; 


//dependencies 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager; 


//constructor? 
public class GamePanel extends JPanel implements Runnable{
	
	//screen settings 
	public final int originalTileSize = 16; //16x16 tile 
	public final int scale = 3; 
		
	public final int tileSize = originalTileSize * scale; //48x48 
	public final int maxScreenRow = 16; 
	public final int maxScreenCol = 12; 
	
	public final int screenHeight = tileSize * maxScreenRow; //768
	public final int screenWidth = tileSize * maxScreenCol;//576



	//world map settings
	public final int maxWorldCol = 50 ;
	public final int maxWorldRow = 50;
	public final int worldHeight = tileSize * maxWorldRow;
	public final int worldWidth = tileSize * maxWorldCol;

	
	//fps 
	int fps = 60; 

	
	TileManager tileM = new TileManager(this); 
	KeyHandler keyH = new KeyHandler();
	Thread GameThread; 	//thread for drawing screen 
	public Player player = new Player(this,keyH);

	
	public GamePanel() { 
		this.setPreferredSize(new Dimension(screenWidth , screenHeight)); 
		this.setBackground(Color.black); 
		this.setDoubleBuffered(true); 
		this.addKeyListener(keyH);//needed for keyboard input also saves lots of debugging 
		this.setFocusable(true);
		
	}
	
	//start main thread (main game loop) 
	public void startGameThread() {
		
		//Instantiate the game thread 
		GameThread = new Thread(this); //calls the thread object referencing current class to make GameThread 
		GameThread.start(); //starts main loop? 
		
	}
	

	
	// sleep method 
	@Override
	public void run() {
		
		double drawInterval = (double) 1000000000 /fps; //0.1666 second in nano seconds (very precise)
		double nextDrawTime = System.nanoTime() + drawInterval; //adds the interval to the internal time to slow it 
		//all inside while will continue as long as game thread exists 
		while (GameThread != null) {
			
			//update char position 
			update(); 
			 	
			//draw screen with updated info 
			repaint(); //calls the paint component function (it has a weird name convention)

			
			//try catch is just for errors 
			try {

				double remainingTime = nextDrawTime - System.nanoTime(); 
				remainingTime = remainingTime/1000000;//converts to seconds 
				
				if (remainingTime < 0 ) { 
					remainingTime = 0 ; 
				}
				
				
				Thread.sleep((long) remainingTime);//slows the computer to wait remaining time + (casts it to correct type)
				
				
				//
				nextDrawTime += drawInterval; 
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}

	
	
	

	
	
	//fancy display stuff 
	public void update() {
		
		
		//calls player object and calls function from player class 
		player.update();
	}
	
	public void paintComponent(Graphics g) { 
		
		//calls the parent class(JPannel) to paint the component to the screen 
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g; //extends the graphics class (inherits it)
		
		
		//always before everything else because it needs to be in layers 
		tileM.draw(g2);
		
		//calls player object and calls function from player class 		
		player.draw(g2);
		
		g2.dispose();
		
	}
}





/* 
Broken needs to be fixed 

TODO: fix method 
//DELTA /ACUMULATOR method  
	@Override
	public void run() {
			
		double drawInterval = 1000000000/fps; 
		double delta = 0 ; 
		long lastTime = System.nanoTime(); 
		long currentTime ; 
		
	
		while (GameThread != null) {
			currentTime = System.nanoTime() ; 
			
			delta += (currentTime -lastTime) /drawInterval; 
			
			if (delta >= 1) { 

				//update char position 
				update(); 
				
				//draw screen with updated info 
				repaint(); //calls the paintcomponent function (it has a weird name convention) 
				
				delta--; 
			}
				
				
		}
	} 

*/ 