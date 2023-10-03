package com.android.p032ex.chips;

import com.android.p032ex.chips.p033a.C0660b;

/* renamed from: com.android.ex.chips.da */
class C0671da implements Runnable {
    final /* synthetic */ C0673ea this$2;

    /* renamed from: tv */
    final /* synthetic */ C0660b f790tv;
    final /* synthetic */ C0699ra val$entry;

    C0671da(C0673ea eaVar, C0660b bVar, C0699ra raVar) {
        this.this$2 = eaVar;
        this.f790tv = bVar;
        this.val$entry = raVar;
    }

    public void run() {
        this.this$2.this$1.this$0.replaceChip(this.f790tv, this.val$entry);
    }
}
