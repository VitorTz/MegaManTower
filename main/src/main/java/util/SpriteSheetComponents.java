package util;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


record Pixel(int i, int j, int rgb) { }


class ImageComponent {

    private final List<Pixel> pixels = new ArrayList<>();
    private int height, width;
    private int minCol, maxCol, minLine, maxLine;

    public ImageComponent() {
        this.minCol = -1;
        this.maxCol = -1;
        this.minLine = -1;
        this.maxLine = -1;
    }

    public void addPixel(Pixel pixel) {
        this.pixels.add(pixel);
        if (pixel.j() < this.minCol || this.minCol == -1) this.minCol = pixel.j();
        if (pixel.j() > this.maxCol || this.maxCol == -1) this.maxCol = pixel.j();
        if (pixel.i() < this.minLine || this.minLine == -1) this.minLine = pixel.i();
        if (pixel.i() > this.maxLine || this.maxLine == -1) this.maxLine = pixel.i();
        this.width = this.maxCol -  this.minCol + 1;
        this.height = this.maxLine - this.minLine + 1;
    }

    public BufferedImage convertToImage(int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        // Centraliza a imagem
        int deltaY = height / 2 - this.height / 2;
        int deltaX = width / 2 - this.width / 2;
        for (Pixel p : this.pixels) {
            int i  = p.i() - this.minLine + deltaY;
            int j = p.j() - this.minCol + deltaX;
            if (i < 0) i = 0;
            if (j < 0) j = 0;
            image.setRGB(j, i, p.rgb());
        }
        return image;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }


    @Override
    public String toString() {
        return super.toString();
    }
}

public class SpriteSheetComponents {

    private final int width, height;
    private final boolean[][] mk;
    public int spriteWidth, spriteHeight;
    private final BufferedImage spriteImage;
    private final List<BufferedImage> images;
    private final int[][] pixelNeighborhood = new int[][]{
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0},
            {1, 1},
            {1, -1},
            {-1, 1},
            {-1, -1},
            {-2, 2},
            {-2, -2},
            {2, -2},
            {2, 2},
    };


    public SpriteSheetComponents(BufferedImage spriteSheet) {
        this.spriteImage = spriteSheet;
        this.width = spriteSheet.getWidth();
        this.height = spriteSheet.getHeight();
        this.spriteHeight = this.height;
        this.spriteWidth = -1;
        this.mk = new boolean[this.height][this.width];

        List<ImageComponent> components = new ArrayList<>();

        for (int j = 0; j < width; j++)
            for (int i = 0; i < height; i++)
                if (!mk[i][j] && this.spriteImage.getRGB(j, i) != 0) {
                    ImageComponent c = this.loadComponent(i, j);
                    components.add(c);
                    if (c.getWidth() > this.spriteWidth) this.spriteWidth = c.getWidth();
                }

        this.images = components.stream().map(c -> c.convertToImage(this.spriteWidth, this.spriteHeight)).toList();
    }

    private ImageComponent loadComponent(int i, int j) {
        LinkedList<Pixel> queue = new LinkedList<>();
        ImageComponent component = new ImageComponent();
        queue.add(new Pixel(i, j, this.spriteImage.getRGB(j, i)));
        this.mk[i][j] = true;

        while (!queue.isEmpty()) {
            Pixel p = queue.removeFirst();
            component.addPixel(p);

            for (int[] delta : this.pixelNeighborhood) {
                int x = p.i() + delta[0];
                int y = p.j() + delta[1];
                if (
                    x >= 0 && x < this.height &&
                    y >= 0 && y < this.width &&
                    !this.mk[x][y]
                ) {
                    int rgb = this.spriteImage.getRGB(y, x);
                    if (rgb != 0) {
                        queue.add(new Pixel(x, y, rgb));
                        this.mk[x][y] = true;
                    }
                }
            }
        }
        return component;
    }

    public int getSpriteHeight() {
        return spriteHeight;
    }

    public int getSpriteWidth() {
        return spriteWidth;
    }

    public List<BufferedImage> getComponents() {
        return this.images;
    }

}
