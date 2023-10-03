package p000;

import android.content.Context;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.feedback.ErrorReport;

/* renamed from: ery */
/* compiled from: PG */
final class ery extends esc {

    /* renamed from: h */
    private final /* synthetic */ esi f8886h;

    /* renamed from: i */
    private final /* synthetic */ Context f8887i;

    /* renamed from: j */
    private final /* synthetic */ long f8888j;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ery(ekv ekv, esi esi, Context context, long j) {
        super(ekv);
        this.f8886h = esi;
        this.f8887i = context;
        this.f8888j = j;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo4881a(ekl ekl) {
        esv esv;
        esq esq = (esq) ekl;
        esi esi = this.f8886h;
        if (esi == null || (esv = esi.f8940q) == null) {
            esv.m8125a(esi);
            esq.mo5211a(esi);
            ((esu) esq.mo5126p()).mo5219b(new ErrorReport(esi, esq.f8950r.getCacheDir()));
            mo3507a((ela) Status.f4972a);
            return;
        }
        esv.m8123a(this.f8887i, esv, this.f8888j);
        esi esi2 = this.f8886h;
        long j = this.f8888j;
        esv.m8125a(esi2);
        esq.mo5211a(esi2);
        ((esu) esq.mo5126p()).mo5216a(new ErrorReport(esi2, esq.f8950r.getCacheDir()), j);
        mo3507a((ela) Status.f4972a);
    }
}
