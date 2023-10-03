package p000;

import android.net.Uri;
import android.os.Bundle;
import java.util.concurrent.Callable;

/* renamed from: gtq */
/* compiled from: PG */
public final /* synthetic */ class gtq implements Callable {

    /* renamed from: a */
    private final gtt f12035a;

    /* renamed from: b */
    private final Uri f12036b;

    /* renamed from: c */
    private final String f12037c;

    public gtq(gtt gtt, Uri uri, String str) {
        this.f12035a = gtt;
        this.f12036b = uri;
        this.f12037c = str;
    }

    public final Object call() {
        gtt gtt = this.f12035a;
        return gtt.f12044a.call(this.f12036b, this.f12037c, (String) null, (Bundle) null);
    }
}
