package com.android.messaging.p041ui;

import android.media.MediaPlayer;

/* renamed from: com.android.messaging.ui.Ja */
class C1050Ja implements MediaPlayer.OnPreparedListener {

    /* renamed from: EG */
    final /* synthetic */ boolean f1649EG;
    final /* synthetic */ VideoThumbnailView this$0;

    C1050Ja(VideoThumbnailView videoThumbnailView, boolean z) {
        this.this$0 = videoThumbnailView;
        this.f1649EG = z;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        boolean unused = this.this$0.f1683Bf = true;
        int unused2 = this.this$0.f1689yf = mediaPlayer.getVideoWidth();
        int unused3 = this.this$0.f1690zf = mediaPlayer.getVideoHeight();
        mediaPlayer.setLooping(this.f1649EG);
        this.this$0.m2654Mm();
    }
}
