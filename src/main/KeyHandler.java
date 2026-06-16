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
		
		//has w been pressed? 
		if (code == KeyEvent.VK_W) { 
			upPressed = true; 
		}
		if (code == KeyEvent.VK_S) { 
			downPressed = true; 
		}
		if (code == KeyEvent.VK_A) { 
			leftPressed = true; 
		}
		if (code == KeyEvent.VK_D) { 
			rightPressed = true; 
		}
		if (code == KeyEvent.VK_ESCAPE) {

			//if pause is pressed
			if (gp.gameState == gp.playState) {

				//set state to pauses
				gp.gameState = gp.pauseState;

			}
			else if (gp.gameState == gp.pauseState){
				gp.gameState = gp.playState;
			}//end else if

		}
		
	}// end key pressed

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
