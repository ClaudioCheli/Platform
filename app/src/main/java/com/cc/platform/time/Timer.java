package com.cc.platform.time;

/**
 * Created by Claudio on 29/09/2017.
 */

public class Timer {

    private long startTime;

    private boolean started;

    public Timer(){
        started = false;
    }

    public void start(){
        startTime = Time.getCurrentTime();
        started = true;
    }

    public long getElapsedTime() {
        if(started)
            return Time.getCurrentTime() - startTime;
        return 0;
    }

    public void stop() {
        started = false;
        startTime = 0;
    }

}
