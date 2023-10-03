package p000;

import android.net.Uri;
import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: egl */
/* compiled from: PG */
final /* synthetic */ class egl implements Consumer {

    /* renamed from: a */
    private final egp f8205a;

    /* renamed from: b */
    private final Uri f8206b;

    public egl(egp egp, Uri uri) {
        this.f8205a = egp;
        this.f8206b = uri;
    }

    public final void accept(Object obj) {
        egp egp = this.f8205a;
        String str = (String) obj;
        egp.f8210a.mo6986a(grw.m10691f(egp.f8211b.f8189a.mo6360a(new ega(str, this.f8206b), idh.f13918a)), grt.m10683a((CharSequence) str), egp.f8214e);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
