package com.android.voicemail.impl.transcribe;

import android.app.job.JobWorkItem;
import android.content.Context;
import android.util.Pair;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.android.voicemail.VoicemailComponent;
import com.android.voicemail.impl.VvmLog;
import com.android.voicemail.impl.transcribe.TranscriptionService;
import com.android.voicemail.impl.transcribe.TranscriptionTask;
import com.android.voicemail.impl.transcribe.grpc.TranscriptionClient;
import com.android.voicemail.impl.transcribe.grpc.TranscriptionClientFactory;
import com.android.voicemail.impl.transcribe.grpc.TranscriptionResponse;
import com.android.voicemail.impl.transcribe.grpc.TranscriptionResponseAsync;
import com.google.internal.communications.voicemailtranscription.p008v1.DonationPreference;
import com.google.internal.communications.voicemailtranscription.p008v1.TranscribeVoicemailAsyncRequest;
import com.google.internal.communications.voicemailtranscription.p008v1.TranscriptionStatus;

public class TranscriptionTaskAsync extends TranscriptionTask {
    public TranscriptionTaskAsync(Context context, TranscriptionService.JobCallback jobCallback, JobWorkItem jobWorkItem, TranscriptionClientFactory transcriptionClientFactory, TranscriptionConfigProvider transcriptionConfigProvider) {
        super(context, jobCallback, jobWorkItem, transcriptionClientFactory, transcriptionConfigProvider);
    }

    /* access modifiers changed from: protected */
    public DialerImpression$Type getRequestSentImpression() {
        return DialerImpression$Type.VVM_TRANSCRIPTION_REQUEST_SENT_ASYNC;
    }

    /* access modifiers changed from: protected */
    public Pair<String, TranscriptionStatus> getTranscription() {
        VvmLog.m45i("TranscriptionTaskAsync", "getTranscription");
        if (GetTranscriptReceiver.hasPendingAlarm(this.context)) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("getTranscription, pending transcription, postponing transcription of: ");
            outline13.append(this.voicemailUri);
            VvmLog.m45i("TranscriptionTaskAsync", outline13.toString());
            return new Pair<>((Object) null, (Object) null);
        }
        TranscribeVoicemailAsyncRequest uploadRequest = getUploadRequest();
        StringBuilder outline132 = GeneratedOutlineSupport.outline13("getTranscription, uploading voicemail: ");
        outline132.append(this.voicemailUri);
        outline132.append(", id: ");
        outline132.append(uploadRequest.getTranscriptionId());
        VvmLog.m45i("TranscriptionTaskAsync", outline132.toString());
        TranscriptionResponseAsync transcriptionResponseAsync = (TranscriptionResponseAsync) sendRequest(new TranscriptionTask.Request() {
            public final TranscriptionResponse getResponse(TranscriptionClient transcriptionClient) {
                return transcriptionClient.sendUploadRequest(TranscribeVoicemailAsyncRequest.this);
            }
        });
        if (this.cancelled) {
            VvmLog.m45i("TranscriptionTaskAsync", "getTranscription, cancelled.");
            return new Pair<>((Object) null, TranscriptionStatus.FAILED_NO_RETRY);
        } else if (transcriptionResponseAsync == null) {
            VvmLog.m45i("TranscriptionTaskAsync", "getTranscription, failed to upload voicemail.");
            return new Pair<>((Object) null, TranscriptionStatus.FAILED_NO_RETRY);
        } else if (transcriptionResponseAsync.isStatusAlreadyExists()) {
            VvmLog.m45i("TranscriptionTaskAsync", "getTranscription, transcription already exists.");
            GetTranscriptReceiver.beginPolling(this.context, this.voicemailUri, uploadRequest.getTranscriptionId(), 0, this.configProvider, this.phoneAccountHandle);
            return new Pair<>((Object) null, (Object) null);
        } else if (transcriptionResponseAsync.getTranscriptionId() == null) {
            StringBuilder outline133 = GeneratedOutlineSupport.outline13("getTranscription, upload error: ");
            outline133.append(transcriptionResponseAsync.status);
            VvmLog.m45i("TranscriptionTaskAsync", outline133.toString());
            return new Pair<>((Object) null, TranscriptionStatus.FAILED_NO_RETRY);
        } else {
            StringBuilder outline134 = GeneratedOutlineSupport.outline13("getTranscription, begin polling for: ");
            outline134.append(transcriptionResponseAsync.getTranscriptionId());
            VvmLog.m45i("TranscriptionTaskAsync", outline134.toString());
            GetTranscriptReceiver.beginPolling(this.context, this.voicemailUri, transcriptionResponseAsync.getTranscriptionId(), transcriptionResponseAsync.getEstimatedWaitMillis(), this.configProvider, this.phoneAccountHandle);
            return new Pair<>((Object) null, (Object) null);
        }
    }

    /* access modifiers changed from: package-private */
    public TranscribeVoicemailAsyncRequest getUploadRequest() {
        TranscribeVoicemailAsyncRequest.Builder newBuilder = TranscribeVoicemailAsyncRequest.newBuilder();
        newBuilder.setVoicemailData(this.audioData);
        newBuilder.setAudioFormat(this.encoding);
        newBuilder.setDonationPreference(this.phoneAccountHandle != null && VoicemailComponent.get(this.context).getVoicemailClient().isVoicemailDonationEnabled(this.context, this.phoneAccountHandle) ? DonationPreference.DONATE : DonationPreference.DO_NOT_DONATE);
        if (this.configProvider.useClientGeneratedVoicemailIds() || VoicemailComponent.get(this.context).getVoicemailClient().isVoicemailDonationAvailable(this.context, this.phoneAccountHandle)) {
            newBuilder.setTranscriptionId(TranscriptionRatingHelper.getFingerprintFor(this.audioData, this.voicemailUri.toString()));
        }
        return (TranscribeVoicemailAsyncRequest) newBuilder.build();
    }
}
