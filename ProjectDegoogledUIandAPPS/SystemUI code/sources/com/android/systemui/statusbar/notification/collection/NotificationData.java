package com.android.systemui.statusbar.notification.collection;

import android.app.Notification;
import android.app.NotificationChannel;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.ArrayMap;
import com.android.internal.annotations.VisibleForTesting;
import com.android.systemui.Dependency;
import com.android.systemui.statusbar.NotificationMediaManager;
import com.android.systemui.statusbar.notification.NotificationFilter;
import com.android.systemui.statusbar.phone.NotificationGroupManager;
import com.android.systemui.statusbar.policy.HeadsUpManager;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;

public class NotificationData {
    private final ArrayMap<String, NotificationEntry> mEntries = new ArrayMap<>();
    private KeyguardEnvironment mEnvironment;
    private final NotificationGroupManager mGroupManager = ((NotificationGroupManager) Dependency.get(NotificationGroupManager.class));
    /* access modifiers changed from: private */
    public HeadsUpManager mHeadsUpManager;
    private NotificationMediaManager mMediaManager;
    private final NotificationFilter mNotificationFilter = ((NotificationFilter) Dependency.get(NotificationFilter.class));
    @VisibleForTesting
    protected final Comparator<NotificationEntry> mRankingComparator = new Comparator<NotificationEntry>() {
        private final NotificationListenerService.Ranking mRankingA = new NotificationListenerService.Ranking();
        private final NotificationListenerService.Ranking mRankingB = new NotificationListenerService.Ranking();

        public int compare(NotificationEntry notificationEntry, NotificationEntry notificationEntry2) {
            int i;
            int i2;
            int i3;
            StatusBarNotification statusBarNotification = notificationEntry.notification;
            StatusBarNotification statusBarNotification2 = notificationEntry2.notification;
            int i4 = 3;
            boolean z = false;
            if (NotificationData.this.mRankingMap != null) {
                NotificationData.this.getRanking(notificationEntry.key, this.mRankingA);
                NotificationData.this.getRanking(notificationEntry2.key, this.mRankingB);
                i4 = this.mRankingA.getImportance();
                i3 = this.mRankingB.getImportance();
                i2 = this.mRankingA.getRank();
                i = this.mRankingB.getRank();
            } else {
                i3 = 3;
                i2 = 0;
                i = 0;
            }
            String mediaNotificationKey = NotificationData.this.getMediaManager().getMediaNotificationKey();
            boolean z2 = notificationEntry.key.equals(mediaNotificationKey) && i4 > 1;
            boolean z3 = notificationEntry2.key.equals(mediaNotificationKey) && i3 > 1;
            boolean z4 = i4 >= 4 && NotificationData.isSystemNotification(statusBarNotification);
            boolean z5 = i3 >= 4 && NotificationData.isSystemNotification(statusBarNotification2);
            boolean isHeadsUp = notificationEntry.getRow().isHeadsUp();
            boolean isHeadsUp2 = notificationEntry2.getRow().isHeadsUp();
            notificationEntry.setIsTopBucket(isHeadsUp || z2 || z4 || notificationEntry.isHighPriority());
            if (isHeadsUp2 || z3 || z5 || notificationEntry2.isHighPriority()) {
                z = true;
            }
            notificationEntry2.setIsTopBucket(z);
            if (isHeadsUp != isHeadsUp2) {
                return isHeadsUp ? -1 : 1;
            }
            if (isHeadsUp) {
                return NotificationData.this.mHeadsUpManager.compare(notificationEntry, notificationEntry2);
            }
            if (z2 != z3) {
                if (z2) {
                    return -1;
                }
                return 1;
            } else if (z4 != z5) {
                if (z4) {
                    return -1;
                }
                return 1;
            } else if (notificationEntry.isHighPriority() != notificationEntry2.isHighPriority()) {
                return Boolean.compare(notificationEntry.isHighPriority(), notificationEntry2.isHighPriority()) * -1;
            } else {
                if (i2 != i) {
                    return i2 - i;
                }
                return Long.compare(statusBarNotification2.getNotification().when, statusBarNotification.getNotification().when);
            }
        }
    };
    /* access modifiers changed from: private */
    public NotificationListenerService.RankingMap mRankingMap;
    private final ArrayList<NotificationEntry> mSortedAndFiltered = new ArrayList<>();
    private final NotificationListenerService.Ranking mTmpRanking = new NotificationListenerService.Ranking();

    public interface KeyguardEnvironment {
        boolean isDeviceProvisioned();

        boolean isNotificationForCurrentProfiles(StatusBarNotification statusBarNotification);
    }

    public void setHeadsUpManager(HeadsUpManager headsUpManager) {
        this.mHeadsUpManager = headsUpManager;
    }

    private KeyguardEnvironment getEnvironment() {
        if (this.mEnvironment == null) {
            this.mEnvironment = (KeyguardEnvironment) Dependency.get(KeyguardEnvironment.class);
        }
        return this.mEnvironment;
    }

    /* access modifiers changed from: private */
    public NotificationMediaManager getMediaManager() {
        if (this.mMediaManager == null) {
            this.mMediaManager = (NotificationMediaManager) Dependency.get(NotificationMediaManager.class);
        }
        return this.mMediaManager;
    }

