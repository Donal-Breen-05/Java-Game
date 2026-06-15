package item;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Item_sheild extends Item {

    public Item_sheild(){
        name = "sheild" ;

        //image path
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/items/sheild.png")));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}