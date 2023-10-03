package p000;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.p002v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.LinearLayout;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.editor.EditorFragmentPeer$10;
import com.google.android.apps.photosgo.editor.EditorFragmentPeer$11;
import com.google.android.apps.photosgo.editor.EditorFragmentPeer$12;
import com.google.android.apps.photosgo.editor.EditorFragmentPeer$3;
import com.google.android.apps.photosgo.editor.EditorFragmentPeer$4;
import com.google.android.apps.photosgo.editor.EditorFragmentPeer$5;
import com.google.android.apps.photosgo.editor.EditorFragmentPeer$6;
import com.google.android.apps.photosgo.editor.EditorFragmentPeer$7;
import com.google.android.apps.photosgo.editor.EditorFragmentPeer$8;
import com.google.android.apps.photosgo.editor.EditorFragmentPeer$9;
import com.google.android.apps.photosgo.editor.ImageContainerBehavior;
import com.google.android.apps.photosgo.editor.presets.PresetSelectionView;

/* renamed from: buk */
/* compiled from: PG */
public final class buk extends bxd implements hbf, ioe, hbd {

    /* renamed from: Z */
    private boolean f3624Z;

    /* renamed from: a */
    public final C0002ab f3625a = new C0002ab(this);

    /* renamed from: c */
    private bvv f3626c;

    /* renamed from: d */
    private Context f3627d;

    @Deprecated
    public buk() {
        new hkx(this);
        fxk.m9830b();
    }

    /* renamed from: ad */
    public final C0600w mo5ad() {
        return this.f3625a;
    }

