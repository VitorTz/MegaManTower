package ecs.components;

import util.Constants;
import window.KL;

import java.awt.event.KeyEvent;

public class PlayerMovement extends Movement {

    public PlayerMovement() {
        super(Constants.PLAYER_SPEED);
    }

    private void handleUserInput() {
        this.direction.x = 0f;
        this.direction.y = 0f;

        if (KL.isPressed(KeyEvent.VK_RIGHT))
            this.direction.x = 1f;
        else if (KL.isPressed(KeyEvent.VK_LEFT))
            this.direction.x = -1f;

        if (KL.isPressed(KeyEvent.VK_SPACE))
            this.direction.y = -1f;
    }

    @Override
    public void update(double deltaTime) {
        this.handleUserInput();
        super.update(deltaTime);
    }
}
