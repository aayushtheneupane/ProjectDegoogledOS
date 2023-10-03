package com.android.messaging.p041ui.mediapicker.camerafocus;

/* renamed from: com.android.messaging.ui.mediapicker.camerafocus.j */
class C1313j implements Runnable {
    final /* synthetic */ C1317n this$0;

    /* synthetic */ C1313j(C1317n nVar, C1309f fVar) {
        this.this$0 = nVar;
    }

    public void run() {
        if (this.this$0.mState != 8) {
            C1317n nVar = this.this$0;
            nVar.mVisible = false;
            nVar.update();
            C1317n nVar2 = this.this$0;
            int unused = nVar2.f2097gJ = nVar2.mCenterX;
            C1317n nVar3 = this.this$0;
            int unused2 = nVar3.f2098hJ = nVar3.mCenterY;
            int unused3 = this.this$0.mState = 0;
            C1317n nVar4 = this.this$0;
            nVar4.m3337Y(nVar4.f2097gJ, this.this$0.f2098hJ);
            boolean unused4 = this.this$0.f2105oJ = false;
        }
    }
}
