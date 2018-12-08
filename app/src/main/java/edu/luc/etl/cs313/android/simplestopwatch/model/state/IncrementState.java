
package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;

class IncrementState implements StopwatchState {

    public IncrementState(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    private final StopwatchSMStateView sm;

    @Override
    public void onStartStop() {
        sm.actionInc();
        sm.toIncrementState();
    }

    @Override
    public void onTick() {
        int d = sm.getDelay();
        sm.setDelay(++d);
        if (d == 3 || sm.reachMax()) {
            sm.actionSoundTheAlarm();
            sm.toRunningState();
        }
    }

    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    @Override
    public int getId() {
        return R.string.INCREMENT;
    }
}