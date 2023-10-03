package p000;

import android.view.View;
import android.widget.ImageView;
import com.google.android.apps.photosgo.R;
import p003j$.util.Optional;

/* renamed from: dpt */
/* compiled from: PG */
public final class dpt implements cnq {

    /* renamed from: A */
    public gry f6999A;

    /* renamed from: B */
    public gry f7000B;

    /* renamed from: C */
    public gry f7001C;

    /* renamed from: D */
    public gry f7002D;

    /* renamed from: E */
    public gry f7003E;

    /* renamed from: F */
    public gry f7004F;

    /* renamed from: G */
    public gry f7005G;

    /* renamed from: H */
    public final ber f7006H = new dpr(this);

    /* renamed from: I */
    public final ber f7007I = new dps(this);

    /* renamed from: J */
    private final egu f7008J;

    /* renamed from: a */
    public final C0147fh f7009a;

    /* renamed from: b */
    public final cxi f7010b;

    /* renamed from: c */
    public final cnh f7011c;

    /* renamed from: d */
    public final blu f7012d;

    /* renamed from: e */
    public final hlz f7013e;

    /* renamed from: f */
    public final bxc f7014f;

    /* renamed from: g */
    public final hdt f7015g;

    /* renamed from: h */
    public final grx f7016h;

    /* renamed from: i */
    public final bwe f7017i;

    /* renamed from: j */
    public final dbs f7018j;

    /* renamed from: k */
    public final egp f7019k;

    /* renamed from: l */
    public final exm f7020l;

    /* renamed from: m */
    public final cny f7021m;

    /* renamed from: n */
    public final fee f7022n;

    /* renamed from: o */
    public final fdv f7023o;

    /* renamed from: p */
    public ImageView f7024p;

    /* renamed from: q */
    public ImageView f7025q;

    /* renamed from: r */
    public View f7026r;

    /* renamed from: s */
    public View f7027s;

    /* renamed from: t */
    public View f7028t;

    /* renamed from: u */
    public hto f7029u;

    /* renamed from: v */
    public hto f7030v;

    /* renamed from: w */
    public View f7031w;

    /* renamed from: x */
    public boolean f7032x = false;

    /* renamed from: y */
    public Optional f7033y = Optional.empty();

    /* renamed from: z */
    public gry f7034z;

    public dpt(cxi cxi, cnh cnh, egu egu, blu blu, hlz hlz, bxc bxc, hdt hdt, grx grx, bwe bwe, dbs dbs, C0147fh fhVar, egp egp, exm exm, cny cny, fee fee, fdv fdv) {
        egp egp2 = egp;
        this.f7009a = fhVar;
        this.f7010b = cxi;
        this.f7011c = cnh;
        this.f7008J = egu;
        this.f7012d = blu;
        this.f7013e = hlz;
        this.f7014f = bxc;
        this.f7015g = hdt;
        this.f7016h = grx;
        this.f7017i = bwe;
        this.f7018j = dbs;
        this.f7019k = egp2;
        this.f7020l = exm;
        this.f7021m = cny;
        this.f7022n = fee;
        this.f7023o = fdv;
        fhVar.mo5ad().mo64a(egp2);
    }

    /* renamed from: h */
    public final void mo2815h() {
    }

    /* renamed from: i */
    public final void mo2816i() {
    }

    /* renamed from: c */
    public final void mo4329c() {
        this.f7033y.ifPresent(dpi.f6988a);
        this.f7033y = Optional.empty();
        this.f7028t.setEnabled(true);
        this.f7032x = false;
    }

    /* renamed from: e */
    public final void mo2812e() {
        this.f7008J.mo4802a();
    }

    /* renamed from: a */
    public final void mo4327a(Throwable th) {
        cwn.m5515b(th, "AutoEnhanceFragment: Unable to save auto-enhanced image.", new Object[0]);
        if (C0643xp.m15943a(th)) {
            this.f7012d.mo2572a((int) R.string.low_storage_error);
        } else {
            this.f7012d.mo2572a((int) R.string.auto_enhance_replace_failure);
        }
        this.f7031w.setVisibility(8);
        mo4329c();
    }

    /* renamed from: b */
    public final void mo4328b() {
        hvr a = this.f7029u.iterator();
        while (a.hasNext()) {
            ((View) a.next()).setVisibility(4);
        }
        hvr a2 = this.f7030v.iterator();
        while (a2.hasNext()) {
            ((View) a2.next()).setVisibility(0);
        }
    }

    /* renamed from: a */
    public final void mo4326a() {
        hvr a = this.f7030v.iterator();
        while (a.hasNext()) {
            ((View) a.next()).setVisibility(4);
        }
        hvr a2 = this.f7029u.iterator();
        while (a2.hasNext()) {
            ((View) a2.next()).setVisibility(0);
        }
    }
}
