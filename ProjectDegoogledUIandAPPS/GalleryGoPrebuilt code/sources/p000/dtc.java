package p000;

import android.media.MediaPlayer;

/* renamed from: dtc */
/* compiled from: PG */
final /* synthetic */ class dtc implements MediaPlayer.OnCompletionListener {

    /* renamed from: a */
    private final dtl f7313a;

    public dtc(dtl dtl) {
        this.f7313a = dtl;
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        dtl dtl = this.f7313a;
        if (dtl.mo4413h()) {
            dtl.f7327b = true;
            dtl.f7326a = dtl.mo4422q();
            dtl.mo4420o();
            dtl.mo4416k();
        }
    }
}
