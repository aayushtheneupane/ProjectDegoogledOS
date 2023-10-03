package com.android.settings.homepage.contextualcards.slices;

import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import androidx.core.graphics.drawable.IconCompat;
import androidx.slice.Slice;
import androidx.slice.builders.ListBuilder;
import androidx.slice.builders.SliceAction;
import com.android.internal.annotations.VisibleForTesting;
import com.android.settings.SubSettings;
import com.android.settings.bluetooth.BluetoothDeviceDetailsFragment;
import com.android.settings.bluetooth.BluetoothPairingDetail;
import com.android.settings.connecteddevice.ConnectedDeviceDashboardFragment;
import com.android.settings.core.SubSettingLauncher;
import com.android.settings.slices.CustomSliceRegistry;
import com.android.settings.slices.CustomSliceable;
import com.android.settings.slices.SliceBroadcastReceiver;
import com.android.settings.slices.SliceBuilderUtils;
import com.android.settingslib.Utils;
import com.android.settingslib.bluetooth.BluetoothUtils;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BluetoothDevicesSlice implements CustomSliceable {
    @VisibleForTesting
    static final String BLUETOOTH_DEVICE_HASH_CODE = "bluetooth_device_hash_code";
    private static final Comparator<CachedBluetoothDevice> COMPARATOR = Comparator.naturalOrder();
    @VisibleForTesting
    static final int DEFAULT_EXPANDED_ROW_COUNT = 3;
    private final Context mContext;

    public BluetoothDevicesSlice(Context context) {
        this.mContext = context;
    }

    public Uri getUri() {
        return CustomSliceRegistry.BLUETOOTH_DEVICES_SLICE_URI;
    }

    public Slice getSlice() {
        this.mContext.getTheme().applyStyle(2131952299, true);
        IconCompat createWithResource = IconCompat.createWithResource(this.mContext, 17302809);
        Drawable drawable = this.mContext.getDrawable(C1715R.C1717drawable.ic_add_24dp);
        drawable.setColorFilter(new PorterDuffColorFilter(Utils.getColorAttrDefaultColor(this.mContext, 16843817), PorterDuff.Mode.SRC_IN));
        IconCompat createIconWithDrawable = com.android.settings.Utils.createIconWithDrawable(drawable);
        CharSequence text = this.mContext.getText(C1715R.string.bluetooth_devices);
        CharSequence text2 = this.mContext.getText(C1715R.string.no_bluetooth_devices);
        CharSequence text3 = this.mContext.getText(C1715R.string.bluetooth_pairing_pref_title);
        SliceAction createDeeplink = SliceAction.createDeeplink(PendingIntent.getActivity(this.mContext, 0, getIntent(), 0), createWithResource, 0, text);
        SliceAction createDeeplink2 = SliceAction.createDeeplink(PendingIntent.getActivity(this.mContext, 0, getPairNewIntent(), 0), createIconWithDrawable, 0, text3);
        ListBuilder listBuilder = new ListBuilder(this.mContext, getUri(), -1);
        listBuilder.setAccentColor(-1);
        List<ListBuilder.RowBuilder> bluetoothRowBuilder = getBluetoothRowBuilder();
        if (bluetoothRowBuilder.isEmpty()) {
            ListBuilder.HeaderBuilder headerBuilder = new ListBuilder.HeaderBuilder();
            headerBuilder.setTitle(text2);
            headerBuilder.setPrimaryAction(createDeeplink);
            listBuilder.setHeader(headerBuilder);
            if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
                listBuilder.setIsError(true);
            }
            ListBuilder.RowBuilder rowBuilder = new ListBuilder.RowBuilder();
            rowBuilder.setTitleItem(createIconWithDrawable, 0);
            rowBuilder.setTitle(text3);
            rowBuilder.setPrimaryAction(createDeeplink2);
            listBuilder.addRow(rowBuilder);
            return listBuilder.build();
        }
        int min = Math.min(bluetoothRowBuilder.size(), 3);
        ListBuilder.HeaderBuilder headerBuilder2 = new ListBuilder.HeaderBuilder();
        headerBuilder2.setTitle(text);
        headerBuilder2.setSubtitle(getSubTitle(min));
        headerBuilder2.setPrimaryAction(createDeeplink);
        listBuilder.setHeader(headerBuilder2);
        for (int i = 0; i < min; i++) {
            listBuilder.addRow(bluetoothRowBuilder.get(i));
        }
        ListBuilder.RowBuilder rowBuilder2 = new ListBuilder.RowBuilder();
        rowBuilder2.setTitleItem(createIconWithDrawable, 0);
        rowBuilder2.setTitle(text3);
        rowBuilder2.setPrimaryAction(createDeeplink2);
        listBuilder.addRow(rowBuilder2);
        return listBuilder.build();
    }

    public Intent getIntent() {
        return SliceBuilderUtils.buildSearchResultPageIntent(this.mContext, ConnectedDeviceDashboardFragment.class.getName(), "", this.mContext.getText(C1715R.string.connected_devices_dashboard_title).toString(), 1401).setClassName(this.mContext.getPackageName(), SubSettings.class.getName()).setData(getUri());
    }

    public Intent getPairNewIntent() {
        return SliceBuilderUtils.buildSearchResultPageIntent(this.mContext, BluetoothPairingDetail.class.getName(), "", this.mContext.getText(C1715R.string.bluetooth_pairing_pref_title).toString(), 1401).setClassName(this.mContext.getPackageName(), SubSettings.class.getName());
    }

    public void onNotifyChange(Intent intent) {
        int intExtra = intent.getIntExtra(BLUETOOTH_DEVICE_HASH_CODE, -1);
        for (CachedBluetoothDevice next : getConnectedBluetoothDevices()) {
            if (next.hashCode() == intExtra) {
                next.setActive();
                return;
            }
        }
    }

    public Class getBackgroundWorkerClass() {
        return BluetoothUpdateWorker.class;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public List<CachedBluetoothDevice> getConnectedBluetoothDevices() {
        ArrayList arrayList = new ArrayList();
        if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            Log.i("BluetoothDevicesSlice", "Cannot get Bluetooth devices, Bluetooth is disabled.");
            return arrayList;
        }
        LocalBluetoothManager localBtManager = com.android.settings.bluetooth.Utils.getLocalBtManager(this.mContext);
        if (localBtManager != null) {
            return (List) localBtManager.getCachedDeviceManager().getCachedDevicesCopy().stream().filter($$Lambda$BluetoothDevicesSlice$V6QlkQR9oKiRxn_TrNtToTfoHoA.INSTANCE).sorted(COMPARATOR).collect(Collectors.toList());
        }
        Log.i("BluetoothDevicesSlice", "Cannot get Bluetooth devices, Bluetooth is unsupported.");
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public PendingIntent getBluetoothDetailIntent(CachedBluetoothDevice cachedBluetoothDevice) {
        Bundle bundle = new Bundle();
        bundle.putString("device_address", cachedBluetoothDevice.getDevice().getAddress());
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(this.mContext);
        subSettingLauncher.setDestination(BluetoothDeviceDetailsFragment.class.getName()).setArguments(bundle).setTitleRes(C1715R.string.device_details_title).setSourceMetricsCategory(1009);
        return PendingIntent.getActivity(this.mContext, cachedBluetoothDevice.hashCode(), subSettingLauncher.toIntent(), 0);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public IconCompat getBluetoothDeviceIcon(CachedBluetoothDevice cachedBluetoothDevice) {
        Drawable drawable = (Drawable) BluetoothUtils.getBtRainbowDrawableWithDescription(this.mContext, cachedBluetoothDevice).first;
        if (drawable == null) {
            return IconCompat.createWithResource(this.mContext, 17302809);
        }
        return com.android.settings.Utils.createIconWithDrawable(drawable);
    }

    private List<ListBuilder.RowBuilder> getBluetoothRowBuilder() {
        ArrayList arrayList = new ArrayList();
        for (CachedBluetoothDevice next : getConnectedBluetoothDevices()) {
            ListBuilder.RowBuilder rowBuilder = new ListBuilder.RowBuilder();
            rowBuilder.setTitleItem(getBluetoothDeviceIcon(next), 0);
            rowBuilder.setTitle(next.getName());
            rowBuilder.setSubtitle(next.getConnectionSummary());
            if (next.isConnectedA2dpDevice()) {
                rowBuilder.setPrimaryAction(buildMediaBluetoothAction(next));
                rowBuilder.addEndItem(buildBluetoothDetailDeepLinkAction(next));
            } else {
                rowBuilder.setPrimaryAction(buildBluetoothDetailDeepLinkAction(next));
            }
            arrayList.add(rowBuilder);
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public SliceAction buildMediaBluetoothAction(CachedBluetoothDevice cachedBluetoothDevice) {
        return SliceAction.create(PendingIntent.getBroadcast(this.mContext, cachedBluetoothDevice.hashCode(), new Intent(getUri().toString()).setClass(this.mContext, SliceBroadcastReceiver.class).putExtra(BLUETOOTH_DEVICE_HASH_CODE, cachedBluetoothDevice.hashCode()), 0), getBluetoothDeviceIcon(cachedBluetoothDevice), 0, cachedBluetoothDevice.getName());
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public SliceAction buildBluetoothDetailDeepLinkAction(CachedBluetoothDevice cachedBluetoothDevice) {
        return SliceAction.createDeeplink(getBluetoothDetailIntent(cachedBluetoothDevice), IconCompat.createWithResource(this.mContext, C1715R.C1717drawable.ic_settings_accent), 0, cachedBluetoothDevice.getName());
    }

    private CharSequence getSubTitle(int i) {
        return this.mContext.getResources().getQuantityString(C1715R.plurals.show_bluetooth_devices, i, new Object[]{Integer.valueOf(i)});
    }
}
