package p000;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* renamed from: la */
/* compiled from: PG */
public abstract class C0304la {

    /* renamed from: a */
    public C0299kw f15182a;

    /* renamed from: b */
    public C0302kz f15183b;

    /* renamed from: c */
    private C0300kx f15184c;

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract int mo9184a();

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract int mo9185a(Object obj);

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract Object mo9186a(int i, int i2);

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract Object mo9187a(int i, Object obj);

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo9188a(int i);

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo9189a(Object obj, Object obj2);

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public abstract int mo9190b(Object obj);

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public abstract Map mo9191b();

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public abstract void mo9192c();

    /* renamed from: a */
    public static boolean m14562a(Set set, Object obj) {
        if (set == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set2 = (Set) obj;
            try {
                return set.size() == set2.size() && set.containsAll(set2);
            } catch (NullPointerException e) {
            } catch (ClassCastException e2) {
                return false;
            }
        }
        return false;
    }

    /* renamed from: d */
    public final Set mo9313d() {
        if (this.f15184c == null) {
            this.f15184c = new C0300kx(this);
        }
        return this.f15184c;
    }

    /* renamed from: a */
    public static boolean m14561a(Map map, Collection collection) {
        int size = map.size();
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            if (!collection.contains(it.next())) {
                it.remove();
            }
        }
        return size != map.size();
    }

    /* renamed from: b */
    public final Object[] mo9312b(int i) {
        int a = mo9184a();
        Object[] objArr = new Object[a];
        for (int i2 = 0; i2 < a; i2++) {
            objArr[i2] = mo9186a(i2, i);
        }
        return objArr;
    }

    /* renamed from: a */
    public final Object[] mo9311a(Object[] objArr, int i) {
        int a = mo9184a();
        if (objArr.length < a) {
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), a);
        }
        for (int i2 = 0; i2 < a; i2++) {
            objArr[i2] = mo9186a(i2, i);
        }
        if (objArr.length > a) {
            objArr[a] = null;
        }
        return objArr;
    }
}
