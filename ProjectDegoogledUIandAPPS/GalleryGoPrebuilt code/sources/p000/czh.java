package p000;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/* renamed from: czh */
/* compiled from: PG */
final /* synthetic */ class czh implements Callable {

    /* renamed from: a */
    private final List f6100a;

    /* renamed from: b */
    private final dip f6101b;

    public czh(List list, dip dip) {
        this.f6100a = list;
        this.f6101b = dip;
    }

    public final Object call() {
        List<cyd> list = this.f6100a;
        dip dip = this.f6101b;
        ArrayList arrayList = new ArrayList();
        for (cyd cyd : list) {
            if (dip.mo4156a(cyd.f5989c)) {
                arrayList.add(cyd);
            }
        }
        return arrayList;
    }
}
