package com.android.messaging.p041ui.mediapicker;

import android.animation.TimeAnimator;

/* renamed from: com.android.messaging.ui.mediapicker.za */
class C1365za implements TimeAnimator.TimeListener {
    final /* synthetic */ SoundLevels this$0;

    C1365za(SoundLevels soundLevels) {
        this.this$0 = soundLevels;
    }

    public void onTimeUpdate(TimeAnimator timeAnimator, long j, long j2) {
        this.this$0.invalidate();
    }
}
