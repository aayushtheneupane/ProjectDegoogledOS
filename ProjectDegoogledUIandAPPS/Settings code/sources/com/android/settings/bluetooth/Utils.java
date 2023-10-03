package com.android.settings.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.DialogInterface;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import com.android.settings.overlay.FeatureFactory;
import com.android.settingslib.bluetooth.BluetoothUtils;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.havoc.config.center.C1715R;

public final class Utils {
    /* access modifiers changed from: private */
    public static final BluetoothUtils.ErrorListener mErrorListener = new BluetoothUtils.ErrorListener() {
        public void onShowError(Context context, String str, int i) {
            Utils.showError(context, str, i);
        }
    };
    private static final LocalBluetoothManager.BluetoothManagerCallback mOnInitCallback = new LocalBluetoothManager.BluetoothManagerCallback() {
        public void onBluetoothManagerInitialized(Context context, LocalBluetoothManager localBluetoothManager) {
            BluetoothUtils.setErrorListener(Utils.mErrorListener);
        }
    };

    static AlertDialog showDisconnectDialog(Context context, AlertDialog alertDialog, DialogInterface.OnClickListener onClickListener, CharSequence charSequence, CharSequence charSequence2) {
        if (alertDialog == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setPositiveButton(17039370, onClickListener);
            builder.setNegativeButton(17039360, (DialogInterface.OnClickListener) null);
            alertDialog = builder.create();
        } else {
            if (alertDialog.isShowing()) {
                alertDialog.dismiss();
            }
            alertDialog.setButton(-1, context.getText(17039370), onClickListener);
        }
        alertDialog.setTitle(charSequence);
        alertDialog.setMessage(charSequence2);
        alertDialog.show();
        return alertDialog;
    }

    static void showConnectingError(Context context, String str, LocalBluetoothManager localBluetoothManager) {
        FeatureFactory.getFactory(context).getMetricsFeatureProvider().visible(context, 0, 869);
        showError(context, str, C1715R.string.bluetooth_connecting_error_message, localBluetoothManager);
    }

    static void showError(Context context, String str, int i) {
        showError(context, str, i, getLocalBtManager(context));
    }

    private static void showError(Context context, String str, int i, LocalBluetoothManager localBluetoothManager) {
        String string = context.getString(i, new Object[]{str});
        Context foregroundActivity = localBluetoothManager.getForegroundActivity();
        if (localBluetoothManager.isForegroundActivity()) {
            try {
                AlertDialog.Builder builder = new AlertDialog.Builder(foregroundActivity);
                builder.setTitle((int) C1715R.string.bluetooth_error_title);
                builder.setMessage((CharSequence) string);
                builder.setPositiveButton(17039370, (DialogInterface.OnClickListener) null);
                builder.show();
            } catch (Exception e) {
                Log.e("BluetoothUtils", "Cannot show error dialog.", e);
            }
        } else {
            Toast.makeText(context, string, 0).show();
        }
    }

    public static LocalBluetoothManager getLocalBtManager(Context context) {
        return LocalBluetoothManager.getInstance(context, mOnInitCallback);
    }

    public static String createRemoteName(Context context, BluetoothDevice bluetoothDevice) {
        String aliasName = bluetoothDevice != null ? bluetoothDevice.getAliasName() : null;
        return aliasName == null ? context.getString(C1715R.string.unknown) : aliasName;
    }

    public static boolean isBluetoothScanningEnabled(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), "ble_scan_always_enabled", 0) == 1;
    }
}
