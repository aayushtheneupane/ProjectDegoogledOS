package p000;

import android.content.Context;
import android.graphics.RectF;
import android.view.accessibility.AccessibilityManager;
import com.google.android.apps.photosgo.R;

/* renamed from: bxg */
/* compiled from: PG */
public final class bxg {

    /* renamed from: a */
    public final RectF f3820a = new RectF();

    /* renamed from: b */
    public final Context f3821b;

    /* renamed from: c */
    public final bxm f3822c;

    /* renamed from: d */
    public final bxc f3823d;

    /* renamed from: e */
    public final AccessibilityManager f3824e;

    /* renamed from: f */
    public C0372no f3825f;

    /* renamed from: g */
    public final int f3826g;

    /* renamed from: h */
    private final RectF f3827h = new RectF();

    public bxg(Context context, C0147fh fhVar, bxc bxc, bxm bxm) {
        ife.m12898e((Object) fhVar);
        this.f3821b = context;
        this.f3823d = bxc;
        this.f3822c = bxm;
        this.f3824e = (AccessibilityManager) context.getSystemService("accessibility");
        this.f3826g = context.getResources().getDimensionPixelSize(R.dimen.editor_crop_touch_handle_size);
    }

    /* renamed from: a */
    public final RectF mo2874a() {
        byu.f3916b.mo2916b(((byc) this.f3822c).f3888c, this.f3827h);
        RectF rectF = this.f3827h;
        rectF.left = ihg.m13049c(rectF.left, this.f3820a);
        RectF rectF2 = this.f3827h;
        rectF2.top = ihg.m13052d(rectF2.top, this.f3820a);
        RectF rectF3 = this.f3827h;
        rectF3.right = ihg.m13049c(rectF3.right, this.f3820a);
        RectF rectF4 = this.f3827h;
        rectF4.bottom = ihg.m13052d(rectF4.bottom, this.f3820a);
        return this.f3827h;
    }
}
