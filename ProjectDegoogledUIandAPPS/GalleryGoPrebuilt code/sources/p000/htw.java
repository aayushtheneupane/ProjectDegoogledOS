package p000;

import java.util.NoSuchElementException;

/* renamed from: htw */
/* compiled from: PG */
final class htw extends hvr {

    /* renamed from: a */
    private boolean f13397a;

    /* renamed from: b */
    private final /* synthetic */ Object f13398b;

    public htw(Object obj) {
        this.f13398b = obj;
    }

    public final boolean hasNext() {
        return !this.f13397a;
    }

    public final Object next() {
        if (!this.f13397a) {
            this.f13397a = true;
            return this.f13398b;
        }
        throw new NoSuchElementException();
    }
}
