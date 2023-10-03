package p000;

import android.os.health.HealthStats;

/* renamed from: fnr */
/* compiled from: PG */
public final class fnr extends fns {

    /* renamed from: a */
    public static final fnr f10110a = new fnr();

    private fnr() {
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ ikf mo5985a(String str, Object obj) {
        HealthStats healthStats = (HealthStats) obj;
        iir g = iqt.f14694i.mo8793g();
        long a = fpt.m9373a(healthStats, 30001);
        if (a != 0) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqt iqt = (iqt) g.f14318b;
            iqt.f14696a |= 1;
            iqt.f14697b = a;
        }
        long a2 = fpt.m9373a(healthStats, 30002);
        if (a2 != 0) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqt iqt2 = (iqt) g.f14318b;
            iqt2.f14696a |= 2;
            iqt2.f14698c = a2;
        }
        long a3 = fpt.m9373a(healthStats, 30003);
        if (a3 != 0) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqt iqt3 = (iqt) g.f14318b;
            iqt3.f14696a |= 4;
            iqt3.f14699d = a3;
        }
        long a4 = fpt.m9373a(healthStats, 30004);
        if (a4 != 0) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqt iqt4 = (iqt) g.f14318b;
            iqt4.f14696a |= 8;
            iqt4.f14700e = a4;
        }
        long a5 = fpt.m9373a(healthStats, 30005);
        if (a5 != 0) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqt iqt5 = (iqt) g.f14318b;
            iqt5.f14696a |= 16;
            iqt5.f14701f = a5;
        }
        long a6 = fpt.m9373a(healthStats, 30006);
        if (a6 != 0) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqt iqt6 = (iqt) g.f14318b;
            iqt6.f14696a |= 32;
            iqt6.f14702g = a6;
        }
        if (str != null) {
            iqq a7 = fpt.m9374a(str);
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqt iqt7 = (iqt) g.f14318b;
            a7.getClass();
            iqt7.f14703h = a7;
            iqt7.f14696a |= 64;
        }
        iqt iqt8 = (iqt) g.mo8770g();
        if (!fpt.m9380a(iqt8)) {
            return iqt8;
        }
        return null;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ String mo5986a(ikf ikf) {
        iqq iqq = ((iqt) ikf).f14703h;
        if (iqq == null) {
            iqq = iqq.f14681d;
        }
        return iqq.f14685c;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ ikf mo5984a(ikf ikf, ikf ikf2) {
        iqt iqt = (iqt) ikf;
        iqt iqt2 = (iqt) ikf2;
        if (iqt == null || iqt2 == null) {
            return iqt;
        }
        iir g = iqt.f14694i.mo8793g();
        if ((iqt.f14696a & 1) != 0) {
            long j = iqt.f14697b - iqt2.f14697b;
            if (j != 0) {
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                iqt iqt3 = (iqt) g.f14318b;
                iqt3.f14696a |= 1;
                iqt3.f14697b = j;
            }
        }
        if ((iqt.f14696a & 2) != 0) {
            long j2 = iqt.f14698c - iqt2.f14698c;
            if (j2 != 0) {
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                iqt iqt4 = (iqt) g.f14318b;
                iqt4.f14696a |= 2;
                iqt4.f14698c = j2;
            }
        }
        if ((iqt.f14696a & 4) != 0) {
            long j3 = iqt.f14699d - iqt2.f14699d;
            if (j3 != 0) {
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                iqt iqt5 = (iqt) g.f14318b;
                iqt5.f14696a |= 4;
                iqt5.f14699d = j3;
            }
        }
        if ((iqt.f14696a & 8) != 0) {
            long j4 = iqt.f14700e - iqt2.f14700e;
            if (j4 != 0) {
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                iqt iqt6 = (iqt) g.f14318b;
                iqt6.f14696a |= 8;
                iqt6.f14700e = j4;
            }
        }
        if ((iqt.f14696a & 16) != 0) {
            long j5 = iqt.f14701f - iqt2.f14701f;
            if (j5 != 0) {
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                iqt iqt7 = (iqt) g.f14318b;
                iqt7.f14696a |= 16;
                iqt7.f14701f = j5;
            }
        }
        if ((iqt.f14696a & 32) != 0) {
            long j6 = iqt.f14702g - iqt2.f14702g;
            if (j6 != 0) {
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                iqt iqt8 = (iqt) g.f14318b;
                iqt8.f14696a |= 32;
                iqt8.f14702g = j6;
            }
        }
        iqq iqq = iqt.f14703h;
        if (iqq == null) {
            iqq = iqq.f14681d;
        }
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        iqt iqt9 = (iqt) g.f14318b;
        iqq.getClass();
        iqt9.f14703h = iqq;
        iqt9.f14696a |= 64;
        iqt iqt10 = (iqt) g.mo8770g();
        if (fpt.m9380a(iqt10)) {
            return null;
        }
        return iqt10;
    }
}
