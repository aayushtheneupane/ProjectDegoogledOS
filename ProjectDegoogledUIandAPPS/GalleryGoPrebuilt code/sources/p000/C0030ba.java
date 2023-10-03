package p000;

import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;
import java.io.File;

/* renamed from: ba */
/* compiled from: PG */
public final class C0030ba {

    /* renamed from: a */
    public C0046bq f1928a;

    /* renamed from: b */
    public final C0054by f1929b;

    /* renamed from: c */
    public final String f1930c;

    /* renamed from: d */
    public final String f1931d;

    public C0030ba() {
    }

    /* renamed from: a */
    public static final void m2003a(String str) {
        if (!str.equalsIgnoreCase(":memory:") && str.trim().length() != 0) {
            Log.w("SupportSQLite", "deleting the database file: " + str);
            try {
                int i = Build.VERSION.SDK_INT;
                SQLiteDatabase.deleteDatabase(new File(str));
            } catch (Exception e) {
                Log.w("SupportSQLite", "delete failed: ", e);
            }
        }
    }

    public C0030ba(C0046bq bqVar, C0054by byVar, String str, String str2) {
        this.f1928a = bqVar;
        this.f1929b = byVar;
        this.f1930c = str;
        this.f1931d = str2;
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0017, code lost:
        r2 = r3;
     */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0028  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x006e A[EDGE_INSN: B:72:0x006e->B:30:0x006e ?: BREAK  , SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo1750a(p000.C0028az r11, int r12, int r13) {
        /*
            r10 = this;
            bq r0 = r10.f1928a
            r1 = 0
            if (r0 == 0) goto L_0x00f8
            bw r0 = r0.f3340c
            r2 = 0
            if (r12 == r13) goto L_0x006a
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            r4 = r12
        L_0x0010:
            if (r13 > r12) goto L_0x0015
            if (r4 <= r13) goto L_0x0017
            goto L_0x0019
        L_0x0015:
            if (r4 < r13) goto L_0x0019
        L_0x0017:
            r2 = r3
            goto L_0x006e
        L_0x0019:
            java.util.HashMap r5 = r0.f3752a
            java.lang.Integer r6 = java.lang.Integer.valueOf(r4)
            java.lang.Object r5 = r5.get(r6)
            java.util.TreeMap r5 = (java.util.TreeMap) r5
            if (r5 != 0) goto L_0x0028
        L_0x0027:
            goto L_0x006e
        L_0x0028:
            if (r13 <= r12) goto L_0x002f
            java.util.NavigableSet r6 = r5.descendingKeySet()
            goto L_0x0033
        L_0x002f:
            java.util.Set r6 = r5.keySet()
        L_0x0033:
            java.util.Iterator r6 = r6.iterator()
        L_0x0037:
            boolean r7 = r6.hasNext()
            r8 = 1
            if (r7 == 0) goto L_0x0066
            java.lang.Object r7 = r6.next()
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            if (r13 > r12) goto L_0x0050
            if (r7 >= r13) goto L_0x004d
            goto L_0x0056
        L_0x004d:
            if (r7 >= r4) goto L_0x0056
            goto L_0x0054
        L_0x0050:
            if (r7 > r13) goto L_0x0056
            if (r7 <= r4) goto L_0x0056
        L_0x0054:
            r9 = 1
            goto L_0x0057
        L_0x0056:
            r9 = 0
        L_0x0057:
            if (r9 == 0) goto L_0x0037
            java.lang.Integer r4 = java.lang.Integer.valueOf(r7)
            java.lang.Object r4 = r5.get(r4)
            r3.add(r4)
            r4 = r7
            goto L_0x0067
        L_0x0066:
            r8 = 0
        L_0x0067:
            if (r8 != 0) goto L_0x0010
            goto L_0x0027
        L_0x006a:
            java.util.List r2 = java.util.Collections.emptyList()
        L_0x006e:
            if (r2 == 0) goto L_0x00f8
            java.util.ArrayList r12 = new java.util.ArrayList
            r12.<init>()
            java.lang.String r13 = "SELECT name FROM sqlite_master WHERE type = 'trigger'"
            android.database.Cursor r13 = r11.mo1733b(r13)
        L_0x007b:
            boolean r0 = r13.moveToNext()     // Catch:{ all -> 0x00f3 }
            if (r0 != 0) goto L_0x00ea
            r13.close()
            int r13 = r12.size()
        L_0x0088:
            if (r1 >= r13) goto L_0x00af
            java.lang.Object r0 = r12.get(r1)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r3 = "room_fts_content_sync_"
            boolean r3 = r0.startsWith(r3)
            if (r3 == 0) goto L_0x00ac
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "DROP TRIGGER IF EXISTS "
            r3.append(r4)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r11.mo1736c(r0)
        L_0x00ac:
            int r1 = r1 + 1
            goto L_0x0088
        L_0x00af:
            java.util.Iterator r12 = r2.iterator()
        L_0x00b3:
            boolean r13 = r12.hasNext()
            if (r13 == 0) goto L_0x00c3
            java.lang.Object r13 = r12.next()
            cf r13 = (p000.C0062cf) r13
            r13.mo519a(r11)
            goto L_0x00b3
        L_0x00c3:
            by r12 = r10.f1929b
            bz r12 = r12.mo521b(r11)
            boolean r13 = r12.f3923a
            if (r13 == 0) goto L_0x00d1
            r10.mo1749a((p000.C0028az) r11)
            return
        L_0x00d1:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            java.lang.String r0 = "Migration didn't properly handle: "
            r13.append(r0)
            java.lang.String r12 = r12.f3924b
            r13.append(r12)
            java.lang.String r12 = r13.toString()
            r11.<init>(r12)
            throw r11
        L_0x00ea:
            java.lang.String r0 = r13.getString(r1)     // Catch:{ all -> 0x00f3 }
            r12.add(r0)     // Catch:{ all -> 0x00f3 }
            goto L_0x007b
        L_0x00f3:
            r11 = move-exception
            r13.close()
            throw r11
        L_0x00f8:
            bq r0 = r10.f1928a
            if (r0 == 0) goto L_0x014f
            if (r12 <= r13) goto L_0x0103
            boolean r2 = r0.f3346i
            if (r2 == 0) goto L_0x0103
            goto L_0x0107
        L_0x0103:
            boolean r0 = r0.f3345h
            if (r0 != 0) goto L_0x014f
        L_0x0107:
            by r12 = r10.f1929b
            java.lang.String r13 = "DROP TABLE IF EXISTS `Dependency`"
            r11.mo1736c(r13)
            java.lang.String r13 = "DROP TABLE IF EXISTS `WorkSpec`"
            r11.mo1736c(r13)
            java.lang.String r13 = "DROP TABLE IF EXISTS `WorkTag`"
            r11.mo1736c(r13)
            java.lang.String r13 = "DROP TABLE IF EXISTS `SystemIdInfo`"
            r11.mo1736c(r13)
            java.lang.String r13 = "DROP TABLE IF EXISTS `WorkName`"
            r11.mo1736c(r13)
            java.lang.String r13 = "DROP TABLE IF EXISTS `WorkProgress`"
            r11.mo1736c(r13)
            java.lang.String r13 = "DROP TABLE IF EXISTS `Preference`"
            r11.mo1736c(r13)
            aio r12 = (p000.aio) r12
            androidx.work.impl.WorkDatabase_Impl r13 = r12.f548a
            java.util.List r13 = r13.f3806g
            if (r13 != 0) goto L_0x0135
            goto L_0x0149
        L_0x0135:
            int r13 = r13.size()
        L_0x0139:
            if (r1 >= r13) goto L_0x0149
            androidx.work.impl.WorkDatabase_Impl r0 = r12.f548a
            java.util.List r0 = r0.f3806g
            java.lang.Object r0 = r0.get(r1)
            cun r0 = (p000.cun) r0
            int r1 = r1 + 1
            goto L_0x0139
        L_0x0149:
            by r12 = r10.f1929b
            r12.mo520a(r11)
            return
        L_0x014f:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "A migration from "
            r0.append(r1)
            r0.append(r12)
            java.lang.String r12 = " to "
            r0.append(r12)
            r0.append(r13)
            java.lang.String r12 = " was required but not found. Please provide the necessary Migration path via RoomDatabase.Builder.addMigration(Migration ...) or allow for destructive migrations via one of the RoomDatabase.Builder.fallbackToDestructiveMigration* methods."
            r0.append(r12)
            java.lang.String r12 = r0.toString()
            r11.<init>(r12)
            goto L_0x0174
        L_0x0173:
            throw r11
        L_0x0174:
            goto L_0x0173
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0030ba.mo1750a(az, int, int):void");
    }

    /* renamed from: a */
    public final void mo1749a(C0028az azVar) {
        azVar.mo1736c("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        String str = this.f1930c;
        azVar.mo1736c("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '" + str + "')");
    }
}
