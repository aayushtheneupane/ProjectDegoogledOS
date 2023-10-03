package com.android.settings.users;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.UserInfo;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.SwitchPreference;

public class UserDetailsSettings extends SettingsPreferenceFragment implements Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener {
    private Bundle mDefaultGuestRestrictions;
    private boolean mGuestUser;
    private SwitchPreference mPhonePref;
    private Preference mRemoveUserPref;
    private UserInfo mUserInfo;
    private UserManager mUserManager;

    public int getDialogMetricsCategory(int i) {
        if (i == 1) {
            return 591;
        }
        if (i != 2) {
            return i != 3 ? 0 : 593;
        }
        return 592;
    }

    public int getMetricsCategory() {
        return 98;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        FragmentActivity activity = getActivity();
        this.mUserManager = (UserManager) activity.getSystemService("user");
        addPreferencesFromResource(C1715R.xml.user_details_settings);
        this.mPhonePref = (SwitchPreference) findPreference("enable_calling");
        this.mRemoveUserPref = findPreference("remove_user");
        this.mGuestUser = getArguments().getBoolean("guest_user", false);
        if (!this.mGuestUser) {
            int i = getArguments().getInt("user_id", -1);
            if (i != -1) {
                this.mUserInfo = this.mUserManager.getUserInfo(i);
                this.mPhonePref.setChecked(!this.mUserManager.hasUserRestriction("no_outgoing_calls", new UserHandle(i)));
                this.mRemoveUserPref.setOnPreferenceClickListener(this);
            } else {
                throw new RuntimeException("Arguments to this fragment must contain the user id");
            }
        } else {
            removePreference("remove_user");
            this.mPhonePref.setTitle((int) C1715R.string.user_enable_calling);
            this.mDefaultGuestRestrictions = this.mUserManager.getDefaultGuestRestrictions();
            this.mPhonePref.setChecked(!this.mDefaultGuestRestrictions.getBoolean("no_outgoing_calls"));
        }
        if (RestrictedLockUtilsInternal.hasBaseUserRestriction(activity, "no_remove_user", UserHandle.myUserId())) {
            removePreference("remove_user");
        }
        this.mPhonePref.setOnPreferenceChangeListener(this);
    }

    public boolean onPreferenceClick(Preference preference) {
        if (preference != this.mRemoveUserPref) {
            return false;
        }
        if (this.mUserManager.isAdminUser()) {
            showDialog(1);
            return true;
        }
        throw new RuntimeException("Only admins can remove a user");
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (Boolean.TRUE.equals(obj)) {
            showDialog(this.mGuestUser ? 2 : 3);
            return false;
        }
        enableCallsAndSms(false);
        return true;
    }

    /* access modifiers changed from: package-private */
    public void enableCallsAndSms(boolean z) {
        this.mPhonePref.setChecked(z);
        if (this.mGuestUser) {
            this.mDefaultGuestRestrictions.putBoolean("no_outgoing_calls", !z);
            this.mDefaultGuestRestrictions.putBoolean("no_sms", true);
            this.mUserManager.setDefaultGuestRestrictions(this.mDefaultGuestRestrictions);
            for (UserInfo userInfo : this.mUserManager.getUsers(true)) {
                if (userInfo.isGuest()) {
                    UserHandle of = UserHandle.of(userInfo.id);
                    for (String str : this.mDefaultGuestRestrictions.keySet()) {
                        this.mUserManager.setUserRestriction(str, this.mDefaultGuestRestrictions.getBoolean(str), of);
                    }
                }
            }
            return;
        }
        UserHandle of2 = UserHandle.of(this.mUserInfo.id);
        this.mUserManager.setUserRestriction("no_outgoing_calls", !z, of2);
        this.mUserManager.setUserRestriction("no_sms", !z, of2);
    }

    public Dialog onCreateDialog(int i) {
        if (getActivity() == null) {
            return null;
        }
        if (i == 1) {
            return UserDialogs.createRemoveDialog(getActivity(), this.mUserInfo.id, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    UserDetailsSettings.this.removeUser();
                }
            });
        }
        if (i == 2) {
            return UserDialogs.createEnablePhoneCallsDialog(getActivity(), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    UserDetailsSettings.this.enableCallsAndSms(true);
                }
            });
        }
        if (i == 3) {
            return UserDialogs.createEnablePhoneCallsAndSmsDialog(getActivity(), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    UserDetailsSettings.this.enableCallsAndSms(true);
                }
            });
        }
        throw new IllegalArgumentException("Unsupported dialogId " + i);
    }

    /* access modifiers changed from: package-private */
    public void removeUser() {
        this.mUserManager.removeUser(this.mUserInfo.id);
        finishFragment();
    }
}
