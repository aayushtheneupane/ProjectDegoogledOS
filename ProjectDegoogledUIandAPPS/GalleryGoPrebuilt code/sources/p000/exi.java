package p000;

import android.content.ContentResolver;
import android.net.Uri;
import android.util.Log;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

/* renamed from: exi */
/* compiled from: PG */
public final class exi {

    /* renamed from: a */
    public static final Uri f9170a = Uri.parse("content://com.google.android.gsf.gservices");

    /* renamed from: b */
    public static final Pattern f9171b = Pattern.compile("^(1|true|t|on|yes|y)$", 2);

    /* renamed from: c */
    public static final Pattern f9172c = Pattern.compile("^(0|false|f|off|no|n)$", 2);

    /* renamed from: d */
    public static final AtomicBoolean f9173d = new AtomicBoolean();

    /* renamed from: e */
    public static final HashMap f9174e = new HashMap();

    /* renamed from: f */
    private static final Uri f9175f = Uri.parse("content://com.google.android.gsf.gservices/prefix");

    /* renamed from: g */
    private static HashMap f9176g;

    /* renamed from: h */
    private static final HashMap f9177h = new HashMap();

    /* renamed from: i */
    private static final HashMap f9178i = new HashMap();

    /* renamed from: j */
    private static final HashMap f9179j = new HashMap();

    /* renamed from: k */
    private static Object f9180k;

    /* renamed from: l */
    private static boolean f9181l;

    /* renamed from: m */
    private static final String[] f9182m = new String[0];

    /* renamed from: b */
    private static void m8316b(ContentResolver contentResolver) {
        if (f9176g == null) {
            f9173d.set(false);
            f9176g = new HashMap();
            f9180k = new Object();
            f9181l = false;
            contentResolver.registerContentObserver(f9170a, true, new exh());
        } else if (f9173d.getAndSet(false)) {
            f9176g.clear();
            f9177h.clear();
            f9174e.clear();
            f9178i.clear();
            f9179j.clear();
            f9180k = new Object();
            f9181l = false;
        }
    }

    /* renamed from: a */
    public static boolean m8315a(ContentResolver contentResolver, String str, boolean z) {
        Object a = m8310a(contentResolver);
        Boolean bool = (Boolean) m8311a(f9177h, str, (Object) Boolean.valueOf(z));
        if (bool != null) {
            return bool.booleanValue();
        }
        String a2 = m8312a(contentResolver, str);
        if (a2 != null && !a2.equals("")) {
            if (f9171b.matcher(a2).matches()) {
                bool = true;
                z = true;
            } else if (!f9172c.matcher(a2).matches()) {
                Log.w("Gservices", "attempt to read gservices key " + str + " (value \"" + a2 + "\") as boolean");
            } else {
                bool = false;
                z = false;
            }
        }
        m8314a(a, f9177h, str, bool);
        return z;
    }

