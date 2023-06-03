package ecs.components;

import math.Transform;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Sprite extends Component {

    private static final HashMap<String, Sprite> sprites = new HashMap<>();

    public final String spriteId;
    private BufferedImage image;

    public static Sprite getSprite(File spriteFile) {
        String spriteName = spriteFile.getAbsolutePath();
        if (!Sprite.sprites.containsKey(spriteName))
            Sprite.sprites.put(spriteName, new Sprite(spriteFile));
        return Sprite.sprites.get(spriteName);
    }

    public static Sprite getSprite(String spriteName, BufferedImage image) {
        if (!Sprite.sprites.containsKey(spriteName))
            Sprite.sprites.put(spriteName, new Sprite(spriteName, image));
        return Sprite.sprites.get(spriteName);
    }

    private Sprite(File spriteFile) {
        super("Sprite");
        this.spriteId = spriteFile.getName().split("\\.")[0];
        try {
            this.image = ImageIO.read(spriteFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private Sprite(String spriteName, BufferedImage image) {
        super("Sprite");
        this.spriteId = spriteName;
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    @Override
    public void draw(Graphics2D g2) {
        Transform transform = gameObj.transform;
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.setToIdentity();
        affineTransform.translate(transform.position.x, transform.position.y);
        affineTransform.rotate(
            Math.toRadians(transform.rotation),
    image.getWidth() * transform.scale.x / 2,
    image.getHeight() * transform.scale.y / 2
        );
        affineTransform.scale(transform.scale.x, transform.scale.y);
        g2.drawImage(image, affineTransform, null);
    }

}
