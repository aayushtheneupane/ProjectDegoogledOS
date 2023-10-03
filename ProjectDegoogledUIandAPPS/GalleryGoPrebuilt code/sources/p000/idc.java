package p000;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: idc */
/* compiled from: PG */
final class idc extends ibz {

    /* renamed from: g */
    private List f13908g;

    public idc(hsf hsf, boolean z) {
        super(hsf, z, true);
        List list;
        if (!hsf.isEmpty()) {
            list = ife.m12886c(hsf.size());
        } else {
            list = hso.m12047f();
        }
        this.f13908g = list;
        for (int i = 0; i < hsf.size(); i++) {
            this.f13908g.add((Object) null);
        }
        mo8368f();
    }

    /* renamed from: a */
    public final void mo8363a(int i, Object obj) {
        List list = this.f13908g;
        if (list != null) {
            list.set(i, hpy.m11894c(obj));
        }
    }

    /* renamed from: g */
    public final void mo8369g() {
        List<hpy> list = this.f13908g;
        if (list != null) {
            ArrayList c = ife.m12886c(list.size());
            for (hpy hpy : list) {
                c.add(hpy != null ? hpy.mo7648c() : null);
            }
            mo8346b((Object) Collections.unmodifiableList(c));
        }
    }

    /* renamed from: a */
    public final void mo8366a(iby iby) {
        super.mo8366a(iby);
        this.f13908g = null;
    }
}
