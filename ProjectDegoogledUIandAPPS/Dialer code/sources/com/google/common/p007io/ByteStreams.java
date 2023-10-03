package com.google.common.p007io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* renamed from: com.google.common.io.ByteStreams */
public final class ByteStreams {
    static {
        new OutputStream() {
            public String toString() {
                return "ByteStreams.nullOutputStream()";
            }

            public void write(int i) {
            }

            public void write(byte[] bArr) {
                if (bArr == null) {
                    throw new NullPointerException();
                }
            }

            public void write(byte[] bArr, int i, int i2) {
                if (bArr == null) {
                    throw new NullPointerException();
                }
            }
        };
    }

    public static long copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        if (inputStream == null) {
            throw new NullPointerException();
        } else if (outputStream != null) {
            byte[] bArr = new byte[8192];
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
}
