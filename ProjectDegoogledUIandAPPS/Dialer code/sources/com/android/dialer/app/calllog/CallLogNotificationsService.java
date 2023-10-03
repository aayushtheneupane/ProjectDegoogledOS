package com.android.dialer.app.calllog;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.p002v7.appcompat.R$style;
import android.telecom.PhoneAccountHandle;
import com.android.dialer.app.voicemail.LegacyVoicemailNotificationReceiver;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DefaultDialerExecutorFactory;
import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import com.android.dialer.telecom.TelecomUtil;
import com.android.dialer.util.PermissionsUtil;
import com.android.tools.p006r8.GeneratedOutlineSupport;

public class CallLogNotificationsService extends IntentService {
    static final String ACTION_CANCEL_ALL_MISSED_CALLS = "com.android.dialer.calllog.ACTION_CANCEL_ALL_MISSED_CALLS";
    static final String ACTION_MARK_ALL_NEW_VOICEMAILS_AS_OLD = "com.android.dialer.calllog.ACTION_MARK_ALL_NEW_VOICEMAILS_AS_OLD";

    private static class CancelAllMissedCallsWorker implements DialerExecutor.Worker<Context, Void> {
        /* synthetic */ CancelAllMissedCallsWorker(C03141 r1) {
        }

        public Object doInBackground(Object obj) throws Throwable {
            Context context = (Context) obj;
            if (context == null) {
                return null;
            }
            CallLogNotificationsService.access$100(context);
            return null;
        }
    }

    public CallLogNotificationsService() {
        super("CallLogNotificationsService");
    }

    static /* synthetic */ void access$100(Context context) {
        LogUtil.enterBlock("CallLogNotificationsService.cancelAllMissedCallsBackground");
        Assert.isWorkerThread();
        CallLogNotificationsQueryHelper.markAllMissedCallsInCallLogAsRead(context);
        R$style.cancelAllInGroup(context, "MissedCallGroup");
        TelecomUtil.cancelMissedCallsNotification(context);
    }

    public static void cancelAllMissedCalls(Context context) {
        LogUtil.enterBlock("CallLogNotificationsService.cancelAllMissedCalls");
        ((DefaultDialerExecutorFactory) DialerExecutorComponent.get(context).dialerExecutorFactory()).createNonUiTaskBuilder(new CancelAllMissedCallsWorker((C03141) null)).build().executeSerial(context);
    }

    public static PendingIntent createCancelAllMissedCallsPendingIntent(Context context) {
        Intent intent = new Intent(context, CallLogNotificationsService.class);
        intent.setAction(ACTION_CANCEL_ALL_MISSED_CALLS);
        return PendingIntent.getService(context, 0, intent, 0);
    }

    public static PendingIntent createCancelSingleMissedCallPendingIntent(Context context, Uri uri) {
        Intent intent = new Intent(context, CallLogNotificationsService.class);
        intent.setAction("com.android.dialer.calllog.ACTION_CANCEL_SINGLE_MISSED_CALL");
        intent.setData(uri);
        return PendingIntent.getService(context, 0, intent, 0);
    }

    public static PendingIntent createLegacyVoicemailDismissedPendingIntent(Context context, PhoneAccountHandle phoneAccountHandle) {
        Intent intent = new Intent(context, CallLogNotificationsService.class);
        intent.setAction("com.android.dialer.calllog.ACTION_LEGACY_VOICEMAIL_DISMISSED");
        intent.putExtra("PHONE_ACCOUNT_HANDLE", phoneAccountHandle);
        return PendingIntent.getService(context, 0, intent, 0);
    }

    public static PendingIntent createMarkAllNewVoicemailsAsOldIntent(Context context) {
        Intent intent = new Intent(context, CallLogNotificationsService.class);
        intent.setAction(ACTION_MARK_ALL_NEW_VOICEMAILS_AS_OLD);
        return PendingIntent.getService(context, 0, intent, 0);
    }

