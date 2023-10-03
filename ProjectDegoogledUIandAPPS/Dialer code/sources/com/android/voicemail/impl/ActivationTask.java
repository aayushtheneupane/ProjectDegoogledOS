package com.android.voicemail.impl;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.telecom.PhoneAccountHandle;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.voicemail.impl.scheduling.BaseTask;
import com.android.voicemail.impl.scheduling.RetryPolicy;
import com.android.voicemail.impl.sms.StatusMessage;
import com.android.voicemail.impl.sync.SyncTask;
import com.android.voicemail.impl.sync.VvmAccountManager;
import com.android.voicemail.impl.utils.LoggerUtils;

@TargetApi(26)
public class ActivationTask extends BaseTask {
    static final String EXTRA_MESSAGE_DATA_BUNDLE = "extra_message_data_bundle";
    private OmtpVvmCarrierConfigHelper configForTest;
    private Bundle messageData;
    private final RetryPolicy retryPolicy = new RetryPolicy(4, 5000);

    public ActivationTask() {
        super(3);
        addPolicy(this.retryPolicy);
    }

    private static void onSuccess(Context context, PhoneAccountHandle phoneAccountHandle, OmtpVvmCarrierConfigHelper omtpVvmCarrierConfigHelper) {
        omtpVvmCarrierConfigHelper.handleEvent(Assert.edit(context, phoneAccountHandle), OmtpEvents.CONFIG_REQUEST_STATUS_SUCCESS);
        Intent intent = new Intent("com.android.voicemail.VoicemailClient.ACTION_SHOW_LEGACY_VOICEMAIL");
        intent.setPackage(context.getPackageName());
        intent.putExtra("android.telephony.extra.PHONE_ACCOUNT_HANDLE", phoneAccountHandle);
        intent.putExtra("android.telephony.extra.NOTIFICATION_COUNT", 0);
        context.sendBroadcast(intent);
        SyncTask.start(context, phoneAccountHandle);
    }

    public static void start(Context context, PhoneAccountHandle phoneAccountHandle, Bundle bundle) {
        boolean z = false;
        if (Settings.Global.getInt(context.getContentResolver(), "device_provisioned", 0) == 1) {
            z = true;
        }
        if (!z) {
            VvmLog.m45i("VvmActivationTask", "Activation requested while device is not provisioned, postponing");
            DeviceProvisionedJobService.activateAfterProvisioned(context, phoneAccountHandle);
            return;
        }
        Intent createIntent = BaseTask.createIntent(context, ActivationTask.class, phoneAccountHandle);
        if (bundle != null) {
            createIntent.putExtra(EXTRA_MESSAGE_DATA_BUNDLE, bundle);
        }
        context.sendBroadcast(createIntent);
    }

    private static void updateSource(Context context, PhoneAccountHandle phoneAccountHandle, StatusMessage statusMessage, OmtpVvmCarrierConfigHelper omtpVvmCarrierConfigHelper) {
        if ("0".equals(statusMessage.getReturnCode())) {
            VvmAccountManager.addAccount(context, phoneAccountHandle, statusMessage);
            onSuccess(context, phoneAccountHandle, omtpVvmCarrierConfigHelper);
            return;
        }
        VvmLog.m43e("VvmActivationTask", "Visual voicemail not available for subscriber.");
    }

    public Intent createRestartIntent() {
        LoggerUtils.logImpressionOnMainThread(getContext(), DialerImpression$Type.VVM_AUTO_RETRY_ACTIVATION);
        return super.createRestartIntent();
    }

