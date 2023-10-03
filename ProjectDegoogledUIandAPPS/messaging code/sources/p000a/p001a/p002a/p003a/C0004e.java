package p000a.p001a.p002a.p003a;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* renamed from: a.a.a.a.e */
public class C0004e extends C0005f {

    /* renamed from: Nn */
    private final ExecutorService f1Nn = Executors.newFixedThreadPool(4, new C0003d(this));

    /* renamed from: Pn */
    private volatile Handler f2Pn;
    private final Object mLock = new Object();

    /* renamed from: e */
    public void mo3e(Runnable runnable) {
        this.f1Nn.execute(runnable);
    }

    /* renamed from: f */
    public void mo4f(Runnable runnable) {
        if (this.f2Pn == null) {
            synchronized (this.mLock) {
                if (this.f2Pn == null) {
                    Looper mainLooper = Looper.getMainLooper();
                    int i = Build.VERSION.SDK_INT;
                    this.f2Pn = Handler.createAsync(mainLooper);
                }
            }
        }
        this.f2Pn.post(runnable);
    }

    /* renamed from: oc */
    public boolean mo5oc() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }
}
