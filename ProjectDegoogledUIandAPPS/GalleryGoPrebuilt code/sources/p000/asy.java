package p000;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* renamed from: asy */
/* compiled from: PG */
public final class asy {

    /* renamed from: a */
    public final bci f1592a;

    /* renamed from: b */
    public final C0306lc f1593b;

    /* renamed from: c */
    private final Class f1594c;

    /* renamed from: d */
    private final List f1595d;

    /* renamed from: e */
    private final String f1596e;

    public asy(Class cls, Class cls2, Class cls3, List list, bci bci, C0306lc lcVar) {
        this.f1594c = cls;
        this.f1595d = list;
        this.f1592a = bci;
        this.f1593b = lcVar;
        String simpleName = cls.getSimpleName();
        String simpleName2 = cls2.getSimpleName();
        String simpleName3 = cls3.getSimpleName();
        int length = String.valueOf(simpleName).length();
        StringBuilder sb = new StringBuilder(length + 23 + String.valueOf(simpleName2).length() + String.valueOf(simpleName3).length());
        sb.append("Failed DecodePath{");
        sb.append(simpleName);
        sb.append("->");
        sb.append(simpleName2);
        sb.append("->");
        sb.append(simpleName3);
        sb.append("}");
        this.f1596e = sb.toString();
    }

    /* renamed from: a */
    public final aua mo1576a(ark ark, int i, int i2, aqz aqz, List list) {
        int size = this.f1595d.size();
        aua aua = null;
        for (int i3 = 0; i3 < size; i3++) {
            arb arb = (arb) this.f1595d.get(i3);
            try {
                if (arb.mo1508a(ark.mo1528a(), aqz)) {
                    aua = arb.mo1507a(ark.mo1528a(), i, i2, aqz);
                }
            } catch (IOException | OutOfMemoryError | RuntimeException e) {
                list.add(e);
            }
            if (aua != null) {
                break;
            }
        }
        if (aua != null) {
            return aua;
        }
        throw new atu(this.f1596e, (List) new ArrayList(list));
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f1594c);
        String valueOf2 = String.valueOf(this.f1595d);
        String valueOf3 = String.valueOf(this.f1592a);
        int length = String.valueOf(valueOf).length();
        StringBuilder sb = new StringBuilder(length + 47 + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length());
        sb.append("DecodePath{ dataClass=");
        sb.append(valueOf);
        sb.append(", decoders=");
        sb.append(valueOf2);
        sb.append(", transcoder=");
        sb.append(valueOf3);
        sb.append('}');
        return sb.toString();
    }
}
