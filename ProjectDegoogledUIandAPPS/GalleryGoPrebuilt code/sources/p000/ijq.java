package p000;

import java.util.List;

/* renamed from: ijq */
/* compiled from: PG */
final class ijq extends ijr {
    private ijq() {
    }

    public /* synthetic */ ijq(byte[] bArr) {
    }

    /* renamed from: c */
    private static ije m13681c(Object obj, long j) {
        return (ije) ilv.m14048f(obj, j);
    }

    /* renamed from: b */
    public final void mo8826b(Object obj, long j) {
        m13681c(obj, j).mo8526b();
    }

    /* renamed from: a */
    public final void mo8825a(Object obj, Object obj2, long j) {
        ije c = m13681c(obj, j);
        ije c2 = m13681c(obj2, j);
        int size = c.size();
        int size2 = c2.size();
        if (size > 0 && size2 > 0) {
            if (!c.mo8521a()) {
                c = c.mo8585a(size2 + size);
            }
            c.addAll(c2);
        }
        if (size > 0) {
            c2 = c;
        }
        ilv.m14036a(obj, j, (Object) c2);
    }

    /* renamed from: a */
    public final List mo8824a(Object obj, long j) {
        int i;
        ije c = m13681c(obj, j);
        if (c.mo8521a()) {
            return c;
        }
        int size = c.size();
        if (size != 0) {
            i = size + size;
        } else {
            i = 10;
        }
        ije a = c.mo8585a(i);
        ilv.m14036a(obj, j, (Object) a);
        return a;
    }
}
