package com.android.settings.security;

import android.content.Context;
import android.os.UserHandle;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.internal.widget.LockPatternUtils;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settings.users.OwnerInfoSettings;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.RestrictedPreference;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnResume;
import com.havoc.config.center.C1715R;

public class OwnerInfoPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin, LifecycleObserver, OnResume {
    private static final int MY_USER_ID = UserHandle.myUserId();
    private final LockPatternUtils mLockPatternUtils;
    private RestrictedPreference mOwnerInfoPref;
    /* access modifiers changed from: private */
    public final Fragment mParent;

    public interface OwnerInfoCallback {
        void onOwnerInfoUpdated();
    }

    public String getPreferenceKey() {
        return "owner_info_settings";
    }

    public boolean isAvailable() {
        return true;
    }

    public OwnerInfoPreferenceController(Context context, Fragment fragment, Lifecycle lifecycle) {
        super(context);
        this.mParent = fragment;
        this.mLockPatternUtils = new LockPatternUtils(context);
        if (lifecycle != null) {
            lifecycle.addObserver(this);
        }
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        this.mOwnerInfoPref = (RestrictedPreference) preferenceScreen.findPreference("owner_info_settings");
    }

    public void onResume() {
        updateEnableState();
        updateSummary();
    }

    public void updateEnableState() {
        if (this.mOwnerInfoPref != null) {
            if (isDeviceOwnerInfoEnabled()) {
                this.mOwnerInfoPref.setDisabledByAdmin(getDeviceOwner());
                return;
            }
            this.mOwnerInfoPref.setDisabledByAdmin((RestrictedLockUtils.EnforcedAdmin) null);
            this.mOwnerInfoPref.setEnabled(!this.mLockPatternUtils.isLockScreenDisabled(MY_USER_ID));
            if (this.mOwnerInfoPref.isEnabled()) {
                this.mOwnerInfoPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                    public boolean onPreferenceClick(Preference preference) {
                        OwnerInfoSettings.show(OwnerInfoPreferenceController.this.mParent);
                        return true;
                    }
                });
            }
        }
    }

    public void updateSummary() {
        String str;
        if (this.mOwnerInfoPref == null) {
            return;
        }
        if (isDeviceOwnerInfoEnabled()) {
            this.mOwnerInfoPref.setSummary((CharSequence) getDeviceOwnerInfo());
            return;
        }
        RestrictedPreference restrictedPreference = this.mOwnerInfoPref;
        if (isOwnerInfoEnabled()) {
            str = getOwnerInfo();
        } else {
            str = this.mContext.getString(C1715R.string.owner_info_settings_summary);
        }
        restrictedPreference.setSummary((CharSequence) str);
    }

    /* access modifiers changed from: package-private */
    public boolean isDeviceOwnerInfoEnabled() {
        return this.mLockPatternUtils.isDeviceOwnerInfoEnabled();
    }

    /* access modifiers changed from: package-private */
    public String getDeviceOwnerInfo() {
        return this.mLockPatternUtils.getDeviceOwnerInfo();
    }

    /* access modifiers changed from: package-private */
    public boolean isOwnerInfoEnabled() {
        return this.mLockPatternUtils.isOwnerInfoEnabled(MY_USER_ID);
    }

    /* access modifiers changed from: package-private */
    public String getOwnerInfo() {
        return this.mLockPatternUtils.getOwnerInfo(MY_USER_ID);
    }

    /* access modifiers changed from: package-private */
    public RestrictedLockUtils.EnforcedAdmin getDeviceOwner() {
        return RestrictedLockUtilsInternal.getDeviceOwner(this.mContext);
    }
}
