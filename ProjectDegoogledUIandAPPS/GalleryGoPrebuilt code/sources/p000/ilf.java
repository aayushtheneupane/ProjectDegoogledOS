package p000;

import java.util.Iterator;
import java.util.Map;
import p003j$.util.Iterator$$CC;
import p003j$.util.function.Consumer;

/* renamed from: ilf */
/* compiled from: PG */
final class ilf implements Iterator, p003j$.util.Iterator {

    /* renamed from: a */
    private int f14437a = -1;

    /* renamed from: b */
    private boolean f14438b;

    /* renamed from: c */
    private Iterator f14439c;

    /* renamed from: d */
    private final /* synthetic */ ilh f14440d;

    public /* synthetic */ ilf(ilh ilh) {
        this.f14440d = ilh;
    }

    public final void forEachRemaining(Consumer consumer) {
        Iterator$$CC.forEachRemaining$$dflt$$(this, consumer);
    }

    /* renamed from: a */
    private final Iterator m13958a() {
        if (this.f14439c == null) {
            this.f14439c = this.f14440d.f14443b.entrySet().iterator();
        }
        return this.f14439c;
    }

    public final boolean hasNext() {
        if (this.f14437a + 1 < this.f14440d.f14442a.size()) {
            return true;
        }
        if (this.f14440d.f14443b.isEmpty() || !m13958a().hasNext()) {
            return false;
        }
        return true;
    }

    public final /* bridge */ /* synthetic */ Object next() {
        this.f14438b = true;
        int i = this.f14437a + 1;
        this.f14437a = i;
        if (i < this.f14440d.f14442a.size()) {
            return (Map.Entry) this.f14440d.f14442a.get(this.f14437a);
        }
        return (Map.Entry) m13958a().next();
    }

    public final void remove() {
        if (this.f14438b) {
            this.f14438b = false;
            this.f14440d.mo8921c();
            if (this.f14437a < this.f14440d.f14442a.size()) {
                ilh ilh = this.f14440d;
                int i = this.f14437a;
                this.f14437a = i - 1;
                ilh.mo8920c(i);
                return;
            }
            m13958a().remove();
            return;
        }
        throw new IllegalStateException("remove() was called before next()");
    }
}
