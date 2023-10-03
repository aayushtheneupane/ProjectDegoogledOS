package p000;

import android.text.TextUtils;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: axf */
/* compiled from: PG */
public final class axf implements axb {

    /* renamed from: b */
    private final Map f1819b;

    /* renamed from: c */
    private volatile Map f1820c;

    public axf(Map map) {
        this.f1819b = Collections.unmodifiableMap(map);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof axf) {
            return this.f1819b.equals(((axf) obj).f1819b);
        }
        return false;
    }

    /* renamed from: a */
    public final Map mo1705a() {
        if (this.f1820c == null) {
            synchronized (this) {
                if (this.f1820c == null) {
                    HashMap hashMap = new HashMap();
                    for (Map.Entry entry : this.f1819b.entrySet()) {
                        List list = (List) entry.getValue();
                        StringBuilder sb = new StringBuilder();
                        int size = list.size();
                        for (int i = 0; i < size; i++) {
                            String a = ((axc) list.get(i)).mo1706a();
                            if (!TextUtils.isEmpty(a)) {
                                sb.append(a);
                                if (i != list.size() - 1) {
                                    sb.append(',');
                                }
                            }
                        }
                        String sb2 = sb.toString();
                        if (!TextUtils.isEmpty(sb2)) {
                            hashMap.put((String) entry.getKey(), sb2);
                        }
                    }
                    this.f1820c = Collections.unmodifiableMap(hashMap);
                }
            }
        }
        return this.f1820c;
    }

    public final int hashCode() {
        return this.f1819b.hashCode();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f1819b);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 21);
        sb.append("LazyHeaders{headers=");
        sb.append(valueOf);
        sb.append('}');
        return sb.toString();
    }
}
