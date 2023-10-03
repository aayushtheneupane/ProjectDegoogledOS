package com.android.messaging.datamodel.action;

import com.android.messaging.datamodel.action.ReadDraftDataAction;
import com.android.messaging.datamodel.data.C0889A;
import com.android.messaging.util.C1424b;

/* renamed from: com.android.messaging.datamodel.action.E */
public class C0791E extends C0813c implements C0812b {
    private final C0790D mListener;

    C0791E(Object obj, C0790D d) {
        super(1, Action.m1321P("ReadDraftDataAction"), obj);
        mo6035a(this);
        this.mListener = d;
    }

    /* renamed from: a */
    public void mo5970a(C0813c cVar, Action action, Object obj, Object obj2) {
        C1424b.fail("Reading draft should not fail");
    }

    /* renamed from: b */
    public void mo5971b(C0813c cVar, Action action, Object obj, Object obj2) {
        ReadDraftDataAction.DraftData draftData = (ReadDraftDataAction.DraftData) obj2;
        if (draftData == null) {
            ((C0889A) this.mListener).mo6172a((ReadDraftDataAction) action, obj);
            return;
        }
        ((C0889A) this.mListener).mo6173a((ReadDraftDataAction) action, obj, draftData.message, draftData.f1080Zy);
    }
}
