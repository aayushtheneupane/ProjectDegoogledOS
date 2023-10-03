package com.google.common.p043io;

import java.io.OutputStream;

/* renamed from: com.google.common.io.b */
class C1713b extends OutputStream {
    C1713b() {
    }

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
}
