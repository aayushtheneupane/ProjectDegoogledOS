package com.android.settings;

import android.app.Dialog;
import android.app.admin.DevicePolicyManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Proxy;
import android.net.ProxyInfo;
import android.os.Bundle;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.core.InstrumentedFragment;
import com.havoc.config.center.C1715R;

public class ProxySelector extends InstrumentedFragment implements DialogCreatable {
    Button mClearButton;
    View.OnClickListener mClearHandler = new View.OnClickListener() {
        public void onClick(View view) {
            ProxySelector.this.mHostnameField.setText("");
            ProxySelector.this.mPortField.setText("");
            ProxySelector.this.mExclusionListField.setText("");
        }
    };
    Button mDefaultButton;
    View.OnClickListener mDefaultHandler = new View.OnClickListener() {
        public void onClick(View view) {
            ProxySelector.this.populateFields();
        }
    };
    private SettingsPreferenceFragment.SettingsDialogFragment mDialogFragment;
    EditText mExclusionListField;
    EditText mHostnameField;
    Button mOKButton;
    View.OnClickListener mOKHandler = new View.OnClickListener() {
        public void onClick(View view) {
            if (ProxySelector.this.saveToDb()) {
                ProxySelector.this.getActivity().onBackPressed();
            }
        }
    };
    View.OnFocusChangeListener mOnFocusChangeHandler = new View.OnFocusChangeListener() {
        public void onFocusChange(View view, boolean z) {
            if (z) {
                Selection.selectAll((Spannable) ((TextView) view).getText());
            }
        }
    };
    EditText mPortField;
    private View mView;

    public int getDialogMetricsCategory(int i) {
        return 574;
    }

    public int getMetricsCategory() {
        return 82;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mView = layoutInflater.inflate(C1715R.layout.proxy, viewGroup, false);
        initView(this.mView);
        populateFields();
        return this.mView;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        boolean z = ((DevicePolicyManager) getActivity().getSystemService("device_policy")).getGlobalProxyAdmin() == null;
        this.mHostnameField.setEnabled(z);
        this.mPortField.setEnabled(z);
        this.mExclusionListField.setEnabled(z);
        this.mOKButton.setEnabled(z);
        this.mClearButton.setEnabled(z);
        this.mDefaultButton.setEnabled(z);
    }

    public Dialog onCreateDialog(int i) {
        if (i != 0) {
            return null;
        }
        String string = getActivity().getString(validate(this.mHostnameField.getText().toString().trim(), this.mPortField.getText().toString().trim(), this.mExclusionListField.getText().toString().trim()));
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle((int) C1715R.string.proxy_error);
        builder.setPositiveButton((int) C1715R.string.proxy_error_dismiss, (DialogInterface.OnClickListener) null);
        builder.setMessage((CharSequence) string);
        return builder.create();
    }

    private void showDialog(int i) {
        if (this.mDialogFragment != null) {
            Log.e("ProxySelector", "Old dialog fragment not null!");
        }
        this.mDialogFragment = SettingsPreferenceFragment.SettingsDialogFragment.newInstance(this, i);
        this.mDialogFragment.show(getActivity().getSupportFragmentManager(), Integer.toString(i));
    }

    private void initView(View view) {
        this.mHostnameField = (EditText) view.findViewById(C1715R.C1718id.hostname);
        this.mHostnameField.setOnFocusChangeListener(this.mOnFocusChangeHandler);
        this.mPortField = (EditText) view.findViewById(C1715R.C1718id.port);
        this.mPortField.setOnClickListener(this.mOKHandler);
        this.mPortField.setOnFocusChangeListener(this.mOnFocusChangeHandler);
        this.mExclusionListField = (EditText) view.findViewById(C1715R.C1718id.exclusionlist);
        this.mExclusionListField.setOnFocusChangeListener(this.mOnFocusChangeHandler);
        this.mOKButton = (Button) view.findViewById(C1715R.C1718id.action);
        this.mOKButton.setOnClickListener(this.mOKHandler);
        this.mClearButton = (Button) view.findViewById(C1715R.C1718id.clear);
        this.mClearButton.setOnClickListener(this.mClearHandler);
        this.mDefaultButton = (Button) view.findViewById(C1715R.C1718id.defaultView);
        this.mDefaultButton.setOnClickListener(this.mDefaultHandler);
    }

    /* access modifiers changed from: package-private */
    public void populateFields() {
        int i;
        String str;
        String str2;
        FragmentActivity activity = getActivity();
        ProxyInfo globalProxy = ((ConnectivityManager) getActivity().getSystemService("connectivity")).getGlobalProxy();
        String str3 = "";
        if (globalProxy != null) {
            str = globalProxy.getHost();
            i = globalProxy.getPort();
            str2 = globalProxy.getExclusionListAsString();
        } else {
            i = -1;
            str2 = str3;
            str = str2;
        }
        if (str == null) {
            str = str3;
        }
        this.mHostnameField.setText(str);
        if (i != -1) {
            str3 = Integer.toString(i);
        }
        this.mPortField.setText(str3);
        this.mExclusionListField.setText(str2);
        Intent intent = activity.getIntent();
        String stringExtra = intent.getStringExtra("button-label");
        if (!TextUtils.isEmpty(stringExtra)) {
            this.mOKButton.setText(stringExtra);
        }
        String stringExtra2 = intent.getStringExtra("title");
        if (!TextUtils.isEmpty(stringExtra2)) {
            activity.setTitle(stringExtra2);
        } else {
            activity.setTitle(C1715R.string.proxy_settings_title);
        }
    }

    public static int validate(String str, String str2, String str3) {
        int validate = Proxy.validate(str, str2, str3);
        if (validate == 0) {
            return 0;
        }
        if (validate == 1) {
            return C1715R.string.proxy_error_empty_host_set_port;
        }
        if (validate == 2) {
            return C1715R.string.proxy_error_invalid_host;
        }
        if (validate == 3) {
            return C1715R.string.proxy_error_empty_port;
        }
        if (validate == 4) {
            return C1715R.string.proxy_error_invalid_port;
        }
        if (validate == 5) {
            return C1715R.string.proxy_error_invalid_exclusion_list;
        }
        Log.e("ProxySelector", "Unknown proxy settings error");
        return -1;
    }

    /* access modifiers changed from: package-private */
    public boolean saveToDb() {
        String trim = this.mHostnameField.getText().toString().trim();
        String trim2 = this.mPortField.getText().toString().trim();
        String trim3 = this.mExclusionListField.getText().toString().trim();
        int i = 0;
        if (validate(trim, trim2, trim3) != 0) {
            showDialog(0);
            return false;
        }
        if (trim2.length() > 0) {
            try {
                i = Integer.parseInt(trim2);
            } catch (NumberFormatException unused) {
                return false;
            }
        }
        ((ConnectivityManager) getActivity().getSystemService("connectivity")).setGlobalProxy(new ProxyInfo(trim, i, trim3));
        return true;
    }
}
