package p000;

import android.os.Looper;

/* renamed from: ekp */
/* compiled from: PG */
public final class ekp {

    /* renamed from: a */
    public eok f8479a;

    /* renamed from: b */
    private Looper f8480b;

    /* renamed from: a */
    public final ekq mo4942a() {
        if (this.f8479a == null) {
            this.f8479a = new elm();
        }
        if (this.f8480b == null) {
            this.f8480b = Looper.getMainLooper();
        }
        return new ekq(this.f8479a, this.f8480b);
    }
}
