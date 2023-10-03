package p000;

import com.google.android.apps.photosgo.media.Filter$Category;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import p003j$.util.Optional;

/* renamed from: bnm */
/* compiled from: PG */
final /* synthetic */ class bnm implements icf {

    /* renamed from: a */
    private final bnq f3199a;

    public bnm(bnq bnq) {
        this.f3199a = bnq;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        ieh ieh;
        Filter$Category filter$Category;
        bnq bnq = this.f3199a;
        List<cxd> list = (List) obj;
        bnk bnk = bnq.f3204a;
        ArrayList arrayList = new ArrayList(list.size());
        Optional empty = Optional.empty();
        for (cxd cxd : list) {
            Filter$Category filter$Category2 = Filter$Category.PEOPLE;
            if (cxd.f5887b == 2) {
                filter$Category = Filter$Category.forNumber(((Integer) cxd.f5888c).intValue());
                if (filter$Category == null) {
                    filter$Category = Filter$Category.UNKNOWN_CATEGORY;
                }
            } else {
                filter$Category = Filter$Category.UNKNOWN_CATEGORY;
            }
            if (!filter$Category2.equals(filter$Category)) {
                arrayList.add(cxd);
            } else {
                empty = Optional.m16285of(cxd);
            }
        }
        if (!empty.isPresent()) {
            ieh = ife.m12820a((Object) Optional.empty());
        } else {
            hgn hgn = new hgn();
            hgn.mo7409a(" SELECT b, c, ");
            hgn.mo7409a(" SUM( tf_g ) AS tf_f");
            hgn.mo7409a(" FROM mt");
            hgn.mo7409a(" JOIN (");
            hgn.mo7409a(" SELECT c AS tf_c, h AS tf_d");
            hgn.mo7409a(" FROM ft )");
            hgn.mo7409a(" ON mt.a = tf_c");
            hgn.mo7409a(" JOIN ( ");
            hgn.mo7409a(" SELECT h AS tf_e, ");
            hgn.mo7409a(" COUNT(DISTINCT c) AS tf_g");
            hgn.mo7409a(" FROM ft");
            hgn.mo7409a(" GROUP BY h");
            hgn.mo7409a(" HAVING h IS NOT NULL) ");
            hgn.mo7409a(" ON tf_d = tf_e");
            hgn.mo7409a(" GROUP BY mt.a");
            hgn.mo7409a(" HAVING a IN (");
            hgn.mo7409a(" SELECT DISTINCT c");
            hgn.mo7409a(" FROM ft");
            hgn.mo7409a(" WHERE h IN (");
            hgn.mo7409a(" SELECT h FROM( ");
            hgn.mo7409a(" SELECT h, COUNT(DISTINCT c) AS tf_g");
            hgn.mo7409a(" FROM ft");
            hgn.mo7409a(" GROUP BY h");
            hgn.mo7409a(" HAVING tf_g >= 3 AND h IS NOT NULL )))");
            hgn.mo7409a(" ORDER BY tf_f DESC, a DESC ");
            hgn.mo7409a(" LIMIT 1");
            hlj a = hnb.m11765a("Get canonical people media");
            try {
                ieh = a.mo7548a(bnk.f3197a.mo2655a(hgn.mo7407a(), bne.f3188a).mo6899b());
                if (a != null) {
                    a.close();
                }
            } catch (Throwable th) {
                ifn.m12935a(th, th);
            }
        }
        hgn hgn2 = new hgn();
        int i = 0;
        while (i < arrayList.size()) {
            hgn2.mo7409a("SELECT * FROM (");
            hgn2.mo7409a("SELECT a, i, b, c, ? AS sq FROM mt");
            hgn2.mo7411b(String.valueOf(i));
            dcm.m5894a(hgn2, (cxd) arrayList.get(i));
            hgn2.mo7409a(" ORDER BY i DESC, a DESC");
            hgn2.mo7409a(" LIMIT ?) ");
            int i2 = i + 1;
            hgn2.mo7411b(String.valueOf(i2));
            if (i < arrayList.size() - 1) {
                hgn2.mo7409a(" UNION ");
            }
            i = i2;
        }
        hgn2.mo7409a(" ORDER BY i DESC, a DESC");
        ieh b = bnk.f3197a.mo2655a(hgn2.mo7407a(), new bnf(arrayList)).mo6899b();
        return gte.m10770a(gte.m10769a(ieh, b).mo7613a((Callable) new bng(list, ieh, empty, b), (Executor) idh.f13918a), bnp.f3202a, (Executor) bnq.f3205b);
        throw th;
    }
}
