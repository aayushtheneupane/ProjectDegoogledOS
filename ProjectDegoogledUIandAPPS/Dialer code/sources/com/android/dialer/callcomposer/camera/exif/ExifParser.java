package com.android.dialer.callcomposer.camera.exif;

import android.annotation.SuppressLint;
import com.android.dialer.common.LogUtil;
import com.android.dialer.searchfragment.list.NewSearchFragment;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.TreeMap;

public class ExifParser {
    private static final short TAG_EXIF_IFD = ((short) ExifInterface.TAG_EXIF_IFD);
    private static final short TAG_GPS_IFD = ((short) ExifInterface.TAG_GPS_IFD);
    private static final short TAG_INTEROPERABILITY_IFD = ((short) ExifInterface.TAG_INTEROPERABILITY_IFD);
    private static final short TAG_JPEG_INTERCHANGE_FORMAT = ((short) ExifInterface.TAG_JPEG_INTERCHANGE_FORMAT);
    private static final short TAG_JPEG_INTERCHANGE_FORMAT_LENGTH = ((short) ExifInterface.TAG_JPEG_INTERCHANGE_FORMAT_LENGTH);
    private static final short TAG_STRIP_BYTE_COUNTS = ((short) ExifInterface.TAG_STRIP_BYTE_COUNTS);
    private static final short TAG_STRIP_OFFSETS = ((short) ExifInterface.TAG_STRIP_OFFSETS);
    private static final Charset US_ASCII = Charset.forName("US-ASCII");
    private int app1End;
    private boolean containExifData = false;
    private final TreeMap<Integer, Object> correspondingEvent = new TreeMap<>();
    private byte[] dataAboveIfd0;
    private int ifd0Position;
    private int ifdStartOffset = 0;
    private int ifdType;
    private ImageEvent imageEvent;
    private ExifTag jpegSizeTag;
    private final ExifInterface mInterface;
    private boolean needToParseOffsetsInCurrentIfd;
    private int numOfTagInIfd = 0;
    private final int options;
    private ExifTag stripSizeTag;
    private ExifTag tag;
    private final CountedDataInputStream tiffStream;

    private static class ExifTagEvent {
        boolean isRequested;
        ExifTag tag;

        ExifTagEvent(ExifTag exifTag, boolean z) {
            this.tag = exifTag;
            this.isRequested = z;
        }
    }

    private static class IfdEvent {
        int ifd;
        boolean isRequested;

        IfdEvent(int i, boolean z) {
            this.ifd = i;
            this.isRequested = z;
        }
    }

