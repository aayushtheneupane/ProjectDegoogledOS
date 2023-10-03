package com.android.voicemail.impl;

import android.annotation.TargetApi;
import android.content.Context;
import android.telecom.PhoneAccountHandle;
import com.android.dialer.common.PerAccountSharedPreferences;
import com.android.voicemail.PinChanger;

@TargetApi(26)
class PinChangerImpl implements PinChanger {
    private final Context context;
    private final PhoneAccountHandle phoneAccountHandle;

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

    PinChangerImpl(Context context2, PhoneAccountHandle phoneAccountHandle2) {
        this.context = context2;
        this.phoneAccountHandle = phoneAccountHandle2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0036, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        $closeResource(r7, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x003a, code lost:
        throw r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0058, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0059, code lost:
        if (r0 != null) goto L_0x005b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        $closeResource(r7, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x005e, code lost:
        throw r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int changePin(java.lang.String r8, java.lang.String r9) {
        /*
            r7 = this;
            com.android.dialer.common.Assert.isWorkerThread()
            com.android.voicemail.impl.OmtpVvmCarrierConfigHelper r0 = new com.android.voicemail.impl.OmtpVvmCarrierConfigHelper
            android.content.Context r1 = r7.context
            android.telecom.PhoneAccountHandle r2 = r7.phoneAccountHandle
            r0.<init>(r1, r2)
            android.content.Context r1 = r7.context
            android.telecom.PhoneAccountHandle r2 = r7.phoneAccountHandle
            com.android.voicemail.impl.VoicemailStatus$Editor r1 = com.android.voicemail.impl.Assert.edit(r1, r2)
            r2 = 6
            android.telecom.PhoneAccountHandle r3 = r7.phoneAccountHandle     // Catch:{ RequestFailedException -> 0x005f }
            com.android.voicemail.impl.sync.VvmNetworkRequest$NetworkWrapper r0 = com.android.voicemail.impl.sync.VvmNetworkRequest.getNetwork(r0, r3, r1)     // Catch:{ RequestFailedException -> 0x005f }
            android.net.Network r3 = r0.get()     // Catch:{ all -> 0x0056 }
            r4 = 0
            com.android.voicemail.impl.imap.ImapHelper r5 = new com.android.voicemail.impl.imap.ImapHelper     // Catch:{ InitializingException | MessagingException -> 0x003b }
            android.content.Context r6 = r7.context     // Catch:{ InitializingException | MessagingException -> 0x003b }
            android.telecom.PhoneAccountHandle r7 = r7.phoneAccountHandle     // Catch:{ InitializingException | MessagingException -> 0x003b }
            r5.<init>(r6, r7, r3, r1)     // Catch:{ InitializingException | MessagingException -> 0x003b }
            int r7 = r5.changePin(r8, r9)     // Catch:{ all -> 0x0034 }
            $closeResource(r4, r5)     // Catch:{ InitializingException | MessagingException -> 0x003b }
            $closeResource(r4, r0)     // Catch:{ RequestFailedException -> 0x005f }
            return r7
        L_0x0034:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x0036 }
        L_0x0036:
            r8 = move-exception
            $closeResource(r7, r5)     // Catch:{ InitializingException | MessagingException -> 0x003b }
            throw r8     // Catch:{ InitializingException | MessagingException -> 0x003b }
        L_0x003b:
            r7 = move-exception
            java.lang.String r8 = "VoicemailClientImpl.changePin"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x0056 }
            r9.<init>()     // Catch:{ all -> 0x0056 }
            java.lang.String r1 = "ChangePinNetworkRequestCallback: onAvailable: "
            r9.append(r1)     // Catch:{ all -> 0x0056 }
            r9.append(r7)     // Catch:{ all -> 0x0056 }
            java.lang.String r7 = r9.toString()     // Catch:{ all -> 0x0056 }
            com.android.voicemail.impl.VvmLog.m43e(r8, r7)     // Catch:{ all -> 0x0056 }
            $closeResource(r4, r0)     // Catch:{ RequestFailedException -> 0x005f }
            return r2
        L_0x0056:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x0058 }
        L_0x0058:
            r8 = move-exception
            if (r0 == 0) goto L_0x005e
            $closeResource(r7, r0)     // Catch:{ RequestFailedException -> 0x005f }
        L_0x005e:
            throw r8     // Catch:{ RequestFailedException -> 0x005f }
        L_0x005f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.voicemail.impl.PinChangerImpl.changePin(java.lang.String, java.lang.String):int");
    }

    public PinChanger.PinSpecification getPinSpecification() {
        PinChanger.PinSpecification pinSpecification = new PinChanger.PinSpecification();
        String[] split = new VisualVoicemailPreferences(this.context, this.phoneAccountHandle).getString("pw_len", "").split("-");
        if (split.length == 2) {
            try {
                pinSpecification.minLength = Integer.parseInt(split[0]);
                pinSpecification.maxLength = Integer.parseInt(split[1]);
            } catch (NumberFormatException unused) {
            }
        }
        return pinSpecification;
    }

    public String getScrambledPin() {
        return new VisualVoicemailPreferences(this.context, this.phoneAccountHandle).getString("default_old_pin");
    }

    public void setScrambledPin(String str) {
        PerAccountSharedPreferences.Editor edit = new VisualVoicemailPreferences(this.context, this.phoneAccountHandle).edit();
        edit.putString("default_old_pin", str);
        edit.apply();
        if (str == null) {
            new OmtpVvmCarrierConfigHelper(this.context, this.phoneAccountHandle).handleEvent(Assert.edit(this.context, this.phoneAccountHandle), OmtpEvents.CONFIG_PIN_SET);
        }
    }
}
