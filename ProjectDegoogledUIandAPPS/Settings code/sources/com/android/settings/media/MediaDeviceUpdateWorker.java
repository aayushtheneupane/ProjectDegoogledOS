package com.android.settings.media;

import android.app.Notification;
import android.content.Context;
import android.net.Uri;
import com.android.settings.slices.SliceBackgroundWorker;
import com.android.settingslib.media.LocalMediaManager;
import com.android.settingslib.media.MediaDevice;
import com.android.settingslib.utils.ThreadUtils;
import java.util.ArrayList;
import java.util.List;

public class MediaDeviceUpdateWorker extends SliceBackgroundWorker implements LocalMediaManager.DeviceCallback {
    private final Context mContext;
    LocalMediaManager mLocalMediaManager;
    private final List<MediaDevice> mMediaDevices = new ArrayList();
    private String mPackageName;

    public MediaDeviceUpdateWorker(Context context, Uri uri) {
        super(context, uri);
        this.mContext = context;
    }

    public void setPackageName(String str) {
        this.mPackageName = str;
    }

    /* access modifiers changed from: protected */
    public void onSlicePinned() {
        this.mMediaDevices.clear();
        if (this.mLocalMediaManager == null) {
            this.mLocalMediaManager = new LocalMediaManager(this.mContext, this.mPackageName, (Notification) null);
        }
        this.mLocalMediaManager.registerCallback(this);
        this.mLocalMediaManager.startScan();
    }

    /* access modifiers changed from: protected */
    public void onSliceUnpinned() {
        this.mLocalMediaManager.unregisterCallback(this);
        this.mLocalMediaManager.stopScan();
    }

    public void close() {
        this.mLocalMediaManager = null;
    }

    public void onDeviceListUpdate(List<MediaDevice> list) {
        buildMediaDevices(list);
        notifySliceChange();
    }

    private void buildMediaDevices(List<MediaDevice> list) {
        this.mMediaDevices.clear();
        this.mMediaDevices.addAll(list);
    }

    public void onSelectedDeviceStateChanged(MediaDevice mediaDevice, int i) {
        notifySliceChange();
    }

    public List<MediaDevice> getMediaDevices() {
        return new ArrayList(this.mMediaDevices);
    }

    public void connectDevice(MediaDevice mediaDevice) {
        ThreadUtils.postOnBackgroundThread((Runnable) new Runnable(mediaDevice) {
            private final /* synthetic */ MediaDevice f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                MediaDeviceUpdateWorker.this.lambda$connectDevice$0$MediaDeviceUpdateWorker(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$connectDevice$0$MediaDeviceUpdateWorker(MediaDevice mediaDevice) {
        this.mLocalMediaManager.connectDevice(mediaDevice);
    }

    public MediaDevice getMediaDeviceById(String str) {
        return this.mLocalMediaManager.getMediaDeviceById(this.mMediaDevices, str);
    }

    public MediaDevice getCurrentConnectedMediaDevice() {
        return this.mLocalMediaManager.getCurrentConnectedDevice();
    }
}
