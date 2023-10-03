package p000;

import java.util.ArrayList;

/* renamed from: hgl */
/* compiled from: PG */
public final class hgl {

    /* renamed from: a */
    private final gbl f12698a;

    private hgl(gbl gbl) {
        this.f12698a = gbl;
    }

    /* renamed from: b */
    public final void mo7404b(String str) {
        this.f12698a.f10839b.append(str);
    }

    /* renamed from: c */
    public final void mo7405c(String str) {
        this.f12698a.f10840c.add(str);
    }

    /* renamed from: a */
    public final hgk mo7403a() {
        gbl gbl = this.f12698a;
        String str = gbl.f10838a;
        String sb = gbl.f10839b.toString();
        ArrayList arrayList = gbl.f10840c;
        return new hgk(new gbk(str, sb, (String[]) arrayList.toArray(new String[arrayList.size()])));
    }

    /* renamed from: a */
    public static hgl m11446a(String str) {
        return new hgl(new gbl(str));
    }
}
