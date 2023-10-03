package p000;

import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;

/* renamed from: abv */
/* compiled from: PG */
final class abv extends InputStream implements DataInput {

    /* renamed from: d */
    private static final ByteOrder f109d = ByteOrder.LITTLE_ENDIAN;

    /* renamed from: e */
    private static final ByteOrder f110e = ByteOrder.BIG_ENDIAN;

    /* renamed from: a */
    public ByteOrder f111a;

    /* renamed from: b */
    public final int f112b;

    /* renamed from: c */
    public int f113c;

    /* renamed from: f */
    private DataInputStream f114f;

    public final String readLine() {
        return null;
    }

    public abv(InputStream inputStream) {
        ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
        this.f111a = ByteOrder.BIG_ENDIAN;
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        this.f114f = dataInputStream;
        int available = dataInputStream.available();
        this.f112b = available;
        this.f113c = 0;
        this.f114f.mark(available);
        this.f111a = byteOrder;
    }

    public abv(byte[] bArr) {
        this((InputStream) new ByteArrayInputStream(bArr));
    }

    public final int available() {
        return this.f114f.available();
    }

    public final int read() {
        this.f113c++;
        return this.f114f.read();
    }

    public final int read(byte[] bArr, int i, int i2) {
        int read = this.f114f.read(bArr, i, i2);
        this.f113c += read;
        return read;
    }

    public final boolean readBoolean() {
        this.f113c++;
        return this.f114f.readBoolean();
    }

    public final byte readByte() {
        int i = this.f113c + 1;
        this.f113c = i;
        if (i <= this.f112b) {
            int read = this.f114f.read();
            if (read >= 0) {
                return (byte) read;
            }
            throw new EOFException();
        }
        throw new EOFException();
    }

    public final char readChar() {
        this.f113c += 2;
        return this.f114f.readChar();
    }

    public final double readDouble() {
        return Double.longBitsToDouble(readLong());
    }

    public final float readFloat() {
        return Float.intBitsToFloat(readInt());
    }

    public final void readFully(byte[] bArr) {
        int i = this.f113c;
        int length = bArr.length;
        int i2 = i + length;
        this.f113c = i2;
        if (i2 > this.f112b) {
            throw new EOFException();
        } else if (this.f114f.read(bArr, 0, length) != length) {
            throw new IOException("Couldn't read up to the length of buffer");
        }
    }

    public final void readFully(byte[] bArr, int i, int i2) {
        int i3 = this.f113c + i2;
        this.f113c = i3;
        if (i3 > this.f112b) {
            throw new EOFException();
        } else if (this.f114f.read(bArr, i, i2) != i2) {
            throw new IOException("Couldn't read up to the length of buffer");
        }
    }

    public final int readInt() {
        int i = this.f113c + 4;
        this.f113c = i;
        if (i <= this.f112b) {
            int read = this.f114f.read();
            int read2 = this.f114f.read();
            int read3 = this.f114f.read();
            int read4 = this.f114f.read();
            if ((read | read2 | read3 | read4) >= 0) {
                ByteOrder byteOrder = this.f111a;
                if (byteOrder == f109d) {
                    return (read4 << 24) + (read3 << 16) + (read2 << 8) + read;
                }
                if (byteOrder == f110e) {
                    return (read << 24) + (read2 << 16) + (read3 << 8) + read4;
                }
                throw new IOException("Invalid byte order: " + this.f111a);
            }
            throw new EOFException();
        }
        throw new EOFException();
    }

    public final long readLong() {
        int i = this.f113c + 8;
        this.f113c = i;
        if (i <= this.f112b) {
            int read = this.f114f.read();
            int read2 = this.f114f.read();
            int read3 = this.f114f.read();
            int read4 = this.f114f.read();
            int read5 = this.f114f.read();
            int read6 = this.f114f.read();
            int read7 = this.f114f.read();
            int read8 = this.f114f.read();
            if ((read | read2 | read3 | read4 | read5 | read6 | read7 | read8) >= 0) {
                ByteOrder byteOrder = this.f111a;
                if (byteOrder == f109d) {
                    return (((long) read8) << 56) + (((long) read7) << 48) + (((long) read6) << 40) + (((long) read5) << 32) + (((long) read4) << 24) + (((long) read3) << 16) + (((long) read2) << 8) + ((long) read);
                }
                int i2 = read2;
                if (byteOrder == f110e) {
                    return (((long) read) << 56) + (((long) i2) << 48) + (((long) read3) << 40) + (((long) read4) << 32) + (((long) read5) << 24) + (((long) read6) << 16) + (((long) read7) << 8) + ((long) read8);
                }
                throw new IOException("Invalid byte order: " + this.f111a);
            }
            throw new EOFException();
        }
        throw new EOFException();
    }

    public final short readShort() {
        int i = this.f113c + 2;
        this.f113c = i;
        if (i <= this.f112b) {
            int read = this.f114f.read();
            int read2 = this.f114f.read();
            if ((read | read2) >= 0) {
                ByteOrder byteOrder = this.f111a;
                if (byteOrder == f109d) {
                    return (short) ((read2 << 8) + read);
                }
                if (byteOrder == f110e) {
                    return (short) ((read << 8) + read2);
                }
                throw new IOException("Invalid byte order: " + this.f111a);
            }
            throw new EOFException();
        }
        throw new EOFException();
    }

    public final String readUTF() {
        this.f113c += 2;
        return this.f114f.readUTF();
    }

    public final int readUnsignedByte() {
        this.f113c++;
        return this.f114f.readUnsignedByte();
    }

    /* renamed from: a */
    public final long mo127a() {
        return ((long) readInt()) & 4294967295L;
    }

    public final int readUnsignedShort() {
        int i = this.f113c + 2;
        this.f113c = i;
        if (i <= this.f112b) {
            int read = this.f114f.read();
            int read2 = this.f114f.read();
            if ((read | read2) >= 0) {
                ByteOrder byteOrder = this.f111a;
                if (byteOrder == f109d) {
                    return (read2 << 8) + read;
                }
                if (byteOrder == f110e) {
                    return (read << 8) + read2;
                }
                throw new IOException("Invalid byte order: " + this.f111a);
            }
            throw new EOFException();
        }
        throw new EOFException();
    }

    /* renamed from: a */
    public final void mo128a(long j) {
        long j2 = (long) this.f113c;
        if (j2 > j) {
            this.f113c = 0;
            this.f114f.reset();
            this.f114f.mark(this.f112b);
        } else {
            j -= j2;
        }
        int i = (int) j;
        if (skipBytes(i) != i) {
            throw new IOException("Couldn't seek up to the byteCount");
        }
    }

    public final int skipBytes(int i) {
        int min = Math.min(i, this.f112b - this.f113c);
        int i2 = 0;
        while (i2 < min) {
            i2 += this.f114f.skipBytes(min - i2);
        }
        this.f113c += i2;
        return i2;
    }
}
