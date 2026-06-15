package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;


import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
    GamePanel gp ;
    public Tile[] tile;
    public int mapTileArray[][];

    //constructor??
    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        mapTileArray = new int[gp.maxWorldCol][gp.maxWorldRow];


        getTileImage();
        loadMapArray("/map/map.txt"); // loads txt file of map to draw to screen
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass.png")));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass2.png")));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grey-bricks.png")));
            tile[2].collision = true; // makes a tile have collision

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wood.png")));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wood.png")));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wood.png")));

        }catch(IOException e){
            e.printStackTrace();
        }
    }//end getTileImage()

    public void draw(Graphics2D g2) {

        int col = 0;
        int row = 0 ;

        int worldCol = 0;
        int worldRow = 0;

        //loop to draw tiles (bacground
        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileArray[worldCol][worldRow];


            int worldx = worldCol * gp.tileSize;
            int worldy = worldRow * gp.tileSize;
            int screenX = worldx - gp.player.worldx + gp.player.screenx;
            int screenY = worldy - gp.player.worldy + gp.player.screeny;


            // only render in tiles near player
            if (worldx + gp.tileSize > gp.player.worldx - gp.player.screenx &&
                worldx - gp.tileSize * 2 < gp.player.worldx + gp.player.screenx &&
                worldy + gp.tileSize > gp.player.worldy - gp.player.screeny &&
                worldy - gp.tileSize * 2 < gp.player.worldy + gp.player.screeny )  {

                g2.drawImage(tile[tileNum].image , screenX,screenY , gp.tileSize , gp.tileSize , null);
            }// end if

            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0 ;
                worldRow++;

            }//end if
        }//end while

    }//end draw()


    public void loadMapArray(String filepath) {
        try {
            InputStream is = getClass().getResourceAsStream(filepath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is)); //fancy file reading


            int col = 0;
            int row = 0;

            //loop to read all of the text file
            while(col < gp.maxWorldCol && row < gp.maxWorldRow) {

                String line = br.readLine();

                while(col < gp.maxWorldCol) {

                    String numbers[] =line.split(" "); // removes spaces

                    int num = Integer.parseInt(numbers[col]);

                    mapTileArray[col][row] = num;
                    col++;

                }
                if(col ==gp.maxWorldCol) {
                    col = 0 ;
                    row++;
                }
            }//end while
            br.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}// end class




/*
 //draws a single tile for background image
		g2.drawImage(tile[0].image , 0,0 , gp.tileSize , gp.tileSize , null);

		*/
