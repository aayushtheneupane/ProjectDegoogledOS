package com.android.systemui.statusbar.phone;

import android.os.SystemClock;
import android.service.notification.StatusBarNotification;
import android.util.ArrayMap;
import com.android.internal.statusbar.NotificationVisibility;
import com.android.systemui.Dependency;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.AlertingNotificationManager;
import com.android.systemui.statusbar.notification.NotificationEntryListener;
import com.android.systemui.statusbar.notification.NotificationEntryManager;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.phone.NotificationGroupManager;
import com.android.systemui.statusbar.policy.HeadsUpManager;
import com.android.systemui.statusbar.policy.OnHeadsUpChangedListener;
import java.util.Objects;

public class NotificationGroupAlertTransferHelper implements OnHeadsUpChangedListener, StatusBarStateController.StateListener {
    private NotificationEntryManager mEntryManager;
    /* access modifiers changed from: private */
    public final ArrayMap<String, GroupAlertEntry> mGroupAlertEntries = new ArrayMap<>();
    /* access modifiers changed from: private */
    public final NotificationGroupManager mGroupManager = ((NotificationGroupManager) Dependency.get(NotificationGroupManager.class));
    /* access modifiers changed from: private */
    public HeadsUpManager mHeadsUpManager;
    private boolean mIsDozing;
    private final NotificationEntryListener mNotificationEntryListener = new NotificationEntryListener() {
        public void onPendingEntryAdded(NotificationEntry notificationEntry) {
            GroupAlertEntry groupAlertEntry = (GroupAlertEntry) NotificationGroupAlertTransferHelper.this.mGroupAlertEntries.get(NotificationGroupAlertTransferHelper.this.mGroupManager.getGroupKey(notificationEntry.notification));
            if (groupAlertEntry != null) {
                NotificationGroupAlertTransferHelper.this.checkShouldTransferBack(groupAlertEntry);
            }
        }

        public void onEntryReinflated(NotificationEntry notificationEntry) {
            PendingAlertInfo pendingAlertInfo = (PendingAlertInfo) NotificationGroupAlertTransferHelper.this.mPendingAlerts.remove(notificationEntry.key);
            if (pendingAlertInfo == null) {
                return;
            }
            if (pendingAlertInfo.isStillValid()) {
                NotificationGroupAlertTransferHelper notificationGroupAlertTransferHelper = NotificationGroupAlertTransferHelper.this;
                notificationGroupAlertTransferHelper.alertNotificationWhenPossible(notificationEntry, notificationGroupAlertTransferHelper.mHeadsUpManager);
                return;
            }
            notificationEntry.getRow().freeContentViewWhenSafe(NotificationGroupAlertTransferHelper.this.mHeadsUpManager.getContentFlag());
        }

        public void onEntryRemoved(NotificationEntry notificationEntry, NotificationVisibility notificationVisibility, boolean z) {
            NotificationGroupAlertTransferHelper.this.mPendingAlerts.remove(notificationEntry.key);
        }
    };
    private final NotificationGroupManager.OnGroupChangeListener mOnGroupChangeListener = new NotificationGroupManager.OnGroupChangeListener() {
        public void onGroupCreated(NotificationGroupManager.NotificationGroup notificationGroup, String str) {
            NotificationGroupAlertTransferHelper.this.mGroupAlertEntries.put(str, new GroupAlertEntry(notificationGroup));
        }

        public void onGroupRemoved(NotificationGroupManager.NotificationGroup notificationGroup, String str) {
            NotificationGroupAlertTransferHelper.this.mGroupAlertEntries.remove(str);
        }

        public void onGroupSuppressionChanged(NotificationGroupManager.NotificationGroup notificationGroup, boolean z) {
            if (z) {
                if (NotificationGroupAlertTransferHelper.this.mHeadsUpManager.isAlerting(notificationGroup.summary.key)) {
                    NotificationGroupAlertTransferHelper notificationGroupAlertTransferHelper = NotificationGroupAlertTransferHelper.this;
                    notificationGroupAlertTransferHelper.handleSuppressedSummaryAlerted(notificationGroup.summary, notificationGroupAlertTransferHelper.mHeadsUpManager);
                }
            } else if (notificationGroup.summary != null) {
                GroupAlertEntry groupAlertEntry = (GroupAlertEntry) NotificationGroupAlertTransferHelper.this.mGroupAlertEntries.get(NotificationGroupAlertTransferHelper.this.mGroupManager.getGroupKey(notificationGroup.summary.notification));
                if (groupAlertEntry.mAlertSummaryOnNextAddition) {
                    if (!NotificationGroupAlertTransferHelper.this.mHeadsUpManager.isAlerting(notificationGroup.summary.key)) {
                        NotificationGroupAlertTransferHelper notificationGroupAlertTransferHelper2 = NotificationGroupAlertTransferHelper.this;
                        notificationGroupAlertTransferHelper2.alertNotificationWhenPossible(notificationGroup.summary, notificationGroupAlertTransferHelper2.mHeadsUpManager);
                    }
                    groupAlertEntry.mAlertSummaryOnNextAddition = false;
                    return;
                }
                NotificationGroupAlertTransferHelper.this.checkShouldTransferBack(groupAlertEntry);
            }
        }
    };
    /* access modifiers changed from: private */
    public final ArrayMap<String, PendingAlertInfo> mPendingAlerts = new ArrayMap<>();

