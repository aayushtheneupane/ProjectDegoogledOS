package p000;

import java.util.Collection;
import java.util.logging.Level;

/* renamed from: ifv */
/* compiled from: PG */
public final class ifv extends hxm {

    /* renamed from: b */
    private final Collection f14022b;

    public ifv(String str, Collection collection) {
        super(str);
        this.f14022b = collection;
    }

    /* renamed from: a */
    public final boolean mo7301a(Level level) {
        for (hxa a : this.f14022b) {
            if (a.mo7301a(level)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public final void mo7299a(hwz hwz) {
        for (hxa hxa : this.f14022b) {
            if (hwz.mo8216k() || hxa.mo7301a(hwz.mo8209d())) {
                hxa.mo7299a(hwz);
            }
        }
    }
}
