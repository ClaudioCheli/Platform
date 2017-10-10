package com.example.claudio.platform.time;

import android.os.SystemClock;

/**
 * Created by Claudio on 29/09/2017.
 */

public class Time {

    private static long  lastFrameTime;
    private static float delta;

    private static long lastFPS = 0;
    private static int tmpFPS = 0;
    private static int fps = 0;

    public static void start(){
        lastFrameTime = getCurrentTime();
        lastFPS = getCurrentTime();
    }

    public static void update(){
        long currentFrameTime   = getCurrentTime();
        delta                   = (currentFrameTime - lastFrameTime);
        lastFrameTime           = currentFrameTime;
        if(getCurrentTime() - lastFPS > 1000) {
            fps = tmpFPS;
            tmpFPS = 0;
            lastFPS += 1000;
        }
        tmpFPS++;
    }

    /**
     * get current time in milliseconds
     * @return time in milliseconds, long
     */
    public static long getCurrentTime(){
        return SystemClock.uptimeMillis();
    }

    /**
     * return frame time in milliseconds
     * @return frame time, float
     */
    public static float getFrameTime(){return delta;}

    /**
     * return frame time in seconds
     * @return frame time in seconds, float
     */
    public static float getFrameTimeSeconds(){
        return delta/1000f;
    }

    public static int getFps() {
        return fps;
    }

}
