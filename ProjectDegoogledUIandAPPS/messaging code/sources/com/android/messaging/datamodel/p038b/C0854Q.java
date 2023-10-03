package com.android.messaging.datamodel.p038b;

import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.datamodel.b.Q */
class C0854Q implements Runnable {
    final /* synthetic */ C0858V this$0;

    C0854Q(C0858V v) {
        this.this$0 = v;
    }

    public void run() {
        C0632a.m1012Pk().delete(this.this$0.mAvatarUri, (String) null, (String[]) null);
    }
}
