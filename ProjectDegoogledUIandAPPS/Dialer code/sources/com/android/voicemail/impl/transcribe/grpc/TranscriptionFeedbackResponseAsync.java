package com.android.voicemail.impl.transcribe.grpc;

import com.android.dialer.common.Assert;
import com.google.internal.communications.voicemailtranscription.p008v1.SendTranscriptionFeedbackResponse;
import p009io.grpc.Status;

public class TranscriptionFeedbackResponseAsync extends TranscriptionResponse {
    public TranscriptionFeedbackResponseAsync(SendTranscriptionFeedbackResponse sendTranscriptionFeedbackResponse) {
        Assert.checkArgument(sendTranscriptionFeedbackResponse != null);
    }

    public TranscriptionFeedbackResponseAsync(Status status) {
        super(status);
    }
}
