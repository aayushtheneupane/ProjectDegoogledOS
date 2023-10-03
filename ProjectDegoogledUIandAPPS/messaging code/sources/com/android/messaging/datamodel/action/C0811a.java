package com.android.messaging.datamodel.action;

/* renamed from: com.android.messaging.datamodel.action.a */
class C0811a implements Runnable {

    /* renamed from: Sq */
    final /* synthetic */ Action f1081Sq;

    /* renamed from: Ty */
    final /* synthetic */ boolean f1082Ty;
    final /* synthetic */ C0813c this$0;
    final /* synthetic */ Object val$result;

    C0811a(C0813c cVar, boolean z, Action action, Object obj) {
        this.this$0 = cVar;
        this.f1082Ty = z;
        this.f1081Sq = action;
        this.val$result = obj;
    }

    public void run() {
        C0812b c;
        synchronized (this.this$0.mLock) {
            c = this.this$0.f1083Uy != null ? this.this$0.f1083Uy : null;
            C0812b unused = this.this$0.f1083Uy = null;
        }
        if (c == null) {
            return;
        }
        if (this.f1082Ty) {
            C0813c cVar = this.this$0;
            c.mo5971b(cVar, this.f1081Sq, cVar.mData, this.val$result);
            return;
        }
        C0813c cVar2 = this.this$0;
        c.mo5970a(cVar2, this.f1081Sq, cVar2.mData, this.val$result);
    }
}
