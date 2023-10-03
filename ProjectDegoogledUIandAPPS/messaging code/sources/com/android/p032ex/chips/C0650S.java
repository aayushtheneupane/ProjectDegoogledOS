package com.android.p032ex.chips;

import android.text.TextWatcher;

/* renamed from: com.android.ex.chips.S */
class C0650S implements Runnable {
    final /* synthetic */ C0697qa this$0;

    C0650S(C0697qa qaVar) {
        this.this$0 = qaVar;
    }

    public void run() {
        if (this.this$0.mTextWatcher == null) {
            C0697qa qaVar = this.this$0;
            TextWatcher unused = qaVar.mTextWatcher = new C0695pa(qaVar, (C0650S) null);
            C0697qa qaVar2 = this.this$0;
            qaVar2.addTextChangedListener(qaVar2.mTextWatcher);
        }
    }
}
