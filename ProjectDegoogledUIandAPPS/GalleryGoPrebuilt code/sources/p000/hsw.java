package p000;

import java.util.Map;

/* renamed from: hsw */
/* compiled from: PG */
abstract class hsw extends hto {
    /* renamed from: e */
    public abstract hsu mo7934e();

    /* renamed from: f */
    public final boolean mo7964f() {
        return false;
    }

    public final boolean contains(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object obj2 = mo7934e().get(entry.getKey());
            if (obj2 == null || !obj2.equals(entry.getValue())) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return mo7934e().hashCode();
    }

    /* renamed from: h */
    public final boolean mo7885h() {
        return mo7934e().mo7899c();
    }

    public final int size() {
        return mo7934e().size();
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new hsv(mo7934e());
    }
}
