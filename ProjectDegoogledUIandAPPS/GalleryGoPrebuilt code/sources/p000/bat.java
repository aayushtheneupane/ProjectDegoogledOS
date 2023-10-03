package p000;

import android.graphics.Bitmap;
import java.io.IOException;

/* renamed from: bat */
/* compiled from: PG */
final class bat implements bab {

    /* renamed from: a */
    private final bap f1975a;

    /* renamed from: b */
    private final bfg f1976b;

    public bat(bap bap, bfg bfg) {
        this.f1975a = bap;
        this.f1976b = bfg;
    }

    /* renamed from: a */
    public final void mo1752a(auk auk, Bitmap bitmap) {
        IOException iOException = this.f1976b.f2206c;
        if (iOException != null) {
            if (bitmap != null) {
                auk.mo1645a(bitmap);
            }
            throw iOException;
        }
    }

    /* renamed from: a */
    public final void mo1751a() {
        this.f1975a.mo1760a();
    }
}
