package com.android.dialer.callcomposer.camera.exif;

import android.annotation.SuppressLint;
import android.util.SparseIntArray;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.TimeZone;

public class ExifInterface {
    static final int TAG_EXIF_IFD = defineTag(0, -30871);
    static final int TAG_GPS_IFD = defineTag(0, -30683);
    static final int TAG_INTEROPERABILITY_IFD = defineTag(2, -24571);
    static final int TAG_JPEG_INTERCHANGE_FORMAT = defineTag(1, 513);
    static final int TAG_JPEG_INTERCHANGE_FORMAT_LENGTH = defineTag(1, 514);
    public static final int TAG_ORIENTATION = defineTag(0, 274);
    static final int TAG_STRIP_BYTE_COUNTS = defineTag(0, 279);
    static final int TAG_STRIP_OFFSETS = defineTag(0, 273);
    private static HashSet<Short> offsetTags = new HashSet<>();
    private ExifData data = new ExifData();
    private SparseIntArray tagInfo = null;

    static {
        offsetTags.add(Short.valueOf((short) TAG_GPS_IFD));
        offsetTags.add(Short.valueOf((short) TAG_EXIF_IFD));
        offsetTags.add(Short.valueOf((short) TAG_JPEG_INTERCHANGE_FORMAT));
        offsetTags.add(Short.valueOf((short) TAG_INTEROPERABILITY_IFD));
        offsetTags.add(Short.valueOf((short) TAG_STRIP_OFFSETS));
    }

    @SuppressLint({"SimpleDateFormat"})
    public ExifInterface() {
        new SimpleDateFormat("yyyy:MM:dd").setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    private static int defineTag(int i, short s) {
        return (i << 16) | (s & 65535);
    }

    private static int getFlagsFromAllowedIfds(int[] iArr) {
        if (iArr == null || iArr.length == 0) {
            return 0;
        }
        int[] ifds = IfdData.getIfds();
        int i = 0;
        for (int i2 = 0; i2 < 5; i2++) {
            int length = iArr.length;
            int i3 = 0;
            while (true) {
                if (i3 >= length) {
                    break;
                }
                if (ifds[i2] == iArr[i3]) {
                    i |= 1 << i2;
                    break;
                }
                i3++;
            }
        }
        return i;
    }

    static boolean isOffsetTag(short s) {
        return offsetTags.contains(Short.valueOf(s));
    }

    public void clearExif() {
        this.data = new ExifData();
    }

    /* access modifiers changed from: package-private */
    public SparseIntArray getTagInfo() {
        if (this.tagInfo == null) {
            this.tagInfo = new SparseIntArray();
            int flagsFromAllowedIfds = getFlagsFromAllowedIfds(new int[]{0, 1}) << 24;
            int i = flagsFromAllowedIfds | 262144;
            this.tagInfo.put(TAG_STRIP_OFFSETS, i);
            int i2 = i | 1;
            this.tagInfo.put(TAG_EXIF_IFD, i2);
            this.tagInfo.put(TAG_GPS_IFD, i2);
            this.tagInfo.put(TAG_ORIENTATION, flagsFromAllowedIfds | 196608 | 1);
            this.tagInfo.put(TAG_STRIP_BYTE_COUNTS, i);
            int flagsFromAllowedIfds2 = (getFlagsFromAllowedIfds(new int[]{1}) << 24) | 262144 | 1;
            this.tagInfo.put(TAG_JPEG_INTERCHANGE_FORMAT, flagsFromAllowedIfds2);
            this.tagInfo.put(TAG_JPEG_INTERCHANGE_FORMAT_LENGTH, flagsFromAllowedIfds2);
            this.tagInfo.put(TAG_INTEROPERABILITY_IFD, (getFlagsFromAllowedIfds(new int[]{2}) << 24) | 262144 | 1);
        }
        return this.tagInfo;
    }

    public Integer getTagIntValue(int i) {
        ExifTag exifTag;
        int[] iArr;
        int i2 = getTagInfo().get(i) == 0 ? -1 : i >>> 16;
        if (!ExifTag.isValidIfd(i2)) {
            exifTag = null;
        } else {
            exifTag = this.data.getTag((short) i, i2);
        }
        if (exifTag == null) {
            iArr = null;
        } else {
            iArr = exifTag.getValueAsInts();
        }
        if (iArr == null || iArr.length <= 0) {
            return null;
        }
        return Integer.valueOf(iArr[0]);
    }

    public void readExif(byte[] bArr) throws IOException {
        try {
            this.data = new ExifReader(this).read(new ByteArrayInputStream(bArr));
        } catch (ExifInvalidFormatException e) {
            throw new IOException(GeneratedOutlineSupport.outline6("Invalid exif format : ", e));
        }
    }
}
