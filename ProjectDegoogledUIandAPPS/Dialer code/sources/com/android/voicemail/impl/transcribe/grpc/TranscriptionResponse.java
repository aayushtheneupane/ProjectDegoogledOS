package com.android.voicemail.impl.transcribe.grpc;

import com.android.dialer.common.Assert;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import p009io.grpc.Status;

public abstract class TranscriptionResponse {
    public final Status status;

    TranscriptionResponse() {
        this.status = null;
    }

    public boolean hasRecoverableError() {
        Status status2 = this.status;
        if (status2 == null || status2.getCode() != Status.Code.UNAVAILABLE) {
            return false;
        }
        return true;
    }

    public boolean isStatusAlreadyExists() {
        Status status2 = this.status;
        if (status2 == null || status2.getCode() != Status.Code.ALREADY_EXISTS) {
            return false;
        }
        return true;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("status: ");
        outline13.append(this.status);
        return outline13.toString();
    }

    TranscriptionResponse(Status status2) {
        Assert.checkArgument(status2 != null);
        this.status = status2;
    }
}
