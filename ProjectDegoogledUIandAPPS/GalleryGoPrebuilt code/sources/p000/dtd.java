package p000;

import android.media.MediaPlayer;

/* renamed from: dtd */
/* compiled from: PG */
final /* synthetic */ class dtd implements MediaPlayer.OnInfoListener {

    /* renamed from: a */
    private final dtl f7314a;

    public dtd(dtl dtl) {
        this.f7314a = dtl;
    }

    public final boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
        dtl dtl = this.f7314a;
        if (i != 3) {
            return false;
        }
        dtl.mo4412g();
        return true;
    }
}
