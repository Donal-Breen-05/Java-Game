package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

import enemy.Zombie;
import main.GamePanel;
import main.KeyHandler;
import main.UI;

public class Player extends Entity{
	GamePanel gp ; 
	KeyHandler keyH;

	public Zombie zombie;
	public int detectionRadius = 5 ;

	public final int screenx ;
	public final int screeny;

	//player specific stats
	public int armour;
	public int maxArmour;

	// combat
	public boolean attacking = false ;
	public int attackCounter = 0 ;
	public int attackDuration = 10;
	public Rectangle attackArea;


	//constructor 
	public Player(GamePanel gp ,KeyHandler keyH) {

		super(gp);

        this.gp = gp ;
		this.keyH = keyH ;


		screenx = gp.screenWidth/2 - (gp.tileSize - 2);
		screeny = gp.screenHeight/2 - (gp.tileSize - 2);

		//collision
		solidArea = new Rectangle();
		solidArea.x = 8 ;
		solidArea.y = 16;
		solidArea.width = 32;
		solidArea.height = 32;

		solidAreaDefaultY = solidArea.y;
		solidAreaDefaultX = solidArea.x;

		setDefaultValues(); 
		getPlayerImage();

		// collision for sword
		attackArea = new Rectangle(0, 0, gp.tileSize, gp.tileSize);

	}
	public void setDefaultValues() { 
		//all inherited from Entity class

		//world map position (values is starting position)
		worldx = 100 ;
		worldy = 100 ;

		speed = 4 ; 
		direction = "down"; 

		//stats
		maxHealth = 6 ;//6 half hearts
		health = maxHealth;
		damage = 2;
		armour = 0 ;
		maxArmour = 3;
	}

	// functions for player combat
	public Rectangle getAttackArea() {

		// start at the player's current world position
		int attackX = worldx;
		int attackY = worldy;

		// shift the attack box one tile in the direction the player is facing
		switch (direction) {
			case "up":
				attackY = worldy - gp.tileSize;
				break;
			case "down":
				attackY = worldy + gp.tileSize;
				break;
			case "left":
				attackX = worldx - gp.tileSize;
				break;
			case "right":
				attackX = worldx + gp.tileSize;
				break;
		}

		// return a hitbox the size of one tile, positioned in front of the player
		return new Rectangle(attackX, attackY, gp.tileSize, gp.tileSize);
	}

	public void updateAttackArea() {

		switch (direction) {
			case "up":
				attackArea.x = worldx;
				attackArea.y = worldy - gp.tileSize;
				break;
			case "down":
				attackArea.x = worldx;
				attackArea.y = worldy + gp.tileSize;
				break;
			case "left":
				attackArea.x = worldx - gp.tileSize;
				attackArea.y = worldy;
				break;
			case "right":
				attackArea.x = worldx + gp.tileSize;
				attackArea.y = worldy;
				break;
		}

		attackArea.width = gp.tileSize;
		attackArea.height = gp.tileSize;
	}
	
	public void update() {

		if (knockBack) {
			applyKnockBack();
			return; // skip normal input handling while being pushed back
		}

		// sets invincibility back to false after time
		updateInvincibility();

		// start a new swing on a fresh press
		if (keyH.attackPressed && !attacking) {
			attacking = true;
			attackCounter = 0;
			updateAttackArea();
			keyH.attackPressed = false;

			// check for enemies ONCE, right as the swing starts
			for (int i = 0; i < gp.enemy.length; i++) {
				if (gp.enemy[i] != null) {

					Rectangle enemyArea = gp.enemy[i].solidArea;
					Rectangle enemyHitbox = new Rectangle(
							gp.enemy[i].worldx + enemyArea.x,
							gp.enemy[i].worldy + enemyArea.y,
							enemyArea.width,
							enemyArea.height
					);

					if (attackArea.intersects(enemyHitbox)) {
						gp.enemy[i].takeDamage(damage);
						gp.enemy[i].knockBack = true;
						gp.enemy[i].startKnockBack(this);
					}
				}
			}
		}

		// while the sword is out, just handle the visual/timing — no more damage checks here
		if (attacking) {
			attackCounter++;
			if (attackCounter > attackDuration) {
				attacking = false;
				attackCounter = 0;
			}
		}


		//only up date if keys have been pressed 
		if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {

			if (keyH.upPressed) {

				direction = "up"; 
				//worldy -= speed; //moves character up

			} else if (keyH.downPressed) {

				direction = "down";
				//worldy += speed; //down

			} else if (keyH.leftPressed) {

				direction = "left";
				//worldx -= speed;//left

			}else if (keyH.rightPressed) {
				direction = "right"; 
				//worldx += speed; //right
			}
			
			//collision detection
			collisionOn = false;
			gp.cChecker.checkTile(this);

			//check event
			gp.eHandler.checkEvent();              

			//item collision
			int item_index  = gp.cChecker.checkItem(this , true);
			collectItem(item_index);

			//only let player move if collision is false
			if (collisionOn == false) {
				switch(direction) {
					case "up":
						worldy -= speed; //moves character up
						break;
					case "down":
						worldy += speed; //down
						break;
					case "left":
						worldx -= speed;//left
						break;
					case "right":
						worldx += speed; //right
						break;
				}
			}

			//entity collision
			for (int i = 0; i < gp.enemy.length; i++) {
				if (gp.enemy[i] != null) {
					if (gp.cChecker.checkEntityCollision(this, gp.enemy[i]) && !invincible && !knockBack) {
						knockBack = true;
						startKnockBack(gp.enemy[i]);
						this.takeDamage(1);
					}
				}
			}


			//change sprite image every 10 frames 
			spriteCounter++ ; 
			if(spriteCounter > 10 ) { 
				if (spriteNum ==1) { 
					spriteNum = 2; 
				} else if (spriteNum == 2 ) { 
					spriteNum = 1 ; 
				}
				spriteCounter = 0 ; 
			}

			if (health == 0) {
				gp.gameState = gp.endScreenState;
			}
		}
		
		
	}
	
