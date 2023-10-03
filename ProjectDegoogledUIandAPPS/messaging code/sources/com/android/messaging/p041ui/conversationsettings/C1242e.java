package com.android.messaging.p041ui.conversationsettings;

import com.android.messaging.datamodel.data.C0909V;
import com.android.messaging.p041ui.C1117ca;
import com.android.messaging.p041ui.PersonItemView;

/* renamed from: com.android.messaging.ui.conversationsettings.e */
class C1242e implements C1117ca {

    /* renamed from: OH */
    final /* synthetic */ PersonItemView f1967OH;
    final /* synthetic */ C1243f this$1;

    C1242e(C1243f fVar, PersonItemView personItemView) {
        this.this$1 = fVar;
        this.f1967OH = personItemView;
    }

    /* renamed from: a */
    public void mo7224a(C0909V v) {
        this.f1967OH.mo7064Nb();
    }

    /* renamed from: c */
    public boolean mo7225c(C0909V v) {
        if (!this.this$1.this$0.mBinding.isBound()) {
            return false;
        }
        new C1238a(this.this$1.getContext(), v.getDetails()).show();
        return true;
    }
}
