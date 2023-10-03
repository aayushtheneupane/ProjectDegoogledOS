package com.android.systemui.statusbar.phone;

import android.view.View;

public interface ShadeController {
    void addAfterKeyguardGoneRunnable(Runnable runnable);

    void addPostCollapseAction(Runnable runnable);

    void animateCollapsePanels(int i, boolean z);

    boolean closeShadeIfOpen();

    void collapsePanel(boolean z);

    boolean collapsePanel();

    void goToKeyguard();

    void goToLockedShade(View view);

    void instantExpandNotificationsPanel();

    boolean isDozing();

    boolean isOccluded();

    void onActivationReset();

    void postOnShadeExpanded(Runnable runnable);

    void setLockscreenUser(int i);

    void showBouncer(boolean z);

    void updateAreThereNotifications();

    void wakeUpIfDozing(long j, View view, String str);
}
