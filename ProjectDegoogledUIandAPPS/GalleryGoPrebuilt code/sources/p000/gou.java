package p000;

import java.io.Closeable;
import java.util.concurrent.Callable;

/* renamed from: gou */
/* compiled from: PG */
final /* synthetic */ class gou implements icq {

    /* renamed from: a */
    private final Callable f11772a;

    public gou(Callable callable) {
        this.f11772a = callable;
    }

    /* renamed from: a */
    public final Object mo6374a(icw icw) {
        Closeable closeable = (Closeable) this.f11772a.call();
        icw.mo8382b(closeable, idh.f13918a);
        return closeable;
    }
}
