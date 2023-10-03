package p000;

import android.os.Bundle;
import com.google.android.gms.common.api.internal.LifecycleCallback;

/* renamed from: enx */
/* compiled from: PG */
final class enx implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ LifecycleCallback f8692a;

    /* renamed from: b */
    private final /* synthetic */ String f8693b;

    /* renamed from: c */
    private final /* synthetic */ eny f8694c;

    public enx(eny eny, LifecycleCallback lifecycleCallback, String str) {
        this.f8694c = eny;
        this.f8692a = lifecycleCallback;
        this.f8693b = str;
    }

    public final void run() {
        Bundle bundle;
        eny eny = this.f8694c;
        if (eny.f8696b > 0) {
            LifecycleCallback lifecycleCallback = this.f8692a;
            Bundle bundle2 = eny.f8697c;
            if (bundle2 != null) {
                bundle = bundle2.getBundle(this.f8693b);
            } else {
                bundle = null;
            }
            lifecycleCallback.mo3515a(bundle);
        }
        if (this.f8694c.f8696b >= 2) {
            this.f8692a.mo3517c();
        }
        if (this.f8694c.f8696b >= 3) {
            ((emh) this.f8692a).mo5007f();
        }
        if (this.f8694c.f8696b >= 4) {
            this.f8692a.mo3519e();
        }
    }
}
