package com.android.settingslib.media;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

public abstract class MediaDevice implements Comparable<MediaDevice> {
    private int mConnectedRecord;
    protected Context mContext;
    protected int mType;

    public abstract boolean connect();

    public abstract void disconnect();

    public abstract Drawable getIcon();

    public abstract String getId();

    public abstract String getName();

    public abstract String getSummary();

    /* access modifiers changed from: protected */
    public boolean isCarKitDevice() {
        return false;
    }

    public abstract boolean isConnected();

    MediaDevice(Context context, int i) {
        this.mType = i;
        this.mContext = context;
    }

    /* access modifiers changed from: package-private */
    public void initDeviceRecord() {
        ConnectionRecordManager.getInstance().fetchLastSelectedDevice(this.mContext);
        this.mConnectedRecord = ConnectionRecordManager.getInstance().fetchConnectionRecord(this.mContext, getId());
    }

    /* access modifiers changed from: package-private */
    public void setConnectedRecord() {
        this.mConnectedRecord++;
        ConnectionRecordManager.getInstance().setConnectionRecord(this.mContext, getId(), this.mConnectedRecord);
    }

    public int compareTo(MediaDevice mediaDevice) {
        if (isConnected() ^ mediaDevice.isConnected()) {
            return isConnected() ? -1 : 1;
        }
        if (this.mType == 1) {
            return -1;
        }
        if (mediaDevice.mType == 1) {
            return 1;
        }
        if (isCarKitDevice()) {
            return -1;
        }
        if (mediaDevice.isCarKitDevice()) {
            return 1;
        }
        String lastSelectedDevice = ConnectionRecordManager.getInstance().getLastSelectedDevice();
        if (TextUtils.equals(lastSelectedDevice, getId())) {
            return -1;
        }
        if (TextUtils.equals(lastSelectedDevice, mediaDevice.getId())) {
            return 1;
        }
        int i = this.mConnectedRecord;
        int i2 = mediaDevice.mConnectedRecord;
        if (i != i2 && (i2 > 0 || i > 0)) {
            return mediaDevice.mConnectedRecord - this.mConnectedRecord;
        }
        int i3 = this.mType;
        int i4 = mediaDevice.mType;
        return i3 == i4 ? getName().compareToIgnoreCase(mediaDevice.getName()) : i3 - i4;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof MediaDevice)) {
            return false;
        }
        return ((MediaDevice) obj).getId().equals(getId());
    }
}
