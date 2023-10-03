package p000;

import android.app.Activity;
import android.support.p002v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.home.HomeNavigationView;
import com.google.android.material.appbar.AppBarLayout;

/* renamed from: crd */
/* compiled from: PG */
public final class crd implements cnq, cnc {

    /* renamed from: A */
    public boolean f5455A = false;

    /* renamed from: B */
    public final gvc f5456B = new crc(this);

    /* renamed from: C */
    public int f5457C = 4;

    /* renamed from: D */
    private final crw f5458D;

    /* renamed from: E */
    private final egu f5459E;

    /* renamed from: a */
    public final cqt f5460a;

    /* renamed from: b */
    public final crv f5461b;

    /* renamed from: c */
    public final crx f5462c;

    /* renamed from: d */
    public final cnh f5463d;

    /* renamed from: e */
    public final hnw f5464e;

    /* renamed from: f */
    public final hlz f5465f;

    /* renamed from: g */
    public final C0395ok f5466g;

    /* renamed from: h */
    public final Class f5467h;

    /* renamed from: i */
    public final cjr f5468i;

    /* renamed from: j */
    public final cjr f5469j;

    /* renamed from: k */
    public final inw f5470k;

    /* renamed from: l */
    public final fee f5471l;

    /* renamed from: m */
    public final fdv f5472m;

    /* renamed from: n */
    public final gvi f5473n;

    /* renamed from: o */
    public Toolbar f5474o;

    /* renamed from: p */
    public ViewGroup f5475p;

    /* renamed from: q */
    public View f5476q;

    /* renamed from: r */
    public ViewStub f5477r;

    /* renamed from: s */
    public AppBarLayout f5478s;

    /* renamed from: t */
    public MenuItem f5479t;

    /* renamed from: u */
    public MenuItem f5480u;

    /* renamed from: v */
    public MenuItem f5481v;

    /* renamed from: w */
    public HomeNavigationView f5482w;

    /* renamed from: x */
    public fdr f5483x;

    /* renamed from: y */
    public fdr f5484y;

    /* renamed from: z */
    public fdr f5485z;

    public crd(Activity activity, cqt cqt, crv crv, crw crw, crx crx, cnh cnh, hnw hnw, hlz hlz, cjr cjr, cjr cjr2, Class cls, inw inw, fee fee, fdv fdv, gvi gvi, egu egu) {
        this.f5466g = (C0395ok) activity;
        this.f5460a = cqt;
        this.f5461b = crv;
        this.f5458D = crw;
        this.f5462c = crx;
        this.f5463d = cnh;
        this.f5464e = hnw;
        this.f5465f = hlz;
        this.f5467h = cls;
        this.f5468i = cjr;
        this.f5469j = cjr2;
        this.f5470k = inw;
        this.f5471l = fee;
        this.f5472m = fdv;
        this.f5473n = gvi;
        this.f5459E = egu;
    }

    /* renamed from: h */
    public final void mo2815h() {
    }

    /* renamed from: i */
    public final void mo2816i() {
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo3775a(View view) {
        int i;
        C0147fh b = this.f5460a.mo5659r().mo6432b((int) R.id.main_content);
        if (b != null) {
            int id = view.getId();
            int i2 = this.f5457C;
            int i3 = i2 - 1;
            if (i2 != 0) {
                int i4 = 2;
                if (i3 == 0 || i3 == 1) {
                    i = R.id.home_photos;
                } else if (i3 == 2) {
                    i = R.id.home_folders;
                } else {
                    StringBuilder sb = new StringBuilder(42);
                    sb.append("Unknown HomeFragmentTab value: ");
                    sb.append(i3);
                    throw new IllegalArgumentException(sb.toString());
                }
                if (id != i) {
                    if (view.getId() == R.id.home_photos) {
                        this.f5472m.mo5551a(fdu.m8653a(), view);
                    } else if (view.getId() == R.id.home_folders) {
                        this.f5472m.mo5551a(fdu.m8653a(), view);
                    } else {
                        cwn.m5510a("Unexpected menu item in bottom nav. Skipping interaction log.", new Object[0]);
                    }
                    crw crw = this.f5458D;
                    int id2 = view.getId();
                    if (id2 != R.id.home_photos) {
                        if (id2 == R.id.home_folders) {
                            i4 = 3;
                        } else {
                            StringBuilder sb2 = new StringBuilder(43);
                            sb2.append("Unknown selectedTabResId value: ");
                            sb2.append(id2);
                            throw new IllegalArgumentException(sb2.toString());
                        }
                    }
                    crw.f5526c = i4;
                    crw.f5524a.mo7096a(ife.m12820a((Object) null), (Object) "HOME_FRAGMENT_DATA_SERVICE");
                    cwn.m5509a(crw.f5525b.f8300a.mo6360a(new ehi(i4), idh.f13918a), "HomeFragment: Failed to update user preferences data.", new Object[0]);
                } else if (b instanceof hbf) {
                    hbf hbf = (hbf) b;
                    (hbf.mo2635n() instanceof crl ? (crl) hbf.mo2635n() : cra.f5452a).mo2710k();
                    this.f5478s.mo3586f();
                } else {
                    String valueOf = String.valueOf(b);
                    StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf).length() + 26);
                    sb3.append("Fragment must have a peer ");
                    sb3.append(valueOf);
                    throw new IllegalArgumentException(sb3.toString());
                }
            } else {
                throw null;
            }
        }
    }

    /* renamed from: a */
    public final void mo3776a(C0147fh fhVar, String str) {
        if (this.f5460a.mo5659r().mo6418a(str) == null) {
            C0182gn a = this.f5460a.mo5659r().mo6419a();
            a.mo6848a(cnf.SLIDE_UP.f4724d, cnf.SLIDE_UP.f4725e, cnf.SLIDE_UP.f4726f, cnf.SLIDE_UP.f4727g);
            a.mo6850a(R.id.main_content, fhVar, str);
            a.mo5244a();
        }
    }

    /* renamed from: j */
    public final void mo2638j() {
        this.f5478s.setVisibility(0);
    }

    /* renamed from: b */
    public final void mo2637b() {
        this.f5478s.setVisibility(4);
    }

    /* renamed from: e */
    public final void mo2812e() {
        this.f5459E.mo4804c();
    }

    /* renamed from: b */
    public final void mo3778b(boolean z) {
        int i;
        this.f5455A = z;
        gco gco = (gco) this.f5474o.getLayoutParams();
        int i2 = gco.f10949a;
        if (!z) {
            i = i2 & -2;
        } else {
            i = i2 | 1;
        }
        gco.f10949a = i;
        this.f5474o.setLayoutParams(gco);
        ((abm) this.f5475p.getLayoutParams()).mo103a((abj) z ? new AppBarLayout.ScrollingViewBehavior() : null);
    }

    /* renamed from: c */
    private final void m5295c(boolean z) {
        MenuItem menuItem = this.f5479t;
        if (menuItem != null) {
            menuItem.setVisible(z);
        }
        MenuItem menuItem2 = this.f5480u;
        if (menuItem2 != null) {
            menuItem2.setVisible(z);
        }
    }

    /* renamed from: a */
    public final void mo3777a(boolean z) {
        mo3778b(!z);
        int i = this.f5457C;
        if (i == 0) {
            throw null;
        } else if (i == 2 && !z) {
            m5295c(true);
        } else {
            m5295c(false);
        }
    }
}
