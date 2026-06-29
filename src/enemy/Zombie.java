package enemy;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
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

    }

    public void idleRandomMovement() {

        int actionLockCounter = 0;
        actionLockCounter++;

        if (actionLockCounter == 120){

            Random random = new Random();
            int i = random.nextInt(100)+1; // 1 to 100 random int

            if (i <= 25){direction = "up";}
            if (i > 25 && i <= 50){direction = "down";}
            if (i > 50 && i <= 75) {direction = "left";}
            if (i > 75 && i <= 100){direction = "right";}



            actionLockCounter = 0;
        } // end if
    }// end random movement

    public void update(){

        if (currentState == idelState) {
            idleRandomMovement();
        }else if (currentState == followState){
            System.out.println("follow player");
            //follow();
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

        //dont draw enemys not on screen
        if (worldx + gp.tileSize > gp.player.worldx - gp.player.screenx &&
                worldx - gp.tileSize < gp.player.worldx + gp.player.screenx &&
                worldy + gp.tileSize > gp.player.worldy - gp.player.screeny &&
                worldy - gp.tileSize < gp.player.worldy + gp.player.screeny) {

            g2.drawImage(image, screenx, screeny, gp.tileSize, gp.tileSize, null);
        }
    }
}