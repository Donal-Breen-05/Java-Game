package enemy;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Currency;
import java.util.Objects;
import java.util.Random;

public class Zombie extends Entity{

    public int screenx ;
    public int screeny;

    public int maxLife;
    public int life;
    public String name;

    //zombie states
    public int idelState = 0 ;
    public int followState = 1;
    public int currentState = idelState;

    public int actionLockCounter;

    public Zombie (GamePanel gp) throws IOException {
        super(gp);

        //enemy states

        //stats
        this.name = "zombie";
        speed = 2 ;
        maxLife = 8;
        life = maxLife;
        damage = 1;

        //for collisions
        solidArea.x = 8 ;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        //sprite
        direction = "down";
        getImage();
    }

    public void getImage() throws IOException {


        //regular images
        PlayerUp1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/enemy/zombie/zom-up1.png")));
        PlayerUp2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/enemy/zombie/zom-up2.png")));
        PlayerDown1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/enemy/zombie/zom-down1.png")));
        PlayerDown2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/enemy/zombie/zom-down2.png")));
        PlayerLeft1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/enemy/zombie/zom-left1.png")));
        PlayerLeft2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream( "/enemy/zombie/zom-left2.png")));
        PlayerRight1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/enemy/zombie/zom-right1.png")));
        PlayerRight2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/enemy/zombie/zom-right2.png")));

        //hurt zombie
        hurtPlayerUp1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/enemy/zombie/hurt-zom-up1.png")));
        hurtPlayerUp2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/enemy/zombie/hurt-zom-up2.png")));
        hurtPlayerDown1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/enemy/zombie/hurt-zom-down1.png")));
        hurtPlayerDown2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/enemy/zombie/hurt-zom-down2.png")));
        hurtPlayerLeft1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/enemy/zombie/hurt-zom-left1.png")));
        hurtPlayerLeft2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream( "/enemy/zombie/hurt-zom-left2.png")));
        hurtPlayerRight1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/enemy/zombie/hurt-zom-right1.png")));
        hurtPlayerRight2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/enemy/zombie/hurt-zom-right2.png")));

    }

    public void idleRandomMovement() {


        //check collision
        collisionOn = false;
        gp.cChecker.checkTile(this);

        if (!collisionOn) {
            switch (direction) {
                case "up":
                    worldy -= speed;
                    break;
                case "down":
                    worldy += speed;
                    break;
                case "left":
                    worldx -= speed;
                    break;
                case "right":
                    worldx += speed;
                    break;
            }
        }

        actionLockCounter++;

        if (actionLockCounter >= 240) {

            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if (i <= 25) {
                direction = "up";
            }
            else if (i <= 50) {
                direction = "down";
            }
            else if (i <= 75) {
                direction = "left";
            }
            else {
                direction = "right";
            }

            actionLockCounter = 0;
        }
    }

    public void update(){

        if (currentState == idelState) {
            idleRandomMovement();
        }else if (currentState == followState){
            System.out.println("follow player");
            //follow();
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

    public void draw(Graphics2D g2 ,GamePanel gp) {

        // camera-relative position
        screenx = worldx - gp.player.worldx + gp.player.screenx;
        screeny = worldy - gp.player.worldy + gp.player.screeny;

        //improved switch for enemy movement
        BufferedImage image = switch (direction) {
            case "up" -> (spriteNum == 1) ? PlayerUp1 : PlayerUp2;
            case "down" -> (spriteNum == 1) ? PlayerDown1 : PlayerDown2;
            case "left" -> (spriteNum == 1) ? PlayerLeft1 : PlayerLeft2;
            case "right" -> (spriteNum == 1) ? PlayerRight1 : PlayerRight2;
            default -> null;
        };

        //don't draw enemy's not on screen
        if (worldx + gp.tileSize > gp.player.worldx - gp.player.screenx &&
                worldx - gp.tileSize < gp.player.worldx + gp.player.screenx &&
                worldy + gp.tileSize > gp.player.worldy - gp.player.screeny &&
                worldy - gp.tileSize < gp.player.worldy + gp.player.screeny) {

            g2.drawImage(image, screenx, screeny, gp.tileSize, gp.tileSize, null);
        }
    }
}