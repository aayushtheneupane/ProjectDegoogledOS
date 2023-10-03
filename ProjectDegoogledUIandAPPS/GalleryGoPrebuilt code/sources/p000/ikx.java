package p000;

import java.io.InputStream;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Iterator;

/* renamed from: ikx */
/* compiled from: PG */
final class ikx extends ihw {

    /* renamed from: a */
    public static final int[] f14420a = {1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811, 514229, 832040, 1346269, 2178309, 3524578, 5702887, 9227465, 14930352, 24157817, 39088169, 63245986, 102334155, 165580141, 267914296, 433494437, 701408733, 1134903170, 1836311903, Integer.MAX_VALUE};
    public static final long serialVersionUID = 1;

    /* renamed from: d */
    public final int f14421d;

    /* renamed from: e */
    public final ihw f14422e;

    /* renamed from: f */
    public final ihw f14423f;

    /* renamed from: g */
    public final int f14424g;

    /* renamed from: h */
    private final int f14425h;

    /* renamed from: a */
    public final int mo8597a() {
        return this.f14421d;
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public final int mo8604c() {
        return this.f14424g;
    }

    private ikx(ihw ihw, ihw ihw2) {
        this.f14422e = ihw;
        this.f14423f = ihw2;
        int a = ihw.mo8597a();
        this.f14425h = a;
        this.f14421d = a + ihw2.mo8597a();
        this.f14424g = Math.max(ihw.mo8604c(), ihw2.mo8604c()) + 1;
    }

    public /* synthetic */ ikx(ihw ihw, ihw ihw2, byte[] bArr) {
        this(ihw, ihw2);
    }

    /* renamed from: a */
    public final byte mo8596a(int i) {
        m13166b(i, this.f14421d);
        return mo8599b(i);
    }

    /* renamed from: a */
    static ihw m13877a(ihw ihw, ihw ihw2) {
        if (ihw2.mo8597a() == 0) {
            return ihw;
        }
        if (ihw.mo8597a() == 0) {
            return ihw2;
        }
        int a = ihw.mo8597a() + ihw2.mo8597a();
        if (a < 128) {
            return m13878b(ihw, ihw2);
        }
        if (ihw instanceof ikx) {
            ikx ikx = (ikx) ihw;
            if (ikx.f14423f.mo8597a() + ihw2.mo8597a() < 128) {
                return new ikx(ikx.f14422e, m13878b(ikx.f14423f, ihw2));
            } else if (ikx.f14422e.mo8604c() > ikx.f14423f.mo8604c() && ikx.f14424g > ihw2.mo8604c()) {
                return new ikx(ikx.f14422e, new ikx(ikx.f14423f, ihw2));
            }
        }
        if (a >= m13879d(Math.max(ihw.mo8604c(), ihw2.mo8604c()) + 1)) {
            return new ikx(ihw, ihw2);
        }
        iku iku = new iku((byte[]) null);
        iku.mo8880a(ihw);
        iku.mo8880a(ihw2);
        ihw ihw3 = (ihw) iku.f14410a.pop();
        while (!iku.f14410a.isEmpty()) {
            ihw3 = new ikx((ihw) iku.f14410a.pop(), ihw3, (byte[]) null);
        }
        return ihw3;
    }

    /* renamed from: b */
    private static ihw m13878b(ihw ihw, ihw ihw2) {
        int a = ihw.mo8597a();
        int a2 = ihw2.mo8597a();
        byte[] bArr = new byte[(a + a2)];
        ihw.mo8620b(bArr, 0, 0, a);
        ihw2.mo8620b(bArr, 0, a, a2);
        return ihw.m13164b(bArr);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo8598a(byte[] bArr, int i, int i2, int i3) {
        int i4 = this.f14425h;
        if (i + i3 <= i4) {
            this.f14422e.mo8598a(bArr, i, i2, i3);
        } else if (i < i4) {
            int i5 = i4 - i;
            this.f14422e.mo8598a(bArr, i, i2, i5);
            this.f14423f.mo8598a(bArr, 0, i2 + i5, i3 - i5);
        } else {
            this.f14423f.mo8598a(bArr, i - i4, i2, i3);
        }
    }

    public final boolean equals(Object obj) {
        boolean z;
        if (obj == this) {
            return true;
        }
        if (obj instanceof ihw) {
            ihw ihw = (ihw) obj;
            if (this.f14421d == ihw.mo8597a()) {
                if (this.f14421d == 0) {
                    return true;
                }
                int i = this.f14204c;
                int i2 = ihw.f14204c;
                if (i != 0 && i2 != 0 && i != i2) {
                    return false;
                }
                ikv ikv = new ikv(this);
                ihs a = ikv.next();
                ikv ikv2 = new ikv(ihw);
                ihs a2 = ikv2.next();
                int i3 = 0;
                int i4 = 0;
                int i5 = 0;
                while (true) {
                    int a3 = a.mo8597a() - i3;
                    int a4 = a2.mo8597a() - i4;
                    int min = Math.min(a3, a4);
                    if (i3 != 0) {
                        z = a2.mo8603a(a, i3, min);
                    } else {
                        z = a.mo8603a(a2, i4, min);
                    }
                    if (!z) {
                        return false;
                    }
                    i5 += min;
                    int i6 = this.f14421d;
                    if (i5 < i6) {
                        if (min != a3) {
                            i3 += min;
                        } else {
                            a = ikv.next();
                            i3 = 0;
                        }
                        if (min != a4) {
                            i4 += min;
                        } else {
                            a2 = ikv2.next();
                            i4 = 0;
                        }
                    } else if (i5 == i6) {
                        return true;
                    } else {
                        throw new IllegalStateException();
                    }
                }
            }
        }
        return false;
    }

    /* renamed from: b */
    public final byte mo8599b(int i) {
        int i2 = this.f14425h;
        if (i >= i2) {
            return this.f14423f.mo8599b(i - i2);
        }
        return this.f14422e.mo8599b(i);
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public final boolean mo8605d() {
        return this.f14421d >= m13879d(this.f14424g);
    }

    /* renamed from: e */
    public final boolean mo8612e() {
        int a = this.f14422e.mo8606a(0, 0, this.f14425h);
        ihw ihw = this.f14423f;
        if (ihw.mo8606a(a, 0, ihw.mo8597a()) == 0) {
            return true;
        }
        return false;
    }

    /* renamed from: h */
    public final ihq mo8621h() {
        return new ikt(this);
    }

    public final /* bridge */ /* synthetic */ Iterator iterator() {
        return iterator();
    }

    /* renamed from: d */
    static int m13879d(int i) {
        int[] iArr = f14420a;
        if (i < iArr.length) {
            return iArr[i];
        }
        return Integer.MAX_VALUE;
    }

    /* renamed from: g */
    public final ihz mo8615g() {
        return ihz.m13261a((InputStream) new ikw(this));
    }

    /* renamed from: f */
    public final InputStream mo8614f() {
        return new ikw(this);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final int mo8611b(int i, int i2, int i3) {
        int i4 = this.f14425h;
        if (i2 + i3 <= i4) {
            return this.f14422e.mo8611b(i, i2, i3);
        }
        if (i2 >= i4) {
            return this.f14423f.mo8611b(i, i2 - i4, i3);
        }
        int i5 = i4 - i2;
        return this.f14423f.mo8611b(this.f14422e.mo8611b(i, i2, i5), 0, i3 - i5);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final int mo8606a(int i, int i2, int i3) {
        int i4 = this.f14425h;
        if (i2 + i3 <= i4) {
            return this.f14422e.mo8606a(i, i2, i3);
        }
        if (i2 >= i4) {
            return this.f14423f.mo8606a(i, i2 - i4, i3);
        }
        int i5 = i4 - i2;
        return this.f14423f.mo8606a(this.f14422e.mo8606a(i, i2, i5), 0, i3 - i5);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("RopeByteStream instances are not to be serialized directly");
    }

    /* renamed from: a */
    public final ihw mo8607a(int i, int i2) {
        int c = m13167c(i, i2, this.f14421d);
        if (c == 0) {
            return ihw.f14203b;
        }
        if (c == this.f14421d) {
            return this;
        }
        int i3 = this.f14425h;
        if (i2 <= i3) {
            return this.f14422e.mo8607a(i, i2);
        }
        if (i >= i3) {
            return this.f14423f.mo8607a(i - i3, i2 - i3);
        }
        ihw ihw = this.f14422e;
        return new ikx(ihw.mo8607a(i, ihw.mo8597a()), this.f14423f.mo8607a(0, i2 - this.f14425h));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final String mo8608a(Charset charset) {
        return new String(mo8625j(), charset);
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return ihw.m13164b(mo8625j());
    }

    /* renamed from: a */
    public final void mo8609a(ihk ihk) {
        this.f14422e.mo8609a(ihk);
        this.f14423f.mo8609a(ihk);
    }

    /* renamed from: a */
    public final void mo8610a(OutputStream outputStream) {
        this.f14422e.mo8610a(outputStream);
        this.f14423f.mo8610a(outputStream);
    }
}
