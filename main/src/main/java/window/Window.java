package window;

import scene.LevelScene;
import scene.Scene;
import util.Constants;
import util.Time;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.*;
import java.io.IOException;

public class Window extends JFrame implements Runnable {


    private static Window window = null;
    private Scene currentScene;
    private boolean isRunning;
    private Image doubleBufferImage = null;

    public static Window getWindow() {
        if (Window.window == null) {
            Window.window = new Window();
            Window.window.init();
        }
        return Window.window;
    }

    private Window() {
        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.setTitle(Constants.SCREEN_TITLE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(new KL());
        this.setVisible(true);
    }

    private void init() {
        this.isRunning = true;
        try {
            this.setIconImage(ImageIO.read(Constants.SCREEN_ICON));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Window.changeScene(0);
    }

    private void clear(Graphics2D g2) {
        g2.setColor(Constants.SCREEN_BG_COLOR);
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    private void update(double deltaTime) {
        this.currentScene.update(deltaTime);
        this.draw(this.getGraphics());
    }

    private void renderOffScreen(Graphics2D g2) {
        this.clear(g2);
        this.currentScene.draw(g2);
    }

    private void draw(Graphics g) {
        if (this.doubleBufferImage == null) {
            this.doubleBufferImage = this.createImage(this.getWidth(), this.getHeight());
        }
        this.renderOffScreen((Graphics2D) this.doubleBufferImage.getGraphics());
        g.drawImage(this.doubleBufferImage, 0, 0, this.getWidth(), this.getHeight(), null);
    }

    public static void changeScene(int sceneId) {
        if (sceneId == 0) {
            Window.window.currentScene = new LevelScene();
        } else {
            System.out.println("Nenhuma scena com o id " + sceneId + " foi encotrada!");
            System.exit(-1);
        }
    }

    @Override
    public void run() {
        while (this.isRunning) {
            try {
                double deltaTime = Time.getDeltaTime();
                this.update(deltaTime);
                Time.sleep(Constants.FPS_IN_MILLS);
                System.out.println();
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }
    }
}
