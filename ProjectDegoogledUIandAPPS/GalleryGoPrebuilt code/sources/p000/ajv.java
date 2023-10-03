package p000;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;

/* renamed from: ajv */
/* compiled from: PG */
public final class ajv extends ajy {

    /* renamed from: e */
    private static final String f652e = iol.m14236b("BatteryChrgTracker");

    public ajv(Context context, amz amz) {
        super(context, amz);
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ Object mo560b() {
        Intent registerReceiver = this.f658a.registerReceiver((BroadcastReceiver) null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        boolean z = false;
        if (registerReceiver == null) {
            iol.m14231a();
            iol.m14234a(f652e, "getInitialState - null intent received", new Throwable[0]);
            return null;
        }
        int i = Build.VERSION.SDK_INT;
        int intExtra = registerReceiver.getIntExtra("status", -1);
        if (intExtra == 2 || intExtra == 5) {
            z = true;
        }
        return Boolean.valueOf(z);
    }

    /* renamed from: a */
    public final IntentFilter mo558a() {
        IntentFilter intentFilter = new IntentFilter();
        int i = Build.VERSION.SDK_INT;
        intentFilter.addAction("android.os.action.CHARGING");
        intentFilter.addAction("android.os.action.DISCHARGING");
        return intentFilter;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo559a(android.content.Intent r6) {
        /*
            r5 = this;
            java.lang.String r6 = r6.getAction()
            if (r6 == 0) goto L_0x0073
            p000.iol.m14231a()
            r0 = 1
            java.lang.Object[] r1 = new java.lang.Object[r0]
            r2 = 0
            r1[r2] = r6
            java.lang.String r3 = "Received %s"
            java.lang.String.format(r3, r1)
            int r1 = r6.hashCode()
            r3 = 3
            r4 = 2
            switch(r1) {
                case -1886648615: goto L_0x003d;
                case -54942926: goto L_0x0033;
                case 948344062: goto L_0x0029;
                case 1019184907: goto L_0x001e;
                default: goto L_0x001d;
            }
        L_0x001d:
            goto L_0x0047
        L_0x001e:
            java.lang.String r1 = "android.intent.action.ACTION_POWER_CONNECTED"
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x001d
            r6 = 2
            goto L_0x0048
        L_0x0029:
            java.lang.String r1 = "android.os.action.CHARGING"
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x001d
            r6 = 0
            goto L_0x0048
        L_0x0033:
            java.lang.String r1 = "android.os.action.DISCHARGING"
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x001d
            r6 = 1
            goto L_0x0048
        L_0x003d:
            java.lang.String r1 = "android.intent.action.ACTION_POWER_DISCONNECTED"
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x001d
            r6 = 3
            goto L_0x0048
        L_0x0047:
            r6 = -1
        L_0x0048:
            if (r6 == 0) goto L_0x006b
            if (r6 == r0) goto L_0x0062
            if (r6 == r4) goto L_0x0059
            if (r6 == r3) goto L_0x0051
            return
        L_0x0051:
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r2)
            r5.mo566a((java.lang.Object) r6)
            return
        L_0x0059:
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r0)
            r5.mo566a((java.lang.Object) r6)
            return
        L_0x0062:
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r2)
            r5.mo566a((java.lang.Object) r6)
            return
        L_0x006b:
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r0)
            r5.mo566a((java.lang.Object) r6)
        L_0x0073:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.ajv.mo559a(android.content.Intent):void");
    }
}
