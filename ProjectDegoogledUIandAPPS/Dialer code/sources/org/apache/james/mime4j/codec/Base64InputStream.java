package org.apache.james.mime4j.codec;

import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.io.IOException;
import java.io.InputStream;
import org.apache.james.mime4j.util.ByteArrayBuffer;

public class Base64InputStream extends InputStream {
    private static final int[] BASE64_DECODE = new int[256];
    private boolean closed = false;
    private final ByteArrayBuffer decodedBuf;
    private final byte[] encoded;
    private boolean eof;

    /* renamed from: in */
    private final InputStream f83in;
    private final DecodeMonitor monitor;
    private int position = 0;
    private final byte[] singleByte = new byte[1];
    private int size = 0;

    static {
        int i = 0;
        for (int i2 = 0; i2 < 256; i2++) {
            BASE64_DECODE[i2] = -1;
        }
        while (true) {
            byte[] bArr = Base64OutputStream.BASE64_TABLE;
            if (i < bArr.length) {
                BASE64_DECODE[bArr[i] & 255] = i;
                i++;
            } else {
                return;
            }
        }
    }

    public Base64InputStream(InputStream inputStream, DecodeMonitor decodeMonitor) {
        if (inputStream != null) {
            this.encoded = new byte[1536];
            this.decodedBuf = new ByteArrayBuffer(512);
            this.f83in = inputStream;
            this.monitor = decodeMonitor;
            return;
        }
        throw new IllegalArgumentException();
    }

    private int read0(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        int i4 = i + i2;
        if (this.decodedBuf.length() > 0) {
            int min = Math.min(this.decodedBuf.length(), i2);
            System.arraycopy(this.decodedBuf.buffer(), 0, bArr, i, min);
            this.decodedBuf.remove(0, min);
            i3 = min + i;
        } else {
            i3 = i;
        }
        if (!this.eof) {
            int i5 = 0;
            int i6 = 0;
            while (i3 < i4) {
                while (this.position == this.size) {
                    InputStream inputStream = this.f83in;
                    byte[] bArr2 = this.encoded;
                    int read = inputStream.read(bArr2, 0, bArr2.length);
                    if (read == -1) {
                        this.eof = true;
                        if (i5 != 0) {
                            if (this.monitor.warn("Unexpected end of BASE64 stream", "dropping " + i5 + " sextet(s)")) {
                                throw new IOException("Unexpected end of BASE64 stream");
                            }
                        }
                        if (i3 == i) {
                            return -1;
                        }
                        return i3 - i;
                    } else if (read > 0) {
                        this.position = 0;
                        this.size = read;
                    }
                }
                while (true) {
                    int i7 = this.position;
                    if (i7 < this.size && i3 < i4) {
                        byte[] bArr3 = this.encoded;
                        this.position = i7 + 1;
                        byte b = bArr3[i7] & 255;
                        if (b == 61) {
                            this.eof = true;
                            if (i5 == 2) {
                                byte b2 = (byte) (i6 >>> 4);
                                if (i3 < i4) {
                                    bArr[i3] = b2;
                                    i3++;
                                } else {
                                    this.decodedBuf.append(b2);
                                }
                            } else if (i5 == 3) {
                                byte b3 = (byte) (i6 >>> 10);
                                byte b4 = (byte) ((i6 >>> 2) & 255);
                                if (i3 < i4 - 1) {
                                    int i8 = i3 + 1;
                                    bArr[i3] = b3;
                                    i3 = i8 + 1;
                                    bArr[i8] = b4;
                                } else if (i3 < i4) {
                                    bArr[i3] = b3;
                                    this.decodedBuf.append(b4);
                                    i3++;
                                } else {
                                    this.decodedBuf.append(b3);
                                    this.decodedBuf.append(b4);
                                }
                            } else {
                                if (this.monitor.warn("Unexpected padding character", "dropping " + i5 + " sextet(s)")) {
                                    throw new IOException("Unexpected padding character");
                                }
                            }
                            return i3 - i;
                        }
                        int i9 = BASE64_DECODE[b];
                        if (i9 >= 0) {
                            i6 = (i6 << 6) | i9;
                            i5++;
                            if (i5 == 4) {
                                byte b5 = (byte) (i6 >>> 16);
                                byte b6 = (byte) (i6 >>> 8);
                                byte b7 = (byte) i6;
                                if (i3 < i4 - 2) {
                                    int i10 = i3 + 1;
                                    bArr[i3] = b5;
                                    int i11 = i10 + 1;
                                    bArr[i10] = b6;
                                    bArr[i11] = b7;
                                    i3 = i11 + 1;
                                    i5 = 0;
                                } else {
                                    if (i3 < i4 - 1) {
                                        bArr[i3] = b5;
                                        bArr[i3 + 1] = b6;
                                        this.decodedBuf.append(b7);
                                    } else if (i3 < i4) {
                                        bArr[i3] = b5;
                                        this.decodedBuf.append(b6);
                                        this.decodedBuf.append(b7);
                                    } else {
                                        this.decodedBuf.append(b5);
                                        this.decodedBuf.append(b6);
                                        this.decodedBuf.append(b7);
                                    }
                                    return i4 - i;
                                }
                            } else {
                                continue;
                            }
                        } else if (!(b == 13 || b == 10 || b == 32)) {
                            DecodeMonitor decodeMonitor = this.monitor;
                            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Unexpected base64 byte: ");
                            outline13.append((byte) b);
                            if (decodeMonitor.warn(outline13.toString(), "ignoring.")) {
                                throw new IOException("Unexpected base64 byte");
                            }
                        }
                    }
                }
            }
            return i4 - i;
        } else if (i3 == i) {
            return -1;
        } else {
            return i3 - i;
        }
    }

    public void close() throws IOException {
        if (!this.closed) {
            this.closed = true;
        }
    }

    public int read() throws IOException {
        int read0;
        if (!this.closed) {
            do {
                read0 = read0(this.singleByte, 0, 1);
                if (read0 == -1) {
                    return -1;
                }
            } while (read0 != 1);
            return this.singleByte[0] & 255;
        }
        throw new IOException("Stream has been closed");
    }

    public int read(byte[] bArr) throws IOException {
        if (this.closed) {
            throw new IOException("Stream has been closed");
        } else if (bArr == null) {
            throw new NullPointerException();
        } else if (bArr.length == 0) {
            return 0;
        } else {
            return read0(bArr, 0, bArr.length);
        }
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.closed) {
            throw new IOException("Stream has been closed");
        } else if (bArr == null) {
            throw new NullPointerException();
        } else if (i < 0 || i2 < 0 || i + i2 > bArr.length) {
            throw new IndexOutOfBoundsException();
        } else if (i2 == 0) {
            return 0;
        } else {
            return read0(bArr, i, i2);
        }
    }
}
