package p000;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* renamed from: ham */
/* compiled from: PG */
public final class ham {

    /* renamed from: a */
    public final Set f12413a = new HashSet();

    /* renamed from: b */
    public final Set f12414b = new HashSet();

    /* renamed from: c */
    public final Map f12415c = new HashMap();

    /* renamed from: d */
    public final hpy f12416d;

    /* renamed from: e */
    public boolean f12417e = false;

    public ham(hpy hpy) {
        this.f12416d = hpy;
    }

    /* renamed from: a */
    public final boolean mo7250a() {
        for (gyc a : this.f12415c.values()) {
            if (a.mo7208a()) {
                return true;
            }
        }
        return false;
    }
}
