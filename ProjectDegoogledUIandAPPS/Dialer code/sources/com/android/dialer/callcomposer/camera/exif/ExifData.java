package com.android.dialer.callcomposer.camera.exif;

public class ExifData {
    private final IfdData[] ifdDatas = new IfdData[5];

    /* access modifiers changed from: package-private */
    public void addIfdData(IfdData ifdData) {
        this.ifdDatas[ifdData.getId()] = ifdData;
    }

    /* access modifiers changed from: package-private */
    public IfdData getIfdData(int i) {
        if (ExifTag.isValidIfd(i)) {
            return this.ifdDatas[i];
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public ExifTag getTag(short s, int i) {
        IfdData ifdData = this.ifdDatas[i];
        if (ifdData == null) {
            return null;
        }
        return ifdData.getTag(s);
    }
}
