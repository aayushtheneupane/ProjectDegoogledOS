package p000;

import android.app.Activity;
import android.support.p002v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.apps.photosgo.R;
import com.google.android.material.appbar.AppBarLayout;

/* renamed from: btw */
/* compiled from: PG */
public final class btw implements cnc {

    /* renamed from: a */
    public final bts f3579a;

    /* renamed from: b */
    public final cnh f3580b;

    /* renamed from: c */
    public final btt f3581c;

    /* renamed from: d */
    public final cqe f3582d;

    /* renamed from: e */
    public final C0395ok f3583e;

    /* renamed from: f */
    public final bui f3584f;

    /* renamed from: g */
    public final gvi f3585g;

    /* renamed from: h */
    public final fee f3586h;

    /* renamed from: i */
    public final fdv f3587i;

    /* renamed from: j */
    public ViewGroup f3588j;

    /* renamed from: k */
    public AppBarLayout f3589k;

    /* renamed from: l */
    public MenuItem f3590l;

    /* renamed from: m */
    public MenuItem f3591m;

    /* renamed from: n */
    public fdr f3592n;

    /* renamed from: o */
    public fdr f3593o;

    /* renamed from: p */
    private final hnw f3594p;

    /* renamed from: q */
    private Toolbar f3595q;

    public btw(bts bts, cnh cnh, hnw hnw, btt btt, Activity activity, bui bui, gvi gvi, fee fee, fdv fdv) {
        this.f3579a = bts;
        this.f3580b = cnh;
        this.f3594p = hnw;
        this.f3581c = btt;
        cqe cqe = btt.f3572c;
        this.f3582d = cqe == null ? cqe.f5414d : cqe;
        this.f3583e = (C0395ok) activity;
        this.f3584f = bui;
        this.f3585g = gvi;
        this.f3586h = fee;
        this.f3587i = fdv;
    }

    /* renamed from: a */
    public final C0383nz mo2759a(View view) {
        this.f3579a.mo5629J();
        AppBarLayout appBarLayout = (AppBarLayout) view.findViewById(R.id.single_device_folder_appbar);
        this.f3589k = appBarLayout;
        appBarLayout.mo3587g();
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.single_device_folder_top_toolbar);
        this.f3595q = toolbar;
        this.f3583e.mo9531a(toolbar);
        C0383nz nzVar = (C0383nz) ife.m12898e((Object) this.f3583e.mo9534f());
        nzVar.mo9488a(true);
        this.f3595q.f1030q = this.f3594p.mo7620a(new btu(this), "Menu Item Selected");
        return nzVar;
    }

    /* renamed from: j */
    public final void mo2638j() {
        this.f3589k.setVisibility(0);
    }

    /* renamed from: b */
    public final void mo2637b() {
        this.f3589k.setVisibility(4);
    }

    /* renamed from: a */
    public final void mo2760a(boolean z) {
        int i;
        AppBarLayout.ScrollingViewBehavior scrollingViewBehavior;
        gco gco = (gco) this.f3595q.getLayoutParams();
        int i2 = gco.f10949a;
        if (z) {
            i = i2 & -2;
        } else {
            i = i2 | 1;
        }
        gco.f10949a = i;
        this.f3595q.setLayoutParams(gco);
        abm abm = (abm) this.f3588j.getLayoutParams();
        if (!z) {
            scrollingViewBehavior = new AppBarLayout.ScrollingViewBehavior();
        } else {
            scrollingViewBehavior = null;
        }
        abm.mo103a((abj) scrollingViewBehavior);
        MenuItem menuItem = this.f3590l;
        if (menuItem != null) {
            menuItem.setVisible(!z);
        }
        MenuItem menuItem2 = this.f3591m;
        if (menuItem2 != null) {
            menuItem2.setVisible(!z);
        }
    }
}
