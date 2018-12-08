package edu.luc.etl.cs313.android.simplestopwatch.model.time;

/**
 * An implementation of the stopwatch time model that counts up and down.
 * Counting up represents an embedded clickcounter and counting down
 * represents a regular stopwatch.
 */
public class DefaultTimeModel implements TimeModel {

    int runningTime = 0;

    protected final int min = 0;

    protected final int max = 99;


    @Override
    public void resetRuntime() {
        runningTime = 0;
    }

    @Override
    public void decRuntime() {
        if (!isEmpty()){
            runningTime = (runningTime - 1);
        }
    }

    @Override
    public void incRuntime() {
        if (!isFull()){
            runningTime = (runningTime + 1);
        }
    }

    @Override
    public int getRuntime() {
        return runningTime;
    }

    @Override
    public boolean isFull() {
        return runningTime >= max;
    }

    @Override
    public boolean isEmpty() {
        return runningTime <= min;
    }

}