    /* renamed from: a */
    public static long m8309a(ContentResolver contentResolver, String str, long j) {
        Object a = m8310a(contentResolver);
        Long l = (Long) m8311a(f9178i, str, (Object) Long.valueOf(j));
        if (l != null) {
            return l.longValue();
        }
        String a2 = m8312a(contentResolver, str);
        if (a2 != null) {
            try {
                long parseLong = Long.parseLong(a2);
                l = Long.valueOf(parseLong);
                j = parseLong;
            } catch (NumberFormatException e) {
            }
        }
        m8314a(a, f9178i, str, l);
        return j;
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x007c, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x007e, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0080, code lost:
        r14 = r14.query(f9170a, (java.lang.String[]) null, (java.lang.String) null, new java.lang.String[]{r15}, (java.lang.String) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x008e, code lost:
        if (r14 == null) goto L_0x00ba;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0094, code lost:
        if (r14.moveToFirst() == false) goto L_0x00b1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0096, code lost:
        r0 = r14.getString(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x009a, code lost:
        if (r0 == null) goto L_0x00a5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00a0, code lost:
        if (r0.equals((java.lang.Object) null) != false) goto L_0x00a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00a3, code lost:
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00a5, code lost:
        m8313a(r1, r15, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00a8, code lost:
        if (r0 != null) goto L_0x00ab;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00ab, code lost:
        r3 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00af, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:?, code lost:
        m8313a(r1, r15, (java.lang.String) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00b5, code lost:
        r15 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00b6, code lost:
        r14.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00b9, code lost:
        throw r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00ba, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00ca, code lost:
        return r3;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m8312a(android.content.ContentResolver r14, java.lang.String r15) {
        /*
            java.lang.Class<exi> r0 = p000.exi.class
            monitor-enter(r0)
            m8316b(r14)     // Catch:{ all -> 0x00cb }
            java.lang.Object r1 = f9180k     // Catch:{ all -> 0x00cb }
            java.util.HashMap r2 = f9176g     // Catch:{ all -> 0x00cb }
            boolean r2 = r2.containsKey(r15)     // Catch:{ all -> 0x00cb }
            r3 = 0
            if (r2 != 0) goto L_0x00bb
            java.lang.String[] r2 = f9182m     // Catch:{ all -> 0x00cb }
            int r4 = r2.length     // Catch:{ all -> 0x00cb }
            r5 = 0
            r6 = 0
        L_0x0016:
            r7 = 1
            if (r6 >= r4) goto L_0x007f
            r8 = r2[r6]     // Catch:{ all -> 0x00cb }
            boolean r8 = r15.startsWith(r8)     // Catch:{ all -> 0x00cb }
            if (r8 != 0) goto L_0x0024
            int r6 = r6 + 1
            goto L_0x0016
        L_0x0024:
            boolean r1 = f9181l     // Catch:{ all -> 0x00cb }
            if (r1 == 0) goto L_0x0030
            java.util.HashMap r1 = f9176g     // Catch:{ all -> 0x00cb }
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x00cb }
            if (r1 == 0) goto L_0x007d
        L_0x0030:
            java.lang.String[] r12 = f9182m     // Catch:{ all -> 0x00cb }
            java.util.HashMap r1 = f9176g     // Catch:{ all -> 0x00cb }
            android.net.Uri r9 = f9175f     // Catch:{ all -> 0x00cb }
            r10 = 0
            r11 = 0
            r13 = 0
            r8 = r14
            android.database.Cursor r14 = r8.query(r9, r10, r11, r12, r13)     // Catch:{ all -> 0x00cb }
            java.util.TreeMap r2 = new java.util.TreeMap     // Catch:{ all -> 0x00cb }
            r2.<init>()     // Catch:{ all -> 0x00cb }
            if (r14 == 0) goto L_0x0060
        L_0x0045:
            boolean r4 = r14.moveToNext()     // Catch:{ all -> 0x005b }
            if (r4 == 0) goto L_0x0057
            java.lang.String r4 = r14.getString(r5)     // Catch:{ all -> 0x005b }
            java.lang.String r6 = r14.getString(r7)     // Catch:{ all -> 0x005b }
            r2.put(r4, r6)     // Catch:{ all -> 0x005b }
            goto L_0x0045
        L_0x0057:
            r14.close()     // Catch:{ all -> 0x00cb }
            goto L_0x0060
        L_0x005b:
            r15 = move-exception
            r14.close()     // Catch:{ all -> 0x00cb }
            throw r15     // Catch:{ all -> 0x00cb }
        L_0x0060:
            r1.putAll(r2)     // Catch:{ all -> 0x00cb }
            f9181l = r7     // Catch:{ all -> 0x00cb }
            java.util.HashMap r14 = f9176g     // Catch:{ all -> 0x00cb }
            boolean r14 = r14.containsKey(r15)     // Catch:{ all -> 0x00cb }
            if (r14 == 0) goto L_0x007d
            java.util.HashMap r14 = f9176g     // Catch:{ all -> 0x00cb }
            java.lang.Object r14 = r14.get(r15)     // Catch:{ all -> 0x00cb }
            java.lang.String r14 = (java.lang.String) r14     // Catch:{ all -> 0x00cb }
            if (r14 == 0) goto L_0x0079
            r3 = r14
            goto L_0x007b
        L_0x0079:
        L_0x007b:
            monitor-exit(r0)     // Catch:{ all -> 0x00cb }
            return r3
        L_0x007d:
            monitor-exit(r0)     // Catch:{ all -> 0x00cb }
            return r3
        L_0x007f:
            monitor-exit(r0)     // Catch:{ all -> 0x00cb }
            android.net.Uri r9 = f9170a
            java.lang.String[] r12 = new java.lang.String[r7]
            r12[r5] = r15
            r10 = 0
            r11 = 0
            r13 = 0
            r8 = r14
            android.database.Cursor r14 = r8.query(r9, r10, r11, r12, r13)
            if (r14 == 0) goto L_0x00ba
            boolean r0 = r14.moveToFirst()     // Catch:{ all -> 0x00b5 }
            if (r0 == 0) goto L_0x00b0
            java.lang.String r0 = r14.getString(r7)     // Catch:{ all -> 0x00b5 }
            if (r0 == 0) goto L_0x00a5
            boolean r2 = r0.equals(r3)     // Catch:{ all -> 0x00b5 }
            if (r2 != 0) goto L_0x00a3
            goto L_0x00a5
        L_0x00a3:
            r0 = r3
        L_0x00a5:
            m8313a((java.lang.Object) r1, (java.lang.String) r15, (java.lang.String) r0)     // Catch:{ all -> 0x00b5 }
            if (r0 != 0) goto L_0x00ab
            goto L_0x00ac
        L_0x00ab:
            r3 = r0
        L_0x00ac:
            r14.close()
            return r3
        L_0x00b0:
            m8313a((java.lang.Object) r1, (java.lang.String) r15, (java.lang.String) r3)     // Catch:{ all -> 0x00b5 }
            goto L_0x00ac
        L_0x00b5:
            r15 = move-exception
            r14.close()
            throw r15
        L_0x00ba:
            return r3
        L_0x00bb:
            java.util.HashMap r14 = f9176g     // Catch:{ all -> 0x00cb }
            java.lang.Object r14 = r14.get(r15)     // Catch:{ all -> 0x00cb }
            java.lang.String r14 = (java.lang.String) r14     // Catch:{ all -> 0x00cb }
            if (r14 == 0) goto L_0x00c7
            r3 = r14
            goto L_0x00c9
        L_0x00c7:
        L_0x00c9:
            monitor-exit(r0)     // Catch:{ all -> 0x00cb }
            return r3
        L_0x00cb:
            r14 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00cb }
            goto L_0x00cf
        L_0x00ce:
            throw r14
        L_0x00cf:
            goto L_0x00ce
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.exi.m8312a(android.content.ContentResolver, java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0011, code lost:
        return r4;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object m8311a(java.util.HashMap r2, java.lang.String r3, java.lang.Object r4) {
        /*
            java.lang.Class<exi> r0 = p000.exi.class
            monitor-enter(r0)
            boolean r1 = r2.containsKey(r3)     // Catch:{ all -> 0x0015 }
            if (r1 == 0) goto L_0x0012
            java.lang.Object r2 = r2.get(r3)     // Catch:{ all -> 0x0015 }
            if (r2 == 0) goto L_0x0010
            r4 = r2
        L_0x0010:
            monitor-exit(r0)     // Catch:{ all -> 0x0015 }
            return r4
        L_0x0012:
            monitor-exit(r0)     // Catch:{ all -> 0x0015 }
            r2 = 0
            return r2
        L_0x0015:
            r2 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0015 }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.exi.m8311a(java.util.HashMap, java.lang.String, java.lang.Object):java.lang.Object");
    }

    /* renamed from: a */
    public static Object m8310a(ContentResolver contentResolver) {
        Object obj;
        synchronized (exi.class) {
            m8316b(contentResolver);
            obj = f9180k;
        }
        return obj;
    }

    /* renamed from: a */
    private static void m8313a(Object obj, String str, String str2) {
        synchronized (exi.class) {
            if (obj == f9180k) {
                f9176g.put(str, str2);
            }
        }
    }

    /* renamed from: a */
    public static void m8314a(Object obj, HashMap hashMap, String str, Object obj2) {
        synchronized (exi.class) {
            if (obj == f9180k) {
                hashMap.put(str, obj2);
                f9176g.remove(str);
            }
        }
    }
}
