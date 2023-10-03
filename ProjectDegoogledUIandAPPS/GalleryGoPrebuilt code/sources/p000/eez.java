package p000;

import android.content.ComponentName;
import android.content.Intent;

/* renamed from: eez */
/* compiled from: PG */
public final class eez implements hfl {

    /* renamed from: a */
    private final een f8130a;

    /* renamed from: b */
    private final ble f8131b;

    /* renamed from: c */
    private final iel f8132c;

    public eez(een een, ble ble, iel iel) {
        this.f8130a = een;
        this.f8131b = ble;
        this.f8132c = iel;
    }

    /* renamed from: a */
    public final ieh mo2555a(Intent intent) {
        ComponentName componentName = (ComponentName) intent.getExtras().get("android.intent.extra.CHOSEN_COMPONENT");
        return this.f8131b.mo2553a(this.f8130a.mo4750a(componentName.getClassName(), componentName.getPackageName()), this.f8132c);
    }
}
