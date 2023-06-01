package util;

import java.awt.image.BufferedImage;

public class ImageUtil {

    public static BufferedImage mirrorImage(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage mirroredImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int sourcePixel = image.getRGB(x, y);
                int targetX = width - x - 1; // Espelha a coordenada X
                mirroredImage.setRGB(targetX, y, sourcePixel);
            }
        }

        return mirroredImage;
    }

}
