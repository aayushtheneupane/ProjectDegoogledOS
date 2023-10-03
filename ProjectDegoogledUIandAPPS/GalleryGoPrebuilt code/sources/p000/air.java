package p000;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

/* renamed from: air */
/* compiled from: PG */
final class air implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ String f563a;

    /* renamed from: b */
    private final /* synthetic */ ait f564b;

    /* renamed from: c */
    private final /* synthetic */ amx f565c;

    public air(ait ait, amx amx, String str) {
        this.f564b = ait;
        this.f565c = amx;
        this.f563a = str;
    }

    public final void run() {
        ait ait;
        try {
            ihg ihg = (ihg) this.f565c.get();
            if (ihg != null) {
                iol.m14231a();
                String str = ait.f573a;
                String.format("%s returned a %s result.", new Object[]{this.f564b.f574b.f714c, ihg});
                this.f564b.f579g = ihg;
            } else {
                iol.m14231a();
                iol.m14234a(ait.f573a, String.format("%s returned a null result. Treating it as a failure.", new Object[]{this.f564b.f574b.f714c}), new Throwable[0]);
            }
            ait = this.f564b;
        } catch (CancellationException e) {
            iol.m14231a();
            String str2 = ait.f573a;
            String.format("%s was cancelled", new Object[]{this.f563a});
            new Throwable[1][0] = e;
            ait = this.f564b;
        } catch (InterruptedException | ExecutionException e2) {
            iol.m14231a();
            iol.m14234a(ait.f573a, String.format("%s failed because it threw an exception/error", new Object[]{this.f563a}), e2);
            ait = this.f564b;
        } catch (Throwable th) {
            this.f564b.mo528a();
            throw th;
        }
        ait.mo528a();
    }
}
