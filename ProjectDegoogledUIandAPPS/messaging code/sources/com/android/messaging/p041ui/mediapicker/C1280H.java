package com.android.messaging.p041ui.mediapicker;

import android.net.Uri;
import com.android.messaging.datamodel.data.PendingAttachmentData;

/* renamed from: com.android.messaging.ui.mediapicker.H */
class C1280H implements Runnable {

    /* renamed from: Md */
    final /* synthetic */ Uri f2024Md;
    final /* synthetic */ C1281I this$0;

    C1280H(C1281I i, Uri uri) {
        this.this$0 = i;
        this.f2024Md = uri;
    }

    public void run() {
        this.this$0.f2118Dj.mo7901c(PendingAttachmentData.m1870a("text/x-vCard".toLowerCase(), this.f2024Md));
    }
}
