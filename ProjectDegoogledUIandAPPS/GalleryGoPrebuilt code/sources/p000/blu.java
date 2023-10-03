package p000;

import android.support.p002v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import com.google.android.apps.photosgo.R;

/* renamed from: blu */
/* compiled from: PG */
public final class blu {

    /* renamed from: a */
    public final cnh f3118a;

    /* renamed from: b */
    private final C0147fh f3119b;

    /* renamed from: c */
    private final hlz f3120c;

    /* renamed from: d */
    private View f3121d = null;

    public blu(C0147fh fhVar, hlz hlz, cnh cnh) {
        this.f3119b = fhVar;
        this.f3120c = hlz;
        this.f3118a = cnh;
    }

    /* renamed from: a */
    public static WindowInsets m3210a(View view, WindowInsets windowInsets) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        marginLayoutParams.topMargin = windowInsets.getSystemWindowInsetTop();
        marginLayoutParams.leftMargin = windowInsets.getSystemWindowInsetLeft();
        marginLayoutParams.rightMargin = windowInsets.getSystemWindowInsetRight();
        return windowInsets;
    }

    /* renamed from: a */
    public final void mo2575a(View view) {
        view.setOnApplyWindowInsetsListener(blt.f3117a);
        this.f3121d = view;
    }

    /* renamed from: a */
    public final void mo2573a(Toolbar toolbar) {
        m3211a(toolbar, 0, false);
    }

    /* renamed from: b */
    public final void mo2576b(Toolbar toolbar) {
        m3211a(toolbar, 0, true);
    }

    /* renamed from: a */
    private final void m3211a(Toolbar toolbar, int i, boolean z) {
        if (this.f3118a.mo3273d()) {
            toolbar.mo1096d((int) R.drawable.abc_ic_ab_back_material);
            toolbar.mo1092c((int) R.string.appresources_back);
            toolbar.mo1084a(this.f3120c.mo7575a((View.OnClickListener) new blr(this), "Navigate Up"));
        } else if (z) {
            toolbar.mo1096d((int) R.drawable.abc_ic_ab_back_material);
            toolbar.mo1092c((int) R.string.appresources_back);
            toolbar.mo1084a(this.f3120c.mo7575a(bls.f3116a, "Navigate Up"));
        }
        if (i != 0) {
            toolbar.mo1098e(i);
        }
    }

    /* renamed from: a */
    public final void mo2574a(Toolbar toolbar, View view, int i) {
        view.setOnApplyWindowInsetsListener(blp.f3113a);
        m3211a(toolbar, i, false);
    }

    /* renamed from: b */
    public final void mo2577b(Toolbar toolbar, View view, int i) {
        view.setOnApplyWindowInsetsListener(blq.f3114a);
        m3211a(toolbar, i, true);
    }

    /* renamed from: a */
    public final void mo2572a(int i) {
        View view = this.f3119b.f9573L;
        if (view != null) {
            gin a = gin.m10373a(view, i, -1);
            a.f11421h = this.f3121d;
            a.mo6715c();
        }
    }
}
