package com.android.voicemail.impl.transcribe;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.SystemClock;
import android.telecom.PhoneAccountHandle;
import com.android.dialer.common.Assert;
import com.android.dialer.common.backoff.ExponentialBaseCalculator;
import com.android.dialer.common.concurrent.DefaultDialerExecutorFactory;
import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import com.android.voicemail.impl.VvmLog;
import com.android.voicemail.impl.transcribe.grpc.TranscriptionClientFactory;

public class GetTranscriptReceiver extends BroadcastReceiver {
    private static TranscriptionClientFactory transcriptionClientFactoryForTesting;

    private static class PollWorker implements DialerExecutor.Worker<Intent, Void> {
        private final Context context;

        PollWorker(Context context2) {
            this.context = context2;
        }

        /* JADX WARNING: Removed duplicated region for block: B:36:0x0116  */
        /* JADX WARNING: Removed duplicated region for block: B:37:0x0132  */
        /* JADX WARNING: Removed duplicated region for block: B:52:0x01d2  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object doInBackground(java.lang.Object r24) throws java.lang.Throwable {
            /*
                r23 = this;
                r0 = r23
                r1 = r24
                android.content.Intent r1 = (android.content.Intent) r1
                java.lang.String r2 = "extra_transcript_id"
                java.lang.String r3 = r1.getStringExtra(r2)
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                r4.<init>()
                java.lang.String r5 = "doInBackground, for transcript id: "
                r4.append(r5)
                r4.append(r3)
                java.lang.String r4 = r4.toString()
                java.lang.String r5 = "GetTranscriptReceiver"
                com.android.voicemail.impl.VvmLog.m45i(r5, r4)
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                r4.<init>()
                java.lang.String r6 = "pollForTranscription, transcript id: "
                r4.append(r6)
                r4.append(r3)
                java.lang.String r4 = r4.toString()
                com.android.voicemail.impl.VvmLog.m45i(r5, r4)
                r4 = 0
                if (r3 == 0) goto L_0x003b
                r7 = 1
                goto L_0x003c
            L_0x003b:
                r7 = r4
            L_0x003c:
                com.android.dialer.common.Assert.checkArgument(r7)
                com.google.internal.communications.voicemailtranscription.v1.GetTranscriptRequest$Builder r7 = com.google.internal.communications.voicemailtranscription.p008v1.GetTranscriptRequest.newBuilder()
                r7.setTranscriptionId(r3)
                com.google.protobuf.GeneratedMessageLite r7 = r7.build()
                com.google.internal.communications.voicemailtranscription.v1.GetTranscriptRequest r7 = (com.google.internal.communications.voicemailtranscription.p008v1.GetTranscriptRequest) r7
                r8 = 0
                android.content.Context r9 = r0.context     // Catch:{ all -> 0x01cf }
                com.android.voicemail.impl.transcribe.grpc.TranscriptionClientFactory r9 = com.android.voicemail.impl.transcribe.GetTranscriptReceiver.getTranscriptionClientFactory(r9)     // Catch:{ all -> 0x01cf }
                com.android.voicemail.impl.transcribe.grpc.TranscriptionClient r10 = r9.getClient()     // Catch:{ all -> 0x01cc }
                android.content.Context r11 = r0.context     // Catch:{ all -> 0x01cc }
                com.android.dialer.logging.LoggingBindings r11 = com.android.dialer.logging.Logger.get(r11)     // Catch:{ all -> 0x01cc }
                com.android.dialer.logging.DialerImpression$Type r12 = com.android.dialer.logging.DialerImpression$Type.VVM_TRANSCRIPTION_POLL_REQUEST     // Catch:{ all -> 0x01cc }
                com.android.dialer.logging.LoggingBindingsStub r11 = (com.android.dialer.logging.LoggingBindingsStub) r11
                r11.logImpression(r12)     // Catch:{ all -> 0x01cc }
                com.android.voicemail.impl.transcribe.grpc.GetTranscriptResponseAsync r7 = r10.sendGetTranscriptRequest(r7)     // Catch:{ all -> 0x01cc }
                if (r7 != 0) goto L_0x0075
                java.lang.String r7 = "pollForTranscription, no transcription result."
                com.android.voicemail.impl.VvmLog.m45i(r5, r7)     // Catch:{ all -> 0x01cc }
                android.util.Pair r7 = new android.util.Pair     // Catch:{ all -> 0x01cc }
                r7.<init>(r8, r8)     // Catch:{ all -> 0x01cc }
                goto L_0x0085
            L_0x0075:
                boolean r10 = r7.isTranscribing()     // Catch:{ all -> 0x01cc }
                if (r10 == 0) goto L_0x0089
                java.lang.String r7 = "pollForTranscription, transcribing"
                com.android.voicemail.impl.VvmLog.m45i(r5, r7)     // Catch:{ all -> 0x01cc }
                android.util.Pair r7 = new android.util.Pair     // Catch:{ all -> 0x01cc }
                r7.<init>(r8, r8)     // Catch:{ all -> 0x01cc }
            L_0x0085:
                r9.shutdown()
                goto L_0x00c3
            L_0x0089:
                boolean r10 = r7.hasFatalError()     // Catch:{ all -> 0x01cc }
                if (r10 == 0) goto L_0x00b1
                java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x01cc }
                r10.<init>()     // Catch:{ all -> 0x01cc }
                java.lang.String r11 = "pollForTranscription, fail. "
                r10.append(r11)     // Catch:{ all -> 0x01cc }
                java.lang.String r11 = r7.getErrorDescription()     // Catch:{ all -> 0x01cc }
                r10.append(r11)     // Catch:{ all -> 0x01cc }
                java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x01cc }
                com.android.voicemail.impl.VvmLog.m45i(r5, r10)     // Catch:{ all -> 0x01cc }
                android.util.Pair r10 = new android.util.Pair     // Catch:{ all -> 0x01cc }
                com.google.internal.communications.voicemailtranscription.v1.TranscriptionStatus r7 = r7.getTranscriptionStatus()     // Catch:{ all -> 0x01cc }
                r10.<init>(r8, r7)     // Catch:{ all -> 0x01cc }
                goto L_0x00c1
            L_0x00b1:
                java.lang.String r10 = "pollForTranscription, got transcription"
                com.android.voicemail.impl.VvmLog.m45i(r5, r10)     // Catch:{ all -> 0x01cc }
                android.util.Pair r10 = new android.util.Pair     // Catch:{ all -> 0x01cc }
                java.lang.String r7 = r7.getTranscript()     // Catch:{ all -> 0x01cc }
                com.google.internal.communications.voicemailtranscription.v1.TranscriptionStatus r11 = com.google.internal.communications.voicemailtranscription.p008v1.TranscriptionStatus.SUCCESS     // Catch:{ all -> 0x01cc }
                r10.<init>(r7, r11)     // Catch:{ all -> 0x01cc }
            L_0x00c1:
                r7 = r10
                goto L_0x0085
            L_0x00c3:
                java.lang.Object r9 = r7.first
                java.lang.String r10 = "extra_phone_account"
                java.lang.String r11 = "extra_voicemail_uri"
                if (r9 != 0) goto L_0x0152
                java.lang.Object r9 = r7.second
                if (r9 != 0) goto L_0x0152
                java.lang.String r7 = "extra_remaining_attempts"
                int r7 = r1.getIntExtra(r7, r4)
                r12 = 0
                java.lang.String r9 = "extra_base_multiplier"
                double r19 = r1.getDoubleExtra(r9, r12)
                r12 = 0
                java.lang.String r9 = "extra_delay_millis"
                long r14 = r1.getLongExtra(r9, r12)
                java.lang.String r6 = "extra_is_initial_estimated_wait"
                boolean r6 = r1.getBooleanExtra(r6, r4)
                if (r6 != 0) goto L_0x00f7
                int r7 = r7 + -1
                if (r7 > 0) goto L_0x00f3
                r2 = r8
                goto L_0x0114
            L_0x00f3:
                double r14 = (double) r14
                double r14 = r14 * r19
                long r14 = (long) r14
            L_0x00f7:
                r21 = r7
                r17 = r14
                android.content.Context r14 = r0.context
                android.os.Parcelable r6 = r1.getParcelableExtra(r11)
                r15 = r6
                android.net.Uri r15 = (android.net.Uri) r15
                java.lang.String r16 = r1.getStringExtra(r2)
                android.os.Parcelable r2 = r1.getParcelableExtra(r10)
                r22 = r2
                android.telecom.PhoneAccountHandle r22 = (android.telecom.PhoneAccountHandle) r22
                android.content.Intent r2 = com.android.voicemail.impl.transcribe.GetTranscriptReceiver.makeAlarmIntent(r14, r15, r16, r17, r19, r21, r22)
            L_0x0114:
                if (r2 != 0) goto L_0x0132
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                java.lang.String r6 = "doInBackground, too many failures for: "
                r2.append(r6)
                r2.append(r3)
                java.lang.String r2 = r2.toString()
                com.android.voicemail.impl.VvmLog.m45i(r5, r2)
                android.util.Pair r7 = new android.util.Pair
                com.google.internal.communications.voicemailtranscription.v1.TranscriptionStatus r2 = com.google.internal.communications.voicemailtranscription.p008v1.TranscriptionStatus.FAILED_NO_RETRY
                r7.<init>(r8, r2)
                goto L_0x0152
            L_0x0132:
                long r6 = r2.getLongExtra(r9, r12)
                r1 = 2
                java.lang.Object[] r1 = new java.lang.Object[r1]
                java.lang.Long r9 = java.lang.Long.valueOf(r6)
                r1[r4] = r9
                r4 = 1
                r1[r4] = r3
                java.lang.String r3 = "doInBackground, check again in %d, for: %s"
                java.lang.String r1 = java.lang.String.format(r3, r1)
                com.android.voicemail.impl.VvmLog.m45i(r5, r1)
                android.content.Context r0 = r0.context
                com.android.voicemail.impl.transcribe.GetTranscriptReceiver.scheduleAlarm(r0, r6, r2)
                goto L_0x01cb
            L_0x0152:
                android.os.Parcelable r2 = r1.getParcelableExtra(r11)
                android.net.Uri r2 = (android.net.Uri) r2
                com.android.voicemail.impl.transcribe.TranscriptionDbHelper r3 = new com.android.voicemail.impl.transcribe.TranscriptionDbHelper
                android.content.Context r6 = r0.context
                r3.<init>(r6, r2)
                android.content.Context r2 = r0.context
                com.android.voicemail.impl.transcribe.TranscriptionTask.recordResult(r2, r7, r3, r4)
                android.os.Parcelable r1 = r1.getParcelableExtra(r10)
                android.telecom.PhoneAccountHandle r1 = (android.telecom.PhoneAccountHandle) r1
                com.android.voicemail.impl.transcribe.TranscriptionDbHelper r2 = new com.android.voicemail.impl.transcribe.TranscriptionDbHelper
                android.content.Context r3 = r0.context
                r2.<init>(r3)
                java.util.List r2 = r2.getTranscribingVoicemails()
                boolean r3 = r2.isEmpty()
                if (r3 != 0) goto L_0x01c6
                java.lang.Object r2 = r2.get(r4)
                android.net.Uri r2 = (android.net.Uri) r2
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                java.lang.String r6 = "getPendingTranscription, found pending transcription "
                r3.append(r6)
                r3.append(r2)
                java.lang.String r3 = r3.toString()
                com.android.voicemail.impl.VvmLog.m45i(r5, r3)
                android.content.Context r3 = r0.context
                boolean r3 = com.android.voicemail.impl.transcribe.GetTranscriptReceiver.hasPendingAlarm(r3)
                if (r3 == 0) goto L_0x01bd
                android.content.Context r3 = r0.context
                android.content.Intent r5 = com.android.voicemail.impl.transcribe.GetTranscriptReceiver.makeBaseAlarmIntent(r3)
                android.content.Context r6 = r3.getApplicationContext()
                r7 = 536870912(0x20000000, float:1.0842022E-19)
                android.app.PendingIntent r4 = android.app.PendingIntent.getBroadcast(r6, r4, r5, r7)
                if (r4 == 0) goto L_0x01bd
                java.lang.String r5 = "alarm"
                java.lang.Object r3 = r3.getSystemService(r5)
                android.app.AlarmManager r3 = (android.app.AlarmManager) r3
                r3.cancel(r4)
                r4.cancel()
            L_0x01bd:
                com.android.voicemail.impl.transcribe.-$$Lambda$GetTranscriptReceiver$PollWorker$4kYfNOLHqT1pwPcHLWW-4ZeBDbU r3 = new com.android.voicemail.impl.transcribe.-$$Lambda$GetTranscriptReceiver$PollWorker$4kYfNOLHqT1pwPcHLWW-4ZeBDbU
                r3.<init>(r2, r1)
                com.android.dialer.common.concurrent.DialerExecutorModule.postOnUiThread(r3)
                goto L_0x01cb
            L_0x01c6:
                java.lang.String r0 = "getPendingTranscription, no more pending transcriptions"
                com.android.voicemail.impl.VvmLog.m45i(r5, r0)
            L_0x01cb:
                return r8
            L_0x01cc:
                r0 = move-exception
                r8 = r9
                goto L_0x01d0
            L_0x01cf:
                r0 = move-exception
            L_0x01d0:
                if (r8 == 0) goto L_0x01d5
                r8.shutdown()
            L_0x01d5:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.voicemail.impl.transcribe.GetTranscriptReceiver.PollWorker.doInBackground(java.lang.Object):java.lang.Object");
        }

        /* renamed from: lambda$processPendingTranscriptions$0$GetTranscriptReceiver$PollWorker */
        public /* synthetic */ void mo9393xc8c7aa18(Uri uri, PhoneAccountHandle phoneAccountHandle) {
            TranscriptionService.scheduleNewVoicemailTranscriptionJob(this.context, uri, phoneAccountHandle, true);
        }
    }

