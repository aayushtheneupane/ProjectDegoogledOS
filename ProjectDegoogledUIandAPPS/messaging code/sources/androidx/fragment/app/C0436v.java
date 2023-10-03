package androidx.fragment.app;

import android.view.View;

/* renamed from: androidx.fragment.app.v */
class C0436v implements Runnable {
    final /* synthetic */ C0437w this$1;

    C0436v(C0437w wVar) {
        this.this$1 = wVar;
    }

    public void run() {
        if (this.this$1.f420m.getAnimatingAway() != null) {
            this.this$1.f420m.setAnimatingAway((View) null);
            C0437w wVar = this.this$1;
            C0389H h = wVar.this$0;
            C0424j jVar = wVar.f420m;
            h.mo4079a(jVar, jVar.getStateAfterAnimating(), 0, 0, false);
        }
    }
}
