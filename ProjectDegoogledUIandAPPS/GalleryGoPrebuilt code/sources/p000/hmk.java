package p000;

/* renamed from: hmk */
/* compiled from: PG */
final class hmk implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ hlp f13035a;

    /* renamed from: b */
    private final /* synthetic */ Runnable f13036b;

    public hmk(hlp hlp, Runnable runnable) {
        this.f13035a = hlp;
        this.f13036b = runnable;
    }

    public final void run() {
        hlp b = hnb.m11776b(this.f13035a);
        try {
            this.f13036b.run();
        } finally {
            hnb.m11776b(b);
        }
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f13036b);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 14);
        sb.append("propagating=[");
        sb.append(valueOf);
        sb.append("]");
        return sb.toString();
    }
}
