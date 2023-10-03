package com.android.messaging.datamodel;

import android.content.Context;
import android.database.ContentObserver;
import android.provider.Telephony;
import android.util.Log;
import com.android.messaging.datamodel.action.SyncMessagesAction;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1449g;
import com.android.messaging.util.C1451h;
import com.android.messaging.util.C1464na;
import com.android.messaging.util.C1474sa;
import p000a.p005b.C0019f;

/* renamed from: com.android.messaging.datamodel.U */
public class C0779U {

    /* renamed from: Fy */
    private long f1040Fy = -1;

    /* renamed from: Gy */
    private long f1041Gy = -1;

    /* renamed from: Hy */
    private long f1042Hy = -1;

    /* renamed from: Iy */
    private final C0778T f1043Iy = new C0778T();

    /* renamed from: Jy */
    private C0019f f1044Jy = null;

    /* renamed from: Ky */
    private final ContentObserver f1045Ky = new C0777S(this);
    /* access modifiers changed from: private */

    /* renamed from: Ly */
    public boolean f1046Ly = false;
    /* access modifiers changed from: private */

    /* renamed from: My */
    public boolean f1047My = false;

    C0779U() {
    }

    public synchronized void complete() {
        if (Log.isLoggable("MessagingApp", 3)) {
            C1430e.m3620d("MessagingApp", "SyncManager: Sync started at " + this.f1040Fy + " marked as complete");
        }
        this.f1040Fy = -1;
        this.f1044Jy = null;
    }

    /* renamed from: d */
    public long mo5913d(long j) {
        C1449g gVar = C1449g.get();
        long j2 = C1451h.m3724Hd().getLong("last_full_sync_time_millis", -1);
        gVar.getLong("bugle_sms_full_sync_backoff_time", 3600000);
        long j3 = (j2 < 0 ? j : j2 + 3600000) - j;
        if (j3 > 0) {
            return j3;
        }
        return 0;
    }

    /* renamed from: e */
    public synchronized C0776Q mo5914e(long j) {
        if (this.f1044Jy == null) {
            return null;
        }
        return (C0776Q) this.f1044Jy.get(j, (Object) null);
    }

