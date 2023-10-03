package com.android.systemui.doze;

import android.os.Handler;
import android.util.Log;
import com.android.systemui.doze.DozeMachine;
import com.android.systemui.statusbar.phone.DozeParameters;
import com.android.systemui.util.wakelock.SettableWakeLock;
import com.android.systemui.util.wakelock.WakeLock;

public class DozeScreenState implements DozeMachine.Part {
    private static final boolean DEBUG = DozeService.DEBUG;
    private final Runnable mApplyPendingScreenState = new Runnable() {
        public final void run() {
            DozeScreenState.this.applyPendingScreenState();
        }
    };
    private final DozeMachine.Service mDozeService;
    private final Handler mHandler;
    private final DozeParameters mParameters;
    private int mPendingScreenState = 0;
    private SettableWakeLock mWakeLock;

    public DozeScreenState(DozeMachine.Service service, Handler handler, DozeParameters dozeParameters, WakeLock wakeLock) {
        this.mDozeService = service;
        this.mHandler = handler;
        this.mParameters = dozeParameters;
        this.mWakeLock = new SettableWakeLock(wakeLock, "DozeScreenState");
    }

    public void transitionTo(DozeMachine.State state, DozeMachine.State state2) {
        int screenState = state2.screenState(this.mParameters);
        boolean z = false;
        if (state2 == DozeMachine.State.FINISH) {
            this.mPendingScreenState = 0;
            this.mHandler.removeCallbacks(this.mApplyPendingScreenState);
            applyScreenState(screenState);
            this.mWakeLock.setAcquired(false);
        } else if (screenState != 0) {
            boolean hasCallbacks = this.mHandler.hasCallbacks(this.mApplyPendingScreenState);
            int i = 1;
            boolean z2 = state == DozeMachine.State.DOZE_PULSE_DONE && state2 == DozeMachine.State.DOZE_AOD;
            boolean z3 = (state == DozeMachine.State.DOZE_AOD_PAUSED || state == DozeMachine.State.DOZE) && state2 == DozeMachine.State.DOZE_AOD;
            boolean z4 = state == DozeMachine.State.INITIALIZED;
            if (hasCallbacks || z4 || z2 || z3) {
                this.mPendingScreenState = screenState;
                if (state2 == DozeMachine.State.DOZE_AOD && this.mParameters.shouldControlScreenOff() && !z3) {
                    z = true;
                }
                if (z) {
                    this.mWakeLock.setAcquired(true);
                }
                if (!hasCallbacks) {
                    if (DEBUG) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Display state changed to ");
                        sb.append(screenState);
                        sb.append(" delayed by ");
                        if (z) {
                            i = 4000;
                        }
                        sb.append(i);
                        Log.d("DozeScreenState", sb.toString());
                    }
                    if (z) {
                        this.mHandler.postDelayed(this.mApplyPendingScreenState, 4000);
                    } else {
                        this.mHandler.post(this.mApplyPendingScreenState);
                    }
                } else if (DEBUG) {
                    Log.d("DozeScreenState", "Pending display state change to " + screenState);
                }
            } else {
                applyScreenState(screenState);
            }
        }
    }

    /* access modifiers changed from: private */
    public void applyPendingScreenState() {
        applyScreenState(this.mPendingScreenState);
        this.mPendingScreenState = 0;
    }

    private void applyScreenState(int i) {
        if (i != 0) {
            if (DEBUG) {
                Log.d("DozeScreenState", "setDozeScreenState(" + i + ")");
            }
            this.mDozeService.setDozeScreenState(i);
            this.mPendingScreenState = 0;
            this.mWakeLock.setAcquired(false);
        }
    }
}
