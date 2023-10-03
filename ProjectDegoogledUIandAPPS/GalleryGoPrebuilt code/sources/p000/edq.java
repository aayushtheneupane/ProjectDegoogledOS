package p000;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.google.android.apps.photosgo.sharing.SharingAppGridView;

/* renamed from: edq */
/* compiled from: PG */
public class edq extends FrameLayout implements ioe {

    /* renamed from: a */
    private ftv f8053a;

    public edq(Context context) {
        super(context);
        mo3447a();
    }

    public edq(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mo3447a();
    }

    public edq(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        mo3447a();
    }

    public edq(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        mo3447a();
    }

    /* renamed from: a */
    private final void mo3447a() {
        SharingAppGridView sharingAppGridView = (SharingAppGridView) this;
        ((ecv) mo2453b()).mo2494ad();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
    }

    /* renamed from: b */
    public final Object mo2453b() {
        if (this.f8053a == null) {
            this.f8053a = new ftv(this);
        }
        return this.f8053a.mo2453b();
    }
}
