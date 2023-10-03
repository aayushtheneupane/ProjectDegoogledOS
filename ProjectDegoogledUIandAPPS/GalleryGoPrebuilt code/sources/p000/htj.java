package p000;

import java.io.Serializable;

/* renamed from: htj */
/* compiled from: PG */
final class htj implements Serializable {

    /* renamed from: a */
    private final htk f13381a;

    public htj(htk htk) {
        this.f13381a = htk;
    }

    /* access modifiers changed from: package-private */
    public Object readResolve() {
        return this.f13381a.mo7796f();
    }
}
