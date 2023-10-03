package com.android.messaging.p041ui.conversation;

import androidx.appcompat.app.ActionBar;

/* renamed from: com.android.messaging.ui.conversation.Q */
public abstract class C1148Q {

    /* renamed from: XG */
    protected C1147P f1843XG;
    protected boolean mShowing;

    public C1148Q(C1147P p, boolean z) {
        this.f1843XG = p;
        this.mShowing = z;
    }

    /* renamed from: aa */
    public abstract boolean mo7435aa(boolean z);

    public boolean onBackPressed() {
        if (!this.mShowing) {
            return false;
        }
        this.f1843XG.mo7431a(this, false, true);
        return true;
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(boolean z) {
        if (this.mShowing != z) {
            this.f1843XG.mo7434ka();
            this.mShowing = z;
            if (z) {
                this.f1843XG.mo7432b(this);
            }
            this.f1843XG.endUpdate();
        }
    }

    /* renamed from: sa */
    public boolean mo7438sa() {
        return false;
    }

    public abstract boolean show(boolean z);

    public boolean updateActionBar(ActionBar actionBar) {
        return false;
    }
}