    static void beginPolling(Context context, Uri uri, String str, long j, TranscriptionConfigProvider transcriptionConfigProvider, PhoneAccountHandle phoneAccountHandle) {
        Assert.checkState(!hasPendingAlarm(context));
        long initialGetTranscriptPollDelayMillis = transcriptionConfigProvider.getInitialGetTranscriptPollDelayMillis();
        long maxGetTranscriptPollTimeMillis = transcriptionConfigProvider.getMaxGetTranscriptPollTimeMillis();
        int maxGetTranscriptPolls = transcriptionConfigProvider.getMaxGetTranscriptPolls();
        Intent makeAlarmIntent = makeAlarmIntent(context, uri, str, initialGetTranscriptPollDelayMillis, ExponentialBaseCalculator.findBase(initialGetTranscriptPollDelayMillis, maxGetTranscriptPollTimeMillis, maxGetTranscriptPolls), maxGetTranscriptPolls, phoneAccountHandle);
        makeAlarmIntent.putExtra("extra_is_initial_estimated_wait", true);
        VvmLog.m45i("GetTranscriptReceiver", String.format("beginPolling, check in %d millis, for: %s", new Object[]{Long.valueOf(j), str}));
        Context context2 = context;
        long j2 = j;
        scheduleAlarm(context, j, makeAlarmIntent);
    }

