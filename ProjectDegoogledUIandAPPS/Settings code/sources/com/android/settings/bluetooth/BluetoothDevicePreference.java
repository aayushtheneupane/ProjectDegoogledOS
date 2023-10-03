package com.android.settings.bluetooth;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.UserManager;
import android.text.Html;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Pair;
import android.util.TypedValue;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.widget.GearPreference;
import com.android.settingslib.bluetooth.BluetoothUtils;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import com.havoc.config.center.C1715R;

public final class BluetoothDevicePreference extends GearPreference implements CachedBluetoothDevice.Callback {
    private static int sDimAlpha = Integer.MIN_VALUE;
    private String contentDescription = null;
    /* access modifiers changed from: private */
    public final CachedBluetoothDevice mCachedDevice;
    private AlertDialog mDisconnectDialog;
    private boolean mHideSecondTarget = false;
    boolean mNeedNotifyHierarchyChanged = false;
    Resources mResources = getContext().getResources();
    private final boolean mShowDevicesWithoutNames;
    private final UserManager mUserManager;

    /* access modifiers changed from: protected */
    public int getSecondTargetResId() {
        return C1715R.layout.preference_widget_gear;
    }

    public BluetoothDevicePreference(Context context, CachedBluetoothDevice cachedBluetoothDevice, boolean z) {
        super(context, (AttributeSet) null);
        this.mUserManager = (UserManager) context.getSystemService("user");
        this.mShowDevicesWithoutNames = z;
        if (sDimAlpha == Integer.MIN_VALUE) {
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(16842803, typedValue, true);
            sDimAlpha = (int) (typedValue.getFloat() * 255.0f);
        }
        this.mCachedDevice = cachedBluetoothDevice;
        this.mCachedDevice.registerCallback(this);
        onDeviceAttributesChanged();
    }

    public void setNeedNotifyHierarchyChanged(boolean z) {
        this.mNeedNotifyHierarchyChanged = z;
    }

    /* access modifiers changed from: protected */
    public boolean shouldHideSecondTarget() {
        CachedBluetoothDevice cachedBluetoothDevice = this.mCachedDevice;
        return cachedBluetoothDevice == null || cachedBluetoothDevice.getBondState() != 12 || this.mUserManager.hasUserRestriction("no_config_bluetooth") || this.mHideSecondTarget;
    }

    /* access modifiers changed from: package-private */
    public CachedBluetoothDevice getCachedDevice() {
        return this.mCachedDevice;
    }

    /* access modifiers changed from: protected */
    public void onPrepareForRemoval() {
        super.onPrepareForRemoval();
        this.mCachedDevice.unregisterCallback(this);
        AlertDialog alertDialog = this.mDisconnectDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
            this.mDisconnectDialog = null;
        }
    }

    public CachedBluetoothDevice getBluetoothDevice() {
        return this.mCachedDevice;
    }

    public void hideSecondTarget(boolean z) {
        this.mHideSecondTarget = z;
    }

    public void onDeviceAttributesChanged() {
        setTitle((CharSequence) this.mCachedDevice.getName());
        setSummary((CharSequence) this.mCachedDevice.getConnectionSummary());
        Pair<Drawable, String> btRainbowDrawableWithDescription = BluetoothUtils.getBtRainbowDrawableWithDescription(getContext(), this.mCachedDevice);
        Object obj = btRainbowDrawableWithDescription.first;
        if (obj != null) {
            setIcon((Drawable) obj);
            this.contentDescription = (String) btRainbowDrawableWithDescription.second;
        }
        boolean z = true;
        setEnabled(!this.mCachedDevice.isBusy());
        if (!this.mShowDevicesWithoutNames && !this.mCachedDevice.hasHumanReadableName()) {
            z = false;
        }
        setVisible(z);
        if (this.mNeedNotifyHierarchyChanged) {
            notifyHierarchyChanged();
        }
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        ImageView imageView;
        if (findPreferenceInHierarchy("bt_checkbox") != null) {
            setDependency("bt_checkbox");
        }
        if (this.mCachedDevice.getBondState() == 12 && (imageView = (ImageView) preferenceViewHolder.findViewById(C1715R.C1718id.settings_button)) != null) {
            imageView.setOnClickListener(this);
        }
        ImageView imageView2 = (ImageView) preferenceViewHolder.findViewById(16908294);
        if (imageView2 != null) {
            imageView2.setContentDescription(this.contentDescription);
            imageView2.setImportantForAccessibility(2);
            imageView2.setElevation(getContext().getResources().getDimension(C1715R.dimen.bt_icon_elevation));
        }
        super.onBindViewHolder(preferenceViewHolder);
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof BluetoothDevicePreference)) {
            return false;
        }
        return this.mCachedDevice.equals(((BluetoothDevicePreference) obj).mCachedDevice);
    }

    public int hashCode() {
        return this.mCachedDevice.hashCode();
    }

    public int compareTo(Preference preference) {
        if (!(preference instanceof BluetoothDevicePreference)) {
            return super.compareTo(preference);
        }
        return this.mCachedDevice.compareTo(((BluetoothDevicePreference) preference).mCachedDevice);
    }

    /* access modifiers changed from: package-private */
    public void onClicked() {
        Context context = getContext();
        int bondState = this.mCachedDevice.getBondState();
        MetricsFeatureProvider metricsFeatureProvider = FeatureFactory.getFactory(context).getMetricsFeatureProvider();
        if (this.mCachedDevice.isConnected()) {
            metricsFeatureProvider.action(context, 868, (Pair<Integer, Object>[]) new Pair[0]);
            askDisconnect();
        } else if (bondState == 12) {
            metricsFeatureProvider.action(context, 867, (Pair<Integer, Object>[]) new Pair[0]);
            this.mCachedDevice.connect(true);
        } else if (bondState == 10) {
            metricsFeatureProvider.action(context, 866, (Pair<Integer, Object>[]) new Pair[0]);
            if (!this.mCachedDevice.hasHumanReadableName()) {
                metricsFeatureProvider.action(context, 1096, (Pair<Integer, Object>[]) new Pair[0]);
            }
            pair();
        }
    }

    private void askDisconnect() {
        Context context = getContext();
        String name = this.mCachedDevice.getName();
        if (TextUtils.isEmpty(name)) {
            name = context.getString(C1715R.string.bluetooth_device);
        }
        String string = context.getString(C1715R.string.bluetooth_disconnect_all_profiles, new Object[]{name});
        String string2 = context.getString(C1715R.string.bluetooth_disconnect_title);
        this.mDisconnectDialog = Utils.showDisconnectDialog(context, this.mDisconnectDialog, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                BluetoothDevicePreference.this.mCachedDevice.disconnect();
            }
        }, string2, Html.fromHtml(string));
    }

    private void pair() {
        if (!this.mCachedDevice.startPairing()) {
            Utils.showError(getContext(), this.mCachedDevice.getName(), C1715R.string.bluetooth_pairing_error_message);
        }
    }
}