    @Deprecated
    /* renamed from: P */
    public final Context mo2628P() {
        if (this.f3627d == null) {
            this.f3627d = new hcf((Context) this.f3815b);
        }
        return this.f3627d;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Q */
    public final /* bridge */ /* synthetic */ ftr mo2773Q() {
        return hcl.m11211d(this);
    }

    /* renamed from: k */
    public final Context mo2634k() {
        if (this.f3815b != null) {
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
            if (!this.f3624Z) {
                super.mo1832a(context);
                if (this.f3626c == null) {
                    this.f3626c = ((bvx) mo2453b()).mo2441s();
                    this.f9583V.mo64a(new hbu(this.f3625a));
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
            bvv R = mo2635n();
            R.f3700M = new EditorFragmentPeer$3(new bum(R), new bux(R));
            R.f3736r.mo6988a(R.f3700M);
            R.f3701N = new EditorFragmentPeer$4(new bvi(R), new bvk(R));
            R.f3736r.mo6988a(R.f3701N);
            R.f3702O = new EditorFragmentPeer$5(new bvl(R), bvm.f3678a);
            R.f3736r.mo6988a(R.f3702O);
            R.f3703P = new EditorFragmentPeer$6(new bvn(R), bvo.f3680a);
            R.f3736r.mo6988a(R.f3703P);
            R.f3704Q = new EditorFragmentPeer$7(new bvp(R), bvq.f3682a);
            R.f3736r.mo6988a(R.f3704Q);
            R.f3706S = new EditorFragmentPeer$8(new bun(R), new buo(R));
            R.f3736r.mo6988a(R.f3706S);
            R.f3707T = new EditorFragmentPeer$9(new bup(R), new buq(R));
            R.f3736r.mo6988a(R.f3707T);
            R.f3705R = new EditorFragmentPeer$10(new bur(R), new bus(R));
            R.f3736r.mo6988a(R.f3705R);
            R.f3708U = new EditorFragmentPeer$11(new but(R), new buu(R));
            R.f3736r.mo6988a(R.f3708U);
            R.f3709V = new EditorFragmentPeer$12(new buv(R), buw.f3649a);
            R.f3736r.mo6988a(R.f3709V);
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
        cxi cxi;
        String str;
        hlq c = hnb.m11779c();
        try {
            mo7269c(layoutInflater, viewGroup, bundle);
            bvv R = mo2635n();
            R.f3711X = layoutInflater.inflate(R.layout.editor_layout, viewGroup, false);
            R.f3688A = (GLSurfaceView) R.f3711X.findViewById(R.id.editor_surface);
            SurfaceHolder holder = R.f3688A.getHolder();
            if (holder != null) {
                holder.setFormat(-3);
                R.f3688A.setEGLContextClientVersion(2);
                R.f3688A.setEGLConfigChooser(8, 8, 8, 8, 0, 0);
                R.f3688A.setPreserveEGLContextOnPause(false);
                R.f3698K = new bvz(R.f3688A, R.f3725g);
                R.f3688A.setRenderer(R.f3698K);
                R.f3688A.setRenderMode(0);
                R.f3688A.setOnApplyWindowInsetsListener(new bvj(R));
                ViewGroup.LayoutParams layoutParams = R.f3688A.getLayoutParams();
                if (layoutParams instanceof abm) {
                    abj abj = ((abm) layoutParams).f80a;
                    if (abj instanceof ImageContainerBehavior) {
                        R.f3744z = (ImageContainerBehavior) abj;
                        R.f3744z.f4824a = hso.m12034a((Object) Integer.valueOf(R.id.editor_bottom_bar), (Object) Integer.valueOf(R.id.preset_selection_view));
                        R.f3744z.f4825b = hso.m12033a((Object) Integer.valueOf(R.id.editor_appbar));
                        R.f3744z.mo3345c(true);
                        ImageContainerBehavior imageContainerBehavior = R.f3744z;
                        imageContainerBehavior.f4827d = new buy(R);
                        imageContainerBehavior.mo3344b();
                        View findViewById = R.f3711X.findViewById(R.id.editor_appbar);
                        Toolbar toolbar = (Toolbar) R.f3711X.findViewById(R.id.editor_top_toolbar);
                        if ((R.f3720b.f3630a & 4) != 0) {
                            R.f3724f.mo2577b(toolbar, findViewById, R.menu.top_bar_menu);
                        } else {
                            R.f3724f.mo2574a(toolbar, findViewById, (int) R.menu.top_bar_menu);
                        }
                        toolbar.mo1096d((int) R.drawable.editor_top_bar_close_button);
                        R.f3695H = toolbar.mo1099f().findItem(R.id.editor_top_bar_save);
                        R.f3695H.setTitle(!R.f3720b.f3633d ? R.string.save_copy : R.string.editor_done);
                        R.f3695H.getActionView().setOnClickListener(new bva(R, R.f3731m.mo7620a(new buz(R), "Menu item selected")));
                        R.mo2806a(false);
                        R.f3724f.mo2575a(R.f3711X.findViewById(R.id.editor_bottom_bar));
                        R.f3711X.findViewById(R.id.editor_rotate_button).setOnClickListener(R.f3730l.mo7575a((View.OnClickListener) new bvb(R), "Rotate"));
                        R.f3711X.findViewById(R.id.editor_crop_button).setOnClickListener(R.f3730l.mo7575a((View.OnClickListener) new bvc(R), "Toggle crop"));
                        bwx bwx = R.f3737s;
                        gvi gvi = R.f3738t;
                        bul bul = R.f3720b;
                        ViewStub viewStub = (ViewStub) R.f3711X.findViewById(R.id.editor_exit_button);
                        dhk dhk = bwx.f3791c;
                        if (bul.f3631b == 1) {
                            cxi = (cxi) bul.f3632c;
                        } else {
                            cxi = cxi.f5908x;
                        }
                        if ((cxi.f5909a & 65536) != 0) {
                            str = (bul.f3631b == 1 ? (cxi) bul.f3632c : cxi.f5908x).f5926r;
                        } else {
                            str = null;
                        }
                        gvi.mo7113a(new dhj(dhk, str), guy.DONT_CARE, new bwv(bwx, viewStub, bul));
                        R.f3711X.setOnTouchListener(R.f3729k);
                        R.f3697J = (PresetSelectionView) R.f3711X.findViewById(R.id.preset_selection_view);
                        R.f3699L = (LinearLayout) R.f3711X.findViewById(R.id.editor_bottom_bar);
                        R.f3712Y = fhg.m8901a(((fea) R.f3741w.f9364c.mo5563a(74311).mo5513a(ffh.f9451a)).mo5560a(R.f3711X), 74872).mo5510a();
                        R.f3710W = R.f3711X.findViewById(R.id.progress_indicator);
                        View view = R.f3711X;
                        if (view != null) {
                            if (c != null) {
                                c.close();
                            }
                            return view;
                        }
                        throw new NullPointerException("Fragment cannot use Event annotations with null view!");
                    }
                    throw new IllegalArgumentException("The view is not associated with ImageContainerBehavior");
                }
                throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
            }
            throw new IllegalStateException("Failed to get the surface holder");
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
            this.f3624Z = true;
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

    /* renamed from: w */
    public final void mo2669w() {
        hlq c = hnb.m11779c();
        try {
            mo7261U();
            bvv R = mo2635n();
            R.mo2806a(false);
            R.f3726h.mo2888b(new bvf(R));
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
    public final void mo2705a(int i, String[] strArr, int[] iArr) {
        super.mo2705a(i, strArr, iArr);
        bvv R = mo2635n();
        if (i == 1) {
            if (dvg.m6745a(R.f3722d.mo2634k(), new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"})) {
                R.mo2803a();
            } else {
                R.mo2808b((Throwable) new bvu());
            }
        } else {
            StringBuilder sb = new StringBuilder(33);
            sb.append("Invalid Request Code: ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        }
    }

    /* renamed from: v */
    public final void mo2668v() {
        hlq c = hnb.m11779c();
        try {
            mo7260T();
            bvv R = mo2635n();
            if (!R.f3690C) {
                R.mo2813f();
            }
            R.f3689B.ifPresent(new bvd(R));
            R.mo2806a(!R.f3696I);
            R.f3726h.mo2886a(new bve(R));
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
    public final void mo2632a(View view, Bundle bundle) {
        hlq c = hnb.m11779c();
        try {
            hok.m11838a((Context) mo5653m()).f13171d = view;
            ihg.m13038a((C0147fh) this, cba.class, (hol) new bvw(mo2635n()));
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
    public final bvv mo2635n() {
        bvv bvv = this.f3626c;
        if (bvv == null) {
            throw new IllegalStateException("peer() called before initialized.");
        } else if (!this.f3624Z) {
            return bvv;
        } else {
            throw new IllegalStateException("peer() called after destroyed.");
        }
    }
}
