package p000;

import android.database.Cursor;
import java.util.HashSet;
import java.util.Set;

/* renamed from: bs */
/* compiled from: PG */
final class C0048bs implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ C0050bu f3477a;

    public C0048bs(C0050bu buVar) {
        this.f3477a = buVar;
    }

    /* JADX INFO: finally extract failed */
    /* renamed from: a */
    private final Set m3506a() {
        HashSet hashSet = new HashSet();
        Cursor a = this.f3477a.f3599a.mo2840a((C0036bg) new C0027ay("SELECT * FROM room_table_modification_log WHERE invalidated = 1;"));
        while (a.moveToNext()) {
            try {
                hashSet.add(Integer.valueOf(a.getInt(0)));
            } catch (Throwable th) {
                a.close();
                throw th;
            }
        }
        a.close();
        if (!hashSet.isEmpty()) {
            this.f3477a.f3602d.mo2033b();
        }
        return hashSet;
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0098  */
    /* JADX WARNING: Removed duplicated region for block: B:64:? A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r6 = this;
            bu r0 = r6.f3477a
            bx r0 = r0.f3599a
            java.util.concurrent.locks.Lock r0 = r0.mo2842a()
            r1 = 0
            r0.lock()     // Catch:{ IllegalStateException -> 0x0082, SQLiteException -> 0x0080 }
            bu r2 = r6.f3477a     // Catch:{ IllegalStateException -> 0x0082, SQLiteException -> 0x0080 }
            bx r3 = r2.f3599a     // Catch:{ IllegalStateException -> 0x0082, SQLiteException -> 0x0080 }
            az r3 = r3.f3800a     // Catch:{ IllegalStateException -> 0x0082, SQLiteException -> 0x0080 }
            if (r3 != 0) goto L_0x0015
            goto L_0x0072
        L_0x0015:
            boolean r3 = r3.mo1738e()     // Catch:{ IllegalStateException -> 0x0082, SQLiteException -> 0x0080 }
            if (r3 == 0) goto L_0x0072
            boolean r3 = r2.f3601c     // Catch:{ IllegalStateException -> 0x0082, SQLiteException -> 0x0080 }
            if (r3 != 0) goto L_0x0026
            bx r3 = r2.f3599a     // Catch:{ IllegalStateException -> 0x0082, SQLiteException -> 0x0080 }
            be r3 = r3.f3802c     // Catch:{ IllegalStateException -> 0x0082, SQLiteException -> 0x0080 }
            r3.mo1892a()     // Catch:{ IllegalStateException -> 0x0082, SQLiteException -> 0x0080 }
        L_0x0026:
            boolean r2 = r2.f3601c     // Catch:{ IllegalStateException -> 0x0082, SQLiteException -> 0x0080 }
            if (r2 == 0) goto L_0x0076
            bu r2 = r6.f3477a     // Catch:{ IllegalStateException -> 0x0082, SQLiteException -> 0x0080 }
            java.util.concurrent.atomic.AtomicBoolean r2 = r2.f3600b     // Catch:{ IllegalStateException -> 0x0082, SQLiteException -> 0x0080 }
            r3 = 1
            r4 = 0
            boolean r2 = r2.compareAndSet(r3, r4)     // Catch:{ IllegalStateException -> 0x0082, SQLiteException -> 0x0080 }
            if (r2 == 0) goto L_0x0072
            bu r2 = r6.f3477a     // Catch:{ IllegalStateException -> 0x0082, SQLiteException -> 0x0080 }
            bx r2 = r2.f3599a     // Catch:{ IllegalStateException -> 0x0082, SQLiteException -> 0x0080 }
            boolean r2 = r2.mo2848h()     // Catch:{ IllegalStateException -> 0x0082, SQLiteException -> 0x0080 }
            if (r2 != 0) goto L_0x0072
            bu r2 = r6.f3477a     // Catch:{ IllegalStateException -> 0x0082, SQLiteException -> 0x0080 }
            bx r2 = r2.f3599a     // Catch:{ IllegalStateException -> 0x0082, SQLiteException -> 0x0080 }
            boolean r3 = r2.f3805f     // Catch:{ IllegalStateException -> 0x0082, SQLiteException -> 0x0080 }
            if (r3 != 0) goto L_0x004d
            java.util.Set r2 = r6.m3506a()     // Catch:{ IllegalStateException -> 0x0082, SQLiteException -> 0x0080 }
            goto L_0x0062
        L_0x004d:
            be r2 = r2.f3802c     // Catch:{ IllegalStateException -> 0x0082, SQLiteException -> 0x0080 }
            az r2 = r2.mo1892a()     // Catch:{ IllegalStateException -> 0x0082, SQLiteException -> 0x0080 }
            r2.mo1731a()     // Catch:{ IllegalStateException -> 0x0082, SQLiteException -> 0x0080 }
            java.util.Set r3 = r6.m3506a()     // Catch:{ all -> 0x0068 }
            r2.mo1735c()     // Catch:{ all -> 0x0066 }
            r2.mo1734b()     // Catch:{ IllegalStateException -> 0x0070, SQLiteException -> 0x006e }
            r2 = r3
        L_0x0062:
            r0.unlock()
            goto L_0x008f
        L_0x0066:
            r4 = move-exception
            goto L_0x006a
        L_0x0068:
            r4 = move-exception
            r3 = r1
        L_0x006a:
            r2.mo1734b()     // Catch:{ IllegalStateException -> 0x0070, SQLiteException -> 0x006e }
            throw r4     // Catch:{ IllegalStateException -> 0x0070, SQLiteException -> 0x006e }
        L_0x006e:
            r2 = move-exception
            goto L_0x0084
        L_0x0070:
            r2 = move-exception
            goto L_0x0084
        L_0x0072:
            r0.unlock()
            return
        L_0x0076:
            java.lang.String r2 = "ROOM"
            java.lang.String r3 = "database is not initialized even though it is open"
            android.util.Log.e(r2, r3)     // Catch:{ IllegalStateException -> 0x0082, SQLiteException -> 0x0080 }
            goto L_0x0072
        L_0x007e:
            r1 = move-exception
            goto L_0x00be
        L_0x0080:
            r2 = move-exception
            goto L_0x0083
        L_0x0082:
            r2 = move-exception
        L_0x0083:
            r3 = r1
        L_0x0084:
            java.lang.String r4 = "ROOM"
            java.lang.String r5 = "Cannot run invalidation tracker. Is the db closed?"
            android.util.Log.e(r4, r5, r2)     // Catch:{ all -> 0x007e }
            r0.unlock()
            r2 = r3
        L_0x008f:
            if (r2 != 0) goto L_0x0092
            goto L_0x00bd
        L_0x0092:
            boolean r0 = r2.isEmpty()
            if (r0 != 0) goto L_0x00bd
            bu r0 = r6.f3477a
            l r0 = r0.f3603e
            monitor-enter(r0)
            bu r2 = r6.f3477a     // Catch:{ all -> 0x00ba }
            l r2 = r2.f3603e     // Catch:{ all -> 0x00ba }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x00ba }
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x00ba }
            if (r3 != 0) goto L_0x00ad
            monitor-exit(r0)     // Catch:{ all -> 0x00ba }
            return
        L_0x00ad:
            java.lang.Object r2 = r2.next()     // Catch:{ all -> 0x00ba }
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2     // Catch:{ all -> 0x00ba }
            java.lang.Object r2 = r2.getValue()     // Catch:{ all -> 0x00ba }
            lr r2 = (p000.C0321lr) r2     // Catch:{ all -> 0x00ba }
            throw r1     // Catch:{ all -> 0x00ba }
        L_0x00ba:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00ba }
            throw r1
        L_0x00bd:
            return
        L_0x00be:
            r0.unlock()
            goto L_0x00c3
        L_0x00c2:
            throw r1
        L_0x00c3:
            goto L_0x00c2
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0048bs.run():void");
    }
}
