package scene;

import ecs.component.Sprite;
import ecs.entity.Entity;
import math.Transform;

import java.awt.Graphics2D;


public class LevelScene extends Scene {

    public LevelScene() {
        super("LevelScene");
    }

    @Override
    public void init() {
        Entity player = Entity.newEntity("Player", new Transform(100f, 100f));
        Sprite sp = Sprite.getSprite("main/src/main/resources/raw/75842.png");
        player.addComponent(sp);
        this.addEntity(player);
    }

    @Override
    public void update(double deltaTime) {
        for (Entity e : this.entities.values()) {
            e.update(deltaTime);
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        for (Entity e : this.entities.values()) {
            e.draw(g2);
        }
    }
}
