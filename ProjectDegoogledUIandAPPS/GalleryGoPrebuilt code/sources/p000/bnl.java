package p000;

import android.support.p002v7.widget.RecyclerView;
import com.google.android.apps.photosgo.media.Filter$Category;
import java.util.ArrayList;
import java.util.concurrent.Executor;

/* renamed from: bnl */
/* compiled from: PG */
final /* synthetic */ class bnl implements ice {

    /* renamed from: a */
    private final bnq f3198a;

    public bnl(bnq bnq) {
        this.f3198a = bnq;
    }

    /* renamed from: a */
    public final ieh mo2539a() {
        Filter$Category filter$Category;
        Filter$Category filter$Category2;
        ieh ieh;
        bnq bnq = this.f3198a;
        ArrayList arrayList = new ArrayList();
        hvs i = bnq.f3203o.listIterator();
        while (i.hasNext()) {
            cxd cxd = (cxd) i.next();
            cjm cjm = cjm.UNKNOWN;
            Filter$Category filter$Category3 = Filter$Category.UNKNOWN_CATEGORY;
            if (cxd.f5887b == 2) {
                filter$Category = Filter$Category.forNumber(((Integer) cxd.f5888c).intValue());
                if (filter$Category == null) {
                    filter$Category = Filter$Category.UNKNOWN_CATEGORY;
                }
            } else {
                filter$Category = Filter$Category.UNKNOWN_CATEGORY;
            }
            switch (filter$Category.ordinal()) {
                case 0:
                    ieh = ife.m12820a((Object) false);
                    break;
                case 1:
                    ieh = gte.m10771a(bnq.f3215l.mo4812a(), (icf) new bno(bnq), (Executor) idh.f13918a);
                    break;
                case RecyclerView.SCROLL_STATE_SETTLING:
                    ieh = ife.m12820a((Object) Boolean.valueOf(bnq.f3207d.mo3175a()));
                    break;
                case 3:
                    ieh = ife.m12820a((Object) Boolean.valueOf(bnq.f3208e.mo3175a()));
                    break;
                case 4:
                    ieh = ife.m12820a((Object) Boolean.valueOf(bnq.f3210g.mo3175a()));
                    break;
                case 5:
                    ieh = ife.m12820a((Object) Boolean.valueOf(bnq.f3211h.mo3175a()));
                    break;
                case 6:
                    ieh = ife.m12820a((Object) Boolean.valueOf(bnq.f3212i.mo3175a()));
                    break;
                case 7:
                    ieh = ife.m12820a((Object) Boolean.valueOf(bnq.f3213j.mo3175a()));
                    break;
                case 8:
                    ieh = ife.m12820a((Object) Boolean.valueOf(bnq.f3214k.mo3175a()));
                    break;
                case 9:
                    ieh = ife.m12820a((Object) Boolean.valueOf(bnq.f3209f.mo3175a()));
                    break;
                default:
                    if (cxd.f5887b != 2 || (filter$Category2 = Filter$Category.forNumber(((Integer) cxd.f5888c).intValue())) == null) {
                        filter$Category2 = Filter$Category.UNKNOWN_CATEGORY;
                    }
                    String name = filter$Category2.name();
                    StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 27);
                    sb.append("Filter [");
                    sb.append(name);
                    sb.append("] does not exist...");
                    throw new IllegalArgumentException(sb.toString());
            }
            arrayList.add(ieh);
        }
        return gte.m10771a(gte.m10770a(ife.m12819a((Iterable) arrayList), bnn.f3200a, (Executor) idh.f13918a), (icf) new bnm(bnq), (Executor) idh.f13918a);
    }
}
