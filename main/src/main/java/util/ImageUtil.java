package util;


import javax.imageio.ImageIO;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;


record Pixel(int i, int j, int rgb){}


class ImageComponent {

    private final HashSet<Pixel> pixels = new HashSet<>();
    private int minCol, minLine, maxCol, maxLine;
    private int width, height;

    public ImageComponent() {
        this.minLine = -1;
        this.maxCol = -1;
        this.maxLine = -1;
        this.minCol = -1;
        this.width = 0;
        this.height = 0;
    }

    public void addPixel(Pixel p) {
        this.pixels.add(p);
        if (p.j() < this.minLine || this.minLine == -1) this.minLine = p.j();
        if (p.j() > this.maxLine || this.maxLine == -1) this.maxLine = p.j();
        if (p.i() < this.minCol || this.minCol == -1) this.minCol = p.i();
        if (p.i() > this.maxCol || this.maxCol == -1) this.maxCol = p.i();
        this.height = this.maxLine - this.minLine + 1;
        this.width = this.maxCol - this.minCol + 1;
    }

    public BufferedImage convertToImage(Dimension size, Alignment alignment) {
        BufferedImage image = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
        Pair<Integer, Integer> delta = this.getDeltas(size, alignment);
        for (Pixel p : this.pixels) {
            int xPos = p.i() - this.minCol + delta.first;
            int yPos = p.j() - this.minLine + delta.second;
            image.setRGB(xPos, yPos, p.rgb());
        }
        return image;
    }

    public Pair<Integer, Integer> getDeltas(Dimension size, Alignment alignment) {
        int deltaX = 0, deltaY = 0;
        switch (alignment) {
            case CENTER -> {
                deltaX = size.width / 2 - this.width / 2;
                deltaY = size.height / 2 - this.height / 2;
            } case TOP -> {
                deltaX = size.width / 2 - this.width / 2;
            } case LEFT -> {
                deltaY = size.height / 2 - this.height / 2;
            } case RIGHT -> {
                deltaX = size.width - this.width;
                deltaY = size.height / 2 - this.height / 2;
            } case BOTTOM -> {
                deltaX = size.width / 2 - this.width / 2;
                deltaY = size.height - this.height;
            } case RIGHTTOP -> {
                deltaX = size.width - this.width;
            } case LEFTBOTTOM -> {
                deltaY = size.height - this.height;
            } case RIGHTBOTTOM -> {
                deltaX = size.width - this.width;
                deltaY = size.height - this.height;
            }
        }
        return new Pair<>(deltaX, deltaY);
    }

}

public class ImageUtil {

    private static final int[][] pixelNeighborhood = new int[][]{
        {0, 1},
        {0, -1},
        {1, 0},
        {-1, 0},
        {1, 1},
        {1, -1},
        {-1, 1},
        {-1, -1},
        {1, 3},
        {-1, -3},
        {-1, 3},
        {-1, -3},
    };

    public static List<BufferedImage> loadComponents(BufferedImage image, Dimension size, Alignment alignment) {
        List<ImageComponent> components = new ArrayList<>();
        boolean[][] mk = new boolean[image.getHeight()][image.getWidth()];

        for (int j = 0; j < image.getWidth(); j++)
            for (int i = 0; i < image.getHeight(); i++)
                if (!mk[i][j] && image.getRGB(j, i) != 0)
                    components.add(loadComponent(i, j, mk, image));

        return components.stream().map(c -> c.convertToImage(size, alignment)).toList();
    }

    private static ImageComponent loadComponent(int i, int j, boolean[][] mk, BufferedImage parentImage) {
        final LinkedList<Pixel> queue = new LinkedList<>();
        final ImageComponent component = new ImageComponent();
        final int maxWidth = parentImage.getWidth();
        final int maxHeight = parentImage.getHeight();

        Pixel p = new Pixel(j, i, parentImage.getRGB(j, i));
        queue.add(p);
        component.addPixel(p);
        mk[i][j] = true;

        while (!queue.isEmpty()) {
            Pixel pixel = queue.removeFirst();
            component.addPixel(pixel);
            for (int[] neighbour : ImageUtil.pixelNeighborhood) {
                int x = pixel.i() + neighbour[0];
                int y = pixel.j() + neighbour[1];
                if (x >= 0 && x < maxWidth && y >= 0 && y < maxHeight && !mk[y][x]) {
                    int rgb = parentImage.getRGB(x, y);
                    if (rgb != 0) {
                        Pixel newPixel =  new Pixel(x, y, rgb);
                        component.addPixel(newPixel);
                        queue.add(newPixel);
                        mk[y][x] = true;
                    }
                }
            }
        }

        return component;
    }

    public static void mirrorImage(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width / 2; x++) {
                int leftPixel = image.getRGB(x, y);
                int rightPixel = image.getRGB(width - x - 1, y);
                image.setRGB(x, y, rightPixel);
                image.setRGB(width - x - 1, y, leftPixel);
            }
        }
    }

    public static BufferedImage loadImage(String imagePath) {
        return loadImage(new File(imagePath));
    }

    public static BufferedImage loadImage(File imageFile) {
        try {
            return ImageIO.read(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return null;
    }

}
