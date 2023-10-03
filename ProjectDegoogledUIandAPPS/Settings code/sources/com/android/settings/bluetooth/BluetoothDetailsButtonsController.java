package com.android.settings.bluetooth;

import android.content.Context;
import android.view.View;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.widget.ActionButtonsPreference;
import com.havoc.config.center.C1715R;

public class BluetoothDetailsButtonsController extends BluetoothDetailsController {
    private ActionButtonsPreference mActionButtons;
    private boolean mConnectButtonInitialized;
    private boolean mIsConnected;

    public String getPreferenceKey() {
        return "action_buttons";
    }

    public BluetoothDetailsButtonsController(Context context, PreferenceFragmentCompat preferenceFragmentCompat, CachedBluetoothDevice cachedBluetoothDevice, Lifecycle lifecycle) {
        super(context, preferenceFragmentCompat, cachedBluetoothDevice, lifecycle);
        this.mIsConnected = cachedBluetoothDevice.isConnected();
    }

    private void onForgetButtonPressed() {
        ForgetDeviceDialogFragment.newInstance(this.mCachedDevice.getAddress()).show(this.mFragment.getFragmentManager(), "ForgetBluetoothDevice");
    }

    /* access modifiers changed from: protected */
    public void init(PreferenceScreen preferenceScreen) {
        this.mActionButtons = ((ActionButtonsPreference) preferenceScreen.findPreference(getPreferenceKey())).setButton1Text(C1715R.string.forget).setButton1Icon(C1715R.C1717drawable.ic_settings_delete).setButton1OnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                BluetoothDetailsButtonsController.this.lambda$init$0$BluetoothDetailsButtonsController(view);
            }
        }).setButton1Enabled(true);
    }

    public /* synthetic */ void lambda$init$0$BluetoothDetailsButtonsController(View view) {
        onForgetButtonPressed();
    }

    /* access modifiers changed from: protected */
    public void refresh() {
        this.mActionButtons.setButton2Enabled(!this.mCachedDevice.isBusy());
        boolean z = this.mIsConnected;
        this.mIsConnected = this.mCachedDevice.isConnected();
        if (this.mIsConnected) {
            if (!this.mConnectButtonInitialized || !z) {
                this.mActionButtons.setButton2Text(C1715R.string.bluetooth_device_context_disconnect).setButton2Icon(C1715R.C1717drawable.ic_settings_close).setButton2OnClickListener(new View.OnClickListener() {
                    public final void onClick(View view) {
                        BluetoothDetailsButtonsController.this.lambda$refresh$1$BluetoothDetailsButtonsController(view);
                    }
                });
                this.mConnectButtonInitialized = true;
            }
        } else if (!this.mConnectButtonInitialized || z) {
            this.mActionButtons.setButton2Text(C1715R.string.bluetooth_device_context_connect).setButton2Icon(C1715R.C1717drawable.ic_add_24dp).setButton2OnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    BluetoothDetailsButtonsController.this.lambda$refresh$2$BluetoothDetailsButtonsController(view);
                }
            });
            this.mConnectButtonInitialized = true;
        }
    }

    public /* synthetic */ void lambda$refresh$1$BluetoothDetailsButtonsController(View view) {
        this.mCachedDevice.disconnect();
    }

    public /* synthetic */ void lambda$refresh$2$BluetoothDetailsButtonsController(View view) {
        this.mCachedDevice.connect(true);
    }
}
