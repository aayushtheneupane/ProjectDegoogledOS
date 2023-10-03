package com.android.contacts.vcard;

import android.net.Uri;

public class ExportRequest {
    public final Uri destUri;
    public final String exportType;

    public ExportRequest(Uri uri) {
        this(uri, (String) null);
    }

    public ExportRequest(Uri uri, String str) {
        this.destUri = uri;
        this.exportType = str;
    }
}
