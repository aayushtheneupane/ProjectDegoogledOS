package p000;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* renamed from: dth */
/* compiled from: PG */
final class dth extends BroadcastReceiver {

    /* renamed from: a */
    private boolean f7318a = false;

    /* renamed from: b */
    private final /* synthetic */ dtl f7319b;

    public dth(dtl dtl) {
        this.f7319b = dtl;
    }

    public final void onReceive(Context context, Intent intent) {
        boolean z = this.f7318a;
        boolean z2 = false;
        if (intent.getIntExtra("state", 0) == 1) {
            z2 = true;
        }
        this.f7318a = z2;
        if (z && !z2) {
            dtl dtl = this.f7319b;
            if (dtl.mo4415j()) {
                dtl.mo4416k();
            }
        }
    }
}
