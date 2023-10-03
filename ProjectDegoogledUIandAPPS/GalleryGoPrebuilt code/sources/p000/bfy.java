package p000;

/* renamed from: bfy */
/* compiled from: PG */
public final class bfy {

    /* renamed from: a */
    public volatile boolean f2225a;

    private bfy(byte[] bArr) {
    }

    /* renamed from: b */
    public final void mo1973b() {
        if (this.f2225a) {
            throw new IllegalStateException("Already released");
        }
    }

    private bfy() {
    }

    /* renamed from: a */
    public static bfy m2451a() {
        return new bfy((byte[]) null);
    }
}
