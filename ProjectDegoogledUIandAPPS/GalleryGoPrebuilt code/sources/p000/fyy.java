package p000;

import android.net.Uri;

/* renamed from: fyy */
/* compiled from: PG */
final /* synthetic */ class fyy implements hpr {

    /* renamed from: a */
    public static final hpr f10707a = new fyy();

    private fyy() {
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        Uri uri = (Uri) obj;
        return uri.buildUpon().path(String.valueOf(uri.getPath()).concat(".bak")).build();
    }
}
