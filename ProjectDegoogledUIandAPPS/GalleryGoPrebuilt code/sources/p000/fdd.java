package p000;

import android.content.Context;
import java.util.concurrent.Executor;

/* renamed from: fdd */
/* compiled from: PG */
public final class fdd {

    /* renamed from: a */
    private final Context f9298a;

    /* renamed from: b */
    private final Executor f9299b;

    /* renamed from: c */
    private final ext f9300c;

    /* renamed from: d */
    private exs f9301d;

    /* renamed from: e */
    private exs f9302e;

    fdd() {
    }

    public fdd(Context context, Executor executor, ext ext) {
        this.f9298a = context;
        this.f9299b = executor;
        this.f9300c = ext;
    }

    /* renamed from: a */
    public final synchronized exs mo5508a(fcm fcm) {
        int i = fcm.f9269b - 1;
        if (i == 0 || i == 1) {
            if (this.f9301d == null) {
                this.f9301d = this.f9300c.mo5383a(this.f9298a, (String) null, (String) null);
            }
            return this.f9301d;
        }
        if (this.f9302e == null) {
            this.f9302e = this.f9300c.mo5382a(this.f9298a);
        }
        return this.f9302e;
    }

    /* renamed from: a */
    public final ieh mo5509a(fdc fdc, ieh ieh) {
        fda fda = (fda) fdc;
        return ibd.m12604a(ibv.m12658a(ieh, (icf) new fdg(this, fda.f9295b, new fde(), fda.f9294a), this.f9299b), ezs.class, fdf.f9303a, (Executor) idh.f13918a);
    }
}
