package p000;

import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.api.Status;

/* renamed from: esa */
/* compiled from: PG */
final class esa extends esc {

    /* renamed from: h */
    private final /* synthetic */ Bundle f8895h;

    /* renamed from: i */
    private final /* synthetic */ long f8896i;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public esa(ekv ekv, Bundle bundle, long j) {
        super(ekv);
        this.f8895h = bundle;
        this.f8896i = j;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo4881a(ekl ekl) {
        esq esq = (esq) ekl;
        try {
            Bundle bundle = this.f8895h;
            long j = this.f8896i;
            esv.m8124a(bundle);
            ((esu) esq.mo5126p()).mo5214a(bundle, j);
            mo3507a((ela) Status.f4972a);
        } catch (Exception e) {
            Log.e("gF_Feedback", "Requesting to save the async feedback psd failed!", e);
            mo3513c(esd.f8900a);
        }
    }
}
