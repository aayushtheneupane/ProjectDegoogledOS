package com.android.systemui.statusbar.phone;

import dagger.internal.Factory;

public final class NotificationGroupAlertTransferHelper_Factory implements Factory<NotificationGroupAlertTransferHelper> {
    private static final NotificationGroupAlertTransferHelper_Factory INSTANCE = new NotificationGroupAlertTransferHelper_Factory();

    public NotificationGroupAlertTransferHelper get() {
        return provideInstance();
    }

    public static NotificationGroupAlertTransferHelper provideInstance() {
        return new NotificationGroupAlertTransferHelper();
    }

    public static NotificationGroupAlertTransferHelper_Factory create() {
        return INSTANCE;
    }
}
