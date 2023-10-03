package p000;

import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: fsa */
/* compiled from: PG */
public final class fsa {

    /* renamed from: a */
    private static Method f10344a;

    fsa() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x002a  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0031  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long m9481a(android.content.ContentResolver r7, android.net.Uri r8, java.lang.String r9, long r10) {
        /*
            r0 = 1
            java.lang.String[] r3 = new java.lang.String[r0]
            r0 = 0
            r3[r0] = r9
            r4 = 0
            r5 = 0
            r6 = 0
            r9 = 0
            r1 = r7
            r2 = r8
            android.database.Cursor r9 = r1.query(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x002e, all -> 0x0027 }
            if (r9 == 0) goto L_0x001c
            boolean r7 = r9.moveToNext()     // Catch:{ Exception -> 0x001f, all -> 0x001d }
            if (r7 == 0) goto L_0x001c
            long r10 = r9.getLong(r0)     // Catch:{ Exception -> 0x001f, all -> 0x001d }
        L_0x001c:
            goto L_0x0021
        L_0x001d:
            r7 = move-exception
            goto L_0x0028
        L_0x001f:
            r7 = move-exception
            goto L_0x002f
        L_0x0021:
            if (r9 == 0) goto L_0x0026
            r9.close()
        L_0x0026:
            return r10
        L_0x0027:
            r7 = move-exception
        L_0x0028:
            if (r9 == 0) goto L_0x002d
            r9.close()
        L_0x002d:
            throw r7
        L_0x002e:
            r7 = move-exception
        L_0x002f:
            if (r9 == 0) goto L_0x0034
            r9.close()
        L_0x0034:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.fsa.m9481a(android.content.ContentResolver, android.net.Uri, java.lang.String, long):long");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r9v1 */
    /* JADX WARNING: type inference failed for: r9v2, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r9v4 */
    /* JADX WARNING: type inference failed for: r9v6 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x002c  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0034  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m9482a(android.content.ContentResolver r7, android.net.Uri r8, java.lang.String r9) {
        /*
            r0 = 1
            java.lang.String[] r3 = new java.lang.String[r0]
            r0 = 0
            r3[r0] = r9
            r4 = 0
            r5 = 0
            r6 = 0
            r9 = 0
            r1 = r7
            r2 = r8
            android.database.Cursor r7 = r1.query(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x0030, all -> 0x0029 }
            if (r7 == 0) goto L_0x0023
            boolean r8 = r7.moveToNext()     // Catch:{ Exception -> 0x0021, all -> 0x001e }
            if (r8 == 0) goto L_0x001d
            java.lang.String r9 = r7.getString(r0)     // Catch:{ Exception -> 0x0021, all -> 0x001e }
            goto L_0x0023
        L_0x001d:
            goto L_0x0023
        L_0x001e:
            r8 = move-exception
            r9 = r7
            goto L_0x002a
        L_0x0021:
            r8 = move-exception
            goto L_0x0032
        L_0x0023:
            if (r7 == 0) goto L_0x0028
            r7.close()
        L_0x0028:
            return r9
        L_0x0029:
            r8 = move-exception
        L_0x002a:
            if (r9 == 0) goto L_0x002f
            r9.close()
        L_0x002f:
            throw r8
        L_0x0030:
            r7 = move-exception
            r7 = r9
        L_0x0032:
            if (r7 == 0) goto L_0x0037
            r7.close()
        L_0x0037:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.fsa.m9482a(android.content.ContentResolver, android.net.Uri, java.lang.String):java.lang.String");
    }

    /* renamed from: a */
    public static void m9483a(C0171gd gdVar) {
        if (f10344a == null) {
            try {
                Method declaredMethod = C0171gd.class.getDeclaredMethod("noteStateNotSaved", new Class[0]);
                f10344a = declaredMethod;
                declaredMethod.setAccessible(true);
            } catch (NoSuchMethodException e) {
                m9485a((Throwable) e);
            }
        }
        try {
            ((Method) fxk.m9821a((Object) f10344a)).invoke(gdVar, new Object[0]);
        } catch (IllegalAccessException e2) {
            m9485a((Throwable) e2);
        } catch (InvocationTargetException e3) {
            m9485a((Throwable) e3);
        }
    }

    /* renamed from: a */
    private static void m9485a(Throwable th) {
        throw new IllegalStateException("Could not access method FragmentManager#noteStateNotSaved", th);
    }

    @Deprecated
    /* renamed from: a */
    public static void m9484a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }
}
