package math;

public class Vector2<T> {

    public T x, y;

    public Vector2(T x, T y) {
        this.x = x;
        this.y = y;
    }

    public Vector2<T> copy() {
        return new Vector2<>(this.x, this.y);
    }

    public void set(Vector2<T> vec) {
        this.x = vec.x;
        this.y = vec.y;
    }

    public void set(T x, T y) {
        this.x = x;
        this.y = y;
    }

}
