package com.android.messaging.p041ui.mediapicker;

import android.net.Uri;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.ui.mediapicker.va */
class C1357va implements Runnable {

    /* renamed from: DI */
    final /* synthetic */ Uri f2176DI;

    C1357va(C1359wa waVar, Uri uri) {
        this.f2176DI = uri;
    }

    public void run() {
        C0632a.m1012Pk().delete(this.f2176DI, (String) null, (String[]) null);
    }
}
