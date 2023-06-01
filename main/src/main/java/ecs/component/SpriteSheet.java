package ecs.component;

import util.ImageUtil;
import util.SpriteSheetComponents;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpriteSheet extends Component<SpriteSheet> {

    private final List<Sprite> sprites = new ArrayList<>();
    private final int spriteWidth, spriteHeight;

    public SpriteSheet(String spriteSheetPath, String nameId) {
        super(nameId);

        Sprite sp = Sprite.getSprite(spriteSheetPath);
        SpriteSheetComponents spriteComponents = new SpriteSheetComponents(sp.getImage());
        this.spriteWidth = spriteComponents.getSpriteWidth();
        this.spriteHeight = spriteComponents.getSpriteHeight();

        for (BufferedImage img : spriteComponents.getComponents()) {
            String spriteName = this.name + " : " + this.sprites.size();
            this.sprites.add(Sprite.getSprite(spriteName, img));
        }
    }

    public void mirrorSpriteSheet() {
        for (Sprite sprite : this.sprites)
            sprite.setImage(ImageUtil.mirrorImage(sprite.getImage()));
        Collections.reverse(this.sprites);
    }

    public int getSpriteWidth() {
        return spriteWidth;
    }

    public int getSpriteHeight() {
        return spriteHeight;
    }

    public Sprite get(int index) {
        return this.sprites.get(index);
    }

    public int size() {
        return this.sprites.size();
    }

}
