package p000;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.feedback.ErrorReport;

/* renamed from: erz */
/* compiled from: PG */
final class erz extends esc {

    /* renamed from: h */
    private final /* synthetic */ esi f8889h;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public erz(ekv ekv, esi esi) {
        super(ekv);
        this.f8889h = esi;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo4881a(ekl ekl) {
        esq esq = (esq) ekl;
        esi esi = this.f8889h;
        esv.m8125a(esi);
        ((esu) esq.mo5126p()).mo5215a(new ErrorReport(esi, esq.f8950r.getCacheDir()));
        mo3507a((ela) Status.f4972a);
    }
}