    static TranscriptionClientFactory getTranscriptionClientFactory(Context context) {
        TranscriptionClientFactory transcriptionClientFactory = transcriptionClientFactoryForTesting;
        if (transcriptionClientFactory != null) {
            return transcriptionClientFactory;
        }
        return new TranscriptionClientFactory(context, new TranscriptionConfigProvider(context));
    }

    static boolean hasPendingAlarm(Context context) {
        return PendingIntent.getBroadcast(context.getApplicationContext(), 0, makeBaseAlarmIntent(context), 536870912) != null;
    }

    /* access modifiers changed from: private */
    public static Intent makeAlarmIntent(Context context, Uri uri, String str, long j, double d, int i, PhoneAccountHandle phoneAccountHandle) {
        Intent makeBaseAlarmIntent = makeBaseAlarmIntent(context);
        makeBaseAlarmIntent.putExtra("extra_voicemail_uri", uri);
        makeBaseAlarmIntent.putExtra("extra_transcript_id", str);
        makeBaseAlarmIntent.putExtra("extra_delay_millis", j);
        makeBaseAlarmIntent.putExtra("extra_base_multiplier", d);
        makeBaseAlarmIntent.putExtra("extra_remaining_attempts", i);
        makeBaseAlarmIntent.putExtra("extra_phone_account", phoneAccountHandle);
        return makeBaseAlarmIntent;
    }

