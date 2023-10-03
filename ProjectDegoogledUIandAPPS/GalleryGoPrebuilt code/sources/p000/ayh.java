package p000;

import android.net.Uri;
import android.text.TextUtils;
import java.io.File;

/* renamed from: ayh */
/* compiled from: PG */
public final class ayh implements axo {

    /* renamed from: a */
    private final axo f1860a;

    public ayh(axo axo) {
        this.f1860a = axo;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ axn mo1697a(Object obj, int i, int i2, aqz aqz) {
        Uri uri;
        String str = (String) obj;
        if (TextUtils.isEmpty(str)) {
            uri = null;
        } else if (str.charAt(0) == '/') {
            uri = m1892a(str);
        } else {
            Uri parse = Uri.parse(str);
            uri = parse.getScheme() != null ? parse : m1892a(str);
        }
        if (uri == null || !this.f1860a.mo1698a(uri)) {
            return null;
        }
        return this.f1860a.mo1697a(uri, i, i2, aqz);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ boolean mo1698a(Object obj) {
        String str = (String) obj;
        return true;
    }

    /* renamed from: a */
    private static Uri m1892a(String str) {
        return Uri.fromFile(new File(str));
    }
}
