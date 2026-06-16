package item;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Item_heart extends Item {

    GamePanel gp ;

    public Item_heart(GamePanel gp){
        name = "heart" ;

        //image path
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/ui/health_full.png")));
            image2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/ui/health_half.png")));
            image3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/ui/health_empty.png")));

        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}