    private ExifParser(InputStream inputStream, int i, ExifInterface exifInterface) throws IOException, ExifInvalidFormatException {
        boolean z;
        if (inputStream != null) {
            this.mInterface = exifInterface;
            CountedDataInputStream countedDataInputStream = new CountedDataInputStream(inputStream);
            if (countedDataInputStream.readShort() == -40) {
                short readShort = countedDataInputStream.readShort();
                while (true) {
                    if (readShort == -39) {
                        break;
                    }
                    z = true;
                    if ((readShort < -64 || readShort > -49 || readShort == -60 || readShort == -56 || readShort == -52) ? false : true) {
                        break;
                    }
                    int readUnsignedShort = countedDataInputStream.readUnsignedShort();
                    if (readShort == -31 && readUnsignedShort >= 8) {
                        int readInt = countedDataInputStream.readInt();
                        short readShort2 = countedDataInputStream.readShort();
                        readUnsignedShort -= 6;
                        if (readInt == 1165519206 && readShort2 == 0) {
                            this.app1End = readUnsignedShort;
                            break;
                        }
                    }
                    if (readUnsignedShort < 2) {
                        break;
                    }
                    long j = (long) (readUnsignedShort - 2);
                    if (j != countedDataInputStream.skip(j)) {
                        break;
                    }
                    readShort = countedDataInputStream.readShort();
                }
                LogUtil.m9i("ExifParser.seekTiffData", "Invalid JPEG format.", new Object[0]);
                z = false;
                this.containExifData = z;
                this.tiffStream = new CountedDataInputStream(inputStream);
                this.options = i;
                if (this.containExifData) {
                    short readShort3 = this.tiffStream.readShort();
                    if (18761 == readShort3) {
                        this.tiffStream.setByteOrder(ByteOrder.LITTLE_ENDIAN);
                    } else if (19789 == readShort3) {
                        this.tiffStream.setByteOrder(ByteOrder.BIG_ENDIAN);
                    } else {
                        throw new ExifInvalidFormatException("Invalid TIFF header");
                    }
                    if (this.tiffStream.readShort() == 42) {
                        long readUnsignedInt = this.tiffStream.readUnsignedInt();
                        if (readUnsignedInt <= 2147483647L) {
                            int i2 = (int) readUnsignedInt;
                            this.ifd0Position = i2;
                            this.ifdType = 0;
                            if (isIfdRequested(0) || needToParseOffsetsInCurrentIfd()) {
                                registerIfd(0, readUnsignedInt);
                                if (readUnsignedInt != 8) {
                                    this.dataAboveIfd0 = new byte[(i2 - 8)];
                                    read(this.dataAboveIfd0);
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                        throw new ExifInvalidFormatException("Invalid offset " + readUnsignedInt);
                    }
                    throw new ExifInvalidFormatException("Invalid TIFF header");
                }
                return;
            }
            throw new ExifInvalidFormatException("Invalid JPEG format");
        }
        throw new IOException("Null argument inputStream to ExifParser");
    }

    private boolean checkAllowed(int i, int i2) {
        boolean z;
        int i3 = this.mInterface.getTagInfo().get(i2);
        if (i3 == 0) {
            return false;
        }
        int[] ifds = IfdData.getIfds();
        int i4 = i3 >>> 24;
        int i5 = 0;
        while (true) {
            if (i5 < ifds.length) {
                if (i == ifds[i5] && ((i4 >> i5) & 1) == 1) {
                    z = true;
                    break;
                }
                i5++;
            } else {
                z = false;
                break;
            }
        }
        if (z) {
            return true;
        }
        return false;
    }

    private void checkOffsetOrImageTag(ExifTag exifTag) {
        if (exifTag.getComponentCount() != 0) {
            short tagId = exifTag.getTagId();
            int ifd = exifTag.getIfd();
            if (tagId != TAG_EXIF_IFD || !checkAllowed(ifd, ExifInterface.TAG_EXIF_IFD)) {
                if (tagId != TAG_GPS_IFD || !checkAllowed(ifd, ExifInterface.TAG_GPS_IFD)) {
                    if (tagId != TAG_INTEROPERABILITY_IFD || !checkAllowed(ifd, ExifInterface.TAG_INTEROPERABILITY_IFD)) {
                        if (tagId != TAG_JPEG_INTERCHANGE_FORMAT || !checkAllowed(ifd, ExifInterface.TAG_JPEG_INTERCHANGE_FORMAT)) {
                            if (tagId != TAG_JPEG_INTERCHANGE_FORMAT_LENGTH || !checkAllowed(ifd, ExifInterface.TAG_JPEG_INTERCHANGE_FORMAT_LENGTH)) {
                                if (tagId != TAG_STRIP_OFFSETS || !checkAllowed(ifd, ExifInterface.TAG_STRIP_OFFSETS)) {
                                    if (tagId == TAG_STRIP_BYTE_COUNTS && checkAllowed(ifd, ExifInterface.TAG_STRIP_BYTE_COUNTS) && isThumbnailRequested() && exifTag.hasValue()) {
                                        this.stripSizeTag = exifTag;
                                    }
                                } else if (!isThumbnailRequested()) {
                                } else {
                                    if (exifTag.hasValue()) {
                                        for (int i = 0; i < exifTag.getComponentCount(); i++) {
                                            if (exifTag.getDataType() == 3) {
                                                this.correspondingEvent.put(Integer.valueOf((int) exifTag.getValueAt(i)), new ImageEvent(4, i));
                                            } else {
                                                this.correspondingEvent.put(Integer.valueOf((int) exifTag.getValueAt(i)), new ImageEvent(4, i));
                                            }
                                        }
                                        return;
                                    }
                                    this.correspondingEvent.put(Integer.valueOf(exifTag.getOffset()), new ExifTagEvent(exifTag, false));
                                }
                            } else if (isThumbnailRequested()) {
                                this.jpegSizeTag = exifTag;
                            }
                        } else if (isThumbnailRequested()) {
                            this.correspondingEvent.put(Integer.valueOf((int) exifTag.getValueAt(0)), new ImageEvent(3));
                        }
                    } else if (isIfdRequested(3)) {
                        registerIfd(3, exifTag.getValueAt(0));
                    }
                } else if (isIfdRequested(4)) {
                    registerIfd(4, exifTag.getValueAt(0));
                }
            } else if (isIfdRequested(2) || isIfdRequested(3)) {
                registerIfd(2, exifTag.getValueAt(0));
            }
        }
    }

    private boolean isIfdRequested(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return i == 4 && (this.options & 8) != 0;
                    }
                    if ((this.options & 16) != 0) {
                        return true;
                    }
                    return false;
                } else if ((this.options & 4) != 0) {
                    return true;
                } else {
                    return false;
                }
            } else if ((this.options & 2) != 0) {
                return true;
            } else {
                return false;
            }
        } else if ((this.options & 1) != 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isThumbnailRequested() {
        return (this.options & 32) != 0;
    }

    private boolean needToParseOffsetsInCurrentIfd() {
        int i = this.ifdType;
        if (i != 0) {
            if (i == 1) {
                return isThumbnailRequested();
            }
            if (i != 2) {
                return false;
            }
            return isIfdRequested(3);
        } else if (isIfdRequested(2) || isIfdRequested(4) || isIfdRequested(3) || isIfdRequested(1)) {
            return true;
        } else {
            return false;
        }
    }

    protected static ExifParser parse(InputStream inputStream, ExifInterface exifInterface) throws IOException, ExifInvalidFormatException {
        return new ExifParser(inputStream, 63, exifInterface);
    }

    private int readLong() throws IOException {
        return this.tiffStream.readInt();
    }

    @SuppressLint({"DefaultLocale"})
    private ExifTag readTag() throws IOException, ExifInvalidFormatException {
        short readShort = this.tiffStream.readShort();
        short readShort2 = this.tiffStream.readShort();
        long readUnsignedInt = this.tiffStream.readUnsignedInt();
        if (readUnsignedInt > 2147483647L) {
            throw new ExifInvalidFormatException("Number of component is larger then Integer.MAX_VALUE");
        } else if (!ExifTag.isValidType(readShort2)) {
            LogUtil.m9i("ExifParser.readTag", "Tag %04x: Invalid data type %d", Short.valueOf(readShort), Short.valueOf(readShort2));
            this.tiffStream.skip(4);
            return null;
        } else {
            int i = (int) readUnsignedInt;
            ExifTag exifTag = new ExifTag(readShort, readShort2, i, this.ifdType, i != 0);
            int dataSize = exifTag.getDataSize();
            if (dataSize > 4) {
                long readUnsignedInt2 = this.tiffStream.readUnsignedInt();
                if (readUnsignedInt2 > 2147483647L) {
                    throw new ExifInvalidFormatException("offset is larger then Integer.MAX_VALUE");
                } else if (readUnsignedInt2 >= ((long) this.ifd0Position) || readShort2 != 7) {
                    exifTag.setOffset((int) readUnsignedInt2);
                } else {
                    byte[] bArr = new byte[i];
                    System.arraycopy(this.dataAboveIfd0, ((int) readUnsignedInt2) - 8, bArr, 0, i);
                    exifTag.setValue(bArr);
                }
            } else {
                boolean hasDefinedCount = exifTag.hasDefinedCount();
                exifTag.setHasDefinedCount(false);
                readFullTagValue(exifTag);
                exifTag.setHasDefinedCount(hasDefinedCount);
                this.tiffStream.skip((long) (4 - dataSize));
                exifTag.setOffset(this.tiffStream.getReadByteCount() - 4);
            }
            return exifTag;
        }
    }

    private long readUnsignedLong() throws IOException {
        return ((long) readLong()) & 4294967295L;
    }

    private void registerIfd(int i, long j) {
        this.correspondingEvent.put(Integer.valueOf((int) j), new IfdEvent(i, isIfdRequested(i)));
    }

    private void skipTo(int i) throws IOException {
        this.tiffStream.skipTo((long) i);
        while (!this.correspondingEvent.isEmpty() && this.correspondingEvent.firstKey().intValue() < i) {
            this.correspondingEvent.pollFirstEntry();
        }
    }

    /* access modifiers changed from: package-private */
    public int getCompressedImageSize() {
        ExifTag exifTag = this.jpegSizeTag;
        if (exifTag == null) {
            return 0;
        }
        return (int) exifTag.getValueAt(0);
    }

    /* access modifiers changed from: package-private */
    public int getCurrentIfd() {
        return this.ifdType;
    }

    /* access modifiers changed from: package-private */
    public int getStripSize() {
        ExifTag exifTag = this.stripSizeTag;
        if (exifTag == null) {
            return 0;
        }
        return (int) exifTag.getValueAt(0);
    }

    /* access modifiers changed from: protected */
    public ExifTag getTag() {
        return this.tag;
    }

    /* access modifiers changed from: protected */
    public int next() throws IOException, ExifInvalidFormatException {
        if (!this.containExifData) {
            return 5;
        }
        int readByteCount = this.tiffStream.getReadByteCount();
        int i = (this.numOfTagInIfd * 12) + this.ifdStartOffset + 2;
        if (readByteCount < i) {
            this.tag = readTag();
            ExifTag exifTag = this.tag;
            if (exifTag == null) {
                return next();
            }
            if (this.needToParseOffsetsInCurrentIfd) {
                checkOffsetOrImageTag(exifTag);
            }
            return 1;
        }
        if (readByteCount == i) {
            if (this.ifdType == 0) {
                long readUnsignedLong = readUnsignedLong();
                if ((isIfdRequested(1) || isThumbnailRequested()) && readUnsignedLong != 0) {
                    registerIfd(1, readUnsignedLong);
                }
            } else {
                int intValue = this.correspondingEvent.size() > 0 ? this.correspondingEvent.firstEntry().getKey().intValue() - this.tiffStream.getReadByteCount() : 4;
                if (intValue < 4) {
                    LogUtil.m9i("ExifParser.next", GeneratedOutlineSupport.outline5("Invalid size of link to next IFD: ", intValue), new Object[0]);
                } else {
                    long readUnsignedLong2 = readUnsignedLong();
                    if (readUnsignedLong2 != 0) {
                        LogUtil.m9i("ExifParser.next", "Invalid link to next IFD: " + readUnsignedLong2, new Object[0]);
                    }
                }
            }
        }
        while (this.correspondingEvent.size() != 0) {
            Map.Entry<Integer, Object> pollFirstEntry = this.correspondingEvent.pollFirstEntry();
            Object value = pollFirstEntry.getValue();
            try {
                skipTo(pollFirstEntry.getKey().intValue());
                if (value instanceof IfdEvent) {
                    IfdEvent ifdEvent = (IfdEvent) value;
                    this.ifdType = ifdEvent.ifd;
                    this.numOfTagInIfd = this.tiffStream.readShort() & 65535;
                    this.ifdStartOffset = pollFirstEntry.getKey().intValue();
                    if ((this.numOfTagInIfd * 12) + this.ifdStartOffset + 2 > this.app1End) {
                        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Invalid size of IFD ");
                        outline13.append(this.ifdType);
                        LogUtil.m9i("ExifParser.next", outline13.toString(), new Object[0]);
                        return 5;
                    }
                    this.needToParseOffsetsInCurrentIfd = needToParseOffsetsInCurrentIfd();
                    if (ifdEvent.isRequested) {
                        return 0;
                    }
                    int i2 = (this.numOfTagInIfd * 12) + this.ifdStartOffset + 2;
                    int readByteCount2 = this.tiffStream.getReadByteCount();
                    if (readByteCount2 <= i2) {
                        if (this.needToParseOffsetsInCurrentIfd) {
                            while (readByteCount2 < i2) {
                                this.tag = readTag();
                                readByteCount2 += 12;
                                ExifTag exifTag2 = this.tag;
                                if (exifTag2 != null) {
                                    checkOffsetOrImageTag(exifTag2);
                                }
                            }
                        } else {
                            skipTo(i2);
                        }
                        long readUnsignedLong3 = readUnsignedLong();
                        if (this.ifdType == 0 && ((isIfdRequested(1) || isThumbnailRequested()) && readUnsignedLong3 > 0)) {
                            registerIfd(1, readUnsignedLong3);
                        }
                    }
                } else if (value instanceof ImageEvent) {
                    this.imageEvent = (ImageEvent) value;
                    return this.imageEvent.type;
                } else {
                    ExifTagEvent exifTagEvent = (ExifTagEvent) value;
                    this.tag = exifTagEvent.tag;
                    if (this.tag.getDataType() != 7) {
                        readFullTagValue(this.tag);
                        checkOffsetOrImageTag(this.tag);
                    }
                    if (exifTagEvent.isRequested) {
                        return 2;
                    }
                }
            } catch (IOException unused) {
                StringBuilder outline132 = GeneratedOutlineSupport.outline13("Failed to skip to data at: ");
                outline132.append(pollFirstEntry.getKey());
                outline132.append(" for ");
                outline132.append(value.getClass().getName());
                outline132.append(", the file may be broken.");
                LogUtil.m9i("ExifParser.next", outline132.toString(), new Object[0]);
            }
        }
        return 5;
    }

    /* access modifiers changed from: protected */
    public int read(byte[] bArr) throws IOException {
        return this.tiffStream.read(bArr);
    }

    /* access modifiers changed from: package-private */
    public void readFullTagValue(ExifTag exifTag) throws IOException {
        short dataType = exifTag.getDataType();
        int i = 0;
        if (dataType == 2 || dataType == 7 || dataType == 1) {
            int componentCount = exifTag.getComponentCount();
            if (this.correspondingEvent.size() > 0 && this.correspondingEvent.firstEntry().getKey().intValue() < this.tiffStream.getReadByteCount() + componentCount) {
                Object value = this.correspondingEvent.firstEntry().getValue();
                if (value instanceof ImageEvent) {
                    StringBuilder outline13 = GeneratedOutlineSupport.outline13("Thumbnail overlaps value for tag: \n");
                    outline13.append(exifTag.toString());
                    LogUtil.m9i("ExifParser.readFullTagValue", outline13.toString(), new Object[0]);
                    Map.Entry<Integer, Object> pollFirstEntry = this.correspondingEvent.pollFirstEntry();
                    StringBuilder outline132 = GeneratedOutlineSupport.outline13("Invalid thumbnail offset: ");
                    outline132.append(pollFirstEntry.getKey());
                    LogUtil.m9i("ExifParser.readFullTagValue", outline132.toString(), new Object[0]);
                } else {
                    if (value instanceof IfdEvent) {
                        StringBuilder outline133 = GeneratedOutlineSupport.outline13("Ifd ");
                        outline133.append(((IfdEvent) value).ifd);
                        outline133.append(" overlaps value for tag: \n");
                        outline133.append(exifTag.toString());
                        LogUtil.m9i("ExifParser.readFullTagValue", outline133.toString(), new Object[0]);
                    } else if (value instanceof ExifTagEvent) {
                        StringBuilder outline134 = GeneratedOutlineSupport.outline13("Tag value for tag: \n");
                        outline134.append(((ExifTagEvent) value).tag.toString());
                        outline134.append(" overlaps value for tag: \n");
                        outline134.append(exifTag.toString());
                        LogUtil.m9i("ExifParser.readFullTagValue", outline134.toString(), new Object[0]);
                    }
                    int intValue = this.correspondingEvent.firstEntry().getKey().intValue() - this.tiffStream.getReadByteCount();
                    StringBuilder outline135 = GeneratedOutlineSupport.outline13("Invalid size of tag: \n");
                    outline135.append(exifTag.toString());
                    outline135.append(" setting count to: ");
                    outline135.append(intValue);
                    LogUtil.m9i("ExifParser.readFullTagValue", outline135.toString(), new Object[0]);
                    exifTag.forceSetComponentCount(intValue);
                }
            }
        }
        switch (exifTag.getDataType()) {
            case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE /*1*/:
            case 7:
                byte[] bArr = new byte[exifTag.getComponentCount()];
                this.tiffStream.read(bArr);
                exifTag.setValue(bArr);
                return;
            case 2:
                int componentCount2 = exifTag.getComponentCount();
                exifTag.setValue(componentCount2 > 0 ? this.tiffStream.readString(componentCount2, US_ASCII) : "");
                return;
            case 3:
                int[] iArr = new int[exifTag.getComponentCount()];
                int length = iArr.length;
                while (i < length) {
                    iArr[i] = this.tiffStream.readShort() & 65535;
                    i++;
                }
                exifTag.setValue(iArr);
                return;
            case 4:
                long[] jArr = new long[exifTag.getComponentCount()];
                int length2 = jArr.length;
                while (i < length2) {
                    jArr[i] = readUnsignedLong();
                    i++;
                }
                exifTag.setValue(jArr);
                return;
            case 5:
                Rational[] rationalArr = new Rational[exifTag.getComponentCount()];
                int length3 = rationalArr.length;
                while (i < length3) {
                    rationalArr[i] = new Rational(readUnsignedLong(), readUnsignedLong());
                    i++;
                }
                exifTag.setValue(rationalArr);
                return;
            case 9:
                int[] iArr2 = new int[exifTag.getComponentCount()];
                int length4 = iArr2.length;
                while (i < length4) {
                    iArr2[i] = readLong();
                    i++;
                }
                exifTag.setValue(iArr2);
                return;
            case 10:
                Rational[] rationalArr2 = new Rational[exifTag.getComponentCount()];
                int length5 = rationalArr2.length;
                while (i < length5) {
                    rationalArr2[i] = new Rational((long) readLong(), (long) readLong());
                    i++;
                }
                exifTag.setValue(rationalArr2);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: package-private */
    public void registerForTagValue(ExifTag exifTag) {
        if (exifTag.getOffset() >= this.tiffStream.getReadByteCount()) {
            this.correspondingEvent.put(Integer.valueOf(exifTag.getOffset()), new ExifTagEvent(exifTag, true));
        }
    }

    private static class ImageEvent {
        int type;

        ImageEvent(int i) {
            this.type = i;
        }

        ImageEvent(int i, int i2) {
            this.type = i;
        }
    }
}
