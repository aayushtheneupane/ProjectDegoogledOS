package p000;

import java.io.InputStream;

/* renamed from: ihc */
/* compiled from: PG */
public abstract class ihc implements ikn {

    /* renamed from: a */
    private static final iij f14174a = iij.m13501a();

    /* renamed from: a */
    public ikf mo8517a(byte[] bArr, int i, int i2, iij iij) {
        throw null;
    }

    /* renamed from: a */
    private static final ikf m13001a(ikf ikf) {
        ill ill;
        if (ikf == null || ikf.mo8757c()) {
            return ikf;
        }
        if (ikf instanceof iha) {
            iha iha = (iha) ikf;
            ill = iha.m12994e();
        } else if (ikf instanceof ihb) {
            ihb ihb = (ihb) ikf;
            throw null;
        } else {
            ill = new ill();
        }
        throw ill.mo8942a();
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo8518a(InputStream inputStream, iij iij) {
        ihz a = ihz.m13261a(inputStream);
        ikf ikf = (ikf) mo8782a(a, iij);
        try {
            a.mo8629a(0);
            return m13001a(ikf);
        } catch (ijh e) {
            throw e;
        }
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo8519a(byte[] bArr) {
        return m13002b(bArr, 0, bArr.length, f14174a);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo8520a(byte[] bArr, int i) {
        return m13002b(bArr, 1, i, f14174a);
    }

    /* renamed from: b */
    private final ikf m13002b(byte[] bArr, int i, int i2, iij iij) {
        return m13001a(mo8517a(bArr, i, i2, iij));
    }
}
