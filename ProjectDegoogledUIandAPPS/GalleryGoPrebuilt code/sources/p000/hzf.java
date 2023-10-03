package p000;

import java.nio.charset.Charset;

/* renamed from: hzf */
/* compiled from: PG */
abstract class hzf implements hzk {
    /* renamed from: a */
    public hzk mo8284a(byte[] bArr, int i) {
        throw null;
    }

    /* renamed from: a */
    public hzk mo8283a(byte[] bArr) {
        return mo8284a(bArr, bArr.length);
    }

    /* renamed from: a */
    public hzk mo8289a(CharSequence charSequence, Charset charset) {
        return mo8283a(charSequence.toString().getBytes(charset));
    }
}
