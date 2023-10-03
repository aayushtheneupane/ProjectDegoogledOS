package p000;

import android.media.MediaPlayer;

/* renamed from: dsu */
/* compiled from: PG */
final /* synthetic */ class dsu implements MediaPlayer.OnErrorListener {

    /* renamed from: a */
    private final dsw f7293a;

    public dsu(dsw dsw) {
        this.f7293a = dsw;
    }

    public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        this.f7293a.mo4395g();
        return true;
    }
}
