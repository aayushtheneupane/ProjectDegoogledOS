package p000;

import java.util.Iterator;
import java.util.Map;

/* renamed from: hsy */
/* compiled from: PG */
final class hsy extends htq {

    /* renamed from: a */
    private final hsu f13363a;

    public hsy(hsu hsu) {
        this.f13363a = hsu;
    }

    /* renamed from: h */
    public final boolean mo7885h() {
        return true;
    }

    public final boolean contains(Object obj) {
        return this.f13363a.containsKey(obj);
    }

    /* renamed from: a */
    public final Object mo7968a(int i) {
        return ((Map.Entry) this.f13363a.entrySet().mo7884g().get(i)).getKey();
    }

    /* renamed from: a */
    public final hvr mo7876a() {
        return this.f13363a.mo7897a();
    }

    public final /* bridge */ /* synthetic */ Iterator iterator() {
        return iterator();
    }

    public final int size() {
        return this.f13363a.size();
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new hsx(this.f13363a);
    }
}
