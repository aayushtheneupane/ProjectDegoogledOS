package p000;

import android.content.ComponentCallbacks2;
import android.content.res.Configuration;

/* renamed from: hdq */
/* compiled from: PG */
final class hdq implements ComponentCallbacks2 {

    /* renamed from: a */
    private final /* synthetic */ hdt f12543a;

    public hdq(hdt hdt) {
        this.f12543a = hdt;
    }

    public final void onConfigurationChanged(Configuration configuration) {
    }

    public final void onLowMemory() {
    }

    public final void onTrimMemory(int i) {
        if (this.f12543a.f12550a == null) {
            return;
        }
        if (i == 60 || i == 80) {
            this.f12543a.f12550a.mo1438a();
        }
    }
}
