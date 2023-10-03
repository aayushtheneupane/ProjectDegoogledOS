package p000;

import java.util.Collections;
import java.util.List;

/* renamed from: axs */
/* compiled from: PG */
public final class axs {

    /* renamed from: a */
    private final axx f1834a;

    /* renamed from: b */
    private final axr f1835b = new axr();

    public axs(C0306lc lcVar) {
        axx axx = new axx(lcVar);
        this.f1834a = axx;
    }

    /* renamed from: a */
    public final synchronized void mo1718a(Class cls, Class cls2, axp axp) {
        this.f1834a.mo1724a(cls, cls2, axp);
        this.f1835b.f1833a.clear();
    }

    /* renamed from: a */
    public final synchronized List mo1717a(Class cls) {
        return this.f1834a.mo1725b(cls);
    }

    /* renamed from: b */
    public final synchronized List mo1719b(Class cls) {
        List list;
        axq axq = (axq) this.f1835b.f1833a.get(cls);
        if (axq != null) {
            list = axq.f1832a;
        } else {
            list = null;
        }
        if (list == null) {
            list = Collections.unmodifiableList(this.f1834a.mo1723a(cls));
            if (((axq) this.f1835b.f1833a.put(cls, new axq(list))) != null) {
                String valueOf = String.valueOf(cls);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 34);
                sb.append("Already cached loaders for model: ");
                sb.append(valueOf);
                throw new IllegalStateException(sb.toString());
            }
        }
        return list;
    }
}
