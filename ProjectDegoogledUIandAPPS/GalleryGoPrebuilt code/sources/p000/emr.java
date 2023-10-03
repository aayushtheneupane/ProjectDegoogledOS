package p000;

import android.util.Log;

/* renamed from: emr */
/* compiled from: PG */
final class emr extends enc {

    /* renamed from: b */
    private final /* synthetic */ emv f8569b;

    /* renamed from: c */
    private final /* synthetic */ ewp f8570c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public emr(enb enb, emv emv, ewp ewp) {
        super(enb);
        this.f8569b = emv;
        this.f8570c = ewp;
    }

    /* renamed from: a */
    public final void mo5008a() {
        emv emv = this.f8569b;
        ewp ewp = this.f8570c;
        if (emv.mo5025b(0)) {
            ejq ejq = ewp.f9140a;
            if (ejq.mo4895b()) {
                eqs eqs = ewp.f9141b;
                ejq ejq2 = eqs.f8852a;
                if (!ejq2.mo4895b()) {
                    String valueOf = String.valueOf(ejq2);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 48);
                    sb.append("Sign-in succeeded with resolve account failure: ");
                    sb.append(valueOf);
                    Log.wtf("GACConnecting", sb.toString(), new Exception());
                    emv.mo5023b(ejq2);
                    return;
                }
                emv.f8580g = true;
                emv.f8581h = eqs.mo5168a();
                emv.f8582i = eqs.f8853b;
                emv.f8583j = eqs.f8854c;
                emv.mo5027e();
            } else if (emv.mo5022a(ejq)) {
                emv.mo5028f();
                emv.mo5027e();
            } else {
                emv.mo5023b(ejq);
            }
        }
    }
}
