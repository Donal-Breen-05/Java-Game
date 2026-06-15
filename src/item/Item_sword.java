package item;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Item_sword extends Item {

    public Item_sword(){
        name = "sword" ;

        //image path 
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/items/sword.png")));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
