package com.android.systemui.statusbar.notification;

import android.content.Context;
import android.util.ArraySet;
import com.android.internal.annotations.VisibleForTesting;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.NotificationLockscreenUserManager;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.systemui.statusbar.phone.UnlockMethodCache;
import com.android.systemui.statusbar.policy.KeyguardMonitor;
import java.util.Iterator;

public class DynamicPrivacyController implements UnlockMethodCache.OnUnlockMethodChangedListener {
    private boolean mCacheInvalid;
    private final KeyguardMonitor mKeyguardMonitor;
    private boolean mLastDynamicUnlocked;
    private ArraySet<Listener> mListeners;
    private final NotificationLockscreenUserManager mLockscreenUserManager;
    private final StatusBarStateController mStateController;
    private StatusBarKeyguardViewManager mStatusBarKeyguardViewManager;
    private final UnlockMethodCache mUnlockMethodCache;

    public interface Listener {
        void onDynamicPrivacyChanged();
    }

    DynamicPrivacyController(Context context, KeyguardMonitor keyguardMonitor, NotificationLockscreenUserManager notificationLockscreenUserManager, StatusBarStateController statusBarStateController) {
        this(notificationLockscreenUserManager, keyguardMonitor, UnlockMethodCache.getInstance(context), statusBarStateController);
    }

    @VisibleForTesting
    DynamicPrivacyController(NotificationLockscreenUserManager notificationLockscreenUserManager, KeyguardMonitor keyguardMonitor, UnlockMethodCache unlockMethodCache, StatusBarStateController statusBarStateController) {
        this.mListeners = new ArraySet<>();
        this.mLockscreenUserManager = notificationLockscreenUserManager;
        this.mStateController = statusBarStateController;
        this.mUnlockMethodCache = unlockMethodCache;
        this.mKeyguardMonitor = keyguardMonitor;
        this.mUnlockMethodCache.addListener(this);
        this.mLastDynamicUnlocked = isDynamicallyUnlocked();
    }

    public void onUnlockMethodStateChanged() {
        if (isDynamicPrivacyEnabled()) {
            boolean isDynamicallyUnlocked = isDynamicallyUnlocked();
            if (isDynamicallyUnlocked != this.mLastDynamicUnlocked || this.mCacheInvalid) {
                this.mLastDynamicUnlocked = isDynamicallyUnlocked;
                Iterator<Listener> it = this.mListeners.iterator();
                while (it.hasNext()) {
                    it.next().onDynamicPrivacyChanged();
                }
            }
            this.mCacheInvalid = false;
            return;
        }
        this.mCacheInvalid = true;
    }

    private boolean isDynamicPrivacyEnabled() {
        NotificationLockscreenUserManager notificationLockscreenUserManager = this.mLockscreenUserManager;
        return !notificationLockscreenUserManager.shouldHideNotifications(notificationLockscreenUserManager.getCurrentUserId());
    }

    public boolean isDynamicallyUnlocked() {
        return (this.mUnlockMethodCache.canSkipBouncer() || this.mKeyguardMonitor.isKeyguardGoingAway() || this.mKeyguardMonitor.isKeyguardFadingAway()) && isDynamicPrivacyEnabled();
    }

    public void addListener(Listener listener) {
        this.mListeners.add(listener);
    }

    public boolean isInLockedDownShade() {
        int state;
        if (!this.mStatusBarKeyguardViewManager.isShowing() || !this.mUnlockMethodCache.isMethodSecure() || (((state = this.mStateController.getState()) != 0 && state != 2) || !isDynamicPrivacyEnabled() || isDynamicallyUnlocked())) {
            return false;
        }
        return true;
    }

    public void setStatusBarKeyguardViewManager(StatusBarKeyguardViewManager statusBarKeyguardViewManager) {
        this.mStatusBarKeyguardViewManager = statusBarKeyguardViewManager;
    }
}
