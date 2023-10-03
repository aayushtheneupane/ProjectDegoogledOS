package p000;

import android.os.Handler;
import java.util.concurrent.Callable;

/* renamed from: ka */
/* compiled from: PG */
final class C0277ka implements Runnable {

    /* renamed from: a */
    public final /* synthetic */ C0279kc f15112a;

    /* renamed from: b */
    private final /* synthetic */ Callable f15113b;

    /* renamed from: c */
    private final /* synthetic */ Handler f15114c;

    public C0277ka(Callable callable, Handler handler, C0279kc kcVar) {
        this.f15113b = callable;
        this.f15114c = handler;
        this.f15112a = kcVar;
    }

    public final void run() {
        C0272jw jwVar;
        try {
            jwVar = ((C0266jq) this.f15113b).call();
        } catch (Exception e) {
            jwVar = null;
        }
        this.f15114c.post(new C0275jz(this, jwVar));
    }
}
