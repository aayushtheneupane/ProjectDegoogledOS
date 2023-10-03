package com.android.voicemail.impl.imap;

public class VoicemailPayload {
    private final byte[] bytes;
    private final String mimeType;

    public VoicemailPayload(String str, byte[] bArr) {
        this.mimeType = str;
        this.bytes = bArr;
    }

    public byte[] getBytes() {
        return this.bytes;
    }

    public String getMimeType() {
        return this.mimeType;
    }
}
