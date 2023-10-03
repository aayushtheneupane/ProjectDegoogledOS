package p000;

import android.os.Handler;
import android.os.Looper;

/* renamed from: aue */
/* compiled from: PG */
public final class aue {

    /* renamed from: a */
    private boolean f1703a;

    /* renamed from: b */
    private final Handler f1704b = new Handler(Looper.getMainLooper(), new aud());

    /* renamed from: a */
    public final synchronized void mo1628a(aua aua) {
        if (this.f1703a) {
            this.f1704b.obtainMessage(1, aua).sendToTarget();
            return;
        }
        this.f1703a = true;
        aua.mo1607d();
        this.f1703a = false;
    }
}
