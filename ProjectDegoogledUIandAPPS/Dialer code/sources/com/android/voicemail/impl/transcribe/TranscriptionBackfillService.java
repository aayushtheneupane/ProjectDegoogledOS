package com.android.voicemail.impl.transcribe;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.app.job.JobWorkItem;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.p000v4.app.JobIntentService;
import android.telecom.PhoneAccountHandle;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DialerExecutorModule;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.List;

public class TranscriptionBackfillService extends JobIntentService {
    public static boolean scheduleTask(Context context, PhoneAccountHandle phoneAccountHandle) {
        int i = Build.VERSION.SDK_INT;
        LogUtil.enterBlock("TranscriptionBackfillService.transcribeOldVoicemails");
        JobInfo build = new JobInfo.Builder(204, new ComponentName(context, TranscriptionBackfillService.class)).setRequiredNetworkType(2).build();
        Intent intent = new Intent();
        intent.putExtra("extra_account_handle", phoneAccountHandle);
        return ((JobScheduler) context.getSystemService(JobScheduler.class)).enqueue(build, new JobWorkItem(intent)) == 1;
    }

    public /* synthetic */ void lambda$onHandleWork$0$TranscriptionBackfillService(Uri uri, PhoneAccountHandle phoneAccountHandle) {
        TranscriptionService.scheduleNewVoicemailTranscriptionJob(this, uri, phoneAccountHandle, false);
    }

    public void onDestroy() {
        LogUtil.enterBlock("TranscriptionBackfillService.onDestroy");
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onHandleWork(Intent intent) {
        LogUtil.enterBlock("TranscriptionBackfillService.onHandleWork");
        PhoneAccountHandle phoneAccountHandle = (PhoneAccountHandle) intent.getExtras().get("extra_account_handle");
        List<Uri> untranscribedVoicemails = new TranscriptionDbHelper(this).getUntranscribedVoicemails();
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("found ");
        outline13.append(untranscribedVoicemails.size());
        outline13.append(" untranscribed voicemails");
        LogUtil.m9i("TranscriptionBackfillService.onHandleWork", outline13.toString(), new Object[0]);
        for (Uri r1 : untranscribedVoicemails) {
            DialerExecutorModule.postOnUiThread(new Runnable(r1, phoneAccountHandle) {
                private final /* synthetic */ Uri f$1;
                private final /* synthetic */ PhoneAccountHandle f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    TranscriptionBackfillService.this.lambda$onHandleWork$0$TranscriptionBackfillService(this.f$1, this.f$2);
                }
            });
        }
    }
}
