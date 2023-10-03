package p000;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.work.impl.background.systemalarm.ConstraintProxyUpdateReceiver;
import java.util.Iterator;
import java.util.List;

/* renamed from: aix */
/* compiled from: PG */
public class aix extends BroadcastReceiver {
    static {
        iol.m14236b("ConstraintProxy");
    }

    public final void onReceive(Context context, Intent intent) {
        iol.m14231a();
        String.format("onReceive : %s", new Object[]{intent});
        context.startService(aiw.m575a(context));
    }

    /* renamed from: a */
    static void m581a(Context context, List list) {
        Iterator it = list.iterator();
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        while (it.hasNext()) {
            ahb ahb = ((alg) it.next()).f721j;
            z5 |= ahb.f477d;
            z2 |= ahb.f475b;
            z3 |= ahb.f478e;
            z4 |= ahb.f482i != 1;
            if (z5 && z2 && z3 && z4) {
                break;
            }
        }
        if (z3) {
            z = true;
        }
        context.sendBroadcast(ConstraintProxyUpdateReceiver.m1131a(context, z5, z2, z, z4));
    }
}
