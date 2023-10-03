package com.android.settings.media;

import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.graphics.drawable.IconCompat;
import androidx.slice.Slice;
import androidx.slice.builders.ListBuilder;
import androidx.slice.builders.SliceAction;
import com.android.settings.Utils;
import com.android.settings.slices.CustomSliceRegistry;
import com.android.settings.slices.CustomSliceable;
import com.android.settings.slices.SliceBackgroundWorker;
import com.android.settings.slices.SliceBroadcastReceiver;
import com.android.settingslib.media.MediaDevice;
import java.util.List;

public class MediaOutputSlice implements CustomSliceable {
    private final Context mContext;
    private String mPackageName = getUri().getQueryParameter("media_package_name");
    private MediaDeviceUpdateWorker mWorker;

    public Intent getIntent() {
        return null;
    }

    public MediaOutputSlice(Context context) {
        this.mContext = context;
    }

    /* access modifiers changed from: package-private */
    public void init(String str, MediaDeviceUpdateWorker mediaDeviceUpdateWorker) {
        this.mPackageName = str;
        this.mWorker = mediaDeviceUpdateWorker;
    }

    public Slice getSlice() {
        this.mContext.getTheme().applyStyle(2131952299, true);
        ListBuilder listBuilder = new ListBuilder(this.mContext, getUri(), -1);
        listBuilder.setAccentColor(-1);
        if (!isVisible()) {
            Log.d("MediaOutputSlice", "getSlice() is not visible");
            return listBuilder.build();
        }
        List<MediaDevice> mediaDevices = getMediaDevices();
        MediaDevice currentConnectedMediaDevice = getWorker().getCurrentConnectedMediaDevice();
        if (currentConnectedMediaDevice != null) {
            listBuilder.addRow(getActiveDeviceHeaderRow(currentConnectedMediaDevice));
        }
        for (MediaDevice next : mediaDevices) {
            if (currentConnectedMediaDevice == null || !TextUtils.equals(currentConnectedMediaDevice.getId(), next.getId())) {
                listBuilder.addRow(getMediaDeviceRow(next));
            }
        }
        return listBuilder.build();
    }

    private ListBuilder.RowBuilder getActiveDeviceHeaderRow(MediaDevice mediaDevice) {
        String name = mediaDevice.getName();
        IconCompat deviceIconCompat = getDeviceIconCompat(mediaDevice);
        SliceAction createDeeplink = SliceAction.createDeeplink(getBroadcastIntent(this.mContext, mediaDevice.getId(), mediaDevice.hashCode()), deviceIconCompat, 0, name);
        ListBuilder.RowBuilder rowBuilder = new ListBuilder.RowBuilder();
        rowBuilder.setTitleItem(deviceIconCompat, 0);
        rowBuilder.setTitle(name);
        rowBuilder.setSubtitle(mediaDevice.getSummary());
        rowBuilder.setPrimaryAction(createDeeplink);
        return rowBuilder;
    }

    private IconCompat getDeviceIconCompat(MediaDevice mediaDevice) {
        Drawable icon = mediaDevice.getIcon();
        if (icon == null) {
            Log.d("MediaOutputSlice", "getDeviceIconCompat() device : " + mediaDevice.getName() + ", drawable is null");
            icon = this.mContext.getDrawable(17302308);
        }
        return Utils.createIconWithDrawable(icon);
    }

    private MediaDeviceUpdateWorker getWorker() {
        if (this.mWorker == null) {
            this.mWorker = (MediaDeviceUpdateWorker) SliceBackgroundWorker.getInstance(getUri());
            MediaDeviceUpdateWorker mediaDeviceUpdateWorker = this.mWorker;
            if (mediaDeviceUpdateWorker != null) {
                mediaDeviceUpdateWorker.setPackageName(this.mPackageName);
            }
        }
        return this.mWorker;
    }

    private List<MediaDevice> getMediaDevices() {
        return getWorker().getMediaDevices();
    }

    private ListBuilder.RowBuilder getMediaDeviceRow(MediaDevice mediaDevice) {
        String name = mediaDevice.getName();
        PendingIntent broadcastIntent = getBroadcastIntent(this.mContext, mediaDevice.getId(), mediaDevice.hashCode());
        IconCompat deviceIconCompat = getDeviceIconCompat(mediaDevice);
        ListBuilder.RowBuilder rowBuilder = new ListBuilder.RowBuilder();
        rowBuilder.setTitleItem(deviceIconCompat, 0);
        rowBuilder.setPrimaryAction(SliceAction.create(broadcastIntent, deviceIconCompat, 0, name));
        rowBuilder.setTitle(name);
        rowBuilder.setSubtitle(mediaDevice.getSummary());
        return rowBuilder;
    }

    private PendingIntent getBroadcastIntent(Context context, String str, int i) {
        Intent intent = new Intent(getUri().toString());
        intent.setClass(context, SliceBroadcastReceiver.class);
        intent.putExtra("media_device_id", str);
        intent.addFlags(268435456);
        return PendingIntent.getBroadcast(context, i, intent, 268435456);
    }

    public Uri getUri() {
        return CustomSliceRegistry.MEDIA_OUTPUT_SLICE_URI;
    }

    public void onNotifyChange(Intent intent) {
        MediaDeviceUpdateWorker worker = getWorker();
        MediaDevice mediaDeviceById = worker.getMediaDeviceById(intent != null ? intent.getStringExtra("media_device_id") : "");
        if (mediaDeviceById != null) {
            Log.d("MediaOutputSlice", "onNotifyChange() device name : " + mediaDeviceById.getName());
            worker.connectDevice(mediaDeviceById);
        }
    }

    public Class getBackgroundWorkerClass() {
        return MediaDeviceUpdateWorker.class;
    }

    private boolean isVisible() {
        return ((TelephonyManager) this.mContext.getSystemService("phone")).getCallState() == 0 && BluetoothAdapter.getDefaultAdapter().isEnabled() && getWorker() != null;
    }
}
