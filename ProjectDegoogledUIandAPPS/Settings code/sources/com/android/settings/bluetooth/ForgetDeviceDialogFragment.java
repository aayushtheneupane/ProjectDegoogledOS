package com.android.settings.bluetooth;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import com.android.settings.core.instrumentation.InstrumentedDialogFragment;
import com.android.settingslib.bluetooth.BluetoothUtils;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.havoc.config.center.C1715R;

public class ForgetDeviceDialogFragment extends InstrumentedDialogFragment {
    private CachedBluetoothDevice mDevice;

    public int getMetricsCategory() {
        return 1031;
    }

    public static ForgetDeviceDialogFragment newInstance(String str) {
        Bundle bundle = new Bundle(1);
        bundle.putString("device_address", str);
        ForgetDeviceDialogFragment forgetDeviceDialogFragment = new ForgetDeviceDialogFragment();
        forgetDeviceDialogFragment.setArguments(bundle);
        return forgetDeviceDialogFragment;
    }

    /* access modifiers changed from: package-private */
    public CachedBluetoothDevice getDevice(Context context) {
        String string = getArguments().getString("device_address");
        LocalBluetoothManager localBtManager = Utils.getLocalBtManager(context);
        return localBtManager.getCachedDeviceManager().findDevice(localBtManager.getBluetoothAdapter().getRemoteDevice(string));
    }

    public Dialog onCreateDialog(Bundle bundle) {
        $$Lambda$ForgetDeviceDialogFragment$EDf2UTKPcHIZGnJUVoyf7QwuxfU r5 = new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                ForgetDeviceDialogFragment.this.lambda$onCreateDialog$0$ForgetDeviceDialogFragment(dialogInterface, i);
            }
        };
        Context context = getContext();
        this.mDevice = getDevice(context);
        boolean booleanMetaData = BluetoothUtils.getBooleanMetaData(this.mDevice.getDevice(), 6);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setPositiveButton((int) C1715R.string.bluetooth_unpair_dialog_forget_confirm_button, (DialogInterface.OnClickListener) r5);
        builder.setNegativeButton(17039360, (DialogInterface.OnClickListener) null);
        AlertDialog create = builder.create();
        create.setTitle((int) C1715R.string.bluetooth_unpair_dialog_title);
        create.setMessage(context.getString(booleanMetaData ? C1715R.string.bluetooth_untethered_unpair_dialog_body : C1715R.string.bluetooth_unpair_dialog_body, new Object[]{this.mDevice.getName()}));
        return create;
    }

    public /* synthetic */ void lambda$onCreateDialog$0$ForgetDeviceDialogFragment(DialogInterface dialogInterface, int i) {
        this.mDevice.unpair();
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.finish();
        }
    }
}
