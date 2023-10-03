package p000;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: hbj */
/* compiled from: PG */
final class hbj extends BroadcastReceiver {

    /* renamed from: a */
    private final /* synthetic */ AtomicBoolean f12449a;

    /* renamed from: b */
    private final /* synthetic */ hbk f12450b;

    public hbj(hbk hbk, AtomicBoolean atomicBoolean) {
        this.f12450b = hbk;
        this.f12449a = atomicBoolean;
    }

    public final void onReceive(Context context, Intent intent) {
        if (this.f12449a.compareAndSet(false, true)) {
            context.unregisterReceiver(this);
            hlp a = ((hlz) this.f12450b.f12456f.mo2097a()).mo7577a("StartupAfterPackageReplacedUnlock");
            try {
                this.f12450b.mo7256a(false);
                if (a != null) {
                    a.close();
                    return;
                }
                return;
            } catch (Throwable th) {
                ifn.m12935a(th, th);
            }
        } else {
            return;
        }
        throw th;
    }
}