    /* access modifiers changed from: private */
    public static Intent makeBaseAlarmIntent(Context context) {
        Intent intent = new Intent(context.getApplicationContext(), GetTranscriptReceiver.class);
        intent.setAction("com.android.voicemail.impl.transcribe.GetTranscriptReceiver.POLL_ALARM");
        return intent;
    }

    /* access modifiers changed from: private */
    public void onFailure(Throwable th) {
        VvmLog.m44e("GetTranscriptReceiver", "onFailure", th);
    }

    /* access modifiers changed from: private */
    public void onSuccess(Void voidR) {
        VvmLog.m45i("GetTranscriptReceiver", "onSuccess");
    }

    /* access modifiers changed from: private */
    public static void scheduleAlarm(Context context, long j, Intent intent) {
        ((AlarmManager) context.getSystemService("alarm")).set(2, SystemClock.elapsedRealtime() + j, PendingIntent.getBroadcast(context.getApplicationContext(), 0, intent, 134217728));
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null && "com.android.voicemail.impl.transcribe.GetTranscriptReceiver.POLL_ALARM".equals(intent.getAction())) {
            String stringExtra = intent.getStringExtra("extra_transcript_id");
            VvmLog.m45i("GetTranscriptReceiver", "onReceive, for transcript id: " + stringExtra);
            ((DefaultDialerExecutorFactory) DialerExecutorComponent.get(context).dialerExecutorFactory()).createNonUiTaskBuilder(new PollWorker(context)).onSuccess(new DialerExecutor.SuccessListener() {
                public final void onSuccess(Object obj) {
                    GetTranscriptReceiver.this.onSuccess((Void) obj);
                }
            }).onFailure(new DialerExecutor.FailureListener() {
                public final void onFailure(Throwable th) {
                    GetTranscriptReceiver.this.onFailure(th);
                }
            }).build().executeParallel(intent);
        }
    }
}
