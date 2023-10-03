package p000;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* renamed from: apn */
/* compiled from: PG */
public class apn implements ComponentCallbacks2, bcw {

    /* renamed from: k */
    private static final bdx f1354k = bdx.m2223b(Bitmap.class).mo1871j();

    /* renamed from: a */
    public final aow f1355a;

    /* renamed from: b */
    public final Context f1356b;

    /* renamed from: c */
    public final bcv f1357c;

    /* renamed from: d */
    public final CopyOnWriteArrayList f1358d;

    /* renamed from: e */
    private final bde f1359e;

    /* renamed from: f */
    private final bdd f1360f;

    /* renamed from: g */
    private final bdh f1361g = new bdh();

    /* renamed from: h */
    private final Runnable f1362h = new apk(this);

    /* renamed from: i */
    private final Handler f1363i = new Handler(Looper.getMainLooper());

    /* renamed from: j */
    private final bcp f1364j;

    /* renamed from: l */
    private bdx f1365l;

    static {
        bdx.m2223b(bbt.class).mo1871j();
        bdx.m2221b(atc.f1598b).mo1852a(apb.LOW).mo1876o();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: g */
    public final synchronized bdx mo1447g() {
        return this.f1365l;
    }

    public final void onConfigurationChanged(Configuration configuration) {
    }

    public final void onLowMemory() {
    }

    public final void onTrimMemory(int i) {
    }

    public apn(aow aow, bcv bcv, bdd bdd, Context context) {
        bcp bcp;
        bde bde = new bde();
        this.f1355a = aow;
        this.f1357c = bcv;
        this.f1360f = bdd;
        this.f1359e = bde;
        this.f1356b = context;
        Context applicationContext = context.getApplicationContext();
        apm apm = new apm(this, bde);
        if (applicationContext.checkPermission("android.permission.ACCESS_NETWORK_STATE", Process.myPid(), Process.myUid()) != 0) {
            bcp = new bcx();
        } else {
            bcp = new bcs(applicationContext, apm);
        }
        this.f1364j = bcp;
        if (bfp.m2439d()) {
            this.f1363i.post(this.f1362h);
        } else {
            bcv.mo1812a(this);
        }
        bcv.mo1812a(this.f1364j);
        this.f1358d = new CopyOnWriteArrayList(aow.f1290c.f1317d);
        mo1439a(aow.f1290c.mo1402a());
        synchronized (aow.f1294g) {
            if (!aow.f1294g.contains(this)) {
                aow.f1294g.add(this);
            } else {
                throw new IllegalStateException("Cannot register already registered manager");
            }
        }
    }

    /* renamed from: a */
    public apj mo1435a(Class cls) {
        return new apj(this.f1355a, this, cls);
    }

    /* renamed from: e */
    public apj mo1445e() {
        return mo1435a(Bitmap.class).mo1426b(f1354k);
    }

    /* renamed from: f */
    public apj mo1446f() {
        return mo1435a(Drawable.class);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002c, code lost:
        if (r1 != null) goto L_0x002f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002f, code lost:
        r5.mo1894a((p000.bea) null);
        r1.mo1880b();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0036, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        return;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo1440a(p000.ber r5) {
        /*
            r4 = this;
            if (r5 == 0) goto L_0x003a
            boolean r0 = r4.m1391b(r5)
            bea r1 = r5.mo1896ae()
            if (r0 != 0) goto L_0x003a
            aow r0 = r4.f1355a
            java.util.List r2 = r0.f1294g
            monitor-enter(r2)
            java.util.List r0 = r0.f1294g     // Catch:{ all -> 0x0037 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x0037 }
        L_0x0017:
            boolean r3 = r0.hasNext()     // Catch:{ all -> 0x0037 }
            if (r3 == 0) goto L_0x002b
            java.lang.Object r3 = r0.next()     // Catch:{ all -> 0x0037 }
            apn r3 = (p000.apn) r3     // Catch:{ all -> 0x0037 }
            boolean r3 = r3.m1391b(r5)     // Catch:{ all -> 0x0037 }
            if (r3 == 0) goto L_0x0017
            monitor-exit(r2)     // Catch:{ all -> 0x0037 }
            return
        L_0x002b:
            monitor-exit(r2)     // Catch:{ all -> 0x0037 }
            if (r1 != 0) goto L_0x002f
            goto L_0x003a
        L_0x002f:
            r0 = 0
            r5.mo1894a((p000.bea) r0)
            r1.mo1880b()
            return
        L_0x0037:
            r5 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0037 }
            throw r5
        L_0x003a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.apn.mo1440a(ber):void");
    }

    /* renamed from: a */
    public apj mo1434a(Uri uri) {
        return mo1446f().mo1415a(uri);
    }

    /* renamed from: a */
    public apj mo1436a(Object obj) {
        return mo1446f().mo1419a(obj);
    }

    /* renamed from: a */
    public apj mo1437a(String str) {
        return mo1446f().mo1420a(str);
    }

    /* renamed from: d */
    public final synchronized void mo1444d() {
        this.f1361g.mo1444d();
        List a = bfp.m2428a((Collection) this.f1361g.f2088a);
        int size = a.size();
        for (int i = 0; i < size; i++) {
            mo1440a((ber) a.get(i));
        }
        this.f1361g.f2088a.clear();
        bde bde = this.f1359e;
        List a2 = bfp.m2428a((Collection) bde.f2078a);
        int size2 = a2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            bde.mo1829a((bea) a2.get(i2));
        }
        bde.f2079b.clear();
        this.f1357c.mo1814b(this);
        this.f1357c.mo1814b(this.f1364j);
        this.f1363i.removeCallbacks(this.f1362h);
        aow aow = this.f1355a;
        synchronized (aow.f1294g) {
            if (aow.f1294g.contains(this)) {
                aow.f1294g.remove(this);
            } else {
                throw new IllegalStateException("Cannot unregister not yet registered manager");
            }
        }
    }

    /* renamed from: b */
    public final synchronized void mo1442b() {
        m1393i();
        this.f1361g.mo1442b();
    }

    /* renamed from: c */
    public final synchronized void mo1443c() {
        m1392h();
        this.f1361g.mo1443c();
    }

    /* renamed from: a */
    public final synchronized void mo1438a() {
        bde bde = this.f1359e;
        bde.f2080c = true;
        List a = bfp.m2428a((Collection) bde.f2078a);
        int size = a.size();
        for (int i = 0; i < size; i++) {
            bea bea = (bea) a.get(i);
            if (bea.mo1884d() || bea.mo1887e()) {
                bea.mo1880b();
                bde.f2079b.add(bea);
            }
        }
    }

    /* renamed from: h */
    private final synchronized void m1392h() {
        bde bde = this.f1359e;
        bde.f2080c = true;
        List a = bfp.m2428a((Collection) bde.f2078a);
        int size = a.size();
        for (int i = 0; i < size; i++) {
            bea bea = (bea) a.get(i);
            if (bea.mo1884d()) {
                bea.mo1882c();
                bde.f2079b.add(bea);
            }
        }
    }

    /* renamed from: i */
    private final synchronized void m1393i() {
        bde bde = this.f1359e;
        bde.f2080c = false;
        List a = bfp.m2428a((Collection) bde.f2078a);
        int size = a.size();
        for (int i = 0; i < size; i++) {
            bea bea = (bea) a.get(i);
            if (!bea.mo1887e() && !bea.mo1884d()) {
                bea.mo1878a();
            }
        }
        bde.f2079b.clear();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public synchronized void mo1439a(bdx bdx) {
        this.f1365l = bdx.clone().mo1872k();
    }

    public final synchronized String toString() {
        StringBuilder sb;
        String obj = super.toString();
        String valueOf = String.valueOf(this.f1359e);
        String valueOf2 = String.valueOf(this.f1360f);
        int length = String.valueOf(obj).length();
        sb = new StringBuilder(length + 21 + String.valueOf(valueOf).length() + String.valueOf(valueOf2).length());
        sb.append(obj);
        sb.append("{tracker=");
        sb.append(valueOf);
        sb.append(", treeNode=");
        sb.append(valueOf2);
        sb.append("}");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final synchronized void mo1441a(ber ber, bea bea) {
        this.f1361g.f2088a.add(ber);
        bde bde = this.f1359e;
        bde.f2078a.add(bea);
        if (!bde.f2080c) {
            bea.mo1878a();
            return;
        }
        bea.mo1880b();
        bde.f2079b.add(bea);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001c, code lost:
        return true;
     */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final synchronized boolean m1391b(p000.ber r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            bea r0 = r4.mo1896ae()     // Catch:{ all -> 0x0020 }
            r1 = 1
            if (r0 == 0) goto L_0x001b
            bde r2 = r3.f1359e     // Catch:{ all -> 0x0020 }
            boolean r0 = r2.mo1829a(r0)     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x001d
            bdh r0 = r3.f1361g     // Catch:{ all -> 0x0020 }
            java.util.Set r0 = r0.f2088a     // Catch:{ all -> 0x0020 }
            r0.remove(r4)     // Catch:{ all -> 0x0020 }
            r0 = 0
            r4.mo1894a((p000.bea) r0)     // Catch:{ all -> 0x0020 }
        L_0x001b:
            monitor-exit(r3)
            return r1
        L_0x001d:
            r4 = 0
            monitor-exit(r3)
            return r4
        L_0x0020:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.apn.m1391b(ber):boolean");
    }
}
