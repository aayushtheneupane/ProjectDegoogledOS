package p000;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.google.android.apps.photosgo.oneup.photo.carousel.CarouselItemView;

/* renamed from: dsc */
/* compiled from: PG */
public class dsc extends LinearLayout implements ioe {

    /* renamed from: a */
    private ftv f7265a;

    public dsc(Context context) {
        super(context);
        mo3408a();
    }

    public dsc(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mo3408a();
    }

    public dsc(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        mo3408a();
    }

    public dsc(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        mo3408a();
    }

    /* renamed from: a */
    private final void mo3408a() {
        CarouselItemView carouselItemView = (CarouselItemView) this;
        ((drv) mo2453b()).mo2468E();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
    }

    /* renamed from: b */
    public final Object mo2453b() {
        if (this.f7265a == null) {
            this.f7265a = new ftv(this);
        }
        return this.f7265a.mo2453b();
    }
}
