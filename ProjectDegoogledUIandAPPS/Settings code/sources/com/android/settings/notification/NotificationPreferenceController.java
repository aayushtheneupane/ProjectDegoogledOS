package com.android.settings.notification;

import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.UserManager;
import android.util.Log;
import com.android.settings.notification.NotificationBackend;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.core.AbstractPreferenceController;
import java.util.Objects;

public abstract class NotificationPreferenceController extends AbstractPreferenceController {
    protected RestrictedLockUtils.EnforcedAdmin mAdmin;
    protected NotificationBackend.AppRow mAppRow;
    protected final NotificationBackend mBackend;
    protected NotificationChannel mChannel;
    protected NotificationChannelGroup mChannelGroup;
    protected final Context mContext;
    protected final NotificationManager mNm = ((NotificationManager) this.mContext.getSystemService("notification"));
    protected final PackageManager mPm;
    protected final UserManager mUm;

    public NotificationPreferenceController(Context context, NotificationBackend notificationBackend) {
        super(context);
        this.mContext = context;
        this.mBackend = notificationBackend;
        this.mUm = (UserManager) this.mContext.getSystemService("user");
        this.mPm = this.mContext.getPackageManager();
    }

    public boolean isAvailable() {
        NotificationBackend.AppRow appRow = this.mAppRow;
        if (appRow == null || appRow.banned) {
            return false;
        }
        NotificationChannelGroup notificationChannelGroup = this.mChannelGroup;
        if (notificationChannelGroup != null && notificationChannelGroup.isBlocked()) {
            return false;
        }
        NotificationChannel notificationChannel = this.mChannel;
        if (notificationChannel == null || notificationChannel.getImportance() != 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onResume(NotificationBackend.AppRow appRow, NotificationChannel notificationChannel, NotificationChannelGroup notificationChannelGroup, RestrictedLockUtils.EnforcedAdmin enforcedAdmin) {
        this.mAppRow = appRow;
        this.mChannel = notificationChannel;
        this.mChannelGroup = notificationChannelGroup;
        this.mAdmin = enforcedAdmin;
    }

    /* access modifiers changed from: protected */
    public boolean checkCanBeVisible(int i) {
        NotificationChannel notificationChannel = this.mChannel;
        if (notificationChannel == null) {
            Log.w("ChannelPrefContr", "No channel");
            return false;
        }
        int importance = notificationChannel.getImportance();
        if (importance == -1000) {
            return true;
        }
        if (importance >= i) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void saveChannel() {
        NotificationBackend.AppRow appRow;
        NotificationChannel notificationChannel = this.mChannel;
        if (notificationChannel != null && (appRow = this.mAppRow) != null) {
            this.mBackend.updateChannel(appRow.pkg, appRow.uid, notificationChannel);
        }
    }

    /* access modifiers changed from: protected */
    public boolean isChannelBlockable() {
        return isChannelBlockable(this.mChannel);
    }

    /* access modifiers changed from: protected */
    public boolean isChannelBlockable(NotificationChannel notificationChannel) {
        if (notificationChannel == null || this.mAppRow == null) {
            return false;
        }
        if (notificationChannel.isImportanceLockedByCriticalDeviceFunction() || notificationChannel.isImportanceLockedByOEM()) {
            if (notificationChannel.getImportance() == 0) {
                return true;
            }
            return false;
        } else if (notificationChannel.isBlockableSystem() || !this.mAppRow.systemApp || notificationChannel.getImportance() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public boolean isChannelConfigurable(NotificationChannel notificationChannel) {
        if (notificationChannel == null || this.mAppRow == null) {
            return false;
        }
        return !notificationChannel.isImportanceLockedByOEM();
    }

    /* access modifiers changed from: protected */
    public boolean isChannelGroupBlockable() {
        return isChannelGroupBlockable(this.mChannelGroup);
    }

    /* access modifiers changed from: protected */
    public boolean isChannelGroupBlockable(NotificationChannelGroup notificationChannelGroup) {
        NotificationBackend.AppRow appRow;
        if (notificationChannelGroup == null || (appRow = this.mAppRow) == null) {
            return false;
        }
        if (!appRow.systemApp) {
            return true;
        }
        return notificationChannelGroup.isBlocked();
    }

    /* access modifiers changed from: protected */
    public boolean hasValidGroup() {
        return this.mChannelGroup != null;
    }

    /* access modifiers changed from: protected */
    public final boolean isDefaultChannel() {
        NotificationChannel notificationChannel = this.mChannel;
        if (notificationChannel == null) {
            return false;
        }
        return Objects.equals("miscellaneous", notificationChannel.getId());
    }
}
