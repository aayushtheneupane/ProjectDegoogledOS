package p000;

import java.io.InputStream;

/* renamed from: fyo */
/* compiled from: PG */
public final class fyo implements fxq {

    /* renamed from: a */
    public iij f10705a = iij.m13501a();

    /* renamed from: b */
    private final ikn f10706b;

    public fyo(ikn ikn) {
        this.f10706b = ikn;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo6315a(fxp fxp) {
        InputStream b = fyp.m9885b(fxp);
        try {
            ikf ikf = (ikf) this.f10706b.mo8518a(b, this.f10705a);
            if (b != null) {
                b.close();
            }
            return ikf;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }
}
