package p000;

import android.content.Context;
import android.net.Uri;
import android.provider.MediaStore;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* renamed from: ddf */
/* compiled from: PG */
public final class ddf {

    /* renamed from: a */
    public static /* synthetic */ int f6327a;

    /* renamed from: b */
    private static final Uri f6328b = MediaStore.Files.getContentUri("external");

    /* renamed from: a */
    static ieh m5948a(List list, gtt gtt, iek iek) {
        List<List> list2;
        ArrayList f = ife.m12904f();
        ife.m12898e((Object) list);
        ife.m12890c(true);
        if (list instanceof RandomAccess) {
            list2 = new hua(list);
        } else {
            list2 = new htz(list);
        }
        for (List<cyd> it : list2) {
            ArrayList f2 = ife.m12904f();
            for (cyd cyd : it) {
                f2.add(Long.valueOf(cyd.f5989c));
            }
            hgn hgn = new hgn();
            hgn.mo7409a("_id IN(?");
            hgn.mo7411b(String.valueOf(f2.get(0)));
            for (int i = 1; i < f2.size(); i++) {
                hgn.mo7409a(", ?");
                hgn.mo7411b(String.valueOf(f2.get(i)));
            }
            hgn.mo7409a(")");
            hgm a = hgn.mo7407a();
            f.add(gtt.mo7041a(f6328b, new String[]{"_data"}, a, (String) null).mo6897b(ddd.f6325a, (Executor) iek).mo6899b());
        }
        return gte.m10770a(ife.m12819a((Iterable) f), dde.f6326a, (Executor) idh.f13918a);
    }

    /* renamed from: a */
    static ieh m5947a(Context context, efu efu, cjr cjr, List list, List list2, iek iek) {
        return iek.mo5933a(hmq.m11749a((Callable) new ddc(context, list, list2, cjr, efu)));
    }

    /* renamed from: a */
    public static List m5949a(List list) {
        ArrayList f = ife.m12904f();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            f.addAll((List) it.next());
        }
        return f;
    }
}
