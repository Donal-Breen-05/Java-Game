package main;

import entity.Entity;
import item.Item;

public class CollisionDetection {

    GamePanel gp ;

    public CollisionDetection(GamePanel gp ) {
        this.gp = gp;
    }

    //function to check if the entity is touching a tile with collision
    public void checkTile(Entity entity) {

        //variables for each side of solid area
        int entityLeftWorldx = entity.worldx + entity.solidArea.x;
        int entityRightWorldx = entity.worldx + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldy = entity.worldy + entity.solidArea.y;
        int entityBottomWorldy = entity.worldy + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldx/gp.tileSize;
        int entityRightCol = entityRightWorldx/gp.tileSize;
        int entityTopRow = entityTopWorldy/gp.tileSize;
        int entityBottomRow = entityBottomWorldy/gp.tileSize;

        int tileNum1 , tileNum2;

        //check entity direction
        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldy - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileArray[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileArray[entityRightCol][entityTopRow];

                //check collision (if the tile 1 or 2 has collision )
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true; // sets the player collision to true
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldy - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileArray[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileArray[entityRightCol][entityBottomRow];

                //check collision (if the tile 1 or 2 has collision )
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true; // sets the player collision to true
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldx - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileArray[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileArray[entityLeftCol][entityBottomRow];

                //check collision (if the tile 1 or 2 has collision )
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true; // sets the player collision to trueWWW
                }
                break;
            case "right":
                entityLeftCol = (entityRightWorldx - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileArray[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileArray[entityRightCol][entityBottomRow];

                //check collision (if the tile 1 or 2 has collision )
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true; // sets the player collision to true
                }
                break;
        }// end switch
    }// end check tile

    public int checkItem (Entity entity, boolean player) {

        int index = 999;

        for(int i = 0 ; i < gp.item_array.length ; i++) {

            if (gp.item_array[i] != null) {

                //get entity solid area position
                entity.solidArea.x = entity.worldx + entity.solidArea.x;
                entity.solidArea.y = entity.worldy + entity.solidArea.y;

                // get items solid area
                gp.item_array[i].solidArea.x = gp.item_array[i].worldx + gp.item_array[i].solidArea.x;
                gp.item_array[i].solidArea.y = gp.item_array[i].worldy + gp.item_array[i].solidArea.y;

                switch(entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(gp.item_array[i].solidArea)){

                            if(gp.item_array[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }//end if
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(gp.item_array[i].solidArea)){
                            if(gp.item_array[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }//end if
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(gp.item_array[i].solidArea)){
                            if(gp.item_array[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }//end if
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(gp.item_array[i].solidArea)){
                            if(gp.item_array[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }//end if
                        break;
                }//end switch

                //reset positions
                //entity
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;

                //item
                gp.item_array[i].solidArea.x = gp.item_array[i].solidAreaDefaultX;
                gp.item_array[i].solidArea.y = gp.item_array[i].solidAreaDefaultY;

            }//end if
        }// end for
        return index ;
    }// end check item

    //checks is in an enemy
    public boolean checkEntityCollision(Entity a, Entity b) {

        boolean hit = false;

        // move both solid areas to their actual world position
        a.solidArea.x = a.worldx + a.solidArea.x;
        a.solidArea.y = a.worldy + a.solidArea.y;

        b.solidArea.x = b.worldx + b.solidArea.x;
        b.solidArea.y = b.worldy + b.solidArea.y;

        if (a.solidArea.intersects(b.solidArea)) {
            hit = true;
        }

        // reset back to entity-relative offsets
        a.solidArea.x = a.solidAreaDefaultX;
        a.solidArea.y = a.solidAreaDefaultY;

        b.solidArea.x = b.solidAreaDefaultX;
        b.solidArea.y = b.solidAreaDefaultY;

        return hit;
    }
}// end class

