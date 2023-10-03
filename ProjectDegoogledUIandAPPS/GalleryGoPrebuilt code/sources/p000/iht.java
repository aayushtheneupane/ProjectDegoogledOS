package p000;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

/* renamed from: iht */
/* compiled from: PG */
class iht extends ihs {
    public static final long serialVersionUID = 1;

    /* renamed from: a */
    public final byte[] f14196a;

    public iht(byte[] bArr) {
        if (bArr != null) {
            this.f14196a = bArr;
            return;
        }
        throw null;
    }

    /* renamed from: a */
    public int mo8597a() {
        return this.f14196a.length;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public int mo8600b() {
        return 0;
    }

    /* renamed from: a */
    public byte mo8596a(int i) {
        return this.f14196a[i];
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo8598a(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.f14196a, i, bArr, i2, i3);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ihw) || mo8597a() != ((ihw) obj).mo8597a()) {
            return false;
        }
        if (mo8597a() == 0) {
            return true;
        }
        if (!(obj instanceof iht)) {
            return obj.equals(this);
        }
        iht iht = (iht) obj;
        int i = this.f14204c;
        int i2 = iht.f14204c;
        if (i == 0 || i2 == 0 || i == i2) {
            return mo8603a((ihw) iht, 0, mo8597a());
        }
        return false;
    }

    /* renamed from: a */
    public final boolean mo8603a(ihw ihw, int i, int i2) {
        if (i2 <= ihw.mo8597a()) {
            int i3 = i + i2;
            if (i3 > ihw.mo8597a()) {
                int a = ihw.mo8597a();
                StringBuilder sb = new StringBuilder(59);
                sb.append("Ran off end of other: ");
                sb.append(i);
                sb.append(", ");
                sb.append(i2);
                sb.append(", ");
                sb.append(a);
                throw new IllegalArgumentException(sb.toString());
            } else if (!(ihw instanceof iht)) {
                return ihw.mo8607a(i, i3).equals(mo8607a(0, i2));
            } else {
                iht iht = (iht) ihw;
                byte[] bArr = this.f14196a;
                byte[] bArr2 = iht.f14196a;
                int b = mo8600b() + i2;
                int b2 = mo8600b();
                int b3 = iht.mo8600b() + i;
                while (b2 < b) {
                    if (bArr[b2] != bArr2[b3]) {
                        return false;
                    }
                    b2++;
                    b3++;
                }
                return true;
            }
        } else {
            int a2 = mo8597a();
            StringBuilder sb2 = new StringBuilder(40);
            sb2.append("Length too large: ");
            sb2.append(i2);
            sb2.append(a2);
            throw new IllegalArgumentException(sb2.toString());
        }
    }

    /* renamed from: b */
    public byte mo8599b(int i) {
        return this.f14196a[i];
    }

    /* renamed from: e */
    public final boolean mo8612e() {
        int b = mo8600b();
        return ima.m14069a(this.f14196a, b, mo8597a() + b);
    }

    /* renamed from: g */
    public final ihz mo8615g() {
        return ihz.m13263a(this.f14196a, mo8600b(), mo8597a());
    }

    /* renamed from: f */
    public final InputStream mo8614f() {
        return new ByteArrayInputStream(this.f14196a, mo8600b(), mo8597a());
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final int mo8611b(int i, int i2, int i3) {
        return ijf.m13644a(i, this.f14196a, mo8600b() + i2, i3);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final int mo8606a(int i, int i2, int i3) {
        int b = mo8600b() + i2;
        return ima.f14474a.mo8988a(i, this.f14196a, b, i3 + b);
    }

    /* renamed from: a */
    public final ihw mo8607a(int i, int i2) {
        int c = m13167c(i, i2, mo8597a());
        return c != 0 ? new iho(this.f14196a, mo8600b() + i, c) : ihw.f14203b;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final String mo8608a(Charset charset) {
        return new String(this.f14196a, mo8600b(), mo8597a(), charset);
    }

    /* renamed from: a */
    public final void mo8609a(ihk ihk) {
        ihk.mo8590a(this.f14196a, mo8600b(), mo8597a());
    }

    /* renamed from: a */
    public final void mo8610a(OutputStream outputStream) {
        outputStream.write(mo8625j());
    }
}
