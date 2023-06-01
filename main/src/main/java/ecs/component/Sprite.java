package ecs.component;

import math.Transform;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.io.File;
import java.util.Objects;

public class Sprite extends Component<Sprite> {

    private static final HashMap<String, Sprite> sprites = new HashMap<>();

    private final String spriteId;
    private BufferedImage image;
    private Dimension dimension;

    public static Sprite getSprite(File imageFile) {
        String spriteId = imageFile.getName();
        System.out.println(imageFile);
        if (!Sprite.sprites.containsKey(spriteId)) {
            Sprite.sprites.put(spriteId, new Sprite(spriteId, imageFile));
        }
        return Sprite.sprites.get(spriteId);
    }

    public static Sprite getSprite(String imagePath) {
        return Sprite.getSprite(new File(imagePath));
    }

    public static Sprite getSpriteById(String id) {
        return Sprite.sprites.get(id);
    }

    public static Sprite getSprite(String spriteId, BufferedImage image) {
        if (!Sprite.sprites.containsKey(spriteId)) {
            Sprite.sprites.put(spriteId, new Sprite(spriteId, image));
        }
        return Sprite.sprites.get(spriteId);
    }

    public static void removeSprite(String spriteId) {
        Sprite.sprites.remove(spriteId);
    }

    public static void removeSprite(Sprite sprite) {
        Sprite.sprites.remove(sprite.getSpriteId());
    }

    private Sprite(String spriteId, BufferedImage image) {
        super("Sprite");
        this.spriteId = spriteId;
        this.image = image;
        this.dimension = new Dimension(this.image.getWidth(), this.image.getHeight());
    }

    private Sprite(String spriteId, File spriteFile) {
        super("Sprite");
        this.spriteId = spriteId;
        try {
            this.image = ImageIO.read(spriteFile);
            this.dimension = new Dimension(this.image.getWidth(), this.image.getHeight());
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        Transform transform = this.entity.transform;
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.setToIdentity();
        affineTransform.translate(transform.position.x, transform.position.y);
        affineTransform.rotate(
            Math.toRadians(transform.rotation),
            this.getWidth() * transform.scale.x / 2,
            this.getHeight() * transform.scale.y / 2
        );
        affineTransform.scale(transform.scale.x, transform.scale.y);
        g2.drawImage(this.image, affineTransform, null);
    }

    public Dimension getDimension() {
        return this.dimension;
    }

    public int getWidth() {
        return this.dimension.width;
    }

    public int getHeight() {
        return this.dimension.height;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getSpriteId() {
        return spriteId;
    }

    @Override
    public String toString() {
        return "Sprite{" +
                "spriteId='" + spriteId + '\'' +
                ", image=" + image +
                ", dimension=" + dimension +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sprite sprite = (Sprite) o;

        return Objects.equals(spriteId, sprite.spriteId);
    }

    @Override
    public int hashCode() {
        return spriteId != null ? spriteId.hashCode() : 0;
    }
}

