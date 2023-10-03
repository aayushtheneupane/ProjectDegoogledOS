package com.android.systemui.statusbar.phone;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.Region;
import android.util.Log;
import android.util.Pools;
import android.view.DisplayCutout;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.collection.ArraySet;
import com.android.internal.annotations.VisibleForTesting;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.C1778R$integer;
import com.android.systemui.Dumpable;
import com.android.systemui.ScreenDecorations;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.AlertingNotificationManager;
import com.android.systemui.statusbar.notification.VisualStabilityManager;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.phone.HeadsUpManagerPhone;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.HeadsUpManager;
import com.android.systemui.statusbar.policy.OnHeadsUpChangedListener;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;

public class HeadsUpManagerPhone extends HeadsUpManager implements Dumpable, VisualStabilityManager.Callback, OnHeadsUpChangedListener, ConfigurationController.ConfigurationListener, StatusBarStateController.StateListener {
    private AnimationStateHandler mAnimationStateHandler;
    /* access modifiers changed from: private */
    public final int mAutoHeadsUpNotificationDecay;
    private final KeyguardBypassController mBypassController;
    private int mDisplayCutoutTouchableRegionSize;
    /* access modifiers changed from: private */
    public boolean mDozing;
    /* access modifiers changed from: private */
    public HashSet<NotificationEntry> mEntriesToRemoveAfterExpand = new HashSet<>();
    /* access modifiers changed from: private */
    public ArraySet<NotificationEntry> mEntriesToRemoveWhenReorderingAllowed = new ArraySet<>();
    private final Pools.Pool<HeadsUpEntryPhone> mEntryPool = new Pools.Pool<HeadsUpEntryPhone>() {
        private Stack<HeadsUpEntryPhone> mPoolObjects = new Stack<>();

        public HeadsUpEntryPhone acquire() {
            if (!this.mPoolObjects.isEmpty()) {
                return this.mPoolObjects.pop();
            }
            return new HeadsUpEntryPhone();
        }

        public boolean release(HeadsUpEntryPhone headsUpEntryPhone) {
            this.mPoolObjects.push(headsUpEntryPhone);
            return true;
        }
    };
    @VisibleForTesting
    final int mExtensionTime;
    private NotificationGroupManager mGroupManager;
    private boolean mHeadsUpGoingAway;
    private int mHeadsUpInset;
    private boolean mIsExpanded;
    /* access modifiers changed from: private */
    public HashSet<String> mKeysToRemoveWhenLeavingKeyguard = new HashSet<>();
    /* access modifiers changed from: private */
    public int mPulseDurationDecay;
    private boolean mReleaseOnExpandFinish;
    private int mStatusBarHeight;
    /* access modifiers changed from: private */
    public int mStatusBarState;
    private final StatusBarStateController mStatusBarStateController;
    /* access modifiers changed from: private */
    public StatusBarTouchableRegionManager mStatusBarTouchableRegionManager;
    private View mStatusBarWindowView;
    private HashSet<String> mSwipedOutKeys = new HashSet<>();
    private int[] mTmpTwoArray = new int[2];
    private Region mTouchableRegion = new Region();
    /* access modifiers changed from: private */
    public boolean mTrackingHeadsUp;
    /* access modifiers changed from: private */
    public VisualStabilityManager mVisualStabilityManager;

    public interface AnimationStateHandler {
        void setHeadsUpGoingAwayAnimationsAllowed(boolean z);
    }

    public HeadsUpManagerPhone(Context context, StatusBarStateController statusBarStateController, KeyguardBypassController keyguardBypassController) {
        super(context);
        Resources resources = this.mContext.getResources();
        this.mExtensionTime = resources.getInteger(C1778R$integer.ambient_notification_extension_time);
        this.mAutoHeadsUpNotificationDecay = resources.getInteger(C1778R$integer.auto_heads_up_notification_decay);
        this.mPulseDurationDecay = resources.getInteger(C1778R$integer.doze_pulse_duration_visible);
        this.mStatusBarStateController = statusBarStateController;
        this.mStatusBarStateController.addCallback(this);
        this.mBypassController = keyguardBypassController;
        initResources();
    }

