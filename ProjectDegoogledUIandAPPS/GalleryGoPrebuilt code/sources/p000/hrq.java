package p000;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import p003j$.util.Iterator$$CC;
import p003j$.util.function.Consumer;

/* renamed from: hrq */
/* compiled from: PG */
abstract class hrq implements Iterator, p003j$.util.Iterator {

    /* renamed from: a */
    private int f13313a;

    /* renamed from: b */
    private int f13314b;

    /* renamed from: c */
    private int f13315c = -1;

    /* renamed from: d */
    private final /* synthetic */ hru f13316d;

    public /* synthetic */ hrq(hru hru) {
        this.f13316d = hru;
        hru hru2 = this.f13316d;
        this.f13313a = hru2.f13327f;
        this.f13314b = hru2.mo7845e();
    }

    /* renamed from: a */
    public abstract Object mo7806a(int i);

    public final void forEachRemaining(Consumer consumer) {
        Iterator$$CC.forEachRemaining$$dflt$$(this, consumer);
    }

    public final boolean hasNext() {
        return this.f13314b >= 0;
    }

    /* renamed from: a */
    private final void m11979a() {
        hru hru = this.f13316d;
        Object obj = hru.f13322a;
        if (hru.f13327f != this.f13313a) {
            throw new ConcurrentModificationException();
        }
    }

    public final Object next() {
        m11979a();
        if (hasNext()) {
            int i = this.f13314b;
            this.f13315c = i;
            Object a = mo7806a(i);
            this.f13314b = this.f13316d.mo7832a(this.f13314b);
            return a;
        }
        throw new NoSuchElementException();
    }

    public final void remove() {
        boolean z;
        m11979a();
        if (this.f13315c >= 0) {
            z = true;
        } else {
            z = false;
        }
        ife.m12875b(z);
        this.f13313a += 32;
        hru hru = this.f13316d;
        hru.remove(hru.f13325d[this.f13315c]);
        this.f13314b--;
        this.f13315c = -1;
    }
}
