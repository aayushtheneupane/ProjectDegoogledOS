package p000;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

/* renamed from: bde */
/* compiled from: PG */
public final class bde {

    /* renamed from: a */
    public final Set f2078a = Collections.newSetFromMap(new WeakHashMap());

    /* renamed from: b */
    public final List f2079b = new ArrayList();

    /* renamed from: c */
    public boolean f2080c;

    /* renamed from: a */
    public final boolean mo1829a(bea bea) {
        boolean z = true;
        if (bea == null) {
            return true;
        }
        boolean remove = this.f2078a.remove(bea);
        if (!this.f2079b.remove(bea) && !remove) {
            z = false;
        }
        if (z) {
            bea.mo1880b();
        }
        return z;
    }

    public final String toString() {
        String obj = super.toString();
        int size = this.f2078a.size();
        boolean z = this.f2080c;
        StringBuilder sb = new StringBuilder(String.valueOf(obj).length() + 41);
        sb.append(obj);
        sb.append("{numRequests=");
        sb.append(size);
        sb.append(", isPaused=");
        sb.append(z);
        sb.append("}");
        return sb.toString();
    }
}
