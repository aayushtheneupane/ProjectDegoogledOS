package p000;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* renamed from: gyc */
/* compiled from: PG */
public final class gyc {

    /* renamed from: a */
    public final gzy f12290a;

    /* renamed from: b */
    public final Map f12291b;

    public gyc(int i, Map map, gzy gzy, iek iek, exm exm, Set set, fxr fxr, gww gww, icf icf, Executor executor, Map map2, gzm gzm, gzm gzm2, gxs gxs, icf icf2) {
        gzy gzy2 = gzy;
        gww gww2 = gww;
        this.f12290a = gzy2;
        hsq g = hsu.m12070g();
        for (Map.Entry entry : map.entrySet()) {
            if (gww2.equals(entry.getValue())) {
                String str = (String) entry.getKey();
                hsq g2 = hsu.m12070g();
                for (Map.Entry entry2 : map2.entrySet()) {
                    String a = m11035a((String) entry2.getKey());
                    if (str.equals(gzy2.mo7210a(a))) {
                        g2.mo7932a(m11036a(a, (String) entry2.getKey()), (gxc) entry2.getValue());
                    }
                }
                gzn gzn = r10;
                gzn gzn2 = new gzn(exm, !set.contains(str) ? gzm2 : gzm, g2.mo7930a(), i, iek, executor, gbz.m9993a(str, ife.m12816a((ice) new gya(str, gww2, icf), (Executor) iek), gwx.f12223i, iek, fzz.f10768a, iij.m13501a(), fxr), gxs, new gyb(icf2, str));
                g.mo7932a(str, gzn);
            } else {
                icf icf3 = icf2;
            }
        }
        this.f12291b = g.mo7930a();
    }

