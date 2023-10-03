package p000;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.google.android.apps.photosgo.category.CategoryListView;

/* renamed from: boq */
/* compiled from: PG */
public class boq extends FrameLayout implements ioe {

    /* renamed from: a */
    private ftv f3272a;

    public boq(Context context) {
        super(context);
        mo3332a();
    }

    public boq(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mo3332a();
    }

    public boq(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        mo3332a();
    }

    public boq(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        mo3332a();
    }

    /* renamed from: a */
    private final void mo3332a() {
        CategoryListView categoryListView = (CategoryListView) this;
        ((bnw) mo2453b()).mo2470G();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
    }

    /* renamed from: b */
    public final Object mo2453b() {
        if (this.f3272a == null) {
            this.f3272a = new ftv(this);
        }
        return this.f3272a.mo2453b();
    }
}
