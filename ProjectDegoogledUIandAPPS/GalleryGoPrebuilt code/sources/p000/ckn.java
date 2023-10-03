package p000;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.p002v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.foldermanagement.creation.AddItemsToFolderFragmentPeer$1;
import com.google.android.material.button.MaterialButton;

/* renamed from: ckn */
/* compiled from: PG */
public final class ckn extends cmc implements hbf, ioe, hbd {

    /* renamed from: Z */
    private boolean f4573Z;

    /* renamed from: a */
    public final C0002ab f4574a = new C0002ab(this);

    /* renamed from: c */
    private cks f4575c;

    /* renamed from: d */
    private Context f4576d;

    @Deprecated
    public ckn() {
        new hkx(this);
        fxk.m9830b();
    }

    /* renamed from: ad */
    public final C0600w mo5ad() {
        return this.f4574a;
    }

    @Deprecated
    /* renamed from: P */
    public final Context mo2628P() {
        if (this.f4576d == null) {
            this.f4576d = new hcf((Context) this.f4664b);
        }
        return this.f4576d;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Q */
    public final /* bridge */ /* synthetic */ ftr mo3211Q() {
        return hcl.m11211d(this);
    }

    /* renamed from: k */
    public final Context mo2634k() {
        if (this.f4664b != null) {
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
            if (!this.f4573Z) {
                super.mo1832a(context);
                if (this.f4575c == null) {
                    this.f4575c = ((ckt) mo2453b()).mo2445w();
                    this.f9583V.mo64a(new hbu(this.f4574a));
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
    public final void mo165a(Bundle bundle) {
        hlq c = hnb.m11779c();
        try {
            mo7268c(bundle);
            cks R = mo2635n();
            R.f4586f = new AddItemsToFolderFragmentPeer$1(new cko(R), new ckp(R));
            R.f4585e.mo6988a(R.f4586f);
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
    public final View mo2630a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        hlq c = hnb.m11779c();
        try {
            mo7269c(layoutInflater, viewGroup, bundle);
            cks R = mo2635n();
            View inflate = layoutInflater.inflate(R.layout.add_items_to_folder_fragment, viewGroup, false);
            Toolbar toolbar = (Toolbar) inflate.findViewById(R.id.add_items_to_folder_toolbar);
            toolbar.mo1088b((int) R.string.add_items_to_folder_title);
            R.f4582b.mo2576b(toolbar);
            if (bundle == null) {
                iir g = dwi.f7492h.mo8793g();
                cxd cxd = cxd.f5884h;
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                dwi dwi = (dwi) g.f14318b;
                cxd.getClass();
                dwi.f7495b = cxd;
                dwi.f7494a |= 1;
                iir g2 = cqe.f5414d.mo8793g();
                if (g2.f14319c) {
                    g2.mo8751b();
                    g2.f14319c = false;
                }
                cqe cqe = (cqe) g2.f14318b;
                cqe.f5417b = 1;
                cqe.f5416a = 1 | cqe.f5416a;
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                dwi dwi2 = (dwi) g.f14318b;
                cqe cqe2 = (cqe) g2.mo8770g();
                cqe2.getClass();
                dwi2.f7497d = cqe2;
                dwi2.f7494a |= 4;
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                dwi dwi3 = (dwi) g.f14318b;
                dwi3.f7494a |= 2;
                dwi3.f7496c = false;
                cpi cpi = cpi.FOLDER_CREATION;
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                dwi dwi4 = (dwi) g.f14318b;
                dwi4.f7498e = cpi.f5364h;
                int i = dwi4.f7494a | 8;
                dwi4.f7494a = i;
                dwi4.f7494a = i | 32;
                dwi4.f7500g = R.layout.empty_state_view;
                dwh a = dwn.m6817a((dwi) g.mo8770g());
                R.f4581a.mo5659r().mo6419a().mo6849a((int) R.id.add_items_to_folder_main_content, (C0147fh) a).mo5244a();
                a.mo2635n().mo4525a((cqg) R);
            } else {
                R.mo3218c().mo4525a((cqg) R);
            }
            ((MaterialButton) inflate.findViewById(R.id.add_items_to_folder_move_btn)).setOnClickListener(R.f4583c.mo7575a((View.OnClickListener) new ckq(R), "move"));
            ((MaterialButton) inflate.findViewById(R.id.add_items_to_folder_copy_btn)).setOnClickListener(R.f4583c.mo7575a((View.OnClickListener) new ckr(R), "copy"));
            if (c != null) {
                c.close();
            }
            return inflate;
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
            this.f4573Z = true;
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

    /* access modifiers changed from: private */
    /* renamed from: R */
    public final cks mo2635n() {
        cks cks = this.f4575c;
        if (cks == null) {
            throw new IllegalStateException("peer() called before initialized.");
        } else if (!this.f4573Z) {
            return cks;
        } else {
            throw new IllegalStateException("peer() called after destroyed.");
        }
    }
}
