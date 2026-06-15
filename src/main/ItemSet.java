package main;

import item.Item_coin;

public class ItemSet {
    GamePanel gp;

    public ItemSet(GamePanel gp) {
        this.gp = gp;
    }

    public void setItem() {

        gp.item_array[0] = new Item_coin();
        gp.item_array[0].worldx = 2 * gp.tileSize;
        gp.item_array[0].worldy = 2 * gp.tileSize;

        gp.item_array[1] = new Item_coin();
        gp.item_array[1].worldx = 4 * gp.tileSize;
        gp.item_array[1].worldy = 5 * gp.tileSize;
    }
}
