package p000;

import java.util.Collections;
import java.util.List;

/* renamed from: ffd */
/* compiled from: PG */
public final class ffd implements ffc {

    /* renamed from: a */
    public final int f9439a;

    /* renamed from: b */
    private final List f9440b;

    /* renamed from: c */
    private final int f9441c;

    public ffd(int i, List list, int i2) {
        this.f9439a = i;
        this.f9440b = Collections.unmodifiableList(list);
        this.f9441c = i2;
        boolean z = true;
        if (i != 3) {
            ife.m12890c(i2 == -1 ? false : z);
        } else {
            ife.m12890c(i2 != -1 ? false : z);
        }
    }

    /* renamed from: a */
    public final List mo5573a() {
        return this.f9440b;
    }

    /* renamed from: b */
    public final fdx mo5574b() {
        return fhg.m8902a((ffc) this);
    }

    /* renamed from: c */
    public final int mo5575c() {
        int i = this.f9439a;
        boolean z = true;
        if (!(i == 1 || i == 2)) {
            z = false;
        }
        ife.m12896d(z);
        return this.f9441c;
    }
}
