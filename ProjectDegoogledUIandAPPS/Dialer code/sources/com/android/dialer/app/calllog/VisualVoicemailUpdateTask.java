package com.android.dialer.app.calllog;

import android.content.Context;
import com.android.dialer.blocking.FilteredNumberAsyncQueryHandler;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DefaultDialerExecutorFactory;
import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import com.android.dialer.telecom.TelecomUtil;
import com.android.tools.p006r8.GeneratedOutlineSupport;

class VisualVoicemailUpdateTask implements DialerExecutor.Worker<Input, Void> {

    static class Input {
        final Context context;
        final FilteredNumberAsyncQueryHandler queryHandler;
        final CallLogNotificationsQueryHelper queryHelper;

        Input(Context context2, CallLogNotificationsQueryHelper callLogNotificationsQueryHelper, FilteredNumberAsyncQueryHandler filteredNumberAsyncQueryHandler) {
            this.context = context2;
            this.queryHelper = callLogNotificationsQueryHelper;
            this.queryHandler = filteredNumberAsyncQueryHandler;
        }
    }

    VisualVoicemailUpdateTask() {
    }

    static /* synthetic */ void lambda$scheduleTask$0(Runnable runnable, Void voidR) {
        LogUtil.m9i("VisualVoicemailUpdateTask.scheduleTask", "update successful", new Object[0]);
        runnable.run();
    }

    static /* synthetic */ void lambda$scheduleTask$1(Runnable runnable, Throwable th) {
        LogUtil.m9i("VisualVoicemailUpdateTask.scheduleTask", GeneratedOutlineSupport.outline6("update failed: ", th), new Object[0]);
        runnable.run();
    }

