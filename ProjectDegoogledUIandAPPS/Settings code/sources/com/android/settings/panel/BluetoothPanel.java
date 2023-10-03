package com.android.settings.panel;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.android.settings.SubSettings;
import com.android.settings.connecteddevice.ConnectedDeviceDashboardFragment;
import com.android.settings.slices.CustomSliceRegistry;
import com.android.settings.slices.SliceBuilderUtils;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class BluetoothPanel implements PanelContent {
    private final Context mContext;

    public int getMetricsCategory() {
        return 2001;
    }

    public static BluetoothPanel create(Context context) {
        return new BluetoothPanel(context);
    }

    private BluetoothPanel(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public CharSequence getTitle() {
        return this.mContext.getText(C1715R.string.bluetooth_settings);
    }

    public List<Uri> getSlices() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(CustomSliceRegistry.BLUETOOTH_URI);
        arrayList.add(CustomSliceRegistry.BLUETOOTH_DEVICES_SLICE_URI);
        return arrayList;
    }

    public Intent getSeeMoreIntent() {
        Intent buildSearchResultPageIntent = SliceBuilderUtils.buildSearchResultPageIntent(this.mContext, ConnectedDeviceDashboardFragment.class.getName(), (String) null, this.mContext.getText(C1715R.string.bluetooth_settings).toString(), 747);
        buildSearchResultPageIntent.setClassName(this.mContext.getPackageName(), SubSettings.class.getName());
        buildSearchResultPageIntent.addFlags(268435456);
        return buildSearchResultPageIntent;
    }
}