    public void setUp(View view, NotificationGroupManager notificationGroupManager, StatusBar statusBar, VisualStabilityManager visualStabilityManager) {
        this.mStatusBarWindowView = view;
        this.mStatusBarTouchableRegionManager = new StatusBarTouchableRegionManager(this.mContext, this, statusBar, view);
        this.mGroupManager = notificationGroupManager;
        this.mVisualStabilityManager = visualStabilityManager;
        addListener(new OnHeadsUpChangedListener() {
            public void onHeadsUpPinnedModeChanged(boolean z) {
                if (Log.isLoggable("HeadsUpManagerPhone", 5)) {
                    Log.w("HeadsUpManagerPhone", "onHeadsUpPinnedModeChanged");
                }
                HeadsUpManagerPhone.this.mStatusBarTouchableRegionManager.updateTouchableRegion();
            }
        });
    }

    public void setAnimationStateHandler(AnimationStateHandler animationStateHandler) {
        this.mAnimationStateHandler = animationStateHandler;
    }

    private void initResources() {
        Resources resources = this.mContext.getResources();
        this.mStatusBarHeight = resources.getDimensionPixelSize(17105434);
        this.mHeadsUpInset = this.mStatusBarHeight + resources.getDimensionPixelSize(C1775R$dimen.heads_up_status_bar_padding);
        this.mDisplayCutoutTouchableRegionSize = resources.getDimensionPixelSize(17105143);
    }

    public void onDensityOrFontScaleChanged() {
        super.onDensityOrFontScaleChanged();
        initResources();
    }

    public void onOverlayChanged() {
        initResources();
    }

    public boolean shouldSwallowClick(String str) {
        HeadsUpManager.HeadsUpEntry headsUpEntry = getHeadsUpEntry(str);
        return headsUpEntry != null && this.mClock.currentTimeMillis() < headsUpEntry.mPostTime;
    }

    public void onExpandingFinished() {
        if (this.mReleaseOnExpandFinish) {
            releaseAllImmediately();
            this.mReleaseOnExpandFinish = false;
        } else {
            Iterator<NotificationEntry> it = this.mEntriesToRemoveAfterExpand.iterator();
            while (it.hasNext()) {
                NotificationEntry next = it.next();
                if (isAlerting(next.key)) {
                    removeAlertEntry(next.key);
                }
            }
        }
        this.mEntriesToRemoveAfterExpand.clear();
    }

    public void setTrackingHeadsUp(boolean z) {
        this.mTrackingHeadsUp = z;
    }

    public void setIsPanelExpanded(boolean z) {
        if (z != this.mIsExpanded) {
            this.mIsExpanded = z;
            if (z) {
                this.mHeadsUpGoingAway = false;
            }
            this.mStatusBarTouchableRegionManager.setIsStatusBarExpanded(z);
            this.mStatusBarTouchableRegionManager.updateTouchableRegion();
        }
    }

    public void onStateChanged(int i) {
        boolean z = true;
        boolean z2 = this.mStatusBarState == 1;
        if (i != 1) {
            z = false;
        }
        this.mStatusBarState = i;
        if (z2 && !z && this.mKeysToRemoveWhenLeavingKeyguard.size() != 0) {
            for (String removeAlertEntry : (String[]) this.mKeysToRemoveWhenLeavingKeyguard.toArray(new String[0])) {
                removeAlertEntry(removeAlertEntry);
            }
            this.mKeysToRemoveWhenLeavingKeyguard.clear();
        }
    }

    public void onDozingChanged(boolean z) {
        this.mDozing = z;
        if (!z) {
            for (AlertingNotificationManager.AlertEntry updateEntry : this.mAlertEntries.values()) {
                updateEntry.updateEntry(true);
            }
        }
    }

    public boolean isEntryAutoHeadsUpped(String str) {
        HeadsUpEntryPhone headsUpEntryPhone = getHeadsUpEntryPhone(str);
        if (headsUpEntryPhone == null) {
            return false;
        }
        return headsUpEntryPhone.isAutoHeadsUp();
    }

