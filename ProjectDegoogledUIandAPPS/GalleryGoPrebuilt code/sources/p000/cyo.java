package p000;

import java.util.Iterator;
import java.util.List;

/* renamed from: cyo */
/* compiled from: PG */
final /* synthetic */ class cyo implements hga {

    /* renamed from: a */
    private final List f6042a;

    public cyo(List list) {
        this.f6042a = list;
    }

    /* renamed from: a */
    public final void mo2584a(hfz hfz) {
        List list = this.f6042a;
        hgl a = hgl.m11446a("mt");
        a.mo7404b("a IN (");
        Iterator it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            i++;
            a.mo7404b("?");
            a.mo7405c(String.valueOf((Long) it.next()));
            if (it.hasNext() && i != 999) {
                a.mo7404b(",");
            } else {
                a.mo7404b(")");
                hfz.mo7387a(a.mo7403a());
                a = hgl.m11446a("mt");
                a.mo7404b("a IN (");
                i = 0;
            }
        }
    }
}
