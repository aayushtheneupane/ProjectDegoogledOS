package com.android.messaging.p041ui.conversation;

import androidx.appcompat.app.ActionBar;
import com.android.messaging.R;
import com.android.messaging.datamodel.data.C0943z;
import com.android.messaging.datamodel.p037a.C0784d;
import com.android.messaging.p041ui.C1037D;
import com.android.messaging.p041ui.mediapicker.C1343oa;
import com.android.messaging.p041ui.mediapicker.C1345pa;

/* renamed from: com.android.messaging.ui.conversation.Y */
class C1155Y extends C1148Q {

    /* renamed from: Dj */
    private C1345pa f1850Dj;
    final /* synthetic */ C1158aa this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1155Y(C1158aa aaVar, C1147P p) {
        super(p, false);
        this.this$0 = aaVar;
    }

    /* access modifiers changed from: private */
    public boolean isOpen() {
        C1345pa paVar = this.f1850Dj;
        return paVar != null && paVar.isOpen();
    }

    /* access modifiers changed from: private */
    /* renamed from: so */
    public C1345pa m2964so() {
        C1345pa paVar = this.f1850Dj;
        if (paVar != null) {
            return paVar;
        }
        C1345pa paVar2 = (C1345pa) this.this$0.mFragmentManager.findFragmentByTag("mediapicker");
        if (paVar2 == null) {
            paVar2 = this.this$0.mHost.mo7407la();
            if (paVar2 == null) {
                return null;
            }
            this.this$0.mFragmentManager.beginTransaction().replace(R.id.mediapicker_container, paVar2, "mediapicker").commit();
        }
        return paVar2;
    }

    /* renamed from: Ka */
    public void mo7460Ka() {
        C1345pa paVar = this.f1850Dj;
        if (paVar != null) {
            paVar.mo7892Ka();
        }
    }

    /* renamed from: aa */
    public boolean mo7435aa(boolean z) {
        C1345pa paVar = this.f1850Dj;
        if (paVar != null) {
            paVar.mo7905i(z);
        }
        return !isOpen();
    }

    public boolean onBackPressed() {
        C1345pa paVar = this.f1850Dj;
        if (paVar != null && paVar.onBackPressed()) {
            return true;
        }
        if (!this.mShowing) {
            return false;
        }
        this.f1843XG.mo7431a(this, false, true);
        return true;
    }

    /* renamed from: sa */
    public boolean mo7438sa() {
        if (!isOpen() || !this.f1850Dj.isFullScreen()) {
            return false;
        }
        C1345pa paVar = this.f1850Dj;
        if (paVar != null && paVar.onBackPressed()) {
            return true;
        }
        if (!this.mShowing) {
            return false;
        }
        this.f1843XG.mo7431a(this, false, true);
        return true;
    }

    public boolean show(boolean z) {
        if (this.f1850Dj == null) {
            this.f1850Dj = m2964so();
            int Ea = C1037D.get().mo6936Ea();
            C1345pa paVar = this.f1850Dj;
            if (paVar != null) {
                paVar.mo7904i(Ea);
            }
            this.f1850Dj.mo7897a((C0943z) this.this$0.mHost);
            this.f1850Dj.mo7895a((C0784d) this.this$0.f1859dH);
            this.f1850Dj.mo7899a((C1343oa) new C1154X(this));
        }
        this.f1850Dj.mo7894a(0, z);
        return isOpen();
    }

    public boolean updateActionBar(ActionBar actionBar) {
        if (!isOpen()) {
            return false;
        }
        this.f1850Dj.updateActionBar(actionBar);
        return true;
    }
}
