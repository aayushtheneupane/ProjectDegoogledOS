package p000;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.widget.LinearLayout;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.editor.ImageContainerBehavior;
import com.google.android.apps.photosgo.editor.parameters.PipelineParams;
import com.google.android.apps.photosgo.editor.presets.PresetSelectionView;
import java.util.List;
import java.util.UUID;
import p003j$.util.Optional;

/* renamed from: bvv */
/* compiled from: PG */
public final class bvv implements cnq {

    /* renamed from: a */
    public static final TimeInterpolator f3687a = new fry();

    /* renamed from: A */
    public GLSurfaceView f3688A;

    /* renamed from: B */
    public Optional f3689B = Optional.empty();

    /* renamed from: C */
    public boolean f3690C = false;

    /* renamed from: D */
    public boolean f3691D = false;

    /* renamed from: E */
    public int f3692E = 0;

    /* renamed from: F */
    public Rect f3693F = new Rect();

    /* renamed from: G */
    public Rect f3694G = new Rect();

    /* renamed from: H */
    public MenuItem f3695H;

    /* renamed from: I */
    public boolean f3696I = false;

    /* renamed from: J */
    public PresetSelectionView f3697J;

    /* renamed from: K */
    public bvz f3698K;

    /* renamed from: L */
    public LinearLayout f3699L;

    /* renamed from: M */
    public gry f3700M;

    /* renamed from: N */
    public gry f3701N;

    /* renamed from: O */
    public gry f3702O;

    /* renamed from: P */
    public gry f3703P;

    /* renamed from: Q */
    public gry f3704Q;

    /* renamed from: R */
    public gry f3705R;

    /* renamed from: S */
    public gry f3706S;

    /* renamed from: T */
    public gry f3707T;

    /* renamed from: U */
    public gry f3708U;

    /* renamed from: V */
    public gry f3709V;

    /* renamed from: W */
    public View f3710W;

    /* renamed from: X */
    public View f3711X;

    /* renamed from: Y */
    public fdr f3712Y;

    /* renamed from: Z */
    private final egu f3713Z;

    /* renamed from: aa */
    private final RectF f3714aa = new RectF();

    /* renamed from: ab */
    private final egp f3715ab;

    /* renamed from: ac */
    private final cog f3716ac;

    /* renamed from: ad */
    private Optional f3717ad = Optional.empty();

    /* renamed from: ae */
    private final ber f3718ae = new bvs(this);

    /* renamed from: af */
    private final ber f3719af = new bvt(this);

    /* renamed from: b */
    public final bul f3720b;

    /* renamed from: c */
    public final cnh f3721c;

    /* renamed from: d */
    public final buk f3722d;

    /* renamed from: e */
    public final hdt f3723e;

    /* renamed from: f */
    public final blu f3724f;

    /* renamed from: g */
    public final bxc f3725g;

    /* renamed from: h */
    public final bxq f3726h;

    /* renamed from: i */
    public final bxm f3727i;

    /* renamed from: j */
    public final bxo f3728j;

    /* renamed from: k */
    public final bxl f3729k;

    /* renamed from: l */
    public final hlz f3730l;

    /* renamed from: m */
    public final hnw f3731m;

    /* renamed from: n */
    public final dbs f3732n;

    /* renamed from: o */
    public final bwe f3733o;

    /* renamed from: p */
    public final bwr f3734p;

    /* renamed from: q */
    public final bxb f3735q;

    /* renamed from: r */
    public final grx f3736r;

    /* renamed from: s */
    public final bwx f3737s;

    /* renamed from: t */
    public final gvi f3738t;

    /* renamed from: u */
    public final exm f3739u;

    /* renamed from: v */
    public final cny f3740v;

    /* renamed from: w */
    public final fee f3741w;

    /* renamed from: x */
    public final fdv f3742x;

    /* renamed from: y */
    public final Context f3743y;

    /* renamed from: z */
    public ImageContainerBehavior f3744z;

    /* renamed from: h */
    public final void mo2815h() {
    }

    /* renamed from: i */
    public final void mo2816i() {
    }

