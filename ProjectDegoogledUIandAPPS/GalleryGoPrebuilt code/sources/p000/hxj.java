package p000;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

/* renamed from: hxj */
/* compiled from: PG */
public final class hxj {

    /* renamed from: a */
    public static final hxj f13581a = new hxj(Collections.unmodifiableSortedMap(new TreeMap()));

    /* renamed from: b */
    public final SortedMap f13582b;

    /* renamed from: c */
    private Integer f13583c = null;

    /* renamed from: d */
    private String f13584d = null;

    static {
        Collections.unmodifiableSortedSet(new TreeSet());
    }

    private hxj(SortedMap sortedMap) {
        this.f13582b = sortedMap;
    }

    /* renamed from: a */
    public final void mo8254a(hwy hwy) {
        for (Map.Entry entry : this.f13582b.entrySet()) {
            String str = (String) entry.getKey();
            Set<Object> set = (Set) entry.getValue();
            if (!set.isEmpty()) {
                for (Object a : set) {
                    ((hwx) hwy).mo8239a(str, a);
                }
            } else {
                ((hwx) hwy).mo8239a(str, (Object) null);
            }
        }
    }

    public final boolean equals(Object obj) {
        return (obj instanceof hxj) && ((hxj) obj).f13582b.equals(this.f13582b);
    }

    public final int hashCode() {
        if (this.f13583c == null) {
            this.f13583c = Integer.valueOf(this.f13582b.hashCode());
        }
        return this.f13583c.intValue();
    }

    public final String toString() {
        if (this.f13584d == null) {
            StringBuilder sb = new StringBuilder();
            hwx hwx = new hwx("[ ", " ]", sb);
            mo8254a(hwx);
            hwx.mo8238a();
            this.f13584d = sb.toString();
        }
        return this.f13584d;
    }
}
