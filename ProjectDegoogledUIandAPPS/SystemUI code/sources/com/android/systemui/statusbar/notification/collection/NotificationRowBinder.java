package com.android.systemui.statusbar.notification.collection;

import com.android.systemui.statusbar.NotificationUiAdjustment;
import com.android.systemui.statusbar.notification.InflationException;

public interface NotificationRowBinder {
    void inflateViews(NotificationEntry notificationEntry, Runnable runnable) throws InflationException;

    void onNotificationRankingUpdated(NotificationEntry notificationEntry, Integer num, NotificationUiAdjustment notificationUiAdjustment, NotificationUiAdjustment notificationUiAdjustment2);
}
