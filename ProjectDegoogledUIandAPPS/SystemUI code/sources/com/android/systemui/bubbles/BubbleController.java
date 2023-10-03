package com.android.systemui.bubbles;

import android.app.ActivityManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ParceledListSlice;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.provider.Settings;
import android.service.notification.NotificationListenerService;
import android.service.notification.ZenModeConfig;
import android.util.ArraySet;
import android.util.Log;
import android.util.Pair;
import android.util.SparseSetArray;
import android.view.IPinnedStackController;
import android.view.IPinnedStackListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.statusbar.IStatusBarService;
import com.android.systemui.C1777R$id;
import com.android.systemui.Dependency;
import com.android.systemui.bubbles.BubbleController;
import com.android.systemui.bubbles.BubbleData;
import com.android.systemui.bubbles.BubbleStackView;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import com.android.systemui.shared.system.TaskStackChangeListener;
import com.android.systemui.shared.system.WindowManagerWrapper;
import com.android.systemui.statusbar.NotificationLockscreenUserManager;
import com.android.systemui.statusbar.NotificationRemoveInterceptor;
import com.android.systemui.statusbar.notification.NotificationEntryListener;
import com.android.systemui.statusbar.notification.NotificationEntryManager;
import com.android.systemui.statusbar.notification.NotificationInterruptionStateProvider;
import com.android.systemui.statusbar.notification.collection.NotificationData;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.phone.NotificationGroupManager;
import com.android.systemui.statusbar.phone.StatusBarWindowController;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.ZenModeController;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class BubbleController implements ConfigurationController.ConfigurationListener {
    /* access modifiers changed from: private */
    public IStatusBarService mBarService;
    /* access modifiers changed from: private */
    public BubbleData mBubbleData;
    private final BubbleData.Listener mBubbleDataListener;
    /* access modifiers changed from: private */
    public final Context mContext;
    private int mCurrentUserId;
    private final NotificationEntryListener mEntryListener;
    private BubbleExpandListener mExpandListener;
    private final NotificationLockscreenUserManager mNotifUserManager;
    /* access modifiers changed from: private */
    public final NotificationEntryManager mNotificationEntryManager;
    /* access modifiers changed from: private */
    public final NotificationGroupManager mNotificationGroupManager;
    /* access modifiers changed from: private */
    public final NotificationInterruptionStateProvider mNotificationInterruptionStateProvider;
    private int mOrientation;
    private final NotificationRemoveInterceptor mRemoveInterceptor;
    private final SparseSetArray<String> mSavedBubbleKeysPerUser;
    /* access modifiers changed from: private */
    public BubbleStackView mStackView;
    private BubbleStateChangeListener mStateChangeListener;
    private StatusBarStateListener mStatusBarStateListener;
    private final StatusBarWindowController mStatusBarWindowController;
    private BubbleStackView.SurfaceSynchronizer mSurfaceSynchronizer;
    private final BubbleTaskStackListener mTaskStackListener;
    private Rect mTempRect;
    private final ZenModeController mZenModeController;

    public interface BubbleExpandListener {
        void onBubbleExpandChanged(boolean z, String str);
    }

    public interface BubbleStateChangeListener {
        void onHasBubblesChanged(boolean z);
    }

    private class StatusBarStateListener implements StatusBarStateController.StateListener {
        private int mState;

        private StatusBarStateListener() {
        }

        public int getCurrentState() {
            return this.mState;
        }

        public void onStateChanged(int i) {
            this.mState = i;
            if (this.mState != 0) {
                BubbleController.this.collapseStack();
            }
            BubbleController.this.updateStack();
        }
    }

    public BubbleController(Context context, StatusBarWindowController statusBarWindowController, BubbleData bubbleData, ConfigurationController configurationController, NotificationInterruptionStateProvider notificationInterruptionStateProvider, ZenModeController zenModeController, NotificationLockscreenUserManager notificationLockscreenUserManager, NotificationGroupManager notificationGroupManager) {
        this(context, statusBarWindowController, bubbleData, (BubbleStackView.SurfaceSynchronizer) null, configurationController, notificationInterruptionStateProvider, zenModeController, notificationLockscreenUserManager, notificationGroupManager);
    }

    public BubbleController(Context context, StatusBarWindowController statusBarWindowController, BubbleData bubbleData, BubbleStackView.SurfaceSynchronizer surfaceSynchronizer, ConfigurationController configurationController, NotificationInterruptionStateProvider notificationInterruptionStateProvider, ZenModeController zenModeController, NotificationLockscreenUserManager notificationLockscreenUserManager, NotificationGroupManager notificationGroupManager) {
        this.mTempRect = new Rect();
        this.mOrientation = 0;
        this.mRemoveInterceptor = new NotificationRemoveInterceptor() {
            public boolean onNotificationRemoveRequested(String str, int i) {
                NotificationEntry notificationEntry = BubbleController.this.mNotificationEntryManager.getNotificationData().get(str);
                String groupKey = notificationEntry != null ? notificationEntry.notification.getGroupKey() : null;
                ArrayList<Bubble> bubblesInGroup = BubbleController.this.mBubbleData.getBubblesInGroup(groupKey);
                boolean hasBubbleWithKey = BubbleController.this.mBubbleData.hasBubbleWithKey(str);
                boolean z = ((BubbleController.this.mBubbleData.isSummarySuppressed(groupKey) && BubbleController.this.mBubbleData.getSummaryKey(groupKey).equals(str)) || (notificationEntry != null && notificationEntry.notification.getNotification().isGroupSummary())) && bubblesInGroup != null && !bubblesInGroup.isEmpty();
                if (!hasBubbleWithKey && !z) {
                    return false;
                }
                boolean z2 = (notificationEntry != null && notificationEntry.isRowDismissed() && !(i == 8 || i == 9)) || (i == 3) || (i == 2 || i == 1) || (i == 12);
                if (z) {
                    return BubbleController.this.handleSummaryRemovalInterception(notificationEntry, z2);
                }
                Bubble bubbleWithKey = BubbleController.this.mBubbleData.getBubbleWithKey(str);
                if (notificationEntry != null && notificationEntry.isBubble() && z2) {
                    bubbleWithKey.setShowInShadeWhenBubble(false);
                    bubbleWithKey.setShowBubbleDot(false);
                    if (BubbleController.this.mStackView != null) {
                        BubbleController.this.mStackView.updateDotVisibility(notificationEntry.key);
                    }
                    BubbleController.this.mNotificationEntryManager.updateNotifications();
                    return true;
                }
                if (!z2 && notificationEntry != null) {
                    BubbleController.this.mBubbleData.notificationEntryRemoved(notificationEntry, 5);
                }
                return false;
            }
        };
        this.mEntryListener = new NotificationEntryListener() {
            public void onPendingEntryAdded(NotificationEntry notificationEntry) {
                if (BubbleController.areBubblesEnabled(BubbleController.this.mContext) && BubbleController.this.mNotificationInterruptionStateProvider.shouldBubbleUp(notificationEntry) && BubbleController.canLaunchInActivityView(BubbleController.this.mContext, notificationEntry)) {
                    BubbleController.this.updateBubble(notificationEntry);
                }
            }

            public void onPreEntryUpdated(NotificationEntry notificationEntry) {
                if (BubbleController.areBubblesEnabled(BubbleController.this.mContext)) {
                    boolean z = BubbleController.this.mNotificationInterruptionStateProvider.shouldBubbleUp(notificationEntry) && BubbleController.canLaunchInActivityView(BubbleController.this.mContext, notificationEntry);
                    if (!z && BubbleController.this.mBubbleData.hasBubbleWithKey(notificationEntry.key)) {
                        BubbleController.this.removeBubble(notificationEntry.key, 7);
                    } else if (z) {
                        BubbleController.this.mBubbleData.getBubbleWithKey(notificationEntry.key);
                        BubbleController.this.updateBubble(notificationEntry);
                    }
                }
            }

            public void onNotificationRankingUpdated(NotificationListenerService.RankingMap rankingMap) {
                BubbleController.this.mBubbleData.notificationRankingUpdated(rankingMap);
            }
        };
        this.mBubbleDataListener = new BubbleData.Listener() {
            public void applyUpdate(BubbleData.Update update) {
                if (BubbleController.this.mStackView == null && update.addedBubble != null) {
                    BubbleController.this.ensureStackViewCreated();
                }
                if (BubbleController.this.mStackView != null) {
                    if (update.addedBubble != null) {
                        BubbleController.this.mStackView.addBubble(update.addedBubble);
                    }
                    if (update.expandedChanged && !update.expanded) {
                        BubbleController.this.mStackView.setExpanded(false);
                    }
                    Iterator it = new ArrayList(update.removedBubbles).iterator();
                    while (it.hasNext()) {
                        Pair pair = (Pair) it.next();
                        Bubble bubble = (Bubble) pair.first;
                        int intValue = ((Integer) pair.second).intValue();
                        BubbleController.this.mStackView.removeBubble(bubble);
                        if (intValue != 8) {
                            if (BubbleController.this.mBubbleData.hasBubbleWithKey(bubble.getKey()) || bubble.showInShadeWhenBubble()) {
                                bubble.getEntry().notification.getNotification().flags &= -4097;
                                try {
                                    BubbleController.this.mBarService.onNotificationBubbleChanged(bubble.getKey(), false);
                                } catch (RemoteException unused) {
                                }
                            } else {
                                BubbleController.this.mNotificationEntryManager.performRemoveNotification(bubble.getEntry().notification, 0);
                            }
                            String groupKey = bubble.getEntry().notification.getGroupKey();
                            if (BubbleController.this.mBubbleData.isSummarySuppressed(groupKey) && BubbleController.this.mBubbleData.getBubblesInGroup(groupKey).isEmpty()) {
                                String summaryKey = BubbleController.this.mBubbleData.getSummaryKey(groupKey);
                                BubbleController.this.mBubbleData.removeSuppressedSummary(groupKey);
                                BubbleController.this.mNotificationEntryManager.performRemoveNotification(BubbleController.this.mNotificationEntryManager.getNotificationData().get(summaryKey).notification, 0);
                            }
                            NotificationEntry logicalGroupSummary = BubbleController.this.mNotificationGroupManager.getLogicalGroupSummary(bubble.getEntry().notification);
                            if (logicalGroupSummary != null) {
                                ArrayList<NotificationEntry> logicalChildren = BubbleController.this.mNotificationGroupManager.getLogicalChildren(logicalGroupSummary.notification);
                                if (!logicalGroupSummary.key.equals(bubble.getEntry().key) && (logicalChildren == null || logicalChildren.isEmpty())) {
                                    BubbleController.this.mNotificationEntryManager.performRemoveNotification(logicalGroupSummary.notification, 0);
                                }
                            }
                        }
                    }
                    if (update.updatedBubble != null) {
                        BubbleController.this.mStackView.updateBubble(update.updatedBubble);
                    }
                    if (update.orderChanged) {
                        BubbleController.this.mStackView.updateBubbleOrder(update.bubbles);
                    }
                    if (update.selectionChanged) {
                        BubbleController.this.mStackView.setSelectedBubble(update.selectedBubble);
                        if (update.selectedBubble != null) {
                            BubbleController.this.mNotificationGroupManager.updateSuppression(update.selectedBubble.getEntry());
                        }
                    }
                    if (update.expandedChanged && update.expanded) {
                        BubbleController.this.mStackView.setExpanded(true);
                    }
                    BubbleController.this.mNotificationEntryManager.updateNotifications();
                    BubbleController.this.updateStack();
                }
            }
        };
        this.mContext = context;
        this.mNotificationInterruptionStateProvider = notificationInterruptionStateProvider;
        this.mNotifUserManager = notificationLockscreenUserManager;
        this.mZenModeController = zenModeController;
        this.mZenModeController.addCallback(new ZenModeController.Callback() {
            public void onZenChanged(int i) {
                if (BubbleController.this.mStackView != null) {
                    BubbleController.this.mStackView.updateDots();
                }
            }

            public void onConfigChanged(ZenModeConfig zenModeConfig) {
                if (BubbleController.this.mStackView != null) {
                    BubbleController.this.mStackView.updateDots();
                }
            }
        });
        configurationController.addCallback(this);
        this.mBubbleData = bubbleData;
        this.mBubbleData.setListener(this.mBubbleDataListener);
        this.mNotificationEntryManager = (NotificationEntryManager) Dependency.get(NotificationEntryManager.class);
        this.mNotificationEntryManager.addNotificationEntryListener(this.mEntryListener);
        this.mNotificationEntryManager.setNotificationRemoveInterceptor(this.mRemoveInterceptor);
        this.mNotificationGroupManager = notificationGroupManager;
        this.mNotificationGroupManager.addOnGroupChangeListener(new NotificationGroupManager.OnGroupChangeListener() {
            public void onGroupSuppressionChanged(NotificationGroupManager.NotificationGroup notificationGroup, boolean z) {
                NotificationEntry notificationEntry = notificationGroup.summary;
                String groupKey = notificationEntry != null ? notificationEntry.notification.getGroupKey() : null;
                if (!z && groupKey != null && BubbleController.this.mBubbleData.isSummarySuppressed(groupKey)) {
                    BubbleController.this.mBubbleData.removeSuppressedSummary(groupKey);
                }
            }
        });
        this.mStatusBarWindowController = statusBarWindowController;
        this.mStatusBarStateListener = new StatusBarStateListener();
        ((StatusBarStateController) Dependency.get(StatusBarStateController.class)).addCallback(this.mStatusBarStateListener);
        this.mTaskStackListener = new BubbleTaskStackListener();
        ActivityManagerWrapper.getInstance().registerTaskStackListener(this.mTaskStackListener);
        try {
            WindowManagerWrapper.getInstance().addPinnedStackListener(new BubblesImeListener());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        this.mSurfaceSynchronizer = surfaceSynchronizer;
        this.mBarService = IStatusBarService.Stub.asInterface(ServiceManager.getService("statusbar"));
        this.mSavedBubbleKeysPerUser = new SparseSetArray<>();
        this.mCurrentUserId = this.mNotifUserManager.getCurrentUserId();
        this.mNotifUserManager.addUserChangedListener(new NotificationLockscreenUserManager.UserChangedListener() {
            public final void onUserChanged(int i) {
                BubbleController.this.lambda$new$0$BubbleController(i);
            }
        });
    }

    public /* synthetic */ void lambda$new$0$BubbleController(int i) {
        saveBubbles(this.mCurrentUserId);
        this.mBubbleData.dismissAll(8);
        restoreBubbles(i);
        this.mCurrentUserId = i;
    }

    /* access modifiers changed from: private */
    public void ensureStackViewCreated() {
        if (this.mStackView == null) {
            this.mStackView = new BubbleStackView(this.mContext, this.mBubbleData, this.mSurfaceSynchronizer);
            ViewGroup statusBarView = this.mStatusBarWindowController.getStatusBarView();
            statusBarView.addView(this.mStackView, statusBarView.indexOfChild(statusBarView.findViewById(C1777R$id.scrim_behind)) + 1, new FrameLayout.LayoutParams(-1, -1));
            BubbleExpandListener bubbleExpandListener = this.mExpandListener;
            if (bubbleExpandListener != null) {
                this.mStackView.setExpandListener(bubbleExpandListener);
            }
        }
    }

    private void saveBubbles(int i) {
        this.mSavedBubbleKeysPerUser.remove(i);
        for (Bubble key : this.mBubbleData.getBubbles()) {
            this.mSavedBubbleKeysPerUser.add(i, key.getKey());
        }
    }

    private void restoreBubbles(int i) {
        NotificationData notificationData = this.mNotificationEntryManager.getNotificationData();
        ArraySet arraySet = this.mSavedBubbleKeysPerUser.get(i);
        if (arraySet != null) {
            Iterator<NotificationEntry> it = notificationData.getNotificationsForCurrentUser().iterator();
            while (it.hasNext()) {
                NotificationEntry next = it.next();
                if (arraySet.contains(next.key) && this.mNotificationInterruptionStateProvider.shouldBubbleUp(next) && canLaunchInActivityView(this.mContext, next)) {
                    updateBubble(next, true);
                }
            }
            this.mSavedBubbleKeysPerUser.remove(this.mCurrentUserId);
        }
    }

    public void onUiModeChanged() {
        BubbleStackView bubbleStackView = this.mStackView;
        if (bubbleStackView != null) {
            bubbleStackView.onThemeChanged();
        }
    }

    public void onOverlayChanged() {
        BubbleStackView bubbleStackView = this.mStackView;
        if (bubbleStackView != null) {
            bubbleStackView.onThemeChanged();
        }
    }

    public void onConfigChanged(Configuration configuration) {
        int i;
        BubbleStackView bubbleStackView = this.mStackView;
        if (bubbleStackView != null && configuration != null && (i = configuration.orientation) != this.mOrientation) {
            this.mOrientation = i;
            bubbleStackView.onOrientationChanged(i);
        }
    }

    public void setBubbleStateChangeListener(BubbleStateChangeListener bubbleStateChangeListener) {
        this.mStateChangeListener = bubbleStateChangeListener;
    }

    public void setExpandListener(BubbleExpandListener bubbleExpandListener) {
        this.mExpandListener = new BubbleExpandListener(bubbleExpandListener) {
            private final /* synthetic */ BubbleController.BubbleExpandListener f$1;

            {
                this.f$1 = r2;
            }

            public final void onBubbleExpandChanged(boolean z, String str) {
                BubbleController.this.lambda$setExpandListener$1$BubbleController(this.f$1, z, str);
            }
        };
        BubbleStackView bubbleStackView = this.mStackView;
        if (bubbleStackView != null) {
            bubbleStackView.setExpandListener(this.mExpandListener);
        }
    }

    public /* synthetic */ void lambda$setExpandListener$1$BubbleController(BubbleExpandListener bubbleExpandListener, boolean z, String str) {
        if (bubbleExpandListener != null) {
            bubbleExpandListener.onBubbleExpandChanged(z, str);
        }
        this.mStatusBarWindowController.setBubbleExpanded(z);
    }

    public boolean hasBubbles() {
        if (this.mStackView == null) {
            return false;
        }
        return this.mBubbleData.hasBubbles();
    }

    public boolean isStackExpanded() {
        return this.mBubbleData.isExpanded();
    }

    public void collapseStack() {
        this.mBubbleData.setExpanded(false);
    }

    public boolean isBubbleNotificationSuppressedFromShade(String str) {
        boolean z = this.mBubbleData.hasBubbleWithKey(str) && !this.mBubbleData.getBubbleWithKey(str).showInShadeWhenBubble();
        NotificationEntry notificationEntry = this.mNotificationEntryManager.getNotificationData().get(str);
        String groupKey = notificationEntry != null ? notificationEntry.notification.getGroupKey() : null;
        boolean isSummarySuppressed = this.mBubbleData.isSummarySuppressed(groupKey);
        if ((!str.equals(this.mBubbleData.getSummaryKey(groupKey)) || !isSummarySuppressed) && !z) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void selectBubble(Bubble bubble) {
        this.mBubbleData.setSelectedBubble(bubble);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void selectBubble(String str) {
        selectBubble(this.mBubbleData.getBubbleWithKey(str));
    }

    public void expandStackAndSelectBubble(String str) {
        Bubble bubbleWithKey = this.mBubbleData.getBubbleWithKey(str);
        if (bubbleWithKey != null) {
            this.mBubbleData.setSelectedBubble(bubbleWithKey);
            this.mBubbleData.setExpanded(true);
        }
    }

    /* access modifiers changed from: package-private */
    public void dismissStack(int i) {
        this.mBubbleData.dismissAll(i);
    }

    public void performBackPressIfNeeded() {
        BubbleStackView bubbleStackView = this.mStackView;
        if (bubbleStackView != null) {
            bubbleStackView.performBackPressIfNeeded();
        }
    }

    /* access modifiers changed from: package-private */
    public void updateBubble(NotificationEntry notificationEntry) {
        updateBubble(notificationEntry, false);
    }

    /* access modifiers changed from: package-private */
    public void updateBubble(NotificationEntry notificationEntry, boolean z) {
        if (notificationEntry.importance >= 4) {
            notificationEntry.setInterruption();
        }
        this.mBubbleData.notificationEntryUpdated(notificationEntry, z);
    }

    /* access modifiers changed from: package-private */
    public void removeBubble(String str, int i) {
        Bubble bubbleWithKey = this.mBubbleData.getBubbleWithKey(str);
        if (bubbleWithKey != null) {
            this.mBubbleData.notificationEntryRemoved(bubbleWithKey.getEntry(), i);
        }
    }

    /* access modifiers changed from: private */
    public boolean handleSummaryRemovalInterception(NotificationEntry notificationEntry, boolean z) {
        String groupKey = notificationEntry.notification.getGroupKey();
        ArrayList<Bubble> bubblesInGroup = this.mBubbleData.getBubblesInGroup(groupKey);
        boolean z2 = false;
        if (z) {
            for (int i = 0; i < bubblesInGroup.size(); i++) {
                Bubble bubble = bubblesInGroup.get(i);
                this.mNotificationGroupManager.onEntryRemoved(bubble.getEntry());
                bubble.setShowInShadeWhenBubble(false);
                bubble.setShowBubbleDot(false);
                BubbleStackView bubbleStackView = this.mStackView;
                if (bubbleStackView != null) {
                    bubbleStackView.updateDotVisibility(bubble.getKey());
                }
            }
            this.mNotificationGroupManager.onEntryRemoved(notificationEntry);
            if ((notificationEntry.notification.getNotification().flags & 1024) != 0) {
                z2 = true;
            }
            if (!z2) {
                this.mBubbleData.addSummaryToSuppress(notificationEntry.notification.getGroupKey(), notificationEntry.key);
                this.mNotificationEntryManager.updateNotifications();
            }
            return !z2;
        }
        this.mBubbleData.removeSuppressedSummary(groupKey);
        for (int i2 = 0; i2 < bubblesInGroup.size(); i2++) {
            this.mBubbleData.notificationEntryRemoved(bubblesInGroup.get(i2).getEntry(), 9);
        }
        return false;
    }

    public void updateStack() {
        if (this.mStackView != null) {
            boolean z = false;
            int i = 4;
            if (this.mStatusBarStateListener.getCurrentState() != 0 || !hasBubbles()) {
                BubbleStackView bubbleStackView = this.mStackView;
                if (bubbleStackView != null) {
                    bubbleStackView.setVisibility(4);
                }
            } else {
                BubbleStackView bubbleStackView2 = this.mStackView;
                if (hasBubbles()) {
                    i = 0;
                }
                bubbleStackView2.setVisibility(i);
            }
            boolean bubblesShowing = this.mStatusBarWindowController.getBubblesShowing();
            if (hasBubbles() && this.mStackView.getVisibility() == 0) {
                z = true;
            }
            this.mStatusBarWindowController.setBubblesShowing(z);
            BubbleStateChangeListener bubbleStateChangeListener = this.mStateChangeListener;
            if (!(bubbleStateChangeListener == null || bubblesShowing == z)) {
                bubbleStateChangeListener.onHasBubblesChanged(z);
            }
            this.mStackView.updateContentDescription();
        }
    }

    public Rect getTouchableRegion() {
        BubbleStackView bubbleStackView = this.mStackView;
        if (bubbleStackView == null || bubbleStackView.getVisibility() != 0) {
            return null;
        }
        this.mStackView.getBoundsOnScreen(this.mTempRect);
        return this.mTempRect;
    }

    public int getExpandedDisplayId(Context context) {
        Bubble expandedBubble = getExpandedBubble(context);
        if (expandedBubble != null) {
            return expandedBubble.getDisplayId();
        }
        return -1;
    }

    private Bubble getExpandedBubble(Context context) {
        if (this.mStackView == null) {
            return null;
        }
        boolean z = context.getDisplay() != null && context.getDisplay().getDisplayId() == 0;
        Bubble expandedBubble = this.mStackView.getExpandedBubble();
        if (!z || expandedBubble == null || !isStackExpanded() || this.mStatusBarWindowController.getPanelExpanded()) {
            return null;
        }
        return expandedBubble;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public BubbleStackView getStackView() {
        return this.mStackView;
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.println("BubbleController state:");
        this.mBubbleData.dump(fileDescriptor, printWriter, strArr);
        printWriter.println();
        BubbleStackView bubbleStackView = this.mStackView;
        if (bubbleStackView != null) {
            bubbleStackView.dump(fileDescriptor, printWriter, strArr);
        }
        printWriter.println();
    }

    private class BubbleTaskStackListener extends TaskStackChangeListener {
        private BubbleTaskStackListener() {
        }

        public void onTaskMovedToFront(ActivityManager.RunningTaskInfo runningTaskInfo) {
            if (BubbleController.this.mStackView != null && runningTaskInfo.displayId == 0 && !BubbleController.this.mStackView.isExpansionAnimating()) {
                BubbleController.this.mBubbleData.setExpanded(false);
            }
        }

        public void onActivityLaunchOnSecondaryDisplayRerouted() {
            if (BubbleController.this.mStackView != null) {
                BubbleController.this.mBubbleData.setExpanded(false);
            }
        }

        public void onBackPressedOnTaskRoot(ActivityManager.RunningTaskInfo runningTaskInfo) {
            if (BubbleController.this.mStackView != null) {
                int i = runningTaskInfo.displayId;
                BubbleController bubbleController = BubbleController.this;
                if (i == bubbleController.getExpandedDisplayId(bubbleController.mContext)) {
                    BubbleController.this.mBubbleData.setExpanded(false);
                }
            }
        }

        public void onSingleTaskDisplayDrawn(int i) {
            Bubble expandedBubble = BubbleController.this.mStackView != null ? BubbleController.this.mStackView.getExpandedBubble() : null;
            if (expandedBubble != null && expandedBubble.getDisplayId() == i) {
                expandedBubble.setContentVisibility(true);
            }
        }

        public void onSingleTaskDisplayEmpty(int i) {
            Bubble expandedBubble = BubbleController.this.mStackView != null ? BubbleController.this.mStackView.getExpandedBubble() : null;
            int displayId = expandedBubble != null ? expandedBubble.getDisplayId() : -1;
            if (BubbleController.this.mStackView != null && BubbleController.this.mStackView.isExpanded() && displayId == i) {
                BubbleController.this.mBubbleData.setExpanded(false);
            }
            BubbleController.this.mBubbleData.notifyDisplayEmpty(i);
        }
    }

    /* access modifiers changed from: private */
    public static boolean areBubblesEnabled(Context context) {
        return Settings.Secure.getInt(context.getContentResolver(), "experiment_enable_bubbles", 1) != 0;
    }

    static boolean canLaunchInActivityView(Context context, NotificationEntry notificationEntry) {
        PendingIntent intent = notificationEntry.getBubbleMetadata() != null ? notificationEntry.getBubbleMetadata().getIntent() : null;
        if (intent == null) {
            Log.w("Bubbles", "Unable to create bubble -- no intent");
            return false;
        }
        ActivityInfo resolveActivityInfo = intent.getIntent().resolveActivityInfo(context.getPackageManager(), 0);
        if (resolveActivityInfo == null) {
            Log.w("Bubbles", "Unable to send as bubble -- couldn't find activity info for intent: " + intent);
            return false;
        } else if (!ActivityInfo.isResizeableMode(resolveActivityInfo.resizeMode)) {
            Log.w("Bubbles", "Unable to send as bubble -- activity is not resizable for intent: " + intent);
            return false;
        } else if (resolveActivityInfo.documentLaunchMode != 2) {
            Log.w("Bubbles", "Unable to send as bubble -- activity is not documentLaunchMode=always for intent: " + intent);
            return false;
        } else if ((resolveActivityInfo.flags & Integer.MIN_VALUE) != 0) {
            return true;
        } else {
            Log.w("Bubbles", "Unable to send as bubble -- activity is not embeddable for intent: " + intent);
            return false;
        }
    }

    private class BubblesImeListener extends IPinnedStackListener.Stub {
        public void onActionsChanged(ParceledListSlice parceledListSlice) throws RemoteException {
        }

        public void onListenerRegistered(IPinnedStackController iPinnedStackController) throws RemoteException {
        }

        public void onMinimizedStateChanged(boolean z) throws RemoteException {
        }

        public void onMovementBoundsChanged(Rect rect, Rect rect2, Rect rect3, boolean z, boolean z2, int i) throws RemoteException {
        }

        public void onShelfVisibilityChanged(boolean z, int i) throws RemoteException {
        }

        private BubblesImeListener() {
        }

        public void onImeVisibilityChanged(boolean z, int i) {
            if (BubbleController.this.mStackView != null && BubbleController.this.mStackView.getBubbleCount() > 0) {
                BubbleController.this.mStackView.post(new Runnable(z, i) {
                    private final /* synthetic */ boolean f$1;
                    private final /* synthetic */ int f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    public final void run() {
                        BubbleController.BubblesImeListener.this.mo9567xb7519a2f(this.f$1, this.f$2);
                    }
                });
            }
        }

        /* renamed from: lambda$onImeVisibilityChanged$0$BubbleController$BubblesImeListener */
        public /* synthetic */ void mo9567xb7519a2f(boolean z, int i) {
            BubbleController.this.mStackView.onImeVisibilityChanged(z, i);
        }
    }
}
