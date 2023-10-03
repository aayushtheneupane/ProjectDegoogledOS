package p000;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: ijp */
/* compiled from: PG */
final class ijp extends ijr {

    /* renamed from: c */
    private static final Class f14354c = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private ijp() {
    }

    public /* synthetic */ ijp(byte[] bArr) {
    }

    /* renamed from: c */
    private static List m13677c(Object obj, long j) {
        return (List) ilv.m14048f(obj, j);
    }

    /* renamed from: b */
    public final void mo8826b(Object obj, long j) {
        Object obj2;
        List list = (List) ilv.m14048f(obj, j);
        if (list instanceof ijo) {
            obj2 = ((ijo) list).mo8821e();
        } else if (f14354c.isAssignableFrom(list.getClass())) {
            return;
        } else {
            if (!(list instanceof iko) || !(list instanceof ije)) {
                obj2 = Collections.unmodifiableList(list);
            } else {
                ije ije = (ije) list;
                if (ije.mo8521a()) {
                    ije.mo8526b();
                    return;
                }
                return;
            }
        }
        ilv.m14036a(obj, j, obj2);
    }

    /* renamed from: a */
    public final void mo8825a(Object obj, Object obj2, long j) {
        List c = m13677c(obj2, j);
        List a = m13676a(obj, j, c.size());
        int size = a.size();
        int size2 = c.size();
        if (size > 0 && size2 > 0) {
            a.addAll(c);
        }
        if (size > 0) {
            c = a;
        }
        ilv.m14036a(obj, j, (Object) c);
    }

    /* renamed from: a */
    public final List mo8824a(Object obj, long j) {
        return m13676a(obj, j, 10);
    }

    /* renamed from: a */
    private static List m13676a(Object obj, long j, int i) {
        List list;
        List c = m13677c(obj, j);
        if (c.isEmpty()) {
            if (c instanceof ijo) {
                list = new ijn(i);
            } else if (!(c instanceof iko) || !(c instanceof ije)) {
                list = new ArrayList(i);
            } else {
                list = ((ije) c).mo8585a(i);
            }
            ilv.m14036a(obj, j, (Object) list);
            return list;
        } else if (f14354c.isAssignableFrom(c.getClass())) {
            ArrayList arrayList = new ArrayList(c.size() + i);
            arrayList.addAll(c);
            ilv.m14036a(obj, j, (Object) arrayList);
            return arrayList;
        } else if (c instanceof ilp) {
            ijn ijn = new ijn(c.size() + i);
            ijn.addAll((ilp) c);
            ilv.m14036a(obj, j, (Object) ijn);
            return ijn;
        } else if (!(c instanceof iko) || !(c instanceof ije)) {
            return c;
        } else {
            ije ije = (ije) c;
            if (ije.mo8521a()) {
                return c;
            }
            ije a = ije.mo8585a(c.size() + i);
            ilv.m14036a(obj, j, (Object) a);
            return a;
        }
    }
}
