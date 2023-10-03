package com.android.systemui.statusbar.notification;

import android.app.Notification;
import android.media.MediaMetadata;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import com.android.internal.statusbar.NotificationVisibility;
import com.android.systemui.statusbar.NotificationListener;
import com.android.systemui.statusbar.NotificationMediaManager;
import com.android.systemui.statusbar.NotificationRemoteInputManager;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.phone.ShadeController;
import com.android.systemui.statusbar.phone.StatusBar;
import com.android.systemui.statusbar.policy.HeadsUpManager;
import dagger.Lazy;

public class NotificationAlertingManager {
    private HeadsUpManager mHeadsUpManager;
    private final NotificationMediaManager mMediaManager;
    private final NotificationInterruptionStateProvider mNotificationInterruptionStateProvider;
    private final NotificationListener mNotificationListener;
    private final NotificationRemoteInputManager mRemoteInputManager;
    private final Lazy<ShadeController> mShadeController;
    private StatusBar mStatusBar;
    private final VisualStabilityManager mVisualStabilityManager;

    public NotificationAlertingManager(NotificationEntryManager notificationEntryManager, NotificationRemoteInputManager notificationRemoteInputManager, VisualStabilityManager visualStabilityManager, Lazy<ShadeController> lazy, NotificationInterruptionStateProvider notificationInterruptionStateProvider, NotificationListener notificationListener, NotificationMediaManager notificationMediaManager) {
        this.mRemoteInputManager = notificationRemoteInputManager;
        this.mVisualStabilityManager = visualStabilityManager;
        this.mShadeController = lazy;
        this.mNotificationInterruptionStateProvider = notificationInterruptionStateProvider;
        this.mNotificationListener = notificationListener;
        this.mMediaManager = notificationMediaManager;
        notificationEntryManager.addNotificationEntryListener(new NotificationEntryListener() {
            public void onEntryInflated(NotificationEntry notificationEntry, int i) {
                NotificationAlertingManager.this.showAlertingView(notificationEntry, i);
            }

            public void onPostEntryUpdated(NotificationEntry notificationEntry) {
                NotificationAlertingManager.this.updateAlertState(notificationEntry);
            }

            public void onEntryRemoved(NotificationEntry notificationEntry, NotificationVisibility notificationVisibility, boolean z) {
                NotificationAlertingManager.this.stopAlerting(notificationEntry.key);
            }
        });
    }

    public void setHeadsUpManager(HeadsUpManager headsUpManager) {
        this.mHeadsUpManager = headsUpManager;
    }

    /* access modifiers changed from: private */
    public void showAlertingView(NotificationEntry notificationEntry, int i) {
        if ((i & 4) == 0) {
            StatusBar statusBar = this.mStatusBar;
            if (statusBar != null && statusBar.mTickerEnabled) {
                statusBar.tick(notificationEntry.notification, true, false, (MediaMetadata) null, (String) null);
            }
        } else if (this.mNotificationInterruptionStateProvider.shouldHeadsUp(notificationEntry)) {
            this.mHeadsUpManager.showNotification(notificationEntry);
            if (!this.mShadeController.get().isDozing()) {
                setNotificationShown(notificationEntry.notification);
            }
        } else {
            notificationEntry.freeContentViewWhenSafe(4);
            StatusBar statusBar2 = this.mStatusBar;
            if (statusBar2 != null && statusBar2.mTickerEnabled) {
                statusBar2.tick(notificationEntry.notification, true, false, (MediaMetadata) null, (String) null);
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateAlertState(NotificationEntry notificationEntry) {
        boolean shouldHeadsUp = this.mNotificationInterruptionStateProvider.shouldHeadsUp(notificationEntry);
        boolean z = alertAgain(notificationEntry, notificationEntry.notification.getNotification()) || this.mMediaManager.isNewTrackNotification();
        if (this.mHeadsUpManager.isAlerting(notificationEntry.key)) {
            if (shouldHeadsUp) {
                this.mHeadsUpManager.updateNotification(notificationEntry.key, z);
            } else if (!this.mHeadsUpManager.isEntryAutoHeadsUpped(notificationEntry.key)) {
                this.mHeadsUpManager.removeNotification(notificationEntry.key, false);
            }
        } else if (shouldHeadsUp && z) {
            this.mHeadsUpManager.showNotification(notificationEntry);
        }
    }

    public static boolean alertAgain(NotificationEntry notificationEntry, Notification notification) {
        return notificationEntry == null || !notificationEntry.hasInterrupted() || (notification.flags & 8) == 0;
    }

    private void setNotificationShown(StatusBarNotification statusBarNotification) {
        try {
            this.mNotificationListener.setNotificationsShown(new String[]{statusBarNotification.getKey()});
        } catch (RuntimeException e) {
            Log.d("NotifAlertManager", "failed setNotificationsShown: ", e);
        }
    }

    /* access modifiers changed from: private */
    public void stopAlerting(String str) {
        if (this.mHeadsUpManager.isAlerting(str)) {
            this.mHeadsUpManager.removeNotification(str, (this.mRemoteInputManager.getController().isSpinning(str) && !NotificationRemoteInputManager.FORCE_REMOTE_INPUT_HISTORY) || !this.mVisualStabilityManager.isReorderingAllowed());
        }
    }

    public void setStatusBar(StatusBar statusBar) {
        this.mStatusBar = statusBar;
    }
}
