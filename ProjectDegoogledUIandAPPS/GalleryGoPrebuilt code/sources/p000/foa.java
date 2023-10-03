package p000;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* renamed from: foa */
/* compiled from: PG */
public final class foa extends BroadcastReceiver {

    /* renamed from: a */
    public final fmw f10129a;

    /* renamed from: b */
    public final hqk f10130b;

    /* renamed from: c */
    private final hqk f10131c;

    public foa(fmw fmw, hqk hqk, hqk hqk2) {
        this.f10129a = fmw;
        this.f10130b = hqk;
        this.f10131c = hqk2;
    }

    public final void onReceive(Context context, Intent intent) {
        hqk hqk;
        iel iel;
        flw.m9199b("PrimesShutdown", "BroadcastReceiver: action = %s", intent.getAction());
        if (this.f10129a.f10070a) {
            context.unregisterReceiver(this);
        } else if ("com.google.gservices.intent.action.GSERVICES_CHANGED".equals(intent.getAction()) && (hqk = this.f10131c) != null && (iel = (iel) hqk.mo2652a()) != null) {
            iel.mo5931a((Runnable) new fnz(this));
        }
    }
}
