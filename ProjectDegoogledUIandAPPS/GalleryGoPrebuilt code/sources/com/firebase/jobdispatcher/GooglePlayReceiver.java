package com.firebase.jobdispatcher;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.Looper;
import android.os.Messenger;
import android.util.Log;
import java.util.ArrayList;

/* compiled from: PG */
public class GooglePlayReceiver extends Service implements bhc {

    /* renamed from: a */
    public static final bhu f4795a = new bhu("com.firebase.jobdispatcher.");

    /* renamed from: b */
    public static final C0309lf f4796b = new C0309lf(1);

    /* renamed from: c */
    private Messenger f4797c;

    /* renamed from: d */
    private bgy f4798d;

    /* renamed from: e */
    private bik f4799e;

    /* renamed from: f */
    private bhd f4800f;

    /* renamed from: g */
    private int f4801g;

    /* renamed from: h */
    private boolean f4802h = false;

    /* renamed from: b */
    public final synchronized boolean mo3312b() {
        return this.f4802h;
    }

    /* renamed from: a */
    public final synchronized bhd mo3310a() {
        if (this.f4800f == null) {
            this.f4800f = new bhd(new bgw(getApplicationContext()), this, m4721d(), this);
        }
        return this.f4800f;
    }

    /* renamed from: d */
    private final synchronized bgy m4721d() {
        if (this.f4798d == null) {
            this.f4798d = new bhg(getApplicationContext());
        }
        return this.f4798d;
    }

    /* renamed from: c */
    private final synchronized Messenger m4720c() {
        if (this.f4797c == null) {
            this.f4797c = new Messenger(new bhj(Looper.getMainLooper(), this));
        }
        return this.f4797c;
    }

    /* renamed from: e */
    private final synchronized bik m4722e() {
        if (this.f4799e == null) {
            this.f4799e = new bik(m4721d().mo2028a());
        }
        return this.f4799e;
    }

    public final IBinder onBind(Intent intent) {
        if (intent == null) {
            return null;
        }
        int i = Build.VERSION.SDK_INT;
        if ("com.google.android.gms.gcm.ACTION_TASK_READY".equals(intent.getAction())) {
            return m4720c().getBinder();
        }
        return null;
    }

