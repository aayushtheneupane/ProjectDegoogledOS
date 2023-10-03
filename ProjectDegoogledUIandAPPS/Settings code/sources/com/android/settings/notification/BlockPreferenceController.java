package com.android.settings.notification;

import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.content.Context;
import android.widget.Switch;
import androidx.preference.Preference;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settings.notification.NotificationBackend;
import com.android.settings.notification.NotificationSettingsBase;
import com.android.settings.widget.SwitchBar;
import com.android.settingslib.widget.LayoutPreference;
import com.havoc.config.center.C1715R;

public class BlockPreferenceController extends NotificationPreferenceController implements PreferenceControllerMixin, SwitchBar.OnSwitchChangeListener {
    private NotificationSettingsBase.ImportanceListener mImportanceListener;

    public String getPreferenceKey() {
        return "block";
    }

    public BlockPreferenceController(Context context, NotificationSettingsBase.ImportanceListener importanceListener, NotificationBackend notificationBackend) {
        super(context, notificationBackend);
        this.mImportanceListener = importanceListener;
    }

    public boolean isAvailable() {
        return this.mAppRow != null;
    }

    public void updateState(Preference preference) {
        LayoutPreference layoutPreference = (LayoutPreference) preference;
        boolean z = false;
        layoutPreference.setSelectable(false);
        SwitchBar switchBar = (SwitchBar) layoutPreference.findViewById(C1715R.C1718id.switch_bar);
        if (switchBar != null) {
            switchBar.setSwitchBarText(C1715R.string.notification_switch_label, C1715R.string.notification_switch_label);
            switchBar.show();
            try {
                switchBar.addOnSwitchChangeListener(this);
            } catch (IllegalStateException unused) {
            }
            switchBar.setDisabledByAdmin(this.mAdmin);
            if (this.mChannel != null && !isChannelBlockable()) {
                switchBar.setEnabled(false);
            }
            if (this.mChannelGroup != null && !isChannelGroupBlockable()) {
                switchBar.setEnabled(false);
            }
            if (this.mChannel == null) {
                NotificationBackend.AppRow appRow = this.mAppRow;
                if (appRow.systemApp && (!appRow.banned || appRow.lockedImportance)) {
                    switchBar.setEnabled(false);
                }
            }
            NotificationChannel notificationChannel = this.mChannel;
            if (notificationChannel != null) {
                if (!this.mAppRow.banned && notificationChannel.getImportance() != 0) {
                    z = true;
                }
                switchBar.setChecked(z);
                return;
            }
            NotificationChannelGroup notificationChannelGroup = this.mChannelGroup;
            if (notificationChannelGroup != null) {
                if (!this.mAppRow.banned && !notificationChannelGroup.isBlocked()) {
                    z = true;
                }
                switchBar.setChecked(z);
                return;
            }
            switchBar.setChecked(!this.mAppRow.banned);
        }
    }

    public void onSwitchChanged(Switch switchR, boolean z) {
        int i;
        boolean z2 = true;
        boolean z3 = !z;
        NotificationChannel notificationChannel = this.mChannel;
        if (notificationChannel != null) {
            int importance = notificationChannel.getImportance();
            if (z3 || importance == 0) {
                if (z3) {
                    i = 0;
                } else {
                    i = isDefaultChannel() ? -1000 : 3;
                }
                this.mChannel.setImportance(i);
                saveChannel();
            }
            NotificationBackend notificationBackend = this.mBackend;
            NotificationBackend.AppRow appRow = this.mAppRow;
            if (notificationBackend.onlyHasDefaultChannel(appRow.pkg, appRow.uid)) {
                NotificationBackend.AppRow appRow2 = this.mAppRow;
                if (appRow2.banned != z3) {
                    appRow2.banned = z3;
                    NotificationBackend notificationBackend2 = this.mBackend;
                    String str = appRow2.pkg;
                    int i2 = appRow2.uid;
                    if (z3) {
                        z2 = false;
                    }
                    notificationBackend2.setNotificationsEnabledForPackage(str, i2, z2);
                }
            }
        } else {
            NotificationChannelGroup notificationChannelGroup = this.mChannelGroup;
            if (notificationChannelGroup != null) {
                notificationChannelGroup.setBlocked(z3);
                NotificationBackend notificationBackend3 = this.mBackend;
                NotificationBackend.AppRow appRow3 = this.mAppRow;
                notificationBackend3.updateChannelGroup(appRow3.pkg, appRow3.uid, this.mChannelGroup);
            } else {
                NotificationBackend.AppRow appRow4 = this.mAppRow;
                if (appRow4 != null) {
                    appRow4.banned = z3;
                    NotificationBackend notificationBackend4 = this.mBackend;
                    String str2 = appRow4.pkg;
                    int i3 = appRow4.uid;
                    if (z3) {
                        z2 = false;
                    }
                    notificationBackend4.setNotificationsEnabledForPackage(str2, i3, z2);
                }
            }
        }
        this.mImportanceListener.onImportanceChanged();
    }
}
