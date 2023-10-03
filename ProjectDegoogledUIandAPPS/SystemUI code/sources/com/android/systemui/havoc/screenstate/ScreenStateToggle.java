package com.android.systemui.havoc.screenstate;

import android.content.Context;

public abstract class ScreenStateToggle {
    protected Context mContext;
    protected boolean mDoAction = false;

    /* access modifiers changed from: protected */
    public abstract boolean doScreenOffAction();

    /* access modifiers changed from: protected */
    public abstract boolean doScreenOnAction();

    /* access modifiers changed from: protected */
    public abstract Runnable getScreenOffAction();

    /* access modifiers changed from: protected */
    public abstract Runnable getScreenOnAction();

    /* access modifiers changed from: protected */
    public abstract boolean isEnabled();

    /* access modifiers changed from: protected */
    public boolean runInThread() {
        return true;
    }

    public ScreenStateToggle(Context context) {
        this.mContext = context;
    }

    public void doScreenOff() {
        if (isEnabled() && doScreenOffAction()) {
            final Runnable screenOffAction = getScreenOffAction();
            if (runInThread()) {
                new Thread() {
                    public void run() {
                        screenOffAction.run();
                    }
                }.start();
            } else {
                screenOffAction.run();
            }
        }
    }

    public void doScreenOn() {
        if (isEnabled() && doScreenOnAction()) {
            final Runnable screenOnAction = getScreenOnAction();
            if (runInThread()) {
                new Thread() {
                    public void run() {
                        screenOnAction.run();
                    }
                }.start();
            } else {
                screenOnAction.run();
            }
        }
    }
}
