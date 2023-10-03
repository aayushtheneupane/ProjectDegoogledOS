package com.android.settings.flashlight;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;
import androidx.core.graphics.drawable.IconCompat;
import androidx.slice.Slice;
import androidx.slice.builders.ListBuilder;
import androidx.slice.builders.SliceAction;
import com.android.settings.slices.CustomSliceRegistry;
import com.android.settings.slices.CustomSliceable;
import com.android.settingslib.Utils;
import com.havoc.config.center.C1715R;

public class FlashlightSlice implements CustomSliceable {
    private final Context mContext;

    public Intent getIntent() {
        return null;
    }

    public FlashlightSlice(Context context) {
        this.mContext = context;
    }

    public Slice getSlice() {
        if (!isFlashlightAvailable(this.mContext)) {
            return null;
        }
        PendingIntent broadcastIntent = getBroadcastIntent(this.mContext);
        int colorAccentDefaultColor = Utils.getColorAccentDefaultColor(this.mContext);
        IconCompat createWithResource = IconCompat.createWithResource(this.mContext, C1715R.C1717drawable.ic_signal_flashlight);
        ListBuilder listBuilder = new ListBuilder(this.mContext, CustomSliceRegistry.FLASHLIGHT_SLICE_URI, -1);
        listBuilder.setAccentColor(colorAccentDefaultColor);
        ListBuilder.RowBuilder rowBuilder = new ListBuilder.RowBuilder();
        rowBuilder.setTitle(this.mContext.getText(C1715R.string.power_flashlight));
        rowBuilder.setTitleItem(createWithResource, 0);
        rowBuilder.setPrimaryAction(SliceAction.createToggle(broadcastIntent, (CharSequence) null, isFlashlightEnabled(this.mContext)));
        listBuilder.addRow(rowBuilder);
        return listBuilder.build();
    }

    public Uri getUri() {
        return CustomSliceRegistry.FLASHLIGHT_SLICE_URI;
    }

    public IntentFilter getIntentFilter() {
        return new IntentFilter("com.android.settings.flashlight.action.FLASHLIGHT_CHANGED");
    }

    public void onNotifyChange(Intent intent) {
        try {
            String cameraId = getCameraId(this.mContext);
            if (cameraId != null) {
                ((CameraManager) this.mContext.getSystemService(CameraManager.class)).setTorchMode(cameraId, intent.getBooleanExtra("android.app.slice.extra.TOGGLE_STATE", isFlashlightEnabled(this.mContext)));
            }
        } catch (CameraAccessException e) {
            Log.e("FlashlightSlice", "Camera couldn't set torch mode.", e);
        }
        this.mContext.getContentResolver().notifyChange(CustomSliceRegistry.FLASHLIGHT_SLICE_URI, (ContentObserver) null);
    }

    private static String getCameraId(Context context) throws CameraAccessException {
        CameraManager cameraManager = (CameraManager) context.getSystemService(CameraManager.class);
        for (String str : cameraManager.getCameraIdList()) {
            CameraCharacteristics cameraCharacteristics = cameraManager.getCameraCharacteristics(str);
            Boolean bool = (Boolean) cameraCharacteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
            Integer num = (Integer) cameraCharacteristics.get(CameraCharacteristics.LENS_FACING);
            if (bool != null && bool.booleanValue() && num != null && num.intValue() == 1) {
                return str;
            }
        }
        return null;
    }

    private static boolean isFlashlightAvailable(Context context) {
        if (Settings.Secure.getInt(context.getContentResolver(), "flashlight_available", 0) == 1) {
            return true;
        }
        return false;
    }

    private static boolean isFlashlightEnabled(Context context) {
        if (Settings.Secure.getInt(context.getContentResolver(), "flashlight_enabled", 0) == 1) {
            return true;
        }
        return false;
    }
}
