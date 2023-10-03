package com.android.voicemail.impl.sync;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telecom.PhoneAccountHandle;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.voicemail.impl.Assert;
import com.android.voicemail.impl.Voicemail;
import com.android.voicemail.impl.scheduling.BaseTask;
import com.android.voicemail.impl.scheduling.RetryPolicy;
import com.android.voicemail.impl.utils.LoggerUtils;

public class SyncOneTask extends BaseTask {
    private PhoneAccountHandle phone;
    private Voicemail voicemail;

    public SyncOneTask() {
        super(-2);
        addPolicy(new RetryPolicy(2, 5000));
    }

    public static void start(Context context, PhoneAccountHandle phoneAccountHandle, Voicemail voicemail2) {
        Intent createIntent = BaseTask.createIntent(context, SyncOneTask.class, phoneAccountHandle);
        createIntent.putExtra(BaseTask.EXTRA_PHONE_ACCOUNT_HANDLE, phoneAccountHandle);
        createIntent.putExtra("extra_voicemail", voicemail2);
        context.sendBroadcast(createIntent);
    }

    public Intent createRestartIntent() {
        LoggerUtils.logImpressionOnMainThread(getContext(), DialerImpression$Type.VVM_AUTO_RETRY_SYNC);
        Intent createRestartIntent = super.createRestartIntent();
        createRestartIntent.putExtra(BaseTask.EXTRA_PHONE_ACCOUNT_HANDLE, this.phone);
        createRestartIntent.putExtra("extra_voicemail", this.voicemail);
        return createRestartIntent;
    }

    public void onCreate(Context context, Bundle bundle) {
        super.onCreate(context, bundle);
        this.phone = (PhoneAccountHandle) bundle.getParcelable(BaseTask.EXTRA_PHONE_ACCOUNT_HANDLE);
        this.voicemail = (Voicemail) bundle.getParcelable("extra_voicemail");
    }

    public void onExecuteInBackgroundThread() {
        new OmtpVvmSyncService(getContext()).sync(this, this.phone, this.voicemail, Assert.edit(getContext(), this.phone));
    }
}
