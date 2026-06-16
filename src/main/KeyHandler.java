package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//takes care of user input? 
public class KeyHandler implements KeyListener {

	GamePanel gp;

	// NOT USED IN GAME PROGRAMMING (TOO SLOW) 
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	
	
	public boolean upPressed; 
	public boolean downPressed; 
	public boolean leftPressed; 
	public boolean rightPressed; 


	@Override
	public void keyPressed(KeyEvent e) {
		//gets current key
		int code = e.getKeyCode();

		//title state
		if (gp.gameState == gp.titleScreenState) {
			//has w been pressed?
			if (code == KeyEvent.VK_S) {
				gp.ui.commandNum--; //move cursor up in menu
				if (gp.ui.commandNum < 0 ) {
					gp.ui.commandNum = 2 ;
				}
			}
			if (code == KeyEvent.VK_W) {
				gp.ui.commandNum++;// move cursor down in menu
				if (gp.ui.commandNum > 2 ) {
					gp.ui.commandNum = 0 ;
				}
			}
			if (code == KeyEvent.VK_ENTER) {

				if (gp.ui.commandNum == 0 ) {
					gp.gameState = gp.playState; // start game
					System.out.println("startGame");
				}
				if (gp.ui.commandNum == 1 ) {
					System.out.println("Controls");
					gp.gameState = gp.controlScreenState;

				}
				if (gp.ui.commandNum == 2 ) {
					System.out.println("exitGame");
					System.exit(0); // quit game
				}
			}
		}

		// if play state
		if (gp.gameState == gp.playState) {
			if (code == KeyEvent.VK_W) { upPressed = true; }
			if (code == KeyEvent.VK_S) { downPressed = true; }
			if (code == KeyEvent.VK_A) { leftPressed = true; }
			if (code == KeyEvent.VK_D) { rightPressed = true; }
			if (code == KeyEvent.VK_ESCAPE) {
				gp.gameState = gp.pauseState;
			}
		}
		else if (gp.gameState == gp.pauseState) {  // ← else if, not if
			if (code == KeyEvent.VK_ESCAPE) {
				gp.gameState = gp.playState;
			}
		}
	}//end KeyPressed

	@Override
	public void keyReleased(KeyEvent e) {
		
		//gets current key 
		int code = e.getKeyCode(); 
		
		//has w been released? 
		if (code == KeyEvent.VK_W) { 
			upPressed = false;  	
		}
		if (code == KeyEvent.VK_S) { 
			downPressed = false; 
		}
		if (code == KeyEvent.VK_A) { 
			leftPressed = false; 
		}
		if (code == KeyEvent.VK_D) { 
			rightPressed = false; 
		}
		
	}

}
