package p000;

import android.util.Log;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.logging.Level;

/* renamed from: hdl */
/* compiled from: PG */
final class hdl extends hxm {

    /* renamed from: b */
    private final hdm f12534b;

    public hdl(String str, hdm hdm) {
        super(str);
        this.f12534b = hdm;
    }

    /* renamed from: a */
    public final void mo7300a(RuntimeException runtimeException, hwz hwz) {
        Log.e("TikTokClientLogging", "Internal logging error", runtimeException);
    }

    /* renamed from: a */
    public final boolean mo7301a(Level level) {
        return ((ifr) this.f12534b.f12538d.mo9034a()).mo3861a(level);
    }

    /* renamed from: a */
    public final void mo7299a(hwz hwz) {
        iap iap;
        ieh ieh;
        hdm hdm = this.f12534b;
        ift b = ((hdi) hdm.f12537c).mo2097a();
        String a = ift.m12942a(hwz);
        Throwable th = (Throwable) ift.m12941a(hwz, hwa.f13496a);
        if ((a == null || a.isEmpty()) && th == null) {
            iap = null;
        } else {
            hwg g = hwz.mo8212g();
            Level d = hwz.mo8209d();
            String a2 = g.mo8219a();
            String b2 = g.mo8220b();
            iao iao = (iao) iap.f13738i.mo8793g();
            ian ian = ift.f14017a;
            if (iao.f14319c) {
                iao.mo8751b();
                iao.f14319c = false;
            }
            iap iap2 = (iap) iao.f14318b;
            ian.getClass();
            iap2.f13741b = ian;
            iap2.f13740a |= 1;
            String name = Thread.currentThread().getName();
            if (iao.f14319c) {
                iao.mo8751b();
                iao.f14319c = false;
            }
            iap iap3 = (iap) iao.f14318b;
            name.getClass();
            iap3.f13740a |= 2;
            iap3.f13742c = name;
            int intValue = d.intValue();
            if (iao.f14319c) {
                iao.mo8751b();
                iao.f14319c = false;
            }
            iap iap4 = (iap) iao.f14318b;
            int i = iap4.f13740a | 4;
            iap4.f13740a = i;
            iap4.f13743d = intValue;
            a2.getClass();
            int i2 = i | 8;
            iap4.f13740a = i2;
            iap4.f13744e = a2;
            b2.getClass();
            int i3 = i2 | 16;
            iap4.f13740a = i3;
            iap4.f13745f = b2;
            if (a != null) {
                a.getClass();
                iap4.f13740a = i3 | 64;
                iap4.f13746g = a;
            }
            if (th != null) {
                iau iau = (iau) iav.f13763d.mo8793g();
                iaq b3 = ife.m12865b(th);
                if (iau.f14319c) {
                    iau.mo8751b();
                    iau.f14319c = false;
                }
                iav iav = (iav) iau.f14318b;
                iat iat = (iat) b3.mo8770g();
                iat.getClass();
                iav.f13766b = iat;
                iav.f13765a |= 1;
                while (true) {
                    Throwable cause = th.getCause();
                    if (!(cause == null || cause == th)) {
                        iaq b4 = ife.m12865b(th.getCause());
                        if (iau.f14319c) {
                            iau.mo8751b();
                            iau.f14319c = false;
                        }
                        iav iav2 = (iav) iau.f14318b;
                        iat iat2 = (iat) b4.mo8770g();
                        iat2.getClass();
                        if (!iav2.f13767c.mo8521a()) {
                            iav2.f13767c = iix.m13608a(iav2.f13767c);
                        }
                        iav2.f13767c.add(iat2);
                        th = th.getCause();
                    }
                }
                if (iao.f14319c) {
                    iao.mo8751b();
                    iao.f14319c = false;
                }
                iap iap5 = (iap) iao.f14318b;
                iav iav3 = (iav) iau.mo8770g();
                iav3.getClass();
                iap5.f13747h = iav3;
                iap5.f13740a |= 256;
            }
            iap = (iap) iao.mo8770g();
        }
        iir a3 = iap != null ? hwz.mo8213h() != null ? b.mo8493a(iap, hwz.mo8214i()) : b.mo8493a(iap, new Object[0]) : null;
        if (a3 != null) {
            List d2 = hnb.m11782d(hnf.f13084a);
            if (!d2.isEmpty()) {
                iir g2 = ifx.f14028b.mo8793g();
                if (g2.f14319c) {
                    g2.mo8751b();
                    g2.f14319c = false;
                }
                ifx ifx = (ifx) g2.f14318b;
                if (!ifx.f14030a.mo8521a()) {
                    ifx.f14030a = iix.m13608a(ifx.f14030a);
                }
                igz.m12986a((Iterable) d2, (List) ifx.f14030a);
                if (a3.f14319c) {
                    a3.mo8751b();
                    a3.f14319c = false;
                }
                ifz ifz = (ifz) a3.f14318b;
                ifx ifx2 = (ifx) g2.mo8770g();
                ifz ifz2 = ifz.f14037f;
                ifx2.getClass();
                ifz.f14042d = ifx2;
                ifz.f14039a |= 4;
            }
            ifz ifz3 = (ifz) a3.mo8770g();
            int b5 = ift.m12943b(hwz);
            String str = (String) ift.m12941a(hwz, ifu.f14021a);
            if (str != null) {
                ieh = ife.m12820a((Object) str);
            } else {
                gmg gmg = (gmg) hdm.f12536b.mo9034a();
                hlk a4 = gko.m10447a(gtf.f12011a);
                ieh = a4.mo7551b() ? ((gmg) hdm.f12536b.mo9034a()).mo6844a((gkn) a4.mo7550a(), gtf.f12011a) : ife.m12820a((Object) null);
            }
            ife.m12841a(ieh, (idw) new hdk(hdm, ifz3, b5), (Executor) idh.f13918a);
        }
    }
}
