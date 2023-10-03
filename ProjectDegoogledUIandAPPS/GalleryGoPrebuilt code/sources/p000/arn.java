package p000;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* renamed from: arn */
/* compiled from: PG */
public final class arn {

    /* renamed from: b */
    private static final arj f1487b = new arl();

    /* renamed from: a */
    private final Map f1488a = new HashMap();

    /* renamed from: a */
    public final synchronized ark mo1530a(Object obj) {
        arj arj;
        cns.m4632a(obj);
        arj = (arj) this.f1488a.get(obj.getClass());
        if (arj == null) {
            Iterator it = this.f1488a.values().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                arj arj2 = (arj) it.next();
                if (arj2.mo1527a().isAssignableFrom(obj.getClass())) {
                    arj = arj2;
                    break;
                }
            }
        }
        if (arj == null) {
            arj = f1487b;
        }
        return arj.mo1526a(obj);
    }

    /* renamed from: a */
    public final synchronized void mo1531a(arj arj) {
        this.f1488a.put(arj.mo1527a(), arj);
    }
}
