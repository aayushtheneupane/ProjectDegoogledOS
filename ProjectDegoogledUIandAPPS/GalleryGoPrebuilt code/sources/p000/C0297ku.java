package p000;

import android.graphics.PorterDuff;
import java.util.LinkedHashMap;
import java.util.Locale;

/* renamed from: ku */
/* compiled from: PG */
public class C0297ku {

    /* renamed from: a */
    private final LinkedHashMap f15158a;

    /* renamed from: b */
    private int f15159b;

    /* renamed from: c */
    private int f15160c;

    /* renamed from: d */
    private int f15161d;

    /* renamed from: e */
    private int f15162e;

    /* renamed from: f */
    private int f15163f;

    /* renamed from: g */
    private int f15164g;

    /* renamed from: h */
    private int f15165h;

    public C0297ku(int i) {
        this.f15160c = i;
        this.f15158a = new LinkedHashMap(0, 0.75f, true);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public Object mo3941b(Object obj) {
        return null;
    }

    /* renamed from: a */
    public final void mo9239a() {
        m14552a(-1);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r3.f15162e++;
        r1 = r3.f15158a.put(r4, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0025, code lost:
        if (r1 != null) goto L_0x002e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0027, code lost:
        r3.f15159b++;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002e, code lost:
        r3.f15158a.put(r4, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0033, code lost:
        monitor-exit(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0034, code lost:
        if (r1 != null) goto L_0x003c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0036, code lost:
        m14552a(r3.f15160c);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003b, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003c, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0040, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0012, code lost:
        r0 = mo3941b(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0016, code lost:
        if (r0 == null) goto L_0x0040;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0018, code lost:
        monitor-enter(r3);
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object mo9237a(java.lang.Object r4) {
        /*
            r3 = this;
            if (r4 == 0) goto L_0x004d
            monitor-enter(r3)
            java.util.LinkedHashMap r0 = r3.f15158a     // Catch:{ all -> 0x004a }
            java.lang.Object r0 = r0.get(r4)     // Catch:{ all -> 0x004a }
            if (r0 != 0) goto L_0x0042
            int r0 = r3.f15165h     // Catch:{ all -> 0x004a }
            int r0 = r0 + 1
            r3.f15165h = r0     // Catch:{ all -> 0x004a }
            monitor-exit(r3)     // Catch:{ all -> 0x004a }
            java.lang.Object r0 = r3.mo3941b(r4)
            if (r0 == 0) goto L_0x0040
            monitor-enter(r3)
            int r1 = r3.f15162e     // Catch:{ all -> 0x003d }
            int r1 = r1 + 1
            r3.f15162e = r1     // Catch:{ all -> 0x003d }
            java.util.LinkedHashMap r1 = r3.f15158a     // Catch:{ all -> 0x003d }
            java.lang.Object r1 = r1.put(r4, r0)     // Catch:{ all -> 0x003d }
            if (r1 != 0) goto L_0x002e
            int r4 = r3.f15159b     // Catch:{ all -> 0x003d }
            int r4 = r4 + 1
            r3.f15159b = r4     // Catch:{ all -> 0x003d }
            goto L_0x0033
        L_0x002e:
            java.util.LinkedHashMap r2 = r3.f15158a     // Catch:{ all -> 0x003d }
            r2.put(r4, r1)     // Catch:{ all -> 0x003d }
        L_0x0033:
            monitor-exit(r3)     // Catch:{ all -> 0x003d }
            if (r1 != 0) goto L_0x003c
            int r4 = r3.f15160c
            r3.m14552a((int) r4)
            return r0
        L_0x003c:
            return r1
        L_0x003d:
            r4 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x003d }
            throw r4
        L_0x0040:
            r4 = 0
            return r4
        L_0x0042:
            int r4 = r3.f15164g     // Catch:{ all -> 0x004a }
            int r4 = r4 + 1
            r3.f15164g = r4     // Catch:{ all -> 0x004a }
            monitor-exit(r3)     // Catch:{ all -> 0x004a }
            return r0
        L_0x004a:
            r4 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x004a }
            throw r4
        L_0x004d:
            java.lang.NullPointerException r4 = new java.lang.NullPointerException
            java.lang.String r0 = "key == null"
            r4.<init>(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0297ku.mo9237a(java.lang.Object):java.lang.Object");
    }

    /* renamed from: a */
    public final Object mo9238a(Object obj, Object obj2) {
        Object put;
        if (obj == null || obj2 == null) {
            throw new NullPointerException("key == null || value == null");
        }
        synchronized (this) {
            this.f15161d++;
            this.f15159b++;
            put = this.f15158a.put(obj, obj2);
            if (put != null) {
                this.f15159b--;
            }
        }
        m14552a(this.f15160c);
        return put;
    }

    public final synchronized String toString() {
        int i;
        int i2 = this.f15164g;
        int i3 = this.f15165h + i2;
        if (i3 == 0) {
            i = 0;
        } else {
            i = (i2 * 100) / i3;
        }
        return String.format(Locale.US, "LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", new Object[]{Integer.valueOf(this.f15160c), Integer.valueOf(this.f15164g), Integer.valueOf(this.f15165h), Integer.valueOf(i)});
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0067, code lost:
        throw new java.lang.IllegalStateException(getClass().getName() + ".sizeOf() is reporting inconsistent results!");
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void m14552a(int r3) {
        /*
            r2 = this;
        L_0x0000:
            monitor-enter(r2)
            int r0 = r2.f15159b     // Catch:{ all -> 0x0068 }
            if (r0 < 0) goto L_0x0049
            java.util.LinkedHashMap r0 = r2.f15158a     // Catch:{ all -> 0x0068 }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0068 }
            if (r0 == 0) goto L_0x0011
            int r0 = r2.f15159b     // Catch:{ all -> 0x0068 }
            if (r0 != 0) goto L_0x0049
        L_0x0011:
            int r0 = r2.f15159b     // Catch:{ all -> 0x0068 }
            if (r0 <= r3) goto L_0x0047
            java.util.LinkedHashMap r0 = r2.f15158a     // Catch:{ all -> 0x0068 }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0068 }
            if (r0 != 0) goto L_0x0047
            java.util.LinkedHashMap r0 = r2.f15158a     // Catch:{ all -> 0x0068 }
            java.util.Set r0 = r0.entrySet()     // Catch:{ all -> 0x0068 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x0068 }
            java.lang.Object r0 = r0.next()     // Catch:{ all -> 0x0068 }
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0     // Catch:{ all -> 0x0068 }
            java.lang.Object r1 = r0.getKey()     // Catch:{ all -> 0x0068 }
            r0.getValue()     // Catch:{ all -> 0x0068 }
            java.util.LinkedHashMap r0 = r2.f15158a     // Catch:{ all -> 0x0068 }
            r0.remove(r1)     // Catch:{ all -> 0x0068 }
            int r0 = r2.f15159b     // Catch:{ all -> 0x0068 }
            int r0 = r0 + -1
            r2.f15159b = r0     // Catch:{ all -> 0x0068 }
            int r0 = r2.f15163f     // Catch:{ all -> 0x0068 }
            int r0 = r0 + 1
            r2.f15163f = r0     // Catch:{ all -> 0x0068 }
            monitor-exit(r2)     // Catch:{ all -> 0x0068 }
            goto L_0x0000
        L_0x0047:
            monitor-exit(r2)     // Catch:{ all -> 0x0068 }
            return
        L_0x0049:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0068 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0068 }
            r0.<init>()     // Catch:{ all -> 0x0068 }
            java.lang.Class r1 = r2.getClass()     // Catch:{ all -> 0x0068 }
            java.lang.String r1 = r1.getName()     // Catch:{ all -> 0x0068 }
            r0.append(r1)     // Catch:{ all -> 0x0068 }
            java.lang.String r1 = ".sizeOf() is reporting inconsistent results!"
            r0.append(r1)     // Catch:{ all -> 0x0068 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0068 }
            r3.<init>(r0)     // Catch:{ all -> 0x0068 }
            throw r3     // Catch:{ all -> 0x0068 }
        L_0x0068:
            r3 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0068 }
            goto L_0x006c
        L_0x006b:
            throw r3
        L_0x006c:
            goto L_0x006b
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0297ku.m14552a(int):void");
    }

    public C0297ku() {
        this(6);
    }

    /* renamed from: a */
    public static int m14551a(int i, PorterDuff.Mode mode) {
        return ((i + 31) * 31) + mode.hashCode();
    }
}
