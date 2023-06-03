package util;

import ecs.components.Sprite;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SpriteSheet {

    private final List<Sprite> sprites = new ArrayList<>();
    private final String spriteSheetName;

    public SpriteSheet(File spriteSheetFile, Dimension dimension) {
        this(spriteSheetFile, dimension, Alignment.BOTTOM);
    }

    public SpriteSheet(File spriteSheetFile, Dimension spriteSize, Alignment alignment) {
        this.spriteSheetName = spriteSheetFile.getName().split("\\.")[0];
        List<BufferedImage> images = ImageUtil.loadComponents(ImageUtil.loadImage(spriteSheetFile), spriteSize, alignment);
        for (int i = 0; i < images.size(); i++)
            this.sprites.add(Sprite.getSprite(this.spriteSheetName + "_" + i, images.get(i)));
    }

    public int size() {
        return this.sprites.size();
    }

    public Sprite get(int spriteIndex) {
        return this.sprites.get(spriteIndex);
    }

    public List<Sprite> sprites() {
        return this.sprites;
    }

    public String getSpriteSheetName() {
        return spriteSheetName;
    }

    public void mirrorSpriteSheet() {
        this.sprites.forEach( s -> ImageUtil.mirrorImage(s.getImage()));
    }

}
