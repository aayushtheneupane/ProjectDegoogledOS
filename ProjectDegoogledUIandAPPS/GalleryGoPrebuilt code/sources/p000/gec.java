package p000;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Paint;
import com.google.android.apps.photosgo.R;

/* renamed from: gec */
/* compiled from: PG */
final class gec {

    /* renamed from: a */
    public final geb f11093a;

    /* renamed from: b */
    public final geb f11094b;

    /* renamed from: c */
    public final geb f11095c;

    /* renamed from: d */
    public final geb f11096d;

    /* renamed from: e */
    public final geb f11097e;

    /* renamed from: f */
    public final geb f11098f;

    /* renamed from: g */
    public final geb f11099g;

    /* renamed from: h */
    private final Paint f11100h;

    public gec(Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(ggf.m10244a(context, (int) R.attr.materialCalendarStyle, geq.class.getCanonicalName()), gfb.f11154a);
        this.f11093a = geb.m10148a(context, obtainStyledAttributes.getResourceId(3, 0));
        this.f11099g = geb.m10148a(context, obtainStyledAttributes.getResourceId(1, 0));
        this.f11094b = geb.m10148a(context, obtainStyledAttributes.getResourceId(2, 0));
        this.f11095c = geb.m10148a(context, obtainStyledAttributes.getResourceId(4, 0));
        ColorStateList a = gqb.m10615a(context, obtainStyledAttributes, 5);
        this.f11096d = geb.m10148a(context, obtainStyledAttributes.getResourceId(7, 0));
        this.f11097e = geb.m10148a(context, obtainStyledAttributes.getResourceId(6, 0));
        this.f11098f = geb.m10148a(context, obtainStyledAttributes.getResourceId(8, 0));
        Paint paint = new Paint();
        this.f11100h = paint;
        paint.setColor(a.getDefaultColor());
        obtainStyledAttributes.recycle();
    }
}
