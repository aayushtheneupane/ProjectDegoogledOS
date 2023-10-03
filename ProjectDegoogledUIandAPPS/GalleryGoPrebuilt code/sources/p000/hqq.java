package p000;

import java.util.NoSuchElementException;

/* renamed from: hqq */
/* compiled from: PG */
public abstract class hqq extends hvr {

    /* renamed from: a */
    private Object f13269a;

    /* renamed from: b */
    private int f13270b = 2;

    protected hqq() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract Object mo7687a();

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final Object mo7688b() {
        this.f13270b = 3;
        return null;
    }

    public final boolean hasNext() {
        ife.m12896d(this.f13270b != 4);
        int i = this.f13270b;
        int i2 = i - 1;
        if (i == 0) {
            throw null;
        } else if (i2 == 0) {
            return true;
        } else {
            if (i2 != 2) {
                this.f13270b = 4;
                this.f13269a = mo7687a();
                if (this.f13270b != 3) {
                    this.f13270b = 1;
                    return true;
                }
            }
            return false;
        }
    }

    public final Object next() {
        if (hasNext()) {
            this.f13270b = 2;
            Object obj = this.f13269a;
            this.f13269a = null;
            return obj;
        }
        throw new NoSuchElementException();
    }
}
