package p000;

import android.content.Context;
import android.util.AttributeSet;
import com.google.android.apps.photosgo.oneup.OneUpPagerView;

/* renamed from: doo */
/* compiled from: PG */
public class doo extends ebc implements ioe {

    /* renamed from: g */
    private ftv f6948g;

    public doo(Context context) {
        super(context);
        mo3396a();
    }

    public doo(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mo3396a();
    }

    public doo(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        mo3396a();
    }

    /* renamed from: a */
    private final void mo3396a() {
        OneUpPagerView oneUpPagerView = (OneUpPagerView) this;
        ((doi) mo2453b()).mo2483T();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
    }

    /* renamed from: b */
    public final Object mo2453b() {
        if (this.f6948g == null) {
            this.f6948g = new ftv(this);
        }
        return this.f6948g.mo2453b();
    }
}