    public bvv(bul bul, cnh cnh, buk buk, hdt hdt, bxc bxc, egu egu, blu blu, bxq bxq, bxm bxm, bxo bxo, bxl bxl, hlz hlz, hnw hnw, dbs dbs, bwe bwe, bxb bxb, bwr bwr, bwx bwx, grx grx, gvi gvi, egp egp, exm exm, cny cny, fee fee, fdv fdv, Context context) {
        cog cog;
        cxi cxi;
        Object obj;
        ceq ceq;
        bul bul2 = bul;
        buk buk2 = buk;
        egp egp2 = egp;
        this.f3720b = bul2;
        this.f3721c = cnh;
        this.f3722d = buk2;
        this.f3723e = hdt;
        this.f3725g = bxc;
        this.f3713Z = egu;
        this.f3724f = blu;
        this.f3726h = bxq;
        this.f3727i = bxm;
        this.f3728j = bxo;
        this.f3729k = bxl;
        this.f3731m = hnw;
        this.f3732n = dbs;
        this.f3733o = bwe;
        this.f3735q = bxb;
        this.f3734p = bwr;
        this.f3736r = grx;
        this.f3738t = gvi;
        this.f3730l = hlz;
        this.f3737s = bwx;
        this.f3715ab = egp2;
        this.f3739u = exm;
        this.f3740v = cny;
        this.f3741w = fee;
        this.f3742x = fdv;
        this.f3743y = context;
        int b = ggf.m10254b(bul2.f3631b);
        int i = b - 1;
        if (b != 0) {
            if (i == 0) {
                if (bul2.f3631b == 1) {
                    cxi = (cxi) bul2.f3632c;
                } else {
                    cxi = cxi.f5908x;
                }
                Uri parse = Uri.parse(cxi.f5910b);
                long j = cxi.f5918j;
                if ("image/gif".equals(cxi.f5914f)) {
                    StringBuilder sb = new StringBuilder(29);
                    sb.append("image/gif");
                    sb.append(j);
                    obj = sb.toString();
                } else {
                    obj = Long.valueOf(j);
                }
                cog = new cog(parse, new bfa(obj));
            } else if (i == 1) {
                if (bul2.f3631b == 2) {
                    ceq = (ceq) bul2.f3632c;
                } else {
                    ceq = ceq.f4197g;
                }
                cog = new cog(Uri.parse(ceq.f4200b), new bfa(UUID.randomUUID()));
            } else if (i != 2) {
                String a = ggf.m10251a(ggf.m10254b(bul2.f3631b));
                StringBuilder sb2 = new StringBuilder(a.length() + 22);
                sb2.append("Unsupported type case ");
                sb2.append(a);
                throw new IllegalArgumentException(sb2.toString());
            } else {
                throw new IllegalArgumentException("Type case not set");
            }
            this.f3716ac = cog;
            buk2.f3625a.mo64a(egp2);
            return;
        }
        throw null;
    }

    /* renamed from: a */
    public final void mo2803a() {
        cxi cxi;
        if (ggf.m10254b(this.f3720b.f3631b) == 1) {
            bul bul = this.f3720b;
            if (bul.f3631b == 1) {
                cxi = (cxi) bul.f3632c;
            } else {
                cxi = cxi.f5908x;
            }
            this.f3736r.mo6987a(grw.m10690e(this.f3715ab.mo4795a((List) hso.m12033a((Object) cyc.m5648b(cxi)))), this.f3700M);
            return;
        }
        mo2807b();
    }

    /* renamed from: a */
    public static buk m3658a(bul bul) {
        buk buk = new buk();
        ftr.m9611b(buk);
        ftr.m9610a((C0147fh) buk);
        hcl.m11210a(buk, bul);
        return buk;
    }

    /* renamed from: g */
    public final void mo2814g() {
        this.f3717ad.ifPresent(bvh.f3673a);
        this.f3717ad = Optional.empty();
    }

    /* renamed from: b */
    public final void mo2808b(Throwable th) {
        cwn.m5515b(th, "EditorFragment: Failed to save image. %s", th.getMessage());
        if (th instanceof bvu) {
            this.f3724f.mo2572a((int) R.string.editor_no_permission);
        } else if (C0643xp.m15943a(th)) {
            this.f3724f.mo2572a((int) R.string.low_storage_error);
        } else {
            this.f3724f.mo2572a((int) R.string.editor_save_failure);
        }
        mo2814g();
        this.f3696I = false;
        mo2806a(true);
    }

    /* renamed from: a */
    public final void mo2805a(Throwable th) {
        mo2808b(th);
        mo2813f();
    }

    /* renamed from: a */
    private final void m3659a(bdx bdx, ber ber) {
        ((apj) this.f3723e.mo7307a().mo1415a(this.f3716ac.f4773a).mo1854a(this.f3716ac.f4774b)).mo1426b(bdx).mo1421a(hnr.m11806a(ber));
    }

    /* renamed from: f */
    public final void mo2813f() {
        this.f3710W.setVisibility(0);
        m3659a(this.f3740v.mo3299c(), this.f3718ae);
    }

    /* renamed from: c */
    public final void mo2810c() {
        ViewParent parent;
        if (this.f3690C) {
            RectF imageScreenRect = this.f3725g.getImageScreenRect(((byc) this.f3727i).f3888c);
            if (imageScreenRect != null && !imageScreenRect.equals(this.f3714aa)) {
                this.f3714aa.set(imageScreenRect);
                bxl bxl = this.f3729k;
                bxl.f3836d.set(imageScreenRect);
                bxg bxg = bxl.f3844l;
                bxg.f3820a.set(imageScreenRect);
                C0372no noVar = bxg.f3825f;
                if (!(noVar == null || !noVar.f15296b.isEnabled() || (parent = noVar.f15297c.getParent()) == null)) {
                    AccessibilityEvent a = noVar.mo9461a(-1, 2048);
                    C0350mt.m14765a(a, 1);
                    parent.requestSendAccessibilityEvent(noVar.f15297c, a);
                }
                if (bxl.f3845m != null && !bxl.f3848p) {
                    bzy bzy = byu.f3915a;
                    if (!byp.m3789b(((byc) bxl.f3841i).f3888c).booleanValue()) {
                        cam.m3950a(((byc) bxl.f3841i).f3888c, bxl.f3838f);
                        bxl.mo2881a(bxl.f3838f);
                        bxl.f3839g.mo2884a();
                    }
                }
            }
            this.f3698K.mo2818a(((byc) this.f3727i).f3888c);
        }
    }

