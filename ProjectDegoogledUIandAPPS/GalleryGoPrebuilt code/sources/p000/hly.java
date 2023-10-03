package p000;

import android.widget.SeekBar;

/* renamed from: hly */
/* compiled from: PG */
public final class hly implements SeekBar.OnSeekBarChangeListener {

    /* renamed from: a */
    private final /* synthetic */ SeekBar.OnSeekBarChangeListener f13013a;

    /* renamed from: b */
    private final /* synthetic */ String f13014b;

    /* renamed from: c */
    private final /* synthetic */ hlz f13015c;

    public hly(hlz hlz, SeekBar.OnSeekBarChangeListener onSeekBarChangeListener, String str) {
        this.f13015c = hlz;
        this.f13013a = onSeekBarChangeListener;
        this.f13014b = str;
    }

    public final void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (hnb.m11774a(hnf.f13084a)) {
            this.f13013a.onProgressChanged(seekBar, i, z);
            return;
        }
        hlp a = this.f13015c.mo7577a(this.f13014b.concat("#onProgressChanged"));
        try {
            this.f13013a.onProgressChanged(seekBar, i, z);
            if (a != null) {
                a.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    public final void onStartTrackingTouch(SeekBar seekBar) {
        hlp a = this.f13015c.mo7577a(this.f13014b.concat("#onStartTrackingTouch"));
        try {
            this.f13013a.onStartTrackingTouch(seekBar);
            if (a != null) {
                a.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    public final void onStopTrackingTouch(SeekBar seekBar) {
        hlp a = this.f13015c.mo7577a(this.f13014b.concat("#onStopTrackingTouch"));
        try {
            this.f13013a.onStopTrackingTouch(seekBar);
            if (a != null) {
                a.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }
}
