package util;

import java.util.concurrent.TimeUnit;

public class Time {

    private static long startedTime = System.nanoTime();
    private static double lastFramteRate = 0;

    public static double getElapsedTime() {
        return (System.nanoTime() - Time.startedTime) * 1E-9;
    }

    public static double getDeltaTime() {
        double time = Time.getElapsedTime();
        double deltaTime = time - Time.lastFramteRate;
        Time.lastFramteRate = time;
        return deltaTime;
    }

    public static void sleep(long timeInMillis) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(timeInMillis);
    }
}