    public void onStateChanged(int i) {
    }

    public NotificationGroupAlertTransferHelper() {
        ((StatusBarStateController) Dependency.get(StatusBarStateController.class)).addCallback(this);
    }

    public void bind(NotificationEntryManager notificationEntryManager, NotificationGroupManager notificationGroupManager) {
        if (this.mEntryManager == null) {
            this.mEntryManager = notificationEntryManager;
            this.mEntryManager.addNotificationEntryListener(this.mNotificationEntryListener);
            notificationGroupManager.addOnGroupChangeListener(this.mOnGroupChangeListener);
            return;
        }
        throw new IllegalStateException("Already bound.");
    }

    public void setHeadsUpManager(HeadsUpManager headsUpManager) {
        this.mHeadsUpManager = headsUpManager;
    }

    public void onDozingChanged(boolean z) {
        if (this.mIsDozing != z) {
            for (GroupAlertEntry next : this.mGroupAlertEntries.values()) {
                next.mLastAlertTransferTime = 0;
                next.mAlertSummaryOnNextAddition = false;
            }
        }
        this.mIsDozing = z;
    }

    public void onHeadsUpStateChanged(NotificationEntry notificationEntry, boolean z) {
        onAlertStateChanged(notificationEntry, z, this.mHeadsUpManager);
    }

    private void onAlertStateChanged(NotificationEntry notificationEntry, boolean z, AlertingNotificationManager alertingNotificationManager) {
        if (z && this.mGroupManager.isSummaryOfSuppressedGroup(notificationEntry.notification)) {
            handleSuppressedSummaryAlerted(notificationEntry, alertingNotificationManager);
        }
    }

    private int getPendingChildrenNotAlerting(NotificationGroupManager.NotificationGroup notificationGroup) {
        NotificationEntryManager notificationEntryManager = this.mEntryManager;
        int i = 0;
        if (notificationEntryManager == null) {
            return 0;
        }
        for (NotificationEntry next : notificationEntryManager.getPendingNotificationsIterator()) {
            if (isPendingNotificationInGroup(next, notificationGroup) && onlySummaryAlerts(next)) {
                i++;
            }
        }
        return i;
    }

    private boolean pendingInflationsWillAddChildren(NotificationGroupManager.NotificationGroup notificationGroup) {
        NotificationEntryManager notificationEntryManager = this.mEntryManager;
        if (notificationEntryManager == null) {
            return false;
        }
        for (NotificationEntry isPendingNotificationInGroup : notificationEntryManager.getPendingNotificationsIterator()) {
            if (isPendingNotificationInGroup(isPendingNotificationInGroup, notificationGroup)) {
                return true;
            }
        }
        return false;
    }

