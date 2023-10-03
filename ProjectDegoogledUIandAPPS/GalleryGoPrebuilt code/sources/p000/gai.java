package p000;

import java.io.Closeable;

/* renamed from: gai */
/* compiled from: PG */
public final /* synthetic */ class gai implements Closeable {

    /* renamed from: a */
    private final gay f10789a;

    public gai(gay gay) {
        this.f10789a = gay;
    }

    public final void close() {
        boolean z;
        gay gay = this.f10789a;
        synchronized (gay.f10811k) {
            int i = gay.f10816p;
            if (i > 0) {
                z = true;
            } else {
                z = false;
            }
            ife.m12877b(z, "Refcount went negative!", i);
            gay.f10816p--;
            gay.mo6378a();
        }
    }
}
