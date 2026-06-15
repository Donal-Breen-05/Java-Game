package item;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Item_coin extends Item{

    public Item_coin() {
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
