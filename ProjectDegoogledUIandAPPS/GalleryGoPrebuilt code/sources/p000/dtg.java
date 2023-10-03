package p000;

import android.media.AudioManager;

/* renamed from: dtg */
/* compiled from: PG */
final class dtg implements AudioManager.OnAudioFocusChangeListener {

    /* renamed from: a */
    private final /* synthetic */ dtl f7317a;

    public dtg(dtl dtl) {
        this.f7317a = dtl;
    }

    public final void onAudioFocusChange(int i) {
        if (i == -1 || i == -2 || i == -3) {
            this.f7317a.mo4416k();
        }
    }
}