    static void scheduleTask(Context context, Runnable runnable) {
        Assert.isNotNull(context);
        Assert.isNotNull(runnable);
        if (!TelecomUtil.isDefaultDialer(context)) {
            LogUtil.m9i("VisualVoicemailUpdateTask.scheduleTask", "not default dialer, not running", new Object[0]);
            runnable.run();
            return;
        }
        ((DefaultDialerExecutorFactory) DialerExecutorComponent.get(context).dialerExecutorFactory()).createNonUiTaskBuilder(new VisualVoicemailUpdateTask()).onSuccess(new DialerExecutor.SuccessListener(runnable) {
            private final /* synthetic */ Runnable f$0;

            {
                this.f$0 = r1;
            }

            public final void onSuccess(Object obj) {
                VisualVoicemailUpdateTask.lambda$scheduleTask$0(this.f$0, (Void) obj);
            }
        }).onFailure(new DialerExecutor.FailureListener(runnable) {
            private final /* synthetic */ Runnable f$0;

            {
                this.f$0 = r1;
            }

            public final void onFailure(Throwable th) {
                VisualVoicemailUpdateTask.lambda$scheduleTask$1(this.f$0, th);
            }
        }).build().executeParallel(new Input(context, CallLogNotificationsQueryHelper.getInstance(context), new FilteredNumberAsyncQueryHandler(context)));
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x00c2  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x011e  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x012c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object doInBackground(java.lang.Object r17) throws java.lang.Throwable {
        /*
            r16 = this;
            r0 = r17
            com.android.dialer.app.calllog.VisualVoicemailUpdateTask$Input r0 = (com.android.dialer.app.calllog.VisualVoicemailUpdateTask.Input) r0
            android.content.Context r1 = r0.context
            com.android.dialer.app.calllog.CallLogNotificationsQueryHelper r2 = r0.queryHelper
            com.android.dialer.blocking.FilteredNumberAsyncQueryHandler r0 = r0.queryHandler
            com.android.dialer.common.Assert.isWorkerThread()
            java.lang.String r3 = "VisualVoicemailUpdateTask.updateNotification"
            com.android.dialer.common.LogUtil.enterBlock(r3)
            java.util.List r4 = r2.getNewVoicemails()
            if (r4 != 0) goto L_0x001a
            goto L_0x0179
        L_0x001a:
            boolean r6 = com.android.dialer.blocking.FilteredNumbersUtil.hasRecentEmergencyCall(r1)
            r7 = 0
            if (r6 == 0) goto L_0x0029
            java.lang.Object[] r0 = new java.lang.Object[r7]
            java.lang.String r6 = "not filtering due to recent emergency call"
            com.android.dialer.common.LogUtil.m9i(r3, r6, r0)
            goto L_0x0071
        L_0x0029:
            com.android.dialer.common.Assert.isWorkerThread()
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            java.util.Iterator r4 = r4.iterator()
        L_0x0035:
            boolean r8 = r4.hasNext()
            if (r8 == 0) goto L_0x0060
            java.lang.Object r8 = r4.next()
            com.android.dialer.app.calllog.CallLogNotificationsQueryHelper$NewCall r8 = (com.android.dialer.app.calllog.CallLogNotificationsQueryHelper.NewCall) r8
            java.lang.String r9 = r8.number
            java.lang.String r10 = r8.countryIso
            java.lang.Integer r9 = r0.getBlockedIdSynchronous(r9, r10)
            if (r9 == 0) goto L_0x005c
            java.lang.Object[] r9 = new java.lang.Object[r7]
            java.lang.String r10 = "VisualVoicemailUpdateTask.filterBlockedNumbers"
            java.lang.String r11 = "found voicemail from blocked number, deleting"
            com.android.dialer.common.LogUtil.m9i(r10, r11, r9)
            android.net.Uri r8 = r8.voicemailUri
            if (r8 == 0) goto L_0x0035
            com.android.dialer.app.calllog.CallLogAsyncTaskUtil.deleteVoicemailSynchronous(r1, r8)
            goto L_0x0035
        L_0x005c:
            r6.add(r8)
            goto L_0x0035
        L_0x0060:
            com.android.dialer.common.Assert.isWorkerThread()
            com.android.dialer.spam.SpamComponent r0 = com.android.dialer.spam.SpamComponent.get(r1)
            com.android.dialer.spam.SpamSettings r0 = r0.spamSettings()
            com.android.dialer.spam.stub.SpamSettingsStub r0 = (com.android.dialer.spam.stub.SpamSettingsStub) r0
            r0.isSpamBlockingEnabled()
            r4 = r6
        L_0x0071:
            boolean r0 = r4.isEmpty()
            java.lang.String r6 = "VisualVoicemail_"
            r8 = 1
            if (r0 != 0) goto L_0x00b1
            int r0 = r4.size()
            com.android.dialer.common.Assert.isWorkerThread()
            android.service.notification.StatusBarNotification[] r9 = com.android.dialer.notification.DialerNotificationManager.getActiveNotifications(r1)
            int r10 = r9.length
            r11 = r7
            r12 = r11
        L_0x0088:
            if (r11 >= r10) goto L_0x00ad
            r13 = r9[r11]
            int r14 = r13.getId()
            if (r14 == r8) goto L_0x0093
            goto L_0x00aa
        L_0x0093:
            java.lang.String r14 = r13.getTag()
            boolean r14 = android.text.TextUtils.isEmpty(r14)
            if (r14 != 0) goto L_0x00aa
            java.lang.String r13 = r13.getTag()
            boolean r13 = r13.startsWith(r6)
            if (r13 != 0) goto L_0x00a8
            goto L_0x00aa
        L_0x00a8:
            int r12 = r12 + 1
        L_0x00aa:
            int r11 = r11 + 1
            goto L_0x0088
        L_0x00ad:
            if (r0 <= r12) goto L_0x00b1
            r0 = r8
            goto L_0x00b2
        L_0x00b1:
            r0 = r7
        L_0x00b2:
            com.android.dialer.common.Assert.isWorkerThread()
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            android.service.notification.StatusBarNotification[] r10 = com.android.dialer.notification.DialerNotificationManager.getActiveNotifications(r1)
            int r11 = r10.length
            r12 = r7
        L_0x00c0:
            if (r12 >= r11) goto L_0x0115
            r13 = r10[r12]
            int r14 = r13.getId()
            if (r14 == r8) goto L_0x00cb
            goto L_0x0112
        L_0x00cb:
            java.lang.String r14 = r13.getTag()
            boolean r14 = android.text.TextUtils.isEmpty(r14)
            if (r14 != 0) goto L_0x0112
            java.lang.String r14 = r13.getTag()
            boolean r14 = r14.startsWith(r6)
            if (r14 != 0) goto L_0x00e0
            goto L_0x0112
        L_0x00e0:
            java.lang.String r14 = r13.getTag()
            java.lang.String r15 = ""
            java.lang.String r14 = r14.replace(r6, r15)
            com.android.dialer.app.calllog.CallLogNotificationsQueryHelper$NewCallsQuery r15 = r2.getNewCallsQuery()
            android.net.Uri r14 = android.net.Uri.parse(r14)
            com.android.dialer.app.calllog.CallLogNotificationsQueryHelper$DefaultNewCallsQuery r15 = (com.android.dialer.app.calllog.CallLogNotificationsQueryHelper.DefaultNewCallsQuery) r15
            com.android.dialer.app.calllog.CallLogNotificationsQueryHelper$NewCall r14 = r15.queryUnreadVoicemail(r14)
            if (r14 == 0) goto L_0x00fe
            r9.add(r14)
            goto L_0x0112
        L_0x00fe:
            java.lang.Object[] r14 = new java.lang.Object[r7]
            java.lang.String r15 = "VisualVoicemailUpdateTask.getVoicemailsWithExistingNotification"
            java.lang.String r5 = "voicemail deleted, removing notification"
            com.android.dialer.common.LogUtil.m9i(r15, r5, r14)
            java.lang.String r5 = r13.getTag()
            int r13 = r13.getId()
            com.android.dialer.notification.DialerNotificationManager.cancel(r1, r5, r13)
        L_0x0112:
            int r12 = r12 + 1
            goto L_0x00c0
        L_0x0115:
            r4.addAll(r9)
            boolean r5 = r4.isEmpty()
            if (r5 == 0) goto L_0x012c
            java.lang.Object[] r0 = new java.lang.Object[r7]
            java.lang.String r2 = "no voicemails to notify about"
            com.android.dialer.common.LogUtil.m9i(r3, r2, r0)
            com.android.dialer.app.calllog.CallLogAsyncTaskUtil.cancelAllVoicemailNotifications(r1)
            com.android.dialer.app.calllog.VoicemailNotificationJobService.cancelJob(r1)
            goto L_0x0179
        L_0x012c:
            android.util.ArrayMap r3 = new android.util.ArrayMap
            r3.<init>()
            java.util.Iterator r5 = r4.iterator()
            r6 = 0
        L_0x0136:
            boolean r9 = r5.hasNext()
            if (r9 == 0) goto L_0x0173
            java.lang.Object r9 = r5.next()
            com.android.dialer.app.calllog.CallLogNotificationsQueryHelper$NewCall r9 = (com.android.dialer.app.calllog.CallLogNotificationsQueryHelper.NewCall) r9
            java.lang.String r10 = r9.number
            boolean r10 = r3.containsKey(r10)
            if (r10 != 0) goto L_0x0136
            java.lang.String r10 = r9.number
            int r11 = r9.numberPresentation
            java.lang.String r12 = r9.countryIso
            com.android.dialer.phonenumbercache.ContactInfo r10 = r2.getContactInfo(r10, r11, r12)
            java.lang.String r9 = r9.number
            r3.put(r9, r10)
            boolean r9 = android.text.TextUtils.isEmpty(r6)
            if (r9 == 0) goto L_0x0162
            java.lang.String r6 = r10.name
            goto L_0x0136
        L_0x0162:
            r9 = 2131821187(0x7f110283, float:1.927511E38)
            r11 = 2
            java.lang.Object[] r11 = new java.lang.Object[r11]
            r11[r7] = r6
            java.lang.String r6 = r10.name
            r11[r8] = r6
            java.lang.String r6 = r1.getString(r9, r11)
            goto L_0x0136
        L_0x0173:
            com.android.dialer.app.calllog.CallLogAsyncTaskUtil.showNotifications(r1, r4, r3, r6, r0)
            com.android.dialer.app.calllog.VoicemailNotificationJobService.scheduleJob(r1)
        L_0x0179:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.app.calllog.VisualVoicemailUpdateTask.doInBackground(java.lang.Object):java.lang.Object");
    }
}
