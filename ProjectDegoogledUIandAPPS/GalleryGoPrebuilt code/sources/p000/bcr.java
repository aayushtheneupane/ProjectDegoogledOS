package p000;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.Collection;

/* renamed from: bcr */
/* compiled from: PG */
final class bcr extends BroadcastReceiver {

    /* renamed from: a */
    private final /* synthetic */ bcs f2060a;

    public bcr(bcs bcs) {
        this.f2060a = bcs;
    }

    public final void onReceive(Context context, Intent intent) {
        bcs bcs = this.f2060a;
        boolean z = bcs.f2062b;
        bcs.f2062b = bcs.m2164a(context);
        bcs bcs2 = this.f2060a;
        boolean z2 = bcs2.f2062b;
        if (z != z2) {
            bco bco = bcs2.f2061a;
            if (z2) {
                synchronized (((apm) bco).f1353b) {
                    bde bde = ((apm) bco).f1352a;
                    for (bea bea : bfp.m2428a((Collection) bde.f2078a)) {
                        if (!bea.mo1887e() && !bea.mo1889f()) {
                            bea.mo1880b();
                            if (bde.f2080c) {
                                bde.f2079b.add(bea);
                            } else {
                                bea.mo1878a();
                            }
                        }
                    }
                }
            }
        }
    }
}
