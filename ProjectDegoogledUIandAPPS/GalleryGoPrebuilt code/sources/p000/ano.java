package p000;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/* renamed from: ano */
/* compiled from: PG */
public final class ano {

    /* renamed from: a */
    public byte[] f1199a;

    /* renamed from: b */
    public int f1200b;

    /* renamed from: c */
    private String f1201c = null;

    public ano(int i) {
        this.f1199a = new byte[i];
        this.f1200b = 0;
    }

    public ano(byte[] bArr) {
        this.f1199a = bArr;
        this.f1200b = bArr.length;
    }

    /* renamed from: a */
    public final void mo1276a(byte[] bArr) {
        mo1277a(bArr, bArr.length);
    }

    /* renamed from: a */
    public final void mo1277a(byte[] bArr, int i) {
        mo1275a(this.f1200b + i);
        System.arraycopy(bArr, 0, this.f1199a, this.f1200b, i);
        this.f1200b += i;
    }

    /* renamed from: a */
    public final void mo1275a(int i) {
        byte[] bArr = this.f1199a;
        int length = bArr.length;
        if (i > length) {
            byte[] bArr2 = new byte[(length + length)];
            this.f1199a = bArr2;
            System.arraycopy(bArr, 0, bArr2, 0, length);
        }
    }

    /* renamed from: a */
    public final InputStream mo1274a() {
        return new ByteArrayInputStream(this.f1199a, 0, this.f1200b);
    }

    /* renamed from: b */
    public final String mo1278b() {
        if (this.f1201c == null) {
            int i = this.f1200b;
            if (i >= 2) {
                byte[] bArr = this.f1199a;
                byte b = bArr[0];
                if (b != 0) {
                    byte b2 = b & 255;
                    if (b2 >= 128) {
                        if (b2 == 239) {
                            this.f1201c = "UTF-8";
                        } else if (b2 == 254) {
                            this.f1201c = "UTF-16";
                        } else if (i >= 4 && bArr[2] == 0) {
                            this.f1201c = "UTF-32";
                        } else {
                            this.f1201c = "UTF-16";
                        }
                    } else if (bArr[1] != 0) {
                        this.f1201c = "UTF-8";
                    } else if (i >= 4 && bArr[2] == 0) {
                        this.f1201c = "UTF-32LE";
                    } else {
                        this.f1201c = "UTF-16LE";
                    }
                } else if (i < 4 || bArr[1] != 0) {
                    this.f1201c = "UTF-16BE";
                } else if ((bArr[2] & 255) == 254 && (bArr[3] & 255) == 255) {
                    this.f1201c = "UTF-32BE";
                } else {
                    this.f1201c = "UTF-32";
                }
            } else {
                this.f1201c = "UTF-8";
            }
        }
        return this.f1201c;
    }
}
