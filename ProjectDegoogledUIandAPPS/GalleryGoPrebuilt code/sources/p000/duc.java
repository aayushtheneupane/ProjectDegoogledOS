package p000;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.p002v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.peoplegrid.PeopleGridView;
import java.util.ArrayList;
import p003j$.util.Optional;

/* renamed from: duc */
/* compiled from: PG */
public final class duc extends dva implements hbf, ioe, hbd {

    /* renamed from: Z */
    private boolean f7383Z;

    /* renamed from: b */
    private dug f7384b;

    /* renamed from: c */
    private Context f7385c;

    /* renamed from: d */
    private final C0002ab f7386d = new C0002ab(this);

    @Deprecated
    public duc() {
        new hkx(this);
        fxk.m9830b();
    }

    /* renamed from: ad */
    public final C0600w mo5ad() {
        return this.f7386d;
    }

    @Deprecated
    /* renamed from: P */
    public final Context mo2628P() {
        if (this.f7385c == null) {
            this.f7385c = new hcf((Context) this.f7443a);
        }
        return this.f7385c;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Q */
    public final /* bridge */ /* synthetic */ ftr mo4456Q() {
        return hcl.m11211d(this);
    }

    /* renamed from: k */
    public final Context mo2634k() {
        if (this.f7443a != null) {
            return mo2628P();
        }
        return null;
    }

    /* renamed from: a */
    public final void mo2631a(Activity activity) {
        hlq c = hnb.m11779c();
        try {
            super.mo2631a(activity);
            if (c != null) {
                c.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* renamed from: a */
    public final void mo1832a(Context context) {
        hlq c = hnb.m11779c();
        try {
            if (!this.f7383Z) {
                super.mo1832a(context);
                if (this.f7384b == null) {
                    this.f7384b = ((duj) mo2453b()).mo2384F();
                    this.f9583V.mo64a(new hbu(this.f7386d));
                }
                if (c != null) {
                    c.close();
                    return;
                }
                return;
            }
            throw new IllegalStateException("A Fragment cannot be attached more than once. Instead, create a new Fragment instance.");
        } catch (ClassCastException e) {
            throw new IllegalStateException("Missing entry point. If you're in a test with explicit entry points specified in your @TestRoot, check that you're not missing the one for this class.", e);
        } catch (Throwable th) {
            if (c != null) {
                try {
                    c.close();
                } catch (Throwable th2) {
                    ifn.m12935a(th, th2);
                }
            }
            throw th;
        }
    }

    /* renamed from: a */
    public final View mo2630a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        hlq c = hnb.m11779c();
        try {
            mo7269c(layoutInflater, viewGroup, bundle);
            dug R = mo2635n();
            R.f7401k.mo3758a((cqg) R);
            R.f7404n = layoutInflater.inflate(R.layout.people_grid_fragment, viewGroup, false);
            R.f7405o = (PeopleGridView) R.f7404n.findViewById(R.id.people_grid);
            duw a = R.f7405o.mo2635n();
            int i = a.f7433c;
            ArrayList arrayList = new ArrayList(i);
            int i2 = 0;
            while (i2 < i * 3) {
                Optional empty = Optional.empty();
                if (empty != null) {
                    arrayList.add(new dtz(empty));
                    i2++;
                } else {
                    throw null;
                }
            }
            arrayList.add(ede.m7257a(new ede()));
            a.mo4464a(arrayList);
            Toolbar toolbar = (Toolbar) R.f7404n.findViewById(R.id.people_grid_toolbar);
            toolbar.mo1088b(R.f7391a.f3185e);
            toolbar.mo1098e(R.menu.people_grid_top_menu);
            toolbar.f1030q = R.f7392b.mo7620a(new dud(R), "Menu Item Selected");
            R.f7395e.mo2573a(toolbar);
            gvi gvi = R.f7396f;
            dvf dvf = R.f7393c;
            gvi.mo7113a(dvf.f7451b.mo7085a(new dvd(dvf), guj.m10828a("people_grid_datasource_key", "PERSON_SRC")), guy.DONT_CARE, R.f7407q);
            ((fea) R.f7400j.f9364c.mo5563a(74315).mo5513a(ffh.f9451a)).mo5560a(R.f7404n);
            View view = R.f7404n;
            if (view != null) {
                if (c != null) {
                    c.close();
                }
                return view;
            }
            throw new NullPointerException("Fragment cannot use Event annotations with null view!");
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* renamed from: f */
    public final void mo212f() {
        hlq c = hnb.m11779c();
        try {
            mo7263W();
            dug R = mo2635n();
            R.f7401k.mo3761b((cqg) R);
            R.f7402l.mo3732b();
            R.f7401k.mo3762c();
            if (c != null) {
                c.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* renamed from: c */
    public final void mo1834c() {
        hlq c = hnb.m11779c();
        try {
            mo7265Y();
            this.f7383Z = true;
            if (c != null) {
                c.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* renamed from: b */
    public final LayoutInflater mo2633b(Bundle bundle) {
        hlq c = hnb.m11779c();
        try {
            LayoutInflater from = LayoutInflater.from(new hcf(LayoutInflater.from(new fts(mo5627H(), (C0147fh) this, true))));
            if (c != null) {
                c.close();
            }
            return from;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* renamed from: a */
    public final void mo2632a(View view, Bundle bundle) {
        hlq c = hnb.m11779c();
        try {
            hok.m11838a((Context) mo5653m()).f13171d = view;
            dug R = mo2635n();
            ihg.m13038a((C0147fh) this, duz.class, (hol) new duh(R));
            ihg.m13038a((C0147fh) this, dub.class, (hol) new dui(R));
            mo7267b(view, bundle);
            if (c != null) {
                c.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* access modifiers changed from: private */
    /* renamed from: R */
    public final dug mo2635n() {
        dug dug = this.f7384b;
        if (dug == null) {
            throw new IllegalStateException("peer() called before initialized.");
        } else if (!this.f7383Z) {
            return dug;
        } else {
            throw new IllegalStateException("peer() called after destroyed.");
        }
    }
}
