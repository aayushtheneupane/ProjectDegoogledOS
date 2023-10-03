package p000;

import android.os.Build;
import android.util.SparseArray;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import p003j$.util.concurrent.ConcurrentHashMap;

/* renamed from: hmh */
/* compiled from: PG */
public final class hmh implements hme, hkf {

    /* renamed from: a */
    public static final hvy f13022a = hvy.m12261a("com/google/apps/tiktok/tracing/TraceManagerImpl");

    /* renamed from: b */
    public final exm f13023b;

    /* renamed from: c */
    public final iel f13024c;

    /* renamed from: d */
    public final ConcurrentMap f13025d = new ConcurrentHashMap(2, 0.75f, 1);

    /* renamed from: e */
    public final AtomicLong f13026e = new AtomicLong(2100000);

    /* renamed from: f */
    private final grj f13027f;

    /* renamed from: g */
    private final iqk f13028g;

    /* renamed from: h */
    private final hmv f13029h;

    /* renamed from: i */
    private final hky f13030i;

    public hmh(grj grj, exm exm, iel iel, iqk iqk, hmv hmv, hky hky) {
        this.f13027f = grj;
        this.f13023b = exm;
        this.f13024c = iel;
        this.f13028g = iqk;
        this.f13029h = hmv;
        this.f13030i = hky;
    }

    /* renamed from: a */
    public final Map mo7512a() {
        hsq g = hsu.m12070g();
        for (Map.Entry entry : this.f13025d.entrySet()) {
            g.mo7932a((UUID) entry.getKey(), ((hni) entry.getValue()).mo7606a().f13096d);
        }
        return g.mo7930a();
    }

    /* renamed from: a */
    public final hlp mo7580a(String str, hln hln, long j, long j2, hms hms) {
        boolean z;
        String str2 = str;
        hms hms2 = hms;
        hlp a = hnb.m11769a();
        if (a != null) {
            hnb.m11773a(a, str2);
        }
        hky hky = this.f13030i;
        UUID uuid = new UUID((hky.mo7540a() & -61441) ^ hky.f12950a.getMostSignificantBits(), (hky.mo7540a() >>> 2) ^ hky.f12950a.getLeastSignificantBits());
        float f = this.f13029h.f13066a;
        boolean a2 = gte.m10776a(uuid.getLeastSignificantBits(), 0.0f);
        iir g = hmt.f13055i.mo8793g();
        long leastSignificantBits = uuid.getLeastSignificantBits();
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        hmt hmt = (hmt) g.f14318b;
        hmt.f13057a = 2 | hmt.f13057a;
        hmt.f13059c = leastSignificantBits;
        long mostSignificantBits = uuid.getMostSignificantBits();
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        hmt hmt2 = (hmt) g.f14318b;
        int i = hmt2.f13057a | 1;
        hmt2.f13057a = i;
        hmt2.f13058b = mostSignificantBits;
        int i2 = i | 4;
        hmt2.f13057a = i2;
        hmt2.f13061e = j;
        int i3 = i2 | 8;
        hmt2.f13057a = i3;
        long j3 = j2;
        hmt2.f13062f = j3;
        hmt2.f13064h = hms2.f13054c;
        hmt2.f13057a = i3 | 32;
        hmt hmt3 = (hmt) g.mo8770g();
        long e = hms2 != hms.REALTIME ? this.f13023b.mo5374e() : j3;
        hng hng = new hng(str2, hln);
        hni hni = new hni(this, uuid, hmt3, hng, e, a2);
        exm exm = this.f13023b;
        if (hms2 == hms.UPTIME) {
            z = true;
        } else {
            z = false;
        }
        hni hni2 = hni;
        exm exm2 = exm;
        UUID uuid2 = uuid;
        hlf hlf = new hlf(hng, uuid, hni, exm2, e, a2, z);
        grj grj = this.f13027f;
        ife.m12898e((Object) hlf);
        if (grj.f11900d.compareAndSet(false, true)) {
            grj.f11899c.execute(new grg(grj));
        }
        gri gri = new gri(hlf, grj.f11898b);
        grj.f11897a.put(gri, Boolean.TRUE);
        grh grh = gri.f11896a;
        iel iel = this.f13024c;
        hni2.f13100b = grh;
        grh.mo53a(hni2, iel);
        this.f13025d.put(uuid2, hni2);
        hnb.m11776b((hlp) hlf);
        return hlf;
    }

    /* renamed from: a */
    public final void mo7583a(hmt hmt, SparseArray sparseArray, String str) {
        RuntimeException runtimeException;
        hlp a = hnb.m11769a();
        hnb.m11776b((hlp) new hlc(str, hlc.f12959a, hlm.f12987a));
        try {
            runtimeException = null;
            for (hmd a2 : (Set) this.f13028g.mo2097a()) {
                a2.mo7541a(hmt, sparseArray);
            }
            if (runtimeException == null) {
                hnb.m11776b(a);
                return;
            }
            throw runtimeException;
        } catch (RuntimeException e) {
            if (runtimeException != null) {
                int i = Build.VERSION.SDK_INT;
                ifn.m12935a((Throwable) runtimeException, (Throwable) e);
            } else {
                runtimeException = e;
            }
        } catch (Throwable th) {
            hnb.m11776b(a);
            throw th;
        }
    }
}
