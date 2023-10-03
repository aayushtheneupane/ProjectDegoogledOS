package com.android.settingslib.media;

import android.app.Notification;
import android.content.Context;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class MediaManager {
    protected final Collection<MediaDeviceCallback> mCallbacks = new ArrayList();
    protected Context mContext;
    protected final List<MediaDevice> mMediaDevices = new ArrayList();
    protected Notification mNotification;

    public interface MediaDeviceCallback {
        void onConnectedDeviceChanged(String str);

        void onDeviceAdded(MediaDevice mediaDevice);

        void onDeviceAttributesChanged();

        void onDeviceListAdded(List<MediaDevice> list);

        void onDeviceListRemoved(List<MediaDevice> list);

        void onDeviceRemoved(MediaDevice mediaDevice);
    }

    MediaManager(Context context, Notification notification) {
        this.mContext = context;
        this.mNotification = notification;
    }

    /* access modifiers changed from: protected */
    public void registerCallback(MediaDeviceCallback mediaDeviceCallback) {
        synchronized (this.mCallbacks) {
            if (!this.mCallbacks.contains(mediaDeviceCallback)) {
                this.mCallbacks.add(mediaDeviceCallback);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void unregisterCallback(MediaDeviceCallback mediaDeviceCallback) {
        synchronized (this.mCallbacks) {
            if (this.mCallbacks.contains(mediaDeviceCallback)) {
                this.mCallbacks.remove(mediaDeviceCallback);
            }
        }
    }

    /* access modifiers changed from: protected */
    public MediaDevice findMediaDevice(String str) {
        for (MediaDevice next : this.mMediaDevices) {
            if (next.getId().equals(str)) {
                return next;
            }
        }
        Log.e("MediaManager", "findMediaDevice() can't found device");
        return null;
    }

    /* access modifiers changed from: protected */
    public void dispatchDeviceAdded(MediaDevice mediaDevice) {
        synchronized (this.mCallbacks) {
            for (MediaDeviceCallback onDeviceAdded : this.mCallbacks) {
                onDeviceAdded.onDeviceAdded(mediaDevice);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchDeviceRemoved(MediaDevice mediaDevice) {
        synchronized (this.mCallbacks) {
            for (MediaDeviceCallback onDeviceRemoved : this.mCallbacks) {
                onDeviceRemoved.onDeviceRemoved(mediaDevice);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchDeviceListAdded() {
        synchronized (this.mCallbacks) {
            for (MediaDeviceCallback onDeviceListAdded : this.mCallbacks) {
                onDeviceListAdded.onDeviceListAdded(new ArrayList(this.mMediaDevices));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchDeviceListRemoved(List<MediaDevice> list) {
        synchronized (this.mCallbacks) {
            for (MediaDeviceCallback onDeviceListRemoved : this.mCallbacks) {
                onDeviceListRemoved.onDeviceListRemoved(list);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchConnectedDeviceChanged(String str) {
        synchronized (this.mCallbacks) {
            for (MediaDeviceCallback onConnectedDeviceChanged : this.mCallbacks) {
                onConnectedDeviceChanged.onConnectedDeviceChanged(str);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchDataChanged() {
        synchronized (this.mCallbacks) {
            for (MediaDeviceCallback onDeviceAttributesChanged : this.mCallbacks) {
                onDeviceAttributesChanged.onDeviceAttributesChanged();
            }
        }
    }
}
