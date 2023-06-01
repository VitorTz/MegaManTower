package math;

public class Vector2f extends Vector2<Float> {

    public Vector2f(float x, float y) {
        super(x, y);
    }

    public Vector2f() {
        this(0f, 0f);
    }

    public double getMagnitude() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public void normalize() {
        double magnitude = this.getMagnitude();
        if (magnitude > 0) {
            this.x /= (float) magnitude;
            this.y /= (float) magnitude;
        }
    }

}
