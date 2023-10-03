package p000;

import android.widget.SeekBar;

/* renamed from: dtq */
/* compiled from: PG */
final class dtq implements SeekBar.OnSeekBarChangeListener {

    /* renamed from: a */
    private final /* synthetic */ dtl f7356a;

    public dtq(dtl dtl) {
        this.f7356a = dtl;
    }

    public final void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (z) {
            this.f7356a.mo4411f();
            this.f7356a.mo4403a(i);
        }
    }

    public final void onStartTrackingTouch(SeekBar seekBar) {
        this.f7356a.mo4411f();
        this.f7356a.mo4406a(true);
    }

    public final void onStopTrackingTouch(SeekBar seekBar) {
        this.f7356a.mo4406a(false);
    }
}
