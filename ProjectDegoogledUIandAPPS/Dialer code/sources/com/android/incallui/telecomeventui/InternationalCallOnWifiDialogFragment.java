package com.android.incallui.telecomeventui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.R$dimen;
import android.support.p000v4.app.DialogFragment;
import android.support.p000v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import com.android.dialer.R;
import com.android.dialer.common.FragmentUtils;
import com.android.dialer.common.LogUtil;
import com.android.dialer.storage.StorageComponent;
import com.android.incallui.call.CallList;
import com.android.incallui.call.DialerCall;

public class InternationalCallOnWifiDialogFragment extends DialogFragment {
    public static final String ALWAYS_SHOW_WARNING_PREFERENCE_KEY = "ALWAYS_SHOW_INTERNATIONAL_CALL_ON_WIFI_WARNING";

    public static boolean shouldShow(Context context) {
        if (!R$dimen.isUserUnlocked(context)) {
            LogUtil.m9i("InternationalCallOnWifiDialogFragment.shouldShow", "user locked, returning false", new Object[0]);
            return false;
        }
        boolean z = StorageComponent.get(context).unencryptedSharedPrefs().getBoolean(ALWAYS_SHOW_WARNING_PREFERENCE_KEY, true);
        LogUtil.m9i("InternationalCallOnWifiDialogFragment.shouldShow", "result: %b", Boolean.valueOf(z));
        return z;
    }

    public /* synthetic */ void lambda$onCreateDialog$0$InternationalCallOnWifiDialogFragment(SharedPreferences sharedPreferences, CheckBox checkBox, DialogInterface dialogInterface, int i) {
        boolean isChecked = checkBox.isChecked();
        LogUtil.m9i("InternationalCallOnWifiDialogFragment.onPositiveButtonClick", "alwaysWarn: %b", Boolean.valueOf(isChecked));
        sharedPreferences.edit().putBoolean(ALWAYS_SHOW_WARNING_PREFERENCE_KEY, isChecked).apply();
        LogUtil.m9i("InternationalCallOnWifiDialogFragment.continueCall", "Continuing call with ID: %s", getArguments().getString("call_id"));
        InternationalCallOnWifiDialogActivity internationalCallOnWifiDialogActivity = (InternationalCallOnWifiDialogActivity) FragmentUtils.getParent((Fragment) this, InternationalCallOnWifiDialogActivity.class);
        if (internationalCallOnWifiDialogActivity != null) {
            internationalCallOnWifiDialogActivity.finish();
        }
    }

    public /* synthetic */ void lambda$onCreateDialog$1$InternationalCallOnWifiDialogFragment(SharedPreferences sharedPreferences, CheckBox checkBox, DialogInterface dialogInterface, int i) {
        boolean isChecked = checkBox.isChecked();
        LogUtil.m9i("InternationalCallOnWifiDialogFragment.onNegativeButtonClick", "alwaysWarn: %b", Boolean.valueOf(isChecked));
        sharedPreferences.edit().putBoolean(ALWAYS_SHOW_WARNING_PREFERENCE_KEY, isChecked).apply();
        DialerCall callById = CallList.getInstance().getCallById(getArguments().getString("call_id"));
        if (callById == null) {
            LogUtil.m9i("InternationalCallOnWifiDialogFragment.cancelCall", "Call destroyed before the dialog is closed", new Object[0]);
        } else {
            LogUtil.m9i("InternationalCallOnWifiDialogFragment.cancelCall", "Disconnecting international call on WiFi", new Object[0]);
            callById.disconnect();
        }
        InternationalCallOnWifiDialogActivity internationalCallOnWifiDialogActivity = (InternationalCallOnWifiDialogActivity) FragmentUtils.getParent((Fragment) this, InternationalCallOnWifiDialogActivity.class);
        if (internationalCallOnWifiDialogActivity != null) {
            internationalCallOnWifiDialogActivity.finish();
        }
    }

    public Dialog onCreateDialog(Bundle bundle) {
        super.onCreateDialog(bundle);
        LogUtil.enterBlock("InternationalCallOnWifiDialogFragment.onCreateDialog");
        if (shouldShow(getActivity())) {
            View inflate = View.inflate(getActivity(), R.layout.frag_international_call_on_wifi_dialog, (ViewGroup) null);
            CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.always_warn);
            SharedPreferences unencryptedSharedPrefs = StorageComponent.get(getActivity()).unencryptedSharedPrefs();
            checkBox.setChecked(unencryptedSharedPrefs.getBoolean(ALWAYS_SHOW_WARNING_PREFERENCE_KEY, false));
            AlertDialog create = new AlertDialog.Builder(getActivity()).setCancelable(false).setView(inflate).setPositiveButton(17039370, new DialogInterface.OnClickListener(unencryptedSharedPrefs, checkBox) {
                private final /* synthetic */ SharedPreferences f$1;
                private final /* synthetic */ CheckBox f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void onClick(DialogInterface dialogInterface, int i) {
                    InternationalCallOnWifiDialogFragment.this.lambda$onCreateDialog$0$InternationalCallOnWifiDialogFragment(this.f$1, this.f$2, dialogInterface, i);
                }
            }).setNegativeButton(17039360, new DialogInterface.OnClickListener(unencryptedSharedPrefs, checkBox) {
                private final /* synthetic */ SharedPreferences f$1;
                private final /* synthetic */ CheckBox f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void onClick(DialogInterface dialogInterface, int i) {
                    InternationalCallOnWifiDialogFragment.this.lambda$onCreateDialog$1$InternationalCallOnWifiDialogFragment(this.f$1, this.f$2, dialogInterface, i);
                }
            }).create();
            create.setCanceledOnTouchOutside(false);
            return create;
        }
        throw new IllegalStateException("shouldShow indicated InternationalCallOnWifiDialogFragment should not have showed");
    }
}
