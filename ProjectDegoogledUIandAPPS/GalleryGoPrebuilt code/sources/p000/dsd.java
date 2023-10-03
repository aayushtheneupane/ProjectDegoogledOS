package p000;

import android.content.Context;
import android.support.p002v7.widget.RecyclerView;
import android.util.AttributeSet;
import com.google.android.apps.photosgo.oneup.photo.carousel.CarouselView;

/* renamed from: dsd */
/* compiled from: PG */
public class dsd extends RecyclerView implements ioe {

    /* renamed from: a */
    private ftv f7266a;

    public dsd(Context context) {
        super(context);
        mo3412a();
    }

    public dsd(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mo3412a();
    }

    public dsd(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        mo3412a();
    }

    /* renamed from: a */
    private final void mo3412a() {
        CarouselView carouselView = (CarouselView) this;
        ((dsb) mo2453b()).mo2469F();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
    }

    /* renamed from: b */
    public final Object mo2453b() {
        if (this.f7266a == null) {
            this.f7266a = new ftv(this);
        }
        return this.f7266a.mo2453b();
    }
}
