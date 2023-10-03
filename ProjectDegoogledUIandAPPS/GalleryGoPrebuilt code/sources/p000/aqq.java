package p000;

import java.io.InputStream;

/* renamed from: aqq */
/* compiled from: PG */
public final class aqq implements aqs {

    /* renamed from: a */
    private final /* synthetic */ InputStream f1462a;

    /* renamed from: b */
    private final /* synthetic */ aui f1463b;

    public aqq(InputStream inputStream, aui aui) {
        this.f1462a = inputStream;
        this.f1463b = aui;
    }

    /* renamed from: a */
    public final int mo1493a(aqm aqm) {
        try {
            return aqm.mo1489a(this.f1462a, this.f1463b);
        } finally {
            this.f1462a.reset();
        }
    }
}
