package com.android.systemui.statusbar.policy;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.provider.Settings;
import android.util.ArrayMap;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.C1778R$integer;
import com.android.systemui.C1784R$string;
import com.android.systemui.Dependency;
import com.android.systemui.statusbar.AlertingNotificationManager;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;

public abstract class HeadsUpManager extends AlertingNotificationManager {
    /* access modifiers changed from: private */
    public final AccessibilityManagerWrapper mAccessibilityMgr;
    protected final Context mContext;
    protected boolean mHasPinnedNotification;
    protected final HashSet<OnHeadsUpChangedListener> mListeners = new HashSet<>();
    protected int mSnoozeLengthMs;
    private final ArrayMap<String, Long> mSnoozedPackages;
    protected int mTouchAcceptanceDelay;
    protected int mUser;

    public int getContentFlag() {
        return 4;
    }

    public boolean isEntryAutoHeadsUpped(String str) {
        return false;
    }

    public boolean isTrackingHeadsUp() {
        return false;
    }

    public void onDensityOrFontScaleChanged() {
    }

    public HeadsUpManager(Context context) {
        this.mContext = context;
        this.mAccessibilityMgr = (AccessibilityManagerWrapper) Dependency.get(AccessibilityManagerWrapper.class);
        Resources resources = context.getResources();
        this.mMinimumDisplayTime = resources.getInteger(C1778R$integer.heads_up_notification_minimum_time);
        this.mAutoDismissNotificationDecay = Settings.System.getIntForUser(context.getContentResolver(), "heads_up_timeout", resources.getInteger(C1778R$integer.heads_up_notification_decay), -2);
        this.mTouchAcceptanceDelay = resources.getInteger(C1778R$integer.touch_acceptance_delay);
        this.mSnoozedPackages = new ArrayMap<>();
        this.mSnoozeLengthMs = Settings.System.getIntForUser(context.getContentResolver(), "heads_up_notification_snooze", resources.getInteger(C1778R$integer.heads_up_default_snooze_length_ms), -2);
    }

    public void addListener(OnHeadsUpChangedListener onHeadsUpChangedListener) {
        this.mListeners.add(onHeadsUpChangedListener);
    }

    public void removeListener(OnHeadsUpChangedListener onHeadsUpChangedListener) {
        this.mListeners.remove(onHeadsUpChangedListener);
    }

    public void updateNotification(String str, boolean z) {
        super.updateNotification(str, z);
        HeadsUpEntry headsUpEntry = getHeadsUpEntry(str);
        if (z && headsUpEntry != null) {
            setEntryPinned(headsUpEntry, shouldHeadsUpBecomePinned(headsUpEntry.mEntry));
        }
    }

    /* access modifiers changed from: protected */
    public boolean shouldHeadsUpBecomePinned(NotificationEntry notificationEntry) {
        return hasFullScreenIntent(notificationEntry);
    }

    /* access modifiers changed from: protected */
    public boolean hasFullScreenIntent(NotificationEntry notificationEntry) {
        return notificationEntry.notification.getNotification().fullScreenIntent != null;
    }

