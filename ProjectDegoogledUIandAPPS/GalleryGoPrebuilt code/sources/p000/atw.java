package p000;

import java.util.HashMap;
import java.util.Map;

/* renamed from: atw */
/* compiled from: PG */
public final class atw {

    /* renamed from: a */
    private final Map f1674a = new HashMap();

    /* renamed from: b */
    private final Map f1675b = new HashMap();

    /* renamed from: a */
    public final Map mo1622a(boolean z) {
        return z ? this.f1675b : this.f1674a;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo1623a(aqu aqu, ato ato) {
        Map a = mo1622a(ato.f1636g);
        if (ato.equals(a.get(aqu))) {
            a.remove(aqu);
        }
    }
}
