package p000;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* renamed from: bfl */
/* compiled from: PG */
public class bfl {

    /* renamed from: a */
    private final Map f2211a = new LinkedHashMap(100, 0.75f, true);

    /* renamed from: b */
    private final long f2212b;

    /* renamed from: c */
    private long f2213c;

    public bfl(long j) {
        this.f2212b = j;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public int mo1670a(Object obj) {
        return 1;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo1673a(Object obj, Object obj2) {
    }

    /* renamed from: b */
    public final synchronized long mo1956b() {
        return this.f2212b;
    }

    /* renamed from: a */
    public final void mo1954a() {
        mo1955a(0);
    }

    /* renamed from: b */
    public final synchronized Object mo1957b(Object obj) {
        return this.f2211a.get(obj);
    }

    /* renamed from: b */
    public final synchronized Object mo1958b(Object obj, Object obj2) {
        long a = (long) mo1670a(obj2);
        if (a >= this.f2212b) {
            mo1673a(obj, obj2);
            return null;
        }
        if (obj2 != null) {
            this.f2213c += a;
        }
        Object put = this.f2211a.put(obj, obj2);
        if (put != null) {
            this.f2213c -= (long) mo1670a(put);
            if (!put.equals(obj2)) {
                mo1673a(obj, put);
            }
        }
        mo1955a(this.f2212b);
        return put;
    }

    /* renamed from: c */
    public final synchronized Object mo1959c(Object obj) {
        Object remove;
        remove = this.f2211a.remove(obj);
        if (remove != null) {
            this.f2213c -= (long) mo1670a(remove);
        }
        return remove;
    }

    /* renamed from: a */
    public final synchronized void mo1955a(long j) {
        while (this.f2213c > j) {
            Iterator it = this.f2211a.entrySet().iterator();
            Map.Entry entry = (Map.Entry) it.next();
            Object value = entry.getValue();
            this.f2213c -= (long) mo1670a(value);
            Object key = entry.getKey();
            it.remove();
            mo1673a(key, value);
        }
    }
}