    /* access modifiers changed from: protected */
    public void setEntryPinned(HeadsUpEntry headsUpEntry, boolean z) {
        if (Log.isLoggable("HeadsUpManager", 2)) {
            Log.v("HeadsUpManager", "setEntryPinned: " + z);
        }
        NotificationEntry notificationEntry = headsUpEntry.mEntry;
        if (notificationEntry.isRowPinned() != z) {
            notificationEntry.setRowPinned(z);
            updatePinnedMode();
            Iterator<OnHeadsUpChangedListener> it = this.mListeners.iterator();
            while (it.hasNext()) {
                OnHeadsUpChangedListener next = it.next();
                if (z) {
                    next.onHeadsUpPinned(notificationEntry);
                } else {
                    next.onHeadsUpUnPinned(notificationEntry);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onAlertEntryAdded(AlertingNotificationManager.AlertEntry alertEntry) {
        NotificationEntry notificationEntry = alertEntry.mEntry;
        notificationEntry.setHeadsUp(true);
        setEntryPinned((HeadsUpEntry) alertEntry, shouldHeadsUpBecomePinned(notificationEntry));
        Iterator<OnHeadsUpChangedListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onHeadsUpStateChanged(notificationEntry, true);
        }
    }

    /* access modifiers changed from: protected */
    public void onAlertEntryRemoved(AlertingNotificationManager.AlertEntry alertEntry) {
        NotificationEntry notificationEntry = alertEntry.mEntry;
        notificationEntry.setHeadsUp(false);
        setEntryPinned((HeadsUpEntry) alertEntry, false);
        Iterator<OnHeadsUpChangedListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onHeadsUpStateChanged(notificationEntry, false);
        }
        notificationEntry.freeContentViewWhenSafe(4);
    }

    /* access modifiers changed from: protected */
    public void updatePinnedMode() {
        boolean hasPinnedNotificationInternal = hasPinnedNotificationInternal();
        if (hasPinnedNotificationInternal != this.mHasPinnedNotification) {
            if (Log.isLoggable("HeadsUpManager", 2)) {
                Log.v("HeadsUpManager", "Pinned mode changed: " + this.mHasPinnedNotification + " -> " + hasPinnedNotificationInternal);
            }
            this.mHasPinnedNotification = hasPinnedNotificationInternal;
            if (this.mHasPinnedNotification) {
                MetricsLogger.count(this.mContext, "note_peek", 1);
            }
            Iterator<OnHeadsUpChangedListener> it = this.mListeners.iterator();
            while (it.hasNext()) {
                it.next().onHeadsUpPinnedModeChanged(hasPinnedNotificationInternal);
            }
        }
    }

    public boolean isSnoozed(String str) {
        String snoozeKey = snoozeKey(str, this.mUser);
        Long l = this.mSnoozedPackages.get(snoozeKey);
        if (l == null) {
            return false;
        }
        if (l.longValue() <= this.mClock.currentTimeMillis()) {
            this.mSnoozedPackages.remove(str);
            return false;
        } else if (!Log.isLoggable("HeadsUpManager", 2)) {
            return true;
        } else {
            Log.v("HeadsUpManager", snoozeKey + " snoozed");
            return true;
        }
    }

    public void snooze() {
        for (String headsUpEntry : this.mAlertEntries.keySet()) {
            String packageName = getHeadsUpEntry(headsUpEntry).mEntry.notification.getPackageName();
            this.mSnoozedPackages.put(snoozeKey(packageName, this.mUser), Long.valueOf(this.mClock.currentTimeMillis() + ((long) this.mSnoozeLengthMs)));
            if (this.mSnoozeLengthMs != 0) {
                try {
                    packageName = (String) this.mContext.getPackageManager().getApplicationLabel(this.mContext.getPackageManager().getApplicationInfo(packageName, 128));
                } catch (PackageManager.NameNotFoundException unused) {
                }
                int i = this.mSnoozeLengthMs;
                if (i == 60000) {
                    Context context = this.mContext;
                    Toast makeText = Toast.makeText(context, context.getString(C1784R$string.heads_up_snooze_message_one_minute, new Object[]{packageName}), 1);
                    TextView textView = (TextView) makeText.getView().findViewById(16908299);
                    if (textView != null) {
                        textView.setGravity(17);
                    }
                    makeText.show();
                } else {
                    Context context2 = this.mContext;
                    Toast makeText2 = Toast.makeText(context2, context2.getString(C1784R$string.heads_up_snooze_message, new Object[]{packageName, Integer.valueOf((i / 60) / 1000)}), 1);
                    TextView textView2 = (TextView) makeText2.getView().findViewById(16908299);
                    if (textView2 != null) {
                        textView2.setGravity(17);
                    }
                    makeText2.show();
                }
            }
        }
    }

    private static String snoozeKey(String str, int i) {
        return i + "," + str;
    }

    /* access modifiers changed from: protected */
    public HeadsUpEntry getHeadsUpEntry(String str) {
        return (HeadsUpEntry) this.mAlertEntries.get(str);
    }

    public NotificationEntry getTopEntry() {
        HeadsUpEntry topHeadsUpEntry = getTopHeadsUpEntry();
        if (topHeadsUpEntry != null) {
            return topHeadsUpEntry.mEntry;
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public HeadsUpEntry getTopHeadsUpEntry() {
        HeadsUpEntry headsUpEntry = null;
        if (this.mAlertEntries.isEmpty()) {
            return null;
        }
        for (AlertingNotificationManager.AlertEntry next : this.mAlertEntries.values()) {
            if (headsUpEntry == null || next.compareTo((AlertingNotificationManager.AlertEntry) headsUpEntry) < 0) {
                headsUpEntry = (HeadsUpEntry) next;
            }
        }
        return headsUpEntry;
    }

    public void setUser(int i) {
        this.mUser = i;
    }

    /* access modifiers changed from: protected */
    public void dumpInternal(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print("  mTouchAcceptanceDelay=");
        printWriter.println(this.mTouchAcceptanceDelay);
        printWriter.print("  mSnoozeLengthMs=");
        printWriter.println(this.mSnoozeLengthMs);
        printWriter.print("  now=");
        printWriter.println(this.mClock.currentTimeMillis());
        printWriter.print("  mUser=");
        printWriter.println(this.mUser);
        for (AlertingNotificationManager.AlertEntry alertEntry : this.mAlertEntries.values()) {
            printWriter.print("  HeadsUpEntry=");
            printWriter.println(alertEntry.mEntry);
        }
        int size = this.mSnoozedPackages.size();
        printWriter.println("  snoozed packages: " + size);
        for (int i = 0; i < size; i++) {
            printWriter.print("    ");
            printWriter.print(this.mSnoozedPackages.valueAt(i));
            printWriter.print(", ");
            printWriter.println(this.mSnoozedPackages.keyAt(i));
        }
    }

    public boolean hasPinnedHeadsUp() {
        return this.mHasPinnedNotification;
    }

    private boolean hasPinnedNotificationInternal() {
        for (String headsUpEntry : this.mAlertEntries.keySet()) {
            if (getHeadsUpEntry(headsUpEntry).mEntry.isRowPinned()) {
                return true;
            }
        }
        return false;
    }

    public void unpinAll(boolean z) {
        NotificationEntry notificationEntry;
        for (String headsUpEntry : this.mAlertEntries.keySet()) {
            HeadsUpEntry headsUpEntry2 = getHeadsUpEntry(headsUpEntry);
            setEntryPinned(headsUpEntry2, false);
            headsUpEntry2.updateEntry(false);
            if (z && (notificationEntry = headsUpEntry2.mEntry) != null && notificationEntry.mustStayOnScreen()) {
                headsUpEntry2.mEntry.setHeadsUpIsVisible();
            }
        }
    }

    public int compare(NotificationEntry notificationEntry, NotificationEntry notificationEntry2) {
        HeadsUpEntry headsUpEntry = getHeadsUpEntry(notificationEntry.key);
        HeadsUpEntry headsUpEntry2 = getHeadsUpEntry(notificationEntry2.key);
        if (headsUpEntry == null || headsUpEntry2 == null) {
            return headsUpEntry == null ? 1 : -1;
        }
        return headsUpEntry.compareTo((AlertingNotificationManager.AlertEntry) headsUpEntry2);
    }

    public void setExpanded(NotificationEntry notificationEntry, boolean z) {
        HeadsUpEntry headsUpEntry = getHeadsUpEntry(notificationEntry.key);
        if (headsUpEntry != null && notificationEntry.isRowPinned()) {
            headsUpEntry.setExpanded(z);
        }
    }

    /* access modifiers changed from: protected */
    public HeadsUpEntry createAlertEntry() {
        return new HeadsUpEntry();
    }

    protected class HeadsUpEntry extends AlertingNotificationManager.AlertEntry {
        protected boolean expanded;
        public boolean remoteInputActive;

        protected HeadsUpEntry() {
            super();
        }

        public void updateEntry(boolean z) {
            HeadsUpManager headsUpManager = HeadsUpManager.this;
            headsUpManager.mSnoozeLengthMs = Settings.System.getIntForUser(headsUpManager.mContext.getContentResolver(), "heads_up_notification_snooze", HeadsUpManager.this.mContext.getResources().getInteger(C1778R$integer.heads_up_default_snooze_length_ms), -2);
            HeadsUpManager headsUpManager2 = HeadsUpManager.this;
            int unused = headsUpManager2.mAutoDismissNotificationDecay = Settings.System.getIntForUser(headsUpManager2.mContext.getContentResolver(), "heads_up_timeout", HeadsUpManager.this.mContext.getResources().getInteger(C1778R$integer.heads_up_notification_decay), -2);
            super.updateEntry(z);
        }

        /* access modifiers changed from: protected */
        public boolean isSticky() {
            return (this.mEntry.isRowPinned() && this.expanded) || this.remoteInputActive || HeadsUpManager.this.hasFullScreenIntent(this.mEntry);
        }

        public int compareTo(AlertingNotificationManager.AlertEntry alertEntry) {
            HeadsUpEntry headsUpEntry = (HeadsUpEntry) alertEntry;
            boolean isRowPinned = this.mEntry.isRowPinned();
            boolean isRowPinned2 = headsUpEntry.mEntry.isRowPinned();
            if (isRowPinned && !isRowPinned2) {
                return -1;
            }
            if (!isRowPinned && isRowPinned2) {
                return 1;
            }
            boolean hasFullScreenIntent = HeadsUpManager.this.hasFullScreenIntent(this.mEntry);
            boolean hasFullScreenIntent2 = HeadsUpManager.this.hasFullScreenIntent(headsUpEntry.mEntry);
            if (hasFullScreenIntent && !hasFullScreenIntent2) {
                return -1;
            }
            if (!hasFullScreenIntent && hasFullScreenIntent2) {
                return 1;
            }
            if (this.remoteInputActive && !headsUpEntry.remoteInputActive) {
                return -1;
            }
            if (this.remoteInputActive || !headsUpEntry.remoteInputActive) {
                return super.compareTo((AlertingNotificationManager.AlertEntry) headsUpEntry);
            }
            return 1;
        }

        public void setExpanded(boolean z) {
            this.expanded = z;
        }

        public void reset() {
            super.reset();
            this.expanded = false;
            this.remoteInputActive = false;
        }

        /* access modifiers changed from: protected */
        public long calculatePostTime() {
            return super.calculatePostTime() + ((long) HeadsUpManager.this.mTouchAcceptanceDelay);
        }

        /* access modifiers changed from: protected */
        public long calculateFinishTime() {
            return this.mPostTime + ((long) getRecommendedHeadsUpTimeoutMs(HeadsUpManager.this.mAutoDismissNotificationDecay));
        }

        /* access modifiers changed from: protected */
        public int getRecommendedHeadsUpTimeoutMs(int i) {
            return HeadsUpManager.this.mAccessibilityMgr.getRecommendedTimeoutMillis(i, 7);
        }
    }
}
