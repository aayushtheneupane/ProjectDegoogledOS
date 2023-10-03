package p000;

import java.util.ArrayList;
import java.util.List;

/* renamed from: bdw */
/* compiled from: PG */
public final class bdw {

    /* renamed from: a */
    private final List f2106a = new ArrayList();

    /* renamed from: a */
    public final synchronized void mo1849a(Class cls, arc arc) {
        this.f2106a.add(new bdv(cls, arc));
    }

    /* renamed from: a */
    public final synchronized arc mo1848a(Class cls) {
        arc arc;
        int size = this.f2106a.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                arc = null;
                break;
            }
            bdv bdv = (bdv) this.f2106a.get(i);
            if (bdv.f2104a.isAssignableFrom(cls)) {
                arc = bdv.f2105b;
                break;
            }
            i++;
        }
        return arc;
    }
}
