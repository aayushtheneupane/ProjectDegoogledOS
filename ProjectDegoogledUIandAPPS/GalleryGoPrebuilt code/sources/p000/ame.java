package p000;

/* renamed from: ame */
/* compiled from: PG */
final class ame implements Runnable {

    /* renamed from: a */
    private final amf f756a;

    /* renamed from: b */
    private final Runnable f757b;

    public ame(amf amf, Runnable runnable) {
        this.f756a = amf;
        this.f757b = runnable;
    }

    public final void run() {
        try {
            this.f757b.run();
        } finally {
            this.f756a.mo642a();
        }
    }
}
