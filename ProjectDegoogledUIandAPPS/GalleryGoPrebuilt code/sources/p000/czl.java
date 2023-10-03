package p000;

import java.io.File;
import java.util.List;
import p003j$.util.Optional;

/* renamed from: czl */
/* compiled from: PG */
final /* synthetic */ class czl implements hpr {

    /* renamed from: a */
    private final List f6105a;

    public czl(List list) {
        this.f6105a = list;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        List list = this.f6105a;
        List list2 = (List) obj;
        int i = czu.f6115a;
        if (list2.size() != list.size()) {
            cwn.m5514b("DeleterModule: Expect documentFiles to have the same size with files. Actual %s, %s.", Integer.valueOf(list2.size()), Integer.valueOf(list.size()));
            return hso.m12047f();
        }
        hsj j = hso.m12048j();
        for (int i2 = 0; i2 < list.size(); i2++) {
            j.mo7908c(new cyy((File) list.get(i2), (Optional) list2.get(i2)));
        }
        return j.mo7905a();
    }
}
