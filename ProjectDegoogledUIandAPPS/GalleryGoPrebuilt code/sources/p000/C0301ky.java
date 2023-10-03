package p000;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import p003j$.util.Iterator$$CC;
import p003j$.util.Map;
import p003j$.util.function.Consumer;

/* renamed from: ky */
/* compiled from: PG */
final class C0301ky implements Iterator, Map.Entry, p003j$.util.Iterator, Map.Entry {

    /* renamed from: a */
    private int f15173a;

    /* renamed from: b */
    private int f15174b;

    /* renamed from: c */
    private boolean f15175c = false;

    /* renamed from: d */
    private final /* synthetic */ C0304la f15176d;

    public C0301ky(C0304la laVar) {
        this.f15176d = laVar;
        this.f15173a = laVar.mo9184a() - 1;
        this.f15174b = -1;
    }

    public final void forEachRemaining(Consumer consumer) {
        Iterator$$CC.forEachRemaining$$dflt$$(this, consumer);
    }

    public final boolean hasNext() {
        return this.f15174b < this.f15173a;
    }

    public final boolean equals(Object obj) {
        if (this.f15175c) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                return C0294kr.m14539a(entry.getKey(), this.f15176d.mo9186a(this.f15174b, 0)) && C0294kr.m14539a(entry.getValue(), this.f15176d.mo9186a(this.f15174b, 1));
            }
        }
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }

    public final Object getKey() {
        if (this.f15175c) {
            return this.f15176d.mo9186a(this.f15174b, 0);
        }
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }

    public final Object getValue() {
        if (this.f15175c) {
            return this.f15176d.mo9186a(this.f15174b, 1);
        }
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }

    public final int hashCode() {
        if (this.f15175c) {
            int i = 0;
            Object a = this.f15176d.mo9186a(this.f15174b, 0);
            Object a2 = this.f15176d.mo9186a(this.f15174b, 1);
            int hashCode = a != null ? a.hashCode() : 0;
            if (a2 != null) {
                i = a2.hashCode();
            }
            return hashCode ^ i;
        }
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }

    public final /* bridge */ /* synthetic */ Object next() {
        if (hasNext()) {
            this.f15174b++;
            this.f15175c = true;
            return this;
        }
        throw new NoSuchElementException();
    }

    public final void remove() {
        if (this.f15175c) {
            this.f15176d.mo9188a(this.f15174b);
            this.f15174b--;
            this.f15173a--;
            this.f15175c = false;
            return;
        }
        throw new IllegalStateException();
    }

    public final Object setValue(Object obj) {
        if (this.f15175c) {
            return this.f15176d.mo9187a(this.f15174b, obj);
        }
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }

    public final String toString() {
        return getKey() + "=" + getValue();
    }
}
