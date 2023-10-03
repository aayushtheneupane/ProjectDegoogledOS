package p000;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/* renamed from: atx */
/* compiled from: PG */
public final class atx {

    /* renamed from: a */
    public final C0306lc f1676a;

    /* renamed from: b */
    public final List f1677b;

    /* renamed from: c */
    public final String f1678c;

    public atx(Class cls, Class cls2, Class cls3, List list, C0306lc lcVar) {
        this.f1676a = lcVar;
        this.f1677b = (List) cns.m4635a((Collection) list);
        String simpleName = cls.getSimpleName();
        String simpleName2 = cls2.getSimpleName();
        String simpleName3 = cls3.getSimpleName();
        int length = String.valueOf(simpleName).length();
        StringBuilder sb = new StringBuilder(length + 21 + String.valueOf(simpleName2).length() + String.valueOf(simpleName3).length());
        sb.append("Failed LoadPath{");
        sb.append(simpleName);
        sb.append("->");
        sb.append(simpleName2);
        sb.append("->");
        sb.append(simpleName3);
        sb.append("}");
        this.f1678c = sb.toString();
    }

    public final String toString() {
        String arrays = Arrays.toString(this.f1677b.toArray());
        StringBuilder sb = new StringBuilder(String.valueOf(arrays).length() + 22);
        sb.append("LoadPath{decodePaths=");
        sb.append(arrays);
        sb.append('}');
        return sb.toString();
    }
}
