package com.android.messaging.p041ui.mediapicker;

import android.net.Uri;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.ui.mediapicker.aa */
class C1299aa implements Runnable {

    /* renamed from: RH */
    final /* synthetic */ Uri f2055RH;

    C1299aa(C1303ca caVar, Uri uri) {
        this.f2055RH = uri;
    }

    public void run() {
        C0632a.m1012Pk().delete(this.f2055RH, (String) null, (String[]) null);
    }
}
