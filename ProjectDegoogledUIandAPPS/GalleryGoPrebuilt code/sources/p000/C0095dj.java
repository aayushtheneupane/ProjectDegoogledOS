package p000;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.TreeMap;

/* renamed from: dj */
/* compiled from: PG */
public final class C0095dj extends ezt {

    /* renamed from: c */
    public static final C0095dj f6643c = new C0095dj();

    /* renamed from: d */
    private static final ResourceBundle f6644d = new C0075cq();

    /* renamed from: a */
    public Map f6645a;

    /* renamed from: b */
    public Map f6646b;

    /* renamed from: e */
    private final Map f6647e = new HashMap();

    private C0095dj() {
    }

    /* renamed from: a */
    public final void mo4159a() {
        Map map;
        Map map2;
        Map map3;
        synchronized (this) {
            map = this.f6645a;
        }
        if (map == null) {
            try {
                ResourceBundle resourceBundle = f6644d;
                Object[][] objArr = (Object[][]) resourceBundle.getObject("locales");
                map2 = new TreeMap();
                for (Object[] objArr2 : objArr) {
                    map2.put((String) objArr2[0], (String) objArr2[1]);
                }
                Object[][] objArr3 = (Object[][]) resourceBundle.getObject("locales_ordinals");
                map3 = new TreeMap();
                for (Object[] objArr4 : objArr3) {
                    map3.put((String) objArr4[0], (String) objArr4[1]);
                }
            } catch (MissingResourceException e) {
                map2 = Collections.emptyMap();
                map3 = Collections.emptyMap();
            }
            synchronized (this) {
                if (this.f6645a == null) {
                    this.f6645a = map2;
                    this.f6646b = map3;
                }
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: java.lang.Object[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v5, resolved type: java.lang.Object[]} */
    /* JADX WARNING: type inference failed for: r0v8 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final p000.C0094di mo4158a(java.lang.String r10) {
        /*
            r9 = this;
            java.util.Map r0 = r9.f6647e
            monitor-enter(r0)
            java.util.Map r1 = r9.f6647e     // Catch:{ all -> 0x009a }
            boolean r1 = r1.containsKey(r10)     // Catch:{ all -> 0x009a }
            r2 = 0
            if (r1 == 0) goto L_0x0015
            java.util.Map r3 = r9.f6647e     // Catch:{ all -> 0x009a }
            java.lang.Object r3 = r3.get(r10)     // Catch:{ all -> 0x009a }
            di r3 = (p000.C0094di) r3     // Catch:{ all -> 0x009a }
            goto L_0x0017
        L_0x0015:
            r3 = r2
        L_0x0017:
            monitor-exit(r0)     // Catch:{ all -> 0x009a }
            if (r1 != 0) goto L_0x0099
            java.util.ResourceBundle r0 = f6644d     // Catch:{ ParseException -> 0x0076, MissingResourceException -> 0x0074 }
            java.lang.String r1 = "rules"
            java.lang.Object r0 = r0.getObject(r1)     // Catch:{ ParseException -> 0x0076, MissingResourceException -> 0x0074 }
            java.lang.Object[][] r0 = (java.lang.Object[][]) r0     // Catch:{ ParseException -> 0x0076, MissingResourceException -> 0x0074 }
            int r1 = r0.length     // Catch:{ ParseException -> 0x0076, MissingResourceException -> 0x0074 }
            r4 = 0
            r5 = 0
        L_0x0027:
            r6 = 1
            if (r5 >= r1) goto L_0x003d
            r7 = r0[r5]     // Catch:{ ParseException -> 0x0076, MissingResourceException -> 0x0074 }
            r8 = r7[r4]     // Catch:{ ParseException -> 0x0076, MissingResourceException -> 0x0074 }
            boolean r8 = r10.equals(r8)     // Catch:{ ParseException -> 0x0076, MissingResourceException -> 0x0074 }
            if (r8 != 0) goto L_0x0037
            int r5 = r5 + 1
            goto L_0x0027
        L_0x0037:
            r0 = r7[r6]     // Catch:{ ParseException -> 0x0076, MissingResourceException -> 0x0074 }
            r2 = r0
            java.lang.Object[][] r2 = (java.lang.Object[][]) r2     // Catch:{ ParseException -> 0x0076, MissingResourceException -> 0x0074 }
            goto L_0x003e
        L_0x003d:
        L_0x003e:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ ParseException -> 0x0076, MissingResourceException -> 0x0074 }
            r0.<init>()     // Catch:{ ParseException -> 0x0076, MissingResourceException -> 0x0074 }
            int r1 = r2.length     // Catch:{ ParseException -> 0x0076, MissingResourceException -> 0x0074 }
            r5 = 0
        L_0x0045:
            if (r5 >= r1) goto L_0x006b
            r7 = r2[r5]     // Catch:{ ParseException -> 0x0076, MissingResourceException -> 0x0074 }
            int r8 = r0.length()     // Catch:{ ParseException -> 0x0076, MissingResourceException -> 0x0074 }
            if (r8 <= 0) goto L_0x0054
            java.lang.String r8 = "; "
            r0.append(r8)     // Catch:{ ParseException -> 0x0076, MissingResourceException -> 0x0074 }
        L_0x0054:
            r8 = r7[r4]     // Catch:{ ParseException -> 0x0076, MissingResourceException -> 0x0074 }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ ParseException -> 0x0076, MissingResourceException -> 0x0074 }
            r0.append(r8)     // Catch:{ ParseException -> 0x0076, MissingResourceException -> 0x0074 }
            java.lang.String r8 = ": "
            r0.append(r8)     // Catch:{ ParseException -> 0x0076, MissingResourceException -> 0x0074 }
            r7 = r7[r6]     // Catch:{ ParseException -> 0x0076, MissingResourceException -> 0x0074 }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ ParseException -> 0x0076, MissingResourceException -> 0x0074 }
            r0.append(r7)     // Catch:{ ParseException -> 0x0076, MissingResourceException -> 0x0074 }
            int r5 = r5 + 1
            goto L_0x0045
        L_0x006b:
            java.lang.String r0 = r0.toString()     // Catch:{ ParseException -> 0x0076, MissingResourceException -> 0x0074 }
            di r3 = p000.C0094di.m6133a((java.lang.String) r0)     // Catch:{ ParseException -> 0x0076, MissingResourceException -> 0x0074 }
            goto L_0x0078
        L_0x0074:
            r0 = move-exception
            goto L_0x0077
        L_0x0076:
            r0 = move-exception
        L_0x0077:
        L_0x0078:
            java.util.Map r0 = r9.f6647e
            monitor-enter(r0)
            java.util.Map r1 = r9.f6647e     // Catch:{ all -> 0x0096 }
            boolean r1 = r1.containsKey(r10)     // Catch:{ all -> 0x0096 }
            if (r1 == 0) goto L_0x008d
            java.util.Map r1 = r9.f6647e     // Catch:{ all -> 0x0096 }
            java.lang.Object r10 = r1.get(r10)     // Catch:{ all -> 0x0096 }
            di r10 = (p000.C0094di) r10     // Catch:{ all -> 0x0096 }
            r3 = r10
            goto L_0x0094
        L_0x008d:
            java.util.Map r1 = r9.f6647e     // Catch:{ all -> 0x0096 }
            r1.put(r10, r3)     // Catch:{ all -> 0x0096 }
        L_0x0094:
            monitor-exit(r0)     // Catch:{ all -> 0x0096 }
            goto L_0x0099
        L_0x0096:
            r10 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0096 }
            throw r10
        L_0x0099:
            return r3
        L_0x009a:
            r10 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x009a }
            goto L_0x009e
        L_0x009d:
            throw r10
        L_0x009e:
            goto L_0x009d
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0095dj.mo4158a(java.lang.String):di");
    }
}
