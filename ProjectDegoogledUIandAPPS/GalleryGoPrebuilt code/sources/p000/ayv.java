package p000;

import android.net.Uri;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* renamed from: ayv */
/* compiled from: PG */
public final class ayv implements axo {

    /* renamed from: a */
    private static final Set f1874a = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{"http", "https"})));

    /* renamed from: b */
    private final axo f1875b;

    public ayv(axo axo) {
        this.f1875b = axo;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ axn mo1697a(Object obj, int i, int i2, aqz aqz) {
        return this.f1875b.mo1697a(new axa(((Uri) obj).toString()), i, i2, aqz);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ boolean mo1698a(Object obj) {
        return f1874a.contains(((Uri) obj).getScheme());
    }
}
