package p000;

/* renamed from: aqf */
/* compiled from: PG */
final class aqf implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ bdz f1450a;

    public aqf(bdz bdz) {
        this.f1450a = bdz;
    }

    public final void run() {
        this.f1450a.cancel(true);
    }
}
