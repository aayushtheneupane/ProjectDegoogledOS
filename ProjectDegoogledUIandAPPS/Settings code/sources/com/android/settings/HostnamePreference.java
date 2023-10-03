package com.android.settings;

import android.content.Context;
import android.os.SystemProperties;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import androidx.preference.EditTextPreference;

public class HostnamePreference extends EditTextPreference {
    public HostnamePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setSummary((CharSequence) getText());
    }

    public void setText(String str) {
        if (str == null) {
            Log.e("HostnamePreference", "tried to set null hostname, request ignored");
            return;
        }
        String replaceAll = str.replaceAll("[^-.a-zA-Z0-9]", "");
        if (TextUtils.isEmpty(replaceAll)) {
            Log.w("HostnamePreference", "setting empty hostname");
        } else {
            Log.i("HostnamePreference", "hostname has been set: " + replaceAll);
        }
        SystemProperties.set("net.hostname", replaceAll);
        persistHostname(replaceAll);
        setSummary((CharSequence) replaceAll);
    }

    public String getText() {
        return SystemProperties.get("net.hostname");
    }

    public void onSetInitialValue(boolean z, Object obj) {
        persistHostname(getText());
    }

    public void persistHostname(String str) {
        Settings.Secure.putString(getContext().getContentResolver(), "device_hostname", str);
    }
}
