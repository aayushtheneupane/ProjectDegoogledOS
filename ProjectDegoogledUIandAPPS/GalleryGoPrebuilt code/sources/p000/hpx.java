package p000;

import java.util.Arrays;

/* renamed from: hpx */
/* compiled from: PG */
public final class hpx {

    /* renamed from: a */
    private final String f13240a;

    /* renamed from: b */
    private final hpw f13241b;

    /* renamed from: c */
    private hpw f13242c;

    public /* synthetic */ hpx(String str) {
        hpw hpw = new hpw((byte[]) null);
        this.f13241b = hpw;
        this.f13242c = hpw;
        this.f13240a = (String) ife.m12898e((Object) str);
    }

    /* renamed from: a */
    public final hpw mo7671a() {
        hpw hpw = new hpw((byte[]) null);
        this.f13242c.f13239c = hpw;
        this.f13242c = hpw;
        return hpw;
    }

    /* renamed from: a */
    public final void mo7672a(Object obj) {
        mo7671a().f13238b = obj;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(32);
        sb.append(this.f13240a);
        sb.append('{');
        hpw hpw = this.f13241b.f13239c;
        String str = "";
        while (hpw != null) {
            Object obj = hpw.f13238b;
            sb.append(str);
            String str2 = hpw.f13237a;
            if (str2 != null) {
                sb.append(str2);
                sb.append('=');
            }
            if (obj == null || !obj.getClass().isArray()) {
                sb.append(obj);
            } else {
                String deepToString = Arrays.deepToString(new Object[]{obj});
                sb.append(deepToString, 1, deepToString.length() - 1);
            }
            hpw = hpw.f13239c;
            str = ", ";
        }
        sb.append('}');
        return sb.toString();
    }
}
