package p000;

import android.media.MediaPlayer;

/* renamed from: cdc */
/* compiled from: PG */
final /* synthetic */ class cdc implements MediaPlayer.OnErrorListener {

    /* renamed from: a */
    public static final MediaPlayer.OnErrorListener f4097a = new cdc();

    private cdc() {
    }

    public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        cwn.m5510a("Video view error %d %d", Integer.valueOf(i), Integer.valueOf(i2));
        return false;
    }
}
