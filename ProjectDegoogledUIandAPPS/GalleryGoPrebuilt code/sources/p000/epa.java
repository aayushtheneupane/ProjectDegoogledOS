package p000;

import android.os.Looper;
import android.os.Message;

/* renamed from: epa */
/* compiled from: PG */
final class epa extends eui {

    /* renamed from: a */
    private final /* synthetic */ epi f8736a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public epa(epi epi, Looper looper) {
        super(looper);
        this.f8736a = epi;
    }

    /* renamed from: a */
    private static final void m7957a(Message message) {
        epb epb = (epb) message.obj;
        epb.mo5100b();
        epb.mo5105d();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v28, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v2, resolved type: android.app.PendingIntent} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void handleMessage(android.os.Message r8) {
        /*
            r7 = this;
            epi r0 = r7.f8736a
            java.util.concurrent.atomic.AtomicInteger r0 = r0.f8762p
            int r0 = r0.get()
            int r1 = r8.arg1
            if (r0 == r1) goto L_0x0016
            boolean r0 = m7958b(r8)
            if (r0 == 0) goto L_0x0015
            m7957a(r8)
        L_0x0015:
            return
        L_0x0016:
            int r0 = r8.what
            r1 = 4
            r2 = 1
            r3 = 5
            if (r0 == r2) goto L_0x002b
            int r0 = r8.what
            r4 = 7
            if (r0 == r4) goto L_0x002b
            int r0 = r8.what
            if (r0 != r1) goto L_0x0027
            goto L_0x002b
        L_0x0027:
            int r0 = r8.what
            if (r0 != r3) goto L_0x0033
        L_0x002b:
            epi r0 = r7.f8736a
            boolean r0 = r0.mo5119f()
            if (r0 == 0) goto L_0x0178
        L_0x0033:
            int r0 = r8.what
            r4 = 8
            r5 = 3
            r6 = 0
            if (r0 != r1) goto L_0x0087
            epi r0 = r7.f8736a
            ejq r1 = new ejq
            int r8 = r8.arg2
            r1.<init>(r8)
            r0.f8759m = r1
            epi r8 = r7.f8736a
            boolean r0 = r8.f8760n
            if (r0 == 0) goto L_0x004d
        L_0x004c:
            goto L_0x006f
        L_0x004d:
            java.lang.String r0 = r8.mo4884b()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x006f
            boolean r0 = android.text.TextUtils.isEmpty(r6)
            if (r0 != 0) goto L_0x006f
            java.lang.String r8 = r8.mo4884b()     // Catch:{ ClassNotFoundException -> 0x006e }
            java.lang.Class.forName(r8)     // Catch:{ ClassNotFoundException -> 0x006e }
            epi r8 = r7.f8736a
            boolean r0 = r8.f8760n
            if (r0 != 0) goto L_0x004c
            r8.m7969a((int) r5, (android.os.IInterface) null)
            return
        L_0x006e:
            r8 = move-exception
        L_0x006f:
            epi r8 = r7.f8736a
            ejq r8 = r8.f8759m
            if (r8 != 0) goto L_0x007a
            ejq r8 = new ejq
            r8.<init>(r4)
        L_0x007a:
            epi r0 = r7.f8736a
            epc r0 = r0.f8754h
            r0.mo5018a(r8)
            epi r0 = r7.f8736a
            r0.mo5111a((p000.ejq) r8)
            return
        L_0x0087:
            int r0 = r8.what
            if (r0 == r3) goto L_0x0160
            int r0 = r8.what
            if (r0 != r5) goto L_0x00b1
            java.lang.Object r0 = r8.obj
            boolean r0 = r0 instanceof android.app.PendingIntent
            if (r0 == 0) goto L_0x009b
            java.lang.Object r0 = r8.obj
            r6 = r0
            android.app.PendingIntent r6 = (android.app.PendingIntent) r6
            goto L_0x009d
        L_0x009b:
        L_0x009d:
            ejq r0 = new ejq
            int r8 = r8.arg2
            r0.<init>(r8, r6)
            epi r8 = r7.f8736a
            epc r8 = r8.f8754h
            r8.mo5018a(r0)
            epi r8 = r7.f8736a
            r8.mo5111a((p000.ejq) r0)
            return
        L_0x00b1:
            int r0 = r8.what
            r1 = 6
            if (r0 != r1) goto L_0x00dc
            epi r0 = r7.f8736a
            r0.m7969a((int) r3, (android.os.IInterface) null)
            epi r0 = r7.f8736a
            eoy r0 = r0.f8757k
            if (r0 == 0) goto L_0x00ca
            int r1 = r8.arg2
            ept r0 = (p000.ept) r0
            emd r0 = r0.f8804a
            r0.mo4992a((int) r1)
        L_0x00ca:
            epi r0 = r7.f8736a
            int r8 = r8.arg2
            r0.f8747a = r8
            long r4 = java.lang.System.currentTimeMillis()
            r0.f8748b = r4
            epi r8 = r7.f8736a
            r8.mo5116a(r3, r2, r6)
            return
        L_0x00dc:
            int r0 = r8.what
            r1 = 2
            if (r0 != r1) goto L_0x00ee
            epi r0 = r7.f8736a
            boolean r0 = r0.mo5118e()
            if (r0 == 0) goto L_0x00ea
            goto L_0x00ee
        L_0x00ea:
            m7957a(r8)
            return
        L_0x00ee:
            boolean r0 = m7958b(r8)
            if (r0 == 0) goto L_0x0140
            java.lang.Object r8 = r8.obj
            r0 = r8
            epb r0 = (p000.epb) r0
            monitor-enter(r0)
            java.lang.Object r8 = r0.f8737a     // Catch:{ all -> 0x013d }
            boolean r1 = r0.f8738b     // Catch:{ all -> 0x013d }
            if (r1 == 0) goto L_0x0129
            java.lang.String r1 = "GmsClient"
            java.lang.String r3 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x013d }
            java.lang.String r4 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x013d }
            int r4 = r4.length()     // Catch:{ all -> 0x013d }
            int r4 = r4 + 47
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x013d }
            r5.<init>(r4)     // Catch:{ all -> 0x013d }
            java.lang.String r4 = "Callback proxy "
            r5.append(r4)     // Catch:{ all -> 0x013d }
            r5.append(r3)     // Catch:{ all -> 0x013d }
            java.lang.String r3 = " being reused. This is not safe."
            r5.append(r3)     // Catch:{ all -> 0x013d }
            java.lang.String r3 = r5.toString()     // Catch:{ all -> 0x013d }
            android.util.Log.w(r1, r3)     // Catch:{ all -> 0x013d }
        L_0x0129:
            monitor-exit(r0)     // Catch:{ all -> 0x013d }
            if (r8 == 0) goto L_0x0132
            r0.mo5101c()     // Catch:{ RuntimeException -> 0x0130 }
            goto L_0x0132
        L_0x0130:
            r8 = move-exception
            throw r8
        L_0x0132:
            monitor-enter(r0)
            r0.f8738b = r2     // Catch:{ all -> 0x013a }
            monitor-exit(r0)     // Catch:{ all -> 0x013a }
            r0.mo5105d()
            return
        L_0x013a:
            r8 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x013a }
            throw r8
        L_0x013d:
            r8 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x013d }
            throw r8
        L_0x0140:
            int r8 = r8.what
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r1 = 45
            r0.<init>(r1)
            java.lang.String r1 = "Don't know how to handle message: "
            r0.append(r1)
            r0.append(r8)
            java.lang.String r8 = r0.toString()
            java.lang.Exception r0 = new java.lang.Exception
            r0.<init>()
            java.lang.String r1 = "GmsClient"
            android.util.Log.wtf(r1, r8, r0)
            return
        L_0x0160:
            epi r8 = r7.f8736a
            ejq r8 = r8.f8759m
            if (r8 != 0) goto L_0x016b
            ejq r8 = new ejq
            r8.<init>(r4)
        L_0x016b:
            epi r0 = r7.f8736a
            epc r0 = r0.f8754h
            r0.mo5018a(r8)
            epi r0 = r7.f8736a
            r0.mo5111a((p000.ejq) r8)
            return
        L_0x0178:
            m7957a(r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.epa.handleMessage(android.os.Message):void");
    }

    /* renamed from: b */
    private static final boolean m7958b(Message message) {
        return message.what == 2 || message.what == 1 || message.what == 7;
    }
}
