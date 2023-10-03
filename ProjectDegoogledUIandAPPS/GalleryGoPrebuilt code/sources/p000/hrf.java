package p000;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import p003j$.util.Iterator$$CC;
import p003j$.util.function.Consumer;

/* renamed from: hrf */
/* compiled from: PG */
abstract class hrf implements Iterator, p003j$.util.Iterator {

    /* renamed from: a */
    private int f13298a = this.f13301d.f13302a.mo8100a();

    /* renamed from: b */
    private int f13299b = -1;

    /* renamed from: c */
    private int f13300c = this.f13301d.f13302a.f13432d;

    /* renamed from: d */
    private final /* synthetic */ hrg f13301d;

    public hrf(hrg hrg) {
        this.f13301d = hrg;
    }

    /* renamed from: a */
    public abstract Object mo7765a(int i);

    public final void forEachRemaining(Consumer consumer) {
        Iterator$$CC.forEachRemaining$$dflt$$(this, consumer);
    }

    /* renamed from: a */
    private final void m11953a() {
        if (this.f13301d.f13302a.f13432d != this.f13300c) {
            throw new ConcurrentModificationException();
        }
    }

    public final boolean hasNext() {
        m11953a();
        return this.f13298a >= 0;
    }

    public final Object next() {
        if (hasNext()) {
            Object a = mo7765a(this.f13298a);
            int i = this.f13298a;
            this.f13299b = i;
            this.f13298a = this.f13301d.f13302a.mo8101a(i);
            return a;
        }
        throw new NoSuchElementException();
    }

    public final void remove() {
        boolean z;
        m11953a();
        if (this.f13299b != -1) {
            z = true;
        } else {
            z = false;
        }
        ife.m12875b(z);
        hrg hrg = this.f13301d;
        hrg.f13303b -= (long) hrg.f13302a.mo8113g(this.f13299b);
        hut hut = this.f13301d.f13302a;
        this.f13298a--;
        this.f13299b = -1;
        this.f13300c = hut.f13432d;
    }
}