    private boolean isPendingNotificationInGroup(NotificationEntry notificationEntry, NotificationGroupManager.NotificationGroup notificationGroup) {
        return this.mGroupManager.isGroupChild(notificationEntry.notification) && Objects.equals(this.mGroupManager.getGroupKey(notificationEntry.notification), this.mGroupManager.getGroupKey(notificationGroup.summary.notification)) && !notificationGroup.children.containsKey(notificationEntry.key);
    }

    /* access modifiers changed from: private */
    public void handleSuppressedSummaryAlerted(NotificationEntry notificationEntry, AlertingNotificationManager alertingNotificationManager) {
        NotificationEntry next;
        StatusBarNotification statusBarNotification = notificationEntry.notification;
        GroupAlertEntry groupAlertEntry = this.mGroupAlertEntries.get(this.mGroupManager.getGroupKey(statusBarNotification));
        if (this.mGroupManager.isSummaryOfSuppressedGroup(notificationEntry.notification) && alertingNotificationManager.isAlerting(statusBarNotification.getKey()) && groupAlertEntry != null && !pendingInflationsWillAddChildren(groupAlertEntry.mGroup) && (next = this.mGroupManager.getLogicalChildren(notificationEntry.notification).iterator().next()) != null && !next.getRow().keepInParent() && !next.isRowRemoved() && !next.isRowDismissed()) {
            if (!alertingNotificationManager.isAlerting(next.key) && onlySummaryAlerts(notificationEntry)) {
                groupAlertEntry.mLastAlertTransferTime = SystemClock.elapsedRealtime();
            }
            transferAlertState(notificationEntry, next, alertingNotificationManager);
        }
    }

