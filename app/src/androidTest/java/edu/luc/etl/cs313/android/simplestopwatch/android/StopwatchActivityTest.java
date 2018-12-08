package edu.luc.etl.cs313.android.simplestopwatch.android;

import android.test.ActivityInstrumentationTestCase2;

import edu.luc.etl.cs313.android.simplestopwatch.test.android.AbstractStopwatchActivityTest;

/**
 * Concrete Android test subclass. Has to inherit from framework class
 * and uses delegation to concrete subclass of abstract test superclass.
 * IMPORTANT: project must export JUnit 4 to make it available on the
 * device.
 *
 * @author laufer
 * @see "https://developer.android.com/training/testing/ui-testing/"
 */
public class StopwatchActivityTest extends ActivityInstrumentationTestCase2<StopwatchAdapter> {

    /**
     * Creates an {@link ActivityInstrumentationTestCase2} for the
     * {@link StopwatchAdapter} activity.
     */
    public StopwatchActivityTest() {
        super(StopwatchAdapter.class);
        actualTest = new AbstractStopwatchActivityTest() {
            @Override
            protected StopwatchAdapter getActivity() {
                // return activity instance provided by instrumentation test
                if (stopwatchAdapter == null)
                    stopwatchAdapter = StopwatchActivityTest.this.getActivity();
                return stopwatchAdapter;
            }
        };
    }

    private static StopwatchAdapter stopwatchAdapter = null;
    private AbstractStopwatchActivityTest actualTest;

    public void testAActivityCheckTestCaseSetUpProperly() {
        actualTest.testActivityCheckTestCaseSetUpProperly();
    }

    public void testBActivityScenarioInit() throws Throwable {
        actualTest.testActivityScenarioInit();
    }

    public void testFActivityScenarioRun() throws Throwable {
        actualTest.testActivityScenarioRun();
    }

    public void testEActivityScenarioRunReset() throws Throwable {
        actualTest.testActivityScenarioRunReset();
    }

    public void testDActivityScenarioFullRun() throws Throwable {
        actualTest.testActivityScenarioFullRun();
    }

    public void testGActivityScenarioIncUntilFull() throws Throwable {
        actualTest.testActivityScenarioIncUntilFull();
    }
}
