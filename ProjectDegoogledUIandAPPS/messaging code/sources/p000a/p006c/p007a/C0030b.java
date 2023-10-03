package p000a.p006c.p007a;

import android.database.DataSetObserver;

/* renamed from: a.c.a.b */
class C0030b extends DataSetObserver {
    final /* synthetic */ C0031c this$0;

    C0030b(C0031c cVar) {
        this.this$0 = cVar;
    }

    public void onChanged() {
        C0031c cVar = this.this$0;
        cVar.mDataValid = true;
        cVar.notifyDataSetChanged();
    }

    public void onInvalidated() {
        C0031c cVar = this.this$0;
        cVar.mDataValid = false;
        cVar.notifyDataSetInvalidated();
    }
}
