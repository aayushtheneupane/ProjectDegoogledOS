package com.google.common.p043io;

import androidx.core.app.NotificationCompat;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

/* renamed from: com.google.common.io.d */
public final class C1715d {
    static {
        new C1713b();
    }

    /* renamed from: a */
    static byte[] m4647a(InputStream inputStream, int i) {
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
        C1714c cVar = new C1714c((C1713b) null);
        cVar.write(read2);
        copy(inputStream, cVar);
        byte[] bArr2 = new byte[(cVar.size() + bArr.length)];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        cVar.mo9354f(bArr2, bArr.length);
        return bArr2;
    }

    public static long copy(InputStream inputStream, OutputStream outputStream) {
        if (inputStream == null) {
            throw new NullPointerException();
        } else if (outputStream != null) {
            byte[] bArr = new byte[NotificationCompat.FLAG_BUBBLE];
            long j = 0;
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    return j;
                }
                outputStream.write(bArr, 0, read);
                j += (long) read;
            }
        } else {
            throw new NullPointerException();
        }
    }

    /* renamed from: e */
    public static byte[] m4648e(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        copy(inputStream, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}
