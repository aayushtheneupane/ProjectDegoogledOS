package com.android.voicemail.impl.sms;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;

@TargetApi(26)
public class OmtpMessageReceiver extends BroadcastReceiver {
    private Context context;

    /* JADX WARNING: Removed duplicated region for block: B:44:0x00dd  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00fe  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onReceive(android.content.Context r7, android.content.Intent r8) {
        /*
            r6 = this;
            r6.context = r7
            android.os.Bundle r8 = r8.getExtras()
            java.lang.String r0 = "extra_voicemail_sms"
            android.os.Parcelable r8 = r8.getParcelable(r0)
            android.telephony.VisualVoicemailSms r8 = (android.telephony.VisualVoicemailSms) r8
            android.telecom.PhoneAccountHandle r0 = r8.getPhoneAccountHandle()
            java.lang.String r1 = "OmtpMessageReceiver"
            if (r0 != 0) goto L_0x001c
            java.lang.String r6 = "Received message for null phone account"
            com.android.voicemail.impl.VvmLog.m45i(r1, r6)
            return
        L_0x001c:
            java.lang.Class<android.os.UserManager> r2 = android.os.UserManager.class
            java.lang.Object r2 = r7.getSystemService(r2)
            android.os.UserManager r2 = (android.os.UserManager) r2
            boolean r2 = r2.isUserUnlocked()
            if (r2 != 0) goto L_0x0033
            java.lang.String r6 = "Received message on locked device"
            com.android.voicemail.impl.VvmLog.m45i(r1, r6)
            com.android.voicemail.impl.sms.LegacyModeSmsHandler.handle(r7, r8)
            return
        L_0x0033:
            boolean r2 = com.android.voicemail.impl.sync.VvmAccountManager.isAccountActivated(r7, r0)
            if (r2 != 0) goto L_0x0042
            java.lang.String r6 = "Received message on non-activated account"
            com.android.voicemail.impl.VvmLog.m45i(r1, r6)
            com.android.voicemail.impl.sms.LegacyModeSmsHandler.handle(r7, r8)
            return
        L_0x0042:
            com.android.voicemail.impl.OmtpVvmCarrierConfigHelper r2 = new com.android.voicemail.impl.OmtpVvmCarrierConfigHelper
            android.content.Context r3 = r6.context
            r2.<init>(r3, r0)
            boolean r3 = r2.isValid()
            if (r3 != 0) goto L_0x0055
            java.lang.String r6 = "vvm config no longer valid"
            com.android.voicemail.impl.VvmLog.m43e(r1, r6)
            return
        L_0x0055:
            android.content.Context r3 = r6.context
            boolean r3 = com.android.voicemail.impl.settings.VisualVoicemailSettingsUtil.isEnabled(r3, r0)
            if (r3 != 0) goto L_0x006d
            boolean r6 = r2.isLegacyModeEnabled()
            if (r6 == 0) goto L_0x0067
            com.android.voicemail.impl.sms.LegacyModeSmsHandler.handle(r7, r8)
            goto L_0x006c
        L_0x0067:
            java.lang.String r6 = "Received vvm message for disabled vvm source."
            com.android.voicemail.impl.VvmLog.m45i(r1, r6)
        L_0x006c:
            return
        L_0x006d:
            java.lang.String r3 = r8.getPrefix()
            android.os.Bundle r8 = r8.getFields()
            if (r3 == 0) goto L_0x01c1
            if (r8 != 0) goto L_0x007b
            goto L_0x01c1
        L_0x007b:
            java.lang.String r4 = "SYNC"
            boolean r4 = r3.equals(r4)
            if (r4 == 0) goto L_0x0177
            com.android.voicemail.impl.sms.SyncMessage r7 = new com.android.voicemail.impl.sms.SyncMessage
            r7.<init>(r8)
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r2 = "Received SYNC sms for "
            r8.append(r2)
            r8.append(r0)
            java.lang.String r2 = " with event "
            r8.append(r2)
            java.lang.String r2 = r7.getSyncTriggerEvent()
            r8.append(r2)
            r8.toString()
            java.lang.String r8 = r7.getSyncTriggerEvent()
            int r2 = r8.hashCode()
            r3 = 2286(0x8ee, float:3.203E-42)
            r4 = 2
            r5 = 1
            if (r2 == r3) goto L_0x00d0
            r3 = 2495(0x9bf, float:3.496E-42)
            if (r2 == r3) goto L_0x00c6
            r3 = 76128(0x12960, float:1.06678E-40)
            if (r2 == r3) goto L_0x00bc
            goto L_0x00da
        L_0x00bc:
            java.lang.String r2 = "MBU"
            boolean r8 = r8.equals(r2)
            if (r8 == 0) goto L_0x00da
            r8 = r5
            goto L_0x00db
        L_0x00c6:
            java.lang.String r2 = "NM"
            boolean r8 = r8.equals(r2)
            if (r8 == 0) goto L_0x00da
            r8 = 0
            goto L_0x00db
        L_0x00d0:
            java.lang.String r2 = "GU"
            boolean r8 = r8.equals(r2)
            if (r8 == 0) goto L_0x00da
            r8 = r4
            goto L_0x00db
        L_0x00da:
            r8 = -1
        L_0x00db:
            if (r8 == 0) goto L_0x00fe
            if (r8 == r5) goto L_0x00f7
            if (r8 == r4) goto L_0x01c0
            java.lang.String r6 = "Unrecognized sync trigger event: "
            java.lang.StringBuilder r6 = com.android.tools.p006r8.GeneratedOutlineSupport.outline13(r6)
            java.lang.String r7 = r7.getSyncTriggerEvent()
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            com.android.voicemail.impl.VvmLog.m43e(r1, r6)
            goto L_0x01c0
        L_0x00f7:
            android.content.Context r6 = r6.context
            com.android.voicemail.impl.sync.SyncTask.start(r6, r0)
            goto L_0x01c0
        L_0x00fe:
            java.lang.String r8 = r7.getContentType()
            java.lang.String r2 = "v"
            boolean r8 = r2.equals(r8)
            if (r8 != 0) goto L_0x0125
            java.lang.String r6 = "Non-voice message of type '"
            java.lang.StringBuilder r6 = com.android.tools.p006r8.GeneratedOutlineSupport.outline13(r6)
            java.lang.String r7 = r7.getContentType()
            r6.append(r7)
            java.lang.String r7 = "' received, ignoring"
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            com.android.voicemail.impl.VvmLog.m45i(r1, r6)
            goto L_0x01c0
        L_0x0125:
            long r1 = r7.getTimestampMillis()
            java.lang.String r8 = r7.getSender()
            com.android.voicemail.impl.Voicemail$Builder r8 = com.android.voicemail.impl.Voicemail.createForInsertion(r1, r8)
            r8.setPhoneAccount(r0)
            java.lang.String r1 = r7.getId()
            r8.setSourceData(r1)
            int r7 = r7.getLength()
            long r1 = (long) r7
            r8.setDuration(r1)
            android.content.Context r7 = r6.context
            java.lang.String r7 = r7.getPackageName()
            r8.setSourcePackage(r7)
            com.android.voicemail.impl.Voicemail r7 = r8.build()
            com.android.voicemail.impl.sync.VoicemailsQueryHelper r1 = new com.android.voicemail.impl.sync.VoicemailsQueryHelper
            android.content.Context r2 = r6.context
            r1.<init>(r2)
            boolean r1 = r1.isVoicemailUnique(r7)
            if (r1 == 0) goto L_0x01c0
            android.content.Context r1 = r6.context
            android.net.Uri r7 = com.android.voicemail.impl.utils.LoggerUtils.insert(r1, r7)
            long r1 = android.content.ContentUris.parseId(r7)
            r8.setId(r1)
            r8.setUri(r7)
            com.android.voicemail.impl.Voicemail r7 = r8.build()
            android.content.Context r6 = r6.context
            com.android.voicemail.impl.sync.SyncOneTask.start(r6, r0, r7)
            goto L_0x01c0
        L_0x0177:
            java.lang.String r6 = "STATUS"
            boolean r6 = r3.equals(r6)
            if (r6 == 0) goto L_0x0193
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r1 = "Received Status sms for "
            r6.append(r1)
            r6.append(r0)
            r6.toString()
            com.android.voicemail.impl.ActivationTask.start(r7, r0, r8)
            goto L_0x01c0
        L_0x0193:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r4 = "Unknown prefix: "
            r6.append(r4)
            r6.append(r3)
            java.lang.String r6 = r6.toString()
            com.android.voicemail.impl.VvmLog.m46w(r1, r6)
            com.android.voicemail.impl.protocol.VisualVoicemailProtocol r6 = r2.getProtocol()
            if (r6 != 0) goto L_0x01ae
            return
        L_0x01ae:
            com.android.voicemail.impl.protocol.VisualVoicemailProtocol r6 = r2.getProtocol()
            android.os.Bundle r6 = r6.translateStatusSmsBundle(r2, r3, r8)
            if (r6 == 0) goto L_0x01c0
            java.lang.String r6 = "Protocol recognized the SMS as STATUS, activating"
            com.android.voicemail.impl.VvmLog.m45i(r1, r6)
            com.android.voicemail.impl.ActivationTask.start(r7, r0, r8)
        L_0x01c0:
            return
        L_0x01c1:
            java.lang.String r6 = "Unparsable VVM SMS received, ignoring"
            com.android.voicemail.impl.VvmLog.m43e(r1, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.voicemail.impl.sms.OmtpMessageReceiver.onReceive(android.content.Context, android.content.Intent):void");
    }
}
