package p000;

import java.util.ListIterator;
import java.util.NoSuchElementException;

/* renamed from: hvs */
/* compiled from: PG */
public abstract class hvs extends hvr implements ListIterator, p003j$.util.ListIterator {

    /* renamed from: a */
    private final int f13490a;

    /* renamed from: b */
    private int f13491b;

    protected hvs() {
        this(2, 0);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract Object mo7866a(int i);

    public final boolean hasNext() {
        return this.f13491b < this.f13490a;
    }

    public final boolean hasPrevious() {
        return this.f13491b > 0;
    }

    public final int nextIndex() {
        return this.f13491b;
    }

    public final int previousIndex() {
        return this.f13491b - 1;
    }

    protected hvs(int i, int i2) {
        ife.m12888c(i2, i);
        this.f13490a = i;
        this.f13491b = i2;
    }

    public final Object next() {
        if (hasNext()) {
            int i = this.f13491b;
            this.f13491b = i + 1;
            return mo7866a(i);
        }
        throw new NoSuchElementException();
    }

    public final Object previous() {
        if (hasPrevious()) {
            int i = this.f13491b - 1;
            this.f13491b = i;
            return mo7866a(i);
        }
        throw new NoSuchElementException();
    }

    @Deprecated
    public final void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void set(Object obj) {
        throw new UnsupportedOperationException();
    }
}
