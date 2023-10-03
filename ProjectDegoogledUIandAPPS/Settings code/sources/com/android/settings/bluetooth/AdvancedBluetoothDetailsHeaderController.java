package com.android.settings.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.DeviceConfig;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.fuelgauge.BatteryMeterView;
import com.android.settingslib.Utils;
import com.android.settingslib.bluetooth.BluetoothUtils;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnDestroy;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
import com.android.settingslib.utils.ThreadUtils;
import com.android.settingslib.widget.LayoutPreference;
import com.havoc.config.center.C1715R;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AdvancedBluetoothDetailsHeaderController extends BasePreferenceController implements LifecycleObserver, OnStart, OnStop, OnDestroy, CachedBluetoothDevice.Callback {
    private static final int LOW_BATTERY_LEVEL = 20;
    private static final String TAG = "AdvancedBtHeaderCtrl";
    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private CachedBluetoothDevice mCachedDevice;
    Handler mHandler = new Handler(Looper.getMainLooper());
    final Map<String, Bitmap> mIconCache = new HashMap();
    LayoutPreference mLayoutPreference;
    final BluetoothAdapter.OnMetadataChangedListener mMetadataListener = new BluetoothAdapter.OnMetadataChangedListener() {
        public void onMetadataChanged(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
            String str;
            Object[] objArr = new Object[3];
            objArr[0] = bluetoothDevice;
            objArr[1] = Integer.valueOf(i);
            if (bArr == null) {
                str = null;
            } else {
                str = new String(bArr);
            }
            objArr[2] = str;
            Log.i(AdvancedBluetoothDetailsHeaderController.TAG, String.format("Metadata updated in Device %s: %d = %s.", objArr));
            AdvancedBluetoothDetailsHeaderController.this.refresh();
        }
    };

    public AdvancedBluetoothDetailsHeaderController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        return (!DeviceConfig.getBoolean("settings_ui", "bt_advanced_header_enabled", true) || !BluetoothUtils.getBooleanMetaData(this.mCachedDevice.getDevice(), 6)) ? 2 : 0;
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mLayoutPreference = (LayoutPreference) preferenceScreen.findPreference(getPreferenceKey());
        this.mLayoutPreference.setVisible(isAvailable());
        refresh();
    }

    public void onStart() {
        if (isAvailable()) {
            this.mCachedDevice.registerCallback(new CachedBluetoothDevice.Callback() {
                public final void onDeviceAttributesChanged() {
                    AdvancedBluetoothDetailsHeaderController.this.onDeviceAttributesChanged();
                }
            });
            this.mBluetoothAdapter.addOnMetadataChangedListener(this.mCachedDevice.getDevice(), this.mContext.getMainExecutor(), this.mMetadataListener);
        }
    }

    public void onStop() {
        if (isAvailable()) {
            this.mCachedDevice.unregisterCallback(new CachedBluetoothDevice.Callback() {
                public final void onDeviceAttributesChanged() {
                    AdvancedBluetoothDetailsHeaderController.this.onDeviceAttributesChanged();
                }
            });
            this.mBluetoothAdapter.removeOnMetadataChangedListener(this.mCachedDevice.getDevice(), this.mMetadataListener);
        }
    }

    public void onDestroy() {
        if (isAvailable()) {
            for (Bitmap next : this.mIconCache.values()) {
                if (next != null) {
                    next.recycle();
                }
            }
            this.mIconCache.clear();
        }
    }

    public void init(CachedBluetoothDevice cachedBluetoothDevice) {
        this.mCachedDevice = cachedBluetoothDevice;
    }

    /* access modifiers changed from: package-private */
    public void refresh() {
        LayoutPreference layoutPreference = this.mLayoutPreference;
        if (layoutPreference != null && this.mCachedDevice != null) {
            ((TextView) layoutPreference.findViewById(C1715R.C1718id.entity_header_title)).setText(this.mCachedDevice.getName());
            ((TextView) this.mLayoutPreference.findViewById(C1715R.C1718id.entity_header_summary)).setText(this.mCachedDevice.getConnectionSummary(true));
            if (!this.mCachedDevice.isConnected()) {
                updateDisconnectLayout();
                return;
            }
            updateSubLayout((LinearLayout) this.mLayoutPreference.findViewById(C1715R.C1718id.layout_left), 7, 10, 13, C1715R.string.bluetooth_left_name);
            updateSubLayout((LinearLayout) this.mLayoutPreference.findViewById(C1715R.C1718id.layout_middle), 9, 12, 15, C1715R.string.bluetooth_middle_name);
            updateSubLayout((LinearLayout) this.mLayoutPreference.findViewById(C1715R.C1718id.layout_right), 8, 11, 14, C1715R.string.bluetooth_right_name);
        }
    }

    /* access modifiers changed from: package-private */
    public Drawable createBtBatteryIcon(Context context, int i, boolean z) {
        BatteryMeterView.BatteryMeterDrawable batteryMeterDrawable = new BatteryMeterView.BatteryMeterDrawable(context, context.getColor(C1715R.C1716color.meter_background_color), context.getResources().getDimensionPixelSize(C1715R.dimen.advanced_bluetooth_battery_meter_width), context.getResources().getDimensionPixelSize(C1715R.dimen.advanced_bluetooth_battery_meter_height));
        batteryMeterDrawable.setBatteryLevel(i);
        batteryMeterDrawable.setColorFilter(new PorterDuffColorFilter(Utils.getColorAttrDefaultColor(context, (i > 20 || z) ? 16843817 : 16844099), PorterDuff.Mode.SRC));
        batteryMeterDrawable.setCharging(z);
        return batteryMeterDrawable;
    }

    private void updateSubLayout(LinearLayout linearLayout, int i, int i2, int i3, int i4) {
        if (linearLayout != null) {
            BluetoothDevice device = this.mCachedDevice.getDevice();
            String stringMetaData = BluetoothUtils.getStringMetaData(device, i);
            if (stringMetaData != null) {
                updateIcon((ImageView) linearLayout.findViewById(C1715R.C1718id.header_icon), stringMetaData);
            }
            int intMetaData = BluetoothUtils.getIntMetaData(device, i2);
            boolean booleanMetaData = BluetoothUtils.getBooleanMetaData(device, i3);
            if (intMetaData != -1) {
                linearLayout.setVisibility(0);
                ImageView imageView = (ImageView) linearLayout.findViewById(C1715R.C1718id.bt_battery_icon);
                imageView.setImageDrawable(createBtBatteryIcon(this.mContext, intMetaData, booleanMetaData));
                imageView.setVisibility(0);
                TextView textView = (TextView) linearLayout.findViewById(C1715R.C1718id.bt_battery_summary);
                textView.setText(Utils.formatPercentage(intMetaData));
                textView.setVisibility(0);
            } else {
                linearLayout.setVisibility(8);
            }
            TextView textView2 = (TextView) linearLayout.findViewById(C1715R.C1718id.header_title);
            textView2.setText(i4);
            textView2.setVisibility(0);
        }
    }

    private void updateDisconnectLayout() {
        this.mLayoutPreference.findViewById(C1715R.C1718id.layout_left).setVisibility(8);
        this.mLayoutPreference.findViewById(C1715R.C1718id.layout_right).setVisibility(8);
        LinearLayout linearLayout = (LinearLayout) this.mLayoutPreference.findViewById(C1715R.C1718id.layout_middle);
        linearLayout.setVisibility(0);
        linearLayout.findViewById(C1715R.C1718id.header_title).setVisibility(8);
        linearLayout.findViewById(C1715R.C1718id.bt_battery_summary).setVisibility(8);
        linearLayout.findViewById(C1715R.C1718id.bt_battery_icon).setVisibility(8);
        String stringMetaData = BluetoothUtils.getStringMetaData(this.mCachedDevice.getDevice(), 5);
        if (stringMetaData != null) {
            updateIcon((ImageView) linearLayout.findViewById(C1715R.C1718id.header_icon), stringMetaData);
        }
    }

    /* access modifiers changed from: package-private */
    public void updateIcon(ImageView imageView, String str) {
        if (this.mIconCache.containsKey(str)) {
            imageView.setImageBitmap(this.mIconCache.get(str));
        } else {
            ThreadUtils.postOnBackgroundThread((Runnable) new Runnable(str, imageView) {
                private final /* synthetic */ String f$1;
                private final /* synthetic */ ImageView f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    AdvancedBluetoothDetailsHeaderController.this.lambda$updateIcon$1$AdvancedBluetoothDetailsHeaderController(this.f$1, this.f$2);
                }
            });
        }
    }

    public /* synthetic */ void lambda$updateIcon$1$AdvancedBluetoothDetailsHeaderController(String str, ImageView imageView) {
        try {
            ThreadUtils.postOnMainThread(new Runnable(str, MediaStore.Images.Media.getBitmap(this.mContext.getContentResolver(), Uri.parse(str)), imageView) {
                private final /* synthetic */ String f$1;
                private final /* synthetic */ Bitmap f$2;
                private final /* synthetic */ ImageView f$3;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                }

                public final void run() {
                    AdvancedBluetoothDetailsHeaderController.this.lambda$updateIcon$0$AdvancedBluetoothDetailsHeaderController(this.f$1, this.f$2, this.f$3);
                }
            });
        } catch (IOException unused) {
            Log.e(TAG, "Failed to get bitmap for: " + str);
        }
    }

    public /* synthetic */ void lambda$updateIcon$0$AdvancedBluetoothDetailsHeaderController(String str, Bitmap bitmap, ImageView imageView) {
        this.mIconCache.put(str, bitmap);
        imageView.setImageBitmap(bitmap);
    }

    public void onDeviceAttributesChanged() {
        if (this.mCachedDevice != null) {
            refresh();
        }
    }
}
