package p000;

import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.api.Status;

/* renamed from: esb */
/* compiled from: PG */
final class esb extends esc {

    /* renamed from: h */
    private final /* synthetic */ esi f8897h;

    /* renamed from: i */
    private final /* synthetic */ Bundle f8898i;

    /* renamed from: j */
    private final /* synthetic */ long f8899j;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public esb(ekv ekv, esi esi, Bundle bundle, long j) {
        super(ekv);
        this.f8897h = esi;
        this.f8898i = bundle;
        this.f8899j = j;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo4881a(ekl ekl) {
        esq esq = (esq) ekl;
        try {
            esi esi = this.f8897h;
            Bundle bundle = this.f8898i;
            long j = this.f8899j;
            esv.m8124a(bundle);
            esv.m8125a(esi);
            ((esu) esq.mo5126p()).mo5218a(esi, bundle, j);
            mo3507a((ela) Status.f4972a);
        } catch (Exception e) {
            Log.e("gF_Feedback", "Requesting to save the async feedback psbd failed!", e);
            mo3513c(esd.f8900a);
        }
    }
}
