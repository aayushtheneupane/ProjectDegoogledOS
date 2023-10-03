package p000;

import android.util.Log;

/* renamed from: ftb */
/* compiled from: PG */
public final /* synthetic */ class ftb implements eyl {

    /* renamed from: a */
    private final fte f10561a;

    public ftb(fte fte) {
        this.f10561a = fte;
    }

    /* renamed from: a */
    public final void mo5396a(eyb eyb) {
        fte fte = this.f10561a;
        int i = ((eyy) eyb).f9206a.f8443b;
        StringBuilder sb = new StringBuilder(74);
        sb.append("GoogleApiClient silent feedback connection failed with result: ");
        sb.append(i);
        Log.e("GcoreCrashReporter", sb.toString());
        fte.mo6176a();
    }
}
