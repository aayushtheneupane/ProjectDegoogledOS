package p000;

import android.content.Context;

/* renamed from: foc */
/* compiled from: PG */
public final class foc implements hqk {

    /* renamed from: a */
    private final Context f10134a;

    public foc(Context context) {
        this.f10134a = context;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo2652a() {
        flw.m9201c("PrimesTesting", "GserviceFlagsSupplier.get()", new Object[0]);
        fny fny = new fny();
        fln fln = new fln((byte[]) null);
        fln.mo5923b(false);
        fln.mo5922a(false);
        fln.mo5924c(false);
        fln.mo5923b(fny.mo5990a(this.f10134a, "primes:compact_startup_trace_min_version"));
        fln.mo5922a(fny.mo5990a(this.f10134a, "primes:log_process_creation_time_min_version"));
        fln.mo5924c(fny.mo5991b(this.f10134a, "primes:use_process_get_start_elapsed_realtime"));
        String str = fln.f9989a == null ? " useCompactStartupTrace" : "";
        if (fln.f9990b == null) {
            str = str.concat(" logProcessCreationTime");
        }
        if (fln.f9991c == null) {
            str = String.valueOf(str).concat(" useProcessGetStartElapsedRealtime");
        }
        if (str.isEmpty()) {
            return new fij(fln.f9989a.booleanValue(), fln.f9990b.booleanValue(), fln.f9991c.booleanValue());
        }
        String valueOf = String.valueOf(str);
        throw new IllegalStateException(valueOf.length() == 0 ? new String("Missing required properties:") : "Missing required properties:".concat(valueOf));
    }
}
