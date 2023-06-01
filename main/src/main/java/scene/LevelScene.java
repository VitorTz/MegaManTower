package scene;

import ecs.GameObj;
import math.Transform;
import java.awt.Graphics2D;


public class LevelScene extends Scene {

    public LevelScene() {
        super("LevelScene");
    }

    @Override
    public void init() {
        GameObj player = GameObj.newGameObj("Player", new Transform(100f, 100f));
        this.addEntity(player);
    }

    @Override
    public void update(double deltaTime) {
        for (GameObj e : this.entities.values()) {
            e.update(deltaTime);
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        for (GameObj e : this.entities.values()) {
            e.draw(g2);
        }
    }
}
