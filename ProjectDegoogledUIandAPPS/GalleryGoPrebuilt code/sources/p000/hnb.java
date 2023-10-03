package p000;

import android.os.Build;
import android.os.SystemClock;
import android.os.Trace;
import java.util.ArrayList;
import java.util.List;

/* renamed from: hnb */
/* compiled from: PG */
public final class hnb {

    /* renamed from: a */
    public static final List f13076a = new ArrayList();

    /* renamed from: b */
    public static hlp f13077b;

    /* renamed from: c */
    private static final ThreadLocal f13078c = new hmz();

    /* renamed from: d */
    private static final Runnable f13079d = hmy.f13068a;

    /* renamed from: e */
    private static int f13080e;

    /* renamed from: f */
    private static int f13081f = 0;

    /* renamed from: g */
    private static final fuk f13082g = new fuk("tiktok_systrace", (byte[]) null);

    /* renamed from: a */
    public static hlj m11765a(String str) {
        return m11766a(str, hnf.f13084a);
    }

    /* renamed from: a */
    public static hlj m11766a(String str, hnf hnf) {
        return m11767a(str, hnf, hlm.f12987a);
    }

    /* renamed from: a */
    public static hlj m11767a(String str, hnf hnf, hln hln) {
        hlp hlp;
        ife.m12898e((Object) hnf);
        hlp a = m11769a();
        if (a == null) {
            hlp = new hlb(str);
        } else {
            hlp = a.mo7543a(str, hln);
        }
        m11776b(hlp);
        return new hlj(hlp);
    }

    /* renamed from: b */
    private static void m11778b(String str) {
        if (str.length() > 127) {
            str = str.substring(0, 127);
        }
        Trace.beginSection(str);
    }

    /* renamed from: d */
    public static List m11782d(hnf hnf) {
        ife.m12898e((Object) hnf);
        hsj j = hso.m12048j();
        for (hlp a = m11769a(); a != null; a = a.mo7507a()) {
            j.mo7908c(a.mo7509c());
        }
        return ife.m12836a((List) j.mo7905a());
    }

    /* renamed from: a */
    static void m11772a(hlp hlp) {
        boolean z;
        ife.m12898e((Object) hlp);
        hna hna = (hna) f13078c.get();
        hlp hlp2 = hna.f13074c;
        if (hlp == hlp2) {
            z = true;
        } else {
            z = false;
        }
        ife.m12879b(z, "Wrong trace, expected %s but got %s", hlp2.mo7509c(), hlp.mo7509c());
        m11771a(hna, hlp2.mo7507a(), true);
    }

    /* renamed from: d */
    private static void m11784d(hlp hlp) {
        if (hlp.mo7507a() != null) {
            m11784d(hlp.mo7507a());
        }
        m11778b(hlp.mo7509c());
    }

    /* renamed from: e */
    private static void m11786e(hlp hlp) {
        Trace.endSection();
        if (hlp.mo7507a() != null) {
            m11786e(hlp.mo7507a());
        }
    }

    /* renamed from: c */
    public static hlq m11779c() {
        m11785e();
        return hmx.f13067a;
    }

    /* renamed from: a */
    public static hlp m11769a() {
        return ((hna) f13078c.get()).f13074c;
    }

    /* renamed from: a */
    public static hlk m11768a(hok hok) {
        hlk a = hlk.m11693a(2);
        for (hlp a2 = m11769a(); a2 != null; a2 = a2.mo7507a()) {
            a = a2.mo7536a(hok);
            a.f12984a = true;
            int i = a.f12985b - 1;
            if (i == 0 || i == 1) {
                break;
            }
        }
        return a;
    }

    /* renamed from: b */
    static hlp m11775b() {
        hlp a = m11769a();
        return a == null ? new hla() : a;
    }

    /* renamed from: a */
    public static boolean m11774a(hnf hnf) {
        ife.m12898e((Object) hnf);
        return m11769a() != null;
    }

