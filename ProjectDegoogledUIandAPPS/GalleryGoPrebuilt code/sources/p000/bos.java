package p000;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.google.android.apps.photosgo.category.CategoryView;

/* renamed from: bos */
/* compiled from: PG */
public class bos extends FrameLayout implements ioe {

    /* renamed from: a */
    private ftv f3276a;

    public bos(Context context) {
        super(context);
        mo3336a();
    }

    public bos(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mo3336a();
    }

    public bos(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        mo3336a();
    }

    public bos(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        mo3336a();
    }

    /* renamed from: a */
    private final void mo3336a() {
        CategoryView categoryView = (CategoryView) this;
        ((bop) mo2453b()).mo2471H();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
    }

    /* renamed from: b */
    public final Object mo2453b() {
        if (this.f3276a == null) {
            this.f3276a = new ftv(this);
        }
        return this.f3276a.mo2453b();
    }
}