    public ArrayList<NotificationEntry> getActiveNotifications() {
        return this.mSortedAndFiltered;
    }

    public ArrayList<NotificationEntry> getNotificationsForCurrentUser() {
        ArrayList<NotificationEntry> arrayList;
        synchronized (this.mEntries) {
            int size = this.mEntries.size();
            arrayList = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                NotificationEntry valueAt = this.mEntries.valueAt(i);
                if (getEnvironment().isNotificationForCurrentProfiles(valueAt.notification)) {
                    arrayList.add(valueAt);
                }
            }
        }
        return arrayList;
    }

    public boolean hasActiveVisibleNotifications() {
        Iterator<NotificationEntry> it = this.mSortedAndFiltered.iterator();
        while (it.hasNext()) {
            if (it.next().getContentView() != null) {
                return true;
            }
        }
        return false;
    }

    public boolean hasActiveOngoingNotifications() {
        Iterator<NotificationEntry> it = this.mSortedAndFiltered.iterator();
        while (it.hasNext()) {
            NotificationEntry next = it.next();
            if (next.getContentView() != null && next.notification.isOngoing()) {
                return true;
            }
        }
        return false;
    }

    public NotificationEntry get(String str) {
        return this.mEntries.get(str);
    }

    public ArrayList<NotificationEntry> getAllNotificationsForPackage(String str) {
        ArrayList<NotificationEntry> arrayList;
        synchronized (this.mEntries) {
            int size = this.mEntries.size();
            arrayList = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                NotificationEntry valueAt = this.mEntries.valueAt(i);
                if (str.equals(valueAt.notification.getPackageName())) {
                    arrayList.add(valueAt);
                }
            }
        }
        return arrayList;
    }

    public void add(NotificationEntry notificationEntry) {
        synchronized (this.mEntries) {
            this.mEntries.put(notificationEntry.notification.getKey(), notificationEntry);
        }
        this.mGroupManager.onEntryAdded(notificationEntry);
        updateRankingAndSort(this.mRankingMap);
    }

    public NotificationEntry remove(String str, NotificationListenerService.RankingMap rankingMap) {
        NotificationEntry remove;
        synchronized (this.mEntries) {
            remove = this.mEntries.remove(str);
        }
        if (remove == null) {
            return null;
        }
        if (rankingMap == null) {
            rankingMap = this.mRankingMap;
        }
        this.mGroupManager.onEntryRemoved(remove);
        updateRankingAndSort(rankingMap);
        return remove;
    }

    public void update(NotificationEntry notificationEntry, NotificationListenerService.RankingMap rankingMap, StatusBarNotification statusBarNotification) {
        updateRanking(rankingMap);
        StatusBarNotification statusBarNotification2 = notificationEntry.notification;
        notificationEntry.notification = statusBarNotification;
        this.mGroupManager.onEntryUpdated(notificationEntry, statusBarNotification2);
    }

    public void updateRanking(NotificationListenerService.RankingMap rankingMap) {
        updateRankingAndSort(rankingMap);
    }

    public void updateAppOp(int i, int i2, String str, String str2, boolean z) {
        synchronized (this.mEntries) {
            int size = this.mEntries.size();
            for (int i3 = 0; i3 < size; i3++) {
                NotificationEntry valueAt = this.mEntries.valueAt(i3);
                if (i2 == valueAt.notification.getUid() && str.equals(valueAt.notification.getPackageName()) && str2.equals(valueAt.key)) {
                    if (z) {
                        valueAt.mActiveAppOps.add(Integer.valueOf(i));
                    } else {
                        valueAt.mActiveAppOps.remove(Integer.valueOf(i));
                    }
                }
            }
        }
    }

    public boolean isHighPriority(StatusBarNotification statusBarNotification) {
        if (this.mRankingMap == null) {
            return false;
        }
        getRanking(statusBarNotification.getKey(), this.mTmpRanking);
        if (this.mTmpRanking.getImportance() >= 3 || hasHighPriorityCharacteristics(this.mTmpRanking.getChannel(), statusBarNotification)) {
            return true;
        }
        if (!this.mGroupManager.isSummaryOfGroup(statusBarNotification)) {
            return false;
        }
        Iterator<NotificationEntry> it = this.mGroupManager.getLogicalChildren(statusBarNotification).iterator();
        while (it.hasNext()) {
            if (isHighPriority(it.next().notification)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasHighPriorityCharacteristics(NotificationChannel notificationChannel, StatusBarNotification statusBarNotification) {
        if (!isImportantOngoing(statusBarNotification.getNotification()) && !statusBarNotification.getNotification().hasMediaSession() && !hasPerson(statusBarNotification.getNotification()) && !hasStyle(statusBarNotification.getNotification(), Notification.MessagingStyle.class)) {
            return false;
        }
        if (notificationChannel == null || !notificationChannel.hasUserSetImportance()) {
            return true;
        }
        return false;
    }

    private boolean isImportantOngoing(Notification notification) {
        return notification.isForegroundService() && this.mTmpRanking.getImportance() >= 2;
    }

    private boolean hasStyle(Notification notification, Class cls) {
        return cls.equals(notification.getNotificationStyle());
    }

    private boolean hasPerson(Notification notification) {
        ArrayList arrayList;
        Bundle bundle = notification.extras;
        if (bundle != null) {
            arrayList = bundle.getParcelableArrayList("android.people.list");
        } else {
            arrayList = new ArrayList();
        }
        return arrayList != null && !arrayList.isEmpty();
    }

    public boolean isAmbient(String str) {
        if (this.mRankingMap == null) {
            return false;
        }
        getRanking(str, this.mTmpRanking);
        return this.mTmpRanking.isAmbient();
    }

    public int getVisibilityOverride(String str) {
        if (this.mRankingMap == null) {
            return -1000;
        }
        getRanking(str, this.mTmpRanking);
        return this.mTmpRanking.getVisibilityOverride();
    }

    public String getOverrideGroupKey(String str) {
        if (this.mRankingMap == null) {
            return null;
        }
        getRanking(str, this.mTmpRanking);
        return this.mTmpRanking.getOverrideGroupKey();
    }

    public int getRank(String str) {
        if (this.mRankingMap == null) {
            return 0;
        }
        getRanking(str, this.mTmpRanking);
        return this.mTmpRanking.getRank();
    }

    private void updateRankingAndSort(NotificationListenerService.RankingMap rankingMap) {
        if (rankingMap != null) {
            this.mRankingMap = rankingMap;
            synchronized (this.mEntries) {
                int size = this.mEntries.size();
                for (int i = 0; i < size; i++) {
                    NotificationEntry valueAt = this.mEntries.valueAt(i);
                    if (getRanking(valueAt.key, this.mTmpRanking)) {
                        StatusBarNotification cloneLight = valueAt.notification.cloneLight();
                        String overrideGroupKey = getOverrideGroupKey(valueAt.key);
                        if (!Objects.equals(cloneLight.getOverrideGroupKey(), overrideGroupKey)) {
                            valueAt.notification.setOverrideGroupKey(overrideGroupKey);
                            this.mGroupManager.onEntryUpdated(valueAt, cloneLight);
                        }
                        valueAt.populateFromRanking(this.mTmpRanking);
                        valueAt.setIsHighPriority(isHighPriority(valueAt.notification));
                    }
                }
            }
        }
        filterAndSort();
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting
    public boolean getRanking(String str, NotificationListenerService.Ranking ranking) {
        return this.mRankingMap.getRanking(str, ranking);
    }

    public void filterAndSort() {
        this.mSortedAndFiltered.clear();
        synchronized (this.mEntries) {
            int size = this.mEntries.size();
            for (int i = 0; i < size; i++) {
                NotificationEntry valueAt = this.mEntries.valueAt(i);
                if (!this.mNotificationFilter.shouldFilterOut(valueAt)) {
                    this.mSortedAndFiltered.add(valueAt);
                }
            }
        }
        if (this.mSortedAndFiltered.size() == 1) {
            this.mRankingComparator.compare(this.mSortedAndFiltered.get(0), this.mSortedAndFiltered.get(0));
        } else {
            Collections.sort(this.mSortedAndFiltered, this.mRankingComparator);
        }
    }

    public void dump(PrintWriter printWriter, String str) {
        int size = this.mSortedAndFiltered.size();
        printWriter.print(str);
        printWriter.println("active notifications: " + size);
        int i = 0;
        while (i < size) {
            dumpEntry(printWriter, str, i, this.mSortedAndFiltered.get(i));
            i++;
        }
        synchronized (this.mEntries) {
            int size2 = this.mEntries.size();
            printWriter.print(str);
            printWriter.println("inactive notifications: " + (size2 - i));
            int i2 = 0;
            for (int i3 = 0; i3 < size2; i3++) {
                NotificationEntry valueAt = this.mEntries.valueAt(i3);
                if (!this.mSortedAndFiltered.contains(valueAt)) {
                    dumpEntry(printWriter, str, i2, valueAt);
                    i2++;
                }
            }
        }
    }

    private void dumpEntry(PrintWriter printWriter, String str, int i, NotificationEntry notificationEntry) {
        getRanking(notificationEntry.key, this.mTmpRanking);
        printWriter.print(str);
        printWriter.println("  [" + i + "] key=" + notificationEntry.key + " icon=" + notificationEntry.icon);
        StatusBarNotification statusBarNotification = notificationEntry.notification;
        printWriter.print(str);
        printWriter.println("      pkg=" + statusBarNotification.getPackageName() + " id=" + statusBarNotification.getId() + " importance=" + this.mTmpRanking.getImportance());
        printWriter.print(str);
        StringBuilder sb = new StringBuilder();
        sb.append("      notification=");
        sb.append(statusBarNotification.getNotification());
        printWriter.println(sb.toString());
    }

    /* access modifiers changed from: private */
    public static boolean isSystemNotification(StatusBarNotification statusBarNotification) {
        String packageName = statusBarNotification.getPackageName();
        return "android".equals(packageName) || "com.android.systemui".equals(packageName);
    }
}
