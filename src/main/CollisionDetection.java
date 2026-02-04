package main;

import entity.Entity;

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
        switch (entity.direction){
            case "up":
                entityTopRow = (entityTopWorldy - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileArray[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileArray[entityRightCol][entityTopRow];

                //check collision (if the tile 1 or 2 has collision )
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true; // sets the player collision to true
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldy - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileArray[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileArray[entityRightCol][entityBottomRow];

                //check collision (if the tile 1 or 2 has collision )
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true; // sets the player collision to true
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldx - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileArray[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileArray[entityLeftCol][entityBottomRow];

                //check collision (if the tile 1 or 2 has collision )
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true; // sets the player collision to trueWWW
                }
                break;
            case "right":
                entityLeftCol = (entityRightWorldx - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileArray[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileArray[entityRightCol][entityBottomRow];

                //check collision (if the tile 1 or 2 has collision )
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true; // sets the player collision to true
                }
                break;
    }

}
}
