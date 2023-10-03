package com.google.protobuf;

import java.io.IOException;
import java.util.Arrays;

public final class UnknownFieldSetLite {
    private static final UnknownFieldSetLite DEFAULT_INSTANCE = new UnknownFieldSetLite(0, new int[0], new Object[0], false);
    private int count;
    private boolean isMutable;
    private int memoizedSerializedSize;
    private Object[] objects;
    private int[] tags;

    private UnknownFieldSetLite() {
        this(0, new int[8], new Object[8], true);
    }

    public static UnknownFieldSetLite getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    static UnknownFieldSetLite mutableCopyOf(UnknownFieldSetLite unknownFieldSetLite, UnknownFieldSetLite unknownFieldSetLite2) {
        int i = unknownFieldSetLite.count + unknownFieldSetLite2.count;
        int[] copyOf = Arrays.copyOf(unknownFieldSetLite.tags, i);
        System.arraycopy(unknownFieldSetLite2.tags, 0, copyOf, unknownFieldSetLite.count, unknownFieldSetLite2.count);
        Object[] copyOf2 = Arrays.copyOf(unknownFieldSetLite.objects, i);
        System.arraycopy(unknownFieldSetLite2.objects, 0, copyOf2, unknownFieldSetLite.count, unknownFieldSetLite2.count);
        return new UnknownFieldSetLite(i, copyOf, copyOf2, true);
    }

    static UnknownFieldSetLite newInstance() {
        return new UnknownFieldSetLite(0, new int[8], new Object[8], true);
    }

    private void storeField(int i, Object obj) {
        int i2 = this.count;
        if (i2 == this.tags.length) {
            int i3 = this.count + (i2 < 4 ? 8 : i2 >> 1);
            this.tags = Arrays.copyOf(this.tags, i3);
            this.objects = Arrays.copyOf(this.objects, i3);
        }
        int[] iArr = this.tags;
        int i4 = this.count;
        iArr[i4] = i;
        this.objects[i4] = obj;
        this.count = i4 + 1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UnknownFieldSetLite)) {
            return false;
        }
        UnknownFieldSetLite unknownFieldSetLite = (UnknownFieldSetLite) obj;
        return this.count == unknownFieldSetLite.count && Arrays.equals(this.tags, unknownFieldSetLite.tags) && Arrays.deepEquals(this.objects, unknownFieldSetLite.objects);
    }

    public int getSerializedSize() {
        int i;
        int i2 = this.memoizedSerializedSize;
        if (i2 != -1) {
            return i2;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.count; i4++) {
            int i5 = this.tags[i4];
            int i6 = i5 >>> 3;
            int i7 = i5 & 7;
            if (i7 != 0) {
                if (i7 == 1) {
                    i = CodedOutputStream.computeFixed64Size(i6, ((Long) this.objects[i4]).longValue());
                } else if (i7 == 2) {
                    i = CodedOutputStream.computeBytesSize(i6, (ByteString) this.objects[i4]);
                } else if (i7 == 3) {
                    i3 = ((UnknownFieldSetLite) this.objects[i4]).getSerializedSize() + (CodedOutputStream.computeTagSize(i6) * 2) + i3;
                } else if (i7 == 5) {
                    ((Integer) this.objects[i4]).intValue();
                    i3 += CodedOutputStream.computeTagSize(i6) + 4;
                } else {
                    throw new IllegalStateException(InvalidProtocolBufferException.invalidWireType());
                }
                i3 = i + i3;
            } else {
                i3 += CodedOutputStream.computeUInt64SizeNoTag(((Long) this.objects[i4]).longValue()) + CodedOutputStream.computeTagSize(i6);
            }
        }
        this.memoizedSerializedSize = i3;
        return i3;
    }

    public int hashCode() {
        int hashCode = Arrays.hashCode(this.tags);
        return Arrays.deepHashCode(this.objects) + ((hashCode + ((527 + this.count) * 31)) * 31);
    }

    public void makeImmutable() {
        this.isMutable = false;
    }

    /* access modifiers changed from: package-private */
    public boolean mergeFieldFrom(int i, CodedInputStream codedInputStream) throws IOException {
        int readTag;
        if (this.isMutable) {
            int i2 = i >>> 3;
            int i3 = i & 7;
            if (i3 == 0) {
                storeField(i, Long.valueOf(codedInputStream.readRawVarint64()));
                return true;
            } else if (i3 == 1) {
                storeField(i, Long.valueOf(codedInputStream.readFixed64()));
                return true;
            } else if (i3 == 2) {
                storeField(i, codedInputStream.readBytes());
                return true;
            } else if (i3 == 3) {
                UnknownFieldSetLite unknownFieldSetLite = new UnknownFieldSetLite(0, new int[8], new Object[8], true);
                do {
                    readTag = codedInputStream.readTag();
                    if (readTag == 0 || !unknownFieldSetLite.mergeFieldFrom(readTag, codedInputStream)) {
                        codedInputStream.checkLastTagWas((i2 << 3) | 4);
                        storeField(i, unknownFieldSetLite);
                    }
                    readTag = codedInputStream.readTag();
                    break;
                } while (!unknownFieldSetLite.mergeFieldFrom(readTag, codedInputStream));
                codedInputStream.checkLastTagWas((i2 << 3) | 4);
                storeField(i, unknownFieldSetLite);
                return true;
            } else if (i3 == 4) {
                return false;
            } else {
                if (i3 == 5) {
                    storeField(i, Integer.valueOf(codedInputStream.readFixed32()));
                    return true;
                }
                throw InvalidProtocolBufferException.invalidWireType();
            }
        } else {
            throw new UnsupportedOperationException();
        }
    }

    /* access modifiers changed from: package-private */
    public UnknownFieldSetLite mergeVarintField(int i, int i2) {
        if (!this.isMutable) {
            throw new UnsupportedOperationException();
        } else if (i != 0) {
            storeField((i << 3) | 0, Long.valueOf((long) i2));
            return this;
        } else {
            throw new IllegalArgumentException("Zero is not a valid field number.");
        }
    }

    /* access modifiers changed from: package-private */
    public final void printWithIndent(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.count; i2++) {
            MessageLiteToString.printField(sb, i, String.valueOf(this.tags[i2] >>> 3), this.objects[i2]);
        }
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.count; i++) {
            int i2 = this.tags[i];
            int i3 = i2 >>> 3;
            int i4 = i2 & 7;
            if (i4 == 0) {
                codedOutputStream.writeUInt64(i3, ((Long) this.objects[i]).longValue());
            } else if (i4 == 1) {
                codedOutputStream.writeFixed64(i3, ((Long) this.objects[i]).longValue());
            } else if (i4 == 2) {
                codedOutputStream.writeBytes(i3, (ByteString) this.objects[i]);
            } else if (i4 == 3) {
                codedOutputStream.writeTag(i3, 3);
                ((UnknownFieldSetLite) this.objects[i]).writeTo(codedOutputStream);
                codedOutputStream.writeTag(i3, 4);
            } else if (i4 == 5) {
                codedOutputStream.writeFixed32(i3, ((Integer) this.objects[i]).intValue());
            } else {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }
    }

    private UnknownFieldSetLite(int i, int[] iArr, Object[] objArr, boolean z) {
        this.memoizedSerializedSize = -1;
        this.count = i;
        this.tags = iArr;
        this.objects = objArr;
        this.isMutable = z;
    }
}
