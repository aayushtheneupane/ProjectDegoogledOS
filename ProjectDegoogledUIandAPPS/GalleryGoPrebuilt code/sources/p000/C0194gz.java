package p000;

import android.transition.Transition;
import java.util.ArrayList;

/* renamed from: gz */
/* compiled from: PG */
final class C0194gz implements Transition.TransitionListener {

    /* renamed from: a */
    private final /* synthetic */ Object f12331a;

    /* renamed from: b */
    private final /* synthetic */ ArrayList f12332b;

    /* renamed from: c */
    private final /* synthetic */ Object f12333c;

    /* renamed from: d */
    private final /* synthetic */ ArrayList f12334d;

    /* renamed from: e */
    private final /* synthetic */ Object f12335e;

    /* renamed from: f */
    private final /* synthetic */ ArrayList f12336f;

    /* renamed from: g */
    private final /* synthetic */ C0198hc f12337g;

    public C0194gz(C0198hc hcVar, Object obj, ArrayList arrayList, Object obj2, ArrayList arrayList2, Object obj3, ArrayList arrayList3) {
        this.f12337g = hcVar;
        this.f12331a = obj;
        this.f12332b = arrayList;
        this.f12333c = obj2;
        this.f12334d = arrayList2;
        this.f12335e = obj3;
        this.f12336f = arrayList3;
    }

    public final void onTransitionCancel(Transition transition) {
    }

    public final void onTransitionPause(Transition transition) {
    }

    public final void onTransitionResume(Transition transition) {
    }

    public final void onTransitionEnd(Transition transition) {
        transition.removeListener(this);
    }

    public final void onTransitionStart(Transition transition) {
        Object obj = this.f12331a;
        if (obj != null) {
            this.f12337g.mo293b(obj, this.f12332b, (ArrayList) null);
        }
        Object obj2 = this.f12333c;
        if (obj2 != null) {
            this.f12337g.mo293b(obj2, this.f12334d, (ArrayList) null);
        }
        Object obj3 = this.f12335e;
        if (obj3 != null) {
            this.f12337g.mo293b(obj3, this.f12336f, (ArrayList) null);
        }
    }
}
