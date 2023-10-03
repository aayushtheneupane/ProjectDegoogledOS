package p000;

import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;
import java.util.concurrent.Executor;

/* renamed from: hx */
/* compiled from: PG */
public abstract class C0219hx extends C0224ib {

    /* renamed from: a */
    public volatile C0218hw f13568a;

    /* renamed from: b */
    public volatile C0218hw f13569b;

    /* renamed from: j */
    private Executor f13570j;

    public C0219hx(Context context) {
        super(context);
    }

    /* renamed from: b */
    public abstract Object mo6171b();

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo8241a(C0218hw hwVar) {
        if (this.f13569b == hwVar) {
            SystemClock.uptimeMillis();
            this.f13569b = null;
            mo8240a();
        }
    }

    /* renamed from: a */
    public final void mo8240a() {
        if (this.f13569b == null && this.f13568a != null) {
            if (this.f13570j == null) {
                this.f13570j = AsyncTask.THREAD_POOL_EXECUTOR;
            }
            C0218hw hwVar = this.f13568a;
            Executor executor = this.f13570j;
            if (hwVar.f13987d != 1) {
                int i = hwVar.f13987d;
                int i2 = i - 1;
                if (i == 0) {
                    throw null;
                } else if (i2 == 1) {
                    throw new IllegalStateException("Cannot execute task: the task is already running.");
                } else if (i2 != 2) {
                    throw new IllegalStateException("We should never reach this state");
                } else {
                    throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
                }
            } else {
                hwVar.f13987d = 2;
                executor.execute(hwVar.f13984a);
            }
        }
    }

    /* renamed from: c */
    public final void mo8242c() {
        if (this.f13568a != null) {
            if (!this.f13819f) {
                this.f13822i = true;
            }
            if (this.f13569b == null) {
                C0218hw hwVar = this.f13568a;
                hwVar.f13985b.set(true);
                if (hwVar.f13984a.cancel(false)) {
                    this.f13569b = this.f13568a;
                }
                this.f13568a = null;
                return;
            }
            this.f13568a = null;
        }
    }
}
