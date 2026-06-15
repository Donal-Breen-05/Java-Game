package item;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Item_heart extends Item {

    public Item_heart(){
        name = "heart" ;

        //image path
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/items/heart.png")));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}