package util;


import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;


public class ImageUtil {

    public static List<BufferedImage> loadComponents(BufferedImage image, Dimension componentSize) {
        List<BufferedImage> components = new ArrayList<>();
        boolean[][] mk = new boolean[image.getHeight()][image.getWidth()];

        for (int j = 0; j < image.getWidth(); j++)
            for (int i = 0; i < image.getHeight(); i++)
                if (!mk[i][j] && image.getRGB(i, j) == 0)
                    components.add(loadComponent(i, j, componentSize, mk));

        return components;
    }

    private static BufferedImage loadComponent(int i, int j, Dimension componentSize, boolean[][] mk) {
        BufferedImage image = new BufferedImage(componentSize.width, componentSize.height, BufferedImage.TYPE_INT_ARGB);
        
        return image;
    }

}
