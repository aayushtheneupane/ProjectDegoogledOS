package p000;

import android.content.Intent;

/* renamed from: hjv */
/* compiled from: PG */
public final class hjv implements hfl {

    /* renamed from: a */
    private final hjp f12882a;

    /* renamed from: b */
    private final eyc f12883b;

    public hjv(hjp hjp, eyc eyc) {
        this.f12882a = hjp;
        this.f12883b = eyc;
    }

    /* renamed from: a */
    public final ieh mo2555a(Intent intent) {
        if (this.f12883b.mo5388a().equals(intent.getData().getSchemeSpecificPart())) {
            return this.f12882a.mo7465a();
        }
        return ife.m12820a((Object) null);
    }
}
