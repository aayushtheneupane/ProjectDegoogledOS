package com.android.messaging.p041ui.mediapicker;

import android.net.Uri;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.ui.mediapicker.e */
class C1322e implements Runnable {

    /* renamed from: RH */
    final /* synthetic */ Uri f2117RH;

    C1322e(AudioRecordView audioRecordView, Uri uri) {
        this.f2117RH = uri;
    }

    public void run() {
        C0632a.m1012Pk().delete(this.f2117RH, (String) null, (String[]) null);
    }
}
