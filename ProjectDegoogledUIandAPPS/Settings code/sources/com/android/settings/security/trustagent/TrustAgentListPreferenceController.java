package com.android.settings.security.trustagent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.text.TextUtils;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceScreen;
import com.android.internal.widget.LockPatternUtils;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.password.ChooseLockSettingsHelper;
import com.android.settings.security.SecurityFeatureProvider;
import com.android.settings.security.SecuritySettings;
import com.android.settings.security.trustagent.TrustAgentManager;
import com.android.settingslib.RestrictedPreference;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnCreate;
import com.android.settingslib.core.lifecycle.events.OnResume;
import com.android.settingslib.core.lifecycle.events.OnSaveInstanceState;
import com.havoc.config.center.C1715R;
import java.util.List;

public class TrustAgentListPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin, LifecycleObserver, OnSaveInstanceState, OnCreate, OnResume {
    private static final int MY_USER_ID = UserHandle.myUserId();
    static final String PREF_KEY_SECURITY_CATEGORY = "security_category";
    static final String PREF_KEY_TRUST_AGENT = "trust_agent";
    private final SecuritySettings mHost;
    private final LockPatternUtils mLockPatternUtils;
    private PreferenceCategory mSecurityCategory;
    private Intent mTrustAgentClickIntent;
    private final TrustAgentManager mTrustAgentManager;

    public String getPreferenceKey() {
        return PREF_KEY_TRUST_AGENT;
    }

    public TrustAgentListPreferenceController(Context context, SecuritySettings securitySettings, Lifecycle lifecycle) {
        super(context);
        SecurityFeatureProvider securityFeatureProvider = FeatureFactory.getFactory(context).getSecurityFeatureProvider();
        this.mHost = securitySettings;
        this.mLockPatternUtils = securityFeatureProvider.getLockPatternUtils(context);
        this.mTrustAgentManager = securityFeatureProvider.getTrustAgentManager();
        if (lifecycle != null) {
            lifecycle.addObserver(this);
        }
    }

    public boolean isAvailable() {
        return this.mContext.getResources().getBoolean(C1715R.bool.config_show_trust_agent_click_intent);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mSecurityCategory = (PreferenceCategory) preferenceScreen.findPreference(PREF_KEY_SECURITY_CATEGORY);
        updateTrustAgents();
    }

    public void onCreate(Bundle bundle) {
        if (bundle != null && bundle.containsKey("trust_agent_click_intent")) {
            this.mTrustAgentClickIntent = (Intent) bundle.getParcelable("trust_agent_click_intent");
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        Intent intent = this.mTrustAgentClickIntent;
        if (intent != null) {
            bundle.putParcelable("trust_agent_click_intent", intent);
        }
    }

    public boolean handlePreferenceTreeClick(Preference preference) {
        Intent intent;
        if (!TextUtils.equals(preference.getKey(), getPreferenceKey())) {
            return super.handlePreferenceTreeClick(preference);
        }
        ChooseLockSettingsHelper chooseLockSettingsHelper = new ChooseLockSettingsHelper(this.mHost.getActivity(), this.mHost);
        this.mTrustAgentClickIntent = preference.getIntent();
        if (chooseLockSettingsHelper.launchConfirmationActivity(126, preference.getTitle()) || (intent = this.mTrustAgentClickIntent) == null) {
            return true;
        }
        this.mHost.startActivity(intent);
        this.mTrustAgentClickIntent = null;
        return true;
    }

    public void onResume() {
        updateTrustAgents();
    }

    private void updateTrustAgents() {
        if (this.mSecurityCategory != null) {
            while (true) {
                Preference findPreference = this.mSecurityCategory.findPreference(PREF_KEY_TRUST_AGENT);
                if (findPreference == null) {
                    break;
                }
                this.mSecurityCategory.removePreference(findPreference);
            }
            if (isAvailable()) {
                boolean isSecure = this.mLockPatternUtils.isSecure(MY_USER_ID);
                List<TrustAgentManager.TrustAgentComponentInfo> activeTrustAgents = this.mTrustAgentManager.getActiveTrustAgents(this.mContext, this.mLockPatternUtils);
                if (activeTrustAgents != null) {
                    for (TrustAgentManager.TrustAgentComponentInfo next : activeTrustAgents) {
                        RestrictedPreference restrictedPreference = new RestrictedPreference(this.mSecurityCategory.getContext());
                        restrictedPreference.setKey(PREF_KEY_TRUST_AGENT);
                        restrictedPreference.setTitle((CharSequence) next.title);
                        restrictedPreference.setSummary((CharSequence) next.summary);
                        restrictedPreference.setIntent(new Intent("android.intent.action.MAIN").setComponent(next.componentName));
                        restrictedPreference.setDisabledByAdmin(next.admin);
                        if (!restrictedPreference.isDisabledByAdmin() && !isSecure) {
                            restrictedPreference.setEnabled(false);
                            restrictedPreference.setSummary((int) C1715R.string.disabled_because_no_backup_security);
                        }
                        this.mSecurityCategory.addPreference(restrictedPreference);
                    }
                }
            }
        }
    }

    public boolean handleActivityResult(int i, int i2) {
        if (i != 126 || i2 != -1) {
            return false;
        }
        Intent intent = this.mTrustAgentClickIntent;
        if (intent == null) {
            return true;
        }
        this.mHost.startActivity(intent);
        this.mTrustAgentClickIntent = null;
        return true;
    }
}
