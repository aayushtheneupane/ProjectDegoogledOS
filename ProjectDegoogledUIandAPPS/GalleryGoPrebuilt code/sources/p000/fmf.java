package p000;

import android.os.SystemClock;

/* renamed from: fmf */
/* compiled from: PG */
final /* synthetic */ class fmf implements Runnable {

    /* renamed from: a */
    private final fmg f10023a;

    public fmf(fmg fmg) {
        this.f10023a = fmg;
    }

    public final void run() {
        fmg fmg = this.f10023a;
        if (fmg.f10024a.f10027b.f10040f == 0) {
            fmg.f10024a.f10027b.f10040f = SystemClock.elapsedRealtime();
            fmg.f10024a.f10027b.f10042h.f10031d = true;
        }
    }
}
