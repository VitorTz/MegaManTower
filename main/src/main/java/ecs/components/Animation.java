package ecs.components;

import ecs.GameObj;
import util.Alignment;
import util.AnimationKey;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.io.File;
import java.util.HashMap;


public class Animation extends Component {

    public final HashMap<String, SpriteAnimation> animations;
    protected AnimationKey key;

    public Animation(String animationFolder, Dimension spriteSize, Alignment alignment) {
        super("Animation");
        File[] spriteSheetFiles = new File(animationFolder).listFiles();
        this.animations = new HashMap<>();

        if (spriteSheetFiles == null) {
            System.out.println("Nenhuma SpriteSheet encontrada para a animação!");
            System.exit(-1);
        }

        for (File f : spriteSheetFiles) {
            String animationName = f.getName().split("\\.")[0];
            this.animations.put(animationName, new SpriteAnimation(f.getAbsolutePath(), spriteSize, alignment));
        }
    }

    public SpriteAnimation getCurrentAnimation() {
        return this.animations.get(this.key.toString());
    }

    public void setKey(AnimationKey key) {
        if (this.key == null || !key.isEmpty() && !this.key.equals(key)) {
            System.out.println(this.key);
            this.key = key;
            try {
                this.getCurrentAnimation().reset();
            } catch (Exception e) {
                System.out.println("key -> " + key);
            }
        }
    }

    public AnimationKey getKey() {
        return this.key;
    }

    public HashMap<String, SpriteAnimation> getAnimations() {
        return animations;
    }

    @Override
    public void setGameObj(GameObj gameObj) {
        super.setGameObj(gameObj);
        for (SpriteAnimation s : this.animations.values())
            s.setGameObj(gameObj);
    }

    @Override
    public void update(double deltaTime) {
        this.getCurrentAnimation().update(deltaTime);
    }

    @Override
    public void draw(Graphics2D g2) {
        this.getCurrentAnimation().draw(g2);
    }

}
