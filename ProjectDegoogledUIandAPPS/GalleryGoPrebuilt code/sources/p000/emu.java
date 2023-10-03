package p000;

import java.util.concurrent.locks.Lock;

/* renamed from: emu */
/* compiled from: PG */
abstract class emu implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ emv f8573a;

    public /* synthetic */ emu(emv emv) {
        this.f8573a = emv;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo5019a();

    public final void run() {
        Lock lock;
        this.f8573a.f8575b.lock();
        try {
            if (Thread.interrupted()) {
                lock = this.f8573a.f8575b;
            } else {
                mo5019a();
                lock = this.f8573a.f8575b;
            }
        } catch (RuntimeException e) {
            ene ene = this.f8573a.f8574a;
            ene.f8632e.sendMessage(ene.f8632e.obtainMessage(2, e));
            lock = this.f8573a.f8575b;
        } catch (Throwable th) {
            this.f8573a.f8575b.unlock();
            throw th;
        }
        lock.unlock();
    }
}