    public void setHeadsUpGoingAway(boolean z) {
        if (z != this.mHeadsUpGoingAway) {
            this.mHeadsUpGoingAway = z;
            if (!z) {
                this.mStatusBarTouchableRegionManager.updateTouchableRegionAfterLayout();
            } else {
                this.mStatusBarTouchableRegionManager.updateTouchableRegion();
            }
        }
    }

    public boolean isHeadsUpGoingAway() {
        return this.mHeadsUpGoingAway;
    }

    public void setRemoteInputActive(NotificationEntry notificationEntry, boolean z) {
        HeadsUpEntryPhone headsUpEntryPhone = getHeadsUpEntryPhone(notificationEntry.key);
        if (headsUpEntryPhone != null && headsUpEntryPhone.remoteInputActive != z) {
            headsUpEntryPhone.remoteInputActive = z;
            if (z) {
                headsUpEntryPhone.removeAutoRemovalCallbacks();
            } else {
                headsUpEntryPhone.updateEntry(false);
            }
        }
    }

    public void setMenuShown(NotificationEntry notificationEntry, boolean z) {
        HeadsUpManager.HeadsUpEntry headsUpEntry = getHeadsUpEntry(notificationEntry.key);
        if ((headsUpEntry instanceof HeadsUpEntryPhone) && notificationEntry.isRowPinned()) {
            ((HeadsUpEntryPhone) headsUpEntry).setMenuShownPinned(z);
        }
    }

    public void extendHeadsUp() {
        HeadsUpEntryPhone topHeadsUpEntryPhone = getTopHeadsUpEntryPhone();
        if (topHeadsUpEntryPhone != null) {
            topHeadsUpEntryPhone.extendPulse();
        }
    }

    public boolean isTrackingHeadsUp() {
        return this.mTrackingHeadsUp;
    }

    public void snooze() {
        super.snooze();
        this.mReleaseOnExpandFinish = true;
    }

    public void addSwipedOutNotification(String str) {
        this.mSwipedOutKeys.add(str);
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.println("HeadsUpManagerPhone state:");
        dumpInternal(fileDescriptor, printWriter, strArr);
    }

    public void updateTouchableRegion(ViewTreeObserver.InternalInsetsInfo internalInsetsInfo) {
        internalInsetsInfo.setTouchableInsets(3);
        internalInsetsInfo.touchableRegion.set(calculateTouchableRegion());
    }

    public Region calculateTouchableRegion() {
        NotificationEntry groupSummary;
        NotificationEntry topEntry = getTopEntry();
        if (!hasPinnedHeadsUp() || topEntry == null) {
            this.mTouchableRegion.set(0, 0, this.mStatusBarWindowView.getWidth(), this.mStatusBarHeight);
            updateRegionForNotch(this.mTouchableRegion);
        } else {
            if (topEntry.isChildInGroup() && (groupSummary = this.mGroupManager.getGroupSummary(topEntry.notification)) != null) {
                topEntry = groupSummary;
            }
            ExpandableNotificationRow row = topEntry.getRow();
            row.getLocationOnScreen(this.mTmpTwoArray);
            int[] iArr = this.mTmpTwoArray;
            this.mTouchableRegion.set(iArr[0], 0, iArr[0] + row.getWidth(), this.mHeadsUpInset + row.getIntrinsicHeight());
        }
        return this.mTouchableRegion;
    }

    private void updateRegionForNotch(Region region) {
        DisplayCutout displayCutout = this.mStatusBarWindowView.getRootWindowInsets().getDisplayCutout();
        if (displayCutout != null) {
            Rect rect = new Rect();
            ScreenDecorations.DisplayCutoutView.boundsFromDirection(displayCutout, 48, rect);
            rect.offset(0, this.mDisplayCutoutTouchableRegionSize);
            region.union(rect);
        }
    }

