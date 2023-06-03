package ecs.components;

import math.Vector2f;
import util.MovementEnum;

public class Movement extends Component {

    protected final Vector2f direction;
    protected float speed;
    protected MovementEnum movementHorizontal;
    protected MovementEnum movementVertical;

    public Movement(Vector2f direction, float speed) {
        super("Movement");
        this.direction = direction;
        this.speed = speed;
    }

    public Movement(float speed) {
        this(new Vector2f(), speed);
    }

    @Override
    public void update(double deltaTime) {
        if (this.direction.getMagnitude() > 1) this.direction.normalize();
        this.gameObj.transform.position.x += (float) (this.direction.x * deltaTime * this.speed);
        this.gameObj.transform.position.y += (float) (this.direction.y * deltaTime * this.speed);
        if (this.direction.x > 0) this.movementHorizontal = MovementEnum.RIGHT;
        if (this.direction.x < 0) this.movementHorizontal = MovementEnum.LEFT;
        if (this.direction.y < 0) this.movementVertical = MovementEnum.UP;
        if (this.direction.y > 0) this.movementVertical = MovementEnum.DOWN;
        if (this.direction.y == 0) this.movementVertical = null;
    }

    public boolean isMoving() {
        return this.direction.getMagnitude() != 0;
    }

    public Vector2f getDirection() {
        return direction;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

}
