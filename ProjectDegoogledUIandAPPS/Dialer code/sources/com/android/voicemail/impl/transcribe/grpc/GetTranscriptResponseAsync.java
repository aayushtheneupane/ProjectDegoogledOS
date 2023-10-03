package com.android.voicemail.impl.transcribe.grpc;

import com.android.dialer.common.Assert;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.internal.communications.voicemailtranscription.p008v1.GetTranscriptResponse;
import com.google.internal.communications.voicemailtranscription.p008v1.TranscriptionStatus;
import p009io.grpc.Status;

public class GetTranscriptResponseAsync extends TranscriptionResponse {
    private final GetTranscriptResponse response;

    public GetTranscriptResponseAsync(GetTranscriptResponse getTranscriptResponse) {
        Assert.checkArgument(getTranscriptResponse != null);
        this.response = getTranscriptResponse;
    }

    public String getErrorDescription() {
        if (!hasRecoverableError() && !hasFatalError()) {
            return null;
        }
        if (this.status != null) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Grpc error: ");
            outline13.append(this.status);
            return outline13.toString();
        } else if (this.response != null) {
            StringBuilder outline132 = GeneratedOutlineSupport.outline13("Transcription error: ");
            outline132.append(this.response.getStatus());
            return outline132.toString();
        } else {
            throw new AssertionError("Impossible state");
        }
    }

    public String getTranscript() {
        GetTranscriptResponse getTranscriptResponse = this.response;
        if (getTranscriptResponse != null) {
            return getTranscriptResponse.getTranscript();
        }
        return null;
    }

    public TranscriptionStatus getTranscriptionStatus() {
        GetTranscriptResponse getTranscriptResponse = this.response;
        if (getTranscriptResponse == null) {
            return TranscriptionStatus.TRANSCRIPTION_STATUS_UNSPECIFIED;
        }
        return getTranscriptResponse.getStatus();
    }

    public boolean hasFatalError() {
        Status status = this.status;
        if ((status == null || status.getCode() == Status.Code.OK || this.status.getCode() == Status.Code.UNAVAILABLE) ? false : true) {
            return true;
        }
        GetTranscriptResponse getTranscriptResponse = this.response;
        if (getTranscriptResponse == null) {
            return false;
        }
        if (getTranscriptResponse.getStatus() == TranscriptionStatus.FAILED_NO_RETRY || this.response.getStatus() == TranscriptionStatus.FAILED_LANGUAGE_NOT_SUPPORTED || this.response.getStatus() == TranscriptionStatus.FAILED_NO_SPEECH_DETECTED) {
            return true;
        }
        return false;
    }

    public boolean hasRecoverableError() {
        Status status = this.status;
        if (status != null && status.getCode() == Status.Code.UNAVAILABLE) {
            return true;
        }
        GetTranscriptResponse getTranscriptResponse = this.response;
        if (getTranscriptResponse == null) {
            return false;
        }
        if (getTranscriptResponse.getStatus() == TranscriptionStatus.EXPIRED || this.response.getStatus() == TranscriptionStatus.FAILED_RETRY) {
            return true;
        }
        return false;
    }

    public boolean isTranscribing() {
        GetTranscriptResponse getTranscriptResponse = this.response;
        return getTranscriptResponse != null && getTranscriptResponse.getStatus() == TranscriptionStatus.PENDING;
    }

    public GetTranscriptResponseAsync(Status status) {
        super(status);
        this.response = null;
    }
}
