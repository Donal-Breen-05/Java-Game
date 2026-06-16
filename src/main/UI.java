package main;

import item.Item;
import item.Item_heart;
import item.Item_sheild;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    GamePanel gp;
    Graphics2D g2 ;

    Font comic_sans;
    Font comic_sansLarge;

    BufferedImage shieldImage;
    BufferedImage health_full , health_half, health_empty;

    public boolean messageOn = false;
    public String message = "";
    int messageTime = 0 ;

    public int commandNum = 0;
    //end condition
    public boolean gameFinished = false;



    public UI(GamePanel gp) {

        this.gp = gp;

        comic_sans = new Font("Comic Sans MS" , Font.BOLD, 60);
        comic_sansLarge = new Font("Comic Sans MS" , Font.BOLD, 120);

        //hud objects
        //armour
        Item_sheild ui_sheild = new Item_sheild();
        shieldImage = ui_sheild.image;

        //hearts
        Item heart = new Item_heart(gp);
        health_full = heart.image;
        health_half = heart.image2;
        health_empty =heart.image3;

    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        //title state
        if (gp.gameState == gp.titleScreenState) {
            drawTitle();
        }
        if (gp.gameState == gp.controlScreenState) {
            showControls();
        }
        //if play state
        if (gp.gameState == gp.playState) {

            //normal play ui
            drawPlayerLife();

        }else if (gp.gameState == gp.pauseState){
            // pause ui
            drawPauseScreen();
            drawPlayerLife();

        }

    }// end draw

    public void drawTitle() {


        // background
        g2.setColor(Color.BLACK);
        g2.fillRect(0 , 0, gp.screenWidth, gp.screenHeight);

        //title + font
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "Zombie Survival";
        int x = centerText(text);
        int y = gp.tileSize*3;

        //shadow
        g2.setColor(Color.GRAY);
        g2.drawString(text, x+5, y+5);
        //text colour
        g2.setColor(Color.white);
        g2.drawString(text , x , y);


        //images for title screen
        x = gp.screenWidth/2 ;
        y += gp.tileSize*2; // two tiles down

        //player image (right)
        g2.drawImage(gp.player.PlayerRight1, x+gp.tileSize, y , gp.tileSize*2, gp.tileSize*2 , null);
        //sword image (middle)
        g2.drawImage(gp.item_array[1].image, x -gp.tileSize, y , gp.tileSize*2, gp.tileSize*2 , null);
        //Zombie image (left) // todo make Zombie sprite
        g2.drawImage(gp.player.PlayerRight2, x -gp.tileSize*3, y , gp.tileSize*2, gp.tileSize*2 , null);

        //menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));

        text = "START GAME!";
        x= centerText(text);
        y += gp.tileSize *4;
        g2.drawString(text,x ,y);

        if (commandNum == 0) {
            g2.drawString(">", x -gp.tileSize, y);
        }

        text = "CONTROLS!";
        x= centerText(text);
        y += gp.tileSize ;
        g2.drawString(text,x ,y);
        if (commandNum == 1) {
            g2.drawString(">", x -gp.tileSize, y);
        }


        text = "EXIT GAME!";
        x= centerText(text);
        y += gp.tileSize;
        g2.drawString(text,x ,y);
        if (commandNum == 2) {
            g2.drawString(">", x -gp.tileSize, y);
        }

    } //end draw title

    public void showControls() {



        //clear screen
        g2.setColor(Color.BLACK);
        g2.fillRect(0 , 0, gp.screenWidth, gp.screenHeight);

        //text
        g2.setFont(comic_sans);
        g2.setColor(Color.white);

        String text;
        int x , y = 0;

        text = "Controls!";
        x= centerText(text);
        y += gp.tileSize * 4;
        g2.drawString(text,x ,y);

        text = "Walk Up   -> ( W ) ";
        x= centerText(text);
        y += gp.tileSize;
        g2.drawString(text,x ,y);

        text = "Walk Left -> ( A ) ";
        x= centerText(text);
        y += gp.tileSize;
        g2.drawString(text,x ,y);

        text = "Walk Down -> ( S ) ";
        x= centerText(text);
        y += gp.tileSize;
        g2.drawString(text,x ,y);

        text = "Walk right -> ( D ) ";
        x= centerText(text);
        y += gp.tileSize;
        g2.drawString(text,x ,y);

        text = "Pause      -> ( ESC ) ";
        x= centerText(text);
        y += gp.tileSize;
        g2.drawString(text,x ,y);

        text = "Attack     -> ( SPACE ) ";
        x= centerText(text);
        y += gp.tileSize;
        g2.drawString(text,x ,y);

        messageTime++;

        //disapear after 240 frames (4 seconds)
        if (messageTime  > 240) {
            messageTime = 0;
            messageOn = false;

            gp.gameState = gp.titleScreenState;

            //return to tile screen
            drawTitle();

        }




    }

    public void drawPauseScreen() {

        g2.setFont(comic_sans);
        g2.setColor(Color.white);

        String text = "paused";
        int x = centerText(text);
        int y = gp.screenHeight / 2 ;

        g2.drawString(text , x  ,y);
    }

    public void drawPlayerLife(){

        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int maxHearts = gp.player.maxHealth/2 ;
        int fullHearts = gp.player.health/2;
        int halfHearts = gp.player.health%2;

        for (int i = 0; i < maxHearts; i++) {
            // Draw empty heart background for all slots first
            g2.drawImage(health_empty,  x, y, gp.tileSize, gp.tileSize, null);
            x += gp.tileSize;
        }

        // reset x
        x = gp.tileSize/2;

        //draw full and half hearts over empty hearts
        for(int i = 0; i < fullHearts; i++) {
            g2.drawImage(health_full, x, y, gp.tileSize, gp.tileSize, null);
            x += gp.tileSize;
        }
        for(int i = 0; i < halfHearts; i++) {
            g2.drawImage(health_half, x, y, gp.tileSize, gp.tileSize, null);
            x += gp.tileSize;
        }
    }

    //public void drawPlayerArmour() {
    //             g2.drawImage(shieldImage, (gp.tileSize/2) * 8 ,gp.tileSize/2 , gp.tileSize , gp.tileSize, null );
    // }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }


    //helper method to center text
    public int centerText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth/2 - length/2 ;
    }

}
