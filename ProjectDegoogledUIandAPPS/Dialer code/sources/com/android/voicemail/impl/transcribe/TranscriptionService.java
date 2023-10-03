package com.android.voicemail.impl.transcribe;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.app.job.JobWorkItem;
import android.net.Uri;
import android.text.TextUtils;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.android.voicemail.impl.transcribe.grpc.TranscriptionClientFactory;
import java.util.concurrent.ExecutorService;

public class TranscriptionService extends JobService {
    static final String EXTRA_ACCOUNT_HANDLE = "extra_account_handle";
    static final String EXTRA_VOICEMAIL_URI = "extra_voicemail_uri";
    /* access modifiers changed from: private */
    public TranscriptionTask activeTask;
    private TranscriptionClientFactory clientFactory;
    private TranscriptionConfigProvider configProvider;
    private ExecutorService executorService;
    /* access modifiers changed from: private */
    public JobParameters jobParameters;
    /* access modifiers changed from: private */
    public boolean stopped;

    private class Callback implements JobCallback {
        /* synthetic */ Callback(C07891 r2) {
        }

        public void onWorkCompleted(JobWorkItem jobWorkItem) {
            Assert.isMainThread();
            LogUtil.m9i("TranscriptionService.Callback.onWorkCompleted", jobWorkItem.toString(), new Object[0]);
            TranscriptionTask unused = TranscriptionService.this.activeTask = null;
            if (TranscriptionService.this.stopped) {
                LogUtil.m9i("TranscriptionService.Callback.onWorkCompleted", "stopped", new Object[0]);
                return;
            }
            TranscriptionService.this.jobParameters.completeWork(jobWorkItem);
            boolean unused2 = TranscriptionService.this.checkForWork();
        }
    }

    interface JobCallback {
    }

    public TranscriptionService() {
        Assert.isMainThread();
    }

