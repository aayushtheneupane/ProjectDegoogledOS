package com.android.settings.panel;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import com.android.settings.Utils;
import com.android.settings.notification.RemoteVolumePreferenceController;
import com.android.settings.slices.CustomSliceRegistry;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class VolumePanel implements PanelContent {
    private final Context mContext;

    public int getMetricsCategory() {
        return 1655;
    }

    public static VolumePanel create(Context context) {
        return new VolumePanel(context);
    }

    private VolumePanel(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public CharSequence getTitle() {
        return this.mContext.getText(C1715R.string.volume_connectivity_panel_title);
    }

    public List<Uri> getSlices() {
        ArrayList arrayList = new ArrayList();
        if (RemoteVolumePreferenceController.getActiveRemoteToken(this.mContext) != null) {
            arrayList.add(CustomSliceRegistry.VOLUME_REMOTE_MEDIA_URI);
        }
        arrayList.add(CustomSliceRegistry.VOLUME_MEDIA_URI);
        arrayList.add(CustomSliceRegistry.MEDIA_OUTPUT_INDICATOR_SLICE_URI);
        arrayList.add(CustomSliceRegistry.VOLUME_CALL_URI);
        arrayList.add(CustomSliceRegistry.VOLUME_RINGER_URI);
        if (Utils.isVoiceCapable(this.mContext) && Settings.Secure.getInt(this.mContext.getContentResolver(), "volume_link_notification", 1) == 0) {
            arrayList.add(CustomSliceRegistry.VOLUME_NOTIFICATION_URI);
        }
        arrayList.add(CustomSliceRegistry.VOLUME_ALARM_URI);
        return arrayList;
    }

    public Intent getSeeMoreIntent() {
        return new Intent("android.settings.SOUND_SETTINGS").addFlags(268435456);
    }
}
