package item;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Item_coin extends Item{

    GamePanel gp;
    public Item_coin(GamePanel gp) {
        name = "coin";

        //image path
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/items/coin.png")));
        }catch(IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
