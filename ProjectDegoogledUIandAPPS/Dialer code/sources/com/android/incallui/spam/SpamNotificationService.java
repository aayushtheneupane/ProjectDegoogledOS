package com.android.incallui.spam;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.spam.promo.SpamBlockingPromoHelper;

public class SpamNotificationService extends Service {
    private int notificationId;
    private String notificationTag;

    private void logCallImpression(Intent intent, DialerImpression$Type dialerImpression$Type) {
        ((LoggingBindingsStub) Logger.get(this)).logCallImpression(dialerImpression$Type, intent.getStringExtra("service_call_id"), intent.getLongExtra("service_call_start_time_millis", 0));
    }

    public /* synthetic */ void lambda$onStartCommand$0$SpamNotificationService(SpamBlockingPromoHelper spamBlockingPromoHelper, boolean z) {
        if (!z) {
            ((LoggingBindingsStub) Logger.get(this)).logImpression(DialerImpression$Type.SPAM_BLOCKING_MODIFY_FAILURE_THROUGH_AFTER_CALL_NOTIFICATION_PROMO);
        }
        spamBlockingPromoHelper.showModifySettingOnCompleteToast(z);
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onDestroy() {
        super.onDestroy();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0075, code lost:
        if (r4.equals("com.android.incallui.spam.ACTION_MARK_NUMBER_AS_SPAM") == false) goto L_0x008c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x008f  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00c3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int onStartCommand(android.content.Intent r12, int r13, int r14) {
        /*
            r11 = this;
            r13 = 2
            if (r12 != 0) goto L_0x0007
            r11.stopSelf()
            return r13
        L_0x0007:
            java.lang.String r14 = "service_phone_number"
            java.lang.String r14 = r12.getStringExtra(r14)
            java.lang.String r0 = "service_notification_tag"
            java.lang.String r0 = r12.getStringExtra(r0)
            r11.notificationTag = r0
            r0 = 1
            java.lang.String r1 = "service_notification_id"
            int r1 = r12.getIntExtra(r1, r0)
            r11.notificationId = r1
            java.lang.String r6 = android.support.p002v7.appcompat.R$style.getCurrentCountryIso(r11)
            r1 = 0
            java.lang.String r2 = "service_contact_lookup_result_type"
            int r2 = r12.getIntExtra(r2, r1)
            com.android.dialer.logging.ContactLookupResult$Type r5 = com.android.dialer.logging.ContactLookupResult$Type.forNumber(r2)
            com.android.dialer.spam.SpamComponent r2 = com.android.dialer.spam.SpamComponent.get(r11)
            com.android.dialer.spam.SpamSettings r2 = r2.spamSettings()
            com.android.dialer.spam.promo.SpamBlockingPromoHelper r3 = new com.android.dialer.spam.promo.SpamBlockingPromoHelper
            com.android.dialer.spam.SpamComponent r4 = com.android.dialer.spam.SpamComponent.get(r11)
            com.android.dialer.spam.SpamSettings r4 = r4.spamSettings()
            r3.<init>(r11, r4)
            java.lang.String r4 = r12.getAction()
            java.lang.String r7 = "com.android.incallui.spam.ACTION_MARK_NUMBER_AS_SPAM"
            boolean r4 = r7.equals(r4)
            if (r4 == 0) goto L_0x0051
            r3.shouldShowSpamBlockingPromo()
        L_0x0051:
            java.lang.String r4 = r11.notificationTag
            int r8 = r11.notificationId
            com.android.dialer.notification.DialerNotificationManager.cancel(r11, r4, r8)
            java.lang.String r4 = r12.getAction()
            r8 = -1
            int r9 = r4.hashCode()
            r10 = -1824400322(0xffffffff9341dc3e, float:-2.4468613E-27)
            if (r9 == r10) goto L_0x0082
            r10 = -1292075633(0xffffffffb2fc7d8f, float:-2.9393759E-8)
            if (r9 == r10) goto L_0x0078
            r10 = -474617725(0xffffffffe3b5e883, float:-6.71123E21)
            if (r9 == r10) goto L_0x0071
            goto L_0x008c
        L_0x0071:
            boolean r4 = r4.equals(r7)
            if (r4 == 0) goto L_0x008c
            goto L_0x008d
        L_0x0078:
            java.lang.String r1 = "com.android.incallui.spam.ACTION_MARK_NUMBER_AS_NOT_SPAM"
            boolean r1 = r4.equals(r1)
            if (r1 == 0) goto L_0x008c
            r1 = r0
            goto L_0x008d
        L_0x0082:
            java.lang.String r1 = "com.android.incallui.spam.ACTION_ENABLE_SPAM_BLOCKING"
            boolean r1 = r4.equals(r1)
            if (r1 == 0) goto L_0x008c
            r1 = r13
            goto L_0x008d
        L_0x008c:
            r1 = r8
        L_0x008d:
            if (r1 == 0) goto L_0x00c3
            if (r1 == r0) goto L_0x00aa
            if (r1 == r13) goto L_0x0094
            goto L_0x00e4
        L_0x0094:
            com.android.dialer.logging.LoggingBindings r12 = com.android.dialer.logging.Logger.get(r11)
            com.android.dialer.logging.DialerImpression$Type r14 = com.android.dialer.logging.DialerImpression$Type.SPAM_BLOCKING_ENABLED_THROUGH_AFTER_CALL_NOTIFICATION_PROMO
            com.android.dialer.logging.LoggingBindingsStub r12 = (com.android.dialer.logging.LoggingBindingsStub) r12
            r12.logImpression(r14)
            com.android.incallui.spam.-$$Lambda$SpamNotificationService$PGqOWj7uY-lbLn3Vld6lo4TdoS8 r12 = new com.android.incallui.spam.-$$Lambda$SpamNotificationService$PGqOWj7uY-lbLn3Vld6lo4TdoS8
            r12.<init>(r3)
            com.android.dialer.spam.stub.SpamSettingsStub r2 = (com.android.dialer.spam.stub.SpamSettingsStub) r2
            r2.modifySpamBlockingSetting(r0, r12)
            goto L_0x00e4
        L_0x00aa:
            com.android.dialer.logging.DialerImpression$Type r0 = com.android.dialer.logging.DialerImpression$Type.SPAM_NOTIFICATION_SERVICE_ACTION_MARK_NUMBER_AS_NOT_SPAM
            r11.logCallImpression(r12, r0)
            com.android.dialer.spam.SpamComponent r12 = com.android.dialer.spam.SpamComponent.get(r11)
            com.android.dialer.spam.Spam r12 = r12.spam()
            r3 = 1
            com.android.dialer.logging.ReportingLocation$Type r4 = com.android.dialer.logging.ReportingLocation$Type.FEEDBACK_PROMPT
            r0 = r12
            com.android.dialer.spam.stub.SpamStub r0 = (com.android.dialer.spam.stub.SpamStub) r0
            r1 = r14
            r2 = r6
            r0.reportNotSpamFromAfterCallNotification(r1, r2, r3, r4, r5)
            goto L_0x00e4
        L_0x00c3:
            com.android.dialer.logging.DialerImpression$Type r0 = com.android.dialer.logging.DialerImpression$Type.SPAM_NOTIFICATION_SERVICE_ACTION_MARK_NUMBER_AS_SPAM
            r11.logCallImpression(r12, r0)
            com.android.dialer.spam.SpamComponent r12 = com.android.dialer.spam.SpamComponent.get(r11)
            com.android.dialer.spam.Spam r12 = r12.spam()
            r3 = 1
            com.android.dialer.logging.ReportingLocation$Type r4 = com.android.dialer.logging.ReportingLocation$Type.FEEDBACK_PROMPT
            r0 = r12
            com.android.dialer.spam.stub.SpamStub r0 = (com.android.dialer.spam.stub.SpamStub) r0
            r1 = r14
            r2 = r6
            r0.reportSpamFromAfterCallNotification(r1, r2, r3, r4, r5)
            com.android.dialer.blocking.FilteredNumberAsyncQueryHandler r12 = new com.android.dialer.blocking.FilteredNumberAsyncQueryHandler
            r12.<init>(r11)
            r0 = 0
            r12.blockNumber(r0, r14, r6)
        L_0x00e4:
            r11.stopSelf()
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.incallui.spam.SpamNotificationService.onStartCommand(android.content.Intent, int, int):int");
    }
}
