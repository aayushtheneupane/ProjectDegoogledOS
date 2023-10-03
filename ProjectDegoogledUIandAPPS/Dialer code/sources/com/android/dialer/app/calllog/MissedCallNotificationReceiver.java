package com.android.dialer.app.calllog;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.p000v4.util.Pair;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DefaultDialerExecutorFactory;
import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import p010me.leolin.shortcutbadger.ShortcutBadger;

public class MissedCallNotificationReceiver extends BroadcastReceiver {
    static /* synthetic */ void lambda$onReceive$0(Context context, int i, BroadcastReceiver.PendingResult pendingResult, Void voidR) {
        LogUtil.m9i("MissedCallNotificationReceiver.onReceive", "update missed call notifications successful", new Object[0]);
        LogUtil.m9i("MissedCallNotificationReceiver.updateBadgeCount", "update badge count: %d success: %b", Integer.valueOf(i), Boolean.valueOf(ShortcutBadger.applyCount(context, i)));
        pendingResult.finish();
    }

    static /* synthetic */ void lambda$onReceive$1(BroadcastReceiver.PendingResult pendingResult, Throwable th) {
        LogUtil.m9i("MissedCallNotificationReceiver.onReceive", "update missed call notifications failed", new Object[0]);
        pendingResult.finish();
    }

    public void onReceive(Context context, Intent intent) {
        if ("android.telecom.action.SHOW_MISSED_CALLS_NOTIFICATION".equals(intent.getAction())) {
            LogUtil.enterBlock("MissedCallNotificationReceiver.onReceive");
            int intExtra = intent.getIntExtra("android.telecom.extra.NOTIFICATION_COUNT", -1);
            String stringExtra = intent.getStringExtra("android.telecom.extra.NOTIFICATION_PHONE_NUMBER");
            BroadcastReceiver.PendingResult goAsync = goAsync();
            ((DefaultDialerExecutorFactory) DialerExecutorComponent.get(context).dialerExecutorFactory()).createNonUiTaskBuilder(MissedCallNotifier.getInstance(context)).onSuccess(new DialerExecutor.SuccessListener(context, intExtra, goAsync) {
                private final /* synthetic */ Context f$0;
                private final /* synthetic */ int f$1;
                private final /* synthetic */ BroadcastReceiver.PendingResult f$2;

                {
                    this.f$0 = r1;
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void onSuccess(Object obj) {
                    MissedCallNotificationReceiver.lambda$onReceive$0(this.f$0, this.f$1, this.f$2, (Void) obj);
                }
            }).onFailure(new DialerExecutor.FailureListener(goAsync) {
                private final /* synthetic */ BroadcastReceiver.PendingResult f$0;

                {
                    this.f$0 = r1;
                }

                public final void onFailure(Throwable th) {
                    MissedCallNotificationReceiver.lambda$onReceive$1(this.f$0, th);
                }
            }).build().executeParallel(new Pair(Integer.valueOf(intExtra), stringExtra));
        }
    }
}
