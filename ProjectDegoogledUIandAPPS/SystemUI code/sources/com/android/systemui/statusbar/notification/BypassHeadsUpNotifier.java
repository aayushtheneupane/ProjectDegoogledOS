package com.android.systemui.statusbar.notification;

import android.content.Context;
import android.media.MediaMetadata;
import android.provider.Settings;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.NotificationLockscreenUserManager;
import com.android.systemui.statusbar.NotificationMediaManager;
import com.android.systemui.statusbar.notification.collection.NotificationData;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.phone.HeadsUpManagerPhone;
import com.android.systemui.statusbar.phone.KeyguardBypassController;
import com.android.systemui.tuner.TunerService;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BypassHeadsUpNotifier.kt */
public final class BypassHeadsUpNotifier implements StatusBarStateController.StateListener, NotificationMediaManager.MediaListener {
    private final KeyguardBypassController bypassController;
    /* access modifiers changed from: private */
    public final Context context;
    private NotificationEntry currentMediaEntry;
    /* access modifiers changed from: private */
    public boolean enabled = true;
    private NotificationEntryManager entryManager;
    private boolean fullyAwake;
    private final HeadsUpManagerPhone headsUpManager;
    private final NotificationMediaManager mediaManager;
    private final NotificationLockscreenUserManager notificationLockscreenUserManager;
    private final StatusBarStateController statusBarStateController;

    public BypassHeadsUpNotifier(Context context2, KeyguardBypassController keyguardBypassController, StatusBarStateController statusBarStateController2, HeadsUpManagerPhone headsUpManagerPhone, NotificationLockscreenUserManager notificationLockscreenUserManager2, NotificationMediaManager notificationMediaManager, TunerService tunerService) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(keyguardBypassController, "bypassController");
        Intrinsics.checkParameterIsNotNull(statusBarStateController2, "statusBarStateController");
        Intrinsics.checkParameterIsNotNull(headsUpManagerPhone, "headsUpManager");
        Intrinsics.checkParameterIsNotNull(notificationLockscreenUserManager2, "notificationLockscreenUserManager");
        Intrinsics.checkParameterIsNotNull(notificationMediaManager, "mediaManager");
        Intrinsics.checkParameterIsNotNull(tunerService, "tunerService");
        this.context = context2;
        this.bypassController = keyguardBypassController;
        this.statusBarStateController = statusBarStateController2;
        this.headsUpManager = headsUpManagerPhone;
        this.notificationLockscreenUserManager = notificationLockscreenUserManager2;
        this.mediaManager = notificationMediaManager;
        this.statusBarStateController.addCallback(this);
        tunerService.addTunable(new TunerService.Tunable(this) {
            final /* synthetic */ BypassHeadsUpNotifier this$0;

            {
                this.this$0 = r1;
            }

            public final void onTuningChanged(String str, String str2) {
                BypassHeadsUpNotifier bypassHeadsUpNotifier = this.this$0;
                boolean z = false;
                if (Settings.Secure.getIntForUser(bypassHeadsUpNotifier.context.getContentResolver(), "show_media_when_bypassing", 0, KeyguardUpdateMonitor.getCurrentUser()) != 0) {
                    z = true;
                }
                bypassHeadsUpNotifier.enabled = z;
            }
        }, "show_media_when_bypassing");
    }

    public final void setFullyAwake(boolean z) {
        this.fullyAwake = z;
        if (z) {
            updateAutoHeadsUp(this.currentMediaEntry);
        }
    }

    public void onMetadataOrStateChanged(MediaMetadata mediaMetadata, int i) {
        NotificationEntry notificationEntry = this.currentMediaEntry;
        NotificationEntryManager notificationEntryManager = this.entryManager;
        if (notificationEntryManager != null) {
            NotificationEntry notificationEntry2 = notificationEntryManager.getNotificationData().get(this.mediaManager.getMediaNotificationKey());
            if (!NotificationMediaManager.isPlayingState(i)) {
                notificationEntry2 = null;
            }
            this.currentMediaEntry = notificationEntry2;
            updateAutoHeadsUp(notificationEntry);
            updateAutoHeadsUp(this.currentMediaEntry);
            return;
        }
        Intrinsics.throwUninitializedPropertyAccessException("entryManager");
        throw null;
    }

    private final void updateAutoHeadsUp(NotificationEntry notificationEntry) {
        if (notificationEntry != null) {
            boolean z = Intrinsics.areEqual((Object) notificationEntry, (Object) this.currentMediaEntry) && canAutoHeadsUp(notificationEntry);
            notificationEntry.setAutoHeadsUp(z);
            if (z) {
                this.headsUpManager.showNotification(notificationEntry);
            }
        }
    }

    private final boolean canAutoHeadsUp(NotificationEntry notificationEntry) {
        if (!isAutoHeadsUpAllowed() || notificationEntry.isSensitive() || !this.notificationLockscreenUserManager.shouldShowOnKeyguard(notificationEntry)) {
            return false;
        }
        NotificationEntryManager notificationEntryManager = this.entryManager;
        if (notificationEntryManager != null) {
            NotificationData notificationData = notificationEntryManager.getNotificationData();
            Intrinsics.checkExpressionValueIsNotNull(notificationData, "entryManager.notificationData");
            if (!notificationData.getActiveNotifications().contains(notificationEntry)) {
                return false;
            }
            return true;
        }
        Intrinsics.throwUninitializedPropertyAccessException("entryManager");
        throw null;
    }

    public void onStatePostChange() {
        updateAutoHeadsUp(this.currentMediaEntry);
    }

    private final boolean isAutoHeadsUpAllowed() {
        if (this.enabled && this.bypassController.getBypassEnabled() && this.statusBarStateController.getState() == 1 && this.fullyAwake) {
            return true;
        }
        return false;
    }
}
