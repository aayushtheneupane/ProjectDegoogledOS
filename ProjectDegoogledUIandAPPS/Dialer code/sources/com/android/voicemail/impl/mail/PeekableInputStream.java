package com.android.voicemail.impl.mail;

import java.io.IOException;
import java.io.InputStream;

public class PeekableInputStream extends InputStream {

    /* renamed from: in */
    private final InputStream f48in;
    private boolean peeked;
    private int peekedByte;

    public PeekableInputStream(InputStream inputStream) {
        this.f48in = inputStream;
    }

    public int peek() throws IOException {
        if (!this.peeked) {
            this.peekedByte = read();
            this.peeked = true;
        }
        return this.peekedByte;
    }

    public int read() throws IOException {
        if (!this.peeked) {
            return this.f48in.read();
        }
        this.peeked = false;
        return this.peekedByte;
    }

    public String toString() {
        return String.format("PeekableInputStream(in=%s, peeked=%b, peekedByte=%d)", new Object[]{this.f48in.toString(), Boolean.valueOf(this.peeked), Integer.valueOf(this.peekedByte)});
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (!this.peeked) {
            return this.f48in.read(bArr, i, i2);
        }
        bArr[0] = (byte) this.peekedByte;
        this.peeked = false;
        int read = this.f48in.read(bArr, i + 1, i2 - 1);
        if (read == -1) {
            return 1;
        }
        return read + 1;
    }

    public int read(byte[] bArr) throws IOException {
        int length = bArr.length;
        if (!this.peeked) {
            return this.f48in.read(bArr, 0, length);
        }
        bArr[0] = (byte) this.peekedByte;
        this.peeked = false;
        int read = this.f48in.read(bArr, 1, length - 1);
        if (read == -1) {
            return 1;
        }
        return 1 + read;
    }
}
