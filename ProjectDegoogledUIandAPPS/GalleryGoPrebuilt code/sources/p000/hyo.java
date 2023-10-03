package p000;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

/* renamed from: hyo */
/* compiled from: PG */
public final class hyo extends hym {

    /* renamed from: c */
    private static final Map f13656c;

    /* renamed from: d */
    private final hwu f13657d;

    static {
        EnumMap enumMap = new EnumMap(hwu.class);
        for (hwu hwu : hwu.values()) {
            hyo[] hyoArr = new hyo[10];
            for (int i = 0; i < 10; i++) {
                hyoArr[i] = new hyo(i, hwu, hwv.f13551a);
            }
            enumMap.put(hwu, hyoArr);
        }
        f13656c = Collections.unmodifiableMap(enumMap);
    }

    private hyo(int i, hwu hwu, hwv hwv) {
        super(hwv, i);
        this.f13657d = (hwu) ife.m12827a((Object) hwu, "format char");
        if (!hwv.mo8233a()) {
            char c = hwu.f13547d;
            c = hwv.mo8235b() ? c & 65503 : c;
            StringBuilder a = hwv.mo8232a(new StringBuilder("%"));
            a.append((char) c);
            a.toString();
        }
    }

    /* renamed from: a */
    public final void mo8271a(hyn hyn, Object obj) {
        hyn.mo8253a(obj, this.f13657d, this.f13655b);
    }

    /* renamed from: a */
    public static hyo m12467a(int i, hwu hwu, hwv hwv) {
        return (i < 10 && hwv.mo8233a()) ? ((hyo[]) f13656c.get(hwu))[i] : new hyo(i, hwu, hwv);
    }
}
