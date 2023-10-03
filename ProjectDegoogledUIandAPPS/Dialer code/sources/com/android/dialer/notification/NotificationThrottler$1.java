package com.android.dialer.notification;

import android.service.notification.StatusBarNotification;
import java.util.Comparator;

class NotificationThrottler$1 implements Comparator<StatusBarNotification> {
    NotificationThrottler$1() {
    }

    public int compare(Object obj, Object obj2) {
        return Long.compare(((StatusBarNotification) obj).getPostTime(), ((StatusBarNotification) obj2).getPostTime());
    }
}
