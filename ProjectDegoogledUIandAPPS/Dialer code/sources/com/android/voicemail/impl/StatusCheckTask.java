package com.android.voicemail.impl;

import android.annotation.TargetApi;
import android.content.Context;
import android.telecom.PhoneAccountHandle;
import com.android.voicemail.impl.scheduling.BaseTask;

@TargetApi(26)
public class StatusCheckTask extends BaseTask {
    public StatusCheckTask() {
        super(4);
    }

    public static void start(Context context, PhoneAccountHandle phoneAccountHandle) {
        context.sendBroadcast(BaseTask.createIntent(context, StatusCheckTask.class, phoneAccountHandle));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0118, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0121, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onExecuteInBackgroundThread() {
        /*
            r5 = this;
            android.content.Context r0 = r5.getContext()
            java.lang.Class<android.telephony.TelephonyManager> r1 = android.telephony.TelephonyManager.class
            java.lang.Object r0 = r0.getSystemService(r1)
            android.telephony.TelephonyManager r0 = (android.telephony.TelephonyManager) r0
            android.telecom.PhoneAccountHandle r1 = r5.getPhoneAccountHandle()
            android.telephony.TelephonyManager r0 = r0.createForPhoneAccountHandle(r1)
            java.lang.String r1 = "StatusCheckTask.onExecuteInBackgroundThread"
            if (r0 != 0) goto L_0x0031
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            android.telecom.PhoneAccountHandle r5 = r5.getPhoneAccountHandle()
            r0.append(r5)
            java.lang.String r5 = " no longer valid"
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            com.android.voicemail.impl.VvmLog.m46w(r1, r5)
            return
        L_0x0031:
            android.telephony.ServiceState r0 = r0.getServiceState()
            int r0 = r0.getState()
            if (r0 == 0) goto L_0x0054
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            android.telecom.PhoneAccountHandle r5 = r5.getPhoneAccountHandle()
            r0.append(r5)
            java.lang.String r5 = " not in service"
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            com.android.voicemail.impl.VvmLog.m45i(r1, r5)
            return
        L_0x0054:
            com.android.voicemail.impl.OmtpVvmCarrierConfigHelper r0 = new com.android.voicemail.impl.OmtpVvmCarrierConfigHelper
            android.content.Context r2 = r5.getContext()
            android.telecom.PhoneAccountHandle r3 = r5.getPhoneAccountHandle()
            r0.<init>(r2, r3)
            boolean r2 = r0.isValid()
            if (r2 != 0) goto L_0x0087
            java.lang.String r0 = "config no longer valid for "
            java.lang.StringBuilder r0 = com.android.tools.p006r8.GeneratedOutlineSupport.outline13(r0)
            android.telecom.PhoneAccountHandle r2 = r5.getPhoneAccountHandle()
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            com.android.voicemail.impl.VvmLog.m43e(r1, r0)
            android.content.Context r0 = r5.getContext()
            android.telecom.PhoneAccountHandle r5 = r5.getPhoneAccountHandle()
            com.android.voicemail.impl.sync.VvmAccountManager.removeAccount(r0, r5)
            return
        L_0x0087:
            com.android.voicemail.impl.sms.StatusSmsFetcher r2 = new com.android.voicemail.impl.sms.StatusSmsFetcher     // Catch:{ TimeoutException -> 0x012f, CancellationException -> 0x0129, IOException | InterruptedException | ExecutionException -> 0x0122 }
            android.content.Context r3 = r5.getContext()     // Catch:{ TimeoutException -> 0x012f, CancellationException -> 0x0129, IOException | InterruptedException | ExecutionException -> 0x0122 }
            android.telecom.PhoneAccountHandle r4 = r5.getPhoneAccountHandle()     // Catch:{ TimeoutException -> 0x012f, CancellationException -> 0x0129, IOException | InterruptedException | ExecutionException -> 0x0122 }
            r2.<init>(r3, r4)     // Catch:{ TimeoutException -> 0x012f, CancellationException -> 0x0129, IOException | InterruptedException | ExecutionException -> 0x0122 }
            com.android.voicemail.impl.protocol.VisualVoicemailProtocol r3 = r0.getProtocol()     // Catch:{ all -> 0x0116 }
            android.app.PendingIntent r4 = r2.getSentIntent()     // Catch:{ all -> 0x0116 }
            r3.requestStatus(r0, r4)     // Catch:{ all -> 0x0116 }
            android.os.Bundle r0 = r2.get()     // Catch:{ all -> 0x0116 }
            r2.close()     // Catch:{ TimeoutException -> 0x012f, CancellationException -> 0x0129, IOException | InterruptedException | ExecutionException -> 0x0122 }
            com.android.voicemail.impl.sms.StatusMessage r2 = new com.android.voicemail.impl.sms.StatusMessage
            r2.<init>(r0)
            java.lang.String r3 = "STATUS SMS received: st="
            java.lang.StringBuilder r3 = com.android.tools.p006r8.GeneratedOutlineSupport.outline13(r3)
            java.lang.String r4 = r2.getProvisioningStatus()
            r3.append(r4)
            java.lang.String r4 = ", rc="
            r3.append(r4)
            java.lang.String r4 = r2.getReturnCode()
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            com.android.voicemail.impl.VvmLog.m45i(r1, r3)
            java.lang.String r3 = r2.getProvisioningStatus()
            java.lang.String r4 = "R"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x00f1
            java.lang.String r0 = "subscriber ready, no activation required"
            com.android.voicemail.impl.VvmLog.m45i(r1, r0)
            android.content.Context r0 = r5.getContext()
            com.android.dialer.logging.DialerImpression$Type r1 = com.android.dialer.logging.DialerImpression$Type.VVM_STATUS_CHECK_READY
            com.android.voicemail.impl.utils.LoggerUtils.logImpressionOnMainThread(r0, r1)
            android.content.Context r0 = r5.getContext()
            android.telecom.PhoneAccountHandle r5 = r5.getPhoneAccountHandle()
            com.android.voicemail.impl.sync.VvmAccountManager.addAccount(r0, r5, r2)
            goto L_0x0115
        L_0x00f1:
            java.lang.String r2 = "subscriber not ready, attempting reactivation"
            com.android.voicemail.impl.VvmLog.m45i(r1, r2)
            android.content.Context r1 = r5.getContext()
            android.telecom.PhoneAccountHandle r2 = r5.getPhoneAccountHandle()
            com.android.voicemail.impl.sync.VvmAccountManager.removeAccount(r1, r2)
            android.content.Context r1 = r5.getContext()
            com.android.dialer.logging.DialerImpression$Type r2 = com.android.dialer.logging.DialerImpression$Type.VVM_STATUS_CHECK_REACTIVATION
            com.android.voicemail.impl.utils.LoggerUtils.logImpressionOnMainThread(r1, r2)
            android.content.Context r1 = r5.getContext()
            android.telecom.PhoneAccountHandle r5 = r5.getPhoneAccountHandle()
            com.android.voicemail.impl.ActivationTask.start(r1, r5, r0)
        L_0x0115:
            return
        L_0x0116:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0118 }
        L_0x0118:
            r0 = move-exception
            r2.close()     // Catch:{ all -> 0x011d }
            goto L_0x0121
        L_0x011d:
            r2 = move-exception
            r5.addSuppressed(r2)     // Catch:{ TimeoutException -> 0x012f, CancellationException -> 0x0129, IOException | InterruptedException | ExecutionException -> 0x0122 }
        L_0x0121:
            throw r0     // Catch:{ TimeoutException -> 0x012f, CancellationException -> 0x0129, IOException | InterruptedException | ExecutionException -> 0x0122 }
        L_0x0122:
            r5 = move-exception
            java.lang.String r0 = "can't get future STATUS SMS"
            com.android.voicemail.impl.VvmLog.m44e(r1, r0, r5)
            return
        L_0x0129:
            java.lang.String r5 = "Unable to send status request SMS"
            com.android.voicemail.impl.VvmLog.m43e(r1, r5)
            return
        L_0x012f:
            java.lang.String r5 = "timeout requesting status"
            com.android.voicemail.impl.VvmLog.m43e(r1, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.voicemail.impl.StatusCheckTask.onExecuteInBackgroundThread():void");
    }
}
