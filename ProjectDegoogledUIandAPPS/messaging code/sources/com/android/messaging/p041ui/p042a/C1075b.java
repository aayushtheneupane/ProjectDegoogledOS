package com.android.messaging.p041ui.p042a;

import com.android.messaging.util.C1430e;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.ui.a.b */
class C1075b implements Runnable {
    final /* synthetic */ C1079f this$0;

    C1075b(C1079f fVar) {
        this.this$0 = fVar;
    }

    public void run() {
        StringBuilder Pa = C0632a.m1011Pa("PopupTransitionAnimation: ");
        Pa.append(this.this$0.mEvents);
        C1430e.m3630w("MessagingApp", Pa.toString());
    }
}
