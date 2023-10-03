package p000a.p001a.p002a.p004b;

import java.util.HashMap;
import java.util.Map;

/* renamed from: a.a.a.b.a */
public class C0006a extends C0013h {

    /* renamed from: Vn */
    private HashMap f3Vn = new HashMap();

    public boolean contains(Object obj) {
        return this.f3Vn.containsKey(obj);
    }

    /* renamed from: f */
    public Map.Entry mo8f(Object obj) {
        if (this.f3Vn.containsKey(obj)) {
            return ((C0009d) this.f3Vn.get(obj)).f4Qn;
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public C0009d get(Object obj) {
        return (C0009d) this.f3Vn.get(obj);
    }

    public Object putIfAbsent(Object obj, Object obj2) {
        C0009d dVar = (C0009d) this.f3Vn.get(obj);
        if (dVar != null) {
            return dVar.mValue;
        }
        this.f3Vn.put(obj, put(obj, obj2));
        return null;
    }

    public Object remove(Object obj) {
        Object remove = super.remove(obj);
        this.f3Vn.remove(obj);
        return remove;
    }
}
