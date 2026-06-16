package main;

import item.Item_coin;
import item.Item_sword;

public class ItemSet {
    GamePanel gp;

    public ItemSet(GamePanel gp) {
        this.gp = gp;
    }

    public void setItem() {

        //1
        gp.item_array[0] = new Item_coin();
        gp.item_array[0].worldx = 2 * gp.tileSize;
        gp.item_array[0].worldy = 2 * gp.tileSize;

        //2
        gp.item_array[1] = new Item_sword();
        gp.item_array[1].worldx = 4 * gp.tileSize;
        gp.item_array[1].worldy = 5 * gp.tileSize;
        /*
        //3
        gp.item_array[1] = new Item_coin();
        gp.item_array[1].worldx = 4 * gp.tileSize;
        gp.item_array[1].worldy = 5 * gp.tileSize;

        //4
        gp.item_array[1] = new Item_coin();
        gp.item_array[1].worldx = 4 * gp.tileSize;
        gp.item_array[1].worldy = 5 * gp.tileSize;

        //5
        gp.item_array[1] = new Item_coin();
        gp.item_array[1].worldx = 4 * gp.tileSize;
        gp.item_array[1].worldy = 5 * gp.tileSize;

        //6
        gp.item_array[1] = new Item_coin();
        gp.item_array[1].worldx = 4 * gp.tileSize;
        gp.item_array[1].worldy = 5 * gp.tileSize;

        //7
        gp.item_array[1] = new Item_coin();
        gp.item_array[1].worldx = 4 * gp.tileSize;
        gp.item_array[1].worldy = 5 * gp.tileSize;

        // 8
        gp.item_array[1] = new Item_coin();
        gp.item_array[1].worldx = 4 * gp.tileSize;
        gp.item_array[1].worldy = 5 * gp.tileSize;

        */
    }
}
