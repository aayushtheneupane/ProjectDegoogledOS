package com.android.voicemail.impl.transcribe.grpc;

import com.android.dialer.common.Assert;
import com.google.internal.communications.voicemailtranscription.p008v1.TranscribeVoicemailResponse;
import p009io.grpc.Status;

public class TranscriptionResponseSync extends TranscriptionResponse {
    private final TranscribeVoicemailResponse response;

    public TranscriptionResponseSync(Status status) {
        super(status);
        this.response = null;
    }

    public String getTranscript() {
        TranscribeVoicemailResponse transcribeVoicemailResponse = this.response;
        if (transcribeVoicemailResponse != null) {
            return transcribeVoicemailResponse.getTranscript();
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

    public TranscriptionResponseSync(TranscribeVoicemailResponse transcribeVoicemailResponse) {
        Assert.checkArgument(transcribeVoicemailResponse != null);
        this.response = transcribeVoicemailResponse;
    }
}
