package com.android.systemui.statusbar.phone;

import android.graphics.Point;
import android.graphics.Rect;
import android.provider.Settings;
import android.view.DisplayCutout;
import android.view.View;
import android.view.WindowInsets;
import android.widget.LinearLayout;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.widget.ViewClippingUtil;
import com.android.systemui.C1777R$id;
import com.android.systemui.Dependency;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.CrossFadeHelper;
import com.android.systemui.statusbar.HeadsUpStatusBarView;
import com.android.systemui.statusbar.notification.NotificationWakeUpCoordinator;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.policy.KeyguardMonitor;
import com.android.systemui.statusbar.policy.OnHeadsUpChangedListener;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class HeadsUpAppearanceController implements OnHeadsUpChangedListener, DarkIconDispatcher.DarkReceiver, NotificationWakeUpCoordinator.WakeUpListener {
    private boolean mAnimationsEnabled;
    @VisibleForTesting
    float mAppearFraction;
    private final KeyguardBypassController mBypassController;
    private final View mCenteredIconView;
    private final View mClockView;
    private final CommandQueue mCommandQueue;
    private final LinearLayout mCustomIconArea;
    private final DarkIconDispatcher mDarkIconDispatcher;
    @VisibleForTesting
    float mExpandedHeight;
    private final HeadsUpManagerPhone mHeadsUpManager;
    /* access modifiers changed from: private */
    public final HeadsUpStatusBarView mHeadsUpStatusBarView;
    @VisibleForTesting
    boolean mIsExpanded;
    private KeyguardMonitor mKeyguardMonitor;
    private final NotificationIconAreaController mNotificationIconAreaController;
    private final View mOperatorNameView;
    private final NotificationPanelView mPanelView;
    private final ViewClippingUtil.ClippingParameters mParentClippingParams;
    Point mPoint;
    private final BiConsumer<Float, Float> mSetExpandedHeight;
    private final Consumer<ExpandableNotificationRow> mSetTrackingHeadsUp;
    private boolean mShown;
    private final View.OnLayoutChangeListener mStackScrollLayoutChangeListener;
    /* access modifiers changed from: private */
    public final NotificationStackScrollLayout mStackScroller;
    private final StatusBarStateController mStatusBarStateController;
    private ExpandableNotificationRow mTrackedChild;
    private final Runnable mUpdatePanelTranslation;
    private final NotificationWakeUpCoordinator mWakeUpCoordinator;

    public /* synthetic */ void lambda$new$0$HeadsUpAppearanceController(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        updatePanelTranslation();
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public HeadsUpAppearanceController(com.android.systemui.statusbar.phone.NotificationIconAreaController r16, com.android.systemui.statusbar.phone.HeadsUpManagerPhone r17, android.view.View r18, com.android.systemui.statusbar.SysuiStatusBarStateController r19, com.android.systemui.statusbar.phone.KeyguardBypassController r20, com.android.systemui.statusbar.notification.NotificationWakeUpCoordinator r21) {
        /*
            r15 = this;
            r0 = r18
            int r1 = com.android.systemui.C1777R$id.heads_up_status_bar_view
            android.view.View r1 = r0.findViewById(r1)
            r8 = r1
            com.android.systemui.statusbar.HeadsUpStatusBarView r8 = (com.android.systemui.statusbar.HeadsUpStatusBarView) r8
            int r1 = com.android.systemui.C1777R$id.notification_stack_scroller
            android.view.View r1 = r0.findViewById(r1)
            r9 = r1
            com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout r9 = (com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout) r9
            int r1 = com.android.systemui.C1777R$id.notification_panel
            android.view.View r1 = r0.findViewById(r1)
            r10 = r1
            com.android.systemui.statusbar.phone.NotificationPanelView r10 = (com.android.systemui.statusbar.phone.NotificationPanelView) r10
            int r1 = com.android.systemui.C1777R$id.clock
            android.view.View r11 = r0.findViewById(r1)
            int r1 = com.android.systemui.C1777R$id.operator_name_frame
            android.view.View r12 = r0.findViewById(r1)
            int r1 = com.android.systemui.C1777R$id.centered_icon_area
            android.view.View r13 = r0.findViewById(r1)
            int r1 = com.android.systemui.C1777R$id.left_icon_area
            android.view.View r0 = r0.findViewById(r1)
            r14 = r0
            android.widget.LinearLayout r14 = (android.widget.LinearLayout) r14
            r2 = r15
            r3 = r16
            r4 = r17
            r5 = r19
            r6 = r20
            r7 = r21
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.phone.HeadsUpAppearanceController.<init>(com.android.systemui.statusbar.phone.NotificationIconAreaController, com.android.systemui.statusbar.phone.HeadsUpManagerPhone, android.view.View, com.android.systemui.statusbar.SysuiStatusBarStateController, com.android.systemui.statusbar.phone.KeyguardBypassController, com.android.systemui.statusbar.notification.NotificationWakeUpCoordinator):void");
    }

    @VisibleForTesting
    public HeadsUpAppearanceController(NotificationIconAreaController notificationIconAreaController, HeadsUpManagerPhone headsUpManagerPhone, StatusBarStateController statusBarStateController, KeyguardBypassController keyguardBypassController, NotificationWakeUpCoordinator notificationWakeUpCoordinator, HeadsUpStatusBarView headsUpStatusBarView, NotificationStackScrollLayout notificationStackScrollLayout, NotificationPanelView notificationPanelView, View view, View view2, View view3, LinearLayout linearLayout) {
        this.mSetTrackingHeadsUp = new Consumer() {
            public final void accept(Object obj) {
                HeadsUpAppearanceController.this.setTrackingHeadsUp((ExpandableNotificationRow) obj);
            }
        };
        this.mUpdatePanelTranslation = new Runnable() {
            public final void run() {
                HeadsUpAppearanceController.this.updatePanelTranslation();
            }
        };
        this.mSetExpandedHeight = new BiConsumer() {
            public final void accept(Object obj, Object obj2) {
                HeadsUpAppearanceController.this.setAppearFraction(((Float) obj).floatValue(), ((Float) obj2).floatValue());
            }
        };
        this.mStackScrollLayoutChangeListener = new View.OnLayoutChangeListener() {
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                HeadsUpAppearanceController.this.lambda$new$0$HeadsUpAppearanceController(view, i, i2, i3, i4, i5, i6, i7, i8);
            }
        };
        this.mParentClippingParams = new ViewClippingUtil.ClippingParameters() {
            public boolean shouldFinish(View view) {
                return view.getId() == C1777R$id.status_bar;
            }
        };
        this.mAnimationsEnabled = true;
        this.mNotificationIconAreaController = notificationIconAreaController;
        this.mHeadsUpManager = headsUpManagerPhone;
        this.mHeadsUpManager.addListener(this);
        this.mHeadsUpStatusBarView = headsUpStatusBarView;
        this.mCenteredIconView = view3;
        headsUpStatusBarView.setOnDrawingRectChangedListener(new Runnable() {
            public final void run() {
                HeadsUpAppearanceController.this.lambda$new$1$HeadsUpAppearanceController();
            }
        });
        this.mStackScroller = notificationStackScrollLayout;
        this.mPanelView = notificationPanelView;
        notificationPanelView.addTrackingHeadsUpListener(this.mSetTrackingHeadsUp);
        notificationPanelView.addVerticalTranslationListener(this.mUpdatePanelTranslation);
        notificationPanelView.setHeadsUpAppearanceController(this);
        this.mStackScroller.addOnExpandedHeightChangedListener(this.mSetExpandedHeight);
        this.mStackScroller.addOnLayoutChangeListener(this.mStackScrollLayoutChangeListener);
        this.mStackScroller.setHeadsUpAppearanceController(this);
        this.mClockView = view;
        this.mOperatorNameView = view2;
        this.mCustomIconArea = linearLayout;
        this.mDarkIconDispatcher = (DarkIconDispatcher) Dependency.get(DarkIconDispatcher.class);
        this.mDarkIconDispatcher.addDarkReceiver((DarkIconDispatcher.DarkReceiver) this);
        this.mHeadsUpStatusBarView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                if (HeadsUpAppearanceController.this.shouldBeVisible()) {
                    HeadsUpAppearanceController.this.updateTopEntry();
                    HeadsUpAppearanceController.this.mStackScroller.requestLayout();
                }
                HeadsUpAppearanceController.this.mHeadsUpStatusBarView.removeOnLayoutChangeListener(this);
            }
        });
        this.mBypassController = keyguardBypassController;
        this.mStatusBarStateController = statusBarStateController;
        this.mWakeUpCoordinator = notificationWakeUpCoordinator;
        notificationWakeUpCoordinator.addListener(this);
        this.mCommandQueue = (CommandQueue) SysUiServiceProvider.getComponent(headsUpStatusBarView.getContext(), CommandQueue.class);
        this.mKeyguardMonitor = (KeyguardMonitor) Dependency.get(KeyguardMonitor.class);
    }

    public /* synthetic */ void lambda$new$1$HeadsUpAppearanceController() {
        updateIsolatedIconLocation(true);
    }

    public void destroy() {
        this.mHeadsUpManager.removeListener(this);
        this.mHeadsUpStatusBarView.setOnDrawingRectChangedListener((Runnable) null);
        this.mWakeUpCoordinator.removeListener(this);
        this.mPanelView.removeTrackingHeadsUpListener(this.mSetTrackingHeadsUp);
        this.mPanelView.removeVerticalTranslationListener(this.mUpdatePanelTranslation);
        this.mPanelView.setHeadsUpAppearanceController((HeadsUpAppearanceController) null);
        this.mStackScroller.removeOnExpandedHeightChangedListener(this.mSetExpandedHeight);
        this.mStackScroller.removeOnLayoutChangeListener(this.mStackScrollLayoutChangeListener);
        this.mDarkIconDispatcher.removeDarkReceiver((DarkIconDispatcher.DarkReceiver) this);
    }

    private void updateIsolatedIconLocation(boolean z) {
        this.mNotificationIconAreaController.setIsolatedIconLocation(this.mHeadsUpStatusBarView.getIconDrawingRect(), z);
    }

    public void onHeadsUpPinned(NotificationEntry notificationEntry) {
        updateTopEntry();
        lambda$updateHeadsUpHeaders$4$HeadsUpAppearanceController(notificationEntry);
    }

    private int getRtlTranslation() {
        int i;
        if (this.mPoint == null) {
            this.mPoint = new Point();
        }
        int i2 = 0;
        if (this.mStackScroller.getDisplay() != null) {
            this.mStackScroller.getDisplay().getRealSize(this.mPoint);
            i = this.mPoint.x;
        } else {
            i = 0;
        }
        WindowInsets rootWindowInsets = this.mStackScroller.getRootWindowInsets();
        DisplayCutout displayCutout = rootWindowInsets != null ? rootWindowInsets.getDisplayCutout() : null;
        int stableInsetLeft = rootWindowInsets != null ? rootWindowInsets.getStableInsetLeft() : 0;
        int stableInsetRight = rootWindowInsets != null ? rootWindowInsets.getStableInsetRight() : 0;
        int safeInsetLeft = displayCutout != null ? displayCutout.getSafeInsetLeft() : 0;
        if (displayCutout != null) {
            i2 = displayCutout.getSafeInsetRight();
        }
        return ((Math.max(stableInsetLeft, safeInsetLeft) + this.mStackScroller.getRight()) + Math.max(stableInsetRight, i2)) - i;
    }

    public void updatePanelTranslation() {
        int i;
        if (this.mStackScroller.isLayoutRtl()) {
            i = getRtlTranslation();
        } else {
            i = this.mStackScroller.getLeft();
        }
        this.mHeadsUpStatusBarView.setPanelTranslation(((float) i) + this.mStackScroller.getTranslationX());
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0038  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateTopEntry() {
        /*
            r5 = this;
            boolean r0 = r5.shouldBeVisible()
            r1 = 0
            if (r0 == 0) goto L_0x000e
            com.android.systemui.statusbar.phone.HeadsUpManagerPhone r0 = r5.mHeadsUpManager
            com.android.systemui.statusbar.notification.collection.NotificationEntry r0 = r0.getTopEntry()
            goto L_0x000f
        L_0x000e:
            r0 = r1
        L_0x000f:
            com.android.systemui.statusbar.HeadsUpStatusBarView r2 = r5.mHeadsUpStatusBarView
            com.android.systemui.statusbar.notification.collection.NotificationEntry r2 = r2.getShowingEntry()
            com.android.systemui.statusbar.HeadsUpStatusBarView r3 = r5.mHeadsUpStatusBarView
            r3.lambda$setEntry$0$HeadsUpStatusBarView(r0)
            if (r0 == r2) goto L_0x003d
            r3 = 1
            r4 = 0
            if (r0 != 0) goto L_0x0027
            r5.setShown(r4)
            boolean r2 = r5.mIsExpanded
        L_0x0025:
            r2 = r2 ^ r3
            goto L_0x0030
        L_0x0027:
            if (r2 != 0) goto L_0x002f
            r5.setShown(r3)
            boolean r2 = r5.mIsExpanded
            goto L_0x0025
        L_0x002f:
            r2 = r4
        L_0x0030:
            r5.updateIsolatedIconLocation(r4)
            com.android.systemui.statusbar.phone.NotificationIconAreaController r5 = r5.mNotificationIconAreaController
            if (r0 != 0) goto L_0x0038
            goto L_0x003a
        L_0x0038:
            com.android.systemui.statusbar.StatusBarIconView r1 = r0.icon
        L_0x003a:
            r5.showIconIsolated(r1, r2)
        L_0x003d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.phone.HeadsUpAppearanceController.updateTopEntry():void");
    }

    private void setShown(boolean z) {
        int intForUser = Settings.System.getIntForUser(this.mClockView.getContext().getContentResolver(), "statusbar_clock_style", 0, -2);
        boolean z2 = true;
        if (Settings.System.getIntForUser(this.mClockView.getContext().getContentResolver(), "status_bar_clock", 1, -2) != 1) {
            z2 = false;
        }
        if (this.mShown != z) {
            this.mShown = z;
            if (z) {
                updateParentClipping(false);
                this.mHeadsUpStatusBarView.setVisibility(0);
                show(this.mHeadsUpStatusBarView);
                hide(this.mClockView, 4);
                if (this.mCenteredIconView.getVisibility() != 8) {
                    hide(this.mCenteredIconView, 4);
                }
                View view = this.mOperatorNameView;
                if (view != null) {
                    hide(view, 4);
                }
                hide(this.mCustomIconArea, 4);
            } else {
                if (intForUser != 0 || !z2) {
                    this.mClockView.setVisibility(8);
                } else {
                    show(this.mClockView);
                }
                if (this.mCenteredIconView.getVisibility() != 8) {
                    show(this.mCenteredIconView);
                }
                View view2 = this.mOperatorNameView;
                if (view2 != null) {
                    show(view2);
                }
                show(this.mCustomIconArea);
                hide(this.mHeadsUpStatusBarView, 8, new Runnable() {
                    public final void run() {
                        HeadsUpAppearanceController.this.lambda$setShown$2$HeadsUpAppearanceController();
                    }
                });
            }
            if (this.mStatusBarStateController.getState() != 0) {
                this.mCommandQueue.recomputeDisableFlags(this.mHeadsUpStatusBarView.getContext().getDisplayId(), false);
            }
        }
    }

    public /* synthetic */ void lambda$setShown$2$HeadsUpAppearanceController() {
        updateParentClipping(true);
    }

    private void updateParentClipping(boolean z) {
        ViewClippingUtil.setClippingDeactivated(this.mHeadsUpStatusBarView, !z, this.mParentClippingParams);
    }

    private void hide(View view, int i) {
        hide(view, i, (Runnable) null);
    }

    private void hide(View view, int i, Runnable runnable) {
        if (this.mAnimationsEnabled) {
            CrossFadeHelper.fadeOut(view, 110, 0, new Runnable(view, i, runnable) {
                private final /* synthetic */ View f$0;
                private final /* synthetic */ int f$1;
                private final /* synthetic */ Runnable f$2;

                {
                    this.f$0 = r1;
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    HeadsUpAppearanceController.lambda$hide$3(this.f$0, this.f$1, this.f$2);
                }
            });
            return;
        }
        view.setVisibility(i);
        if (runnable != null) {
            runnable.run();
        }
    }

    static /* synthetic */ void lambda$hide$3(View view, int i, Runnable runnable) {
        view.setVisibility(i);
        if (runnable != null) {
            runnable.run();
        }
    }

    private void show(View view) {
        if (this.mAnimationsEnabled) {
            CrossFadeHelper.fadeIn(view, 110, 100);
        } else {
            view.setVisibility(0);
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void setAnimationsEnabled(boolean z) {
        this.mAnimationsEnabled = z;
    }

    @VisibleForTesting
    public boolean isShown() {
        return this.mShown;
    }

    public boolean shouldBeVisible() {
        boolean z = !this.mWakeUpCoordinator.getNotificationsFullyHidden();
        boolean z2 = !this.mIsExpanded && z;
        if (this.mBypassController.getBypassEnabled() && ((this.mStatusBarStateController.getState() == 1 || this.mKeyguardMonitor.isKeyguardGoingAway()) && z)) {
            z2 = true;
        }
        if (!z2 || !this.mHeadsUpManager.hasPinnedHeadsUp()) {
            return false;
        }
        return true;
    }

    public void onHeadsUpUnPinned(NotificationEntry notificationEntry) {
        updateTopEntry();
        lambda$updateHeadsUpHeaders$4$HeadsUpAppearanceController(notificationEntry);
    }

    public void setAppearFraction(float f, float f2) {
        boolean z = true;
        boolean z2 = f != this.mExpandedHeight;
        this.mExpandedHeight = f;
        this.mAppearFraction = f2;
        if (f <= 0.0f) {
            z = false;
        }
        if (z2) {
            updateHeadsUpHeaders();
        }
        if (z != this.mIsExpanded) {
            this.mIsExpanded = z;
            updateTopEntry();
        }
    }

    public void setTrackingHeadsUp(ExpandableNotificationRow expandableNotificationRow) {
        ExpandableNotificationRow expandableNotificationRow2 = this.mTrackedChild;
        this.mTrackedChild = expandableNotificationRow;
        if (expandableNotificationRow2 != null) {
            lambda$updateHeadsUpHeaders$4$HeadsUpAppearanceController(expandableNotificationRow2.getEntry());
        }
    }

    private void updateHeadsUpHeaders() {
        this.mHeadsUpManager.getAllEntries().forEach(new Consumer() {
            public final void accept(Object obj) {
                HeadsUpAppearanceController.this.lambda$updateHeadsUpHeaders$4$HeadsUpAppearanceController((NotificationEntry) obj);
            }
        });
    }

    /* renamed from: updateHeader */
    public void lambda$updateHeadsUpHeaders$4$HeadsUpAppearanceController(NotificationEntry notificationEntry) {
        float f;
        ExpandableNotificationRow row = notificationEntry.getRow();
        if (row.isPinned() || row.isHeadsUpAnimatingAway() || row == this.mTrackedChild || row.showingPulsing()) {
            f = this.mAppearFraction;
        } else {
            f = 1.0f;
        }
        row.setHeaderVisibleAmount(f);
    }

    public void onDarkChanged(Rect rect, float f, int i) {
        this.mHeadsUpStatusBarView.onDarkChanged(rect, f, i);
    }

    public void onStateChanged() {
        updateTopEntry();
    }

    /* access modifiers changed from: package-private */
    public void readFrom(HeadsUpAppearanceController headsUpAppearanceController) {
        if (headsUpAppearanceController != null) {
            this.mTrackedChild = headsUpAppearanceController.mTrackedChild;
            this.mExpandedHeight = headsUpAppearanceController.mExpandedHeight;
            this.mIsExpanded = headsUpAppearanceController.mIsExpanded;
            this.mAppearFraction = headsUpAppearanceController.mAppearFraction;
        }
    }

    public void onFullyHiddenChanged(boolean z) {
        updateTopEntry();
    }
}
