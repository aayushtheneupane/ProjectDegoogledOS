package p000;

/* renamed from: icl */
/* compiled from: PG */
final class icl implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ idb f13883a;

    public icl(idb idb) {
        this.f13883a = idb;
    }

    public final void run() {
        this.f13883a.mo8400a(icy.WILL_CLOSE, icy.CLOSING);
        this.f13883a.mo8403c();
        this.f13883a.mo8400a(icy.CLOSING, icy.CLOSED);
    }
}