    /* renamed from: d */
    public static void m11783d() {
        int i = f13080e;
        int i2 = i - 1;
        f13080e = i2;
        if (i2 < 0) {
            throw new IllegalStateException("More calls to pause than to resume");
        } else if (f13081f == i) {
            ife.m12869b((Object) f13077b, (Object) "current async trace should not be null");
            m11770a((hlp) null, false);
            f13081f = 0;
        }
    }

    /* renamed from: c */
    public static void m11781c(hnf hnf) {
        ife.m12898e((Object) hnf);
        m11783d();
    }

    /* renamed from: e */
    private static void m11785e() {
        hlp hlp;
        f13080e++;
        if (f13081f == 0 && m11769a() == null && (hlp = f13077b) != null) {
            m11770a(hlp, false);
            f13081f = f13080e;
        }
    }

    /* renamed from: b */
    public static void m11777b(hnf hnf) {
        ife.m12898e((Object) hnf);
        m11785e();
    }

    /* renamed from: b */
    static hlp m11776b(hlp hlp) {
        return m11770a(hlp, true);
    }

    /* renamed from: a */
    private static hlp m11770a(hlp hlp, boolean z) {
        return m11771a((hna) f13078c.get(), hlp, z);
    }

    /* renamed from: a */
    private static hlp m11771a(hna hna, hlp hlp, boolean z) {
        boolean z2;
        hlp hlp2 = hna.f13074c;
        if (hlp2 == hlp) {
            return hlp;
        }
        if (hlp2 == null) {
            if (Build.VERSION.SDK_INT >= 29) {
                z2 = Trace.isEnabled();
            } else {
                int i = Build.VERSION.SDK_INT;
                z2 = foj.m9314a(f13082g);
            }
            hna.f13073b = z2;
        }
        if (hna.f13073b) {
            if (hlp2 != null) {
                if (hlp != null) {
                    if (hlp2.mo7507a() == hlp) {
                        Trace.endSection();
                    } else if (hlp2 == hlp.mo7507a()) {
                        m11778b(hlp.mo7509c());
                    }
                }
                m11786e(hlp2);
            }
            if (hlp != null) {
                m11784d(hlp);
            }
        }
        if ((hlp != null && hlp.mo7546e()) || (hlp2 != null && hlp2.mo7546e())) {
            int currentThreadTimeMillis = (int) SystemClock.currentThreadTimeMillis();
            int i2 = currentThreadTimeMillis - hna.f13075d;
            if (i2 > 0 && hlp2 != null && hlp2.mo7546e()) {
                hlp2.mo7544a(i2);
            }
            hna.f13075d = currentThreadTimeMillis;
        }
        hna.f13074c = hlp;
        if (hna.f13072a && z) {
            f13076a.add(hlp);
            fxk.m9824a(f13079d);
        }
        return hlp2;
    }

    /* renamed from: a */
    static void m11773a(hlp hlp, String str) {
        if (hlp instanceof hkt) {
            String c = m11780c(hlp);
            if (!"".equals(c)) {
                String valueOf = String.valueOf(c);
                c = valueOf.length() == 0 ? new String(": ") : ": ".concat(valueOf);
            }
            throw ((hkr) hmw.m11758a((Throwable) new hkr(c, str, ((hkt) hlp).mo7535d())));
        }
        int i = Build.VERSION.SDK_INT;
        throw ((hkr) hmw.m11758a((Throwable) new hkr(str)));
    }

    /* renamed from: c */
    public static String m11780c(hlp hlp) {
        if (hlp.mo7507a() == null) {
            return hlp.mo7509c();
        }
        String c = m11780c(hlp.mo7507a());
        String c2 = hlp.mo7509c();
        StringBuilder sb = new StringBuilder(String.valueOf(c).length() + 4 + String.valueOf(c2).length());
        sb.append(c);
        sb.append(" -> ");
        sb.append(c2);
        return sb.toString();
    }
}
