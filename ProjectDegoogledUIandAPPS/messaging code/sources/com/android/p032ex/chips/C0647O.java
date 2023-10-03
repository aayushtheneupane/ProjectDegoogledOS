package com.android.p032ex.chips;

import android.os.AsyncTask;
import android.widget.ListAdapter;
import android.widget.ListPopupWindow;
import android.widget.ListView;
import com.android.p032ex.chips.p033a.C0660b;

/* renamed from: com.android.ex.chips.O */
class C0647O extends AsyncTask {
    final /* synthetic */ C0697qa this$0;

    /* renamed from: ud */
    final /* synthetic */ C0660b f765ud;

    /* renamed from: vd */
    final /* synthetic */ ListPopupWindow f766vd;

    C0647O(C0697qa qaVar, C0660b bVar, ListPopupWindow listPopupWindow) {
        this.this$0 = qaVar;
        this.f765ud = bVar;
        this.f766vd = listPopupWindow;
    }

    /* access modifiers changed from: protected */
    public Object doInBackground(Object[] objArr) {
        Void[] voidArr = (Void[]) objArr;
        return this.this$0.createAlternatesAdapter(this.f765ud);
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Object obj) {
        ListAdapter listAdapter = (ListAdapter) obj;
        if (this.this$0.mAttachedToWindow) {
            int access$2000 = this.this$0.calculateOffsetFromBottomToTop(this.this$0.getLayout().getLineForOffset(this.this$0.getSpannable().getSpanStart(this.f765ud)));
            this.f766vd.setAnchorView(this.this$0.mAlternatePopupAnchor != null ? this.this$0.mAlternatePopupAnchor : this.this$0);
            this.f766vd.setVerticalOffset(access$2000);
            this.f766vd.setAdapter(listAdapter);
            this.f766vd.setOnItemClickListener(this.this$0.mAlternatesListener);
            int unused = this.this$0.mCheckedItem = -1;
            this.f766vd.show();
            ListView listView = this.f766vd.getListView();
            listView.setChoiceMode(1);
            if (this.this$0.mCheckedItem != -1) {
                listView.setItemChecked(this.this$0.mCheckedItem, true);
                int unused2 = this.this$0.mCheckedItem = -1;
            }
        }
    }
}
