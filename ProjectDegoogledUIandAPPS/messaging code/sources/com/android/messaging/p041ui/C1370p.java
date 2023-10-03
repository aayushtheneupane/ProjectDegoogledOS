package com.android.messaging.p041ui;

import android.media.MediaPlayer;

/* renamed from: com.android.messaging.ui.p */
class C1370p implements MediaPlayer.OnErrorListener {
    final /* synthetic */ AudioAttachmentView this$0;

    C1370p(AudioAttachmentView audioAttachmentView) {
        this.this$0 = audioAttachmentView;
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        boolean unused = this.this$0.f1587ih = false;
        this.this$0.m2483a(i, i2, (Exception) null);
        return true;
    }
}
