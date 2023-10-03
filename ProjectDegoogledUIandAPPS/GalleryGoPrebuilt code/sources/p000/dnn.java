package p000;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.oneup.OneUpActionsView;
import com.google.android.apps.photosgo.oneup.OneUpPagerView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import p003j$.util.Optional;
import p003j$.util.function.Consumer;

/* renamed from: dnn */
/* compiled from: PG */
public final class dnn implements cnq {

    /* renamed from: a */
    public static final String[] f6870a = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};

    /* renamed from: A */
    public final cjr f6871A;

    /* renamed from: B */
    public final bnb f6872B;

    /* renamed from: C */
    public final gry f6873C = new dnk(this);

    /* renamed from: D */
    public final gry f6874D = new dnl(this);

    /* renamed from: E */
    public final gry f6875E = new dnm(this);

    /* renamed from: F */
    public hso f6876F;

    /* renamed from: G */
    public OneUpPagerView f6877G;

    /* renamed from: H */
    public dog f6878H;

    /* renamed from: I */
    public Menu f6879I;

    /* renamed from: J */
    public OneUpActionsView f6880J;

    /* renamed from: K */
    public dot f6881K;

    /* renamed from: L */
    public boolean f6882L = false;

    /* renamed from: M */
    public boolean f6883M = false;

    /* renamed from: N */
    public Optional f6884N = Optional.empty();

    /* renamed from: O */
    public Optional f6885O = Optional.empty();

    /* renamed from: P */
    private Optional f6886P = Optional.empty();

    /* renamed from: Q */
    private final gvc f6887Q = new dnj(this);

    /* renamed from: R */
    private final dqj f6888R;

    /* renamed from: S */
    private final gvi f6889S;

    /* renamed from: T */
    private final egu f6890T;

    /* renamed from: U */
    private final cjo f6891U;

    /* renamed from: V */
    private final dgp f6892V;

    /* renamed from: b */
    public Optional f6893b = Optional.empty();

    /* renamed from: c */
    public Map f6894c = hvb.f13454a;

    /* renamed from: d */
    public final dme f6895d;

    /* renamed from: e */
    public final C0395ok f6896e;

    /* renamed from: f */
    public final dmb f6897f;

    /* renamed from: g */
    public final cnh f6898g;

    /* renamed from: h */
    public final dqx f6899h;

    /* renamed from: i */
    public final inw f6900i;

    /* renamed from: j */
    public final dku f6901j;

    /* renamed from: k */
    public final dlh f6902k;

    /* renamed from: l */
    public final iij f6903l;

    /* renamed from: m */
    public final blu f6904m;

    /* renamed from: n */
    public final hlz f6905n;

    /* renamed from: o */
    public final hnw f6906o;

    /* renamed from: p */
    public final cze f6907p;

    /* renamed from: q */
    public final grx f6908q;

    /* renamed from: r */
    public final iij f6909r;

    /* renamed from: s */
    public final egp f6910s;

    /* renamed from: t */
    public final Class f6911t;

    /* renamed from: u */
    public final blc f6912u;

    /* renamed from: v */
    public final inw f6913v;

    /* renamed from: w */
    public final fee f6914w;

    /* renamed from: x */
    public final Optional f6915x;

    /* renamed from: y */
    public final dou f6916y;

    /* renamed from: z */
    public final cjr f6917z;

    public dnn(dme dme, Activity activity, dmb dmb, cnh cnh, inw inw, blc blc, dqj dqj, gvi gvi, inw inw2, dqx dqx, dku dku, dlh dlh, iij iij, egu egu, blu blu, hnw hnw, hlz hlz, cze cze, grx grx, iij iij2, egp egp, cjo cjo, Class cls, fee fee, Optional optional, dgp dgp, dou dou, cjr cjr, cjr cjr2, bnb bnb) {
        dmb dmb2 = dmb;
        egp egp2 = egp;
        this.f6895d = dme;
        this.f6896e = (C0395ok) activity;
        this.f6897f = dmb2;
        this.f6898g = cnh;
        this.f6900i = inw2;
        this.f6913v = inw;
        this.f6912u = blc;
        this.f6888R = dqj;
        this.f6889S = gvi;
        this.f6899h = dqx;
        this.f6901j = dku;
        this.f6902k = dlh;
        this.f6903l = iij;
        this.f6890T = egu;
        this.f6904m = blu;
        this.f6906o = hnw;
        this.f6905n = hlz;
        this.f6907p = cze;
        this.f6908q = grx;
        this.f6909r = iij2;
        this.f6910s = egp2;
        this.f6891U = cjo;
        this.f6911t = cls;
        this.f6914w = fee;
        this.f6915x = optional;
        this.f6892V = dgp;
        this.f6916y = dou;
        this.f6917z = cjr;
        this.f6871A = cjr2;
        this.f6872B = bnb;
        dmb2.f6813a.mo64a(egp2);
    }

    /* renamed from: a */
    public static dmb m6369a(dme dme) {
        dmb dmb = new dmb();
        ftr.m9611b(dmb);
        ftr.m9610a((C0147fh) dmb);
        hcl.m11210a(dmb, dme);
        return dmb;
    }

    /* renamed from: d */
    public final void mo4276d(cxi cxi) {
        if (dvg.m6744a((Context) this.f6896e)) {
            m6370a(cxi, dmd.EDIT_IN);
            return;
        }
        dku dku = this.f6901j;
        Optional a = dku.mo4181a(cxi, Optional.empty());
        if (a.isPresent()) {
            List<ResolveInfo> a2 = dku.mo4182a((Intent) a.get());
            if (!a2.isEmpty()) {
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                for (ResolveInfo resolveInfo : a2) {
                    if (dku.mo4185a(resolveInfo)) {
                        arrayList.add(dku.m6262b(resolveInfo));
                    } else {
                        arrayList2.add(resolveInfo);
                    }
                }
                if (arrayList2.size() > 1) {
                    String string = dku.f6744a.getString(R.string.oneup_edit_chooser_label);
                    ComponentName[] componentNameArr = (ComponentName[]) arrayList.toArray(new ComponentName[0]);
                    Intent intent = new Intent("android.intent.action.CHOOSER");
                    intent.putExtra("android.intent.extra.INTENT", (Intent) a.get());
                    if (string != null) {
                        intent.putExtra("android.intent.extra.TITLE", string);
                    }
                    if (componentNameArr != null) {
                        intent.putExtra("android.intent.extra.EXCLUDE_COMPONENTS", componentNameArr);
                    }
                    a = Optional.m16285of(intent);
                } else if (arrayList2.size() == 1) {
                    ((Intent) a.get()).setComponent(dku.m6262b((ResolveInfo) arrayList2.get(0)));
                }
                try {
                    dku.f6745b.mo6067a((int) R.id.oneup_request_code_edit_in_external, dvg.m6742a(dku.f6744a, (Intent) a.get()));
                    return;
                } catch (Exception e) {
                    cwn.m5514b("ExternalEditorHandler: edit in external app error", new Object[0]);
                }
            }
        }
        this.f6904m.mo2572a((int) R.string.oneup_edit_in_fail);
    }

    /* renamed from: a */
    public final void mo4270a(String str, Consumer consumer) {
        Optional a = this.f6878H.mo4296a();
        if (!a.isPresent()) {
            cwn.m5510a("OneUpFragment: No media currently shown; can't perform operation[%s]", str);
            return;
        }
        consumer.accept((cxi) a.get());
    }

    /* renamed from: a */
    public final boolean mo4271a(cxi cxi) {
        return this.f6892V.mo4123b() > (((long) cxi.f5923o) * ((long) cxi.f5924p)) * 9;
    }

    /* renamed from: c */
    public final void mo4274c() {
        if (!this.f6898g.mo3274e()) {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addFlags(268468224);
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.setPackage(this.f6896e.getPackageName());
            this.f6896e.finish();
            this.f6896e.startActivity(intent);
        }
    }

    /* renamed from: b */
    public final void mo4273b(cxi cxi) {
        this.f6886P = Optional.m16285of(cxi);
        mo4272b();
    }

    /* renamed from: h */
    public final void mo2815h() {
        for (dls dls : this.f6878H.f6934a.f7803c) {
            dls.f6800e.ifPresent(dlp.f6794a);
        }
    }

    /* renamed from: i */
    public final void mo2816i() {
        for (dls dls : this.f6878H.f6934a.f7803c) {
            dls.f6800e.ifPresent(dlq.f6795a);
        }
    }

    /* renamed from: e */
    public final void mo2812e() {
        this.f6890T.mo4802a();
    }

    /* renamed from: b */
    public final void mo4272b() {
        if (this.f6886P.isPresent() && this.f6893b.isPresent()) {
            for (cxi cxi : (List) this.f6893b.get()) {
                if (cyc.m5645a(cxi, (cxi) this.f6886P.get())) {
                    this.f6878H.mo4297a(cxi);
                    this.f6886P = Optional.empty();
                    return;
                }
            }
        }
    }

    /* renamed from: a */
    private final void m6370a(cxi cxi, dmd dmd) {
        Uri a = this.f6891U.mo3173a(Uri.parse(cxi.f5910b));
        if (Uri.EMPTY.equals(a)) {
            cwn.m5514b("OneUpFragmentPeer: unable to request unloacking behavior for non-shareable Uri", new Object[0]);
            return;
        }
        Intent intent = new Intent();
        intent.setPackage(this.f6896e.getPackageName());
        intent.setAction("com.android.camera.action.REVIEW");
        intent.setData(a);
        intent.setFlags(1);
        intent.putExtra("internal_extra_stacked_behavior", dmd.f6822e);
        C0395ok okVar = this.f6896e;
        okVar.startActivity(dsp.m6593a(okVar, intent));
    }

    /* renamed from: a */
    public final void mo4269a(cxi cxi, Bundle bundle) {
        this.f6878H.mo4297a(cxi);
        if (bundle != null) {
            this.f6878H.f6934a.mo4634a().ifPresent(new dny(bundle));
        }
        this.f6882L = true;
    }

    /* renamed from: e */
    public final void mo4277e(cxi cxi) {
        if (dvg.m6744a((Context) this.f6896e)) {
            m6370a(cxi, dmd.SHARE);
            return;
        }
        iir g = ecx.f7947d.mo8793g();
        cyd b = cyc.m5648b(cxi);
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        ecx ecx = (ecx) g.f14318b;
        b.getClass();
        ecx.mo4686a();
        ecx.f7950b.add(b);
        boolean z = this.f6895d.f6831g;
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        ecx ecx2 = (ecx) g.f14318b;
        ecx2.f7949a |= 1;
        ecx2.f7951c = z;
        ecw.m7199a((ecx) g.mo8770g()).mo5429b(this.f6897f.mo5659r(), "share");
    }

    /* renamed from: a */
    public final void mo4268a() {
        this.f6889S.mo7113a(new dqi(this.f6888R, this.f6895d), guy.DONT_CARE, this.f6887Q);
    }

    /* renamed from: c */
    public final void mo4275c(cxi cxi) {
        if (dvg.m6744a((Context) this.f6896e)) {
            m6370a(cxi, dmd.USE_AS);
            return;
        }
        dlh dlh = this.f6902k;
        Optional a = dlh.mo4206a(cxi);
        if (a.isPresent()) {
            Intent intent = (Intent) a.get();
            List a2 = dlh.mo4207a(intent);
            if (!a2.isEmpty()) {
                if (a2.size() != 1) {
                    intent = Intent.createChooser(intent, dlh.f6773a.getString(R.string.oneup_use_as_chooser_label));
                }
                try {
                    C0149fj m = dlh.f6774b.mo5653m();
                    if (m != null) {
                        m.startActivity(dvg.m6742a(dlh.f6773a, intent));
                        return;
                    }
                    return;
                } catch (Exception e) {
                    cwn.m5515b((Throwable) e, "ExternalUseAsHandler: use as in external app error", new Object[0]);
                }
            }
        }
        this.f6904m.mo2572a((int) R.string.oneup_use_as_fail);
    }
}
