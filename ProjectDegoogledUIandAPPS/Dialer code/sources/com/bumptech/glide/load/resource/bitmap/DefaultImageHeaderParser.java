package com.bumptech.glide.load.resource.bitmap;

import android.support.p002v7.appcompat.R$style;
import android.util.Log;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

public final class DefaultImageHeaderParser implements ImageHeaderParser {
    private static final int[] BYTES_PER_FORMAT = {0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8};
    static final byte[] JPEG_EXIF_SEGMENT_PREAMBLE_BYTES = "Exif\u0000\u0000".getBytes(Charset.forName("UTF-8"));

    private static final class ByteBufferReader implements Reader {
        private final ByteBuffer byteBuffer;

        ByteBufferReader(ByteBuffer byteBuffer2) {
            this.byteBuffer = byteBuffer2;
            byteBuffer2.order(ByteOrder.BIG_ENDIAN);
        }

        public int getByte() {
            if (this.byteBuffer.remaining() < 1) {
                return -1;
            }
            return this.byteBuffer.get();
        }

        public int getUInt16() {
            return (getByte() & 255) | ((getByte() << 8) & 65280);
        }

        public int read(byte[] bArr, int i) {
            int min = Math.min(i, this.byteBuffer.remaining());
            if (min == 0) {
                return -1;
            }
            this.byteBuffer.get(bArr, 0, min);
            return min;
        }

        public long skip(long j) {
            int min = (int) Math.min((long) this.byteBuffer.remaining(), j);
            ByteBuffer byteBuffer2 = this.byteBuffer;
            byteBuffer2.position(byteBuffer2.position() + min);
            return (long) min;
        }
    }

    private static final class RandomAccessReader {
        private final ByteBuffer data;

        RandomAccessReader(byte[] bArr, int i) {
            this.data = (ByteBuffer) ByteBuffer.wrap(bArr).order(ByteOrder.BIG_ENDIAN).limit(i);
        }

        /* access modifiers changed from: package-private */
        public short getInt16(int i) {
            if (this.data.remaining() - i >= 2) {
                return this.data.getShort(i);
            }
            return -1;
        }

        /* access modifiers changed from: package-private */
        public int getInt32(int i) {
            if (this.data.remaining() - i >= 4) {
                return this.data.getInt(i);
            }
            return -1;
        }

        /* access modifiers changed from: package-private */
        public int length() {
            return this.data.remaining();
        }

        /* access modifiers changed from: package-private */
        public void order(ByteOrder byteOrder) {
            this.data.order(byteOrder);
        }
    }

    private interface Reader {
        int getByte() throws IOException;

        int getUInt16() throws IOException;

        int read(byte[] bArr, int i) throws IOException;

        long skip(long j) throws IOException;
    }

    private static final class StreamReader implements Reader {

        /* renamed from: is */
        private final InputStream f64is;

        StreamReader(InputStream inputStream) {
            this.f64is = inputStream;
        }

        public int getByte() throws IOException {
            return this.f64is.read();
        }

        public int getUInt16() throws IOException {
            return (this.f64is.read() & 255) | ((this.f64is.read() << 8) & 65280);
        }

        public short getUInt8() throws IOException {
            return (short) (this.f64is.read() & 255);
        }

        public int read(byte[] bArr, int i) throws IOException {
            int i2 = i;
            while (i2 > 0) {
                int read = this.f64is.read(bArr, i - i2, i2);
                if (read == -1) {
                    break;
                }
                i2 -= read;
            }
            return i - i2;
        }

        public long skip(long j) throws IOException {
            if (j < 0) {
                return 0;
            }
            long j2 = j;
            while (j2 > 0) {
                long skip = this.f64is.skip(j2);
                if (skip <= 0) {
                    if (this.f64is.read() == -1) {
                        break;
                    }
                    skip = 1;
                }
                j2 -= skip;
            }
            return j - j2;
        }
    }

