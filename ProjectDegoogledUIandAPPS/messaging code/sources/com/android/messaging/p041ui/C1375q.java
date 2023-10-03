package com.android.messaging.p041ui;

import android.animation.TimeAnimator;
import android.os.SystemClock;

/* renamed from: com.android.messaging.ui.q */
class C1375q implements TimeAnimator.TimeListener {
    final /* synthetic */ AudioPlaybackProgressBar this$0;

    C1375q(AudioPlaybackProgressBar audioPlaybackProgressBar) {
        this.this$0 = audioPlaybackProgressBar;
    }

    public void onTimeUpdate(TimeAnimator timeAnimator, long j, long j2) {
        int i = 0;
        if (this.this$0.f1592Uj > 0) {
            i = Math.max(Math.min((int) (((((float) ((SystemClock.elapsedRealtime() + this.this$0.f1594Wj) - this.this$0.f1595Xj)) * 1.0f) / ((float) this.this$0.f1592Uj)) * 100.0f), 100), 0);
        }
        this.this$0.setProgress(i);
    }
}
