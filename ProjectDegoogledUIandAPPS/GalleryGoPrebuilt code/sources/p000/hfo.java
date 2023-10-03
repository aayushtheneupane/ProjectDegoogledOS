package p000;

import android.content.Context;
import com.google.android.libraries.stitch.sslguard.SslGuardServerSocketFactory;
import com.google.android.libraries.stitch.sslguard.SslGuardSocketFactory;
import java.security.Security;

/* renamed from: hfo */
/* compiled from: PG */
public final /* synthetic */ class hfo implements hbc {

    /* renamed from: a */
    private final fcd f12658a;

    /* renamed from: b */
    private final eyg f12659b;

    /* renamed from: c */
    private final Context f12660c;

    public hfo(fcd fcd, eyg eyg, Context context) {
        this.f12658a = fcd;
        this.f12659b = eyg;
        this.f12660c = context;
    }

    /* renamed from: a */
    public final void mo7253a() {
        fcd fcd = this.f12658a;
        eyg eyg = this.f12659b;
        Context context = this.f12660c;
        fxd fxd = new fxd(new fxf(fcd, eyg));
        fxe fxe = new fxe(context);
        synchronized (fxd.f10654a) {
            if (fxe.f10659a == null) {
                fxe.f10659a = fxe;
                if (fxd.f10655b == null) {
                    fxd.f10655b = new fxh();
                }
                if (Security.insertProviderAt(fxd.f10655b, 1) == 1) {
                    SslGuardSocketFactory.m4985a(fxd.f10658c);
                    SslGuardServerSocketFactory.m4982a(fxd.f10658c);
                    fxd.m9812b();
                    fxd.m9811a();
                    return;
                }
                throw new RuntimeException("Failed to install SslGuard with top priority.");
            }
        }
    }
}
