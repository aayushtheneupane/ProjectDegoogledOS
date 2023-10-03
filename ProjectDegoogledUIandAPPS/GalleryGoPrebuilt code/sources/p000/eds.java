package p000;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import com.google.android.apps.photosgo.sharing.SingleAppView;

/* renamed from: eds */
/* compiled from: PG */
public class eds extends ConstraintLayout implements ioe {

    /* renamed from: a */
    private ftv f8057a;

    public eds(Context context) {
        super(context);
        mo3450c();
    }

    public eds(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mo3450c();
    }

    public eds(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        mo3450c();
    }

    /* renamed from: c */
    private final void mo3450c() {
        SingleAppView singleAppView = (SingleAppView) this;
        ((edp) mo2453b()).mo2495ae();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
    }

    /* renamed from: b */
    public final Object mo2453b() {
        if (this.f8057a == null) {
            this.f8057a = new ftv(this);
        }
        return this.f8057a.mo2453b();
    }
}