    public void onCreate(Context context, Bundle bundle) {
        super.onCreate(context, bundle);
        this.messageData = (Bundle) bundle.getParcelable(EXTRA_MESSAGE_DATA_BUNDLE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0276, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x027f, code lost:
        throw r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onExecuteInBackgroundThread() {
        /*
            r12 = this;
            com.android.voicemail.impl.Assert.isNotMainThread()
            android.content.Context r0 = r12.getContext()
            com.android.dialer.logging.DialerImpression$Type r1 = com.android.dialer.logging.DialerImpression$Type.VVM_ACTIVATION_STARTED
            com.android.voicemail.impl.utils.LoggerUtils.logImpressionOnMainThread(r0, r1)
            android.telecom.PhoneAccountHandle r4 = r12.getPhoneAccountHandle()
            java.lang.String r0 = "VvmActivationTask"
            if (r4 != 0) goto L_0x001a
            java.lang.String r12 = "null PhoneAccountHandle"
            com.android.voicemail.impl.VvmLog.m43e(r0, r12)
            return
        L_0x001a:
            android.content.Context r1 = r12.getContext()
            com.android.voicemail.impl.Assert.isNotMainThread()
            com.android.voicemail.impl.VisualVoicemailPreferences r2 = new com.android.voicemail.impl.VisualVoicemailPreferences
            r2.<init>(r1, r4)
            java.lang.String r3 = "pre_o_migration_finished"
            r5 = 0
            boolean r6 = r2.getBoolean(r3, r5)
            java.lang.String r7 = "PreOMigrationHandler"
            r8 = 1
            if (r6 == 0) goto L_0x0048
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r4)
            java.lang.String r2 = " already migrated"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.android.voicemail.impl.VvmLog.m45i(r7, r1)
            goto L_0x00e4
        L_0x0048:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r9 = "migrating "
            r6.append(r9)
            r6.append(r4)
            java.lang.String r6 = r6.toString()
            com.android.voicemail.impl.VvmLog.m45i(r7, r6)
            java.lang.String r6 = "PreOMigrationHandler.migrateSettings"
            java.lang.String r7 = "migrating settings"
            com.android.voicemail.impl.VvmLog.m45i(r6, r7)
            java.lang.Class<android.telephony.TelephonyManager> r7 = android.telephony.TelephonyManager.class
            java.lang.Object r7 = r1.getSystemService(r7)
            android.telephony.TelephonyManager r7 = (android.telephony.TelephonyManager) r7
            android.telephony.TelephonyManager r7 = r7.createForPhoneAccountHandle(r4)
            if (r7 != 0) goto L_0x0077
            java.lang.String r1 = "invalid PhoneAccountHandle"
            com.android.voicemail.impl.VvmLog.m43e(r6, r1)
            goto L_0x00da
        L_0x0077:
            java.lang.Class<android.telephony.TelephonyManager> r9 = android.telephony.TelephonyManager.class
            java.lang.String r10 = "getVisualVoicemailSettings"
            java.lang.Class[] r11 = new java.lang.Class[r5]     // Catch:{ ClassCastException | ReflectiveOperationException -> 0x00d5 }
            java.lang.reflect.Method r9 = r9.getMethod(r10, r11)     // Catch:{ ClassCastException | ReflectiveOperationException -> 0x00d5 }
            java.lang.Object[] r10 = new java.lang.Object[r5]     // Catch:{ ClassCastException | ReflectiveOperationException -> 0x00d5 }
            java.lang.Object r7 = r9.invoke(r7, r10)     // Catch:{ ClassCastException | ReflectiveOperationException -> 0x00d5 }
            android.os.Bundle r7 = (android.os.Bundle) r7     // Catch:{ ClassCastException | ReflectiveOperationException -> 0x00d5 }
            java.lang.String r9 = "android.telephony.extra.VISUAL_VOICEMAIL_ENABLED_BY_USER_BOOL"
            boolean r10 = r7.containsKey(r9)
            if (r10 == 0) goto L_0x00ac
            boolean r9 = r7.getBoolean(r9)
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "setting VVM enabled to "
            r10.append(r11)
            r10.append(r9)
            java.lang.String r10 = r10.toString()
            com.android.voicemail.impl.VvmLog.m45i(r6, r10)
            com.android.voicemail.impl.settings.VisualVoicemailSettingsUtil.setEnabled(r1, r4, r9)
        L_0x00ac:
            java.lang.String r9 = "android.telephony.extra.VOICEMAIL_SCRAMBLED_PIN_STRING"
            boolean r10 = r7.containsKey(r9)
            if (r10 == 0) goto L_0x00da
            java.lang.String r7 = r7.getString(r9)
            boolean r9 = android.text.TextUtils.isEmpty(r7)
            if (r9 != 0) goto L_0x00da
            java.lang.String r9 = "migrating scrambled PIN"
            com.android.voicemail.impl.VvmLog.m45i(r6, r9)
            com.android.voicemail.VoicemailComponent r6 = com.android.voicemail.VoicemailComponent.get(r1)
            com.android.voicemail.VoicemailClient r6 = r6.getVoicemailClient()
            com.android.voicemail.PinChanger r1 = r6.createPinChanger(r1, r4)
            com.android.voicemail.impl.PinChangerImpl r1 = (com.android.voicemail.impl.PinChangerImpl) r1
            r1.setScrambledPin(r7)
            goto L_0x00da
        L_0x00d5:
            java.lang.String r1 = "unable to retrieve settings from system"
            com.android.voicemail.impl.VvmLog.m45i(r6, r1)
        L_0x00da:
            com.android.dialer.common.PerAccountSharedPreferences$Editor r1 = r2.edit()
            r1.putBoolean(r3, r8)
            r1.apply()
        L_0x00e4:
            com.android.voicemail.impl.OmtpVvmCarrierConfigHelper r1 = r12.configForTest
            if (r1 == 0) goto L_0x00e9
            goto L_0x00f2
        L_0x00e9:
            com.android.voicemail.impl.OmtpVvmCarrierConfigHelper r1 = new com.android.voicemail.impl.OmtpVvmCarrierConfigHelper
            android.content.Context r2 = r12.getContext()
            r1.<init>(r2, r4)
        L_0x00f2:
            r2 = r1
            boolean r1 = r2.isValid()
            if (r1 != 0) goto L_0x0115
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "VVM not supported on phoneAccountHandle "
            r1.append(r2)
            r1.append(r4)
            java.lang.String r1 = r1.toString()
            com.android.voicemail.impl.VvmLog.m45i(r0, r1)
            android.content.Context r12 = r12.getContext()
            com.android.voicemail.impl.sync.VvmAccountManager.removeAccount(r12, r4)
            return
        L_0x0115:
            android.content.Context r1 = r12.getContext()
            boolean r1 = com.android.voicemail.impl.settings.VisualVoicemailSettingsUtil.isEnabled(r1, r4)
            if (r1 != 0) goto L_0x0133
            boolean r12 = r2.isLegacyModeEnabled()
            if (r12 == 0) goto L_0x012d
            java.lang.String r12 = "Setting up filter for legacy mode"
            com.android.voicemail.impl.VvmLog.m45i(r0, r12)
            r2.activateSmsFilter()
        L_0x012d:
            java.lang.String r12 = "VVM is disabled"
            com.android.voicemail.impl.VvmLog.m45i(r0, r12)
            return
        L_0x0133:
            android.content.Context r1 = r12.getContext()
            com.android.voicemail.impl.VoicemailStatus$Editor r1 = com.android.voicemail.impl.Assert.edit(r1, r4)
            java.lang.String r3 = r2.getVvmType()
            r1.setType(r3)
            boolean r1 = r1.apply()
            if (r1 != 0) goto L_0x015f
            java.lang.String r1 = "Failed to configure content provider - "
            java.lang.StringBuilder r1 = com.android.tools.p006r8.GeneratedOutlineSupport.outline13(r1)
            java.lang.String r3 = r2.getVvmType()
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            com.android.voicemail.impl.VvmLog.m43e(r0, r1)
            r12.fail()
        L_0x015f:
            java.lang.String r1 = "VVM content provider configured - "
            java.lang.StringBuilder r1 = com.android.tools.p006r8.GeneratedOutlineSupport.outline13(r1)
            java.lang.String r3 = r2.getVvmType()
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            com.android.voicemail.impl.VvmLog.m45i(r0, r1)
            android.os.Bundle r1 = r12.messageData
            if (r1 != 0) goto L_0x0191
            android.content.Context r1 = r12.getContext()
            boolean r1 = com.android.voicemail.impl.sync.VvmAccountManager.isAccountActivated(r1, r4)
            if (r1 == 0) goto L_0x0191
            java.lang.String r1 = "Account is already activated"
            com.android.voicemail.impl.VvmLog.m45i(r0, r1)
            r2.activateSmsFilter()
            android.content.Context r12 = r12.getContext()
            onSuccess(r12, r4, r2)
            return
        L_0x0191:
            android.content.Context r1 = r12.getContext()
            com.android.voicemail.impl.VoicemailStatus$Editor r1 = com.android.voicemail.impl.Assert.edit(r1, r4)
            com.android.voicemail.impl.OmtpEvents r3 = com.android.voicemail.impl.OmtpEvents.CONFIG_ACTIVATING
            r2.handleEvent(r1, r3)
            android.content.Context r1 = r12.getContext()
            java.lang.Class<android.telephony.TelephonyManager> r3 = android.telephony.TelephonyManager.class
            java.lang.Object r1 = r1.getSystemService(r3)
            android.telephony.TelephonyManager r1 = (android.telephony.TelephonyManager) r1
            android.telephony.TelephonyManager r1 = r1.createForPhoneAccountHandle(r4)
            android.telephony.ServiceState r1 = r1.getServiceState()
            int r1 = r1.getState()
            if (r1 != 0) goto L_0x01ba
            r1 = r8
            goto L_0x01bb
        L_0x01ba:
            r1 = r5
        L_0x01bb:
            if (r1 != 0) goto L_0x01d0
            java.lang.String r1 = "Service lost during activation, aborting"
            com.android.voicemail.impl.VvmLog.m45i(r0, r1)
            android.content.Context r12 = r12.getContext()
            com.android.voicemail.impl.VoicemailStatus$Editor r12 = com.android.voicemail.impl.Assert.edit(r12, r4)
            com.android.voicemail.impl.OmtpEvents r0 = com.android.voicemail.impl.OmtpEvents.NOTIFICATION_SERVICE_LOST
            r2.handleEvent(r12, r0)
            return
        L_0x01d0:
            r2.activateSmsFilter()
            com.android.voicemail.impl.scheduling.RetryPolicy r1 = r12.retryPolicy
            com.android.voicemail.impl.VoicemailStatus$Editor r1 = r1.getVoicemailStatusEditor()
            com.android.voicemail.impl.protocol.VisualVoicemailProtocol r3 = r2.getProtocol()
            android.os.Bundle r6 = r12.messageData
            if (r6 == 0) goto L_0x01e2
            goto L_0x01e3
        L_0x01e2:
            r8 = r5
        L_0x01e3:
            if (r8 == 0) goto L_0x01e9
            android.os.Bundle r3 = r12.messageData
        L_0x01e7:
            r7 = r3
            goto L_0x0201
        L_0x01e9:
            com.android.voicemail.impl.sms.StatusSmsFetcher r5 = new com.android.voicemail.impl.sms.StatusSmsFetcher     // Catch:{ TimeoutException -> 0x0293, CancellationException -> 0x028a, IOException | InterruptedException | ExecutionException -> 0x0280 }
            android.content.Context r6 = r12.getContext()     // Catch:{ TimeoutException -> 0x0293, CancellationException -> 0x028a, IOException | InterruptedException | ExecutionException -> 0x0280 }
            r5.<init>(r6, r4)     // Catch:{ TimeoutException -> 0x0293, CancellationException -> 0x028a, IOException | InterruptedException | ExecutionException -> 0x0280 }
            android.app.PendingIntent r6 = r5.getSentIntent()     // Catch:{ all -> 0x0274 }
            r3.startActivation(r2, r6)     // Catch:{ all -> 0x0274 }
            android.os.Bundle r3 = r5.get()     // Catch:{ all -> 0x0274 }
            r5.close()     // Catch:{ TimeoutException -> 0x0293, CancellationException -> 0x028a, IOException | InterruptedException | ExecutionException -> 0x0280 }
            goto L_0x01e7
        L_0x0201:
            com.android.voicemail.impl.sms.StatusMessage r6 = new com.android.voicemail.impl.sms.StatusMessage
            r6.<init>(r7)
            java.lang.String r3 = "STATUS SMS received: st="
            java.lang.StringBuilder r3 = com.android.tools.p006r8.GeneratedOutlineSupport.outline13(r3)
            java.lang.String r5 = r6.getProvisioningStatus()
            r3.append(r5)
            java.lang.String r5 = ", rc="
            r3.append(r5)
            java.lang.String r5 = r6.getReturnCode()
            r3.append(r5)
            r3.toString()
            java.lang.String r3 = r6.getProvisioningStatus()
            java.lang.String r5 = "R"
            boolean r3 = r3.equals(r5)
            if (r3 == 0) goto L_0x0236
            android.content.Context r0 = r12.getContext()
            updateSource(r0, r4, r6, r2)
            goto L_0x026a
        L_0x0236:
            boolean r3 = r2.supportsProvisioning()
            if (r3 == 0) goto L_0x0247
            java.lang.String r3 = "Subscriber not ready, start provisioning"
            com.android.voicemail.impl.VvmLog.m45i(r0, r3)
            r3 = r12
            r5 = r1
            r2.startProvisioning(r3, r4, r5, r6, r7, r8)
            goto L_0x026a
        L_0x0247:
            java.lang.String r3 = r6.getProvisioningStatus()
            java.lang.String r5 = "N"
            boolean r3 = r3.equals(r5)
            if (r3 == 0) goto L_0x0260
            java.lang.String r1 = "Subscriber new but provisioning is not supported"
            com.android.voicemail.impl.VvmLog.m45i(r0, r1)
            android.content.Context r0 = r12.getContext()
            updateSource(r0, r4, r6, r2)
            goto L_0x026a
        L_0x0260:
            java.lang.String r3 = "Subscriber not ready but provisioning is not supported"
            com.android.voicemail.impl.VvmLog.m45i(r0, r3)
            com.android.voicemail.impl.OmtpEvents r0 = com.android.voicemail.impl.OmtpEvents.CONFIG_SERVICE_NOT_AVAILABLE
            r2.handleEvent(r1, r0)
        L_0x026a:
            android.content.Context r12 = r12.getContext()
            com.android.dialer.logging.DialerImpression$Type r0 = com.android.dialer.logging.DialerImpression$Type.VVM_ACTIVATION_COMPLETED
            com.android.voicemail.impl.utils.LoggerUtils.logImpressionOnMainThread(r12, r0)
            return
        L_0x0274:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0276 }
        L_0x0276:
            r4 = move-exception
            r5.close()     // Catch:{ all -> 0x027b }
            goto L_0x027f
        L_0x027b:
            r5 = move-exception
            r3.addSuppressed(r5)     // Catch:{ TimeoutException -> 0x0293, CancellationException -> 0x028a, IOException | InterruptedException | ExecutionException -> 0x0280 }
        L_0x027f:
            throw r4     // Catch:{ TimeoutException -> 0x0293, CancellationException -> 0x028a, IOException | InterruptedException | ExecutionException -> 0x0280 }
        L_0x0280:
            r1 = move-exception
            java.lang.String r2 = "can't get future STATUS SMS"
            com.android.voicemail.impl.VvmLog.m44e(r0, r2, r1)
            r12.fail()
            return
        L_0x028a:
            java.lang.String r1 = "Unable to send status request SMS"
            com.android.voicemail.impl.VvmLog.m43e(r0, r1)
            r12.fail()
            return
        L_0x0293:
            com.android.voicemail.impl.OmtpEvents r0 = com.android.voicemail.impl.OmtpEvents.CONFIG_STATUS_SMS_TIME_OUT
            r2.handleEvent(r1, r0)
            r12.fail()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.voicemail.impl.ActivationTask.onExecuteInBackgroundThread():void");
    }

    /* access modifiers changed from: package-private */
    public void setConfigForTest(OmtpVvmCarrierConfigHelper omtpVvmCarrierConfigHelper) {
        this.configForTest = omtpVvmCarrierConfigHelper;
    }
}
