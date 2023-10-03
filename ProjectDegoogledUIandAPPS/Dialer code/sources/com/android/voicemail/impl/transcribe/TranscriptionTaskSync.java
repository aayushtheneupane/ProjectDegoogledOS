package com.android.voicemail.impl.transcribe;

import android.app.job.JobWorkItem;
import android.content.Context;
import android.util.Pair;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.voicemail.impl.VvmLog;
import com.android.voicemail.impl.transcribe.TranscriptionService;
import com.android.voicemail.impl.transcribe.TranscriptionTask;
import com.android.voicemail.impl.transcribe.grpc.TranscriptionClient;
import com.android.voicemail.impl.transcribe.grpc.TranscriptionClientFactory;
import com.android.voicemail.impl.transcribe.grpc.TranscriptionResponse;
import com.android.voicemail.impl.transcribe.grpc.TranscriptionResponseSync;
import com.google.internal.communications.voicemailtranscription.p008v1.TranscribeVoicemailRequest;
import com.google.internal.communications.voicemailtranscription.p008v1.TranscriptionStatus;

public class TranscriptionTaskSync extends TranscriptionTask {
    public TranscriptionTaskSync(Context context, TranscriptionService.JobCallback jobCallback, JobWorkItem jobWorkItem, TranscriptionClientFactory transcriptionClientFactory, TranscriptionConfigProvider transcriptionConfigProvider) {
        super(context, jobCallback, jobWorkItem, transcriptionClientFactory, transcriptionConfigProvider);
    }

    /* access modifiers changed from: protected */
    public DialerImpression$Type getRequestSentImpression() {
        return DialerImpression$Type.VVM_TRANSCRIPTION_REQUEST_SENT;
    }

    /* access modifiers changed from: protected */
    public Pair<String, TranscriptionStatus> getTranscription() {
        VvmLog.m45i("TranscriptionTaskSync", "getTranscription");
        TranscriptionResponseSync transcriptionResponseSync = (TranscriptionResponseSync) sendRequest(new TranscriptionTask.Request() {
            public final TranscriptionResponse getResponse(TranscriptionClient transcriptionClient) {
                return TranscriptionTaskSync.this.lambda$getTranscription$0$TranscriptionTaskSync(transcriptionClient);
            }
        });
        if (transcriptionResponseSync == null) {
            VvmLog.m45i("TranscriptionTaskSync", "getTranscription, failed to transcribe voicemail.");
            return new Pair<>((Object) null, TranscriptionStatus.FAILED_NO_RETRY);
        }
        VvmLog.m45i("TranscriptionTaskSync", "getTranscription, got transcription");
        return new Pair<>(transcriptionResponseSync.getTranscript(), TranscriptionStatus.SUCCESS);
    }

    public /* synthetic */ TranscriptionResponse lambda$getTranscription$0$TranscriptionTaskSync(TranscriptionClient transcriptionClient) {
        TranscribeVoicemailRequest.Builder newBuilder = TranscribeVoicemailRequest.newBuilder();
        newBuilder.setVoicemailData(this.audioData);
        newBuilder.setAudioFormat(this.encoding);
        return transcriptionClient.sendSyncRequest((TranscribeVoicemailRequest) newBuilder.build());
    }
}
