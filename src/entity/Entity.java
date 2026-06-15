package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
	
	//entity variables 
	public int worldx, worldy ;
	public int speed ; 
	
	
	//player sprites  
	BufferedImage PlayerUp1 , PlayerUp2 , PlayerDown1 , PlayerDown2 , PlayerLeft1 , PlayerLeft2 , PlayerRight1 , PlayerRight2; 
	public String direction ; 
	
	//used to cycle animations 
	public int spriteCounter = 0 ; 
	public int spriteNum = 1;

	//variables for collision detection
	public Rectangle solidArea;
	public Boolean collisionOn = false;

	public int solidAreaDefaultX , solidAreaDefaultY;

}	
