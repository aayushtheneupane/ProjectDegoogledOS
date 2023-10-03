package com.android.voicemail.impl.sms;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telecom.PhoneAccountHandle;
import android.telephony.VisualVoicemailSms;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.android.voicemail.impl.Assert;
import com.android.voicemail.impl.OmtpVvmCarrierConfigHelper;
import com.android.voicemail.impl.VvmLog;
import com.android.voicemail.impl.protocol.VisualVoicemailProtocol;
import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@TargetApi(26)
public class StatusSmsFetcher extends BroadcastReceiver implements Closeable {
    private final Context context;
    private CompletableFuture<Bundle> future = new CompletableFuture<>();
    private final PhoneAccountHandle phoneAccountHandle;

    public StatusSmsFetcher(Context context2, PhoneAccountHandle phoneAccountHandle2) {
        this.context = context2;
        this.phoneAccountHandle = phoneAccountHandle2;
        IntentFilter intentFilter = new IntentFilter("com.android.voicemailomtp.sms.REQUEST_SENT");
        intentFilter.addAction("com.android.vociemailomtp.sms.sms_received");
        context2.registerReceiver(this, intentFilter);
    }

    public void close() throws IOException {
        this.context.unregisterReceiver(this);
    }

    public Bundle get() throws InterruptedException, ExecutionException, TimeoutException, CancellationException {
        Assert.isNotMainThread();
        return this.future.get(60000, TimeUnit.MILLISECONDS);
    }

    public PendingIntent getSentIntent() {
        Intent intent = new Intent("com.android.voicemailomtp.sms.REQUEST_SENT");
        intent.setPackage(this.context.getPackageName());
        return PendingIntent.getBroadcast(this.context, 0, intent, 268435456);
    }

    public void onReceive(Context context2, Intent intent) {
        Bundle translateStatusSmsBundle;
        Assert.isMainThread();
        if ("com.android.voicemailomtp.sms.REQUEST_SENT".equals(intent.getAction())) {
            int resultCode = getResultCode();
            if (resultCode != -1) {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("Request SMS send failed: ");
                outline13.append(resultCode != -1 ? (resultCode == 1 || resultCode == 2 || resultCode == 3 || resultCode == 4) ? "RESULT_ERROR_GENERIC_FAILURE" : GeneratedOutlineSupport.outline5("UNKNOWN CODE: ", resultCode) : "OK");
                VvmLog.m43e("VvmStatusSmsFetcher", outline13.toString());
                this.future.cancel(true);
                return;
            }
            return;
        }
        VisualVoicemailSms visualVoicemailSms = (VisualVoicemailSms) intent.getExtras().getParcelable("extra_voicemail_sms");
        if (this.phoneAccountHandle.equals(visualVoicemailSms.getPhoneAccountHandle())) {
            String prefix = visualVoicemailSms.getPrefix();
            if (prefix.equals("STATUS")) {
                this.future.complete(visualVoicemailSms.getFields());
            } else if (!prefix.equals("SYNC")) {
                VvmLog.m45i("VvmStatusSmsFetcher", "VVM SMS with event " + prefix + " received, attempting to translate to STATUS SMS");
                OmtpVvmCarrierConfigHelper omtpVvmCarrierConfigHelper = new OmtpVvmCarrierConfigHelper(context2, this.phoneAccountHandle);
                VisualVoicemailProtocol protocol = omtpVvmCarrierConfigHelper.getProtocol();
                if (protocol != null && (translateStatusSmsBundle = protocol.translateStatusSmsBundle(omtpVvmCarrierConfigHelper, prefix, visualVoicemailSms.getFields())) != null) {
                    VvmLog.m45i("VvmStatusSmsFetcher", "Translated to STATUS SMS");
                    this.future.complete(translateStatusSmsBundle);
                }
            }
        }
    }
}
