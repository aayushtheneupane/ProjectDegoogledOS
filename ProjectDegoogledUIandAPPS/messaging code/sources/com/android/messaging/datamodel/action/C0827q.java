package com.android.messaging.datamodel.action;

import com.android.messaging.util.C1424b;

/* renamed from: com.android.messaging.datamodel.action.q */
public class C0827q extends C0813c implements C0812b {
    private final C0826p mListener;

    C0827q(Object obj, C0826p pVar) {
        super(1, Action.m1321P("GetOrCreateConversationAction"), obj);
        mo6035a(this);
        this.mListener = pVar;
    }

    /* renamed from: a */
    public void mo5970a(C0813c cVar, Action action, Object obj, Object obj2) {
        C1424b.fail("Unreachable");
        this.mListener.onGetOrCreateConversationFailed(cVar, obj);
    }

    /* renamed from: b */
    public void mo5971b(C0813c cVar, Action action, Object obj, Object obj2) {
        if (obj2 == null) {
            this.mListener.onGetOrCreateConversationFailed(cVar, obj);
        } else {
            this.mListener.onGetOrCreateConversationSucceeded(cVar, obj, (String) obj2);
        }
    }
}
