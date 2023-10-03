package p000;

import android.database.DatabaseErrorHandler;

/* renamed from: bk */
/* compiled from: PG */
final class C0040bk implements DatabaseErrorHandler {

    /* renamed from: a */
    private final /* synthetic */ C0039bj[] f2974a;

    public C0040bk(C0039bj[] bjVarArr) {
        this.f2974a = bjVarArr;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0053  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onCorruption(android.database.sqlite.SQLiteDatabase r3) {
        /*
            r2 = this;
            bj[] r0 = r2.f2974a
            bj r3 = p000.C0041bl.m3185a(r0, r3)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Corruption reported by sqlite on database: "
            r0.append(r1)
            java.lang.String r1 = r3.mo1739f()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "SupportSQLite"
            android.util.Log.e(r1, r0)
            boolean r0 = r3.mo1738e()
            if (r0 == 0) goto L_0x007f
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r3.f2518a     // Catch:{ SQLiteException -> 0x0030, all -> 0x002e }
            java.util.List r0 = r1.getAttachedDbs()     // Catch:{ SQLiteException -> 0x0030, all -> 0x002e }
            goto L_0x0032
        L_0x002e:
            r1 = move-exception
            goto L_0x0039
        L_0x0030:
            r1 = move-exception
        L_0x0032:
            r3.close()     // Catch:{ IOException -> 0x005b, all -> 0x0036 }
            goto L_0x005c
        L_0x0036:
            r1 = move-exception
        L_0x0039:
            if (r0 == 0) goto L_0x0053
            java.util.Iterator r3 = r0.iterator()
        L_0x003f:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x005a
            java.lang.Object r0 = r3.next()
            android.util.Pair r0 = (android.util.Pair) r0
            java.lang.Object r0 = r0.second
            java.lang.String r0 = (java.lang.String) r0
            p000.C0030ba.m2003a((java.lang.String) r0)
            goto L_0x003f
        L_0x0053:
            java.lang.String r3 = r3.mo1739f()
            p000.C0030ba.m2003a((java.lang.String) r3)
        L_0x005a:
            throw r1
        L_0x005b:
            r1 = move-exception
        L_0x005c:
            if (r0 == 0) goto L_0x0077
            java.util.Iterator r3 = r0.iterator()
        L_0x0062:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x0076
            java.lang.Object r0 = r3.next()
            android.util.Pair r0 = (android.util.Pair) r0
            java.lang.Object r0 = r0.second
            java.lang.String r0 = (java.lang.String) r0
            p000.C0030ba.m2003a((java.lang.String) r0)
            goto L_0x0062
        L_0x0076:
            return
        L_0x0077:
            java.lang.String r3 = r3.mo1739f()
            p000.C0030ba.m2003a((java.lang.String) r3)
            return
        L_0x007f:
            java.lang.String r3 = r3.mo1739f()
            p000.C0030ba.m2003a((java.lang.String) r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0040bk.onCorruption(android.database.sqlite.SQLiteDatabase):void");
    }
}
