package p000;

import android.view.Window;

/* renamed from: pl */
/* compiled from: PG */
final class C0423pl implements C0485rt {

    /* renamed from: a */
    private boolean f15515a;

    /* renamed from: b */
    private final /* synthetic */ C0426po f15516b;

    public C0423pl(C0426po poVar) {
        this.f15516b = poVar;
    }

    /* renamed from: a */
    public final void mo9574a(C0472rg rgVar, boolean z) {
        if (!this.f15515a) {
            this.f15515a = true;
            this.f15516b.f15519a.mo10342m();
            Window.Callback callback = this.f15516b.f15521c;
            if (callback != null) {
                callback.onPanelClosed(108, rgVar);
            }
            this.f15515a = false;
        }
    }

    /* renamed from: a */
    public final boolean mo9575a(C0472rg rgVar) {
        Window.Callback callback = this.f15516b.f15521c;
        if (callback == null) {
            return false;
        }
        callback.onMenuOpened(108, rgVar);
        return true;
    }
}