	public void getPlayerImage() { 
		try { 
			PlayerUp1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/main-up1.png")));
			PlayerUp2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/main-up2.png")));
			PlayerDown1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/main-down-1.png")));
			PlayerDown2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/main-down-2.png")));
			PlayerLeft1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/main-left1.png")));
			PlayerLeft2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/main-left2.png")));
			PlayerRight1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/main-right1.png")));
			PlayerRight2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/main-right2.png")));

			//hurt images
			hurtPlayerUp1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/hurt-main-up1.png")));
			hurtPlayerUp2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/hurt-main-up2.png")));
			hurtPlayerDown1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/hurt-main-down-1.png")));
			hurtPlayerDown2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/hurt-main-down-2.png")));
			hurtPlayerLeft1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/hurt-main-left1.png")));
			hurtPlayerLeft2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/hurt-main-left2.png")));
			hurtPlayerRight1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/hurt-main-right1.png")));
			hurtPlayerRight2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/hurt-main-right2.png")));

			//player weapon
			swordImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/items/sword.png")));


		}catch(IOException e) { 
			e.printStackTrace();
		}
	}

	public void collectItem(int index) {

		if (index != 999) {
			String itemName = gp.item_array[index].name;

			switch(itemName) {
				case "coin":
					gp.item_array[index] = null;
					gp.ui.showMessage("Score + 1!");
					//score++;
					break;
				case "heart":

					if (health < 6){
						// to be added in the future
						gp.item_array[index] = null;
						gp.ui.showMessage("Health increased!");
						health++;
						if (health > 6) {
							health = 6;
						}
					}//end if


					break;
				case "sword":
					// to be added in the future
					gp.item_array[index] = null;
					gp.ui.showMessage("A Stronger Sword!, you should do more damage now!");
					//damage++;
					break;
				case "sheild":
					gp.ui.showMessage("Armour + 1!");
					gp.item_array[index] = null;
					//armour++;
			}

			gp.item_array[index] = null;
		}

	}
	
	public void draw(Graphics2D g2) { 
		
		BufferedImage image = null;

		//attcking
		if (attacking) {
			int drawX = screenx + (attackArea.x - worldx);
			int drawY = screeny + (attackArea.y - worldy);

			if (direction.equals("down")) {
				// flip 180 degrees around the center of the sword's tile
				g2.rotate(Math.PI, drawX + gp.tileSize / 2.0, drawY + gp.tileSize / 2.0);
				g2.drawImage(swordImage, drawX, drawY, gp.tileSize, gp.tileSize, null);
				g2.rotate(-Math.PI, drawX + gp.tileSize / 2.0, drawY + gp.tileSize / 2.0); // reset rotation
			} else {
				g2.drawImage(swordImage, drawX, drawY, gp.tileSize, gp.tileSize, null);
			}
		}

		
		
		//changes image depending on player movement  
		switch(direction) {

			case "up":
				if (invincible) {
					if (spriteNum == 1) {
						image = hurtPlayerUp1;
					} else {
						image = hurtPlayerUp2;
					}
				} else {
					if (spriteNum == 1) {
						image = PlayerUp1;
					} else {
						image = PlayerUp2;
					}
				}
				break;

			case "down":
				if (invincible) {
					if (spriteNum == 1) {
						image = hurtPlayerDown1;
					} else {
						image = hurtPlayerDown2;
					}
				} else {
					if (spriteNum == 1) {
						image = PlayerDown1;
					} else {
						image = PlayerDown2;
					}
				}
				break;

			case "left":
				if (invincible) {
					if (spriteNum == 1) {
						image = hurtPlayerLeft1;
					} else {
						image = hurtPlayerLeft2;
					}
				} else {
					if (spriteNum == 1) {
						image = PlayerLeft1;
					} else {
						image = PlayerLeft2;
					}
				}
				break;

			case "right":
				if (invincible) {
					if (spriteNum == 1) {
						image = hurtPlayerRight1;
					} else {
						image = hurtPlayerRight2;
					}
				} else {
					if (spriteNum == 1) {
						image = PlayerRight1;
					} else {
						image = PlayerRight2;
					}
				}
				break;
		}
		
		g2.drawImage(image, screenx, screeny, gp.tileSize, gp.tileSize, null);
	}
}


/*
 * 
 * 		g2.setColor(Color.white); 
		
		//x , y , w , h   (gp calls the game panel object and finds the variable because its imported from main) 
		g2.fillRect(x, y, gp.tileSize, gp.tileSize);//REMEBER TO ADD PLAYER X and Y variables (saves hours of debugging) 
*/ 
