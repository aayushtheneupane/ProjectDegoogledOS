package androidx.activity;

import androidx.lifecycle.C0449f;
import androidx.lifecycle.C0450g;
import androidx.lifecycle.C0453j;
import androidx.lifecycle.Lifecycle$Event;

class OnBackPressedDispatcher$LifecycleOnBackPressedCancellable implements C0449f, C0119a {

    /* renamed from: Hl */
    private final C0450g f122Hl;

    /* renamed from: Il */
    private final C0122d f123Il;

    /* renamed from: Jl */
    private C0119a f124Jl;
    final /* synthetic */ C0124f this$0;

    OnBackPressedDispatcher$LifecycleOnBackPressedCancellable(C0124f fVar, C0450g gVar, C0122d dVar) {
        this.this$0 = fVar;
        this.f122Hl = gVar;
        this.f123Il = dVar;
        gVar.mo4460a(this);
    }

    /* renamed from: a */
    public void mo611a(C0453j jVar, Lifecycle$Event lifecycle$Event) {
        if (lifecycle$Event == Lifecycle$Event.ON_START) {
            C0124f fVar = this.this$0;
            C0122d dVar = this.f123Il;
            fVar.f129Ll.add(dVar);
            C0123e eVar = new C0123e(fVar, dVar);
            dVar.mo614a(eVar);
            this.f124Jl = eVar;
        } else if (lifecycle$Event == Lifecycle$Event.ON_STOP) {
            C0119a aVar = this.f124Jl;
            if (aVar != null) {
                aVar.cancel();
            }
        } else if (lifecycle$Event == Lifecycle$Event.ON_DESTROY) {
            cancel();
        }
    }

    public void cancel() {
        this.f122Hl.mo4461b(this);
        this.f123Il.mo615b(this);
        C0119a aVar = this.f124Jl;
        if (aVar != null) {
            aVar.cancel();
            this.f124Jl = null;
        }
    }
}
