package p000;

import java.util.Map;

/* renamed from: gye */
/* compiled from: PG */
public final /* synthetic */ class gye implements gzy {

    /* renamed from: a */
    private final Map f12300a;

    /* renamed from: b */
    private final Map f12301b;

    public gye(Map map, Map map2) {
        this.f12300a = map;
        this.f12301b = map2;
    }

    /* renamed from: a */
    public final String mo7210a(String str) {
        boolean z;
        Map map = this.f12300a;
        Map map2 = this.f12301b;
        if (map.containsKey(str)) {
            return str;
        }
        String str2 = (String) map2.get(str);
        if (str2 != null) {
            z = true;
        } else {
            z = false;
        }
        ife.m12849a(z, "Unknown package %s", (Object) str);
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 1 + String.valueOf(str2).length());
        sb.append(str);
        sb.append("#");
        sb.append(str2);
        return sb.toString();
    }
}
