package p000;

import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.googlehelp.GoogleHelp;

/* renamed from: etm */
/* compiled from: PG */
final class etm extends ets {

    /* renamed from: h */
    private final /* synthetic */ Bundle f9001h;

    /* renamed from: i */
    private final /* synthetic */ long f9002i;

    /* renamed from: j */
    private final /* synthetic */ GoogleHelp f9003j;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public etm(ekv ekv, Bundle bundle, long j, GoogleHelp googleHelp) {
        super(ekv);
        this.f9001h = bundle;
        this.f9002i = j;
        this.f9003j = googleHelp;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo5237a(etz etz) {
        try {
            etz.mo5240a(this.f9001h, this.f9002i, this.f9003j, new etl(this));
        } catch (Exception e) {
            Log.e("gH_GoogleHelpApiImpl", "Requesting to save the async feedback psd failed!", e);
            mo3513c(ett.f9015a);
        }
    }
}
