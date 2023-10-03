package p000;

import android.content.Context;

/* renamed from: ese */
/* compiled from: PG */
public final /* synthetic */ class ese implements eoe {

    /* renamed from: a */
    private final esi f8904a;

    /* renamed from: b */
    private final long f8905b;

    /* renamed from: c */
    private final Context f8906c;

    public ese(esi esi, long j, Context context) {
        this.f8904a = esi;
        this.f8905b = j;
        this.f8906c = context;
    }

    /* renamed from: a */
    public final void mo5083a(Object obj, Object obj2) {
        esi esi = this.f8904a;
        long j = this.f8905b;
        Context context = this.f8906c;
        esq esq = (esq) obj;
        esg esg = new esg(esi);
        esg.f8917j = j;
        esi a = esg.mo5198a();
        esv esv = a.f8940q;
        if (esv != null) {
            esv.m8123a(context, esv, j);
        }
        esv.m8125a(a);
        esq.mo5211a(a);
        ((esu) esq.mo5126p()).mo5217a(a);
        ((exe) obj2).mo5365a((Object) null);
    }
}
