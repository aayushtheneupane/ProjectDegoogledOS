package p000;

import java.io.IOException;
import java.io.OutputStream;

/* renamed from: iha */
/* compiled from: PG */
public abstract class iha implements ikf {

    /* renamed from: y */
    public int f14173y = 0;

    /* renamed from: a */
    public void mo8510a(int i) {
        throw null;
    }

    /* renamed from: d */
    public int mo8515d() {
        throw null;
    }

    /* renamed from: e */
    static final ill m12994e() {
        return new ill();
    }

    /* renamed from: ag */
    public final byte[] mo8512ag() {
        try {
            byte[] bArr = new byte[mo8795i()];
            iie a = iie.m13406a(bArr);
            mo8789a(a);
            a.mo8681c();
            return bArr;
        } catch (IOException e) {
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 72);
            sb.append("Serializing ");
            sb.append(name);
            sb.append(" to a byte array threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e);
        }
    }

    /* renamed from: b */
    public final ihw mo8513b() {
        try {
            ihr c = ihw.m13168c(mo8795i());
            mo8789a(c.f14194a);
            return c.mo8602a();
        } catch (IOException e) {
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 72);
            sb.append("Serializing ");
            sb.append(name);
            sb.append(" to a ByteString threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e);
        }
    }

    /* renamed from: b */
    public final void mo8514b(OutputStream outputStream) {
        int i = mo8795i();
        iie a = iie.m13405a(outputStream, iie.m13414d(iie.m13426h(i) + i));
        a.mo8668b(i);
        mo8789a(a);
        a.mo8667b();
    }

    /* renamed from: a */
    public final void mo8511a(OutputStream outputStream) {
        iie a = iie.m13405a(outputStream, iie.m13414d(mo8795i()));
        mo8789a(a);
        a.mo8667b();
    }
}
