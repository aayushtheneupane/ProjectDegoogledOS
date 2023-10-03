package com.android.messaging.p041ui.conversation;

import androidx.recyclerview.widget.C0566ga;
import androidx.recyclerview.widget.RecyclerView;

/* renamed from: com.android.messaging.ui.conversation.B */
class C1131B extends C0566ga {

    /* renamed from: Ms */
    private int f1762Ms;

    /* renamed from: Ns */
    private boolean f1763Ns;

    /* renamed from: Os */
    private boolean f1764Os = true;

    /* renamed from: li */
    private int f1765li = 0;
    final /* synthetic */ C1146O this$0;

    C1131B(C1146O o) {
        this.this$0 = o;
    }

    /* renamed from: c */
    public void mo5124c(RecyclerView recyclerView, int i) {
        if (i == 0) {
            this.f1762Ms = 0;
            this.f1763Ns = false;
        } else if (i == 1) {
            this.this$0.mRecyclerView.getItemAnimator().endAnimations();
        }
        this.f1765li = i;
    }

    /* renamed from: f */
    public void mo5125f(RecyclerView recyclerView, int i, int i2) {
        if (this.f1765li == 1 && !this.f1763Ns) {
            this.f1762Ms += i2;
            if (this.f1762Ms < (-this.this$0.f1840Ta)) {
                this.this$0.f1828Ha.mo7318r(false);
                this.f1763Ns = true;
            }
        }
        if (this.f1764Os != this.this$0.m2871zm()) {
            this.this$0.f1829Ia.animate().alpha(this.this$0.m2871zm() ? 0.0f : 1.0f);
            this.f1764Os = this.this$0.m2871zm();
        }
    }
}
