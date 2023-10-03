package p000;

import java.util.Iterator;
import java.util.NoSuchElementException;
import p003j$.util.Iterator$$CC;
import p003j$.util.function.Consumer;

/* renamed from: hus */
/* compiled from: PG */
final class hus implements Iterator, p003j$.util.Iterator {

    /* renamed from: a */
    private final huo f13423a;

    /* renamed from: b */
    private final Iterator f13424b;

    /* renamed from: c */
    private hun f13425c;

    /* renamed from: d */
    private int f13426d;

    /* renamed from: e */
    private int f13427e;

    /* renamed from: f */
    private boolean f13428f;

    public hus(huo huo, Iterator it) {
        this.f13423a = huo;
        this.f13424b = it;
    }

    public final void forEachRemaining(Consumer consumer) {
        Iterator$$CC.forEachRemaining$$dflt$$(this, consumer);
    }

    public final boolean hasNext() {
        return this.f13426d > 0 || this.f13424b.hasNext();
    }

    public final Object next() {
        if (hasNext()) {
            int i = this.f13426d;
            if (i == 0) {
                hun hun = (hun) this.f13424b.next();
                this.f13425c = hun;
                i = hun.mo8080b();
                this.f13427e = i;
            }
            this.f13426d = i - 1;
            this.f13428f = true;
            return this.f13425c.mo8079a();
        }
        throw new NoSuchElementException();
    }

    public final void remove() {
        ife.m12875b(this.f13428f);
        if (this.f13427e == 1) {
            this.f13424b.remove();
        } else {
            this.f13423a.remove(this.f13425c.mo8079a());
        }
        this.f13427e--;
        this.f13428f = false;
    }
}
