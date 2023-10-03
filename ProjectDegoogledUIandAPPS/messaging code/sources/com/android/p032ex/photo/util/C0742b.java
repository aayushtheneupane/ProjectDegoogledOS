package com.android.p032ex.photo.util;

import android.content.ContentResolver;
import android.net.Uri;
import java.io.InputStream;

/* renamed from: com.android.ex.photo.util.b */
class C0742b implements C0744d {
    protected final ContentResolver mResolver;
    protected final Uri mUri;

    public C0742b(ContentResolver contentResolver, Uri uri) {
        this.mResolver = contentResolver;
        this.mUri = uri;
    }

    public InputStream createInputStream() {
        return this.mResolver.openInputStream(this.mUri);
    }
}
