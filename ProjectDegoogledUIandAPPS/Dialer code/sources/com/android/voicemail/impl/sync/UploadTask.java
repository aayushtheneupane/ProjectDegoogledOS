package com.android.voicemail.impl.sync;

import android.content.Context;
import android.os.Bundle;
import android.telecom.PhoneAccountHandle;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.android.voicemail.impl.Assert;
import com.android.voicemail.impl.Voicemail;
import com.android.voicemail.impl.VvmLog;
import com.android.voicemail.impl.scheduling.BaseTask;
import com.android.voicemail.impl.scheduling.PostponePolicy;

public class UploadTask extends BaseTask {
    public UploadTask() {
        super(1);
        addPolicy(new PostponePolicy(5000));
    }

    public static void start(Context context, PhoneAccountHandle phoneAccountHandle) {
        context.sendBroadcast(BaseTask.createIntent(context, UploadTask.class, phoneAccountHandle));
    }

    public void onCreate(Context context, Bundle bundle) {
        super.onCreate(context, bundle);
    }

    public void onExecuteInBackgroundThread() {
        OmtpVvmSyncService omtpVvmSyncService = new OmtpVvmSyncService(getContext());
        PhoneAccountHandle phoneAccountHandle = getPhoneAccountHandle();
        if (phoneAccountHandle == null) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("null phone account for phoneAccountHandle ");
            outline13.append(getPhoneAccountHandle());
            VvmLog.m43e("VvmUploadTask", outline13.toString());
            return;
        }
        omtpVvmSyncService.sync(this, phoneAccountHandle, (Voicemail) null, Assert.edit(getContext(), phoneAccountHandle));
    }
}
