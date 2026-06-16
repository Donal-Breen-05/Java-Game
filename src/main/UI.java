package main;

import item.Item_sheild;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    GamePanel gp;
    Font comic_sans;
    Font comic_sansLarge;

    BufferedImage sheildImage;
    public boolean messageOn = false;
    public String message = "";
    int messageTime = 0 ;

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


        //game over?
        if(gameFinished) {

            g2.setFont(comic_sansLarge);
            g2.setColor(Color.red);

            int x ;
            int y ;

            //get textLength
            String text = "Game Over";
            int textLength = (int)g2.getFontMetrics().getStringBounds(text , g2).getWidth();

            x  = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - (gp.tileSize*3);

            g2.drawString(text , x ,y );
            //g2.drawString(Player.score , x ,y );

            //end the game
            gp.gameThread = null;

        }else {
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
        }


    }// end draw

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }
}
