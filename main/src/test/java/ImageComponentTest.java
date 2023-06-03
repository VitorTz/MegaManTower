import util.Alignment;
import util.ImageUtil;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

public class ImageComponentTest {

    public static void main(String[] args) {
        BufferedImage image = ImageUtil.loadImage("main/src/test/java/input/teste.png");
        Dimension size = new Dimension(64, 64);
        List<BufferedImage> images = ImageUtil.loadComponents(image, size, Alignment.BOTTOM);
        String outputFolder = "main/src/test/java/out";

        for (int i = 0; i < images.size(); i++) {
            File outputFile = new File(outputFolder + "/" + i + ".png");
            try {
                ImageIO.write(images.get(i), "png", outputFile);
            } catch ( IOException e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }

    }

}
