package p000;

import java.io.OutputStream;
import java.util.List;

/* renamed from: fxy */
/* compiled from: PG */
public final class fxy implements fxl {

    /* renamed from: a */
    public OutputStream f10693a;

    /* renamed from: b */
    public fyd f10694b;

    /* renamed from: a */
    public final void mo6305a() {
    }

    /* renamed from: a */
    public final void mo6306a(List list) {
        OutputStream outputStream = (OutputStream) ife.m12907g((Iterable) list);
        if (outputStream instanceof fyd) {
            this.f10694b = (fyd) outputStream;
            this.f10693a = (OutputStream) list.iterator().next();
        }
    }
}
