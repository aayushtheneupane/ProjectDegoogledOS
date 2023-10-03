package p000;

import androidx.work.Worker;

/* renamed from: aht */
/* compiled from: PG */
public final class aht implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ Worker f502a;

    public aht(Worker worker) {
        this.f502a = worker;
    }

    public final void run() {
        try {
            this.f502a.f1164d.mo659b((Object) this.f502a.mo1225g());
        } catch (Throwable th) {
            this.f502a.f1164d.mo657a(th);
        }
    }
}
