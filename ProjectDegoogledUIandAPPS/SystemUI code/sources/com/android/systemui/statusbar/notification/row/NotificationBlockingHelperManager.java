package com.android.systemui.statusbar.notification.row;

import android.content.Context;
import android.metrics.LogMaker;
import android.util.Log;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.Dependency;
import com.android.systemui.plugins.statusbar.NotificationMenuRowPlugin;
import com.android.systemui.statusbar.notification.NotificationEntryManager;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class NotificationBlockingHelperManager {
    private ExpandableNotificationRow mBlockingHelperRow;
    private final Context mContext;
    private boolean mIsShadeExpanded;
    private MetricsLogger mMetricsLogger = new MetricsLogger();
    private Set<String> mNonBlockablePkgs;

    public NotificationBlockingHelperManager(Context context) {
        this.mContext = context;
        this.mNonBlockablePkgs = new HashSet();
        Collections.addAll(this.mNonBlockablePkgs, this.mContext.getResources().getStringArray(17236063));
    }

    /* access modifiers changed from: package-private */
    public boolean perhapsShowBlockingHelper(ExpandableNotificationRow expandableNotificationRow, NotificationMenuRowPlugin notificationMenuRowPlugin) {
        if (expandableNotificationRow.getEntry().userSentiment != -1 || !this.mIsShadeExpanded || expandableNotificationRow.getIsNonblockable() || ((expandableNotificationRow.isChildInGroup() && !expandableNotificationRow.isOnlyChildInGroup()) || expandableNotificationRow.getNumUniqueChannels() > 1)) {
            return false;
        }
        dismissCurrentBlockingHelper();
        this.mBlockingHelperRow = expandableNotificationRow;
        this.mBlockingHelperRow.setBlockingHelperShowing(true);
        this.mMetricsLogger.write(getLogMaker().setSubtype(3));
        ((NotificationGutsManager) Dependency.get(NotificationGutsManager.class)).openGuts(this.mBlockingHelperRow, 0, 0, notificationMenuRowPlugin.getLongpressMenuItem(this.mContext));
        ((MetricsLogger) Dependency.get(MetricsLogger.class)).count("blocking_helper_shown", 1);
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean dismissCurrentBlockingHelper() {
        if (isBlockingHelperRowNull()) {
            return false;
        }
        if (!this.mBlockingHelperRow.isBlockingHelperShowing()) {
            Log.e("BlockingHelper", "Manager.dismissCurrentBlockingHelper: Non-null row is not showing a blocking helper");
        }
        this.mBlockingHelperRow.setBlockingHelperShowing(false);
        if (this.mBlockingHelperRow.isAttachedToWindow()) {
            ((NotificationEntryManager) Dependency.get(NotificationEntryManager.class)).updateNotifications();
        }
        this.mBlockingHelperRow = null;
        return true;
    }

    public void setNotificationShadeExpanded(float f) {
        this.mIsShadeExpanded = f > 0.0f;
    }

    public boolean isNonblockable(String str, String str2) {
        return this.mNonBlockablePkgs.contains(str) || this.mNonBlockablePkgs.contains(makeChannelKey(str, str2));
    }

    private LogMaker getLogMaker() {
        return this.mBlockingHelperRow.getStatusBarNotification().getLogMaker().setCategory(1621);
    }

    private String makeChannelKey(String str, String str2) {
        return str + ":" + str2;
    }

    /* access modifiers changed from: package-private */
    public boolean isBlockingHelperRowNull() {
        return this.mBlockingHelperRow == null;
    }

    /* access modifiers changed from: package-private */
    public void setBlockingHelperRowForTest(ExpandableNotificationRow expandableNotificationRow) {
        this.mBlockingHelperRow = expandableNotificationRow;
    }

    /* access modifiers changed from: package-private */
    public void setNonBlockablePkgs(String[] strArr) {
        this.mNonBlockablePkgs = new HashSet();
        Collections.addAll(this.mNonBlockablePkgs, strArr);
    }
}
