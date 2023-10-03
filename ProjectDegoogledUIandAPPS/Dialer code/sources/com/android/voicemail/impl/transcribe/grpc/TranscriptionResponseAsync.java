package com.android.voicemail.impl.transcribe.grpc;

import com.android.dialer.common.Assert;
import com.google.internal.communications.voicemailtranscription.p008v1.TranscribeVoicemailAsyncResponse;
import p009io.grpc.Status;

public class TranscriptionResponseAsync extends TranscriptionResponse {
    private final TranscribeVoicemailAsyncResponse response;

    public TranscriptionResponseAsync(TranscribeVoicemailAsyncResponse transcribeVoicemailAsyncResponse) {
        Assert.checkArgument(transcribeVoicemailAsyncResponse != null);
        this.response = transcribeVoicemailAsyncResponse;
    }

    public long getEstimatedWaitMillis() {
        TranscribeVoicemailAsyncResponse transcribeVoicemailAsyncResponse = this.response;
        if (transcribeVoicemailAsyncResponse != null) {
            return transcribeVoicemailAsyncResponse.getEstimatedWaitSecs() * 1000;
        }
        return 0;
    }

    public String getTranscriptionId() {
        TranscribeVoicemailAsyncResponse transcribeVoicemailAsyncResponse = this.response;
        if (transcribeVoicemailAsyncResponse != null) {
            return transcribeVoicemailAsyncResponse.getTranscriptionId();
        }
        return null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("status: " + this.status);
        sb.append(", response: ");
        sb.append(this.response);
        return sb.toString();
    }

    public TranscriptionResponseAsync(Status status) {
        super(status);
        this.response = null;
    }
}
