package ecs.components;

import ecs.GameObj;

import java.awt.Graphics2D;

public class Component {

    protected String name;
    protected GameObj gameObj;

    public Component(String name) {
        this.name = name;
    }

    public void update(double deltaTime) {

    }

    public void draw(Graphics2D g2) {

    }

    public void setGameObj(GameObj gameObj) {
        this.gameObj = gameObj;
    }

}
