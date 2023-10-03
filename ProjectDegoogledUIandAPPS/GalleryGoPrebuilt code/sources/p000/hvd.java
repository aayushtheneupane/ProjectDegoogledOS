package p000;

import java.io.Serializable;

/* renamed from: hvd */
/* compiled from: PG */
final class hvd implements Serializable {
    public static final long serialVersionUID = 0;

    /* renamed from: a */
    private final Object[] f13459a;

    /* renamed from: b */
    private final int[] f13460b;

    public hvd(huo huo) {
        htk htk = (htk) huo;
        int size = htk.mo7796f().size();
        this.f13459a = new Object[size];
        this.f13460b = new int[size];
        int i = 0;
        for (hun hun : htk.mo7796f()) {
            this.f13459a[i] = hun.mo8079a();
            this.f13460b[i] = hun.mo8080b();
            i++;
        }
    }

    /* access modifiers changed from: package-private */
    public Object readResolve() {
        hth hth = new hth(this.f13459a.length);
        int i = 0;
        while (true) {
            Object[] objArr = this.f13459a;
            if (i >= objArr.length) {
                return hth.mo7981a();
            }
            hth.mo7983a(objArr[i], this.f13460b[i]);
            i++;
        }
    }
}
