package com.android.messaging.p041ui.conversationlist;

import androidx.recyclerview.widget.C0566ga;
import androidx.recyclerview.widget.RecyclerView;
import com.android.messaging.datamodel.data.C0933p;
import com.android.messaging.util.C1419X;

/* renamed from: com.android.messaging.ui.conversationlist.j */
class C1221j extends C0566ga {

    /* renamed from: Ps */
    int f1935Ps = 0;
    final /* synthetic */ ConversationListFragment this$0;

    C1221j(ConversationListFragment conversationListFragment) {
        this.this$0 = conversationListFragment;
    }

    /* renamed from: c */
    public void mo5124c(RecyclerView recyclerView, int i) {
        this.f1935Ps = i;
    }

    /* renamed from: f */
    public void mo5125f(RecyclerView recyclerView, int i, int i2) {
        int i3 = this.f1935Ps;
        if (i3 == 1 || i3 == 2) {
            C1419X.get().mo8048a(this.this$0.getActivity(), this.this$0.mRecyclerView);
        }
        if (this.this$0.m3069Am()) {
            this.this$0.mo7558va();
        } else {
            ((C0933p) this.this$0.mListBinding.getData()).mo6485K(false);
        }
    }
}
