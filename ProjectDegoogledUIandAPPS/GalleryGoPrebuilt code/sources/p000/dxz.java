package p000;

import android.content.Context;
import android.util.AttributeSet;
import com.google.android.apps.photosgo.photogrid.DateHeaderView;

/* renamed from: dxz */
/* compiled from: PG */
public class dxz extends C0558ul implements ioe {

    /* renamed from: b */
    private ftv f7619b;

    public dxz(Context context) {
        super(context);
        mo3426a();
    }

    public dxz(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mo3426a();
    }

    public dxz(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        mo3426a();
    }

    /* renamed from: a */
    private final void mo3426a() {
        DateHeaderView dateHeaderView = (DateHeaderView) this;
        ((dvv) mo2453b()).mo2472I();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
    }

    /* renamed from: b */
    public final Object mo2453b() {
        if (this.f7619b == null) {
            this.f7619b = new ftv(this);
        }
        return this.f7619b.mo2453b();
    }
}
