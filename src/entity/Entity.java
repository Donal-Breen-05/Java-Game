package entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
	
	//entity variables 
	public int worldx, worldy ;
	public int speed ;
	
	//player sprites  
    public BufferedImage PlayerUp1;
	public BufferedImage PlayerUp2;
	public BufferedImage PlayerDown1;
	public BufferedImage PlayerDown2;
	public BufferedImage PlayerLeft1;
	public BufferedImage PlayerLeft2;
    public BufferedImage PlayerRight1;
    public BufferedImage PlayerRight2;
	public String direction ; 
	
	//used to cycle animations 
	public int spriteCounter = 0 ; 
	public int spriteNum = 1;

	//variables for collision detection
	public Rectangle solidArea;
	public Boolean collisionOn = false;

	public int solidAreaDefaultX , solidAreaDefaultY;

	public int health;
	public int maxHealth;
	public int damage;


	public Entity(GamePanel gp) {
		solidArea = new Rectangle();
	}
    public Entity() {
    }
}
