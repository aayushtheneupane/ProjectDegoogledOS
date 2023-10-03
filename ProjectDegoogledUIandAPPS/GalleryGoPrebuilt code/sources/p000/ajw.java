package p000;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/* renamed from: ajw */
/* compiled from: PG */
public final class ajw extends ajy {

    /* renamed from: e */
    private static final String f653e = iol.m14236b("BatteryNotLowTracker");

    public ajw(Context context, amz amz) {
        super(context, amz);
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ Object mo560b() {
        Intent registerReceiver = this.f658a.registerReceiver((BroadcastReceiver) null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        boolean z = false;
        if (registerReceiver != null) {
            int intExtra = registerReceiver.getIntExtra("plugged", 0);
            int intExtra2 = registerReceiver.getIntExtra("status", -1);
            float intExtra3 = ((float) registerReceiver.getIntExtra("level", -1)) / ((float) registerReceiver.getIntExtra("scale", -1));
            if (intExtra != 0 || intExtra2 == 1 || intExtra3 > 0.15f) {
                z = true;
            }
            return Boolean.valueOf(z);
        }
        iol.m14231a();
        iol.m14234a(f653e, "getInitialState - null intent received", new Throwable[0]);
        return null;
    }

    /* renamed from: a */
    public final IntentFilter mo558a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.BATTERY_OKAY");
        intentFilter.addAction("android.intent.action.BATTERY_LOW");
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
            r2 = -1980154005(0xffffffff89f93f6b, float:-6.0004207E-33)
            if (r1 == r2) goto L_0x0036
            r2 = 490310653(0x1d398bfd, float:2.4556918E-21)
            if (r1 == r2) goto L_0x002b
        L_0x002a:
            goto L_0x0040
        L_0x002b:
            java.lang.String r1 = "android.intent.action.BATTERY_LOW"
            boolean r5 = r5.equals(r1)
            if (r5 == 0) goto L_0x002a
            r5 = 1
            goto L_0x0041
        L_0x0036:
            java.lang.String r1 = "android.intent.action.BATTERY_OKAY"
            boolean r5 = r5.equals(r1)
            if (r5 == 0) goto L_0x002a
            r5 = 0
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
        throw new UnsupportedOperationException("Method not decompiled: p000.ajw.mo559a(android.content.Intent):void");
    }
}