    /* renamed from: e */
    public final void mo2812e() {
        this.f3713Z.mo4803b();
    }

    /* renamed from: b */
    public final void mo2807b() {
        bdx bdx;
        iir g = eai.f7772e.mo8793g();
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        eai eai = (eai) g.f14318b;
        eai.f7774a |= 1;
        eai.f7775b = R.string.progress_saving_changes;
        eaj a = eaj.m7021a((eai) g.mo8770g());
        a.mo5429b(this.f3722d.mo5659r(), "progress_fragment");
        this.f3717ad = Optional.m16285of(a);
        this.f3698K.mo2819b();
        cny cny = this.f3740v;
        cnz cnz = cnz.SIZE_512MB;
        int ordinal = cny.f4752a.f4763a.ordinal();
        if (ordinal == 0 || ordinal == 1) {
            bdx = cny.mo3296a(2250, 2250).mo1857a(atc.f1597a);
        } else if (ordinal == 2 || ordinal == 3) {
            bdx = cny.mo3296a(4096, 4096).mo1857a(atc.f1597a);
        } else if (ordinal == 4) {
            bdx = cny.mo3300d();
        } else {
            String valueOf = String.valueOf(cny.f4752a.f4763a);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 19);
            sb.append("Unknown enum type: ");
            sb.append(valueOf);
            throw new IllegalStateException(sb.toString());
        }
        m3659a(bdx, this.f3719af);
    }

    /* renamed from: b */
    public final void mo2809b(boolean z) {
        if (!z) {
            this.f3688A.setOnHoverListener((View.OnHoverListener) null);
            C0340mj.m14698a((View) this.f3688A, (C0315ll) null);
            bxl bxl = this.f3729k;
            bxl.f3844l.f3825f = null;
            bxl.f3846n = -1;
            bxl.f3845m = null;
        } else {
            bxl bxl2 = this.f3729k;
            GLSurfaceView gLSurfaceView = this.f3688A;
            bxl2.f3845m = gLSurfaceView;
            bxg bxg = bxl2.f3844l;
            AccessibilityManager accessibilityManager = bxg.f3824e;
            if (accessibilityManager != null && accessibilityManager.isEnabled()) {
                bxg.f3825f = new bxf(bxg, gLSurfaceView);
            }
            C0372no noVar = this.f3729k.f3844l.f3825f;
            C0340mj.m14698a((View) this.f3688A, (C0315ll) noVar);
            this.f3688A.setOnHoverListener(new bvg(noVar));
        }
        boolean z2 = !z;
        this.f3744z.mo3345c(z2);
        ImageContainerBehavior imageContainerBehavior = this.f3744z;
        imageContainerBehavior.f4826c = z;
        imageContainerBehavior.mo3344b();
        this.f3726h.mo2887a(byu.f3915a, (Object) Boolean.valueOf(z2));
        this.f3691D = z;
        if (z && (this.f3720b.f3630a & 32) != 0) {
            this.f3726h.mo2887a(byu.f3919e, (Object) byh.m3762a(this.f3720b.f3636g));
        }
    }

    /* renamed from: a */
    public final void mo2806a(boolean z) {
        this.f3695H.setEnabled(z);
        this.f3695H.setVisible(z);
    }

    /* renamed from: a */
    public final void mo2804a(Bitmap bitmap) {
        if (this.f3722d.f9587f >= 4) {
            this.f3689B = Optional.empty();
            this.f3736r.mo6987a(this.f3733o.mo2829a(bitmap), this.f3703P);
            if (this.f3720b.f3638i) {
                this.f3697J.setVisibility(0);
                this.f3699L.setVisibility(0);
                grx grx = this.f3736r;
                bwe bwe = this.f3733o;
                grx.mo6987a(bwe.mo2831a("initializeThumbnailProcessor", new bwa(bwe, bitmap)), this.f3702O);
                return;
            }
            this.f3697J.setVisibility(8);
            this.f3699L.setVisibility(8);
            return;
        }
        this.f3689B = Optional.m16285of(bitmap);
    }

    /* renamed from: d */
    public final void mo2811d() {
        bzy bzy = bzl.f3938c;
        PipelineParams pipelineParams = ((byc) this.f3727i).f3888c;
        Rect rect = this.f3694G;
        Rect rect2 = this.f3693F;
        bzy.mo2915a(pipelineParams, new RectF((float) (rect.left + rect2.left), (float) (rect.top + rect2.top), (float) (rect.right + rect2.right), (float) (rect.bottom + rect2.bottom)));
        this.f3698K.mo2818a(((byc) this.f3727i).f3888c);
    }
}
