package androidx.fragment.app;

import android.transition.Transition;
import java.util.ArrayList;

/* renamed from: androidx.fragment.app.Y */
class C0405Y implements Transition.TransitionListener {

    /* renamed from: bp */
    final /* synthetic */ ArrayList f393bp;

    /* renamed from: cp */
    final /* synthetic */ Object f394cp;

    /* renamed from: gp */
    final /* synthetic */ ArrayList f395gp;

    /* renamed from: hp */
    final /* synthetic */ ArrayList f396hp;

    /* renamed from: ip */
    final /* synthetic */ Object f397ip;
    final /* synthetic */ C0408aa this$0;

    /* renamed from: zp */
    final /* synthetic */ Object f398zp;

    C0405Y(C0408aa aaVar, Object obj, ArrayList arrayList, Object obj2, ArrayList arrayList2, Object obj3, ArrayList arrayList3) {
        this.this$0 = aaVar;
        this.f394cp = obj;
        this.f396hp = arrayList;
        this.f397ip = obj2;
        this.f393bp = arrayList2;
        this.f398zp = obj3;
        this.f395gp = arrayList3;
    }

    public void onTransitionCancel(Transition transition) {
    }

    public void onTransitionEnd(Transition transition) {
        transition.removeListener(this);
    }

    public void onTransitionPause(Transition transition) {
    }

    public void onTransitionResume(Transition transition) {
    }

    public void onTransitionStart(Transition transition) {
        Object obj = this.f394cp;
        if (obj != null) {
            this.this$0.mo4218a(obj, this.f396hp, (ArrayList) null);
        }
        Object obj2 = this.f397ip;
        if (obj2 != null) {
            this.this$0.mo4218a(obj2, this.f393bp, (ArrayList) null);
        }
        Object obj3 = this.f398zp;
        if (obj3 != null) {
            this.this$0.mo4218a(obj3, this.f395gp, (ArrayList) null);
        }
    }
}
