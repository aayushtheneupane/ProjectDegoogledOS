package p000;

import android.os.Binder;
import android.os.Process;
import java.util.concurrent.Callable;

/* renamed from: ic */
/* compiled from: PG */
final class C0225ic implements Callable {

    /* renamed from: a */
    private final /* synthetic */ C0228if f13870a;

    public C0225ic(C0228if ifVar) {
        this.f13870a = ifVar;
    }

    public final Object call() {
        Throwable th;
        Object obj;
        this.f13870a.f13986c.set(true);
        try {
            Process.setThreadPriority(10);
            obj = this.f13870a.mo8187a();
            try {
                Binder.flushPendingCommands();
                this.f13870a.mo8480c(obj);
                return obj;
            } catch (Throwable th2) {
                th = th2;
                try {
                    this.f13870a.f13985b.set(true);
                    throw th;
                } catch (Throwable th3) {
                    this.f13870a.mo8480c(obj);
                    throw th3;
                }
            }
        } catch (Throwable th4) {
            th = th4;
            obj = null;
            this.f13870a.f13985b.set(true);
            throw th;
        }
    }
}
