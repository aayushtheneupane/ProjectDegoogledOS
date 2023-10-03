package com.android.voicemail.impl.mail;

import java.io.IOException;
import java.io.InputStream;

public class FixedLengthInputStream extends InputStream {
    private int count;

    /* renamed from: in */
    private final InputStream f46in;
    private final int length;

    public FixedLengthInputStream(InputStream inputStream, int i) {
        this.f46in = inputStream;
        this.length = i;
    }

    public int available() throws IOException {
        return this.length - this.count;
    }

    public int getLength() {
        return this.length;
    }

    public int read() throws IOException {
        int i = this.count;
        if (i >= this.length) {
            return -1;
        }
        this.count = i + 1;
        return this.f46in.read();
    }

    public String toString() {
        return String.format("FixedLengthInputStream(in=%s, length=%d)", new Object[]{this.f46in.toString(), Integer.valueOf(this.length)});
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read;
        int i3 = this.count;
        int i4 = this.length;
        if (i3 >= i4 || (read = this.f46in.read(bArr, i, Math.min(i4 - i3, i2))) == -1) {
            return -1;
        }
        this.count += read;
        return read;
    }

    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }
}
