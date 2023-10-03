package p000;

import java.util.Map;

/* renamed from: hta */
/* compiled from: PG */
final class hta extends hso {

    /* renamed from: a */
    private final /* synthetic */ hso f13367a;

    public hta(hso hso) {
        this.f13367a = hso;
    }

    /* renamed from: h */
    public final boolean mo7885h() {
        return true;
    }

    public final Object get(int i) {
        return ((Map.Entry) this.f13367a.get(i)).getValue();
    }

    public final int size() {
        return this.f13367a.size();
    }
}
