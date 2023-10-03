package p000;

import java.nio.charset.Charset;

/* renamed from: hze */
/* compiled from: PG */
abstract class hze implements hzj {
    /* renamed from: a */
    public final hzi mo8287a(byte[] bArr) {
        return mo8288a(bArr, bArr.length);
    }

    /* renamed from: a */
    public hzi mo8288a(byte[] bArr, int i) {
        ife.m12874b(0, i, bArr.length);
        ife.m12846a(true, "expectedInputSize must be >= 0 but was %s", i);
        return mo8299a().mo8284a(bArr, i).mo8300a();
    }

    /* renamed from: a */
    public hzi mo8286a(CharSequence charSequence, Charset charset) {
        return mo8299a().mo8289a(charSequence, charset).mo8300a();
    }
}
