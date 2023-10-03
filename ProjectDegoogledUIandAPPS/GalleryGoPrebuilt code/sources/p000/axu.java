package p000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* renamed from: axu */
/* compiled from: PG */
final class axu implements axo {

    /* renamed from: a */
    private final List f1843a;

    /* renamed from: b */
    private final C0306lc f1844b;

    public axu(List list, C0306lc lcVar) {
        this.f1843a = list;
        this.f1844b = lcVar;
    }

    /* renamed from: a */
    public final axn mo1697a(Object obj, int i, int i2, aqz aqz) {
        axn a;
        int size = this.f1843a.size();
        ArrayList arrayList = new ArrayList(size);
        aqu aqu = null;
        for (int i3 = 0; i3 < size; i3++) {
            axo axo = (axo) this.f1843a.get(i3);
            if (axo.mo1698a(obj) && (a = axo.mo1697a(obj, i, i2, aqz)) != null) {
                aqu = a.f1829a;
                arrayList.add(a.f1831c);
            }
        }
        if (arrayList.isEmpty() || aqu == null) {
            return null;
        }
        return new axn(aqu, new axt(arrayList, this.f1844b));
    }

    /* renamed from: a */
    public final boolean mo1698a(Object obj) {
        List list = this.f1843a;
        int size = list.size();
        int i = 0;
        while (i < size) {
            int i2 = i + 1;
            if (((axo) list.get(i)).mo1698a(obj)) {
                return true;
            }
            i = i2;
        }
        return false;
    }

    public final String toString() {
        String arrays = Arrays.toString(this.f1843a.toArray());
        StringBuilder sb = new StringBuilder(String.valueOf(arrays).length() + 31);
        sb.append("MultiModelLoader{modelLoaders=");
        sb.append(arrays);
        sb.append('}');
        return sb.toString();
    }
}
