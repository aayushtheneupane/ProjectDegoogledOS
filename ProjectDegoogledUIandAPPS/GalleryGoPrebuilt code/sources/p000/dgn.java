package p000;

import java.io.IOException;
import java.util.List;

/* renamed from: dgn */
/* compiled from: PG */
final /* synthetic */ class dgn implements icf {

    /* renamed from: a */
    public static final icf f6507a = new dgn();

    private dgn() {
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        List list = (List) obj;
        if (list.size() == 1) {
            return ife.m12820a((Object) (cxi) list.get(0));
        }
        int size = list.size();
        StringBuilder sb = new StringBuilder(69);
        sb.append("MediaCodecVideoTrimmer: mediaList.size() expected 1 found ");
        sb.append(size);
        throw cca.m4025a(new IOException(sb.toString()));
    }
}
