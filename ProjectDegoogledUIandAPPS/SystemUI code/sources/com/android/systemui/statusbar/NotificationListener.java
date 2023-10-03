package com.android.systemui.statusbar;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Handler;
import android.os.RemoteException;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import com.android.systemui.Dependency;
import com.android.systemui.statusbar.notification.NotificationEntryManager;
import com.android.systemui.statusbar.phone.NotificationGroupManager;
import com.android.systemui.statusbar.phone.NotificationListenerWithPlugins;
import com.android.systemui.statusbar.phone.StatusBar;
import java.util.ArrayList;
import java.util.Iterator;

@SuppressLint({"OverrideAbstract"})
public class NotificationListener extends NotificationListenerWithPlugins {
    private final Context mContext;
    private final NotificationEntryManager mEntryManager = ((NotificationEntryManager) Dependency.get(NotificationEntryManager.class));
    private final NotificationGroupManager mGroupManager = ((NotificationGroupManager) Dependency.get(NotificationGroupManager.class));
    private final NotificationRemoteInputManager mRemoteInputManager = ((NotificationRemoteInputManager) Dependency.get(NotificationRemoteInputManager.class));
    private final ArrayList<NotificationSettingsListener> mSettingsListeners = new ArrayList<>();

    public interface NotificationSettingsListener {
        void onStatusBarIconsBehaviorChanged(boolean z) {
        }
    }

    public NotificationListener(Context context) {
        this.mContext = context;
    }

    public void onListenerConnected() {
        onPluginConnected();
        StatusBarNotification[] activeNotifications = getActiveNotifications();
        if (activeNotifications == null) {
            Log.w("NotificationListener", "onListenerConnected unable to get active notifications.");
            return;
        }
        ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable(activeNotifications, getCurrentRanking()) {
            private final /* synthetic */ StatusBarNotification[] f$1;
            private final /* synthetic */ NotificationListenerService.RankingMap f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                NotificationListener.this.lambda$onListenerConnected$0$NotificationListener(this.f$1, this.f$2);
            }
        });
        onSilentStatusBarIconsVisibilityChanged(((NotificationManager) this.mContext.getSystemService(NotificationManager.class)).shouldHideSilentStatusBarIcons());
    }

    public /* synthetic */ void lambda$onListenerConnected$0$NotificationListener(StatusBarNotification[] statusBarNotificationArr, NotificationListenerService.RankingMap rankingMap) {
        for (StatusBarNotification addNotification : statusBarNotificationArr) {
            this.mEntryManager.addNotification(addNotification, rankingMap);
        }
    }

    public void onNotificationPosted(StatusBarNotification statusBarNotification, NotificationListenerService.RankingMap rankingMap) {
        if (statusBarNotification != null && !onPluginNotificationPosted(statusBarNotification, rankingMap)) {
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable(statusBarNotification, rankingMap) {
                private final /* synthetic */ StatusBarNotification f$1;
                private final /* synthetic */ NotificationListenerService.RankingMap f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    NotificationListener.this.lambda$onNotificationPosted$1$NotificationListener(this.f$1, this.f$2);
                }
            });
        }
    }

    public /* synthetic */ void lambda$onNotificationPosted$1$NotificationListener(StatusBarNotification statusBarNotification, NotificationListenerService.RankingMap rankingMap) {
        RemoteInputController.processForRemoteInput(statusBarNotification.getNotification(), this.mContext);
        String key = statusBarNotification.getKey();
        boolean z = this.mEntryManager.getNotificationData().get(key) != null;
        if (StatusBar.ENABLE_CHILD_NOTIFICATIONS || !this.mGroupManager.isChildInGroupWithSummary(statusBarNotification)) {
            if (z) {
                this.mEntryManager.updateNotification(statusBarNotification, rankingMap);
            } else {
                this.mEntryManager.addNotification(statusBarNotification, rankingMap);
            }
        } else if (z) {
            this.mEntryManager.removeNotification(key, rankingMap, 0);
        } else {
            this.mEntryManager.getNotificationData().updateRanking(rankingMap);
        }
    }

    public void onNotificationRemoved(StatusBarNotification statusBarNotification, NotificationListenerService.RankingMap rankingMap, int i) {
        if (statusBarNotification != null && !onPluginNotificationRemoved(statusBarNotification, rankingMap)) {
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable(statusBarNotification.getKey(), rankingMap, i) {
                private final /* synthetic */ String f$1;
                private final /* synthetic */ NotificationListenerService.RankingMap f$2;
                private final /* synthetic */ int f$3;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                }

                public final void run() {
                    NotificationListener.this.lambda$onNotificationRemoved$2$NotificationListener(this.f$1, this.f$2, this.f$3);
                }
            });
        }
    }

    public /* synthetic */ void lambda$onNotificationRemoved$2$NotificationListener(String str, NotificationListenerService.RankingMap rankingMap, int i) {
        this.mEntryManager.removeNotification(str, rankingMap, i);
    }

    public void onNotificationRemoved(StatusBarNotification statusBarNotification, NotificationListenerService.RankingMap rankingMap) {
        onNotificationRemoved(statusBarNotification, rankingMap, 0);
    }

    public void onNotificationRankingUpdate(NotificationListenerService.RankingMap rankingMap) {
        if (rankingMap != null) {
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable(onPluginRankingUpdate(rankingMap)) {
                private final /* synthetic */ NotificationListenerService.RankingMap f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    NotificationListener.this.lambda$onNotificationRankingUpdate$3$NotificationListener(this.f$1);
                }
            });
        }
    }

    public /* synthetic */ void lambda$onNotificationRankingUpdate$3$NotificationListener(NotificationListenerService.RankingMap rankingMap) {
        this.mEntryManager.updateNotificationRanking(rankingMap);
    }

    public void onSilentStatusBarIconsVisibilityChanged(boolean z) {
        Iterator<NotificationSettingsListener> it = this.mSettingsListeners.iterator();
        while (it.hasNext()) {
            it.next().onStatusBarIconsBehaviorChanged(z);
        }
    }

    public void registerAsSystemService() {
        try {
            registerAsSystemService(this.mContext, new ComponentName(this.mContext.getPackageName(), getClass().getCanonicalName()), -1);
        } catch (RemoteException e) {
            Log.e("NotificationListener", "Unable to register notification listener", e);
        }
    }
}
