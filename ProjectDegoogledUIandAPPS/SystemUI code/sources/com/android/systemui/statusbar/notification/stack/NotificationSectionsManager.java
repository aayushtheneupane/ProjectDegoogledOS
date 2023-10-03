package com.android.systemui.statusbar.notification.stack;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.internal.annotations.VisibleForTesting;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.notification.row.ActivatableNotificationView;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.stack.StackScrollAlgorithm;
import com.android.systemui.statusbar.policy.ConfigurationController;

class NotificationSectionsManager implements StackScrollAlgorithm.SectionProvider {
    private final ActivityStarter mActivityStarter;
    private final ConfigurationController mConfigurationController;
    private final ConfigurationController.ConfigurationListener mConfigurationListener = new ConfigurationController.ConfigurationListener() {
        public void onLocaleListChanged() {
            NotificationSectionsManager.this.mGentleHeader.reinflateContents();
        }
    };
    private ExpandableNotificationRow mFirstGentleNotif;
    /* access modifiers changed from: private */
    public SectionHeaderView mGentleHeader;
    private boolean mGentleHeaderVisible = false;
    private boolean mInitialized = false;
    private View.OnClickListener mOnClearGentleNotifsClickListener;
    private final NotificationStackScrollLayout mParent;
    private final StatusBarStateController mStatusBarStateController;
    private final boolean mUseMultipleSections;

    NotificationSectionsManager(NotificationStackScrollLayout notificationStackScrollLayout, ActivityStarter activityStarter, StatusBarStateController statusBarStateController, ConfigurationController configurationController, boolean z) {
        this.mParent = notificationStackScrollLayout;
        this.mActivityStarter = activityStarter;
        this.mStatusBarStateController = statusBarStateController;
        this.mConfigurationController = configurationController;
        this.mUseMultipleSections = z;
    }

