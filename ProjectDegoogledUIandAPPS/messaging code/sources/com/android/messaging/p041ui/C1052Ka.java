package com.android.messaging.p041ui;

import android.media.MediaPlayer;

/* renamed from: com.android.messaging.ui.Ka */
class C1052Ka implements MediaPlayer.OnCompletionListener {
    final /* synthetic */ VideoThumbnailView this$0;

    C1052Ka(VideoThumbnailView videoThumbnailView) {
        this.this$0 = videoThumbnailView;
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        this.this$0.f1684jf.setVisibility(0);
    }
}