    /* JADX WARNING: type inference failed for: r0v5, types: [com.android.voicemail.impl.transcribe.TranscriptionTask] */
    /* JADX WARNING: type inference failed for: r2v5, types: [com.android.voicemail.impl.transcribe.TranscriptionTaskAsync] */
    /* JADX WARNING: type inference failed for: r2v6, types: [com.android.voicemail.impl.transcribe.TranscriptionTaskSync] */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean checkForWork() {
        /*
            r9 = this;
            com.android.dialer.common.Assert.isMainThread()
            boolean r0 = r9.stopped
            r1 = 0
            if (r0 == 0) goto L_0x0012
            java.lang.Object[] r9 = new java.lang.Object[r1]
            java.lang.String r0 = "TranscriptionService.checkForWork"
            java.lang.String r2 = "stopped"
            com.android.dialer.common.LogUtil.m9i(r0, r2, r9)
            return r1
        L_0x0012:
            android.app.job.JobParameters r0 = r9.jobParameters
            android.app.job.JobWorkItem r5 = r0.dequeueWork()
            if (r5 == 0) goto L_0x007f
            com.android.voicemail.impl.transcribe.TranscriptionTask r0 = r9.activeTask
            r8 = 1
            if (r0 != 0) goto L_0x0020
            r1 = r8
        L_0x0020:
            com.android.dialer.common.Assert.checkState(r1)
            com.android.voicemail.impl.transcribe.TranscriptionConfigProvider r0 = r9.configProvider
            boolean r0 = r0.shouldUseSyncApi()
            r1 = 0
            if (r0 == 0) goto L_0x004c
            com.android.voicemail.impl.transcribe.TranscriptionTaskSync r0 = new com.android.voicemail.impl.transcribe.TranscriptionTaskSync
            com.android.voicemail.impl.transcribe.TranscriptionService$Callback r4 = new com.android.voicemail.impl.transcribe.TranscriptionService$Callback
            r4.<init>(r1)
            com.android.voicemail.impl.transcribe.grpc.TranscriptionClientFactory r1 = r9.clientFactory
            if (r1 != 0) goto L_0x0042
            com.android.voicemail.impl.transcribe.grpc.TranscriptionClientFactory r1 = new com.android.voicemail.impl.transcribe.grpc.TranscriptionClientFactory
            com.android.voicemail.impl.transcribe.TranscriptionConfigProvider r2 = r9.getConfigProvider()
            r1.<init>(r9, r2)
            r9.clientFactory = r1
        L_0x0042:
            com.android.voicemail.impl.transcribe.grpc.TranscriptionClientFactory r6 = r9.clientFactory
            com.android.voicemail.impl.transcribe.TranscriptionConfigProvider r7 = r9.configProvider
            r2 = r0
            r3 = r9
            r2.<init>(r3, r4, r5, r6, r7)
            goto L_0x006b
        L_0x004c:
            com.android.voicemail.impl.transcribe.TranscriptionTaskAsync r0 = new com.android.voicemail.impl.transcribe.TranscriptionTaskAsync
            com.android.voicemail.impl.transcribe.TranscriptionService$Callback r4 = new com.android.voicemail.impl.transcribe.TranscriptionService$Callback
            r4.<init>(r1)
            com.android.voicemail.impl.transcribe.grpc.TranscriptionClientFactory r1 = r9.clientFactory
            if (r1 != 0) goto L_0x0062
            com.android.voicemail.impl.transcribe.grpc.TranscriptionClientFactory r1 = new com.android.voicemail.impl.transcribe.grpc.TranscriptionClientFactory
            com.android.voicemail.impl.transcribe.TranscriptionConfigProvider r2 = r9.getConfigProvider()
            r1.<init>(r9, r2)
            r9.clientFactory = r1
        L_0x0062:
            com.android.voicemail.impl.transcribe.grpc.TranscriptionClientFactory r6 = r9.clientFactory
            com.android.voicemail.impl.transcribe.TranscriptionConfigProvider r7 = r9.configProvider
            r2 = r0
            r3 = r9
            r2.<init>(r3, r4, r5, r6, r7)
        L_0x006b:
            r9.activeTask = r0
            java.util.concurrent.ExecutorService r0 = r9.executorService
            if (r0 != 0) goto L_0x0077
            java.util.concurrent.ExecutorService r0 = java.util.concurrent.Executors.newSingleThreadExecutor()
            r9.executorService = r0
        L_0x0077:
            java.util.concurrent.ExecutorService r0 = r9.executorService
            com.android.voicemail.impl.transcribe.TranscriptionTask r9 = r9.activeTask
            r0.execute(r9)
            return r8
        L_0x007f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.voicemail.impl.transcribe.TranscriptionService.checkForWork():boolean");
    }

    private TranscriptionConfigProvider getConfigProvider() {
        if (this.configProvider == null) {
            this.configProvider = new TranscriptionConfigProvider(this);
        }
        return this.configProvider;
    }

    static Uri getVoicemailUri(JobWorkItem jobWorkItem) {
        return (Uri) jobWorkItem.getIntent().getParcelableExtra(EXTRA_VOICEMAIL_URI);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0045 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0046  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean scheduleNewVoicemailTranscriptionJob(android.content.Context r7, android.net.Uri r8, android.telecom.PhoneAccountHandle r9, boolean r10) {
        /*
            com.android.dialer.common.Assert.isMainThread()
            int r0 = android.os.Build.VERSION.SDK_INT
            com.android.voicemail.VoicemailComponent r0 = com.android.voicemail.VoicemailComponent.get(r7)
            com.android.voicemail.VoicemailClient r0 = r0.getVoicemailClient()
            boolean r1 = r0.isVoicemailTranscriptionEnabled(r7, r9)
            java.lang.String r2 = "TranscriptionService.canTranscribeVoicemail"
            r3 = 1
            r4 = 0
            if (r1 != 0) goto L_0x0020
            java.lang.Object[] r0 = new java.lang.Object[r4]
            java.lang.String r1 = "transcription is not enabled"
            com.android.dialer.common.LogUtil.m9i(r2, r1, r0)
        L_0x001e:
            r0 = r4
            goto L_0x0043
        L_0x0020:
            boolean r1 = r0.hasAcceptedTos(r7, r9)
            if (r1 != 0) goto L_0x002e
            java.lang.Object[] r0 = new java.lang.Object[r4]
            java.lang.String r1 = "hasn't accepted TOS"
            com.android.dialer.common.LogUtil.m9i(r2, r1, r0)
            goto L_0x001e
        L_0x002e:
            java.lang.String r1 = "vvm_carrier_allows_ott_transcription_string"
            java.lang.String r0 = r0.getCarrierConfigString(r7, r9, r1)
            boolean r0 = java.lang.Boolean.parseBoolean(r0)
            if (r0 != 0) goto L_0x0042
            java.lang.Object[] r0 = new java.lang.Object[r4]
            java.lang.String r1 = "carrier doesn't allow transcription"
            com.android.dialer.common.LogUtil.m9i(r2, r1, r0)
            goto L_0x001e
        L_0x0042:
            r0 = r3
        L_0x0043:
            if (r0 != 0) goto L_0x0046
            return r4
        L_0x0046:
            java.lang.Object[] r0 = new java.lang.Object[r4]
            java.lang.String r1 = "TranscriptionService.scheduleNewVoicemailTranscriptionJob"
            java.lang.String r2 = "scheduling transcription"
            com.android.dialer.common.LogUtil.m9i(r1, r2, r0)
            com.android.dialer.logging.LoggingBindings r0 = com.android.dialer.logging.Logger.get(r7)
            com.android.dialer.logging.DialerImpression$Type r1 = com.android.dialer.logging.DialerImpression$Type.VVM_TRANSCRIPTION_VOICEMAIL_RECEIVED
            com.android.dialer.logging.LoggingBindingsStub r0 = (com.android.dialer.logging.LoggingBindingsStub) r0
            r0.logImpression(r1)
            android.content.ComponentName r0 = new android.content.ComponentName
            java.lang.Class<com.android.voicemail.impl.transcribe.TranscriptionService> r1 = com.android.voicemail.impl.transcribe.TranscriptionService.class
            r0.<init>(r7, r1)
            android.app.job.JobInfo$Builder r1 = new android.app.job.JobInfo$Builder
            r2 = 203(0xcb, float:2.84E-43)
            r1.<init>(r2, r0)
            if (r10 == 0) goto L_0x0078
            r5 = 0
            android.app.job.JobInfo$Builder r10 = r1.setMinimumLatency(r5)
            android.app.job.JobInfo$Builder r10 = r10.setOverrideDeadline(r5)
            r10.setRequiredNetworkType(r3)
            goto L_0x007c
        L_0x0078:
            r10 = 2
            r1.setRequiredNetworkType(r10)
        L_0x007c:
            java.lang.Class<android.app.job.JobScheduler> r10 = android.app.job.JobScheduler.class
            java.lang.Object r7 = r7.getSystemService(r10)
            android.app.job.JobScheduler r7 = (android.app.job.JobScheduler) r7
            android.content.Intent r10 = new android.content.Intent
            r10.<init>()
            java.lang.String r0 = "extra_voicemail_uri"
            r10.putExtra(r0, r8)
            if (r9 == 0) goto L_0x0095
            java.lang.String r8 = "extra_account_handle"
            r10.putExtra(r8, r9)
        L_0x0095:
            android.app.job.JobWorkItem r8 = new android.app.job.JobWorkItem
            r8.<init>(r10)
            android.app.job.JobInfo r9 = r1.build()
            int r7 = r7.enqueue(r9, r8)
            if (r7 != r3) goto L_0x00a5
            goto L_0x00a6
        L_0x00a5:
            r3 = r4
        L_0x00a6:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.voicemail.impl.transcribe.TranscriptionService.scheduleNewVoicemailTranscriptionJob(android.content.Context, android.net.Uri, android.telecom.PhoneAccountHandle, boolean):boolean");
    }

    public void onDestroy() {
        Assert.isMainThread();
        LogUtil.enterBlock("TranscriptionService.onDestroy");
        TranscriptionClientFactory transcriptionClientFactory = this.clientFactory;
        if (transcriptionClientFactory != null) {
            transcriptionClientFactory.shutdown();
            this.clientFactory = null;
        }
        ExecutorService executorService2 = this.executorService;
        if (executorService2 != null) {
            executorService2.shutdownNow();
            this.executorService = null;
        }
    }

    public boolean onStartJob(JobParameters jobParameters2) {
        Assert.isMainThread();
        LogUtil.enterBlock("TranscriptionService.onStartJob");
        if (!getConfigProvider().isVoicemailTranscriptionAvailable()) {
            LogUtil.m9i("TranscriptionService.onStartJob", "transcription not available, exiting.", new Object[0]);
            return false;
        } else if (TextUtils.isEmpty(getConfigProvider().getServerAddress())) {
            LogUtil.m9i("TranscriptionService.onStartJob", "transcription server not configured, exiting.", new Object[0]);
            return false;
        } else {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("transcription server address: ");
            outline13.append(this.configProvider.getServerAddress());
            LogUtil.m9i("TranscriptionService.onStartJob", outline13.toString(), new Object[0]);
            this.jobParameters = jobParameters2;
            return checkForWork();
        }
    }

    public boolean onStopJob(JobParameters jobParameters2) {
        Assert.isMainThread();
        LogUtil.m9i("TranscriptionService.onStopJob", "params: " + jobParameters2, new Object[0]);
        this.stopped = true;
        ((LoggingBindingsStub) Logger.get(this)).logImpression(DialerImpression$Type.VVM_TRANSCRIPTION_JOB_STOPPED);
        if (this.activeTask != null) {
            LogUtil.m9i("TranscriptionService.onStopJob", "cancelling active task", new Object[0]);
            this.activeTask.cancel();
            ((LoggingBindingsStub) Logger.get(this)).logImpression(DialerImpression$Type.VVM_TRANSCRIPTION_TASK_CANCELLED);
        }
        return true;
    }

    TranscriptionService(ExecutorService executorService2, TranscriptionClientFactory transcriptionClientFactory, TranscriptionConfigProvider transcriptionConfigProvider) {
        this.executorService = executorService2;
        this.clientFactory = transcriptionClientFactory;
        this.configProvider = transcriptionConfigProvider;
    }
}
