package p000;

import android.content.Context;
import android.widget.FrameLayout;

/* renamed from: dol */
/* compiled from: PG */
class dol extends FrameLayout implements ioe {

    /* renamed from: a */
    private ftv f6943a;

    public dol(Context context) {
        super(context);
        dln dln = (dln) this;
        ((dly) mo2453b()).mo2479P();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
    }

    /* renamed from: b */
    public final Object mo2453b() {
        if (this.f6943a == null) {
            this.f6943a = new ftv(this);
        }
        return this.f6943a.mo2453b();
    }
}
