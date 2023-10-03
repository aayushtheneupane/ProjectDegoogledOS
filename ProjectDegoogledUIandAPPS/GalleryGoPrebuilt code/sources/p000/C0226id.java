package p000;

import android.util.Log;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/* renamed from: id */
/* compiled from: PG */
final class C0226id extends FutureTask {

    /* renamed from: a */
    private final /* synthetic */ C0228if f13903a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0226id(C0228if ifVar, Callable callable) {
        super(callable);
        this.f13903a = ifVar;
    }

    /* access modifiers changed from: protected */
    public final void done() {
        try {
            this.f13903a.mo8479b(get());
        } catch (InterruptedException e) {
            Log.w("AsyncTask", e);
        } catch (ExecutionException e2) {
            throw new RuntimeException("An error occurred while executing doInBackground()", e2.getCause());
        } catch (CancellationException e3) {
            this.f13903a.mo8479b((Object) null);
        } catch (Throwable th) {
            throw new RuntimeException("An error occurred while executing doInBackground()", th);
        }
    }
}
