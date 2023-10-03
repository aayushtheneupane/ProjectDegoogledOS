package p000;

import android.graphics.drawable.Drawable;
import android.support.p002v7.widget.RecyclerView;
import java.util.List;
import java.util.concurrent.Executor;

/* renamed from: beg */
/* compiled from: PG */
public final class beg implements bea, beq, bef {

    /* renamed from: a */
    private final bfy f2145a = bfy.m2451a();

    /* renamed from: b */
    private final Object f2146b;

    /* renamed from: c */
    private final bee f2147c;

    /* renamed from: d */
    private final bec f2148d;

    /* renamed from: e */
    private final apa f2149e;

    /* renamed from: f */
    private final Object f2150f;

    /* renamed from: g */
    private final Class f2151g;

    /* renamed from: h */
    private final bdx f2152h;

    /* renamed from: i */
    private final int f2153i;

    /* renamed from: j */
    private final int f2154j;

    /* renamed from: k */
    private final apb f2155k;

    /* renamed from: l */
    private final ber f2156l;

    /* renamed from: m */
    private final List f2157m;

    /* renamed from: n */
    private final Executor f2158n;

    /* renamed from: o */
    private aua f2159o;

    /* renamed from: p */
    private ati f2160p;

    /* renamed from: q */
    private long f2161q;

    /* renamed from: r */
    private volatile atj f2162r;

    /* renamed from: s */
    private Drawable f2163s;

    /* renamed from: t */
    private int f2164t;

    /* renamed from: u */
    private int f2165u;

    /* renamed from: v */
    private boolean f2166v;

    /* renamed from: w */
    private int f2167w;

    public beg(apa apa, Object obj, Object obj2, Class cls, bdx bdx, int i, int i2, apb apb, ber ber, bee bee, List list, bec bec, atj atj, Executor executor) {
        this.f2146b = obj;
        this.f2149e = apa;
        this.f2150f = obj2;
        this.f2151g = cls;
        this.f2152h = bdx;
        this.f2153i = i;
        this.f2154j = i2;
        this.f2155k = apb;
        this.f2156l = ber;
        this.f2147c = bee;
        this.f2157m = list;
        this.f2148d = bec;
        this.f2162r = atj;
        this.f2158n = executor;
        this.f2167w = 1;
    }

