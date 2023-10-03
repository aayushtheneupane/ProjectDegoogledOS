package p000;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/* renamed from: ens */
/* compiled from: PG */
public final class ens extends BroadcastReceiver {

    /* renamed from: a */
    public Context f8689a;

    /* renamed from: b */
    private final enr f8690b;

    public ens(enr enr) {
        this.f8690b = enr;
    }

    public final void onReceive(Context context, Intent intent) {
        String str;
        Uri data = intent.getData();
        if (data != null) {
            str = data.getSchemeSpecificPart();
        } else {
            str = null;
        }
        if ("com.google.android.gms".equals(str)) {
            this.f8690b.mo4982a();
            mo5065a();
        }
    }

    /* renamed from: a */
    public final synchronized void mo5065a() {
        Context context = this.f8689a;
        if (context != null) {
            context.unregisterReceiver(this);
        }
        this.f8689a = null;
    }
}
