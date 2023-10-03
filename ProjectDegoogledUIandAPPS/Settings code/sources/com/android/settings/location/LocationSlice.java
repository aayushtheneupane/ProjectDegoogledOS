package com.android.settings.location;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.core.graphics.drawable.IconCompat;
import androidx.slice.Slice;
import androidx.slice.builders.ListBuilder;
import androidx.slice.builders.SliceAction;
import com.android.settings.SubSettings;
import com.android.settings.slices.CustomSliceRegistry;
import com.android.settings.slices.CustomSliceable;
import com.android.settings.slices.SliceBuilderUtils;
import com.android.settingslib.Utils;
import com.havoc.config.center.C1715R;

public class LocationSlice implements CustomSliceable {
    private final Context mContext;

    public void onNotifyChange(Intent intent) {
    }

    public LocationSlice(Context context) {
        this.mContext = context;
    }

    public Slice getSlice() {
        IconCompat createWithResource = IconCompat.createWithResource(this.mContext, 17302825);
        CharSequence text = this.mContext.getText(C1715R.string.location_settings_title);
        int colorAccentDefaultColor = Utils.getColorAccentDefaultColor(this.mContext);
        SliceAction createDeeplink = SliceAction.createDeeplink(getPrimaryAction(), createWithResource, 0, text);
        ListBuilder listBuilder = new ListBuilder(this.mContext, CustomSliceRegistry.LOCATION_SLICE_URI, -1);
        listBuilder.setAccentColor(colorAccentDefaultColor);
        ListBuilder.RowBuilder rowBuilder = new ListBuilder.RowBuilder();
        rowBuilder.setTitle(text);
        rowBuilder.setTitleItem(createWithResource, 0);
        rowBuilder.setPrimaryAction(createDeeplink);
        listBuilder.addRow(rowBuilder);
        return listBuilder.build();
    }

    public Uri getUri() {
        return CustomSliceRegistry.LOCATION_SLICE_URI;
    }

    public Intent getIntent() {
        String charSequence = this.mContext.getText(C1715R.string.location_settings_title).toString();
        return SliceBuilderUtils.buildSearchResultPageIntent(this.mContext, LocationSettings.class.getName(), "location", charSequence, 63).setClassName(this.mContext.getPackageName(), SubSettings.class.getName()).setData(new Uri.Builder().appendPath("location").build());
    }

    private PendingIntent getPrimaryAction() {
        return PendingIntent.getActivity(this.mContext, 0, getIntent(), 0);
    }
}
