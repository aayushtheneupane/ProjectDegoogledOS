package com.android.settings.media;

import android.app.PendingIntent;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.util.Log;
import androidx.core.graphics.drawable.IconCompat;
import androidx.slice.Slice;
import androidx.slice.builders.ListBuilder;
import androidx.slice.builders.SliceAction;
import com.android.internal.util.CollectionUtils;
import com.android.settings.bluetooth.Utils;
import com.android.settings.slices.CustomSliceRegistry;
import com.android.settings.slices.CustomSliceable;
import com.android.settingslib.bluetooth.A2dpProfile;
import com.android.settingslib.bluetooth.HearingAidProfile;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.android.settingslib.bluetooth.LocalBluetoothProfileManager;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class MediaOutputIndicatorSlice implements CustomSliceable {
    private Context mContext;
    private LocalBluetoothManager mLocalBluetoothManager;
    private LocalBluetoothProfileManager mProfileManager;

    public Intent getIntent() {
        return null;
    }

    public MediaOutputIndicatorSlice(Context context) {
        this.mContext = context;
        this.mLocalBluetoothManager = Utils.getLocalBtManager(context);
        LocalBluetoothManager localBluetoothManager = this.mLocalBluetoothManager;
        if (localBluetoothManager == null) {
            Log.e("MediaOutputIndicatorSlice", "Bluetooth is not supported on this device");
        } else {
            this.mProfileManager = localBluetoothManager.getProfileManager();
        }
    }

    public Slice getSlice() {
        if (!isVisible()) {
            return null;
        }
        IconCompat createWithResource = IconCompat.createWithResource(this.mContext, 17302809);
        CharSequence text = this.mContext.getText(C1715R.string.media_output_title);
        SliceAction createDeeplink = SliceAction.createDeeplink(PendingIntent.getActivity(this.mContext, 0, getMediaOutputSliceIntent(), 0), createWithResource, 0, text);
        int colorAccentDefaultColor = com.android.settingslib.Utils.getColorAccentDefaultColor(this.mContext);
        ListBuilder listBuilder = new ListBuilder(this.mContext, CustomSliceRegistry.MEDIA_OUTPUT_INDICATOR_SLICE_URI, -1);
        listBuilder.setAccentColor(colorAccentDefaultColor);
        ListBuilder.RowBuilder rowBuilder = new ListBuilder.RowBuilder();
        rowBuilder.setTitle(text);
        rowBuilder.setSubtitle(findActiveDeviceName());
        rowBuilder.setPrimaryAction(createDeeplink);
        listBuilder.addRow(rowBuilder);
        return listBuilder.build();
    }

    private Intent getMediaOutputSliceIntent() {
        return new Intent().setAction("com.android.settings.panel.action.MEDIA_OUTPUT").addFlags(268435456);
    }

    public Uri getUri() {
        return CustomSliceRegistry.MEDIA_OUTPUT_INDICATOR_SLICE_URI;
    }

    public Class getBackgroundWorkerClass() {
        return MediaOutputIndicatorWorker.class;
    }

    private boolean isVisible() {
        return ((TelephonyManager) this.mContext.getSystemService("phone")).getCallState() == 0 && (!CollectionUtils.isEmpty(getConnectedA2dpDevices()) || !CollectionUtils.isEmpty(getConnectedHearingAidDevices()));
    }

    private List<BluetoothDevice> getConnectedA2dpDevices() {
        A2dpProfile a2dpProfile = this.mProfileManager.getA2dpProfile();
        if (a2dpProfile == null) {
            return new ArrayList();
        }
        return a2dpProfile.getConnectedDevices();
    }

    private List<BluetoothDevice> getConnectedHearingAidDevices() {
        HearingAidProfile hearingAidProfile = this.mProfileManager.getHearingAidProfile();
        if (hearingAidProfile == null) {
            return new ArrayList();
        }
        return hearingAidProfile.getConnectedDevices();
    }

    private CharSequence findActiveDeviceName() {
        BluetoothDevice activeDevice;
        BluetoothDevice findActiveHearingAidDevice = findActiveHearingAidDevice();
        if (findActiveHearingAidDevice != null) {
            return findActiveHearingAidDevice.getAliasName();
        }
        A2dpProfile a2dpProfile = this.mProfileManager.getA2dpProfile();
        if (a2dpProfile == null || (activeDevice = a2dpProfile.getActiveDevice()) == null) {
            return this.mContext.getText(C1715R.string.media_output_default_summary);
        }
        return activeDevice.getAliasName();
    }

    private BluetoothDevice findActiveHearingAidDevice() {
        HearingAidProfile hearingAidProfile = this.mProfileManager.getHearingAidProfile();
        if (hearingAidProfile == null) {
            return null;
        }
        for (BluetoothDevice next : hearingAidProfile.getActiveDevices()) {
            if (next != null) {
                return next;
            }
        }
        return null;
    }
}
