package androidx.recyclerview.widget;

import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: androidx.recyclerview.widget.e */
class C0561e implements Runnable {

    /* renamed from: Vq */
    final /* synthetic */ ArrayList f634Vq;
    final /* synthetic */ C0581o this$0;

    C0561e(C0581o oVar, ArrayList arrayList) {
        this.this$0 = oVar;
        this.f634Vq = arrayList;
    }

    public void run() {
        Iterator it = this.f634Vq.iterator();
        while (it.hasNext()) {
            C0579n nVar = (C0579n) it.next();
            this.this$0.mo5171b(nVar.holder, nVar.fromX, nVar.fromY, nVar.toX, nVar.toY);
        }
        this.f634Vq.clear();
        this.this$0.mMovesList.remove(this.f634Vq);
    }
}
