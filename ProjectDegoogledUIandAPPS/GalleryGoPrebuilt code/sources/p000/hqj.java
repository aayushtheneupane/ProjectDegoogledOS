package p000;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* renamed from: hqj */
/* compiled from: PG */
public final class hqj {

    /* renamed from: a */
    public final hpn f13257a;

    /* renamed from: b */
    public final boolean f13258b;

    /* renamed from: c */
    public final hqi f13259c;

    /* renamed from: d */
    public final int f13260d;

    private hqj(hqi hqi) {
        this(hqi, false, hpm.f13228a, Integer.MAX_VALUE);
    }

    public hqj(hqi hqi, boolean z, hpn hpn, int i) {
        this.f13259c = hqi;
        this.f13258b = z;
        this.f13257a = hpn;
        this.f13260d = i;
    }

    /* renamed from: a */
    public static hqj m11914a(char c) {
        hpk hpk = new hpk(c);
        ife.m12898e((Object) hpk);
        return new hqj(new hqe(hpk));
    }

    /* renamed from: a */
    public static hqj m11915a(String str) {
        ife.m12845a(str.length() != 0, (Object) "The separator may not be the empty string.");
        if (str.length() != 1) {
            return new hqj(new hqg(str));
        }
        return m11914a(str.charAt(0));
    }

    /* renamed from: a */
    public final Iterable mo7679a(CharSequence charSequence) {
        ife.m12898e((Object) charSequence);
        return new hqh(this, charSequence);
    }

    /* renamed from: c */
    public final List mo7681c(CharSequence charSequence) {
        ife.m12898e((Object) charSequence);
        Iterator b = mo7680b(charSequence);
        ArrayList arrayList = new ArrayList();
        while (b.hasNext()) {
            arrayList.add((String) b.next());
        }
        return Collections.unmodifiableList(arrayList);
    }

    /* renamed from: b */
    public final Iterator mo7680b(CharSequence charSequence) {
        return this.f13259c.mo7676a(this, charSequence);
    }
}
