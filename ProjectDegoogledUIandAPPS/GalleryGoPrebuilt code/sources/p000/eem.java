package p000;

import java.util.List;

/* renamed from: eem */
/* compiled from: PG */
final /* synthetic */ class eem implements hga {

    /* renamed from: a */
    private final List f8105a;

    public eem(List list) {
        this.f8105a = list;
    }

    /* renamed from: a */
    public final void mo2584a(hfz hfz) {
        for (edw edw : this.f8105a) {
            hfz.mo7386a("at", edw.mo4737l(), "activity_class_name = ?", String.valueOf(edw.mo4729c()));
        }
    }
}
