package p000;

import android.content.Context;
import android.widget.FrameLayout;

/* renamed from: dvb */
/* compiled from: PG */
class dvb extends FrameLayout implements ioe {

    /* renamed from: a */
    private ftv f7446a;

    public dvb(Context context) {
        super(context);
        dum dum = (dum) this;
        ((dur) mo2453b()).mo2486W();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
    }

    /* renamed from: b */
    public final Object mo2453b() {
        if (this.f7446a == null) {
            this.f7446a = new ftv(this);
        }
        return this.f7446a.mo2453b();
    }
}
