package p000;

import android.content.ComponentCallbacks2;
import java.util.Set;

/* renamed from: hdr */
/* compiled from: PG */
final class hdr implements C0438q {

    /* renamed from: a */
    private final /* synthetic */ C0149fj f12544a;

    /* renamed from: b */
    private final /* synthetic */ hdt f12545b;

    /* renamed from: c */
    private final /* synthetic */ ComponentCallbacks2 f12546c;

    /* renamed from: d */
    private final /* synthetic */ hds f12547d;

    public hdr(hds hds, C0149fj fjVar, hdt hdt, ComponentCallbacks2 componentCallbacks2) {
        this.f12547d = hds;
        this.f12544a = fjVar;
        this.f12545b = hdt;
        this.f12546c = componentCallbacks2;
    }

    /* renamed from: a */
    public final void mo2556a() {
    }

    /* renamed from: a */
    public final void mo2557a(C0681z zVar) {
    }

    /* renamed from: b */
    public final void mo2558b() {
    }

    /* renamed from: b */
    public final void mo2559b(C0681z zVar) {
    }

    /* renamed from: c */
    public final void mo2560c() {
    }

    /* renamed from: c */
    public final void mo2561c(C0681z zVar) {
        hdv hdv = this.f12547d.f12549b;
        C0149fj fjVar = this.f12544a;
        hdt hdt = this.f12545b;
        Set set = (Set) hdv.f12556a.get(fjVar);
        if (set != null) {
            set.remove(hdt);
        }
        this.f12544a.unregisterComponentCallbacks(this.f12546c);
        this.f12547d.f12548a.mo5ad().mo65b(this);
    }
}
