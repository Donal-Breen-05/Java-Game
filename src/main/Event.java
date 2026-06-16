package main;

import java.awt.*;

public class Event {
    GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX , eventRectDefaultY;

    public Event(GamePanel gp){
        this.gp = gp ;

        eventRect = new Rectangle();

        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.width = 2;
        eventRect.height =2;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;


    }

    public void checkEvent(){

        if (hit(4,4 ,"right")) {
            gp.player.health--;
        }
    }

    //helper method for checkEvent
    public boolean hit(int eventCol , int eventRow , String reqDirection) {

        boolean hit = false;
        gp.player.solidArea.x = gp.player.worldx + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldy + gp.player.solidArea.y;

        eventRect.x = eventCol*gp.tileSize + eventRect.x;
        eventRect.y = eventCol*gp.tileSize + eventRect.y;

        //if the player is in the event rectangle
        if (gp.player.solidArea.intersects(eventRect)) {
            if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;

            }
        }
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;

        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;

        return hit;
    }

}
