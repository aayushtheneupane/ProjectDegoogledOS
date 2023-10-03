package com.android.messaging.p041ui.mediapicker;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.widget.Chronometer;
import com.android.messaging.p041ui.C1256ea;

/* renamed from: com.android.messaging.ui.mediapicker.PausableChronometer */
public class PausableChronometer extends Chronometer implements C1256ea {

    /* renamed from: Zj */
    private long f2040Zj = 0;

    public PausableChronometer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void pause() {
        stop();
        this.f2040Zj = SystemClock.elapsedRealtime() - getBase();
    }

    public void reset() {
        stop();
        setBase(SystemClock.elapsedRealtime());
        this.f2040Zj = 0;
    }

    public void restart() {
        reset();
        start();
    }

    public void resume() {
        setBase(SystemClock.elapsedRealtime() - this.f2040Zj);
        start();
    }
}
