package p000;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/* renamed from: ajy */
/* compiled from: PG */
public abstract class ajy extends aka {

    /* renamed from: e */
    private final BroadcastReceiver f655e = new ajx(this);

    static {
        iol.m14236b("BrdcstRcvrCnstrntTrckr");
    }

    /* renamed from: a */
    public abstract IntentFilter mo558a();

    /* renamed from: a */
    public abstract void mo559a(Intent intent);

    public ajy(Context context, amz amz) {
        super(context, amz);
    }

    /* renamed from: c */
    public final void mo562c() {
        iol.m14231a();
        String.format("%s: registering receiver", new Object[]{getClass().getSimpleName()});
        this.f658a.registerReceiver(this.f655e, mo558a());
    }

    /* renamed from: d */
    public final void mo563d() {
        iol.m14231a();
        String.format("%s: unregistering receiver", new Object[]{getClass().getSimpleName()});
        this.f658a.unregisterReceiver(this.f655e);
    }
}
