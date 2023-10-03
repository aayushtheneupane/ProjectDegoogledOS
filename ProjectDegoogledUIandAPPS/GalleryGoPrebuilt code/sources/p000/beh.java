package p000;

/* renamed from: beh */
/* compiled from: PG */
public final class beh implements bec, bea {

    /* renamed from: a */
    public volatile bea f2168a;

    /* renamed from: b */
    public volatile bea f2169b;

    /* renamed from: c */
    private final bec f2170c;

    /* renamed from: d */
    private final Object f2171d;

    /* renamed from: e */
    private beb f2172e = beb.CLEARED;

    /* renamed from: f */
    private beb f2173f = beb.CLEARED;

    /* renamed from: g */
    private boolean f2174g;

    public beh(Object obj, bec bec) {
        this.f2171d = obj;
        this.f2170c = bec;
    }

    /* renamed from: a */
    public final void mo1878a() {
        synchronized (this.f2171d) {
            this.f2174g = true;
            try {
                if (!(this.f2172e == beb.SUCCESS || this.f2173f == beb.RUNNING)) {
                    this.f2173f = beb.RUNNING;
                    this.f2169b.mo1878a();
                }
                if (this.f2174g && this.f2172e != beb.RUNNING) {
                    this.f2172e = beb.RUNNING;
                    this.f2168a.mo1878a();
                }
            } finally {
                this.f2174g = false;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x000d, code lost:
        if (r1.mo1885d(r3) == false) goto L_0x000f;
     */
    /* renamed from: d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean mo1885d(p000.bea r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.f2171d
            monitor-enter(r0)
            bec r1 = r3.f2170c     // Catch:{ all -> 0x0021 }
            r2 = 0
            if (r1 != 0) goto L_0x0009
            goto L_0x0010
        L_0x0009:
            boolean r1 = r1.mo1885d(r3)     // Catch:{ all -> 0x0021 }
            if (r1 != 0) goto L_0x0010
        L_0x000f:
            goto L_0x001f
        L_0x0010:
            bea r1 = r3.f2168a     // Catch:{ all -> 0x0021 }
            boolean r4 = r4.equals(r1)     // Catch:{ all -> 0x0021 }
            if (r4 == 0) goto L_0x000f
            beb r4 = r3.f2172e     // Catch:{ all -> 0x0021 }
            beb r1 = p000.beb.PAUSED     // Catch:{ all -> 0x0021 }
            if (r4 == r1) goto L_0x000f
            r2 = 1
        L_0x001f:
            monitor-exit(r0)     // Catch:{ all -> 0x0021 }
            return r2
        L_0x0021:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0021 }
            goto L_0x0025
        L_0x0024:
            throw r4
        L_0x0025:
            goto L_0x0024
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.beh.mo1885d(bea):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x000d, code lost:
        if (r1.mo1883c(r3) == false) goto L_0x000f;
     */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean mo1883c(p000.bea r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.f2171d
            monitor-enter(r0)
            bec r1 = r3.f2170c     // Catch:{ all -> 0x0021 }
            r2 = 0
            if (r1 != 0) goto L_0x0009
            goto L_0x0010
        L_0x0009:
            boolean r1 = r1.mo1883c(r3)     // Catch:{ all -> 0x0021 }
            if (r1 != 0) goto L_0x0010
        L_0x000f:
            goto L_0x001f
        L_0x0010:
            bea r1 = r3.f2168a     // Catch:{ all -> 0x0021 }
            boolean r4 = r4.equals(r1)     // Catch:{ all -> 0x0021 }
            if (r4 == 0) goto L_0x000f
            boolean r4 = r3.mo1890g()     // Catch:{ all -> 0x0021 }
            if (r4 != 0) goto L_0x000f
            r2 = 1
        L_0x001f:
            monitor-exit(r0)     // Catch:{ all -> 0x0021 }
            return r2
        L_0x0021:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0021 }
            goto L_0x0025
        L_0x0024:
            throw r4
        L_0x0025:
            goto L_0x0024
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.beh.mo1883c(bea):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        if (r4.f2172e == p000.beb.f2135d) goto L_0x0010;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x000e, code lost:
        if (r1.mo1881b(r4) == false) goto L_0x0010;
     */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean mo1881b(p000.bea r5) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.f2171d
            monitor-enter(r0)
            bec r1 = r4.f2170c     // Catch:{ all -> 0x0022 }
            r2 = 1
            r3 = 0
            if (r1 != 0) goto L_0x000a
            goto L_0x0012
        L_0x000a:
            boolean r1 = r1.mo1881b(r4)     // Catch:{ all -> 0x0022 }
            if (r1 != 0) goto L_0x0012
        L_0x0010:
            r2 = 0
            goto L_0x0020
        L_0x0012:
            bea r1 = r4.f2168a     // Catch:{ all -> 0x0022 }
            boolean r5 = r5.equals(r1)     // Catch:{ all -> 0x0022 }
            if (r5 != 0) goto L_0x0020
            beb r5 = r4.f2172e     // Catch:{ all -> 0x0022 }
            beb r1 = p000.beb.SUCCESS     // Catch:{ all -> 0x0022 }
            if (r5 == r1) goto L_0x0010
        L_0x0020:
            monitor-exit(r0)     // Catch:{ all -> 0x0022 }
            return r2
        L_0x0022:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0022 }
            goto L_0x0026
        L_0x0025:
            throw r5
        L_0x0026:
            goto L_0x0025
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.beh.mo1881b(bea):boolean");
    }

    /* renamed from: b */
    public final void mo1880b() {
        synchronized (this.f2171d) {
            this.f2174g = false;
            this.f2172e = beb.CLEARED;
            this.f2173f = beb.CLEARED;
            this.f2169b.mo1880b();
            this.f2168a.mo1880b();
        }
    }

    /* renamed from: h */
    public final bec mo1891h() {
        bec h;
        synchronized (this.f2171d) {
            bec bec = this.f2170c;
            h = bec != null ? bec.mo1891h() : this;
        }
        return h;
    }

    /* renamed from: g */
    public final boolean mo1890g() {
        boolean z;
        synchronized (this.f2171d) {
            z = true;
            if (!this.f2169b.mo1890g() && !this.f2168a.mo1890g()) {
                z = false;
            }
        }
        return z;
    }

    /* renamed from: f */
    public final boolean mo1889f() {
        boolean z;
        synchronized (this.f2171d) {
            z = this.f2172e == beb.CLEARED;
        }
        return z;
    }

    /* renamed from: e */
    public final boolean mo1887e() {
        boolean z;
        synchronized (this.f2171d) {
            z = this.f2172e == beb.SUCCESS;
        }
        return z;
    }

    /* renamed from: a */
    public final boolean mo1879a(bea bea) {
        if (bea instanceof beh) {
            beh beh = (beh) bea;
            if (this.f2168a == null ? beh.f2168a == null : this.f2168a.mo1879a(beh.f2168a)) {
                if (this.f2169b != null) {
                    if (this.f2169b.mo1879a(beh.f2169b)) {
                        return true;
                    }
                } else if (beh.f2169b != null) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    /* renamed from: d */
    public final boolean mo1884d() {
        boolean z;
        synchronized (this.f2171d) {
            z = this.f2172e == beb.RUNNING;
        }
        return z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0017, code lost:
        return;
     */
    /* renamed from: f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo1888f(p000.bea r3) {
        /*
            r2 = this;
            java.lang.Object r0 = r2.f2171d
            monitor-enter(r0)
            bea r1 = r2.f2168a     // Catch:{ all -> 0x001e }
            boolean r3 = r3.equals(r1)     // Catch:{ all -> 0x001e }
            if (r3 == 0) goto L_0x0018
            beb r3 = p000.beb.FAILED     // Catch:{ all -> 0x001e }
            r2.f2172e = r3     // Catch:{ all -> 0x001e }
            bec r3 = r2.f2170c     // Catch:{ all -> 0x001e }
            if (r3 == 0) goto L_0x0016
            r3.mo1888f(r2)     // Catch:{ all -> 0x001e }
        L_0x0016:
            monitor-exit(r0)     // Catch:{ all -> 0x001e }
            return
        L_0x0018:
            beb r3 = p000.beb.FAILED     // Catch:{ all -> 0x001e }
            r2.f2173f = r3     // Catch:{ all -> 0x001e }
            monitor-exit(r0)     // Catch:{ all -> 0x001e }
            return
        L_0x001e:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x001e }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.beh.mo1888f(bea):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0023, code lost:
        return;
     */
    /* renamed from: e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo1886e(p000.bea r3) {
        /*
            r2 = this;
            java.lang.Object r0 = r2.f2171d
            monitor-enter(r0)
            bea r1 = r2.f2169b     // Catch:{ all -> 0x002a }
            boolean r3 = r3.equals(r1)     // Catch:{ all -> 0x002a }
            if (r3 != 0) goto L_0x0024
            beb r3 = p000.beb.SUCCESS     // Catch:{ all -> 0x002a }
            r2.f2172e = r3     // Catch:{ all -> 0x002a }
            bec r3 = r2.f2170c     // Catch:{ all -> 0x002a }
            if (r3 != 0) goto L_0x0014
            goto L_0x0017
        L_0x0014:
            r3.mo1886e(r2)     // Catch:{ all -> 0x002a }
        L_0x0017:
            beb r3 = r2.f2173f     // Catch:{ all -> 0x002a }
            boolean r3 = r3.f2138f     // Catch:{ all -> 0x002a }
            if (r3 != 0) goto L_0x0022
            bea r3 = r2.f2169b     // Catch:{ all -> 0x002a }
            r3.mo1880b()     // Catch:{ all -> 0x002a }
        L_0x0022:
            monitor-exit(r0)     // Catch:{ all -> 0x002a }
            return
        L_0x0024:
            beb r3 = p000.beb.SUCCESS     // Catch:{ all -> 0x002a }
            r2.f2173f = r3     // Catch:{ all -> 0x002a }
            monitor-exit(r0)     // Catch:{ all -> 0x002a }
            return
        L_0x002a:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002a }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.beh.mo1886e(bea):void");
    }

    /* renamed from: c */
    public final void mo1882c() {
        synchronized (this.f2171d) {
            if (!this.f2173f.f2138f) {
                this.f2173f = beb.PAUSED;
                this.f2169b.mo1882c();
            }
            if (!this.f2172e.f2138f) {
                this.f2172e = beb.PAUSED;
                this.f2168a.mo1882c();
            }
        }
    }
}
