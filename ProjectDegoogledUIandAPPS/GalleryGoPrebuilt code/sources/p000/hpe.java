package p000;

/* renamed from: hpe */
/* compiled from: PG */
final /* synthetic */ class hpe implements Runnable {

    /* renamed from: a */
    private final hpf f13210a;

    public hpe(hpf hpf) {
        this.f13210a = hpf;
    }

    public final void run() {
        this.f13210a.f13211a.cancel(false);
    }
}
