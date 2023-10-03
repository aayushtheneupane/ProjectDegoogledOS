package androidx.recyclerview.widget;

import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: androidx.recyclerview.widget.f */
class C0563f implements Runnable {

    /* renamed from: Wq */
    final /* synthetic */ ArrayList f635Wq;
    final /* synthetic */ C0581o this$0;

    C0563f(C0581o oVar, ArrayList arrayList) {
        this.this$0 = oVar;
        this.f635Wq = arrayList;
    }

    public void run() {
        Iterator it = this.f635Wq.iterator();
        while (it.hasNext()) {
            this.this$0.mo5167a((C0577m) it.next());
        }
        this.f635Wq.clear();
        this.this$0.mChangesList.remove(this.f635Wq);
    }
}
