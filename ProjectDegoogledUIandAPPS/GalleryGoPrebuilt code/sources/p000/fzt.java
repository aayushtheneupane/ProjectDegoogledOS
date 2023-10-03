package p000;

import java.util.concurrent.Callable;

/* renamed from: fzt */
/* compiled from: PG */
final /* synthetic */ class fzt implements Callable {

    /* renamed from: a */
    private final fzu f10754a;

    public fzt(fzu fzu) {
        this.f10754a = fzu;
    }

    public final Object call() {
        fzu fzu = this.f10754a;
        synchronized (fzu.f10756b.f10764d) {
            fzu.f10755a = null;
        }
        return null;
    }
}
