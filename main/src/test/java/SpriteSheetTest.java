import ecs.component.Sprite;
import ecs.component.SpriteSheet;

import javax.imageio.ImageIO;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

public class SpriteSheetTest {

    private static SpriteSheet playerRunningLeft;
    private static SpriteSheet playerRunningRight;
    private static SpriteSheet playerTeleport;
    private static final String playerRunningFilePath = "main/src/main/resources/spriteSheets/player/right.png";
    private static final String playerTeleportFilePath = "main/src/main/resources/spriteSheets/player/teleport.png";
    private static final Dimension playerSize = new Dimension(64, 64);
    private static final String outputFolder = "main/src/test/java/out/";

    private static void setUp() {
        playerRunningLeft = new SpriteSheet(playerRunningFilePath, "player_left", playerSize);
        playerRunningRight = new SpriteSheet(playerRunningFilePath, "player_right", playerSize);
        playerTeleport = new SpriteSheet(playerTeleportFilePath, "player_teleport", playerSize);
        playerRunningLeft.mirrorSpriteSheet();
    }

    private static void saveImages(SpriteSheet spriteSheet) {
        for (Sprite s : spriteSheet.getSprites()) {
            String spriteFileName = outputFolder + s.getSpriteId() + ".png";
            File imageFile = new File(spriteFileName);
            try {
                ImageIO.write(s.getImage(), "png", imageFile);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }
    }


    public static void main(String[] args) {
        setUp();
        saveImages(playerRunningLeft);
        saveImages(playerRunningRight);
        saveImages(playerTeleport);
    }

}
