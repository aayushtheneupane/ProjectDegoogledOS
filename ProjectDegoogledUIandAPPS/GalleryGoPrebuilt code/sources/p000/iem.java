package p000;

/* renamed from: iem */
/* compiled from: PG */
final class iem implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ Runnable f13958a;

    /* renamed from: b */
    private final /* synthetic */ ien f13959b;

    public iem(ien ien, Runnable runnable) {
        this.f13959b = ien;
        this.f13958a = runnable;
    }

    public final void run() {
        this.f13959b.f13960a = false;
        this.f13958a.run();
    }
}
