package p000;

import java.util.ArrayList;
import java.util.List;

/* renamed from: eqo */
/* compiled from: PG */
public final class eqo {

    /* renamed from: a */
    private final List f8844a = new ArrayList();

    /* renamed from: b */
    private final Object f8845b;

    public /* synthetic */ eqo(Object obj) {
        this.f8845b = abj.m85a(obj);
    }

    /* renamed from: a */
    public final void mo5163a(String str, Object obj) {
        List list = this.f8844a;
        String str2 = (String) abj.m85a((Object) str);
        String valueOf = String.valueOf(obj);
        StringBuilder sb = new StringBuilder(str2.length() + 1 + String.valueOf(valueOf).length());
        sb.append(str2);
        sb.append("=");
        sb.append(valueOf);
        list.add(sb.toString());
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(100);
        sb.append(this.f8845b.getClass().getSimpleName());
        sb.append('{');
        int size = this.f8844a.size();
        for (int i = 0; i < size; i++) {
            sb.append((String) this.f8844a.get(i));
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
