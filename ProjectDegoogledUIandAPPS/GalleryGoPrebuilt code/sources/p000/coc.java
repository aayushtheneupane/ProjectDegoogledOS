package p000;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: coc */
/* compiled from: PG */
final class coc implements ari {

    /* renamed from: a */
    private final cob f4771a;

    /* renamed from: b */
    private InputStream f4772b;

    public coc(cob cob) {
        this.f4771a = cob;
    }

    /* renamed from: a */
    public final Class mo1510a() {
        return InputStream.class;
    }

    /* renamed from: c */
    public final void mo1517c() {
    }

    /* renamed from: d */
    public final int mo1518d() {
        return 1;
    }

    /* renamed from: b */
    public final void mo1516b() {
        InputStream inputStream = this.f4772b;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
            }
        }
    }

    /* renamed from: a */
    public final void mo1514a(apb apb, arh arh) {
        InputStream inputStream;
        cob cob = this.f4771a;
        byte[] bArr = cob.f4768a;
        if (bArr != null) {
            inputStream = new ByteArrayInputStream(bArr);
        } else {
            ihw ihw = cob.f4769b;
            if (ihw != null) {
                inputStream = ihw.mo8614f();
            } else {
                throw new IllegalStateException("no bytes");
            }
        }
        this.f4772b = inputStream;
        arh.mo1525a((Object) inputStream);
    }
}
