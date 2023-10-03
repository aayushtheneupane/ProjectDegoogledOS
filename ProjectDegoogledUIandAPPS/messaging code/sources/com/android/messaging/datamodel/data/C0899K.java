package com.android.messaging.datamodel.data;

import android.net.Uri;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.datamodel.data.K */
class C0899K implements Runnable {
    final /* synthetic */ Uri val$contentUri;

    C0899K(MessagePartData messagePartData, Uri uri) {
        this.val$contentUri = uri;
    }

    public void run() {
        C0632a.m1012Pk().delete(this.val$contentUri, (String) null, (String[]) null);
    }
}