    /* renamed from: i */
    private final void m2305i() {
        if (this.f2166v) {
            throw new IllegalStateException("You can't start or clear loads in RequestListener or Target callbacks. If you're trying to start a fallback request when a load fails, use RequestBuilder#error(RequestBuilder). Otherwise consider posting your into() or clear() calls to the main thread using a Handler instead.");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0075, code lost:
        return;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo1878a() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.f2146b
            monitor-enter(r0)
            r5.m2305i()     // Catch:{ all -> 0x007e }
            bfy r1 = r5.f2145a     // Catch:{ all -> 0x007e }
            r1.mo1973b()     // Catch:{ all -> 0x007e }
            long r1 = p000.bfk.m2412a()     // Catch:{ all -> 0x007e }
            r5.f2161q = r1     // Catch:{ all -> 0x007e }
            java.lang.Object r1 = r5.f2150f     // Catch:{ all -> 0x007e }
            if (r1 != 0) goto L_0x0034
            int r1 = r5.f2153i     // Catch:{ all -> 0x007e }
            int r2 = r5.f2154j     // Catch:{ all -> 0x007e }
            boolean r1 = p000.bfp.m2431a((int) r1, (int) r2)     // Catch:{ all -> 0x007e }
            if (r1 != 0) goto L_0x0020
            goto L_0x0028
        L_0x0020:
            int r1 = r5.f2153i     // Catch:{ all -> 0x007e }
            r5.f2164t = r1     // Catch:{ all -> 0x007e }
            int r1 = r5.f2154j     // Catch:{ all -> 0x007e }
            r5.f2165u = r1     // Catch:{ all -> 0x007e }
        L_0x0028:
            atu r1 = new atu     // Catch:{ all -> 0x007e }
            java.lang.String r2 = "Received null model"
            r1.<init>(r2)     // Catch:{ all -> 0x007e }
            r5.mo1907a((p000.atu) r1)     // Catch:{ all -> 0x007e }
            monitor-exit(r0)     // Catch:{ all -> 0x007e }
            return
        L_0x0034:
            int r1 = r5.f2167w     // Catch:{ all -> 0x007e }
            r2 = 2
            if (r1 == r2) goto L_0x0076
            r3 = 4
            if (r1 != r3) goto L_0x0044
            aua r1 = r5.f2159o     // Catch:{ all -> 0x007e }
            r2 = 5
            r5.mo1904a((p000.aua) r1, (int) r2)     // Catch:{ all -> 0x007e }
            monitor-exit(r0)     // Catch:{ all -> 0x007e }
            return
        L_0x0044:
            r1 = 3
            r5.f2167w = r1     // Catch:{ all -> 0x007e }
            int r3 = r5.f2153i     // Catch:{ all -> 0x007e }
            int r4 = r5.f2154j     // Catch:{ all -> 0x007e }
            boolean r3 = p000.bfp.m2431a((int) r3, (int) r4)     // Catch:{ all -> 0x007e }
            if (r3 == 0) goto L_0x0059
            int r3 = r5.f2153i     // Catch:{ all -> 0x007e }
            int r4 = r5.f2154j     // Catch:{ all -> 0x007e }
            r5.mo1906a((int) r3, (int) r4)     // Catch:{ all -> 0x007e }
            goto L_0x005e
        L_0x0059:
            ber r3 = r5.f2156l     // Catch:{ all -> 0x007e }
            r3.mo1895a((p000.beq) r5)     // Catch:{ all -> 0x007e }
        L_0x005e:
            int r3 = r5.f2167w     // Catch:{ all -> 0x007e }
            if (r3 == r2) goto L_0x0065
            if (r3 == r1) goto L_0x0065
            goto L_0x0074
        L_0x0065:
            boolean r1 = r5.m2307k()     // Catch:{ all -> 0x007e }
            if (r1 == 0) goto L_0x0074
            ber r1 = r5.f2156l     // Catch:{ all -> 0x007e }
            android.graphics.drawable.Drawable r2 = r5.m2306j()     // Catch:{ all -> 0x007e }
            r1.mo1898c(r2)     // Catch:{ all -> 0x007e }
        L_0x0074:
            monitor-exit(r0)     // Catch:{ all -> 0x007e }
            return
        L_0x0076:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x007e }
            java.lang.String r2 = "Cannot restart a running request"
            r1.<init>(r2)     // Catch:{ all -> 0x007e }
            throw r1     // Catch:{ all -> 0x007e }
        L_0x007e:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x007e }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.beg.mo1878a():void");
    }

    /* renamed from: k */
    private final boolean m2307k() {
        bec bec = this.f2148d;
        return bec == null || bec.mo1883c(this);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0046, code lost:
        if (r1.mo1885d(r6) != false) goto L_0x0048;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0055, code lost:
        if (r3 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0057, code lost:
        p000.atj.m1620a(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
        return;
     */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo1880b() {
        /*
            r6 = this;
            java.lang.Object r0 = r6.f2146b
            monitor-enter(r0)
            r6.m2305i()     // Catch:{ all -> 0x005d }
            bfy r1 = r6.f2145a     // Catch:{ all -> 0x005d }
            r1.mo1973b()     // Catch:{ all -> 0x005d }
            int r1 = r6.f2167w     // Catch:{ all -> 0x005d }
            r2 = 6
            if (r1 == r2) goto L_0x005b
            r6.m2305i()     // Catch:{ all -> 0x005d }
            bfy r1 = r6.f2145a     // Catch:{ all -> 0x005d }
            r1.mo1973b()     // Catch:{ all -> 0x005d }
            ber r1 = r6.f2156l     // Catch:{ all -> 0x005d }
            r1.mo1897b((p000.beq) r6)     // Catch:{ all -> 0x005d }
            ati r1 = r6.f2160p     // Catch:{ all -> 0x005d }
            r3 = 0
            if (r1 == 0) goto L_0x0033
            atj r4 = r1.f1615c     // Catch:{ all -> 0x005d }
            monitor-enter(r4)     // Catch:{ all -> 0x005d }
            ato r5 = r1.f1613a     // Catch:{ all -> 0x0030 }
            bef r1 = r1.f1614b     // Catch:{ all -> 0x0030 }
            r5.mo1599a((p000.bef) r1)     // Catch:{ all -> 0x0030 }
            monitor-exit(r4)     // Catch:{ all -> 0x0030 }
            r6.f2160p = r3     // Catch:{ all -> 0x005d }
            goto L_0x0033
        L_0x0030:
            r1 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0030 }
            throw r1     // Catch:{ all -> 0x005d }
        L_0x0033:
            aua r1 = r6.f2159o     // Catch:{ all -> 0x005d }
            if (r1 == 0) goto L_0x003b
            r6.f2159o = r3     // Catch:{ all -> 0x005d }
            r3 = r1
            goto L_0x003d
        L_0x003b:
        L_0x003d:
            bec r1 = r6.f2148d     // Catch:{ all -> 0x005d }
            if (r1 != 0) goto L_0x0042
            goto L_0x0048
        L_0x0042:
            boolean r1 = r1.mo1885d(r6)     // Catch:{ all -> 0x005d }
            if (r1 == 0) goto L_0x0051
        L_0x0048:
            ber r1 = r6.f2156l     // Catch:{ all -> 0x005d }
            android.graphics.drawable.Drawable r4 = r6.m2306j()     // Catch:{ all -> 0x005d }
            r1.mo1798b((android.graphics.drawable.Drawable) r4)     // Catch:{ all -> 0x005d }
        L_0x0051:
            r6.f2167w = r2     // Catch:{ all -> 0x005d }
            monitor-exit(r0)     // Catch:{ all -> 0x005d }
            if (r3 == 0) goto L_0x005a
            p000.atj.m1620a(r3)
        L_0x005a:
            return
        L_0x005b:
            monitor-exit(r0)     // Catch:{ all -> 0x005d }
            return
        L_0x005d:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x005d }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.beg.mo1880b():void");
    }

    /* renamed from: h */
    public final Object mo1905h() {
        this.f2145a.mo1973b();
        return this.f2146b;
    }

    /* renamed from: j */
    private final Drawable m2306j() {
        if (this.f2163s == null) {
            this.f2163s = this.f2152h.f2112f;
        }
        return this.f2163s;
    }

    /* renamed from: g */
    public final boolean mo1890g() {
        boolean z;
        synchronized (this.f2146b) {
            z = this.f2167w == 4;
        }
        return z;
    }

    /* renamed from: f */
    public final boolean mo1889f() {
        boolean z;
        synchronized (this.f2146b) {
            z = this.f2167w == 6;
        }
        return z;
    }

    /* renamed from: e */
    public final boolean mo1887e() {
        boolean z;
        synchronized (this.f2146b) {
            z = this.f2167w == 4;
        }
        return z;
    }

    /* renamed from: a */
    public final boolean mo1879a(bea bea) {
        int i;
        int i2;
        Object obj;
        Class cls;
        bdx bdx;
        apb apb;
        int size;
        int i3;
        int i4;
        Object obj2;
        Class cls2;
        bdx bdx2;
        apb apb2;
        int i5;
        bea bea2 = bea;
        if (!(bea2 instanceof beg)) {
            return false;
        }
        synchronized (this.f2146b) {
            i = this.f2153i;
            i2 = this.f2154j;
            obj = this.f2150f;
            cls = this.f2151g;
            bdx = this.f2152h;
            apb = this.f2155k;
            List list = this.f2157m;
            size = list != null ? list.size() : 0;
        }
        beg beg = (beg) bea2;
        synchronized (beg.f2146b) {
            i3 = beg.f2153i;
            i4 = beg.f2154j;
            obj2 = beg.f2150f;
            cls2 = beg.f2151g;
            bdx2 = beg.f2152h;
            apb2 = beg.f2155k;
            List list2 = beg.f2157m;
            if (list2 != null) {
                i5 = list2.size();
            } else {
                i5 = 0;
            }
        }
        return i == i3 && i2 == i4 && bfp.m2436b(obj, obj2) && cls.equals(cls2) && bdx.equals(bdx2) && apb == apb2 && size == i5;
    }

    /* renamed from: l */
    private final void m2308l() {
        bec bec = this.f2148d;
        if (bec == null || bec.mo1891h().mo1890g()) {
        }
    }

    /* renamed from: d */
    public final boolean mo1884d() {
        boolean z;
        synchronized (this.f2146b) {
            int i = this.f2167w;
            z = true;
            if (!(i == 2 || i == 3)) {
                z = false;
            }
        }
        return z;
    }

    /* renamed from: a */
    private static int m2304a(int i, float f) {
        return i != Integer.MIN_VALUE ? Math.round(f * ((float) i)) : RecyclerView.UNDEFINED_DURATION;
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00d4 A[Catch:{ all -> 0x00d9 }] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo1907a(p000.atu r9) {
        /*
            r8 = this;
            bfy r0 = r8.f2145a
            r0.mo1973b()
            java.lang.Object r0 = r8.f2146b
            monitor-enter(r0)
            apa r1 = r8.f2149e     // Catch:{ all -> 0x00de }
            int r1 = r1.f1320g     // Catch:{ all -> 0x00de }
            java.lang.String r1 = "Glide"
            java.lang.Object r2 = r8.f2150f     // Catch:{ all -> 0x00de }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ all -> 0x00de }
            int r3 = r8.f2164t     // Catch:{ all -> 0x00de }
            int r4 = r8.f2165u     // Catch:{ all -> 0x00de }
            java.lang.String r5 = java.lang.String.valueOf(r2)     // Catch:{ all -> 0x00de }
            int r5 = r5.length()     // Catch:{ all -> 0x00de }
            int r5 = r5 + 52
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x00de }
            r6.<init>(r5)     // Catch:{ all -> 0x00de }
            java.lang.String r5 = "Load failed for "
            r6.append(r5)     // Catch:{ all -> 0x00de }
            r6.append(r2)     // Catch:{ all -> 0x00de }
            java.lang.String r2 = " with size ["
            r6.append(r2)     // Catch:{ all -> 0x00de }
            r6.append(r3)     // Catch:{ all -> 0x00de }
            java.lang.String r2 = "x"
            r6.append(r2)     // Catch:{ all -> 0x00de }
            r6.append(r4)     // Catch:{ all -> 0x00de }
            java.lang.String r2 = "]"
            r6.append(r2)     // Catch:{ all -> 0x00de }
            java.lang.String r2 = r6.toString()     // Catch:{ all -> 0x00de }
            android.util.Log.w(r1, r2, r9)     // Catch:{ all -> 0x00de }
            java.util.List r1 = r9.mo1614a()     // Catch:{ all -> 0x00de }
            int r2 = r1.size()     // Catch:{ all -> 0x00de }
            r3 = 0
            r4 = 0
        L_0x0055:
            if (r4 >= r2) goto L_0x0081
            int r5 = r4 + 1
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x00de }
            r7 = 39
            r6.<init>(r7)     // Catch:{ all -> 0x00de }
            java.lang.String r7 = "Root cause ("
            r6.append(r7)     // Catch:{ all -> 0x00de }
            r6.append(r5)     // Catch:{ all -> 0x00de }
            java.lang.String r7 = " of "
            r6.append(r7)     // Catch:{ all -> 0x00de }
            r6.append(r2)     // Catch:{ all -> 0x00de }
            java.lang.String r7 = ")"
            r6.append(r7)     // Catch:{ all -> 0x00de }
            r6.toString()     // Catch:{ all -> 0x00de }
            java.lang.Object r4 = r1.get(r4)     // Catch:{ all -> 0x00de }
            java.lang.Throwable r4 = (java.lang.Throwable) r4     // Catch:{ all -> 0x00de }
            r4 = r5
            goto L_0x0055
        L_0x0081:
            r1 = 0
            r8.f2160p = r1     // Catch:{ all -> 0x00de }
            r1 = 5
            r8.f2167w = r1     // Catch:{ all -> 0x00de }
            r1 = 1
            r8.f2166v = r1     // Catch:{ all -> 0x00de }
            java.util.List r2 = r8.f2157m     // Catch:{ all -> 0x00d9 }
            if (r2 == 0) goto L_0x00a8
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x00d9 }
            r4 = 0
        L_0x0093:
            boolean r5 = r2.hasNext()     // Catch:{ all -> 0x00d9 }
            if (r5 == 0) goto L_0x00aa
            java.lang.Object r5 = r2.next()     // Catch:{ all -> 0x00d9 }
            bee r5 = (p000.bee) r5     // Catch:{ all -> 0x00d9 }
            r8.m2308l()     // Catch:{ all -> 0x00d9 }
            boolean r5 = r5.mo1486a((p000.atu) r9)     // Catch:{ all -> 0x00d9 }
            r4 = r4 | r5
            goto L_0x0093
        L_0x00a8:
            r4 = 0
        L_0x00aa:
            bee r2 = r8.f2147c     // Catch:{ all -> 0x00d9 }
            if (r2 == 0) goto L_0x00b9
            r8.m2308l()     // Catch:{ all -> 0x00d9 }
            boolean r9 = r2.mo1486a((p000.atu) r9)     // Catch:{ all -> 0x00d9 }
            if (r9 == 0) goto L_0x00b8
            goto L_0x00ba
        L_0x00b8:
        L_0x00b9:
            r1 = 0
        L_0x00ba:
            r9 = r4 | r1
            if (r9 != 0) goto L_0x00cd
            boolean r9 = r8.m2307k()     // Catch:{ all -> 0x00d9 }
            if (r9 == 0) goto L_0x00cd
            android.graphics.drawable.Drawable r9 = r8.m2306j()     // Catch:{ all -> 0x00d9 }
            ber r1 = r8.f2156l     // Catch:{ all -> 0x00d9 }
            r1.mo1432a((android.graphics.drawable.Drawable) r9)     // Catch:{ all -> 0x00d9 }
        L_0x00cd:
            r8.f2166v = r3     // Catch:{ all -> 0x00de }
            bec r9 = r8.f2148d     // Catch:{ all -> 0x00de }
            if (r9 == 0) goto L_0x00d7
            r9.mo1888f(r8)     // Catch:{ all -> 0x00de }
        L_0x00d7:
            monitor-exit(r0)     // Catch:{ all -> 0x00de }
            return
        L_0x00d9:
            r9 = move-exception
            r8.f2166v = r3     // Catch:{ all -> 0x00de }
            throw r9     // Catch:{ all -> 0x00de }
        L_0x00de:
            r9 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00de }
            goto L_0x00e2
        L_0x00e1:
            throw r9
        L_0x00e2:
            goto L_0x00e1
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.beg.mo1907a(atu):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0130, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x01c7, code lost:
        r12 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x01c8, code lost:
        r0 = r11;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo1904a(p000.aua r11, int r12) {
        /*
            r10 = this;
            bfy r0 = r10.f2145a
            r0.mo1973b()
            r0 = 0
            java.lang.Object r1 = r10.f2146b     // Catch:{ all -> 0x01cd }
            monitor-enter(r1)     // Catch:{ all -> 0x01cd }
            r10.f2160p = r0     // Catch:{ all -> 0x01c2 }
            if (r11 != 0) goto L_0x003d
            atu r11 = new atu     // Catch:{ all -> 0x01c2 }
            java.lang.Class r12 = r10.f2151g     // Catch:{ all -> 0x01c2 }
            java.lang.String r12 = java.lang.String.valueOf(r12)     // Catch:{ all -> 0x01c2 }
            java.lang.String r2 = java.lang.String.valueOf(r12)     // Catch:{ all -> 0x01c2 }
            int r2 = r2.length()     // Catch:{ all -> 0x01c2 }
            int r2 = r2 + 82
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x01c2 }
            r3.<init>(r2)     // Catch:{ all -> 0x01c2 }
            java.lang.String r2 = "Expected to receive a Resource<R> with an object of "
            r3.append(r2)     // Catch:{ all -> 0x01c2 }
            r3.append(r12)     // Catch:{ all -> 0x01c2 }
            java.lang.String r12 = " inside, but instead got null."
            r3.append(r12)     // Catch:{ all -> 0x01c2 }
            java.lang.String r12 = r3.toString()     // Catch:{ all -> 0x01c2 }
            r11.<init>(r12)     // Catch:{ all -> 0x01c2 }
            r10.mo1907a((p000.atu) r11)     // Catch:{ all -> 0x01c2 }
            monitor-exit(r1)     // Catch:{ all -> 0x01c2 }
            return
        L_0x003d:
            java.lang.Object r2 = r11.mo1605b()     // Catch:{ all -> 0x01c2 }
            if (r2 == 0) goto L_0x0135
            java.lang.Class r3 = r10.f2151g     // Catch:{ all -> 0x01c2 }
            java.lang.Class r4 = r2.getClass()     // Catch:{ all -> 0x01c2 }
            boolean r3 = r3.isAssignableFrom(r4)     // Catch:{ all -> 0x01c2 }
            if (r3 == 0) goto L_0x0135
            bec r3 = r10.f2148d     // Catch:{ all -> 0x01c2 }
            r4 = 4
            if (r3 == 0) goto L_0x0065
            boolean r3 = r3.mo1881b(r10)     // Catch:{ all -> 0x01c2 }
            if (r3 == 0) goto L_0x005b
            goto L_0x0065
        L_0x005b:
            r10.f2159o = r0     // Catch:{ all -> 0x01c0 }
            r10.f2167w = r4     // Catch:{ all -> 0x01c0 }
            monitor-exit(r1)     // Catch:{ all -> 0x01c0 }
        L_0x0061:
            p000.atj.m1620a(r11)
            return
        L_0x0065:
            r10.m2308l()     // Catch:{ all -> 0x01c2 }
            r10.f2167w = r4     // Catch:{ all -> 0x01c2 }
            r10.f2159o = r11     // Catch:{ all -> 0x01c2 }
            apa r11 = r10.f2149e     // Catch:{ all -> 0x01c2 }
            int r11 = r11.f1320g     // Catch:{ all -> 0x01c2 }
            r3 = 3
            if (r11 > r3) goto L_0x00ed
            java.lang.Class r11 = r2.getClass()     // Catch:{ all -> 0x01c2 }
            java.lang.String r11 = r11.getSimpleName()     // Catch:{ all -> 0x01c2 }
            java.lang.String r12 = p000.C0652xy.m16064a((int) r12)     // Catch:{ all -> 0x01c2 }
            java.lang.Object r3 = r10.f2150f     // Catch:{ all -> 0x01c2 }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x01c2 }
            int r4 = r10.f2164t     // Catch:{ all -> 0x01c2 }
            int r5 = r10.f2165u     // Catch:{ all -> 0x01c2 }
            long r6 = r10.f2161q     // Catch:{ all -> 0x01c2 }
            long r8 = p000.bfk.m2412a()     // Catch:{ all -> 0x01c2 }
            long r8 = r8 - r6
            double r6 = (double) r8     // Catch:{ all -> 0x01c2 }
            double r8 = p000.bfk.f2210a     // Catch:{ all -> 0x01c2 }
            java.lang.Double.isNaN(r6)
            double r6 = r6 * r8
            java.lang.String r8 = java.lang.String.valueOf(r11)     // Catch:{ all -> 0x01c2 }
            int r8 = r8.length()     // Catch:{ all -> 0x01c2 }
            int r8 = r8 + 95
            int r9 = r12.length()     // Catch:{ all -> 0x01c2 }
            int r8 = r8 + r9
            java.lang.String r9 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x01c2 }
            int r9 = r9.length()     // Catch:{ all -> 0x01c2 }
            int r8 = r8 + r9
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x01c2 }
            r9.<init>(r8)     // Catch:{ all -> 0x01c2 }
            java.lang.String r8 = "Finished loading "
            r9.append(r8)     // Catch:{ all -> 0x01c2 }
            r9.append(r11)     // Catch:{ all -> 0x01c2 }
            java.lang.String r11 = " from "
            r9.append(r11)     // Catch:{ all -> 0x01c2 }
            r9.append(r12)     // Catch:{ all -> 0x01c2 }
            java.lang.String r11 = " for "
            r9.append(r11)     // Catch:{ all -> 0x01c2 }
            r9.append(r3)     // Catch:{ all -> 0x01c2 }
            java.lang.String r11 = " with size ["
            r9.append(r11)     // Catch:{ all -> 0x01c2 }
            r9.append(r4)     // Catch:{ all -> 0x01c2 }
            java.lang.String r11 = "x"
            r9.append(r11)     // Catch:{ all -> 0x01c2 }
            r9.append(r5)     // Catch:{ all -> 0x01c2 }
            java.lang.String r11 = "] in "
            r9.append(r11)     // Catch:{ all -> 0x01c2 }
            r9.append(r6)     // Catch:{ all -> 0x01c2 }
            java.lang.String r11 = " ms"
            r9.append(r11)     // Catch:{ all -> 0x01c2 }
            r9.toString()     // Catch:{ all -> 0x01c2 }
        L_0x00ed:
            r11 = 1
            r10.f2166v = r11     // Catch:{ all -> 0x01c2 }
            r12 = 0
            java.util.List r3 = r10.f2157m     // Catch:{ all -> 0x0131 }
            if (r3 != 0) goto L_0x00f7
            r4 = 0
            goto L_0x010e
        L_0x00f7:
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x0131 }
            r4 = 0
        L_0x00fc:
            boolean r5 = r3.hasNext()     // Catch:{ all -> 0x0131 }
            if (r5 == 0) goto L_0x010e
            java.lang.Object r5 = r3.next()     // Catch:{ all -> 0x0131 }
            bee r5 = (p000.bee) r5     // Catch:{ all -> 0x0131 }
            boolean r5 = r5.mo1487a((java.lang.Object) r2)     // Catch:{ all -> 0x0131 }
            r4 = r4 | r5
            goto L_0x00fc
        L_0x010e:
            bee r3 = r10.f2147c     // Catch:{ all -> 0x0131 }
            if (r3 == 0) goto L_0x011a
            boolean r3 = r3.mo1487a((java.lang.Object) r2)     // Catch:{ all -> 0x0131 }
            if (r3 == 0) goto L_0x0119
            goto L_0x011b
        L_0x0119:
        L_0x011a:
            r11 = 0
        L_0x011b:
            r11 = r11 | r4
            if (r11 != 0) goto L_0x0125
            bew r11 = p000.bew.f2191a     // Catch:{ all -> 0x0131 }
            ber r3 = r10.f2156l     // Catch:{ all -> 0x0131 }
            r3.mo1433a(r2, r11)     // Catch:{ all -> 0x0131 }
        L_0x0125:
            r10.f2166v = r12     // Catch:{ all -> 0x01c2 }
            bec r11 = r10.f2148d     // Catch:{ all -> 0x01c2 }
            if (r11 == 0) goto L_0x012f
            r11.mo1886e(r10)     // Catch:{ all -> 0x01c2 }
        L_0x012f:
            monitor-exit(r1)     // Catch:{ all -> 0x01c2 }
            return
        L_0x0131:
            r11 = move-exception
            r10.f2166v = r12     // Catch:{ all -> 0x01c2 }
            throw r11     // Catch:{ all -> 0x01c2 }
        L_0x0135:
            r10.f2159o = r0     // Catch:{ all -> 0x01c0 }
            atu r12 = new atu     // Catch:{ all -> 0x01c0 }
            java.lang.Class r0 = r10.f2151g     // Catch:{ all -> 0x01c0 }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x01c0 }
            if (r2 != 0) goto L_0x0145
            java.lang.String r3 = ""
            goto L_0x0149
        L_0x0145:
            java.lang.Class r3 = r2.getClass()     // Catch:{ all -> 0x01c0 }
        L_0x0149:
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x01c0 }
            java.lang.String r4 = java.lang.String.valueOf(r2)     // Catch:{ all -> 0x01c0 }
            java.lang.String r5 = java.lang.String.valueOf(r11)     // Catch:{ all -> 0x01c0 }
            if (r2 != 0) goto L_0x015a
            java.lang.String r2 = " To indicate failure return a null Resource object, rather than a Resource object containing null data."
            goto L_0x015c
        L_0x015a:
            java.lang.String r2 = ""
        L_0x015c:
            java.lang.String r6 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x01c0 }
            int r6 = r6.length()     // Catch:{ all -> 0x01c0 }
            int r6 = r6 + 71
            java.lang.String r7 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x01c0 }
            int r7 = r7.length()     // Catch:{ all -> 0x01c0 }
            int r6 = r6 + r7
            java.lang.String r7 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x01c0 }
            int r7 = r7.length()     // Catch:{ all -> 0x01c0 }
            int r6 = r6 + r7
            java.lang.String r7 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x01c0 }
            int r7 = r7.length()     // Catch:{ all -> 0x01c0 }
            int r6 = r6 + r7
            int r7 = r2.length()     // Catch:{ all -> 0x01c0 }
            int r6 = r6 + r7
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x01c0 }
            r7.<init>(r6)     // Catch:{ all -> 0x01c0 }
            java.lang.String r6 = "Expected to receive an object of "
            r7.append(r6)     // Catch:{ all -> 0x01c0 }
            r7.append(r0)     // Catch:{ all -> 0x01c0 }
            java.lang.String r0 = " but instead got "
            r7.append(r0)     // Catch:{ all -> 0x01c0 }
            r7.append(r3)     // Catch:{ all -> 0x01c0 }
            java.lang.String r0 = "{"
            r7.append(r0)     // Catch:{ all -> 0x01c0 }
            r7.append(r4)     // Catch:{ all -> 0x01c0 }
            java.lang.String r0 = "} inside Resource{"
            r7.append(r0)     // Catch:{ all -> 0x01c0 }
            r7.append(r5)     // Catch:{ all -> 0x01c0 }
            java.lang.String r0 = "}."
            r7.append(r0)     // Catch:{ all -> 0x01c0 }
            r7.append(r2)     // Catch:{ all -> 0x01c0 }
            java.lang.String r0 = r7.toString()     // Catch:{ all -> 0x01c0 }
            r12.<init>(r0)     // Catch:{ all -> 0x01c0 }
            r10.mo1907a((p000.atu) r12)     // Catch:{ all -> 0x01c0 }
            monitor-exit(r1)     // Catch:{ all -> 0x01c0 }
            goto L_0x0061
        L_0x01c0:
            r12 = move-exception
            goto L_0x01cc
        L_0x01c2:
            r11 = move-exception
            r12 = r11
            r11 = r0
        L_0x01c5:
            monitor-exit(r1)     // Catch:{ all -> 0x01cb }
            throw r12     // Catch:{ all -> 0x01c7 }
        L_0x01c7:
            r12 = move-exception
            r0 = r11
            goto L_0x01cf
        L_0x01cb:
            r12 = move-exception
        L_0x01cc:
            goto L_0x01c5
        L_0x01cd:
            r11 = move-exception
            r12 = r11
        L_0x01cf:
            if (r0 == 0) goto L_0x01d4
            p000.atj.m1620a(r0)
        L_0x01d4:
            goto L_0x01d6
        L_0x01d5:
            throw r12
        L_0x01d6:
            goto L_0x01d5
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.beg.mo1904a(aua, int):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
        r1.mo1904a(r7, 5);
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x017d, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00b6 A[Catch:{ all -> 0x00ad }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0169 A[Catch:{ all -> 0x017e }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0179 A[Catch:{ all -> 0x0181, all -> 0x0187 }] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo1906a(int r28, int r29) {
        /*
            r27 = this;
            r1 = r27
            bfy r0 = r1.f2145a
            r0.mo1973b()
            java.lang.Object r2 = r1.f2146b
            monitor-enter(r2)
            int r0 = r1.f2167w     // Catch:{ all -> 0x0187 }
            r3 = 3
            if (r0 != r3) goto L_0x0185
            r0 = 2
            r1.f2167w = r0     // Catch:{ all -> 0x0187 }
            bdx r3 = r1.f2152h     // Catch:{ all -> 0x0187 }
            float r3 = r3.f2109c     // Catch:{ all -> 0x0187 }
            r4 = r28
            int r4 = m2304a((int) r4, (float) r3)     // Catch:{ all -> 0x0187 }
            r1.f2164t = r4     // Catch:{ all -> 0x0187 }
            r4 = r29
            int r3 = m2304a((int) r4, (float) r3)     // Catch:{ all -> 0x0187 }
            r1.f2165u = r3     // Catch:{ all -> 0x0187 }
            atj r3 = r1.f2162r     // Catch:{ all -> 0x0187 }
            apa r4 = r1.f2149e     // Catch:{ all -> 0x0187 }
            java.lang.Object r14 = r1.f2150f     // Catch:{ all -> 0x0187 }
            bdx r5 = r1.f2152h     // Catch:{ all -> 0x0187 }
            aqu r15 = r5.f2116j     // Catch:{ all -> 0x0187 }
            int r13 = r1.f2164t     // Catch:{ all -> 0x0187 }
            int r12 = r1.f2165u     // Catch:{ all -> 0x0187 }
            java.lang.Class r11 = r5.f2121o     // Catch:{ all -> 0x0187 }
            java.lang.Class r10 = r1.f2151g     // Catch:{ all -> 0x0187 }
            apb r9 = r1.f2155k     // Catch:{ all -> 0x0187 }
            atc r8 = r5.f2110d     // Catch:{ all -> 0x0187 }
            java.util.Map r7 = r5.f2120n     // Catch:{ all -> 0x0187 }
            boolean r6 = r5.f2117k     // Catch:{ all -> 0x0187 }
            boolean r0 = r5.f2123q     // Catch:{ all -> 0x0187 }
            r28 = r0
            aqz r0 = r5.f2119m     // Catch:{ all -> 0x0187 }
            r29 = r4
            boolean r4 = r5.f2113g     // Catch:{ all -> 0x0187 }
            r16 = r4
            boolean r4 = r5.f2124r     // Catch:{ all -> 0x0187 }
            boolean r5 = r5.f2122p     // Catch:{ all -> 0x0187 }
            r17 = r4
            java.util.concurrent.Executor r4 = r1.f2158n     // Catch:{ all -> 0x0187 }
            atq r1 = new atq     // Catch:{ all -> 0x0181 }
            r18 = r4
            r4 = r5
            r5 = r1
            r19 = r6
            r6 = r14
            r20 = r7
            r7 = r15
            r21 = r8
            r8 = r13
            r22 = r9
            r9 = r12
            r23 = r10
            r10 = r20
            r24 = r11
            r25 = r12
            r12 = r23
            r26 = r13
            r13 = r0
            r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13)     // Catch:{ all -> 0x0181 }
            monitor-enter(r3)     // Catch:{ all -> 0x0181 }
            r5 = 1
            if (r16 == 0) goto L_0x00b2
            ask r7 = r3.f1622g     // Catch:{ all -> 0x00ad }
            ats r7 = r7.mo1549b(r1)     // Catch:{ all -> 0x00ad }
            if (r7 == 0) goto L_0x0085
            r7.mo1608e()     // Catch:{ all -> 0x00ad }
        L_0x0085:
            if (r7 != 0) goto L_0x00b4
            avo r7 = r3.f1617b     // Catch:{ all -> 0x00ad }
            aua r7 = r7.mo1671a(r1)     // Catch:{ all -> 0x00ad }
            if (r7 == 0) goto L_0x009d
            boolean r8 = r7 instanceof p000.ats     // Catch:{ all -> 0x00ad }
            if (r8 != 0) goto L_0x009a
            ats r8 = new ats     // Catch:{ all -> 0x00ad }
            r8.<init>(r7, r5, r1, r3)     // Catch:{ all -> 0x00ad }
            r7 = r8
            goto L_0x009f
        L_0x009a:
            ats r7 = (p000.ats) r7     // Catch:{ all -> 0x00ad }
            goto L_0x009f
        L_0x009d:
            r7 = 0
        L_0x009f:
            if (r7 == 0) goto L_0x00a9
            r7.mo1608e()     // Catch:{ all -> 0x00ad }
            ask r8 = r3.f1622g     // Catch:{ all -> 0x00ad }
            r8.mo1547a(r1, r7)     // Catch:{ all -> 0x00ad }
        L_0x00a9:
            if (r7 == 0) goto L_0x00ac
            goto L_0x00b4
        L_0x00ac:
            goto L_0x00b3
        L_0x00ad:
            r0 = move-exception
            r1 = r27
            goto L_0x017f
        L_0x00b2:
        L_0x00b3:
            r7 = 0
        L_0x00b4:
            if (r7 != 0) goto L_0x0169
            atw r7 = r3.f1616a     // Catch:{ all -> 0x00ad }
            java.util.Map r7 = r7.mo1622a(r4)     // Catch:{ all -> 0x00ad }
            java.lang.Object r7 = r7.get(r1)     // Catch:{ all -> 0x00ad }
            ato r7 = (p000.ato) r7     // Catch:{ all -> 0x00ad }
            if (r7 != 0) goto L_0x0159
            atg r7 = r3.f1618c     // Catch:{ all -> 0x00ad }
            lc r7 = r7.f1610f     // Catch:{ all -> 0x00ad }
            java.lang.Object r7 = r7.mo1971a()     // Catch:{ all -> 0x00ad }
            ato r7 = (p000.ato) r7     // Catch:{ all -> 0x00ad }
            java.lang.Object r7 = p000.cns.m4632a((java.lang.Object) r7)     // Catch:{ all -> 0x00ad }
            ato r7 = (p000.ato) r7     // Catch:{ all -> 0x00ad }
            r8 = r16
            r9 = r17
            r7.mo1598a(r1, r8, r9, r4)     // Catch:{ all -> 0x00ad }
            ate r8 = r3.f1621f     // Catch:{ all -> 0x00ad }
            lc r9 = r8.f1602b     // Catch:{ all -> 0x00ad }
            java.lang.Object r9 = r9.mo1971a()     // Catch:{ all -> 0x00ad }
            asx r9 = (p000.asx) r9     // Catch:{ all -> 0x00ad }
            java.lang.Object r9 = p000.cns.m4632a((java.lang.Object) r9)     // Catch:{ all -> 0x00ad }
            asx r9 = (p000.asx) r9     // Catch:{ all -> 0x00ad }
            int r10 = r8.f1603c     // Catch:{ all -> 0x00ad }
            int r11 = r10 + 1
            r8.f1603c = r11     // Catch:{ all -> 0x00ad }
            asr r8 = r9.f1566a     // Catch:{ all -> 0x00ad }
            asv r11 = r9.f1567b     // Catch:{ all -> 0x00ad }
            r12 = r29
            r8.f1539c = r12     // Catch:{ all -> 0x00ad }
            r8.f1540d = r14     // Catch:{ all -> 0x00ad }
            r8.f1550n = r15     // Catch:{ all -> 0x00ad }
            r13 = r26
            r8.f1541e = r13     // Catch:{ all -> 0x00ad }
            r14 = r25
            r8.f1542f = r14     // Catch:{ all -> 0x00ad }
            r6 = r21
            r8.f1552p = r6     // Catch:{ all -> 0x00ad }
            r5 = r24
            r8.f1543g = r5     // Catch:{ all -> 0x00ad }
            r8.f1544h = r11     // Catch:{ all -> 0x00ad }
            r5 = r23
            r8.f1547k = r5     // Catch:{ all -> 0x00ad }
            r5 = r22
            r8.f1551o = r5     // Catch:{ all -> 0x00ad }
            r8.f1545i = r0     // Catch:{ all -> 0x00ad }
            r11 = r20
            r8.f1546j = r11     // Catch:{ all -> 0x00ad }
            r11 = r19
            r8.f1553q = r11     // Catch:{ all -> 0x00ad }
            r11 = r28
            r8.f1554r = r11     // Catch:{ all -> 0x00ad }
            r9.f1569d = r12     // Catch:{ all -> 0x00ad }
            r9.f1570e = r15     // Catch:{ all -> 0x00ad }
            r9.f1571f = r5     // Catch:{ all -> 0x00ad }
            r9.f1572g = r1     // Catch:{ all -> 0x00ad }
            r9.f1573h = r13     // Catch:{ all -> 0x00ad }
            r9.f1574i = r14     // Catch:{ all -> 0x00ad }
            r9.f1575j = r6     // Catch:{ all -> 0x00ad }
            r9.f1579n = r4     // Catch:{ all -> 0x00ad }
            r9.f1576k = r0     // Catch:{ all -> 0x00ad }
            r9.f1577l = r7     // Catch:{ all -> 0x00ad }
            r9.f1578m = r10     // Catch:{ all -> 0x00ad }
            r0 = 1
            r9.f1582q = r0     // Catch:{ all -> 0x00ad }
            atw r0 = r3.f1616a     // Catch:{ all -> 0x00ad }
            boolean r4 = r7.f1636g     // Catch:{ all -> 0x00ad }
            java.util.Map r0 = r0.mo1622a(r4)     // Catch:{ all -> 0x00ad }
            r0.put(r1, r7)     // Catch:{ all -> 0x00ad }
            r1 = r27
            r0 = r18
            r7.mo1600a(r1, r0)     // Catch:{ all -> 0x017e }
            r7.mo1601b(r9)     // Catch:{ all -> 0x017e }
            ati r0 = new ati     // Catch:{ all -> 0x017e }
            r0.<init>(r3, r1, r7)     // Catch:{ all -> 0x017e }
            goto L_0x0167
        L_0x0159:
            r1 = r27
            r0 = r18
            r7.mo1600a(r1, r0)     // Catch:{ all -> 0x017e }
            ati r0 = new ati     // Catch:{ all -> 0x017e }
            r0.<init>(r3, r1, r7)     // Catch:{ all -> 0x017e }
        L_0x0167:
            monitor-exit(r3)     // Catch:{ all -> 0x017e }
            goto L_0x0172
        L_0x0169:
            r1 = r27
            monitor-exit(r3)     // Catch:{ all -> 0x017e }
            r0 = 5
            r1.mo1904a(r7, r0)     // Catch:{ all -> 0x0187 }
            r0 = 0
        L_0x0172:
            r1.f2160p = r0     // Catch:{ all -> 0x0187 }
            int r0 = r1.f2167w     // Catch:{ all -> 0x0187 }
            r3 = 2
            if (r0 == r3) goto L_0x017c
            r0 = 0
            r1.f2160p = r0     // Catch:{ all -> 0x0187 }
        L_0x017c:
            monitor-exit(r2)     // Catch:{ all -> 0x0187 }
            return
        L_0x017e:
            r0 = move-exception
        L_0x017f:
            monitor-exit(r3)     // Catch:{ all -> 0x017e }
            throw r0     // Catch:{ all -> 0x0187 }
        L_0x0181:
            r0 = move-exception
            r1 = r27
            goto L_0x0188
        L_0x0185:
            monitor-exit(r2)     // Catch:{ all -> 0x0187 }
            return
        L_0x0187:
            r0 = move-exception
        L_0x0188:
            monitor-exit(r2)     // Catch:{ all -> 0x0187 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.beg.mo1906a(int, int):void");
    }

    /* renamed from: c */
    public final void mo1882c() {
        synchronized (this.f2146b) {
            if (mo1884d()) {
                mo1880b();
            }
        }
    }
}