    public final synchronized void onDestroy() {
        ArrayList arrayList;
        bhd a = mo3310a();
        synchronized (bhd.f2361a) {
            arrayList = new ArrayList(bhd.f2361a.values());
            bhd.f2361a.clear();
        }
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ((bic) arrayList.get(i)).mo2078b();
        }
        a.f2367g.shutdownNow();
        this.f4802h = true;
        super.onDestroy();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0029, code lost:
        if (r5.f2414c == false) goto L_0x004c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002f, code lost:
        if ((r5.f2419h instanceof p000.bid) == false) goto L_0x004c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0032, code lost:
        if (r6 == 1) goto L_0x004c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0034, code lost:
        r6 = new p000.bhr(m4722e(), r5);
        r6.mo2055j();
        m4721d().mo2029a(r6.mo2045a());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004c, code lost:
        m4719a(r2, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x004f, code lost:
        r5 = f4796b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0051, code lost:
        monitor-enter(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0058, code lost:
        if (f4796b.isEmpty() == false) goto L_0x005f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x005a, code lost:
        stopSelf(r4.f4801g);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x005f, code lost:
        monitor-exit(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0060, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0065, code lost:
        r5 = f4796b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0067, code lost:
        monitor-enter(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x006e, code lost:
        if (f4796b.isEmpty() == false) goto L_0x0075;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0070, code lost:
        stopSelf(r4.f4801g);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0075, code lost:
        monitor-exit(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0076, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x007b, code lost:
        r5 = f4796b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x007d, code lost:
        monitor-enter(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0084, code lost:
        if (f4796b.isEmpty() == false) goto L_0x008b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0086, code lost:
        stopSelf(r4.f4801g);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x008b, code lost:
        monitor-exit(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x008c, code lost:
        return;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo2036a(p000.bhw r5, int r6) {
        /*
            r4 = this;
            lf r0 = f4796b     // Catch:{ all -> 0x0093 }
            monitor-enter(r0)     // Catch:{ all -> 0x0093 }
            lf r1 = f4796b     // Catch:{ all -> 0x0090 }
            java.lang.String r2 = r5.f2413b     // Catch:{ all -> 0x0090 }
            java.lang.Object r1 = r1.get(r2)     // Catch:{ all -> 0x0090 }
            lf r1 = (p000.C0309lf) r1     // Catch:{ all -> 0x0090 }
            if (r1 == 0) goto L_0x007a
            java.lang.String r2 = r5.f2412a     // Catch:{ all -> 0x0090 }
            java.lang.Object r2 = r1.remove(r2)     // Catch:{ all -> 0x0090 }
            bht r2 = (p000.bht) r2     // Catch:{ all -> 0x0090 }
            if (r2 == 0) goto L_0x0064
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x0090 }
            if (r1 == 0) goto L_0x0026
            lf r1 = f4796b     // Catch:{ all -> 0x0090 }
            java.lang.String r3 = r5.f2413b     // Catch:{ all -> 0x0090 }
            r1.remove(r3)     // Catch:{ all -> 0x0090 }
        L_0x0026:
            monitor-exit(r0)     // Catch:{ all -> 0x0090 }
            boolean r0 = r5.f2414c     // Catch:{ all -> 0x0093 }
            if (r0 == 0) goto L_0x004c
            cns r0 = r5.f2419h     // Catch:{ all -> 0x0093 }
            boolean r0 = r0 instanceof p000.bid     // Catch:{ all -> 0x0093 }
            if (r0 == 0) goto L_0x004c
            r0 = 1
            if (r6 == r0) goto L_0x004c
            bhr r6 = new bhr     // Catch:{ all -> 0x0093 }
            bik r0 = r4.m4722e()     // Catch:{ all -> 0x0093 }
            r6.<init>(r0, r5)     // Catch:{ all -> 0x0093 }
            r6.mo2055j()     // Catch:{ all -> 0x0093 }
            bhs r5 = r6.mo2045a()     // Catch:{ all -> 0x0093 }
            bgy r6 = r4.m4721d()     // Catch:{ all -> 0x0093 }
            r6.mo2029a((p000.bhs) r5)     // Catch:{ all -> 0x0093 }
            goto L_0x004f
        L_0x004c:
            m4719a((p000.bht) r2, (int) r6)     // Catch:{ all -> 0x0093 }
        L_0x004f:
            lf r5 = f4796b
            monitor-enter(r5)
            lf r6 = f4796b     // Catch:{ all -> 0x0061 }
            boolean r6 = r6.isEmpty()     // Catch:{ all -> 0x0061 }
            if (r6 == 0) goto L_0x005f
            int r6 = r4.f4801g     // Catch:{ all -> 0x0061 }
            r4.stopSelf(r6)     // Catch:{ all -> 0x0061 }
        L_0x005f:
            monitor-exit(r5)     // Catch:{ all -> 0x0061 }
            return
        L_0x0061:
            r6 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x0061 }
            throw r6
        L_0x0064:
            monitor-exit(r0)     // Catch:{ all -> 0x0090 }
            lf r5 = f4796b
            monitor-enter(r5)
            lf r6 = f4796b     // Catch:{ all -> 0x0077 }
            boolean r6 = r6.isEmpty()     // Catch:{ all -> 0x0077 }
            if (r6 == 0) goto L_0x0075
            int r6 = r4.f4801g     // Catch:{ all -> 0x0077 }
            r4.stopSelf(r6)     // Catch:{ all -> 0x0077 }
        L_0x0075:
            monitor-exit(r5)     // Catch:{ all -> 0x0077 }
            return
        L_0x0077:
            r6 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x0077 }
            throw r6
        L_0x007a:
            monitor-exit(r0)     // Catch:{ all -> 0x0090 }
            lf r5 = f4796b
            monitor-enter(r5)
            lf r6 = f4796b     // Catch:{ all -> 0x008d }
            boolean r6 = r6.isEmpty()     // Catch:{ all -> 0x008d }
            if (r6 == 0) goto L_0x008b
            int r6 = r4.f4801g     // Catch:{ all -> 0x008d }
            r4.stopSelf(r6)     // Catch:{ all -> 0x008d }
        L_0x008b:
            monitor-exit(r5)     // Catch:{ all -> 0x008d }
            return
        L_0x008d:
            r6 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x008d }
            throw r6
        L_0x0090:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0090 }
            throw r5     // Catch:{ all -> 0x0093 }
        L_0x0093:
            r5 = move-exception
            lf r6 = f4796b
            monitor-enter(r6)
            lf r0 = f4796b     // Catch:{ all -> 0x00a6 }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x00a6 }
            if (r0 == 0) goto L_0x00a4
            int r0 = r4.f4801g     // Catch:{ all -> 0x00a6 }
            r4.stopSelf(r0)     // Catch:{ all -> 0x00a6 }
        L_0x00a4:
            monitor-exit(r6)     // Catch:{ all -> 0x00a6 }
            throw r5
        L_0x00a6:
            r5 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x00a6 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.firebase.jobdispatcher.GooglePlayReceiver.mo2036a(bhw, int):void");
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
        	at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:64)
        	at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:70)
        	at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:248)
        	at java.base/java.util.Objects.checkIndex(Objects.java:372)
        	at java.base/java.util.ArrayList.get(ArrayList.java:458)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:698)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:693)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    public final int onStartCommand(android.content.Intent r10, int r11, int r12) {
        /*
            r9 = this;
            super.onStartCommand(r10, r11, r12)     // Catch:{ all -> 0x01a1 }
            r11 = 2
            if (r10 == 0) goto L_0x0183
            java.lang.String r0 = r10.getAction()     // Catch:{ all -> 0x01a1 }
            java.lang.String r1 = "com.google.android.gms.gcm.ACTION_TASK_READY"
            boolean r1 = r1.equals(r0)     // Catch:{ all -> 0x01a1 }
            if (r1 != 0) goto L_0x004f
            java.lang.String r10 = "com.google.android.gms.gcm.SERVICE_ACTION_INITIALIZE"
            boolean r10 = r10.equals(r0)     // Catch:{ all -> 0x01a1 }
            if (r10 != 0) goto L_0x0038
            java.lang.String r10 = "FJD.GooglePlayReceiver"
            java.lang.String r0 = "Unknown action received, terminating"
            android.util.Log.e(r10, r0)     // Catch:{ all -> 0x01a1 }
            lf r10 = f4796b
            monitor-enter(r10)
            r9.f4801g = r12     // Catch:{ all -> 0x0035 }
            lf r12 = f4796b     // Catch:{ all -> 0x0035 }
            boolean r12 = r12.isEmpty()     // Catch:{ all -> 0x0035 }
            if (r12 == 0) goto L_0x0033
            int r12 = r9.f4801g     // Catch:{ all -> 0x0035 }
            r9.stopSelf(r12)     // Catch:{ all -> 0x0035 }
        L_0x0033:
            monitor-exit(r10)     // Catch:{ all -> 0x0035 }
            return r11
        L_0x0035:
            r11 = move-exception
            monitor-exit(r10)     // Catch:{ all -> 0x0035 }
            throw r11
        L_0x0038:
            lf r10 = f4796b
            monitor-enter(r10)
            r9.f4801g = r12     // Catch:{ all -> 0x004c }
            lf r12 = f4796b     // Catch:{ all -> 0x004c }
            boolean r12 = r12.isEmpty()     // Catch:{ all -> 0x004c }
            if (r12 == 0) goto L_0x004a
            int r12 = r9.f4801g     // Catch:{ all -> 0x004c }
            r9.stopSelf(r12)     // Catch:{ all -> 0x004c }
        L_0x004a:
            monitor-exit(r10)     // Catch:{ all -> 0x004c }
            return r11
        L_0x004c:
            r11 = move-exception
            monitor-exit(r10)     // Catch:{ all -> 0x004c }
            throw r11
        L_0x004f:
            bhd r0 = r9.mo3310a()     // Catch:{ all -> 0x01a1 }
            android.os.Bundle r10 = r10.getExtras()     // Catch:{ all -> 0x01a1 }
            r1 = 0
            if (r10 != 0) goto L_0x0063
            java.lang.String r10 = "FJD.GooglePlayReceiver"
            java.lang.String r2 = "No data provided, terminating"
            android.util.Log.e(r10, r2)     // Catch:{ all -> 0x01a1 }
        L_0x0061:
            goto L_0x0164
        L_0x0063:
            android.os.Bundle r2 = new android.os.Bundle     // Catch:{ all -> 0x01a1 }
            r2.<init>()     // Catch:{ all -> 0x01a1 }
            android.os.Parcel r10 = p000.bhf.m2530a((android.os.Bundle) r10)     // Catch:{ all -> 0x01a1 }
            int r3 = r10.readInt()     // Catch:{ all -> 0x017e }
            if (r3 > 0) goto L_0x007f
            java.lang.String r2 = "FJD.GooglePlayReceiver"
            java.lang.String r3 = "No callback received, terminating"
            android.util.Log.w(r2, r3)     // Catch:{ all -> 0x017e }
        L_0x0079:
            r10.recycle()     // Catch:{ all -> 0x01a1 }
            r2 = r1
            goto L_0x0154
        L_0x007f:
            int r3 = r10.readInt()     // Catch:{ all -> 0x017e }
            r4 = 1279544898(0x4c444e42, float:5.146036E7)
            if (r3 == r4) goto L_0x0090
            java.lang.String r2 = "FJD.GooglePlayReceiver"
            java.lang.String r3 = "No callback received, terminating"
            android.util.Log.w(r2, r3)     // Catch:{ all -> 0x017e }
            goto L_0x0079
        L_0x0090:
            int r3 = r10.readInt()     // Catch:{ all -> 0x017e }
            r4 = 0
            r5 = r1
        L_0x0096:
            if (r4 >= r3) goto L_0x0142
            boolean r6 = p000.bhf.m2532a()     // Catch:{ all -> 0x017e }
            if (r6 != 0) goto L_0x00b2
            java.lang.Object r6 = r10.readValue(r1)     // Catch:{ all -> 0x017e }
            boolean r7 = r6 instanceof java.lang.String     // Catch:{ all -> 0x017e }
            if (r7 != 0) goto L_0x00af
            java.lang.String r6 = "FJD.GooglePlayReceiver"
            java.lang.String r7 = "Bad callback received, terminating"
            android.util.Log.w(r6, r7)     // Catch:{ all -> 0x017e }
            r6 = r1
            goto L_0x00b6
        L_0x00af:
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ all -> 0x017e }
            goto L_0x00b6
        L_0x00b2:
            java.lang.String r6 = r10.readString()     // Catch:{ all -> 0x017e }
        L_0x00b6:
            if (r6 != 0) goto L_0x00ba
        L_0x00b8:
            goto L_0x013e
        L_0x00ba:
            if (r5 != 0) goto L_0x00f4
            java.lang.String r7 = "callback"
            boolean r7 = r7.equals(r6)     // Catch:{ all -> 0x017e }
            if (r7 != 0) goto L_0x00c5
            goto L_0x00f4
        L_0x00c5:
            int r5 = r10.readInt()     // Catch:{ all -> 0x017e }
            r6 = 4
            if (r5 != r6) goto L_0x00ec
            java.lang.String r5 = "com.google.android.gms.gcm.PendingCallback"
            java.lang.String r6 = r10.readString()     // Catch:{ all -> 0x017e }
            boolean r5 = r5.equals(r6)     // Catch:{ all -> 0x017e }
            if (r5 == 0) goto L_0x00e4
            android.os.IBinder r5 = r10.readStrongBinder()     // Catch:{ all -> 0x017e }
            bhh r6 = new bhh     // Catch:{ all -> 0x017e }
            r6.<init>(r5)     // Catch:{ all -> 0x017e }
            r5 = r6
            goto L_0x013e
        L_0x00e4:
            java.lang.String r2 = "FJD.GooglePlayReceiver"
            java.lang.String r3 = "Bad callback received, terminating"
            android.util.Log.w(r2, r3)     // Catch:{ all -> 0x017e }
            goto L_0x0079
        L_0x00ec:
            java.lang.String r2 = "FJD.GooglePlayReceiver"
            java.lang.String r3 = "Bad callback received, terminating"
            android.util.Log.w(r2, r3)     // Catch:{ all -> 0x017e }
            goto L_0x0079
        L_0x00f4:
            java.lang.Object r7 = r10.readValue(r1)     // Catch:{ all -> 0x017e }
            boolean r8 = r7 instanceof java.lang.String     // Catch:{ all -> 0x017e }
            if (r8 == 0) goto L_0x0103
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ all -> 0x017e }
            r2.putString(r6, r7)     // Catch:{ all -> 0x017e }
            goto L_0x00b8
        L_0x0103:
            boolean r8 = r7 instanceof java.lang.Boolean     // Catch:{ all -> 0x017e }
            if (r8 == 0) goto L_0x0111
            java.lang.Boolean r7 = (java.lang.Boolean) r7     // Catch:{ all -> 0x017e }
            boolean r7 = r7.booleanValue()     // Catch:{ all -> 0x017e }
            r2.putBoolean(r6, r7)     // Catch:{ all -> 0x017e }
            goto L_0x00b8
        L_0x0111:
            boolean r8 = r7 instanceof java.lang.Integer     // Catch:{ all -> 0x017e }
            if (r8 == 0) goto L_0x011f
            java.lang.Integer r7 = (java.lang.Integer) r7     // Catch:{ all -> 0x017e }
            int r7 = r7.intValue()     // Catch:{ all -> 0x017e }
            r2.putInt(r6, r7)     // Catch:{ all -> 0x017e }
            goto L_0x00b8
        L_0x011f:
            boolean r8 = r7 instanceof java.util.ArrayList     // Catch:{ all -> 0x017e }
            if (r8 == 0) goto L_0x0129
            java.util.ArrayList r7 = (java.util.ArrayList) r7     // Catch:{ all -> 0x017e }
            r2.putParcelableArrayList(r6, r7)     // Catch:{ all -> 0x017e }
            goto L_0x00b8
        L_0x0129:
            boolean r8 = r7 instanceof android.os.Bundle     // Catch:{ all -> 0x017e }
            if (r8 == 0) goto L_0x0133
            android.os.Bundle r7 = (android.os.Bundle) r7     // Catch:{ all -> 0x017e }
            r2.putBundle(r6, r7)     // Catch:{ all -> 0x017e }
            goto L_0x00b8
        L_0x0133:
            boolean r8 = r7 instanceof android.os.Parcelable     // Catch:{ all -> 0x017e }
            if (r8 == 0) goto L_0x013e
            android.os.Parcelable r7 = (android.os.Parcelable) r7     // Catch:{ all -> 0x017e }
            r2.putParcelable(r6, r7)     // Catch:{ all -> 0x017e }
            goto L_0x00b8
        L_0x013e:
            int r4 = r4 + 1
            goto L_0x0096
        L_0x0142:
            if (r5 != 0) goto L_0x014d
            java.lang.String r2 = "FJD.GooglePlayReceiver"
            java.lang.String r3 = "No callback received, terminating"
            android.util.Log.w(r2, r3)     // Catch:{ all -> 0x017e }
            goto L_0x0079
        L_0x014d:
            android.util.Pair r2 = android.util.Pair.create(r5, r2)     // Catch:{ all -> 0x017e }
            r10.recycle()     // Catch:{ all -> 0x01a1 }
        L_0x0154:
            if (r2 != 0) goto L_0x0158
            goto L_0x0061
        L_0x0158:
            java.lang.Object r10 = r2.first     // Catch:{ all -> 0x01a1 }
            bht r10 = (p000.bht) r10     // Catch:{ all -> 0x01a1 }
            java.lang.Object r1 = r2.second     // Catch:{ all -> 0x01a1 }
            android.os.Bundle r1 = (android.os.Bundle) r1     // Catch:{ all -> 0x01a1 }
            bhw r1 = r9.mo3311a((p000.bht) r10, (android.os.Bundle) r1)     // Catch:{ all -> 0x01a1 }
        L_0x0164:
            r0.mo2037a(r1)     // Catch:{ all -> 0x01a1 }
            lf r10 = f4796b
            monitor-enter(r10)
            r9.f4801g = r12     // Catch:{ all -> 0x017b }
            lf r12 = f4796b     // Catch:{ all -> 0x017b }
            boolean r12 = r12.isEmpty()     // Catch:{ all -> 0x017b }
            if (r12 == 0) goto L_0x0179
            int r12 = r9.f4801g     // Catch:{ all -> 0x017b }
            r9.stopSelf(r12)     // Catch:{ all -> 0x017b }
        L_0x0179:
            monitor-exit(r10)     // Catch:{ all -> 0x017b }
            return r11
        L_0x017b:
            r11 = move-exception
            monitor-exit(r10)     // Catch:{ all -> 0x017b }
            throw r11
        L_0x017e:
            r11 = move-exception
            r10.recycle()     // Catch:{ all -> 0x01a1 }
            throw r11     // Catch:{ all -> 0x01a1 }
        L_0x0183:
            java.lang.String r10 = "FJD.GooglePlayReceiver"
            java.lang.String r0 = "Null Intent passed, terminating"
            android.util.Log.w(r10, r0)     // Catch:{ all -> 0x01a1 }
            lf r10 = f4796b
            monitor-enter(r10)
            r9.f4801g = r12     // Catch:{ all -> 0x019e }
            lf r12 = f4796b     // Catch:{ all -> 0x019e }
            boolean r12 = r12.isEmpty()     // Catch:{ all -> 0x019e }
            if (r12 == 0) goto L_0x019c
            int r12 = r9.f4801g     // Catch:{ all -> 0x019e }
            r9.stopSelf(r12)     // Catch:{ all -> 0x019e }
        L_0x019c:
            monitor-exit(r10)     // Catch:{ all -> 0x019e }
            return r11
        L_0x019e:
            r11 = move-exception
            monitor-exit(r10)     // Catch:{ all -> 0x019e }
            throw r11
        L_0x01a1:
            r10 = move-exception
            lf r11 = f4796b
            monitor-enter(r11)
            r9.f4801g = r12     // Catch:{ all -> 0x01b6 }
            lf r12 = f4796b     // Catch:{ all -> 0x01b6 }
            boolean r12 = r12.isEmpty()     // Catch:{ all -> 0x01b6 }
            if (r12 == 0) goto L_0x01b4
            int r12 = r9.f4801g     // Catch:{ all -> 0x01b6 }
            r9.stopSelf(r12)     // Catch:{ all -> 0x01b6 }
        L_0x01b4:
            monitor-exit(r11)     // Catch:{ all -> 0x01b6 }
            throw r10
        L_0x01b6:
            r10 = move-exception
            monitor-exit(r11)     // Catch:{ all -> 0x01b6 }
            goto L_0x01ba
        L_0x01b9:
            throw r10
        L_0x01ba:
            goto L_0x01b9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.firebase.jobdispatcher.GooglePlayReceiver.onStartCommand(android.content.Intent, int, int):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0033  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x003f  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final p000.bhw mo3311a(p000.bht r5, android.os.Bundle r6) {
        /*
            r4 = this;
            bhu r0 = f4795a
            r1 = 0
            if (r6 != 0) goto L_0x000e
            java.lang.String r6 = "FJD.ExecutionDelegator"
            java.lang.String r0 = "Unexpected null Bundle provided"
            android.util.Log.e(r6, r0)
        L_0x000c:
            r6 = r1
            goto L_0x0031
        L_0x000e:
            java.lang.String r2 = "extras"
            android.os.Bundle r2 = r6.getBundle(r2)
            if (r2 == 0) goto L_0x002f
            bhv r0 = r0.mo2058a(r2)
            java.lang.String r2 = "triggered_uris"
            java.util.ArrayList r6 = r6.getParcelableArrayList(r2)
            if (r6 == 0) goto L_0x002a
            cof r6 = new cof
            r6.<init>()
            r0.f2411j = r6
        L_0x002a:
            bhw r6 = r0.mo2059a()
            goto L_0x0031
        L_0x002f:
            goto L_0x000c
        L_0x0031:
            if (r6 != 0) goto L_0x003f
            java.lang.String r6 = "FJD.GooglePlayReceiver"
            java.lang.String r0 = "unable to decode job"
            android.util.Log.e(r6, r0)
            r6 = 2
            m4719a((p000.bht) r5, (int) r6)
            return r1
        L_0x003f:
            lf r0 = f4796b
            monitor-enter(r0)
            lf r1 = f4796b     // Catch:{ all -> 0x0062 }
            java.lang.String r2 = r6.f2413b     // Catch:{ all -> 0x0062 }
            java.lang.Object r1 = r1.get(r2)     // Catch:{ all -> 0x0062 }
            lf r1 = (p000.C0309lf) r1     // Catch:{ all -> 0x0062 }
            if (r1 != 0) goto L_0x005b
            lf r1 = new lf     // Catch:{ all -> 0x0062 }
            r2 = 1
            r1.<init>((int) r2)     // Catch:{ all -> 0x0062 }
            lf r2 = f4796b     // Catch:{ all -> 0x0062 }
            java.lang.String r3 = r6.f2413b     // Catch:{ all -> 0x0062 }
            r2.put(r3, r1)     // Catch:{ all -> 0x0062 }
        L_0x005b:
            java.lang.String r2 = r6.f2412a     // Catch:{ all -> 0x0062 }
            r1.put(r2, r5)     // Catch:{ all -> 0x0062 }
            monitor-exit(r0)     // Catch:{ all -> 0x0062 }
            return r6
        L_0x0062:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0062 }
            goto L_0x0066
        L_0x0065:
            throw r5
        L_0x0066:
            goto L_0x0065
        */
        throw new UnsupportedOperationException("Method not decompiled: com.firebase.jobdispatcher.GooglePlayReceiver.mo3311a(bht, android.os.Bundle):bhw");
    }

    /* renamed from: a */
    private static void m4719a(bht bht, int i) {
        try {
            bht.mo2039a(i);
        } catch (Throwable th) {
            String valueOf = String.valueOf(th.getMessage());
            Log.e("FJD.GooglePlayReceiver", valueOf.length() == 0 ? new String("Encountered error running callback: ") : "Encountered error running callback: ".concat(valueOf));
        }
    }
}
