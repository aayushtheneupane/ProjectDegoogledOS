package com.android.messaging.p041ui;

import android.animation.TimeAnimator;
import android.content.Context;
import android.graphics.drawable.ClipDrawable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import androidx.core.view.GravityCompat;

/* renamed from: com.android.messaging.ui.AudioPlaybackProgressBar */
public class AudioPlaybackProgressBar extends ProgressBar implements C1256ea {
    /* access modifiers changed from: private */

    /* renamed from: Uj */
    public long f1592Uj;

    /* renamed from: Vj */
    private final TimeAnimator f1593Vj = new TimeAnimator();
    /* access modifiers changed from: private */

    /* renamed from: Wj */
    public long f1594Wj = 0;
    /* access modifiers changed from: private */

    /* renamed from: Xj */
    public long f1595Xj = 0;

    /* renamed from: Yj */
    private boolean f1596Yj = false;

    public AudioPlaybackProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1593Vj.setRepeatCount(-1);
        this.f1593Vj.setTimeListener(new C1375q(this));
        updateAppearance();
    }

    private void updateAppearance() {
        setProgressDrawable(new ClipDrawable(C1037D.get().mo6938U(this.f1596Yj), GravityCompat.START, 1));
        setBackground(C1037D.get().mo6937T(this.f1596Yj));
    }

    /* renamed from: m */
    public void mo6882m(boolean z) {
        if (this.f1596Yj != z) {
            this.f1596Yj = z;
            updateAppearance();
        }
    }

    public void pause() {
        this.f1594Wj = (SystemClock.elapsedRealtime() - this.f1595Xj) + this.f1594Wj;
        if (this.f1593Vj.isStarted()) {
            this.f1593Vj.end();
        }
    }

    public void reset() {
        if (this.f1593Vj.isStarted()) {
            this.f1593Vj.end();
        }
        setProgress(0);
        this.f1594Wj = 0;
        this.f1595Xj = 0;
    }

    public void restart() {
        reset();
        resume();
    }

    public void resume() {
        this.f1595Xj = SystemClock.elapsedRealtime();
        if (!this.f1593Vj.isStarted()) {
            this.f1593Vj.start();
        }
    }

    public void setDuration(long j) {
        this.f1592Uj = j;
    }
}
