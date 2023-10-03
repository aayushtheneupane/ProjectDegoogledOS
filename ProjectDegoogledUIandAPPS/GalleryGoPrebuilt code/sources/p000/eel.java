package p000;

import java.util.List;

/* renamed from: eel */
/* compiled from: PG */
final /* synthetic */ class eel implements hga {

    /* renamed from: a */
    private final List f8104a;

    public eel(List list) {
        this.f8104a = list;
    }

    /* renamed from: a */
    public final void mo2584a(hfz hfz) {
        List list = this.f8104a;
        if (!list.isEmpty()) {
            hgl a = hgl.m11446a("at");
            a.mo7404b("activity_class_name IN (?");
            a.mo7405c((String) list.get(0));
            for (int i = 1; i < list.size(); i++) {
                a.mo7404b(", ?");
                a.mo7405c((String) list.get(i));
            }
            a.mo7404b(")");
            hfz.mo7387a(a.mo7403a());
        }
    }
}
