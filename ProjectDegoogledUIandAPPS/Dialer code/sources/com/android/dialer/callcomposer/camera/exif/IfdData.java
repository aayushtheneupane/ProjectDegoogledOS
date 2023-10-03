package com.android.dialer.callcomposer.camera.exif;

import android.support.p000v4.util.ArrayMap;
import java.util.Map;
import java.util.Objects;

class IfdData {
    private static final int[] ifds = {0, 1, 2, 3, 4};
    private final Map<Short, ExifTag> exifTags = new ArrayMap();
    private final int ifdId;

    IfdData(int i) {
        this.ifdId = i;
    }

    static int[] getIfds() {
        return ifds;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof IfdData)) {
            IfdData ifdData = (IfdData) obj;
            if (ifdData.ifdId == this.ifdId && ifdData.exifTags.size() == this.exifTags.size()) {
                for (ExifTag exifTag : (ExifTag[]) ifdData.exifTags.values().toArray(new ExifTag[ifdData.exifTags.size()])) {
                    if (!ExifInterface.isOffsetTag(exifTag.getTagId()) && !exifTag.equals(this.exifTags.get(Short.valueOf(exifTag.getTagId())))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public int getId() {
        return this.ifdId;
    }

    /* access modifiers changed from: protected */
    public ExifTag getTag(short s) {
        return this.exifTags.get(Short.valueOf(s));
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(this.ifdId), this.exifTags});
    }

    /* access modifiers changed from: protected */
    public ExifTag setTag(ExifTag exifTag) {
        exifTag.setIfd(this.ifdId);
        return this.exifTags.put(Short.valueOf(exifTag.getTagId()), exifTag);
    }
}
