package com.android.voicemail.impl.protocol;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.telecom.PhoneAccountHandle;
import android.text.TextUtils;
import com.android.voicemail.PinChanger;
import com.android.voicemail.VoicemailComponent;
import com.android.voicemail.impl.OmtpEvents;
import com.android.voicemail.impl.OmtpVvmCarrierConfigHelper;
import com.android.voicemail.impl.VisualVoicemailPreferences;
import com.android.voicemail.impl.VoicemailStatus$Editor;
import com.android.voicemail.impl.VvmLog;
import com.android.voicemail.impl.imap.ImapHelper;
import com.android.voicemail.impl.mail.MessagingException;
import com.android.voicemail.impl.sms.OmtpMessageSender;
import com.android.voicemail.impl.sms.StatusMessage;
import com.android.voicemail.impl.sms.Vvm3MessageSender;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Locale;

@TargetApi(26)
public class Vvm3Protocol extends VisualVoicemailProtocol {
    private static /* synthetic */ void $closeResource(Throwable th, AutoCloseable autoCloseable) {
        if (th != null) {
            try {
                autoCloseable.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        } else {
            autoCloseable.close();
        }
    }

    private static boolean setPin(Context context, PhoneAccountHandle phoneAccountHandle, ImapHelper imapHelper, StatusMessage statusMessage) throws IOException, MessagingException {
        String imapUserName = statusMessage.getImapUserName();
        String str = null;
        try {
            String substring = imapUserName.substring(0, imapUserName.indexOf(64));
            if (substring.length() < 4) {
                VvmLog.m43e("Vvm3Protocol", "unable to extract number from IMAP username");
            } else {
                str = "1" + substring.substring(substring.length() - 4);
            }
        } catch (StringIndexOutOfBoundsException unused) {
            VvmLog.m43e("Vvm3Protocol", "unable to extract number from IMAP username");
        }
        if (str == null) {
            VvmLog.m45i("Vvm3Protocol", "cannot generate default PIN");
            return false;
        }
        PinChanger createPinChanger = VoicemailComponent.get(context).getVoicemailClient().createPinChanger(context, phoneAccountHandle);
        if (createPinChanger.getScrambledPin() != null) {
            VvmLog.m45i("Vvm3Protocol", "PIN already set");
            return true;
        }
        String[] split = new VisualVoicemailPreferences(context, phoneAccountHandle).getString("pw_len", "").split("-");
        int i = 6;
        if (split.length == 2) {
            try {
                i = Integer.parseInt(split[0]);
            } catch (NumberFormatException unused2) {
            }
        }
        String substring2 = String.format(Locale.US, "%010d", new Object[]{Long.valueOf(Math.abs(new SecureRandom().nextLong()))}).substring(0, i);
        if (imapHelper.changePin(str, substring2) == 0) {
            createPinChanger.setScrambledPin(substring2);
            imapHelper.handleEvent(OmtpEvents.CONFIG_DEFAULT_PIN_REPLACED);
        }
        VvmLog.m45i("Vvm3Protocol", "new user: PIN set");
        return true;
    }

    public OmtpMessageSender createMessageSender(Context context, PhoneAccountHandle phoneAccountHandle, short s, String str) {
        return new Vvm3MessageSender(context, phoneAccountHandle, s, str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0042 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getCommand(java.lang.String r4) {
        /*
            r3 = this;
            int r3 = r4.hashCode()
            r0 = -2102887634(0xffffffff82a87b2e, float:-2.4756083E-37)
            r1 = 2
            r2 = 1
            if (r3 == r0) goto L_0x002a
            r0 = -386920792(0xffffffffe8f00ea8, float:-9.0691065E24)
            if (r3 == r0) goto L_0x0020
            r0 = -203105431(0xfffffffff3e4db69, float:-3.626385E31)
            if (r3 == r0) goto L_0x0016
            goto L_0x0034
        L_0x0016:
            java.lang.String r3 = "XCHANGE_VM_LANG LANG=%1$s"
            boolean r3 = r4.equals(r3)
            if (r3 == 0) goto L_0x0034
            r3 = r1
            goto L_0x0035
        L_0x0020:
            java.lang.String r3 = "XCHANGE_TUI_PWD PWD=%1$s OLD_PWD=%2$s"
            boolean r3 = r4.equals(r3)
            if (r3 == 0) goto L_0x0034
            r3 = 0
            goto L_0x0035
        L_0x002a:
            java.lang.String r3 = "XCLOSE_NUT"
            boolean r3 = r4.equals(r3)
            if (r3 == 0) goto L_0x0034
            r3 = r2
            goto L_0x0035
        L_0x0034:
            r3 = -1
        L_0x0035:
            if (r3 == 0) goto L_0x0042
            if (r3 == r2) goto L_0x003f
            if (r3 == r1) goto L_0x003c
            return r4
        L_0x003c:
            java.lang.String r3 = "CHANGE_VM_LANG Lang=%1$s"
            return r3
        L_0x003f:
            java.lang.String r3 = "CLOSE_NUT"
            return r3
        L_0x0042:
            java.lang.String r3 = "CHANGE_TUI_PWD PWD=%1$s OLD_PWD=%2$s"
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.voicemail.impl.protocol.Vvm3Protocol.getCommand(java.lang.String):java.lang.String");
    }

    public void handleEvent(Context context, OmtpVvmCarrierConfigHelper omtpVvmCarrierConfigHelper, VoicemailStatus$Editor voicemailStatus$Editor, OmtpEvents omtpEvents) {
        Vvm3EventHandler.handleEvent(context, omtpVvmCarrierConfigHelper, voicemailStatus$Editor, omtpEvents);
    }

    public void startActivation(OmtpVvmCarrierConfigHelper omtpVvmCarrierConfigHelper, PendingIntent pendingIntent) {
        VvmLog.m45i("Vvm3Protocol", "Activating");
        omtpVvmCarrierConfigHelper.requestStatus(pendingIntent);
    }

    public void startDeactivation(OmtpVvmCarrierConfigHelper omtpVvmCarrierConfigHelper) {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00d9, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        $closeResource(r8, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00dd, code lost:
        throw r11;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void startProvisioning(com.android.voicemail.impl.ActivationTask r7, android.telecom.PhoneAccountHandle r8, com.android.voicemail.impl.OmtpVvmCarrierConfigHelper r9, com.android.voicemail.impl.VoicemailStatus$Editor r10, com.android.voicemail.impl.sms.StatusMessage r11, android.os.Bundle r12, boolean r13) {
        /*
            r6 = this;
            java.lang.String r6 = "Vvm3Protocol"
            java.lang.String r0 = "start vvm3 provisioning"
            com.android.voicemail.impl.VvmLog.m45i(r6, r0)
            if (r13 == 0) goto L_0x000f
            java.lang.String r7 = "carrier initiated, ignoring"
            com.android.voicemail.impl.VvmLog.m46w(r6, r7)
            return
        L_0x000f:
            android.content.Context r13 = r9.getContext()
            com.android.dialer.logging.DialerImpression$Type r0 = com.android.dialer.logging.DialerImpression$Type.VVM_PROVISIONING_STARTED
            com.android.voicemail.impl.utils.LoggerUtils.logImpressionOnMainThread(r13, r0)
            java.lang.String r13 = r11.getProvisioningStatus()
            java.lang.String r0 = "U"
            boolean r13 = r0.equals(r13)
            if (r13 == 0) goto L_0x0051
            java.lang.String r13 = "Provisioning status: Unknown"
            com.android.voicemail.impl.VvmLog.m45i(r6, r13)
            java.lang.String r11 = r11.getReturnCode()
            java.lang.String r13 = "2"
            boolean r11 = r13.equals(r11)
            if (r11 == 0) goto L_0x004a
            java.lang.String r11 = "Self provisioning available, subscribing"
            com.android.voicemail.impl.VvmLog.m45i(r6, r11)
            com.android.voicemail.impl.protocol.Vvm3Subscriber r6 = new com.android.voicemail.impl.protocol.Vvm3Subscriber
            r0 = r6
            r1 = r7
            r2 = r8
            r3 = r9
            r4 = r10
            r5 = r12
            r0.<init>(r1, r2, r3, r4, r5)
            r6.subscribe()
            goto L_0x0134
        L_0x004a:
            com.android.voicemail.impl.OmtpEvents r6 = com.android.voicemail.impl.OmtpEvents.VVM3_SUBSCRIBER_UNKNOWN
            r9.handleEvent(r10, r6)
            goto L_0x0134
        L_0x0051:
            java.lang.String r12 = r11.getProvisioningStatus()
            java.lang.String r13 = "N"
            boolean r12 = r13.equals(r12)
            if (r12 == 0) goto L_0x0104
            java.lang.String r12 = "setting up new user"
            com.android.voicemail.impl.VvmLog.m45i(r6, r12)
            com.android.voicemail.impl.VisualVoicemailPreferences r12 = new com.android.voicemail.impl.VisualVoicemailPreferences
            android.content.Context r13 = r9.getContext()
            r12.<init>(r13, r8)
            com.android.dialer.common.PerAccountSharedPreferences$Editor r12 = r12.edit()
            r11.putStatus(r12)
            r12.apply()
            com.android.voicemail.impl.sync.VvmNetworkRequest$NetworkWrapper r12 = com.android.voicemail.impl.sync.VvmNetworkRequest.getNetwork(r9, r8, r10)     // Catch:{ RequestFailedException -> 0x00fb }
            android.net.Network r13 = r12.get()     // Catch:{ all -> 0x00f2 }
            java.lang.String r0 = "new user: network available"
            com.android.voicemail.impl.VvmLog.m45i(r6, r0)     // Catch:{ all -> 0x00f2 }
            r0 = 0
            com.android.voicemail.impl.imap.ImapHelper r1 = new com.android.voicemail.impl.imap.ImapHelper     // Catch:{ InitializingException | MessagingException | IOException -> 0x00de }
            android.content.Context r2 = r9.getContext()     // Catch:{ InitializingException | MessagingException | IOException -> 0x00de }
            r1.<init>(r2, r8, r13, r10)     // Catch:{ InitializingException | MessagingException | IOException -> 0x00de }
            java.util.Locale r13 = java.util.Locale.getDefault()     // Catch:{ all -> 0x00d7 }
            java.lang.String r13 = r13.getLanguage()     // Catch:{ all -> 0x00d7 }
            java.util.Locale r2 = new java.util.Locale     // Catch:{ all -> 0x00d7 }
            java.lang.String r3 = "es"
            r2.<init>(r3)     // Catch:{ all -> 0x00d7 }
            java.lang.String r2 = r2.getLanguage()     // Catch:{ all -> 0x00d7 }
            boolean r13 = r13.equals(r2)     // Catch:{ all -> 0x00d7 }
            if (r13 == 0) goto L_0x00ab
            java.lang.String r13 = "6"
            r1.changeVoicemailTuiLanguage(r13)     // Catch:{ all -> 0x00d7 }
            goto L_0x00b0
        L_0x00ab:
            java.lang.String r13 = "5"
            r1.changeVoicemailTuiLanguage(r13)     // Catch:{ all -> 0x00d7 }
        L_0x00b0:
            java.lang.String r13 = "new user: language set"
            com.android.voicemail.impl.VvmLog.m45i(r6, r13)     // Catch:{ all -> 0x00d7 }
            android.content.Context r13 = r9.getContext()     // Catch:{ all -> 0x00d7 }
            boolean r8 = setPin(r13, r8, r1, r11)     // Catch:{ all -> 0x00d7 }
            if (r8 == 0) goto L_0x00d3
            r1.closeNewUserTutorial()     // Catch:{ all -> 0x00d7 }
            java.lang.String r8 = "new user: NUT closed"
            com.android.voicemail.impl.VvmLog.m45i(r6, r8)     // Catch:{ all -> 0x00d7 }
            android.content.Context r8 = r9.getContext()     // Catch:{ all -> 0x00d7 }
            com.android.dialer.logging.DialerImpression$Type r11 = com.android.dialer.logging.DialerImpression$Type.VVM_PROVISIONING_COMPLETED     // Catch:{ all -> 0x00d7 }
            com.android.voicemail.impl.utils.LoggerUtils.logImpressionOnMainThread(r8, r11)     // Catch:{ all -> 0x00d7 }
            r9.requestStatus(r0)     // Catch:{ all -> 0x00d7 }
        L_0x00d3:
            $closeResource(r0, r1)     // Catch:{ InitializingException | MessagingException | IOException -> 0x00de }
            goto L_0x00ee
        L_0x00d7:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x00d9 }
        L_0x00d9:
            r11 = move-exception
            $closeResource(r8, r1)     // Catch:{ InitializingException | MessagingException | IOException -> 0x00de }
            throw r11     // Catch:{ InitializingException | MessagingException | IOException -> 0x00de }
        L_0x00de:
            r8 = move-exception
            com.android.voicemail.impl.OmtpEvents r11 = com.android.voicemail.impl.OmtpEvents.VVM3_NEW_USER_SETUP_FAILED     // Catch:{ all -> 0x00f2 }
            r9.handleEvent(r10, r11)     // Catch:{ all -> 0x00f2 }
            r7.fail()     // Catch:{ all -> 0x00f2 }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x00f2 }
            com.android.voicemail.impl.VvmLog.m43e(r6, r8)     // Catch:{ all -> 0x00f2 }
        L_0x00ee:
            $closeResource(r0, r12)     // Catch:{ RequestFailedException -> 0x00fb }
            goto L_0x0134
        L_0x00f2:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x00f4 }
        L_0x00f4:
            r8 = move-exception
            if (r12 == 0) goto L_0x00fa
            $closeResource(r6, r12)     // Catch:{ RequestFailedException -> 0x00fb }
        L_0x00fa:
            throw r8     // Catch:{ RequestFailedException -> 0x00fb }
        L_0x00fb:
            com.android.voicemail.impl.OmtpEvents r6 = com.android.voicemail.impl.OmtpEvents.DATA_NO_CONNECTION_CELLULAR_REQUIRED
            r9.handleEvent(r10, r6)
            r7.fail()
            goto L_0x0134
        L_0x0104:
            java.lang.String r7 = r11.getProvisioningStatus()
            java.lang.String r12 = "P"
            boolean r7 = r12.equals(r7)
            if (r7 == 0) goto L_0x011e
            java.lang.String r7 = "User provisioned but not activated, disabling VVM"
            com.android.voicemail.impl.VvmLog.m45i(r6, r7)
            android.content.Context r6 = r9.getContext()
            r7 = 0
            com.android.voicemail.impl.settings.VisualVoicemailSettingsUtil.setEnabled(r6, r8, r7)
            goto L_0x0134
        L_0x011e:
            java.lang.String r7 = r11.getProvisioningStatus()
            java.lang.String r8 = "B"
            boolean r7 = r8.equals(r7)
            if (r7 == 0) goto L_0x0134
            java.lang.String r7 = "User blocked"
            com.android.voicemail.impl.VvmLog.m45i(r6, r7)
            com.android.voicemail.impl.OmtpEvents r6 = com.android.voicemail.impl.OmtpEvents.VVM3_SUBSCRIBER_BLOCKED
            r9.handleEvent(r10, r6)
        L_0x0134:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.voicemail.impl.protocol.Vvm3Protocol.startProvisioning(com.android.voicemail.impl.ActivationTask, android.telecom.PhoneAccountHandle, com.android.voicemail.impl.OmtpVvmCarrierConfigHelper, com.android.voicemail.impl.VoicemailStatus$Editor, com.android.voicemail.impl.sms.StatusMessage, android.os.Bundle, boolean):void");
    }

    public boolean supportsProvisioning() {
        return true;
    }

    public Bundle translateStatusSmsBundle(OmtpVvmCarrierConfigHelper omtpVvmCarrierConfigHelper, String str, Bundle bundle) {
        if (!"UNRECOGNIZED".equals(str) || !"STATUS".equals(bundle.getString("cmd"))) {
            return null;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putString("st", "U");
        bundle2.putString("rc", "2");
        String string = omtpVvmCarrierConfigHelper.getString("default_vmg_url");
        if (TextUtils.isEmpty(string)) {
            VvmLog.m43e("Vvm3Protocol", "Unable to translate STATUS SMS: VMG URL is not set in config");
            return null;
        }
        bundle2.putString("vmg_url", string);
        VvmLog.m45i("Vvm3Protocol", "UNRECOGNIZED?cmd=STATUS translated into unprovisioned STATUS SMS");
        return bundle2;
    }
}
