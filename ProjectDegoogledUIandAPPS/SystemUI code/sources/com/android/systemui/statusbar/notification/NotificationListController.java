package com.android.systemui.statusbar.notification;

import android.os.UserHandle;
import android.service.notification.StatusBarNotification;
import android.util.ArraySet;
import com.android.internal.statusbar.NotificationVisibility;
import com.android.internal.util.Preconditions;
import com.android.systemui.ForegroundServiceController;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.stack.NotificationListContainer;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;

public class NotificationListController {
    private final DeviceProvisionedController mDeviceProvisionedController;
    private final DeviceProvisionedController.DeviceProvisionedListener mDeviceProvisionedListener = new DeviceProvisionedController.DeviceProvisionedListener() {
        public void onDeviceProvisionedChanged() {
            NotificationListController.this.mEntryManager.updateNotifications();
        }
    };
    private final NotificationEntryListener mEntryListener = new NotificationEntryListener() {
        public void onEntryRemoved(NotificationEntry notificationEntry, NotificationVisibility notificationVisibility, boolean z) {
            NotificationListController.this.mListContainer.cleanUpViewStateForEntry(notificationEntry);
        }

        public void onBeforeNotificationAdded(NotificationEntry notificationEntry) {
            NotificationListController.this.tagForeground(notificationEntry.notification);
        }
    };
    /* access modifiers changed from: private */
    public final NotificationEntryManager mEntryManager;
    private final ForegroundServiceController mForegroundServiceController;
    /* access modifiers changed from: private */
    public final NotificationListContainer mListContainer;

    public NotificationListController(NotificationEntryManager notificationEntryManager, NotificationListContainer notificationListContainer, ForegroundServiceController foregroundServiceController, DeviceProvisionedController deviceProvisionedController) {
        this.mEntryManager = (NotificationEntryManager) Preconditions.checkNotNull(notificationEntryManager);
        this.mListContainer = (NotificationListContainer) Preconditions.checkNotNull(notificationListContainer);
        this.mForegroundServiceController = (ForegroundServiceController) Preconditions.checkNotNull(foregroundServiceController);
        this.mDeviceProvisionedController = (DeviceProvisionedController) Preconditions.checkNotNull(deviceProvisionedController);
    }

    public void bind() {
        this.mEntryManager.addNotificationEntryListener(this.mEntryListener);
        this.mDeviceProvisionedController.addCallback(this.mDeviceProvisionedListener);
    }

    /* access modifiers changed from: private */
    public void tagForeground(StatusBarNotification statusBarNotification) {
        ArraySet<Integer> appOps = this.mForegroundServiceController.getAppOps(statusBarNotification.getUserId(), statusBarNotification.getPackageName());
        if (appOps != null) {
            int size = appOps.size();
            for (int i = 0; i < size; i++) {
                updateNotificationsForAppOp(appOps.valueAt(i).intValue(), statusBarNotification.getUid(), statusBarNotification.getPackageName(), true);
            }
        }
    }

    public void updateNotificationsForAppOp(int i, int i2, String str, boolean z) {
        String standardLayoutKey = this.mForegroundServiceController.getStandardLayoutKey(UserHandle.getUserId(i2), str);
        if (standardLayoutKey != null) {
            this.mEntryManager.getNotificationData().updateAppOp(i, i2, str, standardLayoutKey, z);
            this.mEntryManager.updateNotifications();
        }
    }
}
