package com.android.settings.notification;

import android.app.KeyguardManager;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Settings;
import com.android.settings.Utils;
import com.android.settings.core.TogglePreferenceController;
import com.android.settings.overlay.FeatureFactory;

public class RedactNotificationPreferenceController extends TogglePreferenceController {
    static final String KEY_LOCKSCREEN_REDACT = "lock_screen_redact";
    static final String KEY_LOCKSCREEN_WORK_PROFILE_REDACT = "lock_screen_work_redact";
    private static final String TAG = "LockScreenNotifPref";
    private DevicePolicyManager mDpm;
    private KeyguardManager mKm;
    private final int mProfileUserId = Utils.getManagedProfileId(this.mUm, UserHandle.myUserId());
    private UserManager mUm;

    public RedactNotificationPreferenceController(Context context, String str) {
        super(context, str);
        this.mUm = (UserManager) context.getSystemService(UserManager.class);
        this.mDpm = (DevicePolicyManager) context.getSystemService(DevicePolicyManager.class);
        this.mKm = (KeyguardManager) context.getSystemService(KeyguardManager.class);
    }

    public boolean isChecked() {
        return getAllowPrivateNotifications(KEY_LOCKSCREEN_REDACT.equals(getPreferenceKey()) ? UserHandle.myUserId() : this.mProfileUserId);
    }

    public boolean setChecked(boolean z) {
        Settings.Secure.putIntForUser(this.mContext.getContentResolver(), "lock_screen_allow_private_notifications", z ? 1 : 0, KEY_LOCKSCREEN_REDACT.equals(getPreferenceKey()) ? UserHandle.myUserId() : this.mProfileUserId);
        return true;
    }

    public int getAvailabilityStatus() {
        if (KEY_LOCKSCREEN_WORK_PROFILE_REDACT.equals(getPreferenceKey()) && this.mProfileUserId == -10000) {
            return 2;
        }
        int myUserId = KEY_LOCKSCREEN_REDACT.equals(getPreferenceKey()) ? UserHandle.myUserId() : this.mProfileUserId;
        if (!FeatureFactory.getFactory(this.mContext).getSecurityFeatureProvider().getLockPatternUtils(this.mContext).isSecure(myUserId)) {
            return 2;
        }
        if (!getLockscreenNotificationsEnabled(myUserId) || !adminAllowsNotifications(myUserId) || !adminAllowsUnredactedNotifications(myUserId)) {
            return 5;
        }
        if (!KEY_LOCKSCREEN_WORK_PROFILE_REDACT.equals(getPreferenceKey()) || !this.mKm.isDeviceLocked(this.mProfileUserId)) {
            return 0;
        }
        return 5;
    }

    private boolean adminAllowsNotifications(int i) {
        return (this.mDpm.getKeyguardDisabledFeatures((ComponentName) null, i) & 4) == 0;
    }

    private boolean adminAllowsUnredactedNotifications(int i) {
        return (this.mDpm.getKeyguardDisabledFeatures((ComponentName) null, i) & 8) == 0;
    }

    private boolean getAllowPrivateNotifications(int i) {
        return Settings.Secure.getIntForUser(this.mContext.getContentResolver(), "lock_screen_allow_private_notifications", 1, i) != 0;
    }

    private boolean getLockscreenNotificationsEnabled(int i) {
        return Settings.Secure.getIntForUser(this.mContext.getContentResolver(), "lock_screen_show_notifications", 1, i) != 0;
    }
}
