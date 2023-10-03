package com.android.messaging.p041ui.appsettings;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import com.android.messaging.R;
import com.android.messaging.p041ui.C1040Ea;
import com.android.messaging.util.C1410N;
import com.android.messaging.util.C1464na;
import com.android.messaging.util.C1474sa;

/* renamed from: com.android.messaging.ui.appsettings.k */
public class C1095k extends PreferenceFragment {

    /* renamed from: ua */
    private String f1746ua;

    /* renamed from: va */
    private String f1747va;

    /* renamed from: wa */
    private Preference f1748wa;

    /* renamed from: xa */
    private String f1749xa;

    /* renamed from: ya */
    private Preference f1750ya;

    /* renamed from: za */
    private String f1751za;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getPreferenceManager().setSharedPreferencesName("bugle");
        addPreferencesFromResource(R.xml.preferences_application);
        this.f1746ua = getString(R.string.notifications_pref_key);
        findPreference(this.f1746ua);
        this.f1747va = getString(R.string.sms_disabled_pref_key);
        this.f1748wa = findPreference(this.f1747va);
        this.f1749xa = getString(R.string.sms_enabled_pref_key);
        this.f1750ya = findPreference(this.f1749xa);
        this.f1751za = getString(R.string.swipe_right_deletes_conversation_key);
        SwitchPreference switchPreference = (SwitchPreference) findPreference(this.f1751za);
        C1410N.m3547Nj();
        getPreferenceScreen().removePreference(findPreference(getString(R.string.debug_pref_key)));
        PreferenceScreen preferenceScreen = (PreferenceScreen) findPreference(getString(R.string.advanced_pref_key));
        if (getActivity().getIntent().getBooleanExtra("top_level_settings", false)) {
            preferenceScreen.setIntent(C1040Ea.get().mo6979p(getPreferenceScreen().getContext()));
        } else {
            getPreferenceScreen().removePreference(preferenceScreen);
        }
    }

    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        if (preference.getKey() == this.f1746ua) {
            Intent intent = new Intent("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("android.provider.extra.APP_PACKAGE", getContext().getPackageName());
            startActivity(intent);
        }
        if (preference.getKey() != this.f1747va) {
            String key = preference.getKey();
            String str = this.f1749xa;
        }
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }

    public void onResume() {
        super.onResume();
        if (!C1464na.m3757Xj()) {
            getPreferenceScreen().removePreference(this.f1748wa);
            getPreferenceScreen().removePreference(this.f1750ya);
            return;
        }
        String string = getString(R.string.default_sms_app, new Object[]{C1474sa.getDefault().mo8224dk()});
        if (C1474sa.getDefault().mo8228kk()) {
            if (getPreferenceScreen().findPreference(this.f1749xa) == null) {
                getPreferenceScreen().addPreference(this.f1750ya);
            }
            getPreferenceScreen().removePreference(this.f1748wa);
            this.f1750ya.setSummary(string);
            return;
        }
        if (getPreferenceScreen().findPreference(this.f1747va) == null) {
            getPreferenceScreen().addPreference(this.f1748wa);
        }
        getPreferenceScreen().removePreference(this.f1750ya);
        this.f1748wa.setSummary(string);
    }
}
