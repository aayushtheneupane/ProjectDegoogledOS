package com.android.dialer.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.Pair;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import java.util.HashSet;
import java.util.Set;

public final class DialerNotificationManager {
    private static final Set<StatusBarNotification> throttledNotificationSet = new HashSet();

    public static void cancel(Context context, String str, int i) {
        String str2;
        Assert.isNotNull(context);
        Assert.checkArgument(!TextUtils.isEmpty(str));
        NotificationManager notificationManager = getNotificationManager(context);
        StatusBarNotification[] activeNotifications = notificationManager.getActiveNotifications();
        int length = activeNotifications.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                str2 = null;
                break;
            }
            StatusBarNotification statusBarNotification = activeNotifications[i2];
            if (TextUtils.equals(str, statusBarNotification.getTag()) && i == statusBarNotification.getId()) {
                str2 = statusBarNotification.getNotification().getGroup();
                break;
            }
            i2++;
        }
        if (!TextUtils.isEmpty(str2)) {
            int i3 = 0;
            StatusBarNotification statusBarNotification2 = null;
            for (StatusBarNotification statusBarNotification3 : activeNotifications) {
                if (TextUtils.equals(str2, statusBarNotification3.getNotification().getGroup())) {
                    if ((statusBarNotification3.getNotification().flags & 512) != 0) {
                        statusBarNotification2 = statusBarNotification3;
                    } else {
                        i3++;
                    }
                }
            }
            Pair pair = new Pair(statusBarNotification2, Integer.valueOf(i3));
            if (pair.first != null && ((Integer) pair.second).intValue() <= 1) {
                LogUtil.m9i("DialerNotificationManager.cancel", "last notification in group (%s) removed, also removing group summary", str2);
                notificationManager.cancel(((StatusBarNotification) pair.first).getTag(), ((StatusBarNotification) pair.first).getId());
            }
        }
        notificationManager.cancel(str, i);
    }

    public static void cancelAll(Context context, String str) {
        NotificationManager notificationManager = getNotificationManager(context);
        for (StatusBarNotification statusBarNotification : notificationManager.getActiveNotifications()) {
            if (statusBarNotification.getTag() != null && statusBarNotification.getTag().startsWith(str)) {
                notificationManager.cancel(statusBarNotification.getTag(), statusBarNotification.getId());
            }
        }
    }

    public static StatusBarNotification[] getActiveNotifications(Context context) {
        Assert.isNotNull(context);
        return getNotificationManager(context).getActiveNotifications();
    }

    private static NotificationManager getNotificationManager(Context context) {
        return (NotificationManager) context.getSystemService(NotificationManager.class);
    }

    public static Set<StatusBarNotification> getThrottledNotificationSet() {
        return throttledNotificationSet;
    }

    public static void notify(Context context, String str, int i, Notification notification) {
        Assert.isNotNull(context);
        Assert.isNotNull(notification);
        Assert.checkArgument(!TextUtils.isEmpty(str));
        int i2 = Build.VERSION.SDK_INT;
        Assert.checkArgument(!TextUtils.isEmpty(notification.getChannelId()));
        getNotificationManager(context).notify(str, i, notification);
        throttledNotificationSet.addAll(NotificationChannelManager.throttle(context, notification));
    }
}
