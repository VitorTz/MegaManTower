package util;

import ecs.components.Sprite;

import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SpriteSheet {

    private List<Sprite> sprites;

    public SpriteSheet(File spriteSheetFile, Dimension spriteSize, Alignment alignment) {
        this.sprites = new ArrayList<>();
    }

    public Sprite get(int spriteIndex) {
        return this.sprites.get(spriteIndex);
    }

    public List<Sprite> sprites() {
        return this.sprites;
    }


}
