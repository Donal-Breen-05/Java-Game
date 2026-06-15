package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
	GamePanel gp ; 
	KeyHandler keyH;

	public final int screenx ;
	public final int screeny;
	
	//constructor 
	public Player(GamePanel gp ,KeyHandler keyH) {
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

		solidAreaDefaultY = solidArea.x;
		solidAreaDefaultX = solidArea.y;

		setDefaultValues(); 
		getPlayerImage(); 
		
	}
	public void setDefaultValues() { 
		//all inherited from Entity class

		//world map position (values is starting position)
		worldx = 100 ;
		worldy = 100 ;

		speed = 4 ; 
		direction = "down"; 
		
	}
	
	public void update() {
		
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
					score++;
					break;
				case "heart":
					if (health < 6){
						// to be added in the future
						gp.item_array[index] = null;
						health++;
						if (health > 6) {
							health = 6;
						}
					}//end if
					break;
				case "sword":
					// to be added in the future
					gp.item_array[index] = null;
					damage++;
					break;
				case "sheild":
					gp.item_array[index] = null;
					armour++;
			}

			gp.item_array[index] = null;
		}

	}
	
	public void draw(Graphics2D g2) { 
		
		BufferedImage image = null; 
		
		
		//changes image depending on player movement  
		switch(direction) { 
		case "up": 
			if (spriteNum ==1 ) {
				image = PlayerUp1; 
			} else if (spriteNum == 2){ 
				image = PlayerUp2; 
			}
			break; 
		case "down": 
			if (spriteNum ==1 ) {
				image = PlayerDown1;  
			} else if (spriteNum == 2){ 
				image = PlayerDown2; 
			}
			break; 
		case "left": 
			if (spriteNum ==1 ) {
				image = PlayerLeft1;  
			} else if (spriteNum == 2){ 
				image = PlayerLeft2;
			}
			break; 
		case "right": 
			if (spriteNum ==1 ) {
				image = PlayerRight1;  
			} else if (spriteNum == 2){ 
				image = PlayerRight2;
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
