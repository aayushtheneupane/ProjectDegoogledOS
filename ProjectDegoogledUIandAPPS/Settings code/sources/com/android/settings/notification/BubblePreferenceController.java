package com.android.settings.notification;

import android.app.NotificationChannel;
import android.content.Context;
import android.provider.Settings;
import androidx.fragment.app.FragmentManager;
import androidx.preference.Preference;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settings.notification.NotificationBackend;
import com.android.settingslib.RestrictedSwitchPreference;
import com.havoc.config.center.C1715R;

public class BubblePreferenceController extends NotificationPreferenceController implements PreferenceControllerMixin, Preference.OnPreferenceChangeListener {
    static final int SYSTEM_WIDE_OFF = 0;
    static final int SYSTEM_WIDE_ON = 1;
    private FragmentManager mFragmentManager;
    private boolean mIsAppPage;

    public String getPreferenceKey() {
        return "bubble_pref";
    }

    public BubblePreferenceController(Context context, FragmentManager fragmentManager, NotificationBackend notificationBackend, boolean z) {
        super(context, notificationBackend);
        this.mFragmentManager = fragmentManager;
        this.mIsAppPage = z;
    }

    public boolean isAvailable() {
        if (!super.isAvailable()) {
            return false;
        }
        if (!this.mIsAppPage && !isGloballyEnabled()) {
            return false;
        }
        if (this.mChannel == null || isDefaultChannel()) {
            return true;
        }
        NotificationBackend.AppRow appRow = this.mAppRow;
        if (appRow == null || !appRow.allowBubbles) {
            return false;
        }
        return true;
    }

    public void updateState(Preference preference) {
        if (this.mAppRow != null) {
            RestrictedSwitchPreference restrictedSwitchPreference = (RestrictedSwitchPreference) preference;
            restrictedSwitchPreference.setDisabledByAdmin(this.mAdmin);
            NotificationChannel notificationChannel = this.mChannel;
            boolean z = false;
            if (notificationChannel != null) {
                if (notificationChannel.canBubble() && isGloballyEnabled()) {
                    z = true;
                }
                restrictedSwitchPreference.setChecked(z);
                restrictedSwitchPreference.setEnabled(!restrictedSwitchPreference.isDisabledByAdmin());
                return;
            }
            restrictedSwitchPreference.setChecked(this.mAppRow.allowBubbles && isGloballyEnabled());
            restrictedSwitchPreference.setSummary((CharSequence) this.mContext.getString(C1715R.string.bubbles_app_toggle_summary, new Object[]{this.mAppRow.label}));
        }
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        boolean z = ((Boolean) obj).booleanValue() && isGloballyEnabled();
        NotificationChannel notificationChannel = this.mChannel;
        if (notificationChannel != null) {
            notificationChannel.setAllowBubbles(z);
            saveChannel();
            return true;
        }
        if (!(this.mAppRow == null || this.mFragmentManager == null)) {
            RestrictedSwitchPreference restrictedSwitchPreference = (RestrictedSwitchPreference) preference;
            if (isGloballyEnabled() || restrictedSwitchPreference.isChecked()) {
                NotificationBackend.AppRow appRow = this.mAppRow;
                appRow.allowBubbles = z;
                this.mBackend.setAllowBubbles(appRow.pkg, appRow.uid, z);
            } else {
                BubbleWarningDialogFragment bubbleWarningDialogFragment = new BubbleWarningDialogFragment();
                NotificationBackend.AppRow appRow2 = this.mAppRow;
                bubbleWarningDialogFragment.setPkgInfo(appRow2.pkg, appRow2.uid).show(this.mFragmentManager, "dialog");
                return false;
            }
        }
        return true;
    }

    private boolean isGloballyEnabled() {
        return Settings.Global.getInt(this.mContext.getContentResolver(), "notification_bubbles", 0) == 1;
    }

    public static void revertBubblesApproval(Context context, String str, int i) {
        new NotificationBackend().setAllowBubbles(str, i, false);
        Settings.Global.putInt(context.getContentResolver(), "notification_bubbles", 0);
    }

    public static void applyBubblesApproval(Context context, String str, int i) {
        new NotificationBackend().setAllowBubbles(str, i, true);
        Settings.Global.putInt(context.getContentResolver(), "notification_bubbles", 1);
    }
}
