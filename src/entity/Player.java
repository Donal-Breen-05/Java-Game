package entity;

import java.awt.Graphics2D;
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
				worldy -= speed; //moves character up

			} else if (keyH.downPressed) {
				direction = "down";
				worldy += speed; //down
			} else if (keyH.leftPressed) {
				direction = "left";
				worldx -= speed;//left
			}else if (keyH.rightPressed) {
				direction = "right"; 
				worldx += speed; //right
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
			PlayerUp1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/boy_up_1.png")));
			PlayerUp2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/boy_up_2.png")));
			PlayerDown1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/boy_down_1.png")));
			PlayerDown2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/boy_down_2.png")));
			PlayerLeft1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/boy_left_1.png")));
			PlayerLeft2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/boy_left_2.png")));
			PlayerRight1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/boy_right_1.png")));
			PlayerRight2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/boy_right_2.png")));
			
		}catch(IOException e) { 
			e.printStackTrace();
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
