package p000;

import android.view.View;
import android.view.Window;

/* renamed from: zz */
/* compiled from: PG */
final class C0707zz implements C0627x {

    /* renamed from: a */
    private final /* synthetic */ aac f16482a;

    public C0707zz(aac aac) {
        this.f16482a = aac;
    }

    /* renamed from: a */
    public final void mo3a(C0681z zVar, C0546u uVar) {
        View view;
        if (uVar == C0546u.ON_STOP) {
            Window window = this.f16482a.getWindow();
            if (window != null) {
                view = window.peekDecorView();
            } else {
                view = null;
            }
            if (view != null) {
                view.cancelPendingInputEvents();
            }
        }
    }
}
