package com.android.p032ex.chips;

import android.os.Message;
import android.view.View;
import android.widget.AdapterView;

/* renamed from: com.android.ex.chips.V */
class C0653V implements AdapterView.OnItemClickListener {
    final /* synthetic */ C0697qa this$0;

    C0653V(C0697qa qaVar) {
        this.this$0 = qaVar;
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        this.this$0.mAlternatesPopup.setOnItemClickListener((AdapterView.OnItemClickListener) null);
        C0697qa qaVar = this.this$0;
        qaVar.replaceChip(qaVar.mSelectedChip, ((C0646N) adapterView.getAdapter()).mo5446B(i));
        Message obtain = Message.obtain(this.this$0.mHandler, C0697qa.DISMISS);
        obtain.obj = this.this$0.mAlternatesPopup;
        this.this$0.mHandler.sendMessageDelayed(obtain, 300);
        this.this$0.clearComposingText();
    }
}
