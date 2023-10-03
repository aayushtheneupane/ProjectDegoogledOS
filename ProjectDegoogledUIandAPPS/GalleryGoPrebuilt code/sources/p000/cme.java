package p000;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import com.google.android.apps.photosgo.foldermanagement.creation.SingleVolumeChooserView;

/* renamed from: cme */
/* compiled from: PG */
public class cme extends ConstraintLayout implements ioe {

    /* renamed from: a */
    private ftv f4669a;

    public cme(Context context) {
        super(context);
        mo3378c();
    }

    public cme(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mo3378c();
    }

    public cme(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        mo3378c();
    }

    /* renamed from: c */
    private final void mo3378c() {
        SingleVolumeChooserView singleVolumeChooserView = (SingleVolumeChooserView) this;
        ((cmb) mo2453b()).mo2497ag();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
    }

    /* renamed from: b */
    public final Object mo2453b() {
        if (this.f4669a == null) {
            this.f4669a = new ftv(this);
        }
        return this.f4669a.mo2453b();
    }
}
