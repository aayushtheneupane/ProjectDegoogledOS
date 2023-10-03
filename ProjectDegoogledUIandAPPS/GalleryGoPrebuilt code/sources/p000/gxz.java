package p000;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;

/* renamed from: gxz */
/* compiled from: PG */
public final class gxz implements gxw {

    /* renamed from: a */
    private final iqk f12279a;

    /* renamed from: b */
    private final Map f12280b;

    /* renamed from: c */
    private final gzy f12281c;

    /* renamed from: d */
    private final hpy f12282d;

    public gxz(hpy hpy, gzy gzy, iqk iqk, Map map) {
        this.f12282d = hpy;
        this.f12281c = gzy;
        this.f12279a = iqk;
        this.f12280b = map;
    }

    /* renamed from: b */
    private final gxy m11028b() {
        return (gxy) ((hqc) this.f12282d).f13250a;
    }

    /* renamed from: a */
    static ieh m11027a(List list) {
        return ife.m12866b((Iterable) list).mo8442a((ice) new gxx(list), (Executor) idh.f13918a);
    }

    /* renamed from: a */
    public final ieh mo7199a(String str) {
        String a = this.f12281c.mo7210a(str);
        gww gww = (gww) this.f12280b.get(a);
        boolean z = true;
        if (!(gww == gww.UI_DEVICE || gww == gww.DEVICE)) {
            z = false;
        }
        ife.m12879b(z, "Package %s was not a device package. Instead was %s", a, gww);
        return ((gyt) this.f12279a.mo2097a()).mo7214a(a);
    }

    /* renamed from: a */
    public final ieh mo7200a(String str, gkn gkn) {
        String a = this.f12281c.mo7210a(str);
        gww gww = (gww) this.f12280b.get(a);
        boolean z = true;
        if (!(gww == gww.UI_USER || gww == gww.USER)) {
            z = false;
        }
        ife.m12879b(z, "Package %s was not a user package. Instead was %s", a, gww);
        return m11028b().mo7174a(str, gkn);
    }

    /* renamed from: b */
    public final ieh mo7201b(String str) {
        String a = this.f12281c.mo7210a(str);
        gww gww = (gww) this.f12280b.get(a);
        if (gww == null) {
            String valueOf = String.valueOf(a);
            Log.w("ConfigurationUpdater", valueOf.length() == 0 ? new String("No Mendel package registered for ") : "No Mendel package registered for ".concat(valueOf));
            return ife.m12820a((Object) null);
        }
        int ordinal = gww.ordinal();
        if (ordinal != 0) {
            if (ordinal != 1) {
                if (ordinal != 2) {
                    if (ordinal != 3) {
                        throw new UnsupportedOperationException();
                    }
                }
            }
            return m11028b().mo7173a(a);
        }
        return ((gyt) this.f12279a.mo2097a()).mo7214a(a);
    }

    /* renamed from: a */
    public final ieh mo7198a() {
        Set<String> keySet = this.f12280b.keySet();
        ArrayList arrayList = new ArrayList(keySet.size());
        for (String b : keySet) {
            arrayList.add(mo7201b(b));
        }
        return m11027a((List) arrayList);
    }
}
