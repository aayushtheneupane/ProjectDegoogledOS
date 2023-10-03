package com.google.protobuf;

import com.google.protobuf.Utf8;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

public abstract class CodedOutputStream extends ByteOutput {
    /* access modifiers changed from: private */
    public static final long ARRAY_BASE_OFFSET = ((long) (!HAS_UNSAFE_ARRAY_OPERATIONS ? UNSAFE.arrayBaseOffset(byte[].class) : -1));
    /* access modifiers changed from: private */
    public static final boolean HAS_UNSAFE_ARRAY_OPERATIONS;
    /* access modifiers changed from: private */
    public static final Unsafe UNSAFE;
    private static final Logger logger = Logger.getLogger(CodedOutputStream.class.getName());

    private static class ArrayEncoder extends CodedOutputStream {
        private final byte[] buffer;
        private final int limit;
        private int position;

        ArrayEncoder(byte[] bArr, int i, int i2) {
            super((C09151) null);
            if (bArr != null) {
                int i3 = i + i2;
                if ((i | i2 | (bArr.length - i3)) >= 0) {
                    this.buffer = bArr;
                    this.position = i;
                    this.limit = i3;
                    return;
                }
                throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)}));
            }
            throw new NullPointerException("buffer");
        }

        public final int spaceLeft() {
            return this.limit - this.position;
        }

        public final void writeBool(int i, boolean z) throws IOException {
            writeUInt32NoTag((i << 3) | 0);
            byte b = z ? (byte) 1 : 0;
            try {
                byte[] bArr = this.buffer;
                int i2 = this.position;
                this.position = i2 + 1;
                bArr[i2] = b;
            } catch (IndexOutOfBoundsException unused) {
                throw new OutOfSpaceException(new IndexOutOfBoundsException(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), 1})));
            }
        }

        public final void writeBytes(int i, ByteString byteString) throws IOException {
            writeUInt32NoTag((i << 3) | 2);
            writeUInt32NoTag(byteString.size());
            byteString.writeTo(this);
        }

        public final void writeFixed32(int i, int i2) throws IOException {
            writeUInt32NoTag((i << 3) | 5);
            try {
                byte[] bArr = this.buffer;
                int i3 = this.position;
                this.position = i3 + 1;
                bArr[i3] = (byte) (i2 & 255);
                byte[] bArr2 = this.buffer;
                int i4 = this.position;
                this.position = i4 + 1;
                bArr2[i4] = (byte) ((i2 >> 8) & 255);
                byte[] bArr3 = this.buffer;
                int i5 = this.position;
                this.position = i5 + 1;
                bArr3[i5] = (byte) ((i2 >> 16) & 255);
                byte[] bArr4 = this.buffer;
                int i6 = this.position;
                this.position = i6 + 1;
                bArr4[i6] = (byte) ((i2 >> 24) & 255);
            } catch (IndexOutOfBoundsException unused) {
                throw new OutOfSpaceException(new IndexOutOfBoundsException(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), 1})));
            }
        }

        public final void writeFixed64(int i, long j) throws IOException {
            writeUInt32NoTag((i << 3) | 1);
            try {
                byte[] bArr = this.buffer;
                int i2 = this.position;
                this.position = i2 + 1;
                bArr[i2] = (byte) (((int) j) & 255);
                byte[] bArr2 = this.buffer;
                int i3 = this.position;
                this.position = i3 + 1;
                bArr2[i3] = (byte) (((int) (j >> 8)) & 255);
                byte[] bArr3 = this.buffer;
                int i4 = this.position;
                this.position = i4 + 1;
                bArr3[i4] = (byte) (((int) (j >> 16)) & 255);
                byte[] bArr4 = this.buffer;
                int i5 = this.position;
                this.position = i5 + 1;
                bArr4[i5] = (byte) (((int) (j >> 24)) & 255);
                byte[] bArr5 = this.buffer;
                int i6 = this.position;
                this.position = i6 + 1;
                bArr5[i6] = (byte) (((int) (j >> 32)) & 255);
                byte[] bArr6 = this.buffer;
                int i7 = this.position;
                this.position = i7 + 1;
                bArr6[i7] = (byte) (((int) (j >> 40)) & 255);
                byte[] bArr7 = this.buffer;
                int i8 = this.position;
                this.position = i8 + 1;
                bArr7[i8] = (byte) (((int) (j >> 48)) & 255);
                byte[] bArr8 = this.buffer;
                int i9 = this.position;
                this.position = i9 + 1;
                bArr8[i9] = (byte) (((int) (j >> 56)) & 255);
            } catch (IndexOutOfBoundsException unused) {
                throw new OutOfSpaceException(new IndexOutOfBoundsException(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), 1})));
            }
        }

        public final void writeInt32(int i, int i2) throws IOException {
            writeUInt32NoTag((i << 3) | 0);
            if (i2 >= 0) {
                writeUInt32NoTag(i2);
            } else {
                writeUInt64NoTag((long) i2);
            }
        }

        public final void writeLazy(byte[] bArr, int i, int i2) throws IOException {
            try {
                System.arraycopy(bArr, i, this.buffer, this.position, i2);
                this.position += i2;
            } catch (IndexOutOfBoundsException unused) {
                throw new OutOfSpaceException(new IndexOutOfBoundsException(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(i2)})));
            }
        }

        public final void writeMessage(int i, MessageLite messageLite) throws IOException {
            writeUInt32NoTag((i << 3) | 2);
            writeUInt32NoTag(messageLite.getSerializedSize());
            messageLite.writeTo(this);
        }

        public final void writeString(int i, String str) throws IOException {
            writeUInt32NoTag((i << 3) | 2);
            int i2 = this.position;
            try {
                int computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(str.length() * 3);
                int computeUInt32SizeNoTag2 = CodedOutputStream.computeUInt32SizeNoTag(str.length());
                if (computeUInt32SizeNoTag2 == computeUInt32SizeNoTag) {
                    this.position = i2 + computeUInt32SizeNoTag2;
                    int encode = Utf8.encode(str, this.buffer, this.position, spaceLeft());
                    this.position = i2;
                    writeUInt32NoTag((encode - i2) - computeUInt32SizeNoTag2);
                    this.position = encode;
                    return;
                }
                writeUInt32NoTag(Utf8.encodedLength(str));
                this.position = Utf8.encode(str, this.buffer, this.position, spaceLeft());
            } catch (Utf8.UnpairedSurrogateException e) {
                this.position = i2;
                inefficientWriteStringNoTag(str, e);
            } catch (IndexOutOfBoundsException e2) {
                throw new OutOfSpaceException(e2);
            }
        }

        public final void writeTag(int i, int i2) throws IOException {
            writeUInt32NoTag((i << 3) | i2);
        }

        public final void writeUInt32NoTag(int i) throws IOException {
            if (!CodedOutputStream.HAS_UNSAFE_ARRAY_OPERATIONS || spaceLeft() < 10) {
                while ((i & -128) != 0) {
                    byte[] bArr = this.buffer;
                    int i2 = this.position;
                    this.position = i2 + 1;
                    bArr[i2] = (byte) ((i & 127) | 128);
                    i >>>= 7;
                }
                try {
                    byte[] bArr2 = this.buffer;
                    int i3 = this.position;
                    this.position = i3 + 1;
                    bArr2[i3] = (byte) i;
                } catch (IndexOutOfBoundsException unused) {
                    throw new OutOfSpaceException(new IndexOutOfBoundsException(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), 1})));
                }
            } else {
                long access$200 = CodedOutputStream.ARRAY_BASE_OFFSET + ((long) this.position);
                while ((i & -128) != 0) {
                    CodedOutputStream.UNSAFE.putByte(this.buffer, access$200, (byte) ((i & 127) | 128));
                    this.position++;
                    i >>>= 7;
                    access$200 = 1 + access$200;
                }
                CodedOutputStream.UNSAFE.putByte(this.buffer, access$200, (byte) i);
                this.position++;
            }
        }

        public final void writeUInt64(int i, long j) throws IOException {
            writeUInt32NoTag((i << 3) | 0);
            writeUInt64NoTag(j);
        }

        public final void writeUInt64NoTag(long j) throws IOException {
            if (!CodedOutputStream.HAS_UNSAFE_ARRAY_OPERATIONS || spaceLeft() < 10) {
                while ((j & -128) != 0) {
                    byte[] bArr = this.buffer;
                    int i = this.position;
                    this.position = i + 1;
                    bArr[i] = (byte) ((((int) j) & 127) | 128);
                    j >>>= 7;
                }
                try {
                    byte[] bArr2 = this.buffer;
                    int i2 = this.position;
                    this.position = i2 + 1;
                    bArr2[i2] = (byte) ((int) j);
                } catch (IndexOutOfBoundsException unused) {
                    throw new OutOfSpaceException(new IndexOutOfBoundsException(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), 1})));
                }
            } else {
                long access$200 = CodedOutputStream.ARRAY_BASE_OFFSET + ((long) this.position);
                while ((j & -128) != 0) {
                    CodedOutputStream.UNSAFE.putByte(this.buffer, access$200, (byte) ((((int) j) & 127) | 128));
                    this.position++;
                    j >>>= 7;
                    access$200 = 1 + access$200;
                }
                CodedOutputStream.UNSAFE.putByte(this.buffer, access$200, (byte) ((int) j));
                this.position++;
            }
        }
    }

    public static class OutOfSpaceException extends IOException {
        private static final long serialVersionUID = -6947486886997889499L;

        OutOfSpaceException(Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0072  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x007b  */
    static {
        /*
            java.lang.Class<com.google.protobuf.CodedOutputStream> r0 = com.google.protobuf.CodedOutputStream.class
            java.lang.String r0 = r0.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            logger = r0
            com.google.protobuf.CodedOutputStream$1 r0 = new com.google.protobuf.CodedOutputStream$1     // Catch:{ all -> 0x0018 }
            r0.<init>()     // Catch:{ all -> 0x0018 }
            java.lang.Object r0 = java.security.AccessController.doPrivileged(r0)     // Catch:{ all -> 0x0018 }
            sun.misc.Unsafe r0 = (sun.misc.Unsafe) r0     // Catch:{ all -> 0x0018 }
            goto L_0x0019
        L_0x0018:
            r0 = 0
        L_0x0019:
            java.util.logging.Logger r1 = logger
            java.util.logging.Level r2 = java.util.logging.Level.FINEST
            java.lang.String r3 = "available"
            java.lang.String r4 = "unavailable"
            if (r0 == 0) goto L_0x0025
            r5 = r3
            goto L_0x0026
        L_0x0025:
            r5 = r4
        L_0x0026:
            java.lang.String r6 = "sun.misc.Unsafe: {}"
            r1.log(r2, r6, r5)
            UNSAFE = r0
            sun.misc.Unsafe r0 = UNSAFE
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x005e
            java.lang.Class r0 = r0.getClass()     // Catch:{ all -> 0x005e }
            java.lang.String r5 = "arrayBaseOffset"
            java.lang.Class[] r6 = new java.lang.Class[r1]     // Catch:{ all -> 0x005e }
            java.lang.Class<java.lang.Class> r7 = java.lang.Class.class
            r6[r2] = r7     // Catch:{ all -> 0x005e }
            r0.getMethod(r5, r6)     // Catch:{ all -> 0x005e }
            sun.misc.Unsafe r0 = UNSAFE     // Catch:{ all -> 0x005e }
            java.lang.Class r0 = r0.getClass()     // Catch:{ all -> 0x005e }
            java.lang.String r5 = "putByte"
            r6 = 3
            java.lang.Class[] r6 = new java.lang.Class[r6]     // Catch:{ all -> 0x005e }
            java.lang.Class<java.lang.Object> r7 = java.lang.Object.class
            r6[r2] = r7     // Catch:{ all -> 0x005e }
            java.lang.Class r7 = java.lang.Long.TYPE     // Catch:{ all -> 0x005e }
            r6[r1] = r7     // Catch:{ all -> 0x005e }
            r7 = 2
            java.lang.Class r8 = java.lang.Byte.TYPE     // Catch:{ all -> 0x005e }
            r6[r7] = r8     // Catch:{ all -> 0x005e }
            r0.getMethod(r5, r6)     // Catch:{ all -> 0x005e }
            goto L_0x005f
        L_0x005e:
            r1 = r2
        L_0x005f:
            java.util.logging.Logger r0 = logger
            java.util.logging.Level r2 = java.util.logging.Level.FINEST
            if (r1 == 0) goto L_0x0066
            goto L_0x0067
        L_0x0066:
            r3 = r4
        L_0x0067:
            java.lang.String r4 = "Unsafe array operations: {}"
            r0.log(r2, r4, r3)
            HAS_UNSAFE_ARRAY_OPERATIONS = r1
            boolean r0 = HAS_UNSAFE_ARRAY_OPERATIONS
            if (r0 == 0) goto L_0x007b
            sun.misc.Unsafe r0 = UNSAFE
            java.lang.Class<byte[]> r1 = byte[].class
            int r0 = r0.arrayBaseOffset(r1)
            goto L_0x007c
        L_0x007b:
            r0 = -1
        L_0x007c:
            long r0 = (long) r0
            ARRAY_BASE_OFFSET = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedOutputStream.<clinit>():void");
    }

    private CodedOutputStream() {
    }

    public static int computeBoolSize(int i, boolean z) {
        return computeTagSize(i) + 1;
    }

    public static int computeBytesSize(int i, ByteString byteString) {
        return computeTagSize(i) + computeLengthDelimitedFieldSize(byteString.size());
    }

    public static int computeEnumSize(int i, int i2) {
        return computeTagSize(i) + computeInt32SizeNoTag(i2);
    }

    public static int computeFixed64Size(int i, long j) {
        return computeTagSize(i) + 8;
    }

    public static int computeInt32Size(int i, int i2) {
        return (i2 >= 0 ? computeUInt32SizeNoTag(i2) : 10) + computeTagSize(i);
    }

    public static int computeInt32SizeNoTag(int i) {
        if (i >= 0) {
            return computeUInt32SizeNoTag(i);
        }
        return 10;
    }

    public static int computeInt64Size(int i, long j) {
        return computeTagSize(i) + computeUInt64SizeNoTag(j);
    }

    private static int computeLengthDelimitedFieldSize(int i) {
        return computeUInt32SizeNoTag(i) + i;
    }

    public static int computeMessageSize(int i, MessageLite messageLite) {
        return computeTagSize(i) + computeLengthDelimitedFieldSize(messageLite.getSerializedSize());
    }

    public static int computeStringSize(int i, String str) {
        int i2;
        int computeTagSize = computeTagSize(i);
        try {
            i2 = Utf8.encodedLength(str);
        } catch (Utf8.UnpairedSurrogateException unused) {
            i2 = str.getBytes(Internal.UTF_8).length;
        }
        return computeTagSize + computeLengthDelimitedFieldSize(i2);
    }

    public static int computeTagSize(int i) {
        return computeUInt32SizeNoTag((i << 3) | 0);
    }

    public static int computeUInt32SizeNoTag(int i) {
        if ((i & -128) == 0) {
            return 1;
        }
        if ((i & -16384) == 0) {
            return 2;
        }
        if ((-2097152 & i) == 0) {
            return 3;
        }
        return (i & -268435456) == 0 ? 4 : 5;
    }

    public static int computeUInt64SizeNoTag(long j) {
        int i;
        if ((-128 & j) == 0) {
            return 1;
        }
        if (j < 0) {
            return 10;
        }
        if ((-34359738368L & j) != 0) {
            i = 6;
            j >>>= 28;
        } else {
            i = 2;
        }
        if ((-2097152 & j) != 0) {
            i += 2;
            j >>>= 14;
        }
        return (j & -16384) != 0 ? i + 1 : i;
    }

    public static CodedOutputStream newInstance(byte[] bArr) {
        return newInstance(bArr, 0, bArr.length);
    }

    /* access modifiers changed from: package-private */
    public final void inefficientWriteStringNoTag(String str, Utf8.UnpairedSurrogateException unpairedSurrogateException) throws IOException {
        logger.log(Level.WARNING, "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", unpairedSurrogateException);
        byte[] bytes = str.getBytes(Internal.UTF_8);
        try {
            writeUInt32NoTag(bytes.length);
            writeLazy(bytes, 0, bytes.length);
        } catch (IndexOutOfBoundsException e) {
            throw new OutOfSpaceException(e);
        } catch (OutOfSpaceException e2) {
            throw e2;
        }
    }

    public abstract int spaceLeft();

    public abstract void writeBool(int i, boolean z) throws IOException;

    public abstract void writeBytes(int i, ByteString byteString) throws IOException;

    public abstract void writeFixed32(int i, int i2) throws IOException;

    public abstract void writeFixed64(int i, long j) throws IOException;

    public abstract void writeInt32(int i, int i2) throws IOException;

    public abstract void writeMessage(int i, MessageLite messageLite) throws IOException;

    public abstract void writeString(int i, String str) throws IOException;

    public abstract void writeTag(int i, int i2) throws IOException;

    public abstract void writeUInt32NoTag(int i) throws IOException;

    public abstract void writeUInt64(int i, long j) throws IOException;

    /* synthetic */ CodedOutputStream(C09151 r1) {
    }

    public static CodedOutputStream newInstance(byte[] bArr, int i, int i2) {
        return new ArrayEncoder(bArr, i, i2);
    }
}
