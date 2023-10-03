package androidx.recyclerview.widget;

import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: androidx.recyclerview.widget.g */
class C0565g implements Runnable {

    /* renamed from: Xq */
    final /* synthetic */ ArrayList f636Xq;
    final /* synthetic */ C0581o this$0;

    C0565g(C0581o oVar, ArrayList arrayList) {
        this.this$0 = oVar;
        this.f636Xq = arrayList;
    }

    public void run() {
        Iterator it = this.f636Xq.iterator();
        while (it.hasNext()) {
            this.this$0.mo5180x((C0586qa) it.next());
        }
        this.f636Xq.clear();
        this.this$0.mAdditionsList.remove(this.f636Xq);
    }
}