    public static PendingIntent createMarkSingleNewVoicemailAsOldIntent(Context context, Uri uri) {
        Intent intent = new Intent(context, CallLogNotificationsService.class);
        intent.setAction("com.android.dialer.calllog.ACTION_MARK_SINGLE_NEW_VOICEMAIL_AS_OLD ");
        intent.setData(uri);
        return PendingIntent.getService(context, 0, intent, 0);
    }

    public static void markAllNewVoicemailsAsOld(Context context) {
        LogUtil.enterBlock("CallLogNotificationsService.markAllNewVoicemailsAsOld");
        Intent intent = new Intent(context, CallLogNotificationsService.class);
        intent.setAction(ACTION_MARK_ALL_NEW_VOICEMAILS_AS_OLD);
        context.startService(intent);
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        if (intent == null) {
            LogUtil.m8e("CallLogNotificationsService.onHandleIntent", "could not handle null intent", new Object[0]);
        } else if (!PermissionsUtil.hasPermission(this, "android.permission.READ_CALL_LOG") || !PermissionsUtil.hasPermission(this, "android.permission.WRITE_CALL_LOG")) {
            LogUtil.m8e("CallLogNotificationsService.onHandleIntent", "no READ_CALL_LOG permission", new Object[0]);
        } else {
            String action = intent.getAction();
            LogUtil.m9i("CallLogNotificationsService.onHandleIntent", GeneratedOutlineSupport.outline8("action: ", action), new Object[0]);
            char c = 65535;
            switch (action.hashCode()) {
                case -1752019532:
                    if (action.equals("com.android.dialer.calllog.ACTION_CANCEL_SINGLE_MISSED_CALL")) {
                        c = 4;
                        break;
                    }
                    break;
                case -981351248:
                    if (action.equals("com.android.dialer.calllog.ACTION_MARK_SINGLE_NEW_VOICEMAIL_AS_OLD ")) {
                        c = 1;
                        break;
                    }
                    break;
                case -232689031:
                    if (action.equals("com.android.dialer.calllog.CALL_BACK_FROM_MISSED_CALL_NOTIFICATION")) {
                        c = 5;
                        break;
                    }
                    break;
                case 648238328:
                    if (action.equals(ACTION_CANCEL_ALL_MISSED_CALLS)) {
                        c = 3;
                        break;
                    }
                    break;
                case 701156569:
                    if (action.equals("com.android.dialer.calllog.ACTION_LEGACY_VOICEMAIL_DISMISSED")) {
                        c = 2;
                        break;
                    }
                    break;
                case 2087506236:
                    if (action.equals(ACTION_MARK_ALL_NEW_VOICEMAILS_AS_OLD)) {
                        c = 0;
                        break;
                    }
                    break;
            }
            if (c == 0) {
                VoicemailQueryHandler.markAllNewVoicemailsAsOld(this);
                LogUtil.enterBlock("VisualVoicemailNotifier.cancelAllVoicemailNotifications");
                R$style.cancelAllInGroup(this, "VisualVoicemailGroup");
            } else if (c == 1) {
                Uri data = intent.getData();
                VoicemailQueryHandler.markSingleNewVoicemailAsOld(this, data);
                CallLogAsyncTaskUtil.cancelSingleVoicemailNotification(this, data);
            } else if (c == 2) {
                LegacyVoicemailNotificationReceiver.setDismissed(this, (PhoneAccountHandle) intent.getParcelableExtra("PHONE_ACCOUNT_HANDLE"), true);
            } else if (c == 3) {
                cancelAllMissedCalls(this);
            } else if (c == 4) {
                Uri data2 = intent.getData();
                CallLogNotificationsQueryHelper.markSingleMissedCallInCallLogAsRead(this, data2);
                R$style.cancelSingle(this, data2);
                TelecomUtil.cancelMissedCallsNotification(this);
            } else if (c != 5) {
                LogUtil.m8e("CallLogNotificationsService.onHandleIntent", GeneratedOutlineSupport.outline8("no handler for action: ", action), new Object[0]);
            } else {
                MissedCallNotifier.getInstance(this).callBackFromMissedCall(intent.getStringExtra("android.telecom.extra.NOTIFICATION_PHONE_NUMBER"), intent.getData());
            }
        }
    }
}
