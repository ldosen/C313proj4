
package edu.luc.etl.cs313.android.simplestopwatch.model.state;

/**
 * The restricted view states have of their surrounding state machine.
 * This is a client-specific interface in Peter Coad's terminology.
 *
 * @author laufer
 */
interface StopwatchSMStateView {

    // transitions
    void toRunningState();
    void toStoppedState();
    void toIncrementState();
    void toStoppedResetState();

    // actions
    void actionInit();
    void actionReset();
    void actionStart();
    void actionStop();
    void actionDec();
    void actionInc();
    void actionUpdateView();
    void actionSoundTheAlarm();

    // state-dependent UI updates
    void updateUIRuntime();

    int getDelay();
    void setDelay(int delay);
    boolean reachMax();
    boolean countedDown();
}