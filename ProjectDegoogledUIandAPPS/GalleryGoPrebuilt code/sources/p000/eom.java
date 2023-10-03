package p000;

import android.os.Bundle;
import com.google.android.gms.common.api.internal.LifecycleCallback;

/* renamed from: eom */
/* compiled from: PG */
final class eom implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ LifecycleCallback f8714a;

    /* renamed from: b */
    private final /* synthetic */ String f8715b;

    /* renamed from: c */
    private final /* synthetic */ eon f8716c;

    public eom(eon eon, LifecycleCallback lifecycleCallback, String str) {
        this.f8716c = eon;
        this.f8714a = lifecycleCallback;
        this.f8715b = str;
    }

    public final void run() {
        Bundle bundle;
        eon eon = this.f8716c;
        if (eon.f8718b > 0) {
            LifecycleCallback lifecycleCallback = this.f8714a;
            Bundle bundle2 = eon.f8719c;
            if (bundle2 != null) {
                bundle = bundle2.getBundle(this.f8715b);
            } else {
                bundle = null;
            }
            lifecycleCallback.mo3515a(bundle);
        }
        if (this.f8716c.f8718b >= 2) {
            this.f8714a.mo3517c();
        }
        if (this.f8716c.f8718b >= 3) {
            ((emh) this.f8714a).mo5007f();
        }
        if (this.f8716c.f8718b >= 4) {
            this.f8714a.mo3519e();
        }
    }
}
