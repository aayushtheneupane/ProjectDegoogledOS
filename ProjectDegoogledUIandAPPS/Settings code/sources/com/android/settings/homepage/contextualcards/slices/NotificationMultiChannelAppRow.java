package com.android.settings.homepage.contextualcards.slices;

import android.app.role.RoleManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import com.android.settings.notification.NotificationBackend;
import java.util.concurrent.Callable;

class NotificationMultiChannelAppRow implements Callable<NotificationBackend.AppRow> {
    private final Context mContext;
    private final NotificationBackend mNotificationBackend;
    private final PackageInfo mPackageInfo;

    public NotificationMultiChannelAppRow(Context context, NotificationBackend notificationBackend, PackageInfo packageInfo) {
        this.mContext = context;
        this.mNotificationBackend = notificationBackend;
        this.mPackageInfo = packageInfo;
    }

    public NotificationBackend.AppRow call() throws Exception {
        if (this.mNotificationBackend.getChannelCount(this.mPackageInfo.applicationInfo.packageName, this.mPackageInfo.applicationInfo.uid) <= 1) {
            return null;
        }
        NotificationBackend notificationBackend = this.mNotificationBackend;
        Context context = this.mContext;
        return notificationBackend.loadAppRow(context, context.getPackageManager(), (RoleManager) this.mContext.getSystemService(RoleManager.class), this.mPackageInfo);
    }
}