    /* renamed from: f */
    public synchronized boolean mo5915f(long j) {
        boolean z;
        z = true;
        C1424b.m3592ia(this.f1041Gy >= 0);
        long j2 = this.f1042Hy;
        if (j2 < 0 || j2 < j) {
            z = false;
        }
        if (Log.isLoggable("MessagingApp", 3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("SyncManager: Sync batch of messages from ");
            sb.append(j);
            sb.append(" to ");
            sb.append(this.f1041Gy);
            sb.append(" is ");
            sb.append(z ? "DIRTY" : "clean");
            sb.append("; max change timestamp = ");
            sb.append(this.f1042Hy);
            C1430e.m3620d("MessagingApp", sb.toString());
        }
        this.f1041Gy = -1;
        this.f1042Hy = -1;
        return z;
    }

    /* renamed from: g */
    public synchronized boolean mo5916g(long j) {
        boolean z;
        z = true;
        C1424b.m3592ia(j >= 0);
        if (j != this.f1041Gy) {
            z = false;
        }
        return z;
    }

    /* renamed from: h */
    public synchronized void mo5917h(long j) {
        if (this.f1041Gy >= 0 && j <= this.f1041Gy) {
            this.f1042Hy = Math.max(this.f1041Gy, j);
            if (Log.isLoggable("MessagingApp", 3)) {
                C1430e.m3620d("MessagingApp", "SyncManager: New message @ " + j + " before upper bound of current sync batch " + this.f1041Gy);
            }
        } else if (Log.isLoggable("MessagingApp", 3)) {
            C1430e.m3620d("MessagingApp", "SyncManager: New message @ " + j + " after upper bound of current sync batch " + this.f1041Gy);
        }
    }

    /* renamed from: i */
    public synchronized void mo5918i(long j) {
        C1424b.m3592ia(this.f1041Gy < 0);
        this.f1041Gy = j;
        this.f1042Hy = -1;
    }

    /* renamed from: j */
    public void mo5919j(Context context) {
        if (!C1474sa.getDefault().mo8228kk()) {
            this.f1047My = false;
            this.f1046Ly = true;
        } else if (C1464na.m3762ak()) {
            this.f1047My = true;
            this.f1046Ly = true;
        } else {
            this.f1047My = false;
            this.f1046Ly = false;
            if (context.getPackageManager().hasSystemFeature("android.hardware.type.automotive")) {
                this.f1047My = true;
                this.f1046Ly = false;
            }
        }
        if (this.f1047My || this.f1046Ly) {
            context.getContentResolver().registerContentObserver(Telephony.MmsSms.CONTENT_URI, true, this.f1045Ky);
        } else {
            context.getContentResolver().unregisterContentObserver(this.f1045Ky);
        }
        SyncMessagesAction.m1448Ke();
    }

    /* renamed from: re */
    public boolean mo5920re() {
        return C1451h.m3724Hd().getLong("last_sync_time_millis", -1) != -1;
    }

    /* renamed from: se */
    public C0778T mo5921se() {
        return this.f1043Iy;
    }

    /* renamed from: te */
    public synchronized boolean mo5922te() {
        return this.f1040Fy >= 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0069, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00a0, code lost:
        return false;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean mo5911a(boolean r7, long r8) {
        /*
            r6 = this;
            monitor-enter(r6)
            java.lang.String r0 = "MessagingApp"
            r1 = 2
            boolean r0 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x00d3 }
            if (r0 == 0) goto L_0x002f
            java.lang.String r0 = "MessagingApp"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00d3 }
            r1.<init>()     // Catch:{ all -> 0x00d3 }
            java.lang.String r2 = "SyncManager: Checking shouldSync "
            r1.append(r2)     // Catch:{ all -> 0x00d3 }
            if (r7 == 0) goto L_0x001b
            java.lang.String r2 = "full "
            goto L_0x001d
        L_0x001b:
            java.lang.String r2 = ""
        L_0x001d:
            r1.append(r2)     // Catch:{ all -> 0x00d3 }
            java.lang.String r2 = "at "
            r1.append(r2)     // Catch:{ all -> 0x00d3 }
            r1.append(r8)     // Catch:{ all -> 0x00d3 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00d3 }
            com.android.messaging.util.C1430e.m3628v(r0, r1)     // Catch:{ all -> 0x00d3 }
        L_0x002f:
            r0 = 0
            r1 = 3
            if (r7 == 0) goto L_0x006a
            long r2 = r6.mo5913d(r8)     // Catch:{ all -> 0x00d3 }
            r4 = 0
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 <= 0) goto L_0x006a
            java.lang.String r7 = "MessagingApp"
            boolean r7 = android.util.Log.isLoggable(r7, r1)     // Catch:{ all -> 0x00d3 }
            if (r7 == 0) goto L_0x0068
            java.lang.String r7 = "MessagingApp"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00d3 }
            r1.<init>()     // Catch:{ all -> 0x00d3 }
            java.lang.String r4 = "SyncManager: Full sync requested for "
            r1.append(r4)     // Catch:{ all -> 0x00d3 }
            r1.append(r8)     // Catch:{ all -> 0x00d3 }
            java.lang.String r8 = " delayed for "
            r1.append(r8)     // Catch:{ all -> 0x00d3 }
            r1.append(r2)     // Catch:{ all -> 0x00d3 }
            java.lang.String r8 = " ms"
            r1.append(r8)     // Catch:{ all -> 0x00d3 }
            java.lang.String r8 = r1.toString()     // Catch:{ all -> 0x00d3 }
            com.android.messaging.util.C1430e.m3620d(r7, r8)     // Catch:{ all -> 0x00d3 }
        L_0x0068:
            monitor-exit(r6)
            return r0
        L_0x006a:
            boolean r2 = r6.mo5922te()     // Catch:{ all -> 0x00d3 }
            if (r2 == 0) goto L_0x00a1
            java.lang.String r8 = "MessagingApp"
            boolean r8 = android.util.Log.isLoggable(r8, r1)     // Catch:{ all -> 0x00d3 }
            if (r8 == 0) goto L_0x009f
            java.lang.String r8 = "MessagingApp"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x00d3 }
            r9.<init>()     // Catch:{ all -> 0x00d3 }
            java.lang.String r1 = "SyncManager: Not allowed to "
            r9.append(r1)     // Catch:{ all -> 0x00d3 }
            if (r7 == 0) goto L_0x0089
            java.lang.String r7 = "full "
            goto L_0x008b
        L_0x0089:
            java.lang.String r7 = ""
        L_0x008b:
            r9.append(r7)     // Catch:{ all -> 0x00d3 }
            java.lang.String r7 = "sync yet; still running sync started at "
            r9.append(r7)     // Catch:{ all -> 0x00d3 }
            long r1 = r6.f1040Fy     // Catch:{ all -> 0x00d3 }
            r9.append(r1)     // Catch:{ all -> 0x00d3 }
            java.lang.String r7 = r9.toString()     // Catch:{ all -> 0x00d3 }
            com.android.messaging.util.C1430e.m3620d(r8, r7)     // Catch:{ all -> 0x00d3 }
        L_0x009f:
            monitor-exit(r6)
            return r0
        L_0x00a1:
            java.lang.String r0 = "MessagingApp"
            boolean r0 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x00d3 }
            if (r0 == 0) goto L_0x00ce
            java.lang.String r0 = "MessagingApp"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00d3 }
            r1.<init>()     // Catch:{ all -> 0x00d3 }
            java.lang.String r2 = "SyncManager: Starting "
            r1.append(r2)     // Catch:{ all -> 0x00d3 }
            if (r7 == 0) goto L_0x00ba
            java.lang.String r7 = "full "
            goto L_0x00bc
        L_0x00ba:
            java.lang.String r7 = ""
        L_0x00bc:
            r1.append(r7)     // Catch:{ all -> 0x00d3 }
            java.lang.String r7 = "sync at "
            r1.append(r7)     // Catch:{ all -> 0x00d3 }
            r1.append(r8)     // Catch:{ all -> 0x00d3 }
            java.lang.String r7 = r1.toString()     // Catch:{ all -> 0x00d3 }
            com.android.messaging.util.C1430e.m3620d(r0, r7)     // Catch:{ all -> 0x00d3 }
        L_0x00ce:
            r6.f1040Fy = r8     // Catch:{ all -> 0x00d3 }
            r7 = 1
            monitor-exit(r6)
            return r7
        L_0x00d3:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.C0779U.mo5911a(boolean, long):boolean");
    }
}
