package p000;

import java.io.Closeable;

/* renamed from: cnt */
/* compiled from: PG */
final /* synthetic */ class cnt implements Closeable {

    /* renamed from: a */
    private final hdt f4742a;

    /* renamed from: b */
    private final bdz f4743b;

    public cnt(hdt hdt, bdz bdz) {
        this.f4742a = hdt;
        this.f4743b = bdz;
    }

    public final void close() {
        this.f4742a.mo7312a((ber) this.f4743b);
    }
}
