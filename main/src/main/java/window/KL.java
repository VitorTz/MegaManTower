package window;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KL extends KeyAdapter implements KeyListener {

    private final static boolean[] keys = new boolean[128];

    private static boolean isValidKey(int key) {
        return (key >= 0 && key < KL.keys.length);
    }

    private static boolean isValidKey(KeyEvent e) {
        return KL.isValidKey(e.getKeyCode());
    }

    public static boolean isPressed(int key) {
        if (KL.isValidKey(key)) return KL.keys[key];
        return false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (KL.isValidKey(e)) KL.keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (KL.isValidKey(e)) KL.keys[e.getKeyCode()] = false;
    }

    @Override
    public String toString() {
        StringBuilder r = new StringBuilder();
        for (int i = 0; i < KL.keys.length; i++)
            if (KL.keys[i]) r.append(KeyEvent.getKeyText(i)).append(" ");
        return "KL{" +
                "keys=" + r +
                '}';
    }
}
