package p000;

import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.googlehelp.GoogleHelp;

/* renamed from: eto */
/* compiled from: PG */
final class eto extends ets {

    /* renamed from: h */
    private final /* synthetic */ esi f9005h;

    /* renamed from: i */
    private final /* synthetic */ Bundle f9006i;

    /* renamed from: j */
    private final /* synthetic */ long f9007j;

    /* renamed from: k */
    private final /* synthetic */ GoogleHelp f9008k;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public eto(ekv ekv, esi esi, Bundle bundle, long j, GoogleHelp googleHelp) {
        super(ekv);
        this.f9005h = esi;
        this.f9006i = bundle;
        this.f9007j = j;
        this.f9008k = googleHelp;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo5237a(etz etz) {
        try {
            etz.mo5242a(this.f9005h, this.f9006i, this.f9007j, this.f9008k, new etn(this));
        } catch (Exception e) {
            Log.e("gH_GoogleHelpApiImpl", "Requesting to save the async feedback psbd failed!", e);
            mo3513c(ett.f9015a);
        }
    }
}
