import ecs.component.Sprite;
import ecs.component.SpriteSheet;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class SpriteSheetTest {


    public static void main(String[] args) {
        String path = "main/src/main/resources/spriteSheets/player/running.png";
        SpriteSheet spRight = new SpriteSheet(path, "running_right");
        SpriteSheet spLeft = new SpriteSheet(path, "running_left");
        spLeft.mirrorSpriteSheet();

        String mainFolder = "main/src/test/java/out/";

        for (int i = 0; i < spRight.size(); i++) {
            Sprite spriteRight = spRight.get(i);
            Sprite spriteLeft = spLeft.get(i);
            String imagePathRight = mainFolder + spriteRight.getSpriteId().replace(" ", "_") + ".png";
            String imagePathLeft = mainFolder + spriteLeft.getSpriteId().replace(" ", "_") + ".png";
            File imageFileRight = new File(imagePathRight);
            File imageFileLeft = new File(imagePathLeft);
            try {
                ImageIO.write(spriteLeft.getImage(), "png", imageFileLeft);
                ImageIO.write(spriteRight.getImage(), "png", imageFileRight);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
