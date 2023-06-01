package ecs.component;

import java.awt.*;

public class Player extends Component<Player> {

    private SpriteSheet sp;

    public Player() {
        super("Player");
    }

    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
    }
}
