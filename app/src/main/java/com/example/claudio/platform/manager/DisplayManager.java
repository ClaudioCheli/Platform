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

    public static void start(){
        lastFrameTime= getCurrentTime();
    }

    public static void update(){
        long currentFrameTime   = getCurrentTime();
        delta                   = (currentFrameTime - lastFrameTime)/1000f;
        lastFrameTime           = currentFrameTime;
    }

    public static long getCurrentTime(){
        return SystemClock.uptimeMillis()*1000;
    }

    public static float getFrameTimeSeconds(){
        return delta;
    }

}
