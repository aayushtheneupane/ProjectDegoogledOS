package com.android.messaging.util;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Looper;
import com.android.messaging.C0967f;

/* renamed from: com.android.messaging.util.la */
final class C1460la extends Thread {
    public C1458ka mCmd;
    final /* synthetic */ C1462ma this$0;

    public C1460la(C1462ma maVar, C1458ka kaVar) {
        this.this$0 = maVar;
        this.mCmd = kaVar;
    }

    public void run() {
        Looper.prepare();
        Looper unused = this.this$0.mLooper = Looper.myLooper();
        synchronized (this) {
            AudioManager audioManager = (AudioManager) C0967f.get().getApplicationContext().getSystemService("audio");
            try {
                MediaPlayer mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(this.mCmd.f2310xK);
                mediaPlayer.setDataSource(C0967f.get().getApplicationContext(), this.mCmd.uri);
                mediaPlayer.setLooping(this.mCmd.looping);
                mediaPlayer.setVolume(this.mCmd.volume, this.mCmd.volume);
                mediaPlayer.prepare();
                if (!(this.mCmd.uri == null || this.mCmd.uri.getEncodedPath() == null || this.mCmd.uri.getEncodedPath().length() <= 0)) {
                    audioManager.requestAudioFocus((AudioManager.OnAudioFocusChangeListener) null, this.mCmd.f2310xK, this.mCmd.looping ? 2 : 3);
                }
                mediaPlayer.setOnCompletionListener(this.this$0);
                mediaPlayer.start();
                if (this.this$0.f2319DK != null) {
                    this.this$0.f2319DK.release();
                }
                MediaPlayer unused2 = this.this$0.f2319DK = mediaPlayer;
            } catch (Exception e) {
                String b = this.this$0.mTag;
                C1430e.m3631w(b, "error loading sound for " + this.mCmd.uri, e);
            }
            AudioManager unused3 = this.this$0.mAudioManager = audioManager;
            notify();
        }
        Looper.loop();
    }
}
