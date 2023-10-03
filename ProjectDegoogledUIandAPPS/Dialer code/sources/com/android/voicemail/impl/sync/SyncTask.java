package com.android.voicemail.impl.sync;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telecom.PhoneAccountHandle;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.voicemail.impl.Voicemail;
import com.android.voicemail.impl.scheduling.BaseTask;
import com.android.voicemail.impl.scheduling.MinimalIntervalPolicy;
import com.android.voicemail.impl.scheduling.RetryPolicy;
import com.android.voicemail.impl.utils.LoggerUtils;

public class SyncTask extends BaseTask {
    private PhoneAccountHandle phone;
    private final RetryPolicy retryPolicy = new RetryPolicy(4, 5000);

    public SyncTask() {
        super(2);
        addPolicy(this.retryPolicy);
        addPolicy(new MinimalIntervalPolicy(60000));
    }

    public static void start(Context context, PhoneAccountHandle phoneAccountHandle) {
        Intent createIntent = BaseTask.createIntent(context, SyncTask.class, phoneAccountHandle);
        createIntent.putExtra(BaseTask.EXTRA_PHONE_ACCOUNT_HANDLE, phoneAccountHandle);
        context.sendBroadcast(createIntent);
    }

    public Intent createRestartIntent() {
        LoggerUtils.logImpressionOnMainThread(getContext(), DialerImpression$Type.VVM_AUTO_RETRY_SYNC);
        Intent createRestartIntent = super.createRestartIntent();
        createRestartIntent.putExtra(BaseTask.EXTRA_PHONE_ACCOUNT_HANDLE, this.phone);
        return createRestartIntent;
    }

    public void onCreate(Context context, Bundle bundle) {
        super.onCreate(context, bundle);
        this.phone = (PhoneAccountHandle) bundle.getParcelable(BaseTask.EXTRA_PHONE_ACCOUNT_HANDLE);
    }

    public void onExecuteInBackgroundThread() {
        new OmtpVvmSyncService(getContext()).sync(this, this.phone, (Voicemail) null, this.retryPolicy.getVoicemailStatusEditor());
    }
}