    /* access modifiers changed from: package-private */
    public void initialize(LayoutInflater layoutInflater) {
        if (!this.mInitialized) {
            this.mInitialized = true;
            reinflateViews(layoutInflater);
            this.mConfigurationController.addCallback(this.mConfigurationListener);
            return;
        }
        throw new IllegalStateException("NotificationSectionsManager already initialized");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void reinflateViews(android.view.LayoutInflater r6) {
        /*
            r5 = this;
            com.android.systemui.statusbar.notification.stack.SectionHeaderView r0 = r5.mGentleHeader
            r1 = -1
            if (r0 == 0) goto L_0x002f
            android.view.ViewGroup r0 = r0.getTransientContainer()
            if (r0 == 0) goto L_0x0017
            com.android.systemui.statusbar.notification.stack.SectionHeaderView r0 = r5.mGentleHeader
            android.view.ViewGroup r0 = r0.getTransientContainer()
            com.android.systemui.statusbar.notification.stack.SectionHeaderView r2 = r5.mGentleHeader
            r0.removeView(r2)
            goto L_0x002f
        L_0x0017:
            com.android.systemui.statusbar.notification.stack.SectionHeaderView r0 = r5.mGentleHeader
            android.view.ViewParent r0 = r0.getParent()
            if (r0 == 0) goto L_0x002f
            com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout r0 = r5.mParent
            com.android.systemui.statusbar.notification.stack.SectionHeaderView r2 = r5.mGentleHeader
            int r0 = r0.indexOfChild(r2)
            com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout r2 = r5.mParent
            com.android.systemui.statusbar.notification.stack.SectionHeaderView r3 = r5.mGentleHeader
            r2.removeView(r3)
            goto L_0x0030
        L_0x002f:
            r0 = r1
        L_0x0030:
            int r2 = com.android.systemui.C1779R$layout.status_bar_notification_section_header
            com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout r3 = r5.mParent
            r4 = 0
            android.view.View r6 = r6.inflate(r2, r3, r4)
            com.android.systemui.statusbar.notification.stack.SectionHeaderView r6 = (com.android.systemui.statusbar.notification.stack.SectionHeaderView) r6
            r5.mGentleHeader = r6
            com.android.systemui.statusbar.notification.stack.SectionHeaderView r6 = r5.mGentleHeader
            com.android.systemui.statusbar.notification.stack.-$$Lambda$NotificationSectionsManager$Lm4LNd4tUWZPNzSmZnkDovE-xCU r2 = new com.android.systemui.statusbar.notification.stack.-$$Lambda$NotificationSectionsManager$Lm4LNd4tUWZPNzSmZnkDovE-xCU
            r2.<init>()
            r6.setOnHeaderClickListener(r2)
            com.android.systemui.statusbar.notification.stack.SectionHeaderView r6 = r5.mGentleHeader
            com.android.systemui.statusbar.notification.stack.-$$Lambda$NotificationSectionsManager$BXFcLGpgdZnd7PRimoedNDlJa8o r2 = new com.android.systemui.statusbar.notification.stack.-$$Lambda$NotificationSectionsManager$BXFcLGpgdZnd7PRimoedNDlJa8o
            r2.<init>()
            r6.setOnClearAllClickListener(r2)
            if (r0 == r1) goto L_0x005a
            com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout r6 = r5.mParent
            com.android.systemui.statusbar.notification.stack.SectionHeaderView r5 = r5.mGentleHeader
            r6.addView(r5, r0)
        L_0x005a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.stack.NotificationSectionsManager.reinflateViews(android.view.LayoutInflater):void");
    }

    /* access modifiers changed from: package-private */
    public void setOnClearGentleNotifsClickListener(View.OnClickListener onClickListener) {
        this.mOnClearGentleNotifsClickListener = onClickListener;
    }

    /* access modifiers changed from: package-private */
    public void onUiModeChanged() {
        this.mGentleHeader.onUiModeChanged();
    }

    public boolean beginsSection(View view) {
        return view == getFirstLowPriorityChild();
    }

    /* access modifiers changed from: package-private */
    public void updateSectionBoundaries() {
        if (this.mUseMultipleSections) {
            this.mFirstGentleNotif = null;
            int i = -1;
            int childCount = this.mParent.getChildCount();
            int i2 = 0;
            while (true) {
                if (i2 >= childCount) {
                    break;
                }
                View childAt = this.mParent.getChildAt(i2);
                if ((childAt instanceof ExpandableNotificationRow) && childAt.getVisibility() != 8) {
                    ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) childAt;
                    if (!expandableNotificationRow.getEntry().isTopBucket()) {
                        this.mFirstGentleNotif = expandableNotificationRow;
                        i = i2;
                        break;
                    }
                }
                i2++;
            }
            adjustGentleHeaderVisibilityAndPosition(i);
            this.mGentleHeader.setAreThereDismissableGentleNotifs(this.mParent.hasActiveClearableNotifications(2));
        }
    }

    private void adjustGentleHeaderVisibilityAndPosition(int i) {
        boolean z = (i == -1 || this.mStatusBarStateController.getState() == 1) ? false : true;
        int indexOfChild = this.mParent.indexOfChild(this.mGentleHeader);
        if (!z) {
            if (this.mGentleHeaderVisible) {
                this.mGentleHeaderVisible = false;
                this.mParent.removeView(this.mGentleHeader);
            }
        } else if (!this.mGentleHeaderVisible) {
            this.mGentleHeaderVisible = true;
            if (this.mGentleHeader.getTransientContainer() != null) {
                this.mGentleHeader.getTransientContainer().removeTransientView(this.mGentleHeader);
                this.mGentleHeader.setTransientContainer((ViewGroup) null);
            }
            this.mParent.addView(this.mGentleHeader, i);
        } else if (indexOfChild != i - 1) {
            if (indexOfChild < i) {
                i--;
            }
            this.mParent.changeViewPosition(this.mGentleHeader, i);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean updateFirstAndLastViewsInSections(NotificationSection notificationSection, NotificationSection notificationSection2, ActivatableNotificationView activatableNotificationView, ActivatableNotificationView activatableNotificationView2) {
        if (this.mUseMultipleSections) {
            ActivatableNotificationView lastVisibleChild = notificationSection.getLastVisibleChild();
            ActivatableNotificationView firstVisibleChild = notificationSection2.getFirstVisibleChild();
            ActivatableNotificationView lastHighPriorityChild = getLastHighPriorityChild();
            ActivatableNotificationView firstLowPriorityChild = getFirstLowPriorityChild();
            if (lastHighPriorityChild != null && firstLowPriorityChild != null) {
                notificationSection.setFirstVisibleChild(activatableNotificationView);
                notificationSection.setLastVisibleChild(lastHighPriorityChild);
                notificationSection2.setFirstVisibleChild(firstLowPriorityChild);
                notificationSection2.setLastVisibleChild(activatableNotificationView2);
            } else if (lastHighPriorityChild != null) {
                notificationSection.setFirstVisibleChild(activatableNotificationView);
                notificationSection.setLastVisibleChild(activatableNotificationView2);
                notificationSection2.setFirstVisibleChild((ActivatableNotificationView) null);
                notificationSection2.setLastVisibleChild((ActivatableNotificationView) null);
            } else {
                notificationSection.setFirstVisibleChild((ActivatableNotificationView) null);
                notificationSection.setLastVisibleChild((ActivatableNotificationView) null);
                notificationSection2.setFirstVisibleChild(activatableNotificationView);
                notificationSection2.setLastVisibleChild(activatableNotificationView2);
            }
            if (lastHighPriorityChild == lastVisibleChild && firstLowPriorityChild == firstVisibleChild) {
                return false;
            }
            return true;
        }
        notificationSection.setFirstVisibleChild(activatableNotificationView);
        notificationSection.setLastVisibleChild(activatableNotificationView2);
        return false;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public SectionHeaderView getGentleHeaderView() {
        return this.mGentleHeader;
    }

    private ActivatableNotificationView getFirstLowPriorityChild() {
        if (this.mGentleHeaderVisible) {
            return this.mGentleHeader;
        }
        return this.mFirstGentleNotif;
    }

    private ActivatableNotificationView getLastHighPriorityChild() {
        int childCount = this.mParent.getChildCount();
        ExpandableNotificationRow expandableNotificationRow = null;
        for (int i = 0; i < childCount; i++) {
            View childAt = this.mParent.getChildAt(i);
            if (childAt.getVisibility() != 8 && (childAt instanceof ExpandableNotificationRow)) {
                ExpandableNotificationRow expandableNotificationRow2 = (ExpandableNotificationRow) childAt;
                if (!expandableNotificationRow2.getEntry().isTopBucket()) {
                    break;
                }
                expandableNotificationRow = expandableNotificationRow2;
            }
        }
        return expandableNotificationRow;
    }

    /* access modifiers changed from: private */
    public void onGentleHeaderClick(View view) {
        this.mActivityStarter.startActivity(new Intent("android.settings.NOTIFICATION_SETTINGS"), true, true, 536870912);
    }

    /* access modifiers changed from: private */
    public void onClearGentleNotifsClick(View view) {
        View.OnClickListener onClickListener = this.mOnClearGentleNotifsClickListener;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }
}
