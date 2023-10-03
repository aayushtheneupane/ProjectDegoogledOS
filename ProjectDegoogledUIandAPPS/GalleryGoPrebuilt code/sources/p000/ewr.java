package p000;

/* renamed from: ewr */
/* compiled from: PG */
final class ewr implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ ews f9143a;

    public ewr(ews ews) {
        this.f9143a = ews;
    }

    public final void run() {
        synchronized (this.f9143a.f9144a) {
            ewt ewt = this.f9143a.f9145b;
            ((emf) ewt).f8551b.f8553b.remove(((emf) ewt).f8550a);
        }
    }
}
