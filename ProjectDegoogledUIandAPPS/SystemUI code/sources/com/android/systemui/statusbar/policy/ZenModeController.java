package com.android.systemui.statusbar.policy;

import android.app.NotificationManager;
import android.net.Uri;
import android.service.notification.ZenModeConfig;

public interface ZenModeController extends CallbackController<Callback> {

    public interface Callback {
        void onConfigChanged(ZenModeConfig zenModeConfig) {
        }

        void onConsolidatedPolicyChanged(NotificationManager.Policy policy) {
        }

        void onEffectsSupressorChanged() {
        }

        void onManualRuleChanged(ZenModeConfig.ZenRule zenRule) {
        }

        void onNextAlarmChanged() {
        }

        void onZenAvailableChanged(boolean z) {
        }

        void onZenChanged(int i) {
        }
    }

    boolean areAlarmsAllowedInPriority();

    boolean areNotificationsHiddenInShade();

    ZenModeConfig getConfig();

    NotificationManager.Policy getConsolidatedPolicy();

    ZenModeConfig.ZenRule getManualRule();

    long getNextAlarm();

    int getZen();

    boolean isVolumeRestricted();

    void setZen(int i, Uri uri, String str);
}
