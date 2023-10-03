package com.android.messaging.p041ui.conversation;

import com.android.messaging.datamodel.data.MessagePartData;
import com.android.messaging.datamodel.data.PendingAttachmentData;
import com.android.messaging.p041ui.mediapicker.C1343oa;
import java.util.Collection;

/* renamed from: com.android.messaging.ui.conversation.X */
class C1154X implements C1343oa {
    final /* synthetic */ C1155Y this$1;

    C1154X(C1155Y y) {
        this.this$1 = y;
    }

    /* renamed from: uo */
    private void m2954uo() {
        C1155Y y = this.this$1;
        y.onVisibilityChanged(y.isOpen());
        this.this$1.this$0.mHost.invalidateActionBar();
        this.this$1.this$0.mHost.mo7392a(!this.this$1.this$0.f1860eH.isOpen());
    }

    /* renamed from: a */
    public void mo7453a(Collection collection, boolean z) {
        this.this$1.this$0.f1857bH.mo7305a(collection);
        this.this$1.this$0.mHost.invalidateActionBar();
        if (z) {
            this.this$1.this$0.f1857bH.mo7292J();
        }
    }

    /* renamed from: b */
    public void mo7454b(boolean z) {
        this.this$1.this$0.f1857bH.mo7312g(!z);
        m2954uo();
    }

    /* renamed from: c */
    public void mo7455c(MessagePartData messagePartData) {
        this.this$1.this$0.f1857bH.mo7308b(messagePartData);
        this.this$1.this$0.mHost.invalidateActionBar();
    }

    /* renamed from: d */
    public void mo7456d(int i) {
        this.this$1.this$0.mHost.invalidateActionBar();
        this.this$1.this$0.mHost.mo7380G();
    }

    /* renamed from: h */
    public void mo7457h() {
        this.this$1.this$0.f1857bH.mo7292J();
    }

    public void onDismissed() {
        this.this$1.this$0.f1857bH.mo7312g(true);
        m2954uo();
    }

    /* renamed from: x */
    public void mo7459x() {
        m2954uo();
    }

    /* renamed from: a */
    public void mo7452a(PendingAttachmentData pendingAttachmentData) {
        this.this$1.this$0.f1857bH.mo7309b(pendingAttachmentData);
    }
}
