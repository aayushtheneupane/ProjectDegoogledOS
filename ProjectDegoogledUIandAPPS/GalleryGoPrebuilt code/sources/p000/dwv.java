package p000;

import android.util.ArraySet;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* renamed from: dwv */
/* compiled from: PG */
public abstract class dwv implements cpt {
    /* renamed from: a */
    public abstract List mo4477a();

    /* renamed from: b */
    public abstract dwu mo4478b();

    /* renamed from: c */
    public abstract dvs mo4482c();

    /* renamed from: d */
    public abstract Object mo4486d();

    /* renamed from: e */
    public abstract cxi mo4490e();

    /* renamed from: f */
    public abstract Object mo4495f();

    /* renamed from: g */
    public abstract dzf mo4499g();

    /* renamed from: a */
    public static Set m6830a(Collection collection) {
        ArraySet arraySet = new ArraySet();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            cpt cpt = (cpt) it.next();
            if (cpt instanceof dwv) {
                dwv dwv = (dwv) cpt;
                if (dwu.MEDIA.equals(dwv.mo4478b())) {
                    arraySet.add(dwv.mo4490e());
                }
            }
        }
        return arraySet;
    }

    /* renamed from: a */
    public static Set m6831a(Collection collection, boolean z) {
        ArraySet arraySet = new ArraySet();
        for (cxi cxi : m6830a(collection)) {
            if (z && cxi.f5929u.size() != 0) {
                ije ije = cxi.f5929u;
                int size = ije.size();
                for (int i = 0; i < size; i++) {
                    arraySet.add(cyc.m5648b((cxi) ije.get(i)));
                }
            } else {
                arraySet.add(cyc.m5648b(cxi));
            }
        }
        return arraySet;
    }

    /* renamed from: h */
    public final boolean mo4529h() {
        return dwu.MEDIA.equals(mo4478b());
    }

    /* renamed from: j */
    public static dwv m6833j() {
        return new dvj(new Object());
    }

    /* renamed from: i */
    public static dwv m6832i() {
        return new dvl(new Object());
    }

    /* renamed from: k */
    public final Object mo4530k() {
        dwu dwu = dwu.MEDIA;
        int ordinal = mo4478b().ordinal();
        if (ordinal == 0) {
            return Long.valueOf(cyc.m5641a(mo4490e()));
        }
        if (ordinal == 1) {
            return dwu.MEDIA_PLACEHOLDER;
        }
        if (ordinal == 2) {
            return mo4482c().f7463b;
        }
        if (ordinal == 3) {
            return dwu.HEADER_PLACEHOLDER;
        }
        if (ordinal == 4) {
            return Integer.valueOf(mo4499g().mo4613f());
        }
        if (ordinal == 5) {
            return dwu.CATEGORIES;
        }
        String valueOf = String.valueOf(mo4478b());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 26);
        sb.append("Kind ");
        sb.append(valueOf);
        sb.append(" isn't accounted for.");
        throw new IllegalStateException(sb.toString());
    }
}
