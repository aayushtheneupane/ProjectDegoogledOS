package p000;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/* renamed from: hzt */
/* compiled from: PG */
public final class hzt {
    static {
        new hzs();
    }

    /* renamed from: a */
    private static byte[] m12553a(Deque deque, int i) {
        byte[] bArr = new byte[i];
        int i2 = i;
        while (i2 > 0) {
            byte[] bArr2 = (byte[]) deque.removeFirst();
            int min = Math.min(i2, bArr2.length);
            System.arraycopy(bArr2, 0, bArr, i - i2, min);
            i2 -= min;
        }
        return bArr;
    }

    /* renamed from: a */
    public static void m12551a(InputStream inputStream, OutputStream outputStream) {
        ife.m12898e((Object) inputStream);
        ife.m12898e((Object) outputStream);
        byte[] bArr = new byte[8192];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    /* renamed from: a */
    static byte[] m12552a(InputStream inputStream, long j) {
        ife.m12848a(j >= 0, "expectedSize (%s) must be non-negative", j);
        if (j <= 2147483639) {
            int i = (int) j;
            byte[] bArr = new byte[i];
            int i2 = i;
            while (i2 > 0) {
                int i3 = i - i2;
                int read = inputStream.read(bArr, i3, i2);
                if (read == -1) {
                    return Arrays.copyOf(bArr, i3);
                }
                i2 -= read;
            }
            int read2 = inputStream.read();
            if (read2 == -1) {
                return bArr;
            }
            ArrayDeque arrayDeque = new ArrayDeque(22);
            arrayDeque.add(bArr);
            arrayDeque.add(new byte[]{(byte) read2});
            int i4 = i + 1;
            int i5 = 8192;
            while (i4 < 2147483639) {
                int min = Math.min(i5, 2147483639 - i4);
                byte[] bArr2 = new byte[min];
                arrayDeque.add(bArr2);
                int i6 = 0;
                while (i6 < min) {
                    int read3 = inputStream.read(bArr2, i6, min - i6);
                    if (read3 == -1) {
                        return m12553a((Deque) arrayDeque, i4);
                    }
                    i6 += read3;
                    i4 += read3;
                }
                long j2 = (long) i5;
                i5 = ife.m12862b(j2 + j2);
            }
            if (inputStream.read() == -1) {
                return m12553a((Deque) arrayDeque, 2147483639);
            }
            throw new OutOfMemoryError("input is too large to fit in a byte array");
        }
        StringBuilder sb = new StringBuilder(62);
        sb.append(j);
        sb.append(" bytes is too large to fit in a byte array");
        throw new OutOfMemoryError(sb.toString());
    }
}
