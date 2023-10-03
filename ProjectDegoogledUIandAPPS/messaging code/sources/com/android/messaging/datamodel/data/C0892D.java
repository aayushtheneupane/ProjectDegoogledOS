package com.android.messaging.datamodel.data;

import com.android.messaging.datamodel.action.C0813c;
import com.android.messaging.datamodel.action.C0826p;
import com.android.messaging.datamodel.action.C0827q;
import com.android.messaging.datamodel.action.GetOrCreateConversationAction;
import com.android.messaging.datamodel.p037a.C0781a;
import com.android.messaging.datamodel.p037a.C0784d;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;

/* renamed from: com.android.messaging.datamodel.data.D */
public class C0892D extends C0781a implements C0826p {
    private C0891C mListener;
    private C0827q mMonitor;

    public C0892D(C0891C c) {
        this.mListener = c;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Te */
    public void mo5924Te() {
        this.mListener = null;
        C0827q qVar = this.mMonitor;
        if (qVar != null) {
            qVar.unregister();
        }
        this.mMonitor = null;
    }

    /* renamed from: a */
    public void mo6220a(C0784d dVar, String[] strArr) {
        String bindingId = dVar.getBindingId();
        if (mo5926W(bindingId) && this.mMonitor == null) {
            this.mMonitor = GetOrCreateConversationAction.m1371a(strArr, (Object) bindingId, this);
        }
    }

    public void onGetOrCreateConversationFailed(C0813c cVar, Object obj) {
        C0891C c;
        C1424b.m3592ia(cVar == this.mMonitor);
        if (mo5926W((String) obj) && (c = this.mListener) != null) {
            c.mo6213A();
        }
        C1430e.m3622e("MessagingApp", "onGetOrCreateConversationFailed");
        this.mMonitor = null;
    }

    public void onGetOrCreateConversationSucceeded(C0813c cVar, Object obj, String str) {
        C0891C c;
        boolean z = true;
        C1424b.m3592ia(cVar == this.mMonitor);
        if (str == null) {
            z = false;
        }
        C1424b.m3592ia(z);
        if (mo5926W((String) obj) && (c = this.mListener) != null) {
            c.onGetOrCreateNewConversation(str);
        }
        this.mMonitor = null;
    }
}
