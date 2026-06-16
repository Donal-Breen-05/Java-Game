package main;

import item.Item_sheild;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    GamePanel gp;
    Graphics2D g2 ;

    Font comic_sans;
    Font comic_sansLarge;

    BufferedImage sheildImage;
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

        Item_sheild ui_sheild = new Item_sheild();
        sheildImage = ui_sheild.image;

    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        //title state
        if (gp.gameState == gp.titleScreenState) {
            drawTitle();
        }
        //if play state
        if (gp.gameState == gp.playState) {


            //normal play ui
            g2.setFont(comic_sans);
            g2.setColor(Color.white);
            g2.drawString("test" , 50, 50);
            g2.drawImage(sheildImage , (gp.tileSize/2) * 8 ,gp.tileSize/2 , gp.tileSize , gp.tileSize, null );

            if (messageOn) {

                g2.setFont(g2.getFont().deriveFont(40F));
                g2.drawString(message , gp.tileSize/2 , gp.tileSize*5 );

                messageTime++ ;

                //disapear after 240 frames (4 seconds)
                if (messageTime  > 240) {
                    messageTime = 0;
                    messageOn = false;
                }
            }

            //game over?
            if(gameFinished) {

                //game over screen
                g2.setFont(comic_sansLarge);
                g2.setColor(Color.red);


                //get textLength
                String text = "Game Over";
                int x = centerText(text);
                int y = gp.screenHeight / 2 ;

                g2.drawString(text , x  ,y);

                //g2.drawString(Player.score , x ,y );

                //end the game
                gp.gameThread = null;

            }//end game finished
        }else if (gp.gameState == gp.pauseState){
            // pause ui
            drawPauseScreen();

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

        text = "EXIT GAME!";
        x= centerText(text);
        y += gp.tileSize;
        y += gp.tileSize;
        g2.drawString(text,x ,y);
        if (commandNum == 1) {
            g2.drawString(">", x -gp.tileSize, y);
        }

    } //end draw title

    public void drawPauseScreen() {

        g2.setFont(comic_sans);
        g2.setColor(Color.white);

        String text = "paused";
        int x = centerText(text);
        int y = gp.screenHeight / 2 ;

        g2.drawString(text , x  ,y);
    }

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
