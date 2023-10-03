package com.android.messaging.p041ui.conversationlist;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import com.android.messaging.datamodel.data.C0933p;
import com.android.messaging.datamodel.data.C0934q;
import com.android.messaging.datamodel.data.MessageData;
import com.android.messaging.p041ui.BaseBugleActivity;
import com.android.messaging.p041ui.C1040Ea;
import com.android.messaging.util.C1424b;

/* renamed from: com.android.messaging.ui.conversationlist.ForwardMessageActivity */
public class ForwardMessageActivity extends BaseBugleActivity implements C1224m {

    /* renamed from: Yb */
    private MessageData f1924Yb;

    /* renamed from: B */
    public boolean mo7519B() {
        return false;
    }

    /* renamed from: Y */
    public boolean mo7533Y() {
        return false;
    }

    /* renamed from: Z */
    public void mo7520Z() {
        C1040Ea.get().mo6958a((Context) this, this.f1924Yb);
    }

    /* renamed from: a */
    public void mo7521a(C0933p pVar, C0934q qVar, boolean z, ConversationListItemView conversationListItemView) {
        C1040Ea.get().mo6960a((Context) this, qVar.mo6505Ue(), this.f1924Yb, (Bundle) null, false);
    }

    /* renamed from: d */
    public boolean mo7527d(String str) {
        return false;
    }

    public void onAttachFragment(Fragment fragment) {
        C1424b.m3592ia(fragment instanceof ConversationListFragment);
        ((ConversationListFragment) fragment).mo7545a((C1224m) this);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getFragmentManager().beginTransaction().add(16908290, ConversationListFragment.m3074i("forward_message_mode")).commit();
        this.f1924Yb = (MessageData) getIntent().getParcelableExtra("draft_data");
    }
}
