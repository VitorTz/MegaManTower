package math;

public class Transform {

    public final Vector2f position;
    public final Vector2f scale;
    public double rotation;

    public Transform() {
        this.position = new Vector2f();
        this.scale = new Vector2f(1f, 1f);
        this.rotation = 0;
    }

    public Transform(float posX, float posY) {
        this();
        this.position.set(posX, posY);
    }

    public Transform(Vector2f position, Vector2f scale, double rotation) {
        this.position = position;
        this.scale = scale;
        this.rotation = rotation;
    }

}
