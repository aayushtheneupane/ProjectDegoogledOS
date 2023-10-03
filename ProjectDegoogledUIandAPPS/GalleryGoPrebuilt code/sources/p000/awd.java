package p000;

import android.content.res.AssetManager;
import android.net.Uri;

/* renamed from: awd */
/* compiled from: PG */
public final class awd implements axo {

    /* renamed from: a */
    private final AssetManager f1793a;

    /* renamed from: b */
    private final awa f1794b;

    public awd(AssetManager assetManager, awa awa) {
        this.f1793a = assetManager;
        this.f1794b = awa;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ axn mo1697a(Object obj, int i, int i2, aqz aqz) {
        Uri uri = (Uri) obj;
        return new axn(new bfa(uri), this.f1794b.mo1695a(this.f1793a, uri.toString().substring(22)));
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ boolean mo1698a(Object obj) {
        Uri uri = (Uri) obj;
        if ("file".equals(uri.getScheme()) && !uri.getPathSegments().isEmpty() && "android_asset".equals(uri.getPathSegments().get(0))) {
            return true;
        }
        return false;
    }
}