    public boolean shouldExtendLifetime(NotificationEntry notificationEntry) {
        return this.mVisualStabilityManager.isReorderingAllowed() && super.shouldExtendLifetime(notificationEntry);
    }

    public void onConfigChanged(Configuration configuration) {
        initResources();
    }

    public void onReorderingAllowed() {
        this.mAnimationStateHandler.setHeadsUpGoingAwayAnimationsAllowed(false);
        Iterator<NotificationEntry> it = this.mEntriesToRemoveWhenReorderingAllowed.iterator();
        while (it.hasNext()) {
            NotificationEntry next = it.next();
            if (isAlerting(next.key)) {
                removeAlertEntry(next.key);
            }
        }
        this.mEntriesToRemoveWhenReorderingAllowed.clear();
        this.mAnimationStateHandler.setHeadsUpGoingAwayAnimationsAllowed(true);
    }

    /* access modifiers changed from: protected */
    public HeadsUpManager.HeadsUpEntry createAlertEntry() {
        return (HeadsUpManager.HeadsUpEntry) this.mEntryPool.acquire();
    }

    /* access modifiers changed from: protected */
    public void onAlertEntryRemoved(AlertingNotificationManager.AlertEntry alertEntry) {
        this.mKeysToRemoveWhenLeavingKeyguard.remove(alertEntry.mEntry.key);
        super.onAlertEntryRemoved(alertEntry);
        this.mEntryPool.release((HeadsUpEntryPhone) alertEntry);
    }

