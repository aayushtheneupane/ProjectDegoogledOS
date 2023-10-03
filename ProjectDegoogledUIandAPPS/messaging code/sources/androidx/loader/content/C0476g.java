package androidx.loader.content;

import android.os.Binder;
import android.os.Process;
import java.util.concurrent.Callable;

/* renamed from: androidx.loader.content.g */
class C0476g implements Callable {
    final /* synthetic */ C0479j this$0;

    C0476g(C0479j jVar) {
        this.this$0 = jVar;
    }

    public Object call() {
        this.this$0.f454lq.set(true);
        Object obj = null;
        try {
            Process.setThreadPriority(10);
            obj = this.this$0.mo4483Kc();
            Binder.flushPendingCommands();
            this.this$0.mo4529n(obj);
            return obj;
        } catch (Throwable th) {
            this.this$0.mo4529n(obj);
            throw th;
        }
    }
}
