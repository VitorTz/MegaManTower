package ecs.component;

import ecs.entity.Entity;

import java.awt.Graphics2D;

public class Component<T> {

    protected String name;
    protected Entity entity;

    public Component(String name) {
        this.name = name;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public void update(double deltaTime) {
        return;
    }

    public void draw(Graphics2D g2) {
        return;
    }

}