    /* access modifiers changed from: protected */
    public boolean shouldHeadsUpBecomePinned(NotificationEntry notificationEntry) {
        boolean z = this.mStatusBarState == 0 && !this.mIsExpanded;
        if (this.mBypassController.getBypassEnabled()) {
            z |= this.mStatusBarState == 1;
        }
        if (z || super.shouldHeadsUpBecomePinned(notificationEntry)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void dumpInternal(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dumpInternal(fileDescriptor, printWriter, strArr);
        printWriter.print("  mBarState=");
        printWriter.println(this.mStatusBarState);
        printWriter.print("  mTouchableRegion=");
        printWriter.println(this.mTouchableRegion);
    }

    private HeadsUpEntryPhone getHeadsUpEntryPhone(String str) {
        return (HeadsUpEntryPhone) this.mAlertEntries.get(str);
    }

    private HeadsUpEntryPhone getTopHeadsUpEntryPhone() {
        return (HeadsUpEntryPhone) getTopHeadsUpEntry();
    }

    /* access modifiers changed from: protected */
    public boolean canRemoveImmediately(String str) {
        if (this.mSwipedOutKeys.contains(str)) {
            this.mSwipedOutKeys.remove(str);
            return true;
        }
        HeadsUpEntryPhone headsUpEntryPhone = getHeadsUpEntryPhone(str);
        HeadsUpEntryPhone topHeadsUpEntryPhone = getTopHeadsUpEntryPhone();
        if (headsUpEntryPhone == null || headsUpEntryPhone != topHeadsUpEntryPhone || super.canRemoveImmediately(str)) {
            return true;
        }
        return false;
    }

    protected class HeadsUpEntryPhone extends HeadsUpManager.HeadsUpEntry {
        private boolean extended;
        private boolean mIsAutoHeadsUp;
        private boolean mMenuShownPinned;

        protected HeadsUpEntryPhone() {
            super();
        }

        /* access modifiers changed from: protected */
        public boolean isSticky() {
            return super.isSticky() || this.mMenuShownPinned;
        }

        public void setEntry(NotificationEntry notificationEntry) {
            setEntry(notificationEntry, new Runnable(notificationEntry) {
                private final /* synthetic */ NotificationEntry f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    HeadsUpManagerPhone.HeadsUpEntryPhone.this.lambda$setEntry$0$HeadsUpManagerPhone$HeadsUpEntryPhone(this.f$1);
                }
            });
        }

        public /* synthetic */ void lambda$setEntry$0$HeadsUpManagerPhone$HeadsUpEntryPhone(NotificationEntry notificationEntry) {
            if (!HeadsUpManagerPhone.this.mVisualStabilityManager.isReorderingAllowed() && !notificationEntry.showingPulsing()) {
                HeadsUpManagerPhone.this.mEntriesToRemoveWhenReorderingAllowed.add(notificationEntry);
                HeadsUpManagerPhone.this.mVisualStabilityManager.addReorderingAllowedCallback(HeadsUpManagerPhone.this);
            } else if (HeadsUpManagerPhone.this.mTrackingHeadsUp) {
                HeadsUpManagerPhone.this.mEntriesToRemoveAfterExpand.add(notificationEntry);
            } else if (!this.mIsAutoHeadsUp || HeadsUpManagerPhone.this.mStatusBarState != 1) {
                HeadsUpManagerPhone.this.removeAlertEntry(notificationEntry.key);
            } else {
                HeadsUpManagerPhone.this.mKeysToRemoveWhenLeavingKeyguard.add(notificationEntry.key);
            }
        }

        public void updateEntry(boolean z) {
            this.mIsAutoHeadsUp = this.mEntry.isAutoHeadsUp();
            super.updateEntry(z);
            if (HeadsUpManagerPhone.this.mEntriesToRemoveAfterExpand.contains(this.mEntry)) {
                HeadsUpManagerPhone.this.mEntriesToRemoveAfterExpand.remove(this.mEntry);
            }
            if (HeadsUpManagerPhone.this.mEntriesToRemoveWhenReorderingAllowed.contains(this.mEntry)) {
                HeadsUpManagerPhone.this.mEntriesToRemoveWhenReorderingAllowed.remove(this.mEntry);
            }
            HeadsUpManagerPhone.this.mKeysToRemoveWhenLeavingKeyguard.remove(this.mEntry.key);
        }

        public void setExpanded(boolean z) {
            if (this.expanded != z) {
                this.expanded = z;
                if (z) {
                    removeAutoRemovalCallbacks();
                } else {
                    updateEntry(false);
                }
            }
        }

        public void setMenuShownPinned(boolean z) {
            if (this.mMenuShownPinned != z) {
                this.mMenuShownPinned = z;
                if (z) {
                    removeAutoRemovalCallbacks();
                } else {
                    updateEntry(false);
                }
            }
        }

        public void reset() {
            super.reset();
            this.mMenuShownPinned = false;
            this.extended = false;
            this.mIsAutoHeadsUp = false;
        }

        /* access modifiers changed from: private */
        public void extendPulse() {
            if (!this.extended) {
                this.extended = true;
                updateEntry(false);
            }
        }

        public int compareTo(AlertingNotificationManager.AlertEntry alertEntry) {
            boolean isAutoHeadsUp = isAutoHeadsUp();
            boolean isAutoHeadsUp2 = ((HeadsUpEntryPhone) alertEntry).isAutoHeadsUp();
            if (isAutoHeadsUp && !isAutoHeadsUp2) {
                return 1;
            }
            if (isAutoHeadsUp || !isAutoHeadsUp2) {
                return super.compareTo(alertEntry);
            }
            return -1;
        }

        /* access modifiers changed from: protected */
        public long calculateFinishTime() {
            return this.mPostTime + ((long) getDecayDuration()) + ((long) (this.extended ? HeadsUpManagerPhone.this.mExtensionTime : 0));
        }

        private int getDecayDuration() {
            if (HeadsUpManagerPhone.this.mDozing) {
                return getRecommendedHeadsUpTimeoutMs(HeadsUpManagerPhone.this.mPulseDurationDecay);
            }
            if (isAutoHeadsUp()) {
                return getRecommendedHeadsUpTimeoutMs(HeadsUpManagerPhone.this.mAutoHeadsUpNotificationDecay);
            }
            return getRecommendedHeadsUpTimeoutMs(HeadsUpManagerPhone.this.mAutoDismissNotificationDecay);
        }

        /* access modifiers changed from: private */
        public boolean isAutoHeadsUp() {
            return this.mIsAutoHeadsUp;
        }
    }
}
