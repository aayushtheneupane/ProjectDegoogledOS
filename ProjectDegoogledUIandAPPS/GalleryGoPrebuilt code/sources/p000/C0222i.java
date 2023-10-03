package p000;

import java.util.Iterator;
import java.util.Map;
import p003j$.util.Iterator$$CC;
import p003j$.util.function.Consumer;

/* renamed from: i */
/* compiled from: PG */
public final class C0222i implements Iterator, p003j$.util.Iterator, C0276k {

    /* renamed from: a */
    private C0195h f13707a;

    /* renamed from: b */
    private boolean f13708b = true;

    /* renamed from: c */
    private final /* synthetic */ C0303l f13709c;

    public C0222i(C0303l lVar) {
        this.f13709c = lVar;
    }

    public final void forEachRemaining(Consumer consumer) {
        Iterator$$CC.forEachRemaining$$dflt$$(this, consumer);
    }

    public final boolean hasNext() {
        if (this.f13708b) {
            return this.f13709c.f15178b != null;
        }
        C0195h hVar = this.f13707a;
        return (hVar == null || hVar.f12390c == null) ? false : true;
    }

    public final void remove() {
        Iterator$$CC.remove$$dflt$$(this);
    }

    /* renamed from: a */
    public final Map.Entry next() {
        C0195h hVar;
        if (this.f13708b) {
            this.f13708b = false;
            this.f13707a = this.f13709c.f15178b;
        } else {
            C0195h hVar2 = this.f13707a;
            if (hVar2 != null) {
                hVar = hVar2.f12390c;
            } else {
                hVar = null;
            }
            this.f13707a = hVar;
        }
        return this.f13707a;
    }

    /* renamed from: c */
    public final void mo8313c(C0195h hVar) {
        boolean z;
        C0195h hVar2 = this.f13707a;
        if (hVar == hVar2) {
            C0195h hVar3 = hVar2.f12391d;
            this.f13707a = hVar3;
            if (hVar3 == null) {
                z = true;
            } else {
                z = false;
            }
            this.f13708b = z;
        }
    }
}
