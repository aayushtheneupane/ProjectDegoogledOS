package com.android.settings.panel;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.android.settings.slices.CustomSliceRegistry;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class MediaOutputPanel implements PanelContent {
    private final Context mContext;
    private final String mPackageName;

    public int getMetricsCategory() {
        return 1657;
    }

    public Intent getSeeMoreIntent() {
        return null;
    }

    public static MediaOutputPanel create(Context context, String str) {
        return new MediaOutputPanel(context, str);
    }

    private MediaOutputPanel(Context context, String str) {
        this.mContext = context.getApplicationContext();
        this.mPackageName = str;
    }

    public CharSequence getTitle() {
        return this.mContext.getText(C1715R.string.media_output_panel_title);
    }

    public List<Uri> getSlices() {
        ArrayList arrayList = new ArrayList();
        CustomSliceRegistry.MEDIA_OUTPUT_SLICE_URI = CustomSliceRegistry.MEDIA_OUTPUT_SLICE_URI.buildUpon().clearQuery().appendQueryParameter("media_package_name", this.mPackageName).build();
        arrayList.add(CustomSliceRegistry.MEDIA_OUTPUT_SLICE_URI);
        return arrayList;
    }
}
