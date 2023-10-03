package p000;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import com.google.android.apps.photosgo.devicefolders.PromoView;

/* renamed from: bsu */
/* compiled from: PG */
public class bsu extends ConstraintLayout implements ioe {

    /* renamed from: a */
    private ftv f3501a;

    public bsu(Context context) {
        super(context);
        mo3341c();
    }

    public bsu(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mo3341c();
    }

    public bsu(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        mo3341c();
    }

    /* renamed from: c */
    private final void mo3341c() {
        PromoView promoView = (PromoView) this;
        ((bso) mo2453b()).mo2493ac();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
    }

    /* renamed from: b */
    public final Object mo2453b() {
        if (this.f3501a == null) {
            this.f3501a = new ftv(this);
        }
        return this.f3501a.mo2453b();
    }
}
