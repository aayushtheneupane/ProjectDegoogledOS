package com.android.messaging.p041ui;

import android.media.MediaPlayer;
import android.os.SystemClock;

/* renamed from: com.android.messaging.ui.o */
class C1368o implements MediaPlayer.OnPreparedListener {
    final /* synthetic */ AudioAttachmentView this$0;

    C1368o(AudioAttachmentView audioAttachmentView) {
        this.this$0 = audioAttachmentView;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        this.this$0.mChronometer.setBase(SystemClock.elapsedRealtime() - ((long) this.this$0.f1581ch.getDuration()));
        this.this$0.mProgressBar.setDuration((long) this.this$0.f1581ch.getDuration());
        this.this$0.f1581ch.seekTo(0);
        boolean unused = this.this$0.f1589kh = true;
        if (this.this$0.f1587ih) {
            boolean unused2 = this.this$0.f1587ih = false;
            AudioAttachmentView.m2492e(this.this$0);
            this.this$0.m2480Xm();
        }
    }
}
