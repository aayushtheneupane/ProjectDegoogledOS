package p000;

import java.util.List;

/* renamed from: bpg */
/* compiled from: PG */
final /* synthetic */ class bpg implements icf {

    /* renamed from: a */
    public static final icf f3295a = new bpg();

    private bpg() {
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        List list = (List) obj;
        if (!list.isEmpty()) {
            return ife.m12820a((Object) (cxi) list.get(0));
        }
        throw new Exception("ImageCompressionJob: compressed image can not be saved.");
    }
}
