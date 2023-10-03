package com.android.voicemail.impl.transcribe;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.app.job.JobWorkItem;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.p000v4.app.JobIntentService;
import com.android.dialer.common.LogUtil;
import com.android.voicemail.impl.transcribe.grpc.TranscriptionClientFactory;
import com.google.internal.communications.voicemailtranscription.p008v1.SendTranscriptionFeedbackRequest;
import com.google.protobuf.InvalidProtocolBufferException;

public class TranscriptionRatingService extends JobIntentService {
    public static boolean scheduleTask(Context context, SendTranscriptionFeedbackRequest sendTranscriptionFeedbackRequest) {
        int i = Build.VERSION.SDK_INT;
        LogUtil.enterBlock("TranscriptionRatingService.scheduleTask");
        JobInfo build = new JobInfo.Builder(206, new ComponentName(context, TranscriptionRatingService.class)).setRequiredNetworkType(1).build();
        Intent intent = new Intent();
        intent.putExtra("feedback_request_extra", sendTranscriptionFeedbackRequest.toByteArray());
        if (((JobScheduler) context.getSystemService(JobScheduler.class)).enqueue(build, new JobWorkItem(intent)) == 1) {
            return true;
        }
        return false;
    }

    public void onDestroy() {
        LogUtil.enterBlock("TranscriptionRatingService.onDestroy");
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onHandleWork(Intent intent) {
        LogUtil.enterBlock("TranscriptionRatingService.onHandleWork");
        TranscriptionClientFactory transcriptionClientFactory = new TranscriptionClientFactory(this, new TranscriptionConfigProvider(this));
        try {
            transcriptionClientFactory.getClient().sendTranscriptFeedbackRequest(SendTranscriptionFeedbackRequest.parseFrom(intent.getByteArrayExtra("feedback_request_extra")));
        } catch (InvalidProtocolBufferException e) {
            LogUtil.m7e("TranscriptionRatingService.onHandleWork", "failed to send feedback", (Throwable) e);
        } catch (Throwable th) {
            transcriptionClientFactory.shutdown();
            throw th;
        }
        transcriptionClientFactory.shutdown();
    }
}
