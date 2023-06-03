package ecs.components;

import ecs.GameObj;

import java.awt.Graphics2D;

public class Player extends Component {

    private final PlayerMovement movement;
    private final PlayerAnimation animation;

    public Player() {
        super("Player");
        this.movement = new PlayerMovement();
        this.animation = new PlayerAnimation();
    }

    @Override
    public void setGameObj(GameObj gameObj) {
        super.setGameObj(gameObj);
        gameObj.addComponent(this.movement);
        gameObj.addComponent(this.animation);
    }

    @Override
    public void update(double deltaTime) {
        this.movement.update(deltaTime);
        this.animation.update(deltaTime);
    }

    @Override
    public void draw(Graphics2D g2) {
        this.animation.draw(g2);
    }
}
