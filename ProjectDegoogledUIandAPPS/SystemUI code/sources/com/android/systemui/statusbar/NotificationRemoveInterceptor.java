package com.android.systemui.statusbar;

public interface NotificationRemoveInterceptor {
    boolean onNotificationRemoveRequested(String str, int i);
}
