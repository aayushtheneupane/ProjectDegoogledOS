package com.android.messaging.datamodel.action;

/* renamed from: com.android.messaging.datamodel.action.V */
public class C0807V extends C0813c implements C0812b {
    private final C0806U mListener;

    public C0807V(Object obj, C0806U u) {
        super(1, Action.m1321P("UpdateDestinationBlockedAction"), obj);
        mo6035a(this);
        this.mListener = u;
    }

    /* renamed from: b */
    private void m1464b(boolean z, Action action) {
        this.mListener.mo6019a((UpdateDestinationBlockedAction) action, z, action.f1057Oy.getBoolean("blocked"), action.f1057Oy.getString("destination"));
    }

    /* renamed from: a */
    public void mo5970a(C0813c cVar, Action action, Object obj, Object obj2) {
        m1464b(false, action);
    }

    /* renamed from: b */
    public void mo5971b(C0813c cVar, Action action, Object obj, Object obj2) {
        m1464b(true, action);
    }
}
