package scene;

import ecs.entity.Entity;

import java.awt.Graphics2D;
import java.util.HashMap;

public abstract class Scene {

    protected String name;
    protected HashMap<Integer, Entity> entities = new HashMap<>();

    public Scene(String name) {
        this.name = name;
        this.init();
    }

    protected void addEntity(Entity entity) {
        this.entities.put(entity.getId(), entity);
    }

    protected void removeEntity(Entity entity) {
        this.entities.remove(entity.getId());
    }

    public abstract void update(double deltaTime);
    public abstract void draw(Graphics2D g2);
    public abstract void init();

}
