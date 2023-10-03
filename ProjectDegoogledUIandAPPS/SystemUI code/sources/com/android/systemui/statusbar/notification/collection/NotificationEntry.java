package com.android.systemui.statusbar.notification.collection;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.Person;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.SystemClock;
import android.service.notification.NotificationListenerService;
import android.service.notification.SnoozeCriterion;
import android.service.notification.StatusBarNotification;
import android.util.ArraySet;
import android.view.View;
import android.widget.ImageView;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.statusbar.StatusBarIcon;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.ContrastColorUtil;
import com.android.systemui.statusbar.InflationTask;
import com.android.systemui.statusbar.StatusBarIconView;
import com.android.systemui.statusbar.notification.InflationException;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.NotificationGuts;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class NotificationEntry {
    public boolean ambient;
    public StatusBarIconView aodIcon;
    public boolean autoRedacted;
    public boolean canBubble;
    public StatusBarIconView centeredIcon;
    public NotificationChannel channel;
    public EditedSuggestionInfo editedSuggestionInfo;
    public StatusBarIconView expandedIcon;
    private boolean hasSentReply;
    public CharSequence headsUpStatusBarText;
    public CharSequence headsUpStatusBarTextPublic;
    public StatusBarIconView icon;
    public int importance;
    private long initializationTime = -1;
    private boolean interruption;
    public boolean isVisuallyInterruptive;
    public final String key;
    public long lastAudiblyAlertedMs;
    private long lastFullScreenIntentLaunchTime = -2000;
    public long lastRemoteInputSent = -2000;
    public ArraySet<Integer> mActiveAppOps = new ArraySet<>(3);
    private boolean mAutoHeadsUp;
    private int mCachedContrastColor = 1;
    private int mCachedContrastColorIsFor = 1;
    private Throwable mDebugThrowable;
    private boolean mHighPriority;
    public Boolean mIsSystemNotification;
    private boolean mIsTopBucket;
    private Runnable mOnSensitiveChangedListener;
    private boolean mPulseSupressed;
    private InflationTask mRunningTask = null;
    private boolean mSensitive = true;
    public StatusBarNotification notification;
    private NotificationEntry parent;
    public CharSequence remoteInputText;
    public CharSequence remoteInputTextWhenReset;
    private ExpandableNotificationRow row;
    public List<SnoozeCriterion> snoozeCriteria;
    @VisibleForTesting
    public int suppressedVisualEffects;
    public boolean suspended;
    public List<Notification.Action> systemGeneratedSmartActions = Collections.emptyList();
    public CharSequence[] systemGeneratedSmartReplies = new CharSequence[0];
    public int targetSdk;
    public int userSentiment = 0;

    public NotificationEntry(StatusBarNotification statusBarNotification, NotificationListenerService.Ranking ranking) {
        this.key = statusBarNotification.getKey();
        this.notification = statusBarNotification;
        if (ranking != null) {
            populateFromRanking(ranking);
        }
    }

    public void populateFromRanking(NotificationListenerService.Ranking ranking) {
        CharSequence[] charSequenceArr;
        this.channel = ranking.getChannel();
        this.lastAudiblyAlertedMs = ranking.getLastAudiblyAlertedMillis();
        this.importance = ranking.getImportance();
        this.ambient = ranking.isAmbient();
        this.snoozeCriteria = ranking.getSnoozeCriteria();
        this.userSentiment = ranking.getUserSentiment();
        this.systemGeneratedSmartActions = ranking.getSmartActions() == null ? Collections.emptyList() : ranking.getSmartActions();
        if (ranking.getSmartReplies() == null) {
            charSequenceArr = new CharSequence[0];
        } else {
            charSequenceArr = (CharSequence[]) ranking.getSmartReplies().toArray(new CharSequence[0]);
        }
        this.systemGeneratedSmartReplies = charSequenceArr;
        this.suppressedVisualEffects = ranking.getSuppressedVisualEffects();
        this.suspended = ranking.isSuspended();
        this.canBubble = ranking.canBubble();
        this.isVisuallyInterruptive = ranking.visuallyInterruptive();
    }

    public void setInterruption() {
        this.interruption = true;
    }

    public boolean hasInterrupted() {
        return this.interruption;
    }

    public boolean isHighPriority() {
        return this.mHighPriority;
    }

    public void setIsHighPriority(boolean z) {
        this.mHighPriority = z;
    }

    public boolean isTopBucket() {
        return this.mIsTopBucket;
    }

    public void setIsTopBucket(boolean z) {
        this.mIsTopBucket = z;
    }

    public boolean isBubble() {
        return (this.notification.getNotification().flags & 4096) != 0;
    }

    public Notification.BubbleMetadata getBubbleMetadata() {
        return this.notification.getNotification().getBubbleMetadata();
    }

    public void reset() {
        ExpandableNotificationRow expandableNotificationRow = this.row;
        if (expandableNotificationRow != null) {
            expandableNotificationRow.reset();
        }
    }

    public ExpandableNotificationRow getRow() {
        return this.row;
    }

    public void setRow(ExpandableNotificationRow expandableNotificationRow) {
        this.row = expandableNotificationRow;
    }

    public View getContentView() {
        return this.row.getPrivateLayout().getContractedChild();
    }

    public List<NotificationEntry> getChildren() {
        List<ExpandableNotificationRow> notificationChildren;
        ExpandableNotificationRow expandableNotificationRow = this.row;
        if (expandableNotificationRow == null || (notificationChildren = expandableNotificationRow.getNotificationChildren()) == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (ExpandableNotificationRow entry : notificationChildren) {
            arrayList.add(entry.getEntry());
        }
        return arrayList;
    }

    public void notifyFullScreenIntentLaunched() {
        setInterruption();
        this.lastFullScreenIntentLaunchTime = SystemClock.elapsedRealtime();
    }

    public boolean hasJustLaunchedFullScreenIntent() {
        return SystemClock.elapsedRealtime() < this.lastFullScreenIntentLaunchTime + 2000;
    }

    public boolean hasJustSentRemoteInput() {
        return SystemClock.elapsedRealtime() < this.lastRemoteInputSent + 500;
    }

    public boolean hasFinishedInitialization() {
        return this.initializationTime == -1 || SystemClock.elapsedRealtime() > this.initializationTime + 400;
    }

    public void createIcons(Context context, StatusBarNotification statusBarNotification) throws InflationException {
        Notification notification2 = statusBarNotification.getNotification();
        Icon smallIcon = notification2.getSmallIcon();
        if (smallIcon != null) {
            this.icon = new StatusBarIconView(context, statusBarNotification.getPackageName() + "/0x" + Integer.toHexString(statusBarNotification.getId()), statusBarNotification);
            this.icon.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            this.expandedIcon = new StatusBarIconView(context, statusBarNotification.getPackageName() + "/0x" + Integer.toHexString(statusBarNotification.getId()), statusBarNotification);
            this.expandedIcon.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            this.aodIcon = new StatusBarIconView(context, statusBarNotification.getPackageName() + "/0x" + Integer.toHexString(statusBarNotification.getId()), statusBarNotification);
            this.aodIcon.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            this.aodIcon.setIncreasedSize(true);
            StatusBarIcon statusBarIcon = new StatusBarIcon(statusBarNotification.getUser(), statusBarNotification.getPackageName(), smallIcon, notification2.iconLevel, notification2.number, StatusBarIconView.contentDescForNotification(context, notification2));
            if (!this.icon.set(statusBarIcon) || !this.expandedIcon.set(statusBarIcon) || !this.aodIcon.set(statusBarIcon)) {
                this.icon = null;
                this.expandedIcon = null;
                this.centeredIcon = null;
                this.aodIcon = null;
                throw new InflationException("Couldn't create icon: " + statusBarIcon);
            }
            this.expandedIcon.setVisibility(4);
            this.expandedIcon.setOnVisibilityChangedListener(new StatusBarIconView.OnVisibilityChangedListener() {
                public final void onVisibilityChanged(int i) {
                    NotificationEntry.this.lambda$createIcons$0$NotificationEntry(i);
                }
            });
            if (this.notification.getNotification().isMediaNotification()) {
                this.centeredIcon = new StatusBarIconView(context, statusBarNotification.getPackageName() + "/0x" + Integer.toHexString(statusBarNotification.getId()), statusBarNotification);
                this.centeredIcon.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                if (!this.centeredIcon.set(statusBarIcon)) {
                    this.centeredIcon = null;
                    throw new InflationException("Couldn't update centered icon: " + statusBarIcon);
                }
                return;
            }
            return;
        }
        throw new InflationException("No small icon in notification from " + statusBarNotification.getPackageName());
    }

    public /* synthetic */ void lambda$createIcons$0$NotificationEntry(int i) {
        ExpandableNotificationRow expandableNotificationRow = this.row;
        if (expandableNotificationRow != null) {
            expandableNotificationRow.setIconsVisible(i != 0);
        }
    }

    public void setIconTag(int i, Object obj) {
        StatusBarIconView statusBarIconView = this.icon;
        if (statusBarIconView != null) {
            statusBarIconView.setTag(i, obj);
            this.expandedIcon.setTag(i, obj);
        }
        StatusBarIconView statusBarIconView2 = this.centeredIcon;
        if (statusBarIconView2 != null) {
            statusBarIconView2.setTag(i, obj);
        }
        StatusBarIconView statusBarIconView3 = this.aodIcon;
        if (statusBarIconView3 != null) {
            statusBarIconView3.setTag(i, obj);
        }
    }

    public void updateIcons(Context context, StatusBarNotification statusBarNotification) throws InflationException {
        if (this.icon != null) {
            Notification notification2 = statusBarNotification.getNotification();
            StatusBarIcon statusBarIcon = new StatusBarIcon(this.notification.getUser(), this.notification.getPackageName(), notification2.getSmallIcon(), notification2.iconLevel, notification2.number, StatusBarIconView.contentDescForNotification(context, notification2));
            this.icon.setNotification(statusBarNotification);
            this.expandedIcon.setNotification(statusBarNotification);
            this.aodIcon.setNotification(statusBarNotification);
            if (!this.icon.set(statusBarIcon) || !this.expandedIcon.set(statusBarIcon) || !this.aodIcon.set(statusBarIcon)) {
                throw new InflationException("Couldn't update icon: " + statusBarIcon);
            }
            StatusBarIconView statusBarIconView = this.centeredIcon;
            if (statusBarIconView != null) {
                statusBarIconView.setNotification(statusBarNotification);
                if (!this.centeredIcon.set(statusBarIcon)) {
                    throw new InflationException("Couldn't update centered icon: " + statusBarIcon);
                }
            }
        }
    }

    public int getContrastedColor(Context context, boolean z, int i) {
        int i2;
        int i3 = z ? 0 : this.notification.getNotification().color;
        if (this.mCachedContrastColorIsFor == i3 && (i2 = this.mCachedContrastColor) != 1) {
            return i2;
        }
        int resolveContrastColor = ContrastColorUtil.resolveContrastColor(context, i3, i);
        this.mCachedContrastColorIsFor = i3;
        this.mCachedContrastColor = resolveContrastColor;
        return this.mCachedContrastColor;
    }

    public void abortTask() {
        InflationTask inflationTask = this.mRunningTask;
        if (inflationTask != null) {
            inflationTask.abort();
            this.mRunningTask = null;
        }
    }

    public void setInflationTask(InflationTask inflationTask) {
        InflationTask inflationTask2;
        InflationTask inflationTask3 = this.mRunningTask;
        abortTask();
        this.mRunningTask = inflationTask;
        if (inflationTask3 != null && (inflationTask2 = this.mRunningTask) != null) {
            inflationTask2.supersedeTask(inflationTask3);
        }
    }

    public void onInflationTaskFinished() {
        this.mRunningTask = null;
    }

    @VisibleForTesting
    public InflationTask getRunningTask() {
        return this.mRunningTask;
    }

    public void setDebugThrowable(Throwable th) {
        this.mDebugThrowable = th;
    }

    public Throwable getDebugThrowable() {
        return this.mDebugThrowable;
    }

    public void onRemoteInputInserted() {
        this.lastRemoteInputSent = -2000;
        this.remoteInputTextWhenReset = null;
    }

    public void setHasSentReply() {
        this.hasSentReply = true;
    }

    public boolean isLastMessageFromReply() {
        Notification.MessagingStyle.Message messageFromBundle;
        if (!this.hasSentReply) {
            return false;
        }
        Bundle bundle = this.notification.getNotification().extras;
        if (!ArrayUtils.isEmpty(bundle.getCharSequenceArray("android.remoteInputHistory"))) {
            return true;
        }
        Parcelable[] parcelableArray = bundle.getParcelableArray("android.messages");
        if (parcelableArray != null && parcelableArray.length > 0) {
            Parcelable parcelable = parcelableArray[parcelableArray.length - 1];
            if ((parcelable instanceof Bundle) && (messageFromBundle = Notification.MessagingStyle.Message.getMessageFromBundle((Bundle) parcelable)) != null) {
                Person senderPerson = messageFromBundle.getSenderPerson();
                if (senderPerson == null) {
                    return true;
                }
                return Objects.equals((Person) bundle.getParcelable("android.messagingUser"), senderPerson);
            }
        }
        return false;
    }

    public void setInitializationTime(long j) {
        if (this.initializationTime == -1) {
            this.initializationTime = j;
        }
    }

    public void sendAccessibilityEvent(int i) {
        ExpandableNotificationRow expandableNotificationRow = this.row;
        if (expandableNotificationRow != null) {
            expandableNotificationRow.sendAccessibilityEvent(i);
        }
    }

    public boolean isMediaNotification() {
        ExpandableNotificationRow expandableNotificationRow = this.row;
        if (expandableNotificationRow == null) {
            return false;
        }
        return expandableNotificationRow.isMediaRow();
    }

    public boolean isTopLevelChild() {
        ExpandableNotificationRow expandableNotificationRow = this.row;
        return expandableNotificationRow != null && expandableNotificationRow.isTopLevelChild();
    }

    public void resetUserExpansion() {
        ExpandableNotificationRow expandableNotificationRow = this.row;
        if (expandableNotificationRow != null) {
            expandableNotificationRow.resetUserExpansion();
        }
    }

    public void freeContentViewWhenSafe(int i) {
        ExpandableNotificationRow expandableNotificationRow = this.row;
        if (expandableNotificationRow != null) {
            expandableNotificationRow.freeContentViewWhenSafe(i);
        }
    }

    public boolean rowExists() {
        return this.row != null;
    }

    public boolean isRowDismissed() {
        ExpandableNotificationRow expandableNotificationRow = this.row;
        return expandableNotificationRow != null && expandableNotificationRow.isDismissed();
    }

    public boolean isRowRemoved() {
        ExpandableNotificationRow expandableNotificationRow = this.row;
        return expandableNotificationRow != null && expandableNotificationRow.isRemoved();
    }

    public boolean isRemoved() {
        ExpandableNotificationRow expandableNotificationRow = this.row;
        return expandableNotificationRow == null || expandableNotificationRow.isRemoved();
    }

    public boolean isRowPinned() {
        ExpandableNotificationRow expandableNotificationRow = this.row;
        return expandableNotificationRow != null && expandableNotificationRow.isPinned();
    }

    public void setRowPinned(boolean z) {
        ExpandableNotificationRow expandableNotificationRow = this.row;
        if (expandableNotificationRow != null) {
            expandableNotificationRow.setPinned(z);
        }
    }

    public boolean isRowHeadsUp() {
        ExpandableNotificationRow expandableNotificationRow = this.row;
        return expandableNotificationRow != null && expandableNotificationRow.isHeadsUp();
    }

    public boolean showingPulsing() {
        ExpandableNotificationRow expandableNotificationRow = this.row;
        return expandableNotificationRow != null && expandableNotificationRow.showingPulsing();
    }

    public void setHeadsUp(boolean z) {
        ExpandableNotificationRow expandableNotificationRow = this.row;
        if (expandableNotificationRow != null) {
            expandableNotificationRow.setHeadsUp(z);
        }
    }

    public void setHeadsUpAnimatingAway(boolean z) {
        ExpandableNotificationRow expandableNotificationRow = this.row;
        if (expandableNotificationRow != null) {
            expandableNotificationRow.setHeadsUpAnimatingAway(z);
        }
    }

    public void setAutoHeadsUp(boolean z) {
        this.mAutoHeadsUp = z;
    }

    public boolean isAutoHeadsUp() {
        return this.mAutoHeadsUp;
    }

    public boolean mustStayOnScreen() {
        ExpandableNotificationRow expandableNotificationRow = this.row;
        return expandableNotificationRow != null && expandableNotificationRow.mustStayOnScreen();
    }

    public void setHeadsUpIsVisible() {
        ExpandableNotificationRow expandableNotificationRow = this.row;
        if (expandableNotificationRow != null) {
            expandableNotificationRow.setHeadsUpIsVisible();
        }
    }

    public ExpandableNotificationRow getHeadsUpAnimationView() {
        return this.row;
    }

    public void setUserLocked(boolean z) {
        ExpandableNotificationRow expandableNotificationRow = this.row;
        if (expandableNotificationRow != null) {
            expandableNotificationRow.setUserLocked(z);
        }
    }

    public void setUserExpanded(boolean z, boolean z2) {
        ExpandableNotificationRow expandableNotificationRow = this.row;
        if (expandableNotificationRow != null) {
            expandableNotificationRow.setUserExpanded(z, z2);
        }
    }

    public void setGroupExpansionChanging(boolean z) {
        ExpandableNotificationRow expandableNotificationRow = this.row;
        if (expandableNotificationRow != null) {
            expandableNotificationRow.setGroupExpansionChanging(z);
        }
    }

    public void notifyHeightChanged(boolean z) {
        ExpandableNotificationRow expandableNotificationRow = this.row;
        if (expandableNotificationRow != null) {
            expandableNotificationRow.notifyHeightChanged(z);
        }
    }

    public void closeRemoteInput() {
        ExpandableNotificationRow expandableNotificationRow = this.row;
        if (expandableNotificationRow != null) {
            expandableNotificationRow.closeRemoteInput();
        }
    }

    public boolean areChildrenExpanded() {
        ExpandableNotificationRow expandableNotificationRow = this.row;
        return expandableNotificationRow != null && expandableNotificationRow.areChildrenExpanded();
    }

    public boolean isGroupNotFullyVisible() {
        ExpandableNotificationRow expandableNotificationRow = this.row;
        return expandableNotificationRow == null || expandableNotificationRow.isGroupNotFullyVisible();
    }

    public NotificationGuts getGuts() {
        ExpandableNotificationRow expandableNotificationRow = this.row;
        if (expandableNotificationRow != null) {
            return expandableNotificationRow.getGuts();
        }
        return null;
    }

    public void removeRow() {
        ExpandableNotificationRow expandableNotificationRow = this.row;
        if (expandableNotificationRow != null) {
            expandableNotificationRow.setRemoved();
        }
    }

    public boolean isSummaryWithChildren() {
        ExpandableNotificationRow expandableNotificationRow = this.row;
        return expandableNotificationRow != null && expandableNotificationRow.isSummaryWithChildren();
    }

    public void setKeepInParent(boolean z) {
        ExpandableNotificationRow expandableNotificationRow = this.row;
        if (expandableNotificationRow != null) {
            expandableNotificationRow.setKeepInParent(z);
        }
    }

    public void onDensityOrFontScaleChanged() {
        ExpandableNotificationRow expandableNotificationRow = this.row;
        if (expandableNotificationRow != null) {
            expandableNotificationRow.onDensityOrFontScaleChanged();
        }
    }

    public boolean areGutsExposed() {
        ExpandableNotificationRow expandableNotificationRow = this.row;
        return (expandableNotificationRow == null || expandableNotificationRow.getGuts() == null || !this.row.getGuts().isExposed()) ? false : true;
    }

    public boolean isChildInGroup() {
        return this.parent == null;
    }

    public boolean isClearable() {
        StatusBarNotification statusBarNotification = this.notification;
        if (statusBarNotification == null || !statusBarNotification.isClearable()) {
            return false;
        }
        List<NotificationEntry> children = getChildren();
        if (children == null || children.size() <= 0) {
            return true;
        }
        for (int i = 0; i < children.size(); i++) {
            if (!children.get(i).isClearable()) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public boolean isExemptFromDndVisualSuppression() {
        if (isNotificationBlockedByPolicy(this.notification.getNotification())) {
            return false;
        }
        if ((this.notification.getNotification().flags & 64) != 0 || this.notification.getNotification().isMediaNotification()) {
            return true;
        }
        Boolean bool = this.mIsSystemNotification;
        if (bool == null || !bool.booleanValue()) {
            return false;
        }
        return true;
    }

    private boolean shouldSuppressVisualEffect(int i) {
        if (!isExemptFromDndVisualSuppression() && (this.suppressedVisualEffects & i) != 0) {
            return true;
        }
        return false;
    }

    public boolean shouldSuppressFullScreenIntent() {
        return shouldSuppressVisualEffect(4);
    }

    public boolean shouldSuppressPeek() {
        return shouldSuppressVisualEffect(16);
    }

    public boolean shouldSuppressStatusBar() {
        return shouldSuppressVisualEffect(32);
    }

    public boolean shouldSuppressAmbient() {
        return shouldSuppressVisualEffect(128);
    }

    public boolean shouldSuppressNotificationList() {
        return shouldSuppressVisualEffect(256);
    }

    public boolean shouldSuppressNotificationDot() {
        return shouldSuppressVisualEffect(64);
    }

    private static boolean isNotificationBlockedByPolicy(Notification notification2) {
        return isCategory("call", notification2) || isCategory("msg", notification2) || isCategory("alarm", notification2) || isCategory("event", notification2) || isCategory("reminder", notification2);
    }

    private static boolean isCategory(String str, Notification notification2) {
        return Objects.equals(notification2.category, str);
    }

    public void setSensitive(boolean z, boolean z2) {
        getRow().setSensitive(z, z2);
        if (z != this.mSensitive) {
            this.mSensitive = z;
            Runnable runnable = this.mOnSensitiveChangedListener;
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    public boolean isSensitive() {
        return this.mSensitive;
    }

    public void setOnSensitiveChangedListener(Runnable runnable) {
        this.mOnSensitiveChangedListener = runnable;
    }

    public boolean isPulseSuppressed() {
        return this.mPulseSupressed;
    }

    public void setPulseSuppressed(boolean z) {
        this.mPulseSupressed = z;
    }

    public static class EditedSuggestionInfo {
        public final int index;
        public final CharSequence originalText;

        public EditedSuggestionInfo(CharSequence charSequence, int i) {
            this.originalText = charSequence;
            this.index = i;
        }
    }
}
