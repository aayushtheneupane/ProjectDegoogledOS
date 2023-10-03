package com.android.settings.homepage.contextualcards.slices;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.storage.StorageManager;
import android.text.format.Formatter;
import androidx.core.graphics.drawable.IconCompat;
import androidx.slice.Slice;
import androidx.slice.builders.ListBuilder;
import androidx.slice.builders.SliceAction;
import com.android.settings.SubSettings;
import com.android.settings.deviceinfo.StorageSettings;
import com.android.settings.slices.CustomSliceRegistry;
import com.android.settings.slices.CustomSliceable;
import com.android.settings.slices.SliceBuilderUtils;
import com.android.settingslib.Utils;
import com.android.settingslib.deviceinfo.PrivateStorageInfo;
import com.android.settingslib.deviceinfo.StorageManagerVolumeProvider;
import com.havoc.config.center.C1715R;
import java.text.NumberFormat;

public class LowStorageSlice implements CustomSliceable {
    private final Context mContext;

    public void onNotifyChange(Intent intent) {
    }

    public LowStorageSlice(Context context) {
        this.mContext = context;
    }

    public Slice getSlice() {
        PrivateStorageInfo privateStorageInfo = PrivateStorageInfo.getPrivateStorageInfo(new StorageManagerVolumeProvider((StorageManager) this.mContext.getSystemService(StorageManager.class)));
        long j = privateStorageInfo.totalBytes;
        double d = ((double) (j - privateStorageInfo.freeBytes)) / ((double) j);
        String format = NumberFormat.getPercentInstance().format(d);
        String formatFileSize = Formatter.formatFileSize(this.mContext, privateStorageInfo.freeBytes);
        ListBuilder listBuilder = new ListBuilder(this.mContext, CustomSliceRegistry.LOW_STORAGE_SLICE_URI, -1);
        listBuilder.setAccentColor(Utils.getColorAccentDefaultColor(this.mContext));
        IconCompat createWithResource = IconCompat.createWithResource(this.mContext, C1715R.C1717drawable.ic_storage);
        if (d < 0.85d) {
            listBuilder.addRow(buildRowBuilder(this.mContext.getText(C1715R.string.storage_settings), this.mContext.getString(C1715R.string.storage_summary, new Object[]{format, formatFileSize}), createWithResource));
            listBuilder.setIsError(true);
            return listBuilder.build();
        }
        listBuilder.addRow(buildRowBuilder(this.mContext.getText(C1715R.string.storage_menu_free), this.mContext.getString(C1715R.string.low_storage_summary, new Object[]{format, formatFileSize}), createWithResource));
        return listBuilder.build();
    }

    public Uri getUri() {
        return CustomSliceRegistry.LOW_STORAGE_SLICE_URI;
    }

    public Intent getIntent() {
        return SliceBuilderUtils.buildSearchResultPageIntent(this.mContext, StorageSettings.class.getName(), "", this.mContext.getText(C1715R.string.storage_label).toString(), 1401).setClassName(this.mContext.getPackageName(), SubSettings.class.getName()).setData(CustomSliceRegistry.LOW_STORAGE_SLICE_URI);
    }

    private ListBuilder.RowBuilder buildRowBuilder(CharSequence charSequence, String str, IconCompat iconCompat) {
        SliceAction createDeeplink = SliceAction.createDeeplink(PendingIntent.getActivity(this.mContext, 0, getIntent(), 0), iconCompat, 0, charSequence);
        ListBuilder.RowBuilder rowBuilder = new ListBuilder.RowBuilder();
        rowBuilder.setTitleItem(iconCompat, 0);
        rowBuilder.setTitle(charSequence);
        rowBuilder.setSubtitle(str);
        rowBuilder.setPrimaryAction(createDeeplink);
        return rowBuilder;
    }
}
