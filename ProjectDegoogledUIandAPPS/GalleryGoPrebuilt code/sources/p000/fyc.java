package p000;

import java.io.Closeable;

/* renamed from: fyc */
/* compiled from: PG */
public final class fyc implements Closeable {

    /* renamed from: a */
    public final Closeable f10696a;

    public fyc(Closeable closeable) {
        this.f10696a = closeable;
    }

    public final void close() {
        Closeable closeable = this.f10696a;
        if (closeable != null) {
            closeable.close();
        }
    }
}
