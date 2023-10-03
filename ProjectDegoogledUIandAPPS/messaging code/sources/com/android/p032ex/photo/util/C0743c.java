package com.android.p032ex.photo.util;

import android.content.ContentResolver;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/* renamed from: com.android.ex.photo.util.c */
class C0743c extends C0742b {
    private byte[] mData;

    public C0743c(ContentResolver contentResolver, Uri uri) {
        super(contentResolver, uri);
    }

    public InputStream createInputStream() {
        if (this.mData == null) {
            String schemeSpecificPart = this.mUri.getSchemeSpecificPart();
            byte[] bArr = null;
            try {
                if (schemeSpecificPart.startsWith("base64,")) {
                    bArr = Base64.decode(schemeSpecificPart.substring(7), 8);
                } else if (C0745e.f913Cw.matcher(schemeSpecificPart).matches()) {
                    bArr = Base64.decode(schemeSpecificPart.substring(schemeSpecificPart.indexOf("base64,") + 7), 0);
                }
            } catch (IllegalArgumentException e) {
                Log.e("ImageUtils", "Mailformed data URI: " + e);
            }
            this.mData = bArr;
            if (this.mData == null) {
                return this.mResolver.openInputStream(this.mUri);
            }
        }
        return new ByteArrayInputStream(this.mData);
    }
}
