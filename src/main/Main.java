package main; 

import javax.swing.JFrame; 

public class Main {
	
	public static void main(String args[]) { 
		
		JFrame window = new JFrame(); 
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		window.setResizable(false);
		window.setTitle("Zombie Survival");
		
		//calls a regular window but with options from "GamePanel" 
		GamePanel gamePanel = new GamePanel(); 
		window.add(gamePanel); 
		
		window.pack();//it works now (DONT TOUCH THIS LINE) 
		
		//screen location isnt bound 
		window.setLocationRelativeTo(null); 
		window.setVisible(true); 

		//sets items
		gamePanel.setupGame();
		gamePanel.startGameThread();
		d
	}
}