package p000;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.apps.photosgo.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

/* renamed from: cla */
/* compiled from: PG */
public final class cla extends cmd implements hbf, ioe, hbd {

    /* renamed from: Z */
    private boolean f4594Z;

    /* renamed from: a */
    public final C0002ab f4595a = new C0002ab(this);

    /* renamed from: c */
    private clr f4596c;

    /* renamed from: d */
    private Context f4597d;

    @Deprecated
    public cla() {
        new hkx(this);
        fxk.m9830b();
    }

    /* renamed from: ad */
    public final C0600w mo5ad() {
        return this.f4595a;
    }

    @Deprecated
    /* renamed from: P */
    public final Context mo2628P() {
        if (this.f4597d == null) {
            this.f4597d = new hcf((Context) this.f4667b);
        }
        return this.f4597d;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Q */
    public final /* bridge */ /* synthetic */ ftr mo3229Q() {
        return hcl.m11211d(this);
    }

    /* renamed from: a */
    static cla m4489a(clb clb) {
        cla cla = new cla();
        ftr.m9611b(cla);
        ftr.m9610a((C0147fh) cla);
        hcl.m11210a(cla, clb);
        return cla;
    }

    /* renamed from: k */
    public final Context mo2634k() {
        if (this.f4667b != null) {
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
            if (!this.f4594Z) {
                super.mo1832a(context);
                if (this.f4596c == null) {
                    this.f4596c = ((clu) mo2453b()).mo2446x();
                    this.f9583V.mo64a(new hbu(this.f4595a));
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
            clr R = mo2635n();
            R.f4635k.mo6988a(R.f4638n);
            R.f4635k.mo6988a(R.f4640p);
            R.f4635k.mo6988a(R.f4639o);
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
            clr R = mo2635n();
            R.f4646v = true;
            R.f4647w = false;
            R.f4641q = layoutInflater.inflate(R.layout.folder_creation_fragment, viewGroup, false);
            Toolbar toolbar = (Toolbar) R.f4641q.findViewById(R.id.folder_creation_toolbar);
            toolbar.mo1088b((int) R.string.folder_creation_title);
            R.f4629e.mo2573a(toolbar);
            MaterialButton materialButton = (MaterialButton) R.f4641q.findViewById(R.id.folder_creation_cancel_button);
            materialButton.setOnClickListener(R.f4636l.mo7575a((View.OnClickListener) new cll(R), "cancel creation"));
            MaterialButton materialButton2 = (MaterialButton) R.f4641q.findViewById(R.id.folder_creation_create_button);
            materialButton2.setOnClickListener(R.f4636l.mo7575a((View.OnClickListener) new clm(R), "create folder"));
            R.f4643s = (TextInputLayout) R.f4641q.findViewById(R.id.folder_creation_input_layout);
            R.f4642r = (TextInputEditText) R.f4641q.findViewById(R.id.folder_creation_input_edit_text);
            R.f4642r.setImeOptions(2);
            R.f4642r.addTextChangedListener(new hlw(R.f4636l, new clq(R, materialButton2), "folder name changed"));
            R.f4642r.setOnEditorActionListener(new hlr(R.f4636l, new cln(R), "folder name entered"));
            gwb c2 = gwd.m10934c();
            c2.mo7127a((gwe) new cly(layoutInflater));
            c2.mo7128a(clo.f4618a);
            R.f4644t = c2.mo7126a();
            RecyclerView recyclerView = (RecyclerView) R.f4641q.findViewById(R.id.folder_creation_volume_list);
            recyclerView.getContext();
            recyclerView.setLayoutManager(new C0607wg());
            recyclerView.setAdapter(R.f4644t);
            if (bundle != null) {
                cmg cmg = R.f4630f;
                cmg.f4672c = hvb.f13454a;
                cmg.mo3254a(bundle.getString("volume_chooser_chosen_volume", cml.f4678a));
                if (bundle.containsKey("folder_name_input_saving_key")) {
                    ((TextInputEditText) ife.m12885c((Object) R.f4642r)).setText(bundle.getString("folder_name_input_saving_key"));
                }
            }
            R.f4645u = hso.m12036a(ckx.m4480a((View) materialButton), ckx.m4480a((View) materialButton2), ckx.m4480a((View) R.f4642r), R.f4630f);
            R.mo3247e();
            R.f4634j.mo7113a(R.f4628d.f4683f, guy.DONT_CARE, R.f4648x);
            View view = R.f4641q;
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

    /* renamed from: c */
    public final void mo1834c() {
        hlq c = hnb.m11779c();
        try {
            mo7265Y();
            this.f4594Z = true;
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

    /* renamed from: e */
    public final void mo168e(Bundle bundle) {
        super.mo168e(bundle);
        clr R = mo2635n();
        bundle.putString("folder_name_input_saving_key", clr.m4510a(((TextInputEditText) ife.m12885c((Object) R.f4642r)).getText()));
        bundle.putString("volume_chooser_chosen_volume", R.f4630f.f4671b);
    }

    /* renamed from: a */
    public final void mo2632a(View view, Bundle bundle) {
        hlq c = hnb.m11779c();
        try {
            hok.m11838a((Context) mo5653m()).f13171d = view;
            clr R = mo2635n();
            ihg.m13038a((C0147fh) this, cmh.class, (hol) new cls(R));
            ihg.m13038a((C0147fh) this, ckm.class, (hol) new clt(R));
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
    public final clr mo2635n() {
        clr clr = this.f4596c;
        if (clr == null) {
            throw new IllegalStateException("peer() called before initialized.");
        } else if (!this.f4594Z) {
            return clr;
        } else {
            throw new IllegalStateException("peer() called after destroyed.");
        }
    }
}
