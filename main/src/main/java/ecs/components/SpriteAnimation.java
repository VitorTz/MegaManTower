package ecs.components;

import ecs.GameObj;
import util.Alignment;
import util.SpriteSheet;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.io.File;

public class SpriteAnimation extends Component {

    private final SpriteSheet spriteSheet;
    private double currentIndex = 0;
    private double resetIndex = 0;
    private float animationUpdateRate = 7f;
    private int cicles;

    public SpriteAnimation(String spriteSheetName, Dimension spriteSize, Alignment alignment, Boolean mirrorSpriteSheet) {
        this(spriteSheetName, spriteSize, alignment);
        this.cicles = 0;
        if (mirrorSpriteSheet) this.spriteSheet.mirrorSpriteSheet();
    }

    public SpriteAnimation(String spriteSheetName, Dimension spriteSize, Alignment alignment) {
        super("SpriteAnimation");
        this.spriteSheet = new SpriteSheet(new File(spriteSheetName), spriteSize, alignment);
    }

    public SpriteAnimation(String spriteSheetName, Dimension spriteSize) {
        this(spriteSheetName, spriteSize, Alignment.BOTTOM);
    }

    public void reset() {
        this.currentIndex = this.resetIndex;
    }

    public void setCurrentSpriteIndex(double currentIndex) {
        this.currentIndex = currentIndex;
        if (this.currentIndex >= this.spriteSheet.size()) {
            this.cicles++;
            this.reset();
        }
    }

    public SpriteSheet getSpriteSheet() {
        return spriteSheet;
    }

    public int getCicles() {
        return cicles;
    }

    public void setResetIndex(double resetIndex) {
        this.resetIndex = resetIndex;
    }

    public float getAnimationUpdateRate() {
        return animationUpdateRate;
    }

    public void setAnimationUpdateRate(float animationUpdateRate) {
        this.animationUpdateRate = animationUpdateRate;
    }

    @Override
    public void update(double deltaTime) {
        this.setCurrentSpriteIndex(this.currentIndex + deltaTime * this.animationUpdateRate);
    }

    @Override
    public void draw(Graphics2D g2) {
        this.spriteSheet.get((int) this.currentIndex).draw(g2);
    }

    @Override
    public void setGameObj(GameObj gameObj) {
        super.setGameObj(gameObj);
        this.spriteSheet.sprites().forEach(sprite -> sprite.setGameObj(gameObj));
    }
}
