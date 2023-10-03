package com.android.p032ex.chips;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListPopupWindow;
import com.android.p032ex.chips.p033a.C0660b;

/* renamed from: com.android.ex.chips.Q */
class C0649Q implements AdapterView.OnItemClickListener {
    final /* synthetic */ C0697qa this$0;

    /* renamed from: ud */
    final /* synthetic */ C0660b f768ud;
    final /* synthetic */ ListPopupWindow val$popup;

    C0649Q(C0697qa qaVar, C0660b bVar, ListPopupWindow listPopupWindow) {
        this.this$0 = qaVar;
        this.f768ud = bVar;
        this.val$popup = listPopupWindow;
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        this.this$0.unselectChip(this.f768ud);
        this.val$popup.dismiss();
    }
}
