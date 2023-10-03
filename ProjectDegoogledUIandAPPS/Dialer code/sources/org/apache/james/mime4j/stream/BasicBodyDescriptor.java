package org.apache.james.mime4j.stream;

import com.android.tools.p006r8.GeneratedOutlineSupport;

class BasicBodyDescriptor implements BodyDescriptor {
    private final String boundary;
    private final String charset;
    private final String mediaType;
    private final String mimeType;
    private final String subType;
    private final String transferEncoding;

    BasicBodyDescriptor(String str, String str2, String str3, String str4, String str5, String str6, long j) {
        this.mimeType = str;
        this.mediaType = str2;
        this.subType = str3;
        this.boundary = str4;
        this.charset = str5;
        this.transferEncoding = str6;
    }

    public String getBoundary() {
        return this.boundary;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public String getTransferEncoding() {
        return this.transferEncoding;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("[mimeType=");
        outline13.append(this.mimeType);
        outline13.append(", mediaType=");
        outline13.append(this.mediaType);
        outline13.append(", subType=");
        outline13.append(this.subType);
        outline13.append(", boundary=");
        outline13.append(this.boundary);
        outline13.append(", charset=");
        return GeneratedOutlineSupport.outline12(outline13, this.charset, "]");
    }
}
