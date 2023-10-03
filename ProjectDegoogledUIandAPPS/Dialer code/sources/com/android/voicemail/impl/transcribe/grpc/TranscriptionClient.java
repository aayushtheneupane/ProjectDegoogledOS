package com.android.voicemail.impl.transcribe.grpc;

import com.google.internal.communications.voicemailtranscription.p008v1.GetTranscriptRequest;
import com.google.internal.communications.voicemailtranscription.p008v1.GetTranscriptResponse;
import com.google.internal.communications.voicemailtranscription.p008v1.SendTranscriptionFeedbackRequest;
import com.google.internal.communications.voicemailtranscription.p008v1.SendTranscriptionFeedbackResponse;
import com.google.internal.communications.voicemailtranscription.p008v1.TranscribeVoicemailAsyncRequest;
import com.google.internal.communications.voicemailtranscription.p008v1.TranscribeVoicemailAsyncResponse;
import com.google.internal.communications.voicemailtranscription.p008v1.TranscribeVoicemailRequest;
import com.google.internal.communications.voicemailtranscription.p008v1.TranscribeVoicemailResponse;
import com.google.internal.communications.voicemailtranscription.p008v1.VoicemailTranscriptionServiceGrpc;
import p009io.grpc.StatusRuntimeException;
import p009io.grpc.stub.ClientCalls;

public class TranscriptionClient {
    private final VoicemailTranscriptionServiceGrpc.VoicemailTranscriptionServiceBlockingStub stub;

    TranscriptionClient(VoicemailTranscriptionServiceGrpc.VoicemailTranscriptionServiceBlockingStub voicemailTranscriptionServiceBlockingStub) {
        this.stub = voicemailTranscriptionServiceBlockingStub;
    }

    public GetTranscriptResponseAsync sendGetTranscriptRequest(GetTranscriptRequest getTranscriptRequest) {
        try {
            VoicemailTranscriptionServiceGrpc.VoicemailTranscriptionServiceBlockingStub voicemailTranscriptionServiceBlockingStub = this.stub;
            return new GetTranscriptResponseAsync((GetTranscriptResponse) ClientCalls.blockingUnaryCall(voicemailTranscriptionServiceBlockingStub.getChannel(), VoicemailTranscriptionServiceGrpc.METHOD_GET_TRANSCRIPT, voicemailTranscriptionServiceBlockingStub.getCallOptions(), getTranscriptRequest));
        } catch (StatusRuntimeException e) {
            return new GetTranscriptResponseAsync(e.getStatus());
        }
    }

    public TranscriptionResponseSync sendSyncRequest(TranscribeVoicemailRequest transcribeVoicemailRequest) {
        try {
            VoicemailTranscriptionServiceGrpc.VoicemailTranscriptionServiceBlockingStub voicemailTranscriptionServiceBlockingStub = this.stub;
            return new TranscriptionResponseSync((TranscribeVoicemailResponse) ClientCalls.blockingUnaryCall(voicemailTranscriptionServiceBlockingStub.getChannel(), VoicemailTranscriptionServiceGrpc.METHOD_TRANSCRIBE_VOICEMAIL, voicemailTranscriptionServiceBlockingStub.getCallOptions(), transcribeVoicemailRequest));
        } catch (StatusRuntimeException e) {
            return new TranscriptionResponseSync(e.getStatus());
        }
    }

    public TranscriptionFeedbackResponseAsync sendTranscriptFeedbackRequest(SendTranscriptionFeedbackRequest sendTranscriptionFeedbackRequest) {
        try {
            VoicemailTranscriptionServiceGrpc.VoicemailTranscriptionServiceBlockingStub voicemailTranscriptionServiceBlockingStub = this.stub;
            return new TranscriptionFeedbackResponseAsync((SendTranscriptionFeedbackResponse) ClientCalls.blockingUnaryCall(voicemailTranscriptionServiceBlockingStub.getChannel(), VoicemailTranscriptionServiceGrpc.METHOD_SEND_TRANSCRIPTION_FEEDBACK, voicemailTranscriptionServiceBlockingStub.getCallOptions(), sendTranscriptionFeedbackRequest));
        } catch (StatusRuntimeException e) {
            return new TranscriptionFeedbackResponseAsync(e.getStatus());
        }
    }

    public TranscriptionResponseAsync sendUploadRequest(TranscribeVoicemailAsyncRequest transcribeVoicemailAsyncRequest) {
        try {
            VoicemailTranscriptionServiceGrpc.VoicemailTranscriptionServiceBlockingStub voicemailTranscriptionServiceBlockingStub = this.stub;
            return new TranscriptionResponseAsync((TranscribeVoicemailAsyncResponse) ClientCalls.blockingUnaryCall(voicemailTranscriptionServiceBlockingStub.getChannel(), VoicemailTranscriptionServiceGrpc.METHOD_TRANSCRIBE_VOICEMAIL_ASYNC, voicemailTranscriptionServiceBlockingStub.getCallOptions(), transcribeVoicemailAsyncRequest));
        } catch (StatusRuntimeException e) {
            return new TranscriptionResponseAsync(e.getStatus());
        }
    }
}
