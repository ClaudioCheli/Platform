package com.example.claudio.platform.manager;

import android.os.SystemClock;

import com.example.claudio.platform.toolBox.Vector2f;

/**
 * Created by clasb on 28/05/2016.
 */
public class DisplayManager {
    // Display dimension
    /* _                                 _
     *| (-1.334,1.0)          (1.334,1.0) |
     *
     *
     *
     *
     *
     *|_(-1.334,-1.0)        (1.334,-1.0)_|
     */

    public static final Vector2f BOTTOM_RIGHT = new Vector2f( 1.334f, 1.0f);
    public static final Vector2f TOP_LEFT     = new Vector2f(-1.334f, 1.0f);

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
