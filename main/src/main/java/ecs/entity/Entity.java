package ecs.entity;

import ecs.component.Component;
import math.Transform;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Objects;

public class Entity {

    public final Transform transform;
    private final String name;
    private final int id;
    private final HashMap<Class<?>, Component<?>> component = new HashMap<>();
    private static int entityId = 0;

    public static Entity newEntity(String name, Transform transform) {
        return new Entity(name, transform, Entity.entityId++);
    }

    private Entity(String name, Transform transform, int id) {
        this.name = name;
        this.transform = transform;
        this.id = id;
    }

    public void addComponent(Component<?> component) {
        component.setEntity(this);
        this.component.put(component.getClass(), component);
    }

    public <T extends Component<?>> T getComponent(Class<T> componentClass) {
        return componentClass.cast(this.component.get(componentClass));
    }

    public void update(double deltaTime) {
        for (Component<?> c : this.component.values()) {
            c.update(deltaTime);
        }
    }

    public void draw(Graphics2D g2) {
        for (Component<?> c : this.component.values()) {
            c.draw(g2);
        }
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "name='" + name + '\'' +
                ", component=" + component +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entity entity = (Entity) o;

        if (id != entity.id) return false;
        return Objects.equals(name, entity.name);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + id;
        return result;
    }
}