    private void transferAlertState(NotificationEntry notificationEntry, NotificationEntry notificationEntry2, AlertingNotificationManager alertingNotificationManager) {
        alertingNotificationManager.removeNotification(notificationEntry.key, true);
        alertNotificationWhenPossible(notificationEntry2, alertingNotificationManager);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0018, code lost:
        r1 = r11.mGroupManager.getLogicalChildren(r0.notification);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void checkShouldTransferBack(com.android.systemui.statusbar.phone.NotificationGroupAlertTransferHelper.GroupAlertEntry r12) {
        /*
            r11 = this;
            long r0 = android.os.SystemClock.elapsedRealtime()
            long r2 = r12.mLastAlertTransferTime
            long r0 = r0 - r2
            r2 = 300(0x12c, double:1.48E-321)
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 >= 0) goto L_0x008e
            com.android.systemui.statusbar.phone.NotificationGroupManager$NotificationGroup r0 = r12.mGroup
            com.android.systemui.statusbar.notification.collection.NotificationEntry r0 = r0.summary
            boolean r1 = r11.onlySummaryAlerts(r0)
            if (r1 != 0) goto L_0x0018
            return
        L_0x0018:
            com.android.systemui.statusbar.phone.NotificationGroupManager r1 = r11.mGroupManager
            android.service.notification.StatusBarNotification r2 = r0.notification
            java.util.ArrayList r1 = r1.getLogicalChildren(r2)
            int r2 = r1.size()
            com.android.systemui.statusbar.phone.NotificationGroupManager$NotificationGroup r3 = r12.mGroup
            int r3 = r11.getPendingChildrenNotAlerting(r3)
            int r2 = r2 + r3
            r4 = 1
            if (r2 > r4) goto L_0x002f
            return
        L_0x002f:
            r5 = 0
            r6 = r5
            r7 = r6
        L_0x0032:
            int r8 = r1.size()
            if (r6 >= r8) goto L_0x0070
            java.lang.Object r8 = r1.get(r6)
            com.android.systemui.statusbar.notification.collection.NotificationEntry r8 = (com.android.systemui.statusbar.notification.collection.NotificationEntry) r8
            boolean r9 = r11.onlySummaryAlerts(r8)
            if (r9 == 0) goto L_0x0056
            com.android.systemui.statusbar.policy.HeadsUpManager r9 = r11.mHeadsUpManager
            java.lang.String r10 = r8.key
            boolean r9 = r9.isAlerting(r10)
            if (r9 == 0) goto L_0x0056
            com.android.systemui.statusbar.policy.HeadsUpManager r7 = r11.mHeadsUpManager
            java.lang.String r9 = r8.key
            r7.removeNotification(r9, r4)
            r7 = r4
        L_0x0056:
            android.util.ArrayMap<java.lang.String, com.android.systemui.statusbar.phone.NotificationGroupAlertTransferHelper$PendingAlertInfo> r9 = r11.mPendingAlerts
            java.lang.String r10 = r8.key
            boolean r9 = r9.containsKey(r10)
            if (r9 == 0) goto L_0x006d
            android.util.ArrayMap<java.lang.String, com.android.systemui.statusbar.phone.NotificationGroupAlertTransferHelper$PendingAlertInfo> r7 = r11.mPendingAlerts
            java.lang.String r8 = r8.key
            java.lang.Object r7 = r7.get(r8)
            com.android.systemui.statusbar.phone.NotificationGroupAlertTransferHelper$PendingAlertInfo r7 = (com.android.systemui.statusbar.phone.NotificationGroupAlertTransferHelper.PendingAlertInfo) r7
            r7.mAbortOnInflation = r4
            r7 = r4
        L_0x006d:
            int r6 = r6 + 1
            goto L_0x0032
        L_0x0070:
            if (r7 == 0) goto L_0x008e
            com.android.systemui.statusbar.policy.HeadsUpManager r1 = r11.mHeadsUpManager
            java.lang.String r6 = r0.key
            boolean r1 = r1.isAlerting(r6)
            if (r1 != 0) goto L_0x008e
            int r2 = r2 - r3
            if (r2 <= r4) goto L_0x0080
            r5 = r4
        L_0x0080:
            if (r5 == 0) goto L_0x0088
            com.android.systemui.statusbar.policy.HeadsUpManager r1 = r11.mHeadsUpManager
            r11.alertNotificationWhenPossible(r0, r1)
            goto L_0x008a
        L_0x0088:
            r12.mAlertSummaryOnNextAddition = r4
        L_0x008a:
            r0 = 0
            r12.mLastAlertTransferTime = r0
        L_0x008e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.phone.NotificationGroupAlertTransferHelper.checkShouldTransferBack(com.android.systemui.statusbar.phone.NotificationGroupAlertTransferHelper$GroupAlertEntry):void");
    }

    /* access modifiers changed from: private */
    public void alertNotificationWhenPossible(NotificationEntry notificationEntry, AlertingNotificationManager alertingNotificationManager) {
        int contentFlag = alertingNotificationManager.getContentFlag();
        if (!notificationEntry.getRow().isInflationFlagSet(contentFlag)) {
            this.mPendingAlerts.put(notificationEntry.key, new PendingAlertInfo(notificationEntry));
            notificationEntry.getRow().updateInflationFlag(contentFlag, true);
            notificationEntry.getRow().inflateViews();
        } else if (alertingNotificationManager.isAlerting(notificationEntry.key)) {
            alertingNotificationManager.updateNotification(notificationEntry.key, true);
        } else {
            alertingNotificationManager.showNotification(notificationEntry);
        }
    }

    private boolean onlySummaryAlerts(NotificationEntry notificationEntry) {
        return notificationEntry.notification.getNotification().getGroupAlertBehavior() == 1;
    }

    private class PendingAlertInfo {
        boolean mAbortOnInflation;
        final NotificationEntry mEntry;
        final StatusBarNotification mOriginalNotification;

        PendingAlertInfo(NotificationEntry notificationEntry) {
            this.mOriginalNotification = notificationEntry.notification;
            this.mEntry = notificationEntry;
        }

        /* access modifiers changed from: private */
        public boolean isStillValid() {
            if (!this.mAbortOnInflation && this.mEntry.notification.getGroupKey() == this.mOriginalNotification.getGroupKey() && this.mEntry.notification.getNotification().isGroupSummary() == this.mOriginalNotification.getNotification().isGroupSummary()) {
                return true;
            }
            return false;
        }
    }

    private static class GroupAlertEntry {
        boolean mAlertSummaryOnNextAddition;
        final NotificationGroupManager.NotificationGroup mGroup;
        long mLastAlertTransferTime;

        GroupAlertEntry(NotificationGroupManager.NotificationGroup notificationGroup) {
            this.mGroup = notificationGroup;
        }
    }
}
