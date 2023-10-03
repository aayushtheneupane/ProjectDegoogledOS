package p000;

import android.content.Context;

@Deprecated
/* renamed from: fbz */
/* compiled from: PG */
public final class fbz implements fbm {

    /* renamed from: a */
    private final ekr f9259a;

    public fbz(Context context) {
        this.f9259a = evb.m8188a(context);
    }

    /* renamed from: a */
    public final boolean mo5475a() {
        return true;
    }

    /* renamed from: a */
    public final fch mo5472a(String str) {
        ekr ekr = this.f9259a;
        eop b = eoq.m7942b();
        b.f8722a = new evg(str);
        return fck.m8571a(ekr.mo4945a(b.mo5090a()));
    }

    /* renamed from: a */
    public final fch mo5474a(String str, String str2) {
        ekr ekr = this.f9259a;
        eop b = eoq.m7942b();
        b.f8722a = new evf(str, str2);
        return fck.m8572a(ekr.mo4945a(b.mo5090a()), fby.f9258a);
    }

    /* renamed from: a */
    public final fch mo5473a(String str, int i, String[] strArr, byte[] bArr) {
        ekr ekr = this.f9259a;
        eop b = eoq.m7942b();
        b.f8722a = new eve(str, i, strArr, bArr);
        return fck.m8571a(ekr.mo4945a(b.mo5090a()));
    }
}
