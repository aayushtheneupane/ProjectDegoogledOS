package p000;

import android.net.Uri;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* renamed from: ayp */
/* compiled from: PG */
public final class ayp implements axo {

    /* renamed from: a */
    private static final Set f1867a = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{"file", "android.resource", "content"})));

    /* renamed from: b */
    private final ayn f1868b;

    public ayp(ayn ayn) {
        this.f1868b = ayn;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ axn mo1697a(Object obj, int i, int i2, aqz aqz) {
        Uri uri = (Uri) obj;
        return new axn(new bfa(uri), this.f1868b.mo1728a(uri));
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ boolean mo1698a(Object obj) {
        return f1867a.contains(((Uri) obj).getScheme());
    }
}
