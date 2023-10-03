package com.android.incallui.answerproximitysensor;

public interface AnswerProximityWakeLock {

    public interface ScreenOnListener {
    }

    void acquire();

    boolean isHeld();

    void release();

    void setScreenOnListener(ScreenOnListener screenOnListener);
}
