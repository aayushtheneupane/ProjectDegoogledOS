package com.android.settings.bluetooth;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.android.settings.Utils;
import com.android.settings.core.instrumentation.InstrumentedDialogFragment;
import com.havoc.config.center.C1715R;

abstract class BluetoothNameDialogFragment extends InstrumentedDialogFragment implements TextWatcher, TextView.OnEditorActionListener {
    AlertDialog mAlertDialog;
    private boolean mDeviceNameEdited;
    private boolean mDeviceNameUpdated;
    EditText mDeviceNameView;
    private Button mOkButton;

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    /* access modifiers changed from: protected */
    public abstract String getDeviceName();

    /* access modifiers changed from: protected */
    public abstract int getDialogTitle();

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    /* access modifiers changed from: protected */
    public abstract void setDeviceName(String str);

    BluetoothNameDialogFragment() {
    }

    public Dialog onCreateDialog(Bundle bundle) {
        String deviceName = getDeviceName();
        if (bundle != null) {
            deviceName = bundle.getString("device_name", deviceName);
            this.mDeviceNameEdited = bundle.getBoolean("device_name_edited", false);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getDialogTitle());
        builder.setView(createDialogView(deviceName));
        builder.setPositiveButton((int) C1715R.string.bluetooth_rename_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                BluetoothNameDialogFragment.this.lambda$onCreateDialog$0$BluetoothNameDialogFragment(dialogInterface, i);
            }
        });
        builder.setNegativeButton(17039360, (DialogInterface.OnClickListener) null);
        this.mAlertDialog = builder.create();
        this.mAlertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            public final void onShow(DialogInterface dialogInterface) {
                BluetoothNameDialogFragment.this.lambda$onCreateDialog$1$BluetoothNameDialogFragment(dialogInterface);
            }
        });
        return this.mAlertDialog;
    }

    public /* synthetic */ void lambda$onCreateDialog$0$BluetoothNameDialogFragment(DialogInterface dialogInterface, int i) {
        setDeviceName(this.mDeviceNameView.getText().toString());
    }

    public /* synthetic */ void lambda$onCreateDialog$1$BluetoothNameDialogFragment(DialogInterface dialogInterface) {
        InputMethodManager inputMethodManager;
        EditText editText = this.mDeviceNameView;
        if (editText != null && editText.requestFocus() && (inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method")) != null) {
            inputMethodManager.showSoftInput(this.mDeviceNameView, 1);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putString("device_name", this.mDeviceNameView.getText().toString());
        bundle.putBoolean("device_name_edited", this.mDeviceNameEdited);
    }

    private View createDialogView(String str) {
        View inflate = ((LayoutInflater) getActivity().getSystemService("layout_inflater")).inflate(C1715R.layout.dialog_edittext, (ViewGroup) null);
        this.mDeviceNameView = (EditText) inflate.findViewById(C1715R.C1718id.edittext);
        this.mDeviceNameView.setFilters(new InputFilter[]{new BluetoothLengthDeviceNameFilter()});
        this.mDeviceNameView.setText(str);
        if (!TextUtils.isEmpty(str)) {
            this.mDeviceNameView.setSelection(str.length());
        }
        this.mDeviceNameView.addTextChangedListener(this);
        Utils.setEditTextCursorPosition(this.mDeviceNameView);
        this.mDeviceNameView.setOnEditorActionListener(this);
        return inflate;
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i != 6) {
            return false;
        }
        setDeviceName(textView.getText().toString());
        AlertDialog alertDialog = this.mAlertDialog;
        if (alertDialog == null || !alertDialog.isShowing()) {
            return true;
        }
        this.mAlertDialog.dismiss();
        return true;
    }

    public void onDestroy() {
        super.onDestroy();
        this.mAlertDialog = null;
        this.mDeviceNameView = null;
        this.mOkButton = null;
    }

    public void onResume() {
        super.onResume();
        if (this.mOkButton == null) {
            this.mOkButton = this.mAlertDialog.getButton(-1);
            this.mOkButton.setEnabled(this.mDeviceNameEdited);
        }
    }

    /* access modifiers changed from: package-private */
    public void updateDeviceName() {
        String deviceName = getDeviceName();
        if (deviceName != null) {
            this.mDeviceNameUpdated = true;
            this.mDeviceNameEdited = false;
            this.mDeviceNameView.setText(deviceName);
        }
    }

    public void afterTextChanged(Editable editable) {
        if (this.mDeviceNameUpdated) {
            this.mDeviceNameUpdated = false;
            this.mOkButton.setEnabled(false);
            return;
        }
        boolean z = true;
        this.mDeviceNameEdited = true;
        Button button = this.mOkButton;
        if (button != null) {
            if (editable.toString().trim().length() == 0) {
                z = false;
            }
            button.setEnabled(z);
        }
    }
}
