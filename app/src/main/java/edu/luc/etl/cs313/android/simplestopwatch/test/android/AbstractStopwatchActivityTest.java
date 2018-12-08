package edu.luc.etl.cs313.android.simplestopwatch.test.android;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import android.app.Activity;
import android.os.SystemClock;
import android.view.View;
import android.content.pm.ActivityInfo;
import android.widget.Button;
import android.widget.TextView;
import edu.luc.etl.cs313.android.simplestopwatch.R;
import edu.luc.etl.cs313.android.simplestopwatch.android.StopwatchAdapter;


/**
 * Abstract GUI-level test superclass of several essential stopwatch scenarios.
 *
 * @author laufer
 *
 * TODO move this and the other tests to src/test once Android Studio supports
 * non-instrumentation unit tests properly.
 */
public abstract class AbstractStopwatchActivityTest {

    /**
     * Verifies that the activity under test can be launched.
     */
    @Test
    public void testActivityCheckTestCaseSetUpProperly() {
        assertNotNull("activity should be launched successfully", getActivity());
    }

    /**
     * Verifies the following scenario: time is 0.
     *
     * @throws Throwable
     */
    @Test
    public void testActivityScenarioInit() throws Throwable {
        getActivity().runOnUiThread(new Runnable() { @Override public void run() {
            assertEquals(0, getDisplayedValue());
        }});
    }


    /**
     * Verifies the following scenario: time is 0, press button 5 times,
     * wait 4 seconds, expect time 4.
     *
     * @throws Throwable
     */
    @Test
    public void testActivityScenarioRun() throws Throwable {
        getActivity().runOnUiThread(new Runnable() { @Override public void run() {
            assertEquals(0, getDisplayedValue());
            assertTrue(getButton().performClick());
            assertTrue(getButton().performClick());
            assertTrue(getButton().performClick());
            assertTrue(getButton().performClick());
            assertTrue(getButton().performClick());
            assertEquals(5, getDisplayedValue());
        }});
        Thread.sleep(5000); // <-- do not run this in the UI thread!
        runUiThreadTasks();
        getActivity().runOnUiThread(new Runnable() { @Override public void run() {
            //expect running state
            assertEquals(4, getDisplayedValue());
        }});
    }

    /**
     * Verifies the following scenario: time is 0, press button 5 times, expect time 5,
     * wait 4 seconds, expect time 4, press button, expect time 0.
     *
     * @throws Throwable
     */
    @Test
    public void testActivityScenarioRunReset() throws Throwable {
        getActivity().runOnUiThread(new Runnable() { @Override public void run() {
            assertEquals(0, getDisplayedValue());
            assertTrue(getButton().performClick());
            assertTrue(getButton().performClick());
            assertTrue(getButton().performClick());
            assertTrue(getButton().performClick());
            assertTrue(getButton().performClick());
            assertEquals(5, getDisplayedValue());
        }});
        SystemClock.sleep(5000); // <-- do not run this in the UI thread!
        assertEquals(4, getDisplayedValue());
        runUiThreadTasks();
        getActivity().runOnUiThread(new Runnable() { @Override public void run() {
            //expect running state
            assertTrue(getButton().performClick());
        }});
        runUiThreadTasks();
        getActivity().runOnUiThread(new Runnable() { @Override public void run() {
            //expect stopped state
            assertEquals(0, getDisplayedValue());
        }});
    }

    /**
     * Verifies the following scenario: time is 0, press button 5 times,
     * wait 4 seconds, expect time 4, wait 4 seconds, expect time 0, indefinite beeping.
     *
     * @throws Throwable
     */
    @Test
    public void testActivityScenarioFullRun() throws Throwable {
        getActivity().runOnUiThread(new Runnable() { @Override public void run() {
            assertEquals(0, getDisplayedValue());
            assertTrue(getButton().performClick());
            assertTrue(getButton().performClick());
            assertTrue(getButton().performClick());
            assertTrue(getButton().performClick());
            assertTrue(getButton().performClick());
        }});
        SystemClock.sleep(5000); // <-- do not run this in the UI thread!
        runUiThreadTasks();
        assertEquals(4, getDisplayedValue());
        SystemClock.sleep(5000); // <-- do not run this in the UI thread!
        runUiThreadTasks();
        assertEquals(0, getDisplayedValue());
        //expect indefinite beeping
    }

    //tests from ClickCounter
    // begin-method-testActivityScenarioInc
    @Test
    public void testActivityScenarioInc() throws Throwable {
        getActivity();
        assertEquals(0, getDisplayedValue());
        assertTrue(getButton().performClick());
        assertTrue(getButton().performClick());
        assertEquals(2, getDisplayedValue());
    }

    // begin-method-testActivityScenarioIncUntilFull
    @Test
    public void testActivityScenarioIncUntilFull() throws Throwable {
        getActivity().runOnUiThread(new Runnable() {@Override public void run() {
            assertEquals(0, getDisplayedValue());
            for (int i = 0; i < 99; i++) {
                assertTrue(getButton().performClick());
            }
            assertEquals(99, getDisplayedValue());
            assertTrue(getButton().performClick());
            assertEquals(0, getDisplayedValue());
            assertTrue(getButton().performClick());
        }});
    }

    // begin-method-testActivityScenarioRotation
    @Test
    public void testActivityScenarioRotation() throws Throwable {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                assertEquals(0, getDisplayedValue());
                assertTrue(getButton().performClick());
                assertTrue(getButton().performClick());
                assertEquals(2, getDisplayedValue());
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                assertEquals(2, getDisplayedValue());
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                assertEquals(2, getDisplayedValue());
            }});
    }


    // auxiliary methods for easy access to UI widgets

    protected abstract StopwatchAdapter getActivity();

    protected int tvToInt(final TextView t) {
        return Integer.parseInt(t.getText().toString().trim());
    }

    protected int getDisplayedValue() {
        final TextView ts = (TextView) getActivity().findViewById(R.id.seconds);
        return tvToInt(ts);
    }


    protected Button getButton() {
        return (Button) getActivity().findViewById(R.id.startStop);
    }


    /**
     * Explicitly runs tasks scheduled to run on the UI thread in case this is required
     * by the testing framework, e.g., Robolectric.
     */
    protected void runUiThreadTasks() { }
}