    /* renamed from: a */
    public static final String m11036a(String str, String str2) {
        boolean z = false;
        if (str2.length() > str.length() + 1 && str2.charAt(str.length()) == ' ') {
            z = true;
        }
        ife.m12850a(z, "Illegal flag name for package: %s, %s ", (Object) str, (Object) str2);
        return str2.substring(str.length() + 1);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean mo7208a() {
        for (gzn a : ((hsu) this.f12291b).values()) {
            if (a.mo7224a()) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public static String m11035a(String str) {
        int indexOf = str.indexOf(32);
        ife.m12849a(indexOf > 0, "Bad flag format for %s", (Object) str);
        return str.substring(0, indexOf);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final ieh mo7207a(String str, fqi fqi) {
        gxa gxa;
        boolean z;
        double d;
        ihw ihw;
        ihw ihw2;
        fqi fqi2 = fqi;
        gzn gzn = (gzn) this.f12291b.get(str);
        ieh a = gzn.f12357c.mo6948a();
        iir g = gwx.f12223i.mo8793g();
        int i = gzn.f12358d;
        boolean z2 = false;
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        gwx gwx = (gwx) g.f14318b;
        int i2 = gwx.f12225a | 8;
        gwx.f12225a = i2;
        gwx.f12231g = i;
        if ((fqi2.f10260a & 2) != 0) {
            ihw ihw3 = fqi2.f10262c;
            ihw3.getClass();
            i2 |= 4;
            gwx.f12225a = i2;
            gwx.f12228d = ihw3;
        }
        String str2 = fqi2.f10261b;
        str2.getClass();
        int i3 = i2 | 2;
        gwx.f12225a = i3;
        gwx.f12227c = str2;
        String str3 = fqi2.f10263d;
        str3.getClass();
        gwx.f12225a = i3 | 1;
        gwx.f12226b = str3;
        long a2 = gzn.f12363i.mo5370a();
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        gwx gwx2 = (gwx) g.f14318b;
        gwx2.f12225a |= 16;
        gwx2.f12232h = a2;
        ije ije = fqi2.f10264e;
        int size = ije.size();
        int i4 = 0;
        while (i4 < size) {
            fqj fqj = (fqj) ije.get(i4);
            gxc gxc = (gxc) gzn.f12356b.get(fqj.f10271d);
            if (gxc != null) {
                iir g2 = gxa.f12234e.mo8793g();
                String str4 = fqj.f10271d;
                if (g2.f14319c) {
                    g2.mo8751b();
                    g2.f14319c = z2;
                }
                gxa gxa2 = (gxa) g2.f14318b;
                str4.getClass();
                gxa2.f12236a |= 1;
                gxa2.f12239d = str4;
                int b = gxc.mo7165b();
                int i5 = b - 1;
                if (b != 0) {
                    if (i5 == 0) {
                        long longValue = fqj.f10269b == 1 ? ((Long) fqj.f10270c).longValue() : 0;
                        if (g2.f14319c) {
                            g2.mo8751b();
                            g2.f14319c = false;
                        }
                        gxa gxa3 = (gxa) g2.f14318b;
                        gxa3.f12237b = 1;
                        gxa3.f12238c = Long.valueOf(longValue);
                        gxa = (gxa) g2.mo8770g();
                    } else if (i5 == 1) {
                        if (fqj.f10269b == 2) {
                            z = ((Boolean) fqj.f10270c).booleanValue();
                        } else {
                            z = false;
                        }
                        if (g2.f14319c) {
                            g2.mo8751b();
                            g2.f14319c = false;
                        }
                        gxa gxa4 = (gxa) g2.f14318b;
                        gxa4.f12237b = 2;
                        gxa4.f12238c = Boolean.valueOf(z);
                        gxa = (gxa) g2.mo8770g();
                    } else if (i5 == 2) {
                        if (fqj.f10269b == 3) {
                            d = ((Double) fqj.f10270c).doubleValue();
                        } else {
                            d = 0.0d;
                        }
                        if (g2.f14319c) {
                            g2.mo8751b();
                            g2.f14319c = false;
                        }
                        gxa gxa5 = (gxa) g2.f14318b;
                        gxa5.f12237b = 3;
                        gxa5.f12238c = Double.valueOf(d);
                        gxa = (gxa) g2.mo8770g();
                    } else if (i5 == 3) {
                        String str5 = fqj.f10269b == 4 ? (String) fqj.f10270c : "";
                        if (g2.f14319c) {
                            g2.mo8751b();
                            g2.f14319c = false;
                        }
                        gxa gxa6 = (gxa) g2.f14318b;
                        str5.getClass();
                        gxa6.f12237b = 4;
                        gxa6.f12238c = str5;
                        gxa = (gxa) g2.mo8770g();
                    } else if (i5 == 4) {
                        if (fqj.f10269b == 5) {
                            ihw = (ihw) fqj.f10270c;
                        } else {
                            ihw = ihw.f14203b;
                        }
                        if (g2.f14319c) {
                            g2.mo8751b();
                            g2.f14319c = false;
                        }
                        gxa gxa7 = (gxa) g2.f14318b;
                        ihw.getClass();
                        gxa7.f12237b = 5;
                        gxa7.f12238c = ihw;
                        gxa = (gxa) g2.mo8770g();
                    } else if (i5 == 5) {
                        if (fqj.f10269b == 5) {
                            ihw2 = (ihw) fqj.f10270c;
                        } else {
                            ihw2 = ihw.f14203b;
                        }
                        if (g2.f14319c) {
                            g2.mo8751b();
                            g2.f14319c = false;
                        }
                        gxa gxa8 = (gxa) g2.f14318b;
                        ihw2.getClass();
                        gxa8.f12237b = 6;
                        gxa8.f12238c = ihw2;
                        gxa = (gxa) g2.mo8770g();
                    } else {
                        throw new IllegalStateException("No known flag type");
                    }
                    if (g.f14319c) {
                        g.mo8751b();
                        g.f14319c = false;
                    }
                    gwx gwx3 = (gwx) g.f14318b;
                    gxa.getClass();
                    if (!gwx3.f12229e.mo8521a()) {
                        gwx3.f12229e = iix.m13608a(gwx3.f12229e);
                    }
                    gwx3.f12229e.add(gxa);
                } else {
                    throw null;
                }
            }
            i4++;
            z2 = false;
        }
        ije ije2 = fqi2.f10265f;
        int size2 = ije2.size();
        for (int i6 = 0; i6 < size2; i6++) {
            String str6 = (String) ije2.get(i6);
            if (gzn.f12356b.containsKey(str6)) {
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                gwx gwx4 = (gwx) g.f14318b;
                str6.getClass();
                if (!gwx4.f12230f.mo8521a()) {
                    gwx4.f12230f = iix.m13608a(gwx4.f12230f);
                }
                gwx4.f12230f.add(str6);
            }
        }
        gwx gwx5 = (gwx) g.mo8770g();
        ieh a3 = gzn.mo7222a(gwx5);
        return ibv.m12658a(ife.m12884c(a3, ibv.m12658a(a3, hmq.m11744a((icf) new gze(gzn, gwx5)), (Executor) idh.f13918a), a).mo8443a((Callable) new gzf(a, a3), gzn.f12360f), hmq.m11744a((icf) new gzg(gzn, a3)), (Executor) idh.f13918a);
    }
}
