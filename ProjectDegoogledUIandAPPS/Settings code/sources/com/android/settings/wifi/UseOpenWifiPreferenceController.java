package com.android.settings.wifi;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.net.NetworkScoreManager;
import android.net.NetworkScorerAppData;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnPause;
import com.android.settingslib.core.lifecycle.events.OnResume;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.SwitchPreference;

public class UseOpenWifiPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin, Preference.OnPreferenceChangeListener, LifecycleObserver, OnResume, OnPause {
    private final ContentResolver mContentResolver;
    private boolean mDoFeatureSupportedScorersExist;
    private ComponentName mEnableUseWifiComponentName;
    private final Fragment mFragment;
    private final NetworkScoreManager mNetworkScoreManager;
    /* access modifiers changed from: private */
    public Preference mPreference;
    private final SettingObserver mSettingObserver = new SettingObserver();

    public String getPreferenceKey() {
        return "use_open_wifi_automatically";
    }

    public UseOpenWifiPreferenceController(Context context, Fragment fragment, Lifecycle lifecycle) {
        super(context);
        this.mContentResolver = context.getContentResolver();
        this.mFragment = fragment;
        this.mNetworkScoreManager = (NetworkScoreManager) context.getSystemService("network_score");
        updateEnableUseWifiComponentName();
        checkForFeatureSupportedScorers();
        lifecycle.addObserver(this);
    }

    /* access modifiers changed from: private */
    public void updateEnableUseWifiComponentName() {
        ComponentName componentName;
        NetworkScorerAppData activeScorer = this.mNetworkScoreManager.getActiveScorer();
        if (activeScorer == null) {
            componentName = null;
        } else {
            componentName = activeScorer.getEnableUseOpenWifiActivity();
        }
        this.mEnableUseWifiComponentName = componentName;
    }

    private void checkForFeatureSupportedScorers() {
        if (this.mEnableUseWifiComponentName != null) {
            this.mDoFeatureSupportedScorersExist = true;
            return;
        }
        for (NetworkScorerAppData enableUseOpenWifiActivity : this.mNetworkScoreManager.getAllValidScorers()) {
            if (enableUseOpenWifiActivity.getEnableUseOpenWifiActivity() != null) {
                this.mDoFeatureSupportedScorersExist = true;
                return;
            }
        }
        this.mDoFeatureSupportedScorersExist = false;
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = preferenceScreen.findPreference("use_open_wifi_automatically");
    }

    public void onResume() {
        this.mSettingObserver.register(this.mContentResolver);
    }

    public void onPause() {
        this.mSettingObserver.unregister(this.mContentResolver);
    }

    public boolean isAvailable() {
        return this.mDoFeatureSupportedScorersExist;
    }

    public void updateState(Preference preference) {
        if (preference instanceof SwitchPreference) {
            SwitchPreference switchPreference = (SwitchPreference) preference;
            boolean z = true;
            boolean z2 = this.mNetworkScoreManager.getActiveScorerPackage() != null;
            boolean z3 = this.mEnableUseWifiComponentName != null;
            switchPreference.setChecked(isSettingEnabled());
            switchPreference.setVisible(isAvailable());
            if (!z2 || !z3) {
                z = false;
            }
            switchPreference.setEnabled(z);
            if (!z2) {
                switchPreference.setSummary((int) C1715R.string.use_open_wifi_automatically_summary_scoring_disabled);
            } else if (!z3) {
                switchPreference.setSummary((int) C1715R.string.use_open_wifi_automatically_summary_scorer_unsupported_disabled);
            } else {
                switchPreference.setSummary((int) C1715R.string.use_open_wifi_automatically_summary);
            }
        }
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (TextUtils.equals(preference.getKey(), "use_open_wifi_automatically") && isAvailable()) {
            if (isSettingEnabled()) {
                Settings.Global.putString(this.mContentResolver, "use_open_wifi_package", "");
                return true;
            }
            Intent intent = new Intent("android.net.scoring.CUSTOM_ENABLE");
            intent.setComponent(this.mEnableUseWifiComponentName);
            this.mFragment.startActivityForResult(intent, 400);
        }
        return false;
    }

    private boolean isSettingEnabled() {
        String str;
        String string = Settings.Global.getString(this.mContentResolver, "use_open_wifi_package");
        ComponentName componentName = this.mEnableUseWifiComponentName;
        if (componentName == null) {
            str = null;
        } else {
            str = componentName.getPackageName();
        }
        return TextUtils.equals(string, str);
    }

    public boolean onActivityResult(int i, int i2) {
        if (i != 400) {
            return false;
        }
        if (i2 != -1) {
            return true;
        }
        Settings.Global.putString(this.mContentResolver, "use_open_wifi_package", this.mEnableUseWifiComponentName.getPackageName());
        return true;
    }

    class SettingObserver extends ContentObserver {
        private final Uri NETWORK_RECOMMENDATIONS_ENABLED_URI = Settings.Global.getUriFor("network_recommendations_enabled");

        public SettingObserver() {
            super(new Handler(Looper.getMainLooper()));
        }

        public void register(ContentResolver contentResolver) {
            contentResolver.registerContentObserver(this.NETWORK_RECOMMENDATIONS_ENABLED_URI, false, this);
            onChange(true, this.NETWORK_RECOMMENDATIONS_ENABLED_URI);
        }

        public void unregister(ContentResolver contentResolver) {
            contentResolver.unregisterContentObserver(this);
        }

        public void onChange(boolean z, Uri uri) {
            super.onChange(z, uri);
            if (this.NETWORK_RECOMMENDATIONS_ENABLED_URI.equals(uri)) {
                UseOpenWifiPreferenceController.this.updateEnableUseWifiComponentName();
                UseOpenWifiPreferenceController useOpenWifiPreferenceController = UseOpenWifiPreferenceController.this;
                useOpenWifiPreferenceController.updateState(useOpenWifiPreferenceController.mPreference);
            }
        }
    }
}
