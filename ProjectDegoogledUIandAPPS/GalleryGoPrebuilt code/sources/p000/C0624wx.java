package p000;

import android.graphics.Rect;
import android.support.p002v7.widget.RecyclerView;
import android.view.View;

/* renamed from: wx */
/* compiled from: PG */
public abstract class C0624wx {

    /* renamed from: a */
    public final C0647xt f16276a;

    /* renamed from: b */
    public int f16277b = RecyclerView.UNDEFINED_DURATION;

    /* renamed from: c */
    public final Rect f16278c = new Rect();

    public /* synthetic */ C0624wx(C0647xt xtVar) {
        this.f16276a = xtVar;
    }

    /* renamed from: a */
    public abstract int mo10511a();

    /* renamed from: a */
    public abstract int mo10512a(View view);

    /* renamed from: a */
    public abstract void mo10513a(int i);

    /* renamed from: b */
    public abstract int mo10514b();

    /* renamed from: b */
    public abstract int mo10515b(View view);

    /* renamed from: c */
    public abstract int mo10516c();

    /* renamed from: c */
    public abstract int mo10517c(View view);

    /* renamed from: d */
    public abstract int mo10518d();

    /* renamed from: d */
    public abstract int mo10519d(View view);

    /* renamed from: e */
    public abstract int mo10520e();

    /* renamed from: e */
    public abstract int mo10521e(View view);

    /* renamed from: f */
    public abstract int mo10522f();

    /* renamed from: f */
    public abstract int mo10523f(View view);

    /* renamed from: g */
    public abstract int mo10524g();

    /* renamed from: a */
    public static C0624wx m15820a(C0647xt xtVar) {
        return new C0622wv(xtVar);
    }

    /* renamed from: b */
    public static C0624wx m15821b(C0647xt xtVar) {
        return new C0623ww(xtVar);
    }

    /* renamed from: h */
    public final int mo10525h() {
        if (this.f16277b != Integer.MIN_VALUE) {
            return mo10518d() - this.f16277b;
        }
        return 0;
    }
}
