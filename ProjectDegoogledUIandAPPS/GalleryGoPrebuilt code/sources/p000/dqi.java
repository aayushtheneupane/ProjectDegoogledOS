package p000;

import android.content.ContentUris;
import android.net.Uri;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Executor;
import p003j$.util.Optional;

/* renamed from: dqi */
/* compiled from: PG */
public final class dqi implements gud {

    /* renamed from: a */
    public final /* synthetic */ dqj f7106a;

    /* renamed from: b */
    private final dme f7107b;

    /* renamed from: c */
    private final Object f7108c = new Object();

    /* renamed from: d */
    private dpz f7109d = null;

    /* renamed from: e */
    private ieh f7110e = ife.m12820a((Object) null);

    public /* synthetic */ dqi(dqj dqj, dme dme) {
        this.f7106a = dqj;
        this.f7107b = dme;
    }

    /* renamed from: a */
    public final ieh mo2733a() {
        ieh ieh;
        ieh a;
        ieh ieh2;
        ieh a2;
        ieh a3;
        ceq ceq;
        ieh ieh3;
        synchronized (this.f7108c) {
            this.f7110e.cancel(true);
            dpz dpz = new dpz((byte[]) null);
            dpz.mo4337a(false);
            this.f7109d = dpz;
            dme dme = this.f7107b;
            if ((dme.f6825a & 4) == 0) {
                if (dme.f6826b == 2) {
                    ceq ceq2 = (ceq) dme.f6827c;
                    if (dvg.m6744a(this.f7106a.f7111a)) {
                        HashSet hashSet = new HashSet();
                        for (Long longValue : ceq2.f4203e) {
                            hashSet.add(Long.valueOf(longValue.longValue()));
                        }
                        Uri parse = Uri.parse(ceq2.f4200b);
                        if (parse != null) {
                            try {
                                hashSet.add(Long.valueOf(ContentUris.parseId(parse)));
                            } catch (Exception e) {
                            }
                        }
                        iir g = cxd.f5884h.mo8793g();
                        iir g2 = cwz.f5879b.mo8793g();
                        if (g2.f14319c) {
                            g2.mo8751b();
                            g2.f14319c = false;
                        }
                        cwz cwz = (cwz) g2.f14318b;
                        if (!cwz.f5881a.mo8521a()) {
                            cwz.f5881a = iix.m13607a(cwz.f5881a);
                        }
                        igz.m12986a((Iterable) hashSet, (List) cwz.f5881a);
                        cwz cwz2 = (cwz) g2.mo8770g();
                        if (g.f14319c) {
                            g.mo8751b();
                            g.f14319c = false;
                        }
                        cxd cxd = (cxd) g.f14318b;
                        cwz2.getClass();
                        cxd.f5888c = cwz2;
                        cxd.f5887b = 3;
                        cxd.m5587a(cxd);
                        ieh = ife.m12820a((Object) (cxd) g.mo8770g());
                    } else if ("com.android.camera.action.REVIEW".equals(ceq2.f4202d)) {
                        iir g3 = cxd.f5884h.mo8793g();
                        if (g3.f14319c) {
                            g3.mo8751b();
                            g3.f14319c = false;
                        }
                        cxd cxd2 = (cxd) g3.f14318b;
                        cxd2.f5887b = 5;
                        cxd2.f5888c = true;
                        if (g3.f14319c) {
                            g3.mo8751b();
                            g3.f14319c = false;
                        }
                        cxd.m5587a((cxd) g3.f14318b);
                        ieh = ife.m12820a((Object) (cxd) g3.mo8770g());
                    } else if ("android.intent.action.VIEW".equals(ceq2.f4202d)) {
                        Uri parse2 = Uri.parse(ceq2.f4200b);
                        if (parse2 == null || !fxk.m9827a(parse2)) {
                            ieh3 = ife.m12820a((Object) Optional.empty());
                        } else {
                            ieh3 = this.f7106a.f7112b.mo7042a(parse2, new String[]{"bucket_id"}, (String) null, (String[]) null, (String) null).mo6897b(dqe.f7102a, (Executor) this.f7106a.f7117g).mo6899b();
                        }
                        ieh = gte.m10770a(ieh3, dqd.f7101a, (Executor) idh.f13918a);
                    }
                }
                ieh = ife.m12820a((Object) cxd.f5884h);
            } else {
                cxd cxd3 = dme.f6828d;
                if (cxd3 == null) {
                    cxd3 = cxd.f5884h;
                }
                ieh = ife.m12820a((Object) cxd3);
            }
            a = gte.m10771a(ieh, (icf) new dqc(this, dpz), (Executor) idh.f13918a);
            dpz dpz2 = this.f7109d;
            dpy dpy = this.f7106a.f7114d;
            dme dme2 = this.f7107b;
            int i = dme2.f6826b;
            if (i == 1) {
                ieh2 = ife.m12820a((Object) Optional.m16285of((cxi) dme2.f6827c));
            } else {
                cfb cfb = dpy.f7042a;
                if (i == 2) {
                    ceq = (ceq) dme2.f6827c;
                } else {
                    ceq = ceq.f4197g;
                }
                ieh2 = cfb.mo3090a(ceq);
            }
            a2 = gte.m10770a(ieh2, (hpr) new dqg(dpz2), (Executor) idh.f13918a);
            dpz dpz3 = this.f7109d;
            ieh a4 = this.f7106a.f7116f.mo4162a();
            dpz3.getClass();
            a3 = gte.m10770a(a4, (hpr) new dqb(dpz3), (Executor) idh.f13918a);
            this.f7110e = ife.m12823a(a, a2, a3);
        }
        this.f7106a.f7118h.mo7096a(a, (Object) "ONE_UP_FRAGMENT_DATA_SERVICE_KEY");
        this.f7106a.f7118h.mo7096a(a2, (Object) "ONE_UP_FRAGMENT_DATA_SERVICE_KEY");
        this.f7106a.f7118h.mo7096a(a3, (Object) "ONE_UP_FRAGMENT_DATA_SERVICE_KEY");
        cwn.m5509a(a, "OneUpFragmentDataService: Failed to fetch media list.", new Object[0]);
        cwn.m5509a(a2, "OneUpFragmentDataService: Failed to fetch initial media.", new Object[0]);
        cwn.m5509a(a3, "OneUpFragmentDataService: Failed to fetch special type data.", new Object[0]);
        return a2;
    }

    /* renamed from: c */
    public final Object mo2735c() {
        return guj.m10828a("ONE_UP_FRAGMENT_DATA_SERVICE_KEY", "MEDIA_DATA_SERVICE_KEY");
    }

    /* renamed from: b */
    public final gpc mo2734b() {
        synchronized (this.f7108c) {
            if (this.f7109d == null) {
                gpc a = gpc.m10579a((Object) guc.f12067a);
                return a;
            } else if (!this.f7110e.isCancelled()) {
                dpz dpz = this.f7109d;
                String str = "";
                if (dpz.f7046d == null) {
                    str = " initialMediaFetchFailed";
                }
                if (str.isEmpty()) {
                    gpc a2 = gpc.m10579a((Object) guc.m10815a(new dpx(dpz.f7043a, dpz.f7044b, dpz.f7045c, dpz.f7046d.booleanValue()), this.f7106a.f7119i.mo5370a()));
                    return a2;
                }
                throw new IllegalStateException(str.length() == 0 ? new String("Missing required properties:") : "Missing required properties:".concat(str));
            } else {
                gpc a3 = gpc.m10579a((Object) guc.f12067a);
                return a3;
            }
        }
    }
}