    private int parseExifSegment(Reader reader, byte[] bArr, int i) throws IOException {
        ByteOrder byteOrder;
        int read = reader.read(bArr, i);
        if (read != i) {
            if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                StringBuilder sb = new StringBuilder(81);
                sb.append("Unable to read exif segment data, length: ");
                sb.append(i);
                sb.append(", actually read: ");
                sb.append(read);
                Log.d("DfltImageHeaderParser", sb.toString());
            }
            return -1;
        }
        boolean z = bArr != null && i > JPEG_EXIF_SEGMENT_PREAMBLE_BYTES.length;
        if (z) {
            int i2 = 0;
            while (true) {
                byte[] bArr2 = JPEG_EXIF_SEGMENT_PREAMBLE_BYTES;
                if (i2 >= bArr2.length) {
                    break;
                } else if (bArr[i2] != bArr2[i2]) {
                    z = false;
                    break;
                } else {
                    i2++;
                }
            }
        }
        if (z) {
            RandomAccessReader randomAccessReader = new RandomAccessReader(bArr, i);
            short int16 = randomAccessReader.getInt16(6);
            if (int16 == 18761) {
                byteOrder = ByteOrder.LITTLE_ENDIAN;
            } else if (int16 != 19789) {
                if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                    StringBuilder sb2 = new StringBuilder(27);
                    sb2.append("Unknown endianness = ");
                    sb2.append(int16);
                    Log.d("DfltImageHeaderParser", sb2.toString());
                }
                byteOrder = ByteOrder.BIG_ENDIAN;
            } else {
                byteOrder = ByteOrder.BIG_ENDIAN;
            }
            randomAccessReader.order(byteOrder);
            int int32 = randomAccessReader.getInt32(10) + 6;
            short int162 = randomAccessReader.getInt16(int32);
            for (int i3 = 0; i3 < int162; i3++) {
                int i4 = (i3 * 12) + int32 + 2;
                short int163 = randomAccessReader.getInt16(i4);
                if (int163 == 274) {
                    short int164 = randomAccessReader.getInt16(i4 + 2);
                    if (int164 >= 1 && int164 <= 12) {
                        int int322 = randomAccessReader.getInt32(i4 + 4);
                        if (int322 >= 0) {
                            if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                                StringBuilder sb3 = new StringBuilder(94);
                                sb3.append("Got tagIndex=");
                                sb3.append(i3);
                                sb3.append(" tagType=");
                                sb3.append(int163);
                                sb3.append(" formatCode=");
                                sb3.append(int164);
                                sb3.append(" componentCount=");
                                sb3.append(int322);
                                Log.d("DfltImageHeaderParser", sb3.toString());
                            }
                            int i5 = int322 + BYTES_PER_FORMAT[int164];
                            if (i5 <= 4) {
                                int i6 = i4 + 8;
                                if (i6 < 0 || i6 > randomAccessReader.length()) {
                                    if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                                        StringBuilder sb4 = new StringBuilder(54);
                                        sb4.append("Illegal tagValueOffset=");
                                        sb4.append(i6);
                                        sb4.append(" tagType=");
                                        sb4.append(int163);
                                        Log.d("DfltImageHeaderParser", sb4.toString());
                                    }
                                } else if (i5 >= 0 && i5 + i6 <= randomAccessReader.length()) {
                                    return randomAccessReader.getInt16(i6);
                                } else {
                                    if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                                        StringBuilder sb5 = new StringBuilder(59);
                                        sb5.append("Illegal number of bytes for TI tag data tagType=");
                                        sb5.append(int163);
                                        Log.d("DfltImageHeaderParser", sb5.toString());
                                    }
                                }
                            } else if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                                StringBuilder sb6 = new StringBuilder(71);
                                sb6.append("Got byte count > 4, not orientation, continuing, formatCode=");
                                sb6.append(int164);
                                Log.d("DfltImageHeaderParser", sb6.toString());
                            }
                        } else if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                            Log.d("DfltImageHeaderParser", "Negative tiff component count");
                        }
                    } else if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                        StringBuilder sb7 = new StringBuilder(37);
                        sb7.append("Got invalid format code = ");
                        sb7.append(int164);
                        Log.d("DfltImageHeaderParser", sb7.toString());
                    }
                }
            }
            return -1;
        }
        if (Log.isLoggable("DfltImageHeaderParser", 3)) {
            Log.d("DfltImageHeaderParser", "Missing jpeg exif preamble");
        }
        return -1;
    }

    public int getOrientation(InputStream inputStream, ArrayPool arrayPool) throws IOException {
        int i;
        R$style.checkNotNull(inputStream, "Argument must not be null");
        StreamReader streamReader = new StreamReader(inputStream);
        R$style.checkNotNull(arrayPool, "Argument must not be null");
        int uInt16 = streamReader.getUInt16();
        int i2 = -1;
        if ((uInt16 & 65496) == 65496 || uInt16 == 19789 || uInt16 == 18761) {
            while (true) {
                short uInt8 = streamReader.getUInt8();
                if (uInt8 == 255) {
                    short uInt82 = streamReader.getUInt8();
                    if (uInt82 != 218) {
                        if (uInt82 != 217) {
                            i = streamReader.getUInt16() - 2;
                            if (uInt82 == 225) {
                                break;
                            }
                            long j = (long) i;
                            long skip = streamReader.skip(j);
                            if (skip != j) {
                                if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                                    StringBuilder sb = new StringBuilder(113);
                                    sb.append("Unable to skip enough data, type: ");
                                    sb.append(uInt82);
                                    sb.append(", wanted to skip: ");
                                    sb.append(i);
                                    sb.append(", but actually skipped: ");
                                    sb.append(skip);
                                    Log.d("DfltImageHeaderParser", sb.toString());
                                }
                            }
                        } else if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                            Log.d("DfltImageHeaderParser", "Found MARKER_EOI in exif segment");
                        }
                    } else {
                        break;
                    }
                } else if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                    StringBuilder sb2 = new StringBuilder(24);
                    sb2.append("Unknown segmentId=");
                    sb2.append(uInt8);
                    Log.d("DfltImageHeaderParser", sb2.toString());
                }
            }
            i = -1;
            if (i != -1) {
                byte[] bArr = (byte[]) arrayPool.get(i, byte[].class);
                try {
                    i2 = parseExifSegment(streamReader, bArr, i);
                } finally {
                    arrayPool.put(bArr);
                }
            } else if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                Log.d("DfltImageHeaderParser", "Failed to parse exif segment length, or exif segment not found");
            }
        } else if (Log.isLoggable("DfltImageHeaderParser", 3)) {
            StringBuilder sb3 = new StringBuilder(47);
            sb3.append("Parser doesn't handle magic number: ");
            sb3.append(uInt16);
            Log.d("DfltImageHeaderParser", sb3.toString());
        }
        return i2;
    }

    public ImageHeaderParser.ImageType getType(InputStream inputStream) throws IOException {
        R$style.checkNotNull(inputStream, "Argument must not be null");
        return getType((Reader) new StreamReader(inputStream));
    }

    public ImageHeaderParser.ImageType getType(ByteBuffer byteBuffer) throws IOException {
        R$style.checkNotNull(byteBuffer, "Argument must not be null");
        return getType((Reader) new ByteBufferReader(byteBuffer));
    }

    private ImageHeaderParser.ImageType getType(Reader reader) throws IOException {
        int uInt16 = reader.getUInt16();
        if (uInt16 == 65496) {
            return ImageHeaderParser.ImageType.JPEG;
        }
        int uInt162 = ((uInt16 << 16) & -65536) | (reader.getUInt16() & 65535);
        if (uInt162 == -1991225785) {
            reader.skip(21);
            return reader.getByte() >= 3 ? ImageHeaderParser.ImageType.PNG_A : ImageHeaderParser.ImageType.PNG;
        } else if ((uInt162 >> 8) == 4671814) {
            return ImageHeaderParser.ImageType.GIF;
        } else {
            if (uInt162 != 1380533830) {
                return ImageHeaderParser.ImageType.UNKNOWN;
            }
            reader.skip(4);
            if ((((reader.getUInt16() << 16) & -65536) | (reader.getUInt16() & 65535)) != 1464156752) {
                return ImageHeaderParser.ImageType.UNKNOWN;
            }
            int uInt163 = ((reader.getUInt16() << 16) & -65536) | (reader.getUInt16() & 65535);
            if ((uInt163 & -256) != 1448097792) {
                return ImageHeaderParser.ImageType.UNKNOWN;
            }
            int i = uInt163 & 255;
            if (i == 88) {
                reader.skip(4);
                return (reader.getByte() & 16) != 0 ? ImageHeaderParser.ImageType.WEBP_A : ImageHeaderParser.ImageType.WEBP;
            } else if (i != 76) {
                return ImageHeaderParser.ImageType.WEBP;
            } else {
                reader.skip(4);
                return (reader.getByte() & 8) != 0 ? ImageHeaderParser.ImageType.WEBP_A : ImageHeaderParser.ImageType.WEBP;
            }
        }
    }
}
