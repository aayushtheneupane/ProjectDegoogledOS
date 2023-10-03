package com.android.messaging.p041ui;

import com.android.messaging.datamodel.p038b.C0865e;
import com.android.messaging.datamodel.p038b.C0880t;

/* renamed from: com.android.messaging.ui.a */
class C1073a implements Runnable {
    final /* synthetic */ AsyncImageView this$0;

    C1073a(AsyncImageView asyncImageView) {
        this.this$0 = asyncImageView;
    }

    public void run() {
        if (this.this$0.mImageRequestBinding.isBound()) {
            AsyncImageView asyncImageView = this.this$0;
            C0880t unused = asyncImageView.f1566Pj = (C0880t) ((C0865e) asyncImageView.mImageRequestBinding.getData()).getDescriptor();
        }
        this.this$0.m2460rn();
        this.this$0.m2459qn();
    }
}
