package entity;


import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

	public GamePanel gp;

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

	//hurt entity sprites
	public BufferedImage hurtPlayerUp1;
	public BufferedImage hurtPlayerUp2;
	public BufferedImage hurtPlayerDown1;
	public BufferedImage hurtPlayerDown2;
	public BufferedImage hurtPlayerLeft1;
	public BufferedImage hurtPlayerLeft2;
	public BufferedImage hurtPlayerRight1;
	public BufferedImage hurtPlayerRight2;

	public BufferedImage swordImage;


	public String direction ; 
	
	//used to cycle animations 
	public int spriteCounter = 0 ; 
	public int spriteNum = 1;

	//variables for collision detection
	public Rectangle solidArea;
	public Boolean collisionOn = false;

	public int solidAreaDefaultX , solidAreaDefaultY;

	// health
	public int health;
	public int maxHealth;
	public int damage;

	// combat
	public boolean knockBack = false;
	double knockBackX;
	double knockBackY;
	public int knockBackCounter = 0;

	// damage animation
	public boolean invincible = false;
	public int invincibleCounter = 0;


	public void takeDamage(int damage) {
		if (!invincible) {
			health -= damage;
			invincible = true;
			invincibleCounter = 0;
		}
	}

	public void updateInvincibility() {
		if (invincible) {
			invincibleCounter++;

			// 1.5 seconds in game invincibility
			if (invincibleCounter > 90) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
	}

	public void startKnockBack(Entity attacker) {
		int dx = worldx - attacker.worldx;
		int dy = worldy - attacker.worldy;

		double length = Math.sqrt(dx * dx + dy * dy);

		knockBackX = dx / length;
		knockBackY = dy / length;

		knockBack = true;
	}

	public void applyKnockBack() {

		collisionOn = false;

		worldx += (int)(knockBackX * speed * 2.5);
		worldy += (int)(knockBackY * speed * 2.5);

		knockBackCounter++;

		if (knockBackCounter >= gp.tileSize / speed) {
			knockBack = false;
			knockBackCounter = 0;
		}
	}// end apply knockback


	//check if zombie is in the players radius
	public boolean checkRadius(Entity target, int tileRadius) {

		int radius = gp.tileSize * tileRadius;

		int dx = target.worldx - worldx;
		int dy = target.worldy - worldy;

		int distanceSquared = dx * dx + dy * dy;

		return distanceSquared <= radius * radius;
	}

	public Entity(GamePanel gp) {
		this.gp = gp;
		solidArea = new Rectangle();
	}

	public Entity() {}

}
