package p000;

import android.os.Binder;
import android.os.health.HealthStats;
import android.os.health.TimerStat;
import android.support.p002v7.widget.RecyclerView;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* renamed from: fpt */
/* compiled from: PG */
public final /* synthetic */ class fpt {
    private fpt() {
        hph hph = hph.f13219a;
    }

    /* renamed from: a */
    public static boolean m9380a(iqt iqt) {
        if (iqt != null) {
            return iqt.f14697b <= 0 && iqt.f14698c <= 0 && iqt.f14699d <= 0 && iqt.f14700e <= 0 && iqt.f14701f <= 0 && iqt.f14702g <= 0;
        }
        return true;
    }

    /* renamed from: a */
    public static boolean m9381a(iqu iqu) {
        return iqu == null || (((long) iqu.f14707b) <= 0 && ((long) iqu.f14708c) <= 0);
    }

    /* renamed from: a */
    public static long m9373a(HealthStats healthStats, int i) {
        if (healthStats == null || !healthStats.hasMeasurement(i)) {
            return 0;
        }
        return healthStats.getMeasurement(i);
    }

    /* renamed from: d */
    public static Map m9385d(HealthStats healthStats, int i) {
        return (healthStats != null && healthStats.hasStats(i)) ? healthStats.getStats(i) : Collections.emptyMap();
    }

    /* renamed from: b */
    public static iqv m9383b(HealthStats healthStats, int i) {
        if (healthStats == null || !healthStats.hasTimer(i)) {
            return null;
        }
        return m9376a((String) null, healthStats.getTimer(i));
    }

    /* renamed from: c */
    public static List m9384c(HealthStats healthStats, int i) {
        if (healthStats != null && healthStats.hasTimers(i)) {
            return fnu.f10112a.mo5988a((Map) healthStats.getTimers(i));
        }
        return Collections.emptyList();
    }

    /* renamed from: a */
    public static iqq m9374a(String str) {
        iir g = iqq.f14681d.mo8793g();
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        iqq iqq = (iqq) g.f14318b;
        str.getClass();
        iqq.f14683a |= 2;
        iqq.f14685c = str;
        return (iqq) g.mo8770g();
    }

    /* renamed from: a */
    public static boolean m9379a(iqr iqr) {
        if (iqr == null || (iqr.f14689b.size() == 0 && iqr.f14690c.size() == 0)) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    private static boolean m9382a(iqw iqw) {
        if (iqw != null) {
            return iqw.f14761c <= 0 && iqw.f14762d <= 0 && iqw.f14763e <= 0 && iqw.f14764f <= 0 && iqw.f14765g.size() == 0 && iqw.f14766h.size() == 0 && iqw.f14767i.size() == 0 && iqw.f14768j.size() == 0 && iqw.f14769k.size() == 0 && iqw.f14770l.size() == 0 && iqw.f14772n.size() == 0 && iqw.f14773o.size() == 0 && iqw.f14774p.size() == 0 && iqw.f14775q.size() == 0 && iqw.f14776r <= 0 && iqw.f14777s <= 0 && iqw.f14778t <= 0 && iqw.f14779u <= 0 && iqw.f14780v <= 0 && iqw.f14781w <= 0 && iqw.f14782x <= 0 && iqw.f14718B <= 0 && iqw.f14719C <= 0 && iqw.f14720D <= 0 && iqw.f14721E <= 0 && iqw.f14722F <= 0 && iqw.f14723G <= 0 && iqw.f14724H <= 0 && iqw.f14726J <= 0 && iqw.f14740X <= 0 && iqw.f14741Y <= 0 && iqw.f14742Z <= 0 && iqw.f14744aa <= 0 && iqw.f14745ab <= 0 && iqw.f14746ac <= 0 && iqw.f14747ad <= 0 && iqw.f14748ae <= 0 && iqw.f14749af <= 0 && iqw.f14750ag <= 0 && iqw.f14751ah <= 0 && iqw.f14752ai <= 0 && iqw.f14753aj <= 0 && iqw.f14754ak <= 0 && iqw.f14755al <= 0 && iqw.f14757an <= 0 && iqw.f14758ao <= 0 && iqw.f14759ap <= 0;
        }
        return true;
    }

    /* renamed from: a */
    public static iqv m9375a(iqv iqv, iqv iqv2) {
        if (iqv == null || iqv2 == null) {
            return iqv;
        }
        int i = iqv.f14713b - iqv2.f14713b;
        long j = iqv.f14714c - iqv2.f14714c;
        if (i == 0 && j == 0) {
            return null;
        }
        iir g = iqv.f14710e.mo8793g();
        iqq iqq = iqv.f14715d;
        if (iqq == null) {
            iqq = iqq.f14681d;
        }
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        iqv iqv3 = (iqv) g.f14318b;
        iqq.getClass();
        iqv3.f14715d = iqq;
        int i2 = iqv3.f14712a | 4;
        iqv3.f14712a = i2;
        int i3 = i2 | 1;
        iqv3.f14712a = i3;
        iqv3.f14713b = i;
        iqv3.f14712a = i3 | 2;
        iqv3.f14714c = j;
        return (iqv) g.mo8770g();
    }

    /* renamed from: a */
    public static iqw m9377a(iqw iqw, iqw iqw2) {
        iqv iqv;
        iqv iqv2;
        iqv iqv3;
        iqv iqv4;
        iqv iqv5;
        iqv iqv6;
        iqv iqv7;
        iqv iqv8;
        iqv iqv9;
        iqv iqv10;
        iqv iqv11;
        iqv iqv12;
        iqv iqv13;
        iqv iqv14;
        iqv iqv15;
        iqv iqv16;
        iqv iqv17;
        iqv iqv18;
        iqv iqv19;
        iqv iqv20;
        iqv iqv21;
        iqv iqv22;
        iqv iqv23;
        iqv iqv24;
        iqv iqv25;
        iqv iqv26;
        iqv iqv27;
        iqv iqv28;
        iqv iqv29;
        iqv iqv30;
        iqv iqv31;
        iqv iqv32;
        if (iqw == null || iqw2 == null) {
            return iqw;
        }
        iir g = iqw.f14716aq.mo8793g();
        if ((iqw.f14743a & 1) != 0) {
            long j = iqw.f14761c - iqw2.f14761c;
            if (j != 0) {
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                iqw iqw3 = (iqw) g.f14318b;
                iqw3.f14743a |= 1;
                iqw3.f14761c = j;
            }
        }
        if ((iqw.f14743a & 2) != 0) {
            long j2 = iqw.f14762d - iqw2.f14762d;
            if (j2 != 0) {
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                iqw iqw4 = (iqw) g.f14318b;
                iqw4.f14743a |= 2;
                iqw4.f14762d = j2;
            }
        }
        if ((iqw.f14743a & 4) != 0) {
            long j3 = iqw.f14763e - iqw2.f14763e;
            if (j3 != 0) {
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                iqw iqw5 = (iqw) g.f14318b;
                iqw5.f14743a |= 4;
                iqw5.f14763e = j3;
            }
        }
        if ((iqw.f14743a & 8) != 0) {
            long j4 = iqw.f14764f - iqw2.f14764f;
            if (j4 != 0) {
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                iqw iqw6 = (iqw) g.f14318b;
                iqw6.f14743a |= 8;
                iqw6.f14764f = j4;
            }
        }
        g.mo8779k(fnu.f10112a.mo5987a((List) iqw.f14765g, (List) iqw2.f14765g));
        g.mo8780l(fnu.f10112a.mo5987a((List) iqw.f14766h, (List) iqw2.f14766h));
        g.mo8781m(fnu.f10112a.mo5987a((List) iqw.f14767i, (List) iqw2.f14767i));
        g.mo8778j(fnu.f10112a.mo5987a((List) iqw.f14768j, (List) iqw2.f14768j));
        g.mo8777i(fnu.f10112a.mo5987a((List) iqw.f14769k, (List) iqw2.f14769k));
        g.mo8765e((Iterable) fnu.f10112a.mo5987a((List) iqw.f14770l, (List) iqw2.f14770l));
        if ((iqw.f14743a & 16) != 0) {
            iqv = iqw.f14771m;
            if (iqv == null) {
                iqv = iqv.f14710e;
            }
        } else {
            iqv = null;
        }
        if ((iqw2.f14743a & 16) != 0) {
            iqv2 = iqw2.f14771m;
            if (iqv2 == null) {
                iqv2 = iqv.f14710e;
            }
        } else {
            iqv2 = null;
        }
        iqv a = m9375a(iqv, iqv2);
        if (a != null) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw7 = (iqw) g.f14318b;
            a.getClass();
            iqw7.f14771m = a;
            iqw7.f14743a |= 16;
        }
        g.mo8769f((Iterable) fnu.f10112a.mo5987a((List) iqw.f14772n, (List) iqw2.f14772n));
        g.mo8776h((Iterable) fnr.f10110a.mo5987a((List) iqw.f14774p, (List) iqw2.f14774p));
        g.mo8773g((Iterable) fnq.f10109a.mo5987a((List) iqw.f14775q, (List) iqw2.f14775q));
        if ((iqw.f14743a & 32) != 0) {
            long j5 = iqw.f14776r - iqw2.f14776r;
            if (j5 != 0) {
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                iqw iqw8 = (iqw) g.f14318b;
                iqw8.f14743a |= 32;
                iqw8.f14776r = j5;
            }
        }
        if ((iqw.f14743a & 64) != 0) {
            long j6 = iqw.f14777s - iqw2.f14777s;
            if (j6 != 0) {
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                iqw iqw9 = (iqw) g.f14318b;
                iqw9.f14743a |= 64;
                iqw9.f14777s = j6;
            }
        }
        if ((iqw.f14743a & 128) != 0) {
            long j7 = iqw.f14778t - iqw2.f14778t;
            if (j7 != 0) {
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                iqw iqw10 = (iqw) g.f14318b;
                iqw10.f14743a |= 128;
                iqw10.f14778t = j7;
            }
        }
        if ((iqw.f14743a & 256) != 0) {
            long j8 = iqw.f14779u - iqw2.f14779u;
            if (j8 != 0) {
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                iqw iqw11 = (iqw) g.f14318b;
                iqw11.f14743a |= 256;
                iqw11.f14779u = j8;
            }
        }
        if ((iqw.f14743a & 512) != 0) {
            long j9 = iqw.f14780v - iqw2.f14780v;
            if (j9 != 0) {
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                iqw iqw12 = (iqw) g.f14318b;
                iqw12.f14743a |= 512;
                iqw12.f14780v = j9;
            }
        }
        if ((iqw.f14743a & 1024) != 0) {
            long j10 = iqw.f14781w - iqw2.f14781w;
            if (j10 != 0) {
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                iqw iqw13 = (iqw) g.f14318b;
                iqw13.f14743a |= 1024;
                iqw13.f14781w = j10;
            }
        }
        if ((iqw.f14743a & 2048) != 0) {
            long j11 = iqw.f14782x - iqw2.f14782x;
            if (j11 != 0) {
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                iqw iqw14 = (iqw) g.f14318b;
                iqw14.f14743a |= 2048;
                iqw14.f14782x = j11;
            }
        }
        if ((iqw.f14743a & 4096) != 0) {
            long j12 = iqw.f14718B - iqw2.f14718B;
            if (j12 != 0) {
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                iqw iqw15 = (iqw) g.f14318b;
                iqw15.f14743a |= 4096;
                iqw15.f14718B = j12;
            }
        }
        if ((iqw.f14743a & 8192) != 0) {
            long j13 = iqw.f14719C - iqw2.f14719C;
            if (j13 != 0) {
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                iqw iqw16 = (iqw) g.f14318b;
                iqw16.f14743a |= 8192;
                iqw16.f14719C = j13;
            }
        }
        if ((iqw.f14743a & 16384) != 0) {
            long j14 = iqw.f14720D - iqw2.f14720D;
            if (j14 != 0) {
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                iqw iqw17 = (iqw) g.f14318b;
                iqw17.f14743a |= 16384;
                iqw17.f14720D = j14;
            }
        }
        if ((iqw.f14743a & 32768) != 0) {
            long j15 = iqw.f14721E - iqw2.f14721E;
            if (j15 != 0) {
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                iqw iqw18 = (iqw) g.f14318b;
                iqw18.f14743a |= 32768;
                iqw18.f14721E = j15;
            }
        }
        if ((iqw.f14743a & 65536) != 0) {
            long j16 = iqw.f14722F - iqw2.f14722F;
            if (j16 != 0) {
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                iqw iqw19 = (iqw) g.f14318b;
                iqw19.f14743a |= 65536;
                iqw19.f14722F = j16;
            }
        }
        if ((iqw.f14743a & 131072) != 0) {
            long j17 = iqw.f14723G - iqw2.f14723G;
            if (j17 != 0) {
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                iqw iqw20 = (iqw) g.f14318b;
                iqw20.f14743a |= 131072;
                iqw20.f14723G = j17;
            }
        }
        if ((iqw.f14743a & 262144) != 0) {
            long j18 = iqw.f14724H - iqw2.f14724H;
            if (j18 != 0) {
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                iqw iqw21 = (iqw) g.f14318b;
                iqw21.f14743a |= 262144;
                iqw21.f14724H = j18;
            }
        }
        if ((iqw.f14743a & 524288) != 0) {
            iqv3 = iqw.f14725I;
            if (iqv3 == null) {
                iqv3 = iqv.f14710e;
            }
        } else {
            iqv3 = null;
        }
        if ((iqw2.f14743a & 524288) != 0) {
            iqv4 = iqw2.f14725I;
            if (iqv4 == null) {
                iqv4 = iqv.f14710e;
            }
        } else {
            iqv4 = null;
        }
        iqv a2 = m9375a(iqv3, iqv4);
        if (a2 != null) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw22 = (iqw) g.f14318b;
            a2.getClass();
            iqw22.f14725I = a2;
            iqw22.f14743a |= 524288;
        }
        if ((iqw.f14743a & 1048576) != 0) {
            long j19 = iqw.f14726J - iqw2.f14726J;
            if (j19 != 0) {
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                iqw iqw23 = (iqw) g.f14318b;
                iqw23.f14743a |= 1048576;
                iqw23.f14726J = j19;
            }
        }
        if ((iqw.f14743a & 2097152) != 0) {
            iqv5 = iqw.f14727K;
            if (iqv5 == null) {
                iqv5 = iqv.f14710e;
            }
        } else {
            iqv5 = null;
        }
        if ((iqw2.f14743a & 2097152) != 0) {
            iqv6 = iqw2.f14727K;
            if (iqv6 == null) {
                iqv6 = iqv.f14710e;
            }
        } else {
            iqv6 = null;
        }
        iqv a3 = m9375a(iqv5, iqv6);
        if (a3 != null) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw24 = (iqw) g.f14318b;
            a3.getClass();
            iqw24.f14727K = a3;
            iqw24.f14743a |= 2097152;
        }
        if ((iqw.f14743a & 4194304) != 0) {
            iqv7 = iqw.f14728L;
            if (iqv7 == null) {
                iqv7 = iqv.f14710e;
            }
        } else {
            iqv7 = null;
        }
        if ((iqw2.f14743a & 4194304) != 0) {
            iqv8 = iqw2.f14728L;
            if (iqv8 == null) {
                iqv8 = iqv.f14710e;
            }
        } else {
            iqv8 = null;
        }
        iqv a4 = m9375a(iqv7, iqv8);
        if (a4 != null) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw25 = (iqw) g.f14318b;
            a4.getClass();
            iqw25.f14728L = a4;
            iqw25.f14743a |= 4194304;
        }
        if ((iqw.f14743a & 8388608) != 0) {
            iqv9 = iqw.f14729M;
            if (iqv9 == null) {
                iqv9 = iqv.f14710e;
            }
        } else {
            iqv9 = null;
        }
        if ((iqw2.f14743a & 8388608) != 0) {
            iqv10 = iqw2.f14729M;
            if (iqv10 == null) {
                iqv10 = iqv.f14710e;
            }
        } else {
            iqv10 = null;
        }
        iqv a5 = m9375a(iqv9, iqv10);
        if (a5 != null) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw26 = (iqw) g.f14318b;
            a5.getClass();
            iqw26.f14729M = a5;
            iqw26.f14743a |= 8388608;
        }
        if ((iqw.f14743a & 16777216) != 0) {
            iqv11 = iqw.f14730N;
            if (iqv11 == null) {
                iqv11 = iqv.f14710e;
            }
        } else {
            iqv11 = null;
        }
        if ((iqw2.f14743a & 16777216) != 0) {
            iqv12 = iqw2.f14730N;
            if (iqv12 == null) {
                iqv12 = iqv.f14710e;
            }
        } else {
            iqv12 = null;
        }
        iqv a6 = m9375a(iqv11, iqv12);
        if (a6 != null) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw27 = (iqw) g.f14318b;
            a6.getClass();
            iqw27.f14730N = a6;
            iqw27.f14743a |= 16777216;
        }
        if ((iqw.f14743a & 33554432) != 0) {
            iqv13 = iqw.f14731O;
            if (iqv13 == null) {
                iqv13 = iqv.f14710e;
            }
        } else {
            iqv13 = null;
        }
        if ((iqw2.f14743a & 33554432) != 0) {
            iqv14 = iqw2.f14731O;
            if (iqv14 == null) {
                iqv14 = iqv.f14710e;
            }
        } else {
            iqv14 = null;
        }
        iqv a7 = m9375a(iqv13, iqv14);
        if (a7 != null) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw28 = (iqw) g.f14318b;
            a7.getClass();
            iqw28.f14731O = a7;
            iqw28.f14743a |= 33554432;
        }
        if ((iqw.f14743a & 67108864) != 0) {
            iqv15 = iqw.f14732P;
            if (iqv15 == null) {
                iqv15 = iqv.f14710e;
            }
        } else {
            iqv15 = null;
        }
        if ((iqw2.f14743a & 67108864) != 0) {
            iqv16 = iqw2.f14732P;
            if (iqv16 == null) {
                iqv16 = iqv.f14710e;
            }
        } else {
            iqv16 = null;
        }
        iqv a8 = m9375a(iqv15, iqv16);
        if (a8 != null) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw29 = (iqw) g.f14318b;
            a8.getClass();
            iqw29.f14732P = a8;
            iqw29.f14743a |= 67108864;
        }
        if ((iqw.f14743a & 134217728) != 0) {
            iqv17 = iqw.f14733Q;
            if (iqv17 == null) {
                iqv17 = iqv.f14710e;
            }
        } else {
            iqv17 = null;
        }
        if ((iqw2.f14743a & 134217728) != 0) {
            iqv18 = iqw2.f14733Q;
            if (iqv18 == null) {
                iqv18 = iqv.f14710e;
            }
        } else {
            iqv18 = null;
        }
        iqv a9 = m9375a(iqv17, iqv18);
        if (a9 != null) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw30 = (iqw) g.f14318b;
            a9.getClass();
            iqw30.f14733Q = a9;
            iqw30.f14743a |= 134217728;
        }
        if ((iqw.f14743a & 268435456) != 0) {
            iqv19 = iqw.f14734R;
            if (iqv19 == null) {
                iqv19 = iqv.f14710e;
            }
        } else {
            iqv19 = null;
        }
        if ((iqw2.f14743a & 268435456) != 0) {
            iqv20 = iqw2.f14734R;
            if (iqv20 == null) {
                iqv20 = iqv.f14710e;
            }
        } else {
            iqv20 = null;
        }
        iqv a10 = m9375a(iqv19, iqv20);
        if (a10 != null) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw31 = (iqw) g.f14318b;
            a10.getClass();
            iqw31.f14734R = a10;
            iqw31.f14743a |= 268435456;
        }
        if ((iqw.f14743a & 536870912) != 0) {
            iqv21 = iqw.f14735S;
            if (iqv21 == null) {
                iqv21 = iqv.f14710e;
            }
        } else {
            iqv21 = null;
        }
        if ((iqw2.f14743a & 536870912) != 0) {
            iqv22 = iqw2.f14735S;
            if (iqv22 == null) {
                iqv22 = iqv.f14710e;
            }
        } else {
            iqv22 = null;
        }
        iqv a11 = m9375a(iqv21, iqv22);
        if (a11 != null) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw32 = (iqw) g.f14318b;
            a11.getClass();
            iqw32.f14735S = a11;
            iqw32.f14743a |= 536870912;
        }
        if ((iqw.f14743a & 1073741824) != 0) {
            iqv23 = iqw.f14736T;
            if (iqv23 == null) {
                iqv23 = iqv.f14710e;
            }
        } else {
            iqv23 = null;
        }
        if ((iqw2.f14743a & 1073741824) != 0) {
            iqv24 = iqw2.f14736T;
            if (iqv24 == null) {
                iqv24 = iqv.f14710e;
            }
        } else {
            iqv24 = null;
        }
        iqv a12 = m9375a(iqv23, iqv24);
        if (a12 != null) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw33 = (iqw) g.f14318b;
            a12.getClass();
            iqw33.f14736T = a12;
            iqw33.f14743a |= 1073741824;
        }
        if ((iqw.f14743a & RecyclerView.UNDEFINED_DURATION) != 0) {
            iqv25 = iqw.f14737U;
            if (iqv25 == null) {
                iqv25 = iqv.f14710e;
            }
        } else {
            iqv25 = null;
        }
        if ((iqw2.f14743a & RecyclerView.UNDEFINED_DURATION) != 0) {
            iqv26 = iqw2.f14737U;
            if (iqv26 == null) {
                iqv26 = iqv.f14710e;
            }
        } else {
            iqv26 = null;
        }
        iqv a13 = m9375a(iqv25, iqv26);
        if (a13 != null) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw34 = (iqw) g.f14318b;
            a13.getClass();
            iqw34.f14737U = a13;
            iqw34.f14743a |= RecyclerView.UNDEFINED_DURATION;
        }
        if ((iqw.f14760b & 1) != 0) {
            iqv27 = iqw.f14738V;
            if (iqv27 == null) {
                iqv27 = iqv.f14710e;
            }
        } else {
            iqv27 = null;
        }
        if ((iqw2.f14760b & 1) != 0) {
            iqv28 = iqw2.f14738V;
            if (iqv28 == null) {
                iqv28 = iqv.f14710e;
            }
        } else {
            iqv28 = null;
        }
        iqv a14 = m9375a(iqv27, iqv28);
        if (a14 != null) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw35 = (iqw) g.f14318b;
            a14.getClass();
            iqw35.f14738V = a14;
            iqw35.f14760b |= 1;
        }
        if ((iqw.f14760b & 2) != 0) {
            iqv29 = iqw.f14739W;
            if (iqv29 == null) {
                iqv29 = iqv.f14710e;
            }
        } else {
            iqv29 = null;
        }
        if ((iqw2.f14760b & 2) != 0) {
            iqv30 = iqw2.f14739W;
            if (iqv30 == null) {
                iqv30 = iqv.f14710e;
            }
        } else {
            iqv30 = null;
        }
        iqv a15 = m9375a(iqv29, iqv30);
        if (a15 != null) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw36 = (iqw) g.f14318b;
            a15.getClass();
            iqw36.f14739W = a15;
            iqw36.f14760b |= 2;
        }
        if ((iqw.f14760b & 4) != 0) {
            long j20 = iqw.f14740X - iqw2.f14740X;
            if (j20 != 0) {
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                iqw iqw37 = (iqw) g.f14318b;
                iqw37.f14760b |= 4;
                iqw37.f14740X = j20;
            }
        }
        if ((iqw.f14760b & 8) != 0) {
            long j21 = iqw.f14741Y - iqw2.f14741Y;
            if (j21 != 0) {
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                iqw iqw38 = (iqw) g.f14318b;
                iqw38.f14760b |= 8;
                iqw38.f14741Y = j21;
            }
        }
        if ((iqw.f14760b & 16) != 0) {
            long j22 = iqw.f14742Z - iqw2.f14742Z;
            if (j22 != 0) {
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                iqw iqw39 = (iqw) g.f14318b;
                iqw39.f14760b |= 16;
                iqw39.f14742Z = j22;
            }
        }
        if ((iqw.f14760b & 32) != 0) {
            long j23 = iqw.f14744aa - iqw2.f14744aa;
            if (j23 != 0) {
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                iqw iqw40 = (iqw) g.f14318b;
                iqw40.f14760b |= 32;
                iqw40.f14744aa = j23;
            }
        }
        if ((iqw.f14760b & 64) != 0) {
            long j24 = iqw.f14745ab - iqw2.f14745ab;
            if (j24 != 0) {
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                iqw iqw41 = (iqw) g.f14318b;
                iqw41.f14760b |= 64;
                iqw41.f14745ab = j24;
            }
        }
        if ((iqw.f14760b & 128) != 0) {
            long j25 = iqw.f14746ac - iqw2.f14746ac;
            if (j25 != 0) {
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                iqw iqw42 = (iqw) g.f14318b;
                iqw42.f14760b |= 128;
                iqw42.f14746ac = j25;
            }
        }
        if ((iqw.f14760b & 256) != 0) {
            long j26 = iqw.f14747ad - iqw2.f14747ad;
            if (j26 != 0) {
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                iqw iqw43 = (iqw) g.f14318b;
                iqw43.f14760b |= 256;
                iqw43.f14747ad = j26;
            }
        }
        if ((iqw.f14760b & 512) != 0) {
            long j27 = iqw.f14748ae - iqw2.f14748ae;
            if (j27 != 0) {
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                iqw iqw44 = (iqw) g.f14318b;
                iqw44.f14760b |= 512;
                iqw44.f14748ae = j27;
            }
        }
        if ((iqw.f14760b & 1024) != 0) {
            long j28 = iqw.f14749af - iqw2.f14749af;
            if (j28 != 0) {
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                iqw iqw45 = (iqw) g.f14318b;
                iqw45.f14760b |= 1024;
                iqw45.f14749af = j28;
            }
        }
        if ((iqw.f14760b & 2048) != 0) {
            long j29 = iqw.f14750ag - iqw2.f14750ag;
            if (j29 != 0) {
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                iqw iqw46 = (iqw) g.f14318b;
                iqw46.f14760b |= 2048;
                iqw46.f14750ag = j29;
            }
        }
        if ((iqw.f14760b & 4096) != 0) {
            long j30 = iqw.f14751ah - iqw2.f14751ah;
            if (j30 != 0) {
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                iqw iqw47 = (iqw) g.f14318b;
                iqw47.f14760b |= 4096;
                iqw47.f14751ah = j30;
            }
        }
        if ((iqw.f14760b & 8192) != 0) {
            long j31 = iqw.f14752ai - iqw2.f14752ai;
            if (j31 != 0) {
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                iqw iqw48 = (iqw) g.f14318b;
                iqw48.f14760b |= 8192;
                iqw48.f14752ai = j31;
            }
        }
        if ((iqw.f14760b & 16384) != 0) {
            long j32 = iqw.f14753aj - iqw2.f14753aj;
            if (j32 != 0) {
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                iqw iqw49 = (iqw) g.f14318b;
                iqw49.f14760b |= 16384;
                iqw49.f14753aj = j32;
            }
        }
        if ((iqw.f14760b & 32768) != 0) {
            long j33 = iqw.f14754ak - iqw2.f14754ak;
            if (j33 != 0) {
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                iqw iqw50 = (iqw) g.f14318b;
                iqw50.f14760b = 32768 | iqw50.f14760b;
                iqw50.f14754ak = j33;
            }
        }
        if ((iqw.f14760b & 65536) != 0) {
            long j34 = iqw.f14755al - iqw2.f14755al;
            if (j34 != 0) {
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                iqw iqw51 = (iqw) g.f14318b;
                iqw51.f14760b |= 65536;
                iqw51.f14755al = j34;
            }
        }
        if ((iqw.f14760b & 131072) != 0) {
            iqv31 = iqw.f14756am;
            if (iqv31 == null) {
                iqv31 = iqv.f14710e;
            }
        } else {
            iqv31 = null;
        }
        if ((iqw2.f14760b & 131072) != 0) {
            iqv32 = iqw2.f14756am;
            if (iqv32 == null) {
                iqv32 = iqv.f14710e;
            }
        } else {
            iqv32 = null;
        }
        iqv a16 = m9375a(iqv31, iqv32);
        if (a16 != null) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw52 = (iqw) g.f14318b;
            a16.getClass();
            iqw52.f14756am = a16;
            iqw52.f14760b |= 131072;
        }
        if ((iqw.f14760b & 262144) != 0) {
            long j35 = iqw.f14757an - iqw2.f14757an;
            if (j35 != 0) {
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                iqw iqw53 = (iqw) g.f14318b;
                iqw53.f14760b |= 262144;
                iqw53.f14757an = j35;
            }
        }
        if ((iqw.f14760b & 524288) != 0) {
            long j36 = iqw.f14758ao - iqw2.f14758ao;
            if (j36 != 0) {
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                iqw iqw54 = (iqw) g.f14318b;
                iqw54.f14760b |= 524288;
                iqw54.f14758ao = j36;
            }
        }
        if ((iqw.f14760b & 1048576) != 0) {
            long j37 = iqw.f14759ap - iqw2.f14759ap;
            if (j37 != 0) {
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                iqw iqw55 = (iqw) g.f14318b;
                iqw55.f14760b |= 1048576;
                iqw55.f14759ap = j37;
            }
        }
        iqw iqw56 = (iqw) g.mo8770g();
        if (m9382a(iqw56)) {
            return null;
        }
        return iqw56;
    }

    /* renamed from: a */
    public static iqv m9376a(String str, TimerStat timerStat) {
        iir g = iqv.f14710e.mo8793g();
        int count = timerStat.getCount();
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        iqv iqv = (iqv) g.f14318b;
        iqv.f14712a |= 1;
        iqv.f14713b = count;
        long time = timerStat.getTime();
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        iqv iqv2 = (iqv) g.f14318b;
        int i = iqv2.f14712a | 2;
        iqv2.f14712a = i;
        iqv2.f14714c = time;
        if (iqv2.f14713b < 0) {
            iqv2.f14712a = i | 1;
            iqv2.f14713b = 0;
        }
        if (str != null) {
            iqq a = m9374a(str);
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqv iqv3 = (iqv) g.f14318b;
            a.getClass();
            iqv3.f14715d = a;
            iqv3.f14712a |= 4;
        }
        iqv iqv4 = (iqv) g.f14318b;
        if (iqv4.f14713b == 0 && iqv4.f14714c == 0) {
            return null;
        }
        return (iqv) g.mo8770g();
    }

    /* renamed from: a */
    public static Object m9378a(fpu fpu) {
        long clearCallingIdentity;
        try {
            return fpu.mo6020a();
        } catch (SecurityException e) {
            clearCallingIdentity = Binder.clearCallingIdentity();
            Object a = fpu.mo6020a();
            Binder.restoreCallingIdentity(clearCallingIdentity);
            return a;
        } catch (Throwable th) {
            Binder.restoreCallingIdentity(clearCallingIdentity);
            throw th;
        }
    }
}
