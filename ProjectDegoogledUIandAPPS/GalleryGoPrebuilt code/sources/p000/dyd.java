package p000;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import com.google.android.apps.photosgo.photogrid.PhotosPromoView;

/* renamed from: dyd */
/* compiled from: PG */
public class dyd extends ConstraintLayout implements ioe {

    /* renamed from: a */
    private ftv f7639a;

    public dyd(Context context) {
        super(context);
        mo3433c();
    }

    public dyd(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mo3433c();
    }

    public dyd(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        mo3433c();
    }

    /* renamed from: c */
    private final void mo3433c() {
        PhotosPromoView photosPromoView = (PhotosPromoView) this;
        ((dxn) mo2453b()).mo2489Z();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
    }

    /* renamed from: b */
    public final Object mo2453b() {
        if (this.f7639a == null) {
            this.f7639a = new ftv(this);
        }
        return this.f7639a.mo2453b();
    }
}
