package com.android.p032ex.photo.util;

import android.os.Build;
import android.os.Trace;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/* renamed from: com.android.ex.photo.util.f */
public class C0746f {

    /* renamed from: Ew */
    private byte[] f915Ew;

    /* renamed from: Fw */
    private boolean f916Fw;

    /* renamed from: Gw */
    private int f917Gw = 0;
    private InputStream mInputStream;
    private int mOffset = 0;

    public C0746f(InputStream inputStream, int i, boolean z) {
        this.mInputStream = inputStream;
        if (i > 0) {
            this.f915Ew = new byte[m1195Bb(i)];
            this.f916Fw = z;
            return;
        }
        throw new IllegalArgumentException(String.format("Buffer size %d must be positive.", new Object[]{Integer.valueOf(i)}));
    }

    /* renamed from: Bb */
    private static int m1195Bb(int i) {
        int i2 = i - 1;
        int i3 = i2 | (i2 >> 1);
        int i4 = i3 | (i3 >> 2);
        int i5 = i4 | (i4 >> 4);
        int i6 = i5 | (i5 >> 8);
        return (i6 | (i6 >> 16)) + 1;
    }

    /* renamed from: fa */
    public void mo5808fa(int i) {
        boolean z;
        int i2;
        int i3 = Build.VERSION.SDK_INT;
        Trace.beginSection("advance to");
        int i4 = i - this.mOffset;
        if (i4 <= 0) {
            int i5 = Build.VERSION.SDK_INT;
            Trace.endSection();
            return;
        }
        int i6 = this.f917Gw;
        if (i4 < i6) {
            if (i4 < this.f915Ew.length) {
                int i7 = 0;
                while (true) {
                    int i8 = i7 + i4;
                    i2 = this.f917Gw;
                    if (i8 >= i2) {
                        break;
                    }
                    byte[] bArr = this.f915Ew;
                    bArr[i7] = bArr[i8];
                    i7++;
                }
                this.mOffset = i;
                this.f917Gw = i2 - i4;
            } else {
                throw new IndexOutOfBoundsException(String.format("Index %d out of bounds. Length %d", new Object[]{Integer.valueOf(i4), Integer.valueOf(this.f915Ew.length)}));
            }
        } else if (this.mInputStream != null) {
            int i9 = i4 - i6;
            int i10 = 0;
            while (true) {
                if (i9 <= 0) {
                    z = false;
                    break;
                }
                try {
                    long j = (long) i9;
                    long skip = this.mInputStream.skip(j);
                    if (skip <= 0) {
                        i10++;
                    } else {
                        i9 = (int) (j - skip);
                    }
                    if (i10 >= 5) {
                        break;
                    }
                } catch (IOException unused) {
                }
            }
            z = true;
            if (z) {
                this.mInputStream = null;
            }
            this.mOffset = i - i9;
            this.f917Gw = 0;
        } else {
            this.mOffset = i;
            this.f917Gw = 0;
        }
        if (Log.isLoggable("InputStreamBuffer", 3)) {
            Log.d("InputStreamBuffer", String.format("advanceTo %d buffer: %s", new Object[]{Integer.valueOf(i4), this}));
        }
        int i11 = Build.VERSION.SDK_INT;
        Trace.endSection();
    }

    public byte get(int i) {
        int i2 = Build.VERSION.SDK_INT;
        Trace.beginSection("get");
        if (has(i)) {
            int i3 = i - this.mOffset;
            int i4 = Build.VERSION.SDK_INT;
            Trace.endSection();
            return this.f915Ew[i3];
        }
        int i5 = Build.VERSION.SDK_INT;
        Trace.endSection();
        throw new IndexOutOfBoundsException(String.format("Index %d beyond length.", new Object[]{Integer.valueOf(i)}));
    }

    public boolean has(int i) {
        int i2;
        int i3 = Build.VERSION.SDK_INT;
        Trace.beginSection("has");
        int i4 = this.mOffset;
        if (i >= i4) {
            int i5 = i - i4;
            if (i5 >= this.f917Gw || i5 >= this.f915Ew.length) {
                int i6 = Build.VERSION.SDK_INT;
                Trace.endSection();
                int i7 = Build.VERSION.SDK_INT;
                Trace.beginSection("fill");
                int i8 = this.mOffset;
                if (i >= i8) {
                    int i9 = i - i8;
                    if (this.mInputStream == null) {
                        int i10 = Build.VERSION.SDK_INT;
                        Trace.endSection();
                        return false;
                    }
                    int i11 = i9 + 1;
                    if (i11 > this.f915Ew.length) {
                        if (this.f916Fw) {
                            mo5808fa(i);
                            i9 = i - this.mOffset;
                        } else {
                            int Bb = m1195Bb(i11);
                            Log.w("InputStreamBuffer", String.format("Increasing buffer length from %d to %d. Bad buffer size chosen, or advanceTo() not called.", new Object[]{Integer.valueOf(this.f915Ew.length), Integer.valueOf(Bb)}));
                            this.f915Ew = Arrays.copyOf(this.f915Ew, Bb);
                        }
                    }
                    try {
                        i2 = this.mInputStream.read(this.f915Ew, this.f917Gw, this.f915Ew.length - this.f917Gw);
                    } catch (IOException unused) {
                        i2 = -1;
                    }
                    if (i2 != -1) {
                        this.f917Gw += i2;
                    } else {
                        this.mInputStream = null;
                    }
                    if (Log.isLoggable("InputStreamBuffer", 3)) {
                        Log.d("InputStreamBuffer", String.format("fill %d      buffer: %s", new Object[]{Integer.valueOf(i9), this}));
                    }
                    int i12 = Build.VERSION.SDK_INT;
                    Trace.endSection();
                    if (i9 < this.f917Gw) {
                        return true;
                    }
                    return false;
                }
                int i13 = Build.VERSION.SDK_INT;
                Trace.endSection();
                throw new IllegalStateException(String.format("Index %d is before buffer %d", new Object[]{Integer.valueOf(i), Integer.valueOf(this.mOffset)}));
            }
            int i14 = Build.VERSION.SDK_INT;
            Trace.endSection();
            return true;
        }
        int i15 = Build.VERSION.SDK_INT;
        Trace.endSection();
        throw new IllegalStateException(String.format("Index %d is before buffer %d", new Object[]{Integer.valueOf(i), Integer.valueOf(this.mOffset)}));
    }

    public String toString() {
        return String.format("+%d+%d [%d]", new Object[]{Integer.valueOf(this.mOffset), Integer.valueOf(this.f915Ew.length), Integer.valueOf(this.f917Gw)});
    }
}
