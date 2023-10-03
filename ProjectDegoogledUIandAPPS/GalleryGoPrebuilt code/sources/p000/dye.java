package p000;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.google.android.apps.photosgo.photogrid.SinglePhotoView;

/* renamed from: dye */
/* compiled from: PG */
public class dye extends FrameLayout implements ioe {

    /* renamed from: a */
    private ftv f7640a;

    public dye(Context context) {
        super(context);
        mo3437a();
    }

    public dye(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mo3437a();
    }

    public dye(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        mo3437a();
    }

    public dye(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        mo3437a();
    }

    /* renamed from: a */
    private final void mo3437a() {
        SinglePhotoView singlePhotoView = (SinglePhotoView) this;
        ((dxw) mo2453b()).mo2496af();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
    }

    /* renamed from: b */
    public final Object mo2453b() {
        if (this.f7640a == null) {
            this.f7640a = new ftv(this);
        }
        return this.f7640a.mo2453b();
    }
}
