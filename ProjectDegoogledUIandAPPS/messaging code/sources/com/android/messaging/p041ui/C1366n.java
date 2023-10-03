package com.android.messaging.p041ui;

import android.media.MediaPlayer;
import android.os.SystemClock;

/* renamed from: com.android.messaging.ui.n */
class C1366n implements MediaPlayer.OnCompletionListener {
    final /* synthetic */ AudioAttachmentView this$0;

    C1366n(AudioAttachmentView audioAttachmentView) {
        this.this$0 = audioAttachmentView;
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        this.this$0.m2480Xm();
        this.this$0.mChronometer.reset();
        this.this$0.mChronometer.setBase(SystemClock.elapsedRealtime() - ((long) this.this$0.f1581ch.getDuration()));
        this.this$0.m2496ta(false);
        this.this$0.mProgressBar.reset();
        boolean unused = this.this$0.f1590lh = true;
    }
}
