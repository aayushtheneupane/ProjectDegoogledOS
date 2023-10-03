package p000;

import android.net.Uri;
import java.util.concurrent.Callable;

/* renamed from: gtp */
/* compiled from: PG */
final /* synthetic */ class gtp implements Callable {

    /* renamed from: a */
    private final gtt f12031a;

    /* renamed from: b */
    private final Uri f12032b;

    /* renamed from: c */
    private final String f12033c;

    /* renamed from: d */
    private final String[] f12034d;

    public gtp(gtt gtt, Uri uri, String str, String[] strArr) {
        this.f12031a = gtt;
        this.f12032b = uri;
        this.f12033c = str;
        this.f12034d = strArr;
    }

    public final Object call() {
        gtt gtt = this.f12031a;
        return Integer.valueOf(gtt.f12044a.delete(this.f12032b, this.f12033c, this.f12034d));
    }
}
