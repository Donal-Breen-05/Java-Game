package main; 


//dependencies 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.swing.JPanel;

import enemy.Zombie;
import entity.Entity;
import entity.Player;
import item.Item;
import tile.TileManager;


//constructor? 
public class GamePanel extends JPanel implements Runnable{
	
	//screen settings 
	public final int originalTileSize = 16; //16x16 tile 
	public final int scale = 3; 
		
	public final int tileSize = originalTileSize * scale; //48x48 
	public final int maxScreenRow = 24;// originally 20
	public final int maxScreenCol = 20; // originally 16
	
	public final int screenHeight = tileSize * maxScreenRow;
	public final int screenWidth = tileSize * maxScreenCol;



	//world map settings
	public final int maxWorldCol = 50 ;
	public final int maxWorldRow = 50;
	public final int worldHeight = tileSize * maxWorldRow;
	public final int worldWidth = tileSize * maxWorldCol;

	
	//fps 
	int fps = 60; 




	//engine
	TileManager tileM = new TileManager(this); 
	KeyHandler keyH = new KeyHandler(this);
	Thread gameThread; 	//thread for drawing screen
	public UI ui = new UI(this);


	// GAME STATE
	public int gameState;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int titleScreenState = 0;
	public final int controlScreenState = 3;

	//entity / player
	public Player player = new Player(this,keyH);

	//enemy
	public Zombie[] enemy = new Zombie[20];
	public EnemySet enemySetter = new EnemySet(this);

	//collision detection
	public CollisionDetection cChecker = new CollisionDetection(this);

	//set items
	public ItemSet itemSetter = new ItemSet(this);

	//items
	public Item[] item_array = new Item[10];

	//events
	public Event eHandler = new Event(this);

	
	public GamePanel() { 
		this.setPreferredSize(new Dimension(screenWidth , screenHeight)); 
		this.setBackground(Color.black); 
		this.setDoubleBuffered(true); 
		this.addKeyListener(keyH);//needed for keyboard input also saves lots of debugging 
		this.setFocusable(true);
		
	}

	//set up items and entity
	public void setupGame() {
		itemSetter.setItem();
		try {
			enemySetter.setEnemy();
		} catch (IOException e) {
			e.printStackTrace();
		}
		gameState = titleScreenState; //initially sets as the title screen
	}
	
	//start main thread (main game loop) 
	public void startGameThread() {
		
		//Instantiate the game thread 
		gameThread = new Thread(this); //calls the thread object referencing current class to makegameThread
		gameThread.start(); //starts main loop?
		
	}
	

	
	// sleep method 
	@Override
	public void run() {
		
		double drawInterval = (double) 1000000000 /fps; //0.1666 second in nano seconds (very precise)
		double nextDrawTime = System.nanoTime() + drawInterval; //adds the interval to the internal time to slow it 
		//all inside while will continue as long as game thread exists 
		while (gameThread != null) {
			
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

		//if game is playing update player
		if (gameState == playState) {
			for(int i = 0  ; i < enemy.length; i++) {
				if(enemy[i] != null) {
					enemy[i].update();
				}
			}
			//calls player object and calls function from player class
			player.update();
		}
		if (gameState == pauseState) {
			// blank for now
		}


	}
	
	public void paintComponent(Graphics g) { 

		//calls the parent class(JPannel) to paint the component to the screen 
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g; //extends the graphics class (inherits it)

		if (gameState == titleScreenState) {
			ui.draw(g2);
		}
		else {
			//always before everything else because it needs to be in layers
			tileM.draw(g2);

			//items
			for (Item item : item_array) {

				if (item != null) {

					item.draw(g2, this);

				}//end if

			}//end for

            for (int i = 0 ; i < enemy.length ; i++) {
                if (enemy[i] != null) {
                    enemy[i].draw(g2 , this);
                }
            }
			//calls player object and calls function from player class
			player.draw(g2);


			//ui on top layer
			ui.draw(g2);
			g2.dispose();

		}// end else
		

		
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