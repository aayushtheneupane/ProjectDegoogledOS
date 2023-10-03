package p000;

import java.util.ArrayList;

/* renamed from: gbn */
/* compiled from: PG */
public final class gbn {

    /* renamed from: a */
    public final ArrayList f10843a = new ArrayList();

    /* renamed from: b */
    private final StringBuilder f10844b = new StringBuilder();

    /* renamed from: a */
    public final void mo6390a(String str) {
        this.f10844b.append(str);
    }

    /* renamed from: a */
    public final gbm mo6389a() {
        String sb = this.f10844b.toString();
        ArrayList arrayList = this.f10843a;
        return new gbm(sb, arrayList.toArray(new Object[arrayList.size()]));
    }
}
