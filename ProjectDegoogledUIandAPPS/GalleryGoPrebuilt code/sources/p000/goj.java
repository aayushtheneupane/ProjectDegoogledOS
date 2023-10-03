package p000;

import android.os.PowerManager;

/* renamed from: goj */
/* compiled from: PG */
final /* synthetic */ class goj implements Runnable {

    /* renamed from: a */
    private final PowerManager.WakeLock f11738a;

    public goj(PowerManager.WakeLock wakeLock) {
        this.f11738a = wakeLock;
    }

    public final void run() {
        this.f11738a.release();
    }
}
