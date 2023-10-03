package p000;

/* renamed from: on */
/* compiled from: PG */
final class C0398on implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ C0416pe f15418a;

    public C0398on(C0416pe peVar) {
        this.f15418a = peVar;
    }

    public final void run() {
        C0416pe peVar = this.f15418a;
        if ((peVar.f15503v & 1) != 0) {
            peVar.mo9609f(0);
        }
        C0416pe peVar2 = this.f15418a;
        if ((peVar2.f15503v & 4096) != 0) {
            peVar2.mo9609f(108);
        }
        C0416pe peVar3 = this.f15418a;
        peVar3.f15502u = false;
        peVar3.f15503v = 0;
    }
}
