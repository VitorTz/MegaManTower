package ecs;

import ecs.components.Component;
import math.Transform;

import java.awt.Graphics2D;
import java.util.HashMap;

public class GameObj {

    private static int id = 0;

    private final HashMap<Class<?>, Component> components;
    public final Transform transform;
    public final String name;

    public static GameObj newGameObj(String name, Transform transform) {
        return new GameObj(name, transform);
    }

    private GameObj(String name, Transform transform) {
        this.name = name + "_" + GameObj.id++;
        this.transform = transform;
        this.components = new HashMap<>();
    }

    public void addComponent(Component component) {
        this.components.put(component.getClass(), component);
        component.setGameObj(this);
    }

    public <T extends Component> T getComponent(Class<T> componentClass) {
        return componentClass.cast(this.components.get(componentClass));
    }

    public void update(double deltaTime) {
        for (Component c : this.components.values())
            c.update(deltaTime);
    }

    public void draw(Graphics2D g2) {
        for (Component c : this.components.values())
            c.draw(g2);
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "GameObj{" +
                "id=" + id +
                ", components=" + components +
                ", transform=" + transform +
                ", name='" + name + '\'' +
                '}';
    }
}
