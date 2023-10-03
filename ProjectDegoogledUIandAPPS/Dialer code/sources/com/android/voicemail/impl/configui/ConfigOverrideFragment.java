package com.android.voicemail.impl.configui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.telecom.TelecomManager;
import android.text.TextUtils;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.dialer.common.concurrent.DialerExecutorModule;
import com.android.dialer.function.Supplier;
import com.android.dialer.strictmode.StrictModeUtils;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.android.voicemail.VoicemailComponent;

public class ConfigOverrideFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {
    public static final String CONFIG_OVERRIDE_KEY_PREFIX = "vvm_config_override_key_";

    public static boolean isOverridden(Context context) {
        return ((Boolean) StrictModeUtils.bypass(new Supplier(context) {
            private final /* synthetic */ Context f$0;

            {
                this.f$0 = r1;
            }

            public final Object get() {
                return Boolean.valueOf(PreferenceManager.getDefaultSharedPreferences(this.f$0).getBoolean(this.f$0.getString(R.string.vvm_config_override_enabled_key), false));
            }
        })).booleanValue();
    }

    /* access modifiers changed from: private */
    /* renamed from: updatePreference */
    public void lambda$onPreferenceChange$0$ConfigOverrideFragment(Preference preference) {
        if (preference instanceof EditTextPreference) {
            EditTextPreference editTextPreference = (EditTextPreference) preference;
            editTextPreference.setSummary(editTextPreference.getText());
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        PreferenceManager.setDefaultValues(getActivity(), R.xml.vvm_config_override, false);
        addPreferencesFromResource(R.xml.vvm_config_override);
        for (int i = 0; i < getPreferenceScreen().getPreferenceCount(); i++) {
            Preference preference = getPreferenceScreen().getPreference(i);
            preference.setOnPreferenceChangeListener(this);
            lambda$onPreferenceChange$0$ConfigOverrideFragment(preference);
        }
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        Assert.isMainThread();
        DialerExecutorModule.postOnUiThread(new Runnable(preference) {
            private final /* synthetic */ Preference f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                ConfigOverrideFragment.this.lambda$onPreferenceChange$0$ConfigOverrideFragment(this.f$1);
            }
        });
        return true;
    }

    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        String str;
        if (TextUtils.equals(preference.getKey(), getString(R.string.vvm_config_override_load_current_key))) {
            Activity activity = getActivity();
            PersistableBundle config = VoicemailComponent.get(activity).getVoicemailClient().getConfig(activity, ((TelecomManager) activity.getSystemService(TelecomManager.class)).getDefaultOutgoingPhoneAccount("voicemail"));
            for (int i = 0; i < getPreferenceScreen().getPreferenceCount(); i++) {
                Preference preference2 = getPreferenceScreen().getPreference(i);
                String key = preference2.getKey();
                if (key.startsWith(CONFIG_OVERRIDE_KEY_PREFIX)) {
                    String substring = key.substring(24);
                    if (substring.endsWith("bool")) {
                        ((SwitchPreference) preference2).setChecked(config.getBoolean(substring));
                    } else if (substring.endsWith("int")) {
                        ((EditTextPreference) preference2).setText(String.valueOf(config.getInt(substring)));
                    } else if (substring.endsWith("string")) {
                        ((EditTextPreference) preference2).setText(config.getString(substring));
                    } else if (substring.endsWith("string_array")) {
                        EditTextPreference editTextPreference = (EditTextPreference) preference2;
                        String[] stringArray = config.getStringArray(substring);
                        if (stringArray == null) {
                            str = "";
                        } else {
                            StringBuilder sb = new StringBuilder();
                            for (String str2 : stringArray) {
                                if (sb.length() != 0) {
                                    sb.append(",");
                                }
                                sb.append(str2);
                            }
                            str = sb.toString();
                        }
                        editTextPreference.setText(str);
                    } else {
                        throw new AssertionError(GeneratedOutlineSupport.outline8("unknown type for key ", substring));
                    }
                    lambda$onPreferenceChange$0$ConfigOverrideFragment(preference2);
                }
            }
        }
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }
}
