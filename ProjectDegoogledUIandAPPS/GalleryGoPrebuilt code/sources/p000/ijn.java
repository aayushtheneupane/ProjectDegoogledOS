package p000;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* renamed from: ijn */
/* compiled from: PG */
public final class ijn extends ihd implements RandomAccess, ijo {

    /* renamed from: b */
    private final List f14353b;

    static {
        new ijn().mo8526b();
    }

    public ijn() {
        this(10);
    }

    public ijn(int i) {
        this(new ArrayList(i));
    }

    private ijn(ArrayList arrayList) {
        this.f14353b = arrayList;
    }

    public final /* bridge */ /* synthetic */ void add(int i, Object obj) {
        mo8527c();
        this.f14353b.add(i, (String) obj);
        this.modCount++;
    }

    /* renamed from: a */
    public final void mo8817a(ihw ihw) {
        mo8527c();
        this.f14353b.add(ihw);
        this.modCount++;
    }

    public final boolean addAll(int i, Collection collection) {
        mo8527c();
        if (collection instanceof ijo) {
            collection = ((ijo) collection).mo8820d();
        }
        boolean addAll = this.f14353b.addAll(i, collection);
        this.modCount++;
        return addAll;
    }

    public final boolean addAll(Collection collection) {
        return addAll(size(), collection);
    }

    /* renamed from: a */
    private static String m13665a(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof ihw) {
            return ((ihw) obj).mo8626k();
        }
        return ijf.m13652b((byte[]) obj);
    }

    public final void clear() {
        mo8527c();
        this.f14353b.clear();
        this.modCount++;
    }

    /* renamed from: b */
    public final String get(int i) {
        Object obj = this.f14353b.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof ihw) {
            ihw ihw = (ihw) obj;
            String k = ihw.mo8626k();
            if (ihw.mo8612e()) {
                this.f14353b.set(i, k);
            }
            return k;
        }
        byte[] bArr = (byte[]) obj;
        String b = ijf.m13652b(bArr);
        if (ijf.m13651a(bArr)) {
            this.f14353b.set(i, b);
        }
        return b;
    }

    /* renamed from: c */
    public final Object mo8819c(int i) {
        return this.f14353b.get(i);
    }

    /* renamed from: d */
    public final List mo8820d() {
        return Collections.unmodifiableList(this.f14353b);
    }

    /* renamed from: e */
    public final ijo mo8821e() {
        return this.f14175a ? new ilp(this) : this;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ ije mo8585a(int i) {
        if (i >= size()) {
            ArrayList arrayList = new ArrayList(i);
            arrayList.addAll(this.f14353b);
            return new ijn(arrayList);
        }
        throw new IllegalArgumentException();
    }

    public final /* bridge */ /* synthetic */ Object remove(int i) {
        mo8527c();
        Object remove = this.f14353b.remove(i);
        this.modCount++;
        return m13665a(remove);
    }

    public final /* bridge */ /* synthetic */ Object set(int i, Object obj) {
        mo8527c();
        return m13665a(this.f14353b.set(i, (String) obj));
    }

    public final int size() {
        return this.f14353b.size();
    }
}
