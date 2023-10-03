package p000;

import java.io.Serializable;
import java.util.Map;

/* renamed from: hst */
/* compiled from: PG */
final class hst implements Serializable {
    public static final long serialVersionUID = 0;

    /* renamed from: a */
    private final Object[] f13356a;

    /* renamed from: b */
    private final Object[] f13357b;

    public hst(hsu hsu) {
        this.f13356a = new Object[hsu.size()];
        this.f13357b = new Object[hsu.size()];
        hvr a = hsu.entrySet().iterator();
        int i = 0;
        while (a.hasNext()) {
            Map.Entry entry = (Map.Entry) a.next();
            this.f13356a[i] = entry.getKey();
            this.f13357b[i] = entry.getValue();
            i++;
        }
    }

    /* access modifiers changed from: package-private */
    public Object readResolve() {
        hsq hsq = new hsq(this.f13356a.length);
        int i = 0;
        while (true) {
            Object[] objArr = this.f13356a;
            if (i >= objArr.length) {
                return hsq.mo7930a();
            }
            hsq.mo7932a(objArr[i], this.f13357b[i]);
            i++;
        }
    }
}
