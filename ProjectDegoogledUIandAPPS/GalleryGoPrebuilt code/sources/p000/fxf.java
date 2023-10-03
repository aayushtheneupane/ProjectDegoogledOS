package p000;

import android.util.Log;
import java.io.IOException;

@Deprecated
/* renamed from: fxf */
/* compiled from: PG */
public final class fxf implements fxg {

    /* renamed from: a */
    private static final String f10661a = fxg.class.getSimpleName();

    /* renamed from: b */
    private final fcd f10662b;

    /* renamed from: c */
    private final eyg f10663c;

    public fxf(fcd fcd, eyg eyg) {
        this.f10662b = fcd;
        this.f10663c = eyg;
    }

    /* renamed from: a */
    public final void mo6301a(fxe fxe) {
        try {
            this.f10662b.mo5481a(fxe.f10660b);
        } catch (eye e) {
            this.f10663c.mo5390a(e.f9199a, fxe.f10660b);
            throw new IOException("Attempted to use SSL unpatched. Google Play Services needs update.");
        } catch (eyd e2) {
            Log.e(f10661a, "Attempted to use SSL unpatched. Google Play Services unavailable.");
            this.f10663c.mo5390a(e2.f9198a, fxe.f10660b);
            throw new IOException("Blocked unpatched use of SSL stack.");
        }
    }
}
