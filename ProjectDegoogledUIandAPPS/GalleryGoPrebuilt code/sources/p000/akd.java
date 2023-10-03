package p000;

import android.content.Context;
import android.content.IntentFilter;

/* renamed from: akd */
/* compiled from: PG */
public final class akd extends ajy {
    static {
        iol.m14236b("StorageNotLowTracker");
    }

    public akd(Context context, amz amz) {
        super(context, amz);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x004f A[RETURN] */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ java.lang.Object mo560b() {
        /*
            r7 = this;
            android.content.Context r0 = r7.f658a
            android.content.IntentFilter r1 = r7.mo558a()
            r2 = 0
            android.content.Intent r0 = r0.registerReceiver(r2, r1)
            r1 = 1
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r1)
            if (r0 == 0) goto L_0x0050
            java.lang.String r4 = r0.getAction()
            if (r4 != 0) goto L_0x0019
            goto L_0x0050
        L_0x0019:
            java.lang.String r0 = r0.getAction()
            int r4 = r0.hashCode()
            r5 = -1181163412(0xffffffffb998e06c, float:-2.9158907E-4)
            r6 = 0
            if (r4 == r5) goto L_0x0038
            r5 = -730838620(0xffffffffd47049a4, float:-4.12811054E12)
            if (r4 == r5) goto L_0x002d
        L_0x002c:
            goto L_0x0042
        L_0x002d:
            java.lang.String r4 = "android.intent.action.DEVICE_STORAGE_OK"
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L_0x002c
            r0 = 0
            goto L_0x0043
        L_0x0038:
            java.lang.String r4 = "android.intent.action.DEVICE_STORAGE_LOW"
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L_0x002c
            r0 = 1
            goto L_0x0043
        L_0x0042:
            r0 = -1
        L_0x0043:
            if (r0 == 0) goto L_0x004e
            if (r0 == r1) goto L_0x0048
            goto L_0x0052
        L_0x0048:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r6)
            return r0
        L_0x004e:
            return r3
        L_0x0050:
            r2 = r3
        L_0x0052:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.akd.mo560b():java.lang.Object");
    }

    /* renamed from: a */
    public final IntentFilter mo558a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.DEVICE_STORAGE_OK");
        intentFilter.addAction("android.intent.action.DEVICE_STORAGE_LOW");
        return intentFilter;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x004e  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo559a(android.content.Intent r5) {
        /*
            r4 = this;
            java.lang.String r0 = r5.getAction()
            if (r0 == 0) goto L_0x0056
            p000.iol.m14231a()
            r0 = 1
            java.lang.Object[] r1 = new java.lang.Object[r0]
            java.lang.String r2 = r5.getAction()
            r3 = 0
            r1[r3] = r2
            java.lang.String r2 = "Received %s"
            java.lang.String.format(r2, r1)
            java.lang.String r5 = r5.getAction()
            int r1 = r5.hashCode()
            r2 = -1181163412(0xffffffffb998e06c, float:-2.9158907E-4)
            if (r1 == r2) goto L_0x0036
            r2 = -730838620(0xffffffffd47049a4, float:-4.12811054E12)
            if (r1 == r2) goto L_0x002b
        L_0x002a:
            goto L_0x0040
        L_0x002b:
            java.lang.String r1 = "android.intent.action.DEVICE_STORAGE_OK"
            boolean r5 = r5.equals(r1)
            if (r5 == 0) goto L_0x002a
            r5 = 0
            goto L_0x0041
        L_0x0036:
            java.lang.String r1 = "android.intent.action.DEVICE_STORAGE_LOW"
            boolean r5 = r5.equals(r1)
            if (r5 == 0) goto L_0x002a
            r5 = 1
            goto L_0x0041
        L_0x0040:
            r5 = -1
        L_0x0041:
            if (r5 == 0) goto L_0x004e
            if (r5 == r0) goto L_0x0046
            return
        L_0x0046:
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r3)
            r4.mo566a((java.lang.Object) r5)
            return
        L_0x004e:
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r0)
            r4.mo566a((java.lang.Object) r5)
        L_0x0056:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.akd.mo559a(android.content.Intent):void");
    }
}
