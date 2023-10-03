package androidx.loader.content;

import android.util.Log;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/* renamed from: androidx.loader.content.h */
class C0477h extends FutureTask {
    final /* synthetic */ C0479j this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C0477h(C0479j jVar, Callable callable) {
        super(callable);
        this.this$0 = jVar;
    }

    /* access modifiers changed from: protected */
    public void done() {
        try {
            Object obj = get();
            C0479j jVar = this.this$0;
            if (!jVar.f454lq.get()) {
                jVar.mo4529n(obj);
            }
        } catch (InterruptedException e) {
            Log.w("AsyncTask", e);
        } catch (ExecutionException e2) {
            throw new RuntimeException("An error occurred while executing doInBackground()", e2.getCause());
        } catch (CancellationException unused) {
            C0479j jVar2 = this.this$0;
            if (!jVar2.f454lq.get()) {
                jVar2.mo4529n((Object) null);
            }
        } catch (Throwable th) {
            throw new RuntimeException("An error occurred while executing doInBackground()", th);
        }
    }
}
