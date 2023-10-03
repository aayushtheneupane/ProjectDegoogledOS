package com.android.messaging.util;

import android.media.MediaPlayer;

/* renamed from: com.android.messaging.util.ga */
class C1450ga implements MediaPlayer.OnCompletionListener {

    /* renamed from: wK */
    final /* synthetic */ C1448fa f2308wK;

    C1450ga(C1452ha haVar, C1448fa faVar) {
        this.f2308wK = faVar;
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        C1448fa faVar = this.f2308wK;
        if (faVar != null) {
            faVar.mo7850ba();
        }
        mediaPlayer.stop();
        mediaPlayer.release();
    }
}
