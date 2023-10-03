package com.bumptech.glide.load.data;

import android.content.ContentResolver;
import android.content.UriMatcher;
import android.net.Uri;
import android.provider.ContactsContract;
import java.io.IOException;
import java.io.InputStream;

public class StreamLocalUriFetcher extends LocalUriFetcher<InputStream> {
    private static final UriMatcher URI_MATCHER = new UriMatcher(-1);

    static {
        URI_MATCHER.addURI("com.android.contacts", "contacts/lookup/*/#", 1);
        URI_MATCHER.addURI("com.android.contacts", "contacts/lookup/*", 1);
        URI_MATCHER.addURI("com.android.contacts", "contacts/#/photo", 2);
        URI_MATCHER.addURI("com.android.contacts", "contacts/#", 3);
        URI_MATCHER.addURI("com.android.contacts", "contacts/#/display_photo", 4);
        URI_MATCHER.addURI("com.android.contacts", "phone_lookup/*", 5);
    }

    public StreamLocalUriFetcher(ContentResolver contentResolver, Uri uri) {
        super(contentResolver, uri);
    }

    private InputStream openContactPhotoInputStream(ContentResolver contentResolver, Uri uri) {
        return ContactsContract.Contacts.openContactPhotoInputStream(contentResolver, uri, true);
    }

    /* access modifiers changed from: protected */
    public void close(Object obj) throws IOException {
        ((InputStream) obj).close();
    }

    public Class<InputStream> getDataClass() {
        return InputStream.class;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0025 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object loadResource(android.net.Uri r3, android.content.ContentResolver r4) throws java.io.FileNotFoundException {
        /*
            r2 = this;
            android.content.UriMatcher r0 = URI_MATCHER
            int r0 = r0.match(r3)
            r1 = 1
            if (r0 == r1) goto L_0x0019
            r1 = 3
            if (r0 == r1) goto L_0x0014
            r1 = 5
            if (r0 == r1) goto L_0x0019
            java.io.InputStream r2 = r4.openInputStream(r3)
            goto L_0x0023
        L_0x0014:
            java.io.InputStream r2 = r2.openContactPhotoInputStream(r4, r3)
            goto L_0x0023
        L_0x0019:
            android.net.Uri r0 = android.provider.ContactsContract.Contacts.lookupContact(r4, r3)
            if (r0 == 0) goto L_0x003c
            java.io.InputStream r2 = r2.openContactPhotoInputStream(r4, r0)
        L_0x0023:
            if (r2 == 0) goto L_0x0026
            return r2
        L_0x0026:
            java.io.FileNotFoundException r2 = new java.io.FileNotFoundException
            java.lang.String r3 = java.lang.String.valueOf(r3)
            int r4 = r3.length()
            int r4 = r4 + 24
            java.lang.String r0 = "InputStream is null for "
            java.lang.String r3 = com.android.tools.p006r8.GeneratedOutlineSupport.outline4(r4, r0, r3)
            r2.<init>(r3)
            throw r2
        L_0x003c:
            java.io.FileNotFoundException r2 = new java.io.FileNotFoundException
            java.lang.String r3 = "Contact cannot be found"
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.data.StreamLocalUriFetcher.loadResource(android.net.Uri, android.content.ContentResolver):java.lang.Object");
    }
}
