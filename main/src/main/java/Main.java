import window.Window;

public class Main {

    public static void main(String[] args) {
        Window w = Window.getWindow();
        Thread t = new Thread(w);
        t.start();
    }
}
