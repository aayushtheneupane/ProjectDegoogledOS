package p000;

import android.content.Context;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: fqg */
/* compiled from: PG */
public abstract class fqg {

    /* renamed from: a */
    public static final Object f10248a = new Object();

    /* renamed from: b */
    public static Context f10249b = null;

    /* renamed from: c */
    public static hqk f10250c;

    /* renamed from: g */
    private static final AtomicInteger f10251g = new AtomicInteger();

    /* renamed from: d */
    private final fqf f10252d;

    /* renamed from: e */
    private final String f10253e;

    /* renamed from: f */
    private final Object f10254f;

    /* renamed from: h */
    private volatile int f10255h = -1;

    /* renamed from: i */
    private volatile Object f10256i;

    /* renamed from: a */
    public abstract Object mo6026a(Object obj);

    public /* synthetic */ fqg(fqf fqf, String str, Object obj) {
        if (fqf.f10244a != null) {
            this.f10252d = fqf;
            this.f10253e = str;
            this.f10254f = obj;
            return;
        }
        throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v17, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0099  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00a9  */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object mo6028c() {
        /*
            r6 = this;
            java.util.concurrent.atomic.AtomicInteger r0 = f10251g
            int r0 = r0.get()
            int r1 = r6.f10255h
            if (r1 >= r0) goto L_0x0104
            monitor-enter(r6)
            int r1 = r6.f10255h     // Catch:{ all -> 0x0101 }
            if (r1 >= r0) goto L_0x00ff
            android.content.Context r1 = f10249b     // Catch:{ all -> 0x0101 }
            if (r1 == 0) goto L_0x00f7
            android.content.Context r1 = f10249b     // Catch:{ all -> 0x0101 }
            fpy r1 = p000.fpy.m9389a((android.content.Context) r1)     // Catch:{ all -> 0x0101 }
            java.lang.String r2 = "gms:phenotype:phenotype_flag:debug_bypass_phenotype"
            java.lang.String r1 = r1.mo6022a(r2)     // Catch:{ all -> 0x0101 }
            r2 = 0
            if (r1 == 0) goto L_0x0032
            java.util.regex.Pattern r3 = p000.exi.f9171b     // Catch:{ all -> 0x0101 }
            java.util.regex.Matcher r1 = r3.matcher(r1)     // Catch:{ all -> 0x0101 }
            boolean r1 = r1.matches()     // Catch:{ all -> 0x0101 }
            if (r1 != 0) goto L_0x002f
            goto L_0x0032
        L_0x002f:
        L_0x0030:
            r1 = r2
            goto L_0x006c
        L_0x0032:
            fqf r1 = r6.f10252d     // Catch:{ all -> 0x0101 }
            android.net.Uri r1 = r1.f10244a     // Catch:{ all -> 0x0101 }
            if (r1 == 0) goto L_0x0056
            android.content.Context r1 = f10249b     // Catch:{ all -> 0x0101 }
            fqf r3 = r6.f10252d     // Catch:{ all -> 0x0101 }
            android.net.Uri r3 = r3.f10244a     // Catch:{ all -> 0x0101 }
            boolean r1 = p000.fqa.m9395a(r1, r3)     // Catch:{ all -> 0x0101 }
            if (r1 == 0) goto L_0x0053
            android.content.Context r1 = f10249b     // Catch:{ all -> 0x0101 }
            android.content.ContentResolver r1 = r1.getContentResolver()     // Catch:{ all -> 0x0101 }
            fqf r3 = r6.f10252d     // Catch:{ all -> 0x0101 }
            android.net.Uri r3 = r3.f10244a     // Catch:{ all -> 0x0101 }
            fpr r1 = p000.fpr.m9369a(r1, r3)     // Catch:{ all -> 0x0101 }
            goto L_0x005a
        L_0x0053:
            r1 = r2
            goto L_0x005a
        L_0x0056:
            fqh r1 = p000.fqh.m9408b()     // Catch:{ all -> 0x0101 }
        L_0x005a:
            if (r1 == 0) goto L_0x0030
            java.lang.String r3 = r6.mo6027b()     // Catch:{ all -> 0x0101 }
            java.lang.Object r1 = r1.mo6022a(r3)     // Catch:{ all -> 0x0101 }
            if (r1 == 0) goto L_0x006b
            java.lang.Object r1 = r6.mo6026a((java.lang.Object) r1)     // Catch:{ all -> 0x0101 }
            goto L_0x006c
        L_0x006b:
            goto L_0x0030
        L_0x006c:
            if (r1 == 0) goto L_0x006f
            goto L_0x009b
        L_0x006f:
            fqf r1 = r6.f10252d     // Catch:{ all -> 0x0101 }
            boolean r1 = r1.f10247d     // Catch:{ all -> 0x0101 }
            if (r1 != 0) goto L_0x0095
            android.content.Context r1 = f10249b     // Catch:{ all -> 0x0101 }
            fpy r1 = p000.fpy.m9389a((android.content.Context) r1)     // Catch:{ all -> 0x0101 }
            fqf r3 = r6.f10252d     // Catch:{ all -> 0x0101 }
            boolean r4 = r3.f10247d     // Catch:{ all -> 0x0101 }
            if (r4 != 0) goto L_0x0088
            java.lang.String r3 = r3.f10245b     // Catch:{ all -> 0x0101 }
            java.lang.String r3 = r6.m9402a((java.lang.String) r3)     // Catch:{ all -> 0x0101 }
            goto L_0x008a
        L_0x0088:
            r3 = r2
        L_0x008a:
            java.lang.String r1 = r1.mo6022a(r3)     // Catch:{ all -> 0x0101 }
            if (r1 == 0) goto L_0x0096
            java.lang.Object r1 = r6.mo6026a((java.lang.Object) r1)     // Catch:{ all -> 0x0101 }
            goto L_0x0097
        L_0x0095:
        L_0x0096:
            r1 = r2
        L_0x0097:
            if (r1 != 0) goto L_0x009b
            java.lang.Object r1 = r6.f10254f     // Catch:{ all -> 0x0101 }
        L_0x009b:
            hqk r3 = f10250c     // Catch:{ all -> 0x0101 }
            java.lang.Object r3 = r3.mo2652a()     // Catch:{ all -> 0x0101 }
            hpy r3 = (p000.hpy) r3     // Catch:{ all -> 0x0101 }
            boolean r4 = r3.mo7646a()     // Catch:{ all -> 0x0101 }
            if (r4 == 0) goto L_0x00f2
            java.lang.Object r1 = r3.mo7647b()     // Catch:{ all -> 0x0101 }
            fpz r1 = (p000.fpz) r1     // Catch:{ all -> 0x0101 }
            fqf r3 = r6.f10252d     // Catch:{ all -> 0x0101 }
            android.net.Uri r4 = r3.f10244a     // Catch:{ all -> 0x0101 }
            java.lang.String r3 = r3.f10246c     // Catch:{ all -> 0x0101 }
            java.lang.String r5 = r6.f10253e     // Catch:{ all -> 0x0101 }
            if (r4 == 0) goto L_0x00e7
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0101 }
            java.util.Map r1 = r1.f10239a     // Catch:{ all -> 0x0101 }
            java.lang.Object r1 = r1.get(r4)     // Catch:{ all -> 0x0101 }
            java.util.Map r1 = (java.util.Map) r1     // Catch:{ all -> 0x0101 }
            if (r1 != 0) goto L_0x00c8
            goto L_0x00e8
        L_0x00c8:
            if (r3 == 0) goto L_0x00de
            java.lang.String r2 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x0101 }
            int r4 = r2.length()     // Catch:{ all -> 0x0101 }
            if (r4 != 0) goto L_0x00da
            java.lang.String r5 = new java.lang.String     // Catch:{ all -> 0x0101 }
            r5.<init>(r3)     // Catch:{ all -> 0x0101 }
            goto L_0x00de
        L_0x00da:
            java.lang.String r5 = r3.concat(r2)     // Catch:{ all -> 0x0101 }
        L_0x00de:
            java.lang.Object r1 = r1.get(r5)     // Catch:{ all -> 0x0101 }
            r2 = r1
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ all -> 0x0101 }
            goto L_0x00e9
        L_0x00e7:
        L_0x00e8:
        L_0x00e9:
            if (r2 != 0) goto L_0x00ee
            java.lang.Object r1 = r6.f10254f     // Catch:{ all -> 0x0101 }
            goto L_0x00f2
        L_0x00ee:
            java.lang.Object r1 = r6.mo6026a((java.lang.Object) r2)     // Catch:{ all -> 0x0101 }
        L_0x00f2:
            r6.f10256i = r1     // Catch:{ all -> 0x0101 }
            r6.f10255h = r0     // Catch:{ all -> 0x0101 }
            goto L_0x00ff
        L_0x00f7:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0101 }
            java.lang.String r1 = "Must call PhenotypeFlag.init() first"
            r0.<init>(r1)     // Catch:{ all -> 0x0101 }
            throw r0     // Catch:{ all -> 0x0101 }
        L_0x00ff:
            monitor-exit(r6)     // Catch:{ all -> 0x0101 }
            goto L_0x0104
        L_0x0101:
            r0 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x0101 }
            throw r0
        L_0x0104:
            java.lang.Object r0 = r6.f10256i
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.fqg.mo6028c():java.lang.Object");
    }

    /* renamed from: b */
    public final String mo6027b() {
        return m9402a(this.f10252d.f10246c);
    }

    /* renamed from: a */
    private final String m9402a(String str) {
        if (str != null && str.isEmpty()) {
            return this.f10253e;
        }
        String valueOf = String.valueOf(str);
        String valueOf2 = String.valueOf(this.f10253e);
        return valueOf2.length() == 0 ? new String(valueOf) : valueOf.concat(valueOf2);
    }

    /* renamed from: a */
    public static void m9403a() {
        f10251g.incrementAndGet();
    }

    /* renamed from: a */
    public static fqg m9401a(fqf fqf, String str, boolean z) {
        return new fqd(fqf, str, Boolean.valueOf(z));
    }
}
