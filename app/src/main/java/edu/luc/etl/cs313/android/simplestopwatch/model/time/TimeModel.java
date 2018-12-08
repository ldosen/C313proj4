package edu.luc.etl.cs313.android.simplestopwatch.model.time;

/**
 * The passive data model of the stopwatch.
 * It does not emit any events.
 *
 * @author laufer
 */
public interface TimeModel {
    void resetRuntime();
    void decRuntime();
    void incRuntime();
    public int getRuntime();
    boolean isFull();
    boolean isEmpty();

}