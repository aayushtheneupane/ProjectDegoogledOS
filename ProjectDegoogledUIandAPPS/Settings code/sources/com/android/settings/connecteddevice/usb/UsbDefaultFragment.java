package com.android.settings.connecteddevice.usb;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import com.android.settings.Utils;
import com.android.settings.widget.RadioButtonPickerFragment;
import com.android.settingslib.widget.CandidateInfo;
import com.android.settingslib.widget.FooterPreferenceMixinCompat;
import com.google.android.collect.Lists;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class UsbDefaultFragment extends RadioButtonPickerFragment {
    ConnectivityManager mConnectivityManager;
    OnStartTetheringCallback mOnStartTetheringCallback = new OnStartTetheringCallback();
    long mPreviousFunctions;
    UsbBackend mUsbBackend;

    public int getMetricsCategory() {
        return 1312;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.usb_default_fragment;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.mUsbBackend = new UsbBackend(context);
        this.mConnectivityManager = (ConnectivityManager) context.getSystemService(ConnectivityManager.class);
    }

    public void onCreatePreferences(Bundle bundle, String str) {
        super.onCreatePreferences(bundle, str);
        new FooterPreferenceMixinCompat(this, getSettingsLifecycle()).createFooterPreference().setTitle((int) C1715R.string.usb_default_info);
    }

    /* access modifiers changed from: protected */
    public List<? extends CandidateInfo> getCandidates() {
        ArrayList newArrayList = Lists.newArrayList();
        for (Long longValue : UsbDetailsFunctionsController.FUNCTIONS_MAP.keySet()) {
            long longValue2 = longValue.longValue();
            final String string = getContext().getString(UsbDetailsFunctionsController.FUNCTIONS_MAP.get(Long.valueOf(longValue2)).intValue());
            final String usbFunctionsToString = UsbBackend.usbFunctionsToString(longValue2);
            if (this.mUsbBackend.areFunctionsSupported(longValue2)) {
                newArrayList.add(new CandidateInfo(true) {
                    public Drawable loadIcon() {
                        return null;
                    }

                    public CharSequence loadLabel() {
                        return string;
                    }

                    public String getKey() {
                        return usbFunctionsToString;
                    }
                });
            }
        }
        return newArrayList;
    }

    /* access modifiers changed from: protected */
    public String getDefaultKey() {
        return UsbBackend.usbFunctionsToString(this.mUsbBackend.getDefaultUsbFunctions());
    }

    /* access modifiers changed from: protected */
    public boolean setDefaultKey(String str) {
        long usbFunctionsFromString = UsbBackend.usbFunctionsFromString(str);
        this.mPreviousFunctions = this.mUsbBackend.getCurrentFunctions();
        if (!Utils.isMonkeyRunning()) {
            if (usbFunctionsFromString == 32) {
                this.mConnectivityManager.startTethering(1, true, this.mOnStartTetheringCallback);
            } else {
                this.mUsbBackend.setDefaultUsbFunctions(usbFunctionsFromString);
            }
        }
        return true;
    }

    final class OnStartTetheringCallback extends ConnectivityManager.OnStartTetheringCallback {
        OnStartTetheringCallback() {
        }

        public void onTetheringStarted() {
            UsbDefaultFragment.super.onTetheringStarted();
            UsbDefaultFragment.this.mUsbBackend.setDefaultUsbFunctions(32);
        }

        public void onTetheringFailed() {
            UsbDefaultFragment.super.onTetheringFailed();
            UsbDefaultFragment usbDefaultFragment = UsbDefaultFragment.this;
            usbDefaultFragment.mUsbBackend.setDefaultUsbFunctions(usbDefaultFragment.mPreviousFunctions);
            UsbDefaultFragment.this.updateCandidates();
        }
    }
}
