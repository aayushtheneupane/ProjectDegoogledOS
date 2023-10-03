package p000;

import android.content.ContentResolver;
import android.net.Uri;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* renamed from: gtt */
/* compiled from: PG */
public final class gtt {

    /* renamed from: a */
    public final ContentResolver f12044a;

    /* renamed from: b */
    public final iek f12045b;

    public gtt(ContentResolver contentResolver, iek iek) {
        this.f12044a = contentResolver;
        this.f12045b = iek;
    }

    /* renamed from: a */
    public final ieh mo7043a(Uri uri, String str, String[] strArr) {
        return this.f12045b.mo5933a((Callable) new gtp(this, uri, str, strArr));
    }

    /* renamed from: a */
    public final gpc mo7041a(Uri uri, String[] strArr, hgm hgm, String str) {
        ife.m12898e((Object) strArr);
        ife.m12898e((Object) uri);
        return mo7042a(uri, strArr, hgm.f12699a.f10841a, m10786a(hgm), str);
    }

    /* renamed from: a */
    public final gpc mo7042a(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        ife.m12898e((Object) strArr);
        ife.m12898e((Object) uri);
        gbb b = gbb.m9975b(new gtr(this, uri, strArr, str, strArr2, str2));
        b.mo6387a((Executor) this.f12045b);
        return gpc.m10581b(b);
    }

    /* renamed from: a */
    private static final String[] m10786a(hgm hgm) {
        try {
            return (String[]) Arrays.copyOf(hgm.mo7406a(), hgm.mo7406a().length, String[].class);
        } catch (ArrayStoreException e) {
            throw new IllegalArgumentException("AsyncContentResolver cannot be queried with a SafeSQLStatement containing byte array arguments. Only String arguments are allowed.", e);
        }
    }
}
