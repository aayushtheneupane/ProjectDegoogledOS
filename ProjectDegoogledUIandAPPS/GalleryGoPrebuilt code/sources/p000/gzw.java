package p000;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executor;

/* renamed from: gzw */
/* compiled from: PG */
public final class gzw {

    /* renamed from: a */
    public final inw f12378a;

    /* renamed from: b */
    public final inw f12379b;

    /* renamed from: c */
    public final Executor f12380c;

    /* renamed from: d */
    public final int f12381d;

    /* renamed from: e */
    public final Set f12382e;

    /* renamed from: f */
    public final inw f12383f;

    /* renamed from: g */
    public final gzy f12384g;

    public gzw(inw inw, inw inw2, Executor executor, gzy gzy, int i, Set set, inw inw3) {
        this.f12378a = inw;
        this.f12379b = inw2;
        this.f12380c = executor;
        this.f12384g = gzy;
        this.f12381d = i;
        this.f12382e = set;
        this.f12383f = inw3;
    }

    /* renamed from: a */
    public static String[] m11094a(Set set, ifq ifq) {
        if (ifq != null) {
            Iterable[] iterableArr = {set, ifq.f14009d};
            for (int i = 0; i < 2; i++) {
                ife.m12898e((Object) iterableArr[i]);
            }
            Iterator it = new hrz(iterableArr).iterator();
            if (it.hasNext()) {
                Object next = it.next();
                if (!it.hasNext()) {
                    set = hto.m12120a(next);
                } else {
                    htm htm = new htm();
                    htm.mo7874b(next);
                    ife.m12898e((Object) it);
                    while (it.hasNext()) {
                        htm.mo7874b(it.next());
                    }
                    set = htm.mo7993a();
                }
            } else {
                set = hvf.f13465a;
            }
        }
        return (String[]) set.toArray(new String[set.size()]);
    }
}
