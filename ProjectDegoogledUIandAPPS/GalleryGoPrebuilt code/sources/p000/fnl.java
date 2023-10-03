package p000;

import android.os.health.HealthStats;
import android.support.p002v7.widget.RecyclerView;
import java.util.Collections;

/* renamed from: fnl */
/* compiled from: PG */
public final class fnl {

    /* renamed from: a */
    private final Long f10090a;

    /* renamed from: b */
    private final Long f10091b;

    /* renamed from: c */
    private final HealthStats f10092c;

    /* renamed from: d */
    private final iqm f10093d;

    /* renamed from: e */
    private final Boolean f10094e;

    /* renamed from: f */
    private final iqx f10095f;

    /* renamed from: g */
    private final /* synthetic */ fnm f10096g;

    public /* synthetic */ fnl(fnm fnm, Long l, Long l2, HealthStats healthStats, iqm iqm, Boolean bool, iqx iqx) {
        this.f10096g = fnm;
        this.f10090a = l;
        this.f10091b = l2;
        this.f10092c = healthStats;
        this.f10093d = iqm;
        this.f10094e = bool;
        this.f10095f = iqx;
    }

    /* renamed from: a */
    public final fnv mo5981a() {
        fnx fnx = this.f10096g.f10097a;
        HealthStats healthStats = this.f10092c;
        iir g = iqw.f14716aq.mo8793g();
        long a = fpt.m9373a(healthStats, 10001);
        long j = 0;
        if (a != 0) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw = (iqw) g.f14318b;
            iqw.f14743a |= 1;
            iqw.f14761c = a;
        }
        long a2 = fpt.m9373a(healthStats, 10002);
        if (a2 != 0) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw2 = (iqw) g.f14318b;
            iqw2.f14743a |= 2;
            iqw2.f14762d = a2;
        }
        long a3 = fpt.m9373a(healthStats, 10003);
        if (a3 != 0) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw3 = (iqw) g.f14318b;
            iqw3.f14743a |= 4;
            iqw3.f14763e = a3;
        }
        long a4 = fpt.m9373a(healthStats, 10004);
        if (a4 != 0) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw4 = (iqw) g.f14318b;
            iqw4.f14743a |= 8;
            iqw4.f14764f = a4;
        }
        g.mo8779k(fpt.m9384c(healthStats, 10005));
        g.mo8780l(fpt.m9384c(healthStats, 10006));
        g.mo8781m(fpt.m9384c(healthStats, 10007));
        g.mo8778j(fpt.m9384c(healthStats, 10008));
        g.mo8777i(fpt.m9384c(healthStats, 10009));
        g.mo8765e((Iterable) fpt.m9384c(healthStats, 10010));
        iqv b = fpt.m9383b(healthStats, 10011);
        if (b != null) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw5 = (iqw) g.f14318b;
            b.getClass();
            iqw5.f14771m = b;
            iqw5.f14743a |= 16;
        }
        g.mo8769f((Iterable) fpt.m9384c(healthStats, 10012));
        g.mo8776h((Iterable) fnr.f10110a.mo5988a(fpt.m9385d(healthStats, 10014)));
        g.mo8773g((Iterable) fnq.f10109a.mo5988a(fpt.m9385d(healthStats, 10015)));
        long a5 = fpt.m9373a(healthStats, 10016);
        if (a5 != 0) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw6 = (iqw) g.f14318b;
            iqw6.f14743a |= 32;
            iqw6.f14776r = a5;
        }
        long a6 = fpt.m9373a(healthStats, 10017);
        if (a6 != 0) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw7 = (iqw) g.f14318b;
            iqw7.f14743a |= 64;
            iqw7.f14777s = a6;
        }
        long a7 = fpt.m9373a(healthStats, 10018);
        if (a7 != 0) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw8 = (iqw) g.f14318b;
            iqw8.f14743a |= 128;
            iqw8.f14778t = a7;
        }
        long a8 = fpt.m9373a(healthStats, 10019);
        if (a8 != 0) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw9 = (iqw) g.f14318b;
            iqw9.f14743a |= 256;
            iqw9.f14779u = a8;
        }
        long a9 = fpt.m9373a(healthStats, 10020);
        if (a9 != 0) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw10 = (iqw) g.f14318b;
            iqw10.f14743a |= 512;
            iqw10.f14780v = a9;
        }
        long a10 = fpt.m9373a(healthStats, 10021);
        if (a10 != 0) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw11 = (iqw) g.f14318b;
            iqw11.f14743a |= 1024;
            iqw11.f14781w = a10;
        }
        long a11 = fpt.m9373a(healthStats, 10022);
        if (a11 != 0) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw12 = (iqw) g.f14318b;
            iqw12.f14743a |= 2048;
            iqw12.f14782x = a11;
        }
        long a12 = fpt.m9373a(healthStats, 10023);
        if (a12 != 0) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw13 = (iqw) g.f14318b;
            iqw13.f14743a |= 4096;
            iqw13.f14718B = a12;
        }
        long a13 = fpt.m9373a(healthStats, 10024);
        if (a13 != 0) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw14 = (iqw) g.f14318b;
            iqw14.f14743a |= 8192;
            iqw14.f14719C = a13;
        }
        long a14 = fpt.m9373a(healthStats, 10025);
        if (a14 != 0) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw15 = (iqw) g.f14318b;
            iqw15.f14743a |= 16384;
            iqw15.f14720D = a14;
        }
        long a15 = fpt.m9373a(healthStats, 10026);
        if (a15 != 0) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw16 = (iqw) g.f14318b;
            iqw16.f14743a |= 32768;
            iqw16.f14721E = a15;
        }
        long a16 = fpt.m9373a(healthStats, 10027);
        if (a16 != 0) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw17 = (iqw) g.f14318b;
            iqw17.f14743a |= 65536;
            iqw17.f14722F = a16;
        }
        long a17 = fpt.m9373a(healthStats, 10028);
        if (a17 != 0) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw18 = (iqw) g.f14318b;
            iqw18.f14743a |= 131072;
            iqw18.f14723G = a17;
        }
        long a18 = fpt.m9373a(healthStats, 10029);
        if (a18 != 0) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw19 = (iqw) g.f14318b;
            iqw19.f14743a |= 262144;
            iqw19.f14724H = a18;
        }
        iqv b2 = fpt.m9383b(healthStats, 10030);
        if (b2 != null) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw20 = (iqw) g.f14318b;
            b2.getClass();
            iqw20.f14725I = b2;
            iqw20.f14743a |= 524288;
        }
        long a19 = fpt.m9373a(healthStats, 10031);
        if (a19 != 0) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw21 = (iqw) g.f14318b;
            iqw21.f14743a |= 1048576;
            iqw21.f14726J = a19;
        }
        iqv b3 = fpt.m9383b(healthStats, 10032);
        if (b3 != null) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw22 = (iqw) g.f14318b;
            b3.getClass();
            iqw22.f14727K = b3;
            iqw22.f14743a |= 2097152;
        }
        iqv b4 = fpt.m9383b(healthStats, 10033);
        if (b4 != null) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw23 = (iqw) g.f14318b;
            b4.getClass();
            iqw23.f14728L = b4;
            iqw23.f14743a |= 4194304;
        }
        iqv b5 = fpt.m9383b(healthStats, 10034);
        if (b5 != null) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw24 = (iqw) g.f14318b;
            b5.getClass();
            iqw24.f14729M = b5;
            iqw24.f14743a |= 8388608;
        }
        iqv b6 = fpt.m9383b(healthStats, 10035);
        if (b6 != null) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw25 = (iqw) g.f14318b;
            b6.getClass();
            iqw25.f14730N = b6;
            iqw25.f14743a |= 16777216;
        }
        iqv b7 = fpt.m9383b(healthStats, 10036);
        if (b7 != null) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw26 = (iqw) g.f14318b;
            b7.getClass();
            iqw26.f14731O = b7;
            iqw26.f14743a |= 33554432;
        }
        iqv b8 = fpt.m9383b(healthStats, 10037);
        if (b8 != null) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw27 = (iqw) g.f14318b;
            b8.getClass();
            iqw27.f14732P = b8;
            iqw27.f14743a |= 67108864;
        }
        iqv b9 = fpt.m9383b(healthStats, 10038);
        if (b9 != null) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw28 = (iqw) g.f14318b;
            b9.getClass();
            iqw28.f14733Q = b9;
            iqw28.f14743a |= 134217728;
        }
        iqv b10 = fpt.m9383b(healthStats, 10039);
        if (b10 != null) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw29 = (iqw) g.f14318b;
            b10.getClass();
            iqw29.f14734R = b10;
            iqw29.f14743a |= 268435456;
        }
        iqv b11 = fpt.m9383b(healthStats, 10040);
        if (b11 != null) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw30 = (iqw) g.f14318b;
            b11.getClass();
            iqw30.f14735S = b11;
            iqw30.f14743a |= 536870912;
        }
        iqv b12 = fpt.m9383b(healthStats, 10041);
        if (b12 != null) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw31 = (iqw) g.f14318b;
            b12.getClass();
            iqw31.f14736T = b12;
            iqw31.f14743a |= 1073741824;
        }
        iqv b13 = fpt.m9383b(healthStats, 10042);
        if (b13 != null) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw32 = (iqw) g.f14318b;
            b13.getClass();
            iqw32.f14737U = b13;
            iqw32.f14743a |= RecyclerView.UNDEFINED_DURATION;
        }
        iqv b14 = fpt.m9383b(healthStats, 10043);
        if (b14 != null) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw33 = (iqw) g.f14318b;
            b14.getClass();
            iqw33.f14738V = b14;
            iqw33.f14760b |= 1;
        }
        iqv b15 = fpt.m9383b(healthStats, 10044);
        if (b15 != null) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw34 = (iqw) g.f14318b;
            b15.getClass();
            iqw34.f14739W = b15;
            iqw34.f14760b |= 2;
        }
        long a20 = fpt.m9373a(healthStats, 10045);
        if (a20 != 0) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw35 = (iqw) g.f14318b;
            iqw35.f14760b |= 4;
            iqw35.f14740X = a20;
        }
        long a21 = fpt.m9373a(healthStats, 10046);
        if (a21 != 0) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw36 = (iqw) g.f14318b;
            iqw36.f14760b |= 8;
            iqw36.f14741Y = a21;
        }
        long a22 = fpt.m9373a(healthStats, 10047);
        if (a22 != 0) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw37 = (iqw) g.f14318b;
            iqw37.f14760b |= 16;
            iqw37.f14742Z = a22;
        }
        long a23 = fpt.m9373a(healthStats, 10048);
        if (a23 != 0) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw38 = (iqw) g.f14318b;
            iqw38.f14760b |= 32;
            iqw38.f14744aa = a23;
        }
        long a24 = fpt.m9373a(healthStats, 10049);
        if (a24 != 0) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw39 = (iqw) g.f14318b;
            iqw39.f14760b |= 64;
            iqw39.f14745ab = a24;
        }
        long a25 = fpt.m9373a(healthStats, 10050);
        if (a25 != 0) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw40 = (iqw) g.f14318b;
            iqw40.f14760b |= 128;
            iqw40.f14746ac = a25;
        }
        long a26 = fpt.m9373a(healthStats, 10051);
        if (a26 != 0) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw41 = (iqw) g.f14318b;
            iqw41.f14760b |= 256;
            iqw41.f14747ad = a26;
        }
        long a27 = fpt.m9373a(healthStats, 10052);
        if (a27 != 0) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw42 = (iqw) g.f14318b;
            iqw42.f14760b |= 512;
            iqw42.f14748ae = a27;
        }
        long a28 = fpt.m9373a(healthStats, 10053);
        if (a28 != 0) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw43 = (iqw) g.f14318b;
            iqw43.f14760b |= 1024;
            iqw43.f14749af = a28;
        }
        long a29 = fpt.m9373a(healthStats, 10054);
        if (a29 != 0) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw44 = (iqw) g.f14318b;
            iqw44.f14760b |= 2048;
            iqw44.f14750ag = a29;
        }
        long a30 = fpt.m9373a(healthStats, 10055);
        if (a30 != 0) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw45 = (iqw) g.f14318b;
            iqw45.f14760b |= 4096;
            iqw45.f14751ah = a30;
        }
        long a31 = fpt.m9373a(healthStats, 10056);
        if (a31 != 0) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw46 = (iqw) g.f14318b;
            iqw46.f14760b |= 8192;
            iqw46.f14752ai = a31;
        }
        long a32 = fpt.m9373a(healthStats, 10057);
        if (a32 != 0) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw47 = (iqw) g.f14318b;
            iqw47.f14760b |= 16384;
            iqw47.f14753aj = a32;
        }
        long a33 = fpt.m9373a(healthStats, 10058);
        if (a33 != 0) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw48 = (iqw) g.f14318b;
            iqw48.f14760b = 32768 | iqw48.f14760b;
            iqw48.f14754ak = a33;
        }
        long a34 = fpt.m9373a(healthStats, 10059);
        if (a34 != 0) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw49 = (iqw) g.f14318b;
            iqw49.f14760b = 65536 | iqw49.f14760b;
            iqw49.f14755al = a34;
        }
        iqv b16 = fpt.m9383b(healthStats, 10061);
        if (b16 != null) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw50 = (iqw) g.f14318b;
            b16.getClass();
            iqw50.f14756am = b16;
            iqw50.f14760b |= 131072;
        }
        long a35 = fpt.m9373a(healthStats, 10062);
        if (a35 != 0) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw51 = (iqw) g.f14318b;
            iqw51.f14760b |= 262144;
            iqw51.f14757an = a35;
        }
        long a36 = fpt.m9373a(healthStats, 10063);
        if (a36 != 0) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw52 = (iqw) g.f14318b;
            iqw52.f14760b |= 524288;
            iqw52.f14758ao = a36;
        }
        long a37 = fpt.m9373a(healthStats, 10064);
        if (a37 != 0) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            iqw iqw53 = (iqw) g.f14318b;
            iqw53.f14760b |= 1048576;
            iqw53.f14759ap = a37;
        }
        iqw iqw54 = (iqw) g.mo8770g();
        iir iir = (iir) iqw54.mo8790b(5);
        iir.mo8503a((iix) iqw54);
        fno fno = fnx.f10124b;
        Collections.unmodifiableList(((iqw) iir.f14318b).f14765g);
        for (int i = 0; i < ((iqw) iir.f14318b).f14765g.size(); i++) {
            iir.mo8764e(i, fno.mo5982a(fnn.WAKELOCK, iir.mo8750b(i)));
        }
        Collections.unmodifiableList(((iqw) iir.f14318b).f14766h);
        for (int i2 = 0; i2 < ((iqw) iir.f14318b).f14766h.size(); i2++) {
            iir.mo8768f(i2, fno.mo5982a(fnn.WAKELOCK, iir.mo8754c(i2)));
        }
        Collections.unmodifiableList(((iqw) iir.f14318b).f14767i);
        for (int i3 = 0; i3 < ((iqw) iir.f14318b).f14767i.size(); i3++) {
            iir.mo8772g(i3, fno.mo5982a(fnn.WAKELOCK, iir.mo8759d(i3)));
        }
        Collections.unmodifiableList(((iqw) iir.f14318b).f14768j);
        for (int i4 = 0; i4 < ((iqw) iir.f14318b).f14768j.size(); i4++) {
            iir.mo8760d(i4, fno.mo5982a(fnn.WAKELOCK, iir.mo8763e(i4)));
        }
        Collections.unmodifiableList(((iqw) iir.f14318b).f14769k);
        for (int i5 = 0; i5 < ((iqw) iir.f14318b).f14769k.size(); i5++) {
            iir.mo8755c(i5, fno.mo5982a(fnn.SYNC, iir.mo8767f(i5)));
        }
        Collections.unmodifiableList(((iqw) iir.f14318b).f14770l);
        for (int i6 = 0; i6 < ((iqw) iir.f14318b).f14770l.size(); i6++) {
            iir.mo8745a(i6, fno.mo5982a(fnn.JOB, iir.mo8771g(i6)));
        }
        Collections.unmodifiableList(((iqw) iir.f14318b).f14772n);
        for (int i7 = 0; i7 < ((iqw) iir.f14318b).f14772n.size(); i7++) {
            iir.mo8752b(i7, fno.mo5982a(fnn.SENSOR, iir.mo8775h(i7)));
        }
        iqw iqw55 = (iqw) iir.mo8770g();
        Long l = this.f10090a;
        Long l2 = this.f10091b;
        Long l3 = ((fjx) this.f10096g.f10099c.mo2652a()).f9862f;
        String str = ((fjx) this.f10096g.f10099c.mo2652a()).f9859c;
        if (str != null) {
            j = (long) str.hashCode();
        }
        return new fnv(iqw55, l, l2, l3, Long.valueOf(j), this.f10093d, (String) null, this.f10094e, this.f10095f);
    }
}
