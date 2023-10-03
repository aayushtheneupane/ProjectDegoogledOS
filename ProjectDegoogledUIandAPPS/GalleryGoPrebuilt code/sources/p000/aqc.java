package p000;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

/* renamed from: aqc */
/* compiled from: PG */
public final class aqc {

    /* renamed from: a */
    public final byte[] f1426a = new byte[256];

    /* renamed from: b */
    public ByteBuffer f1427b;

    /* renamed from: c */
    public aqb f1428c;

    /* renamed from: d */
    public int f1429d = 0;

    /* renamed from: e */
    public final boolean mo1483e() {
        return this.f1428c.f1414b != 0;
    }

    /* renamed from: c */
    public final int mo1481c() {
        try {
            return this.f1427b.get() & 255;
        } catch (Exception e) {
            this.f1428c.f1414b = 1;
            return 0;
        }
    }

    /* renamed from: b */
    public final void mo1480b() {
        int c = mo1481c();
        this.f1429d = c;
        if (c > 0) {
            int i = 0;
            while (true) {
                try {
                    int i2 = this.f1429d;
                    if (i < i2) {
                        int i3 = i2 - i;
                        this.f1427b.get(this.f1426a, i, i3);
                        i += i3;
                    } else {
                        return;
                    }
                } catch (Exception e) {
                    this.f1428c.f1414b = 1;
                    return;
                }
            }
        }
    }

    /* renamed from: a */
    public final int[] mo1479a(int i) {
        int[] iArr;
        byte[] bArr = new byte[(i * 3)];
        try {
            this.f1427b.get(bArr);
            iArr = new int[256];
            int i2 = 0;
            int i3 = 0;
            while (i2 < i) {
                int i4 = i3 + 1;
                try {
                    int i5 = i4 + 1;
                    int i6 = i5 + 1;
                    int i7 = i2 + 1;
                    iArr[i2] = ((bArr[i3] & 255) << 16) | -16777216 | ((bArr[i4] & 255) << 8) | (bArr[i5] & 255);
                    i3 = i6;
                    i2 = i7;
                } catch (BufferUnderflowException e) {
                    this.f1428c.f1414b = 1;
                    return iArr;
                }
            }
        } catch (BufferUnderflowException e2) {
            iArr = null;
            this.f1428c.f1414b = 1;
            return iArr;
        }
        return iArr;
    }

    /* renamed from: d */
    public final int mo1482d() {
        return this.f1427b.getShort();
    }

    /* renamed from: a */
    public final void mo1478a() {
        int c;
        do {
            c = mo1481c();
            this.f1427b.position(Math.min(this.f1427b.position() + c, this.f1427b.limit()));
        } while (c > 0);
    }
}
