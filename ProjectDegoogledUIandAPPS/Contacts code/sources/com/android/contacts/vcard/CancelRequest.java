package com.android.contacts.vcard;

public class CancelRequest {
    public final String displayName;
    public final int jobId;

    public CancelRequest(int i, String str) {
        this.jobId = i;
        this.displayName = str;
    }
}
