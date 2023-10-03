package com.android.settings.homepage.contextualcards.deviceinfo;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.core.graphics.drawable.IconCompat;
import androidx.slice.Slice;
import androidx.slice.builders.ListBuilder;
import androidx.slice.builders.SliceAction;
import com.android.settings.accounts.EmergencyInfoPreferenceController;
import com.android.settings.slices.CustomSliceRegistry;
import com.android.settings.slices.CustomSliceable;
import com.havoc.config.center.C1715R;

public class EmergencyInfoSlice implements CustomSliceable {
    private final Context mContext;

    public void onNotifyChange(Intent intent) {
    }

    public EmergencyInfoSlice(Context context) {
        this.mContext = context;
    }

    public Slice getSlice() {
        ListBuilder listBuilder = new ListBuilder(this.mContext, CustomSliceRegistry.EMERGENCY_INFO_SLICE_URI, -1);
        ListBuilder.RowBuilder rowBuilder = new ListBuilder.RowBuilder();
        rowBuilder.setTitle(this.mContext.getText(C1715R.string.emergency_info_title));
        rowBuilder.setSubtitle(this.mContext.getText(C1715R.string.emergency_info_contextual_card_summary));
        rowBuilder.setPrimaryAction(createPrimaryAction());
        listBuilder.addRow(rowBuilder);
        return listBuilder.build();
    }

    public Uri getUri() {
        return CustomSliceRegistry.EMERGENCY_INFO_SLICE_URI;
    }

    public Intent getIntent() {
        return new Intent(EmergencyInfoPreferenceController.getIntentAction(this.mContext));
    }

    private SliceAction createPrimaryAction() {
        return SliceAction.createDeeplink(PendingIntent.getActivity(this.mContext, 0, getIntent(), 134217728), IconCompat.createWithResource(this.mContext, C1715R.C1717drawable.empty_icon), 0, this.mContext.getText(C1715R.string.emergency_info_title));
    }
}
