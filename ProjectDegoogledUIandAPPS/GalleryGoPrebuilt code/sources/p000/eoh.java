package p000;

import android.util.Log;
import java.util.Set;

/* renamed from: eoh */
/* compiled from: PG */
final class eoh implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ ewp f8704a;

    /* renamed from: b */
    private final /* synthetic */ eoj f8705b;

    public eoh(eoj eoj, ewp ewp) {
        this.f8705b = eoj;
        this.f8704a = ewp;
    }

    public final void run() {
        eoj eoj = this.f8705b;
        ewp ewp = this.f8704a;
        ejq ejq = ewp.f9140a;
        if (ejq.mo4895b()) {
            eqs eqs = ewp.f9141b;
            ejq ejq2 = eqs.f8852a;
            if (!ejq2.mo4895b()) {
                String valueOf = String.valueOf(ejq2);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 48);
                sb.append("Sign-in succeeded with resolve account failure: ");
                sb.append(valueOf);
                Log.wtf("SignInCoordinator", sb.toString(), new Exception());
                eoj.f8712f.mo5057b(ejq2);
                eoj.f8711e.mo4931d();
                return;
            }
            eoi eoi = eoj.f8712f;
            eqg a = eqs.mo5168a();
            Set set = eoj.f8709c;
            if (a == null || set == null) {
                Log.wtf("GoogleApiManager", "Received null response from onSignInSuccess", new Exception());
                ((eno) eoi).mo5057b(new ejq(4));
            } else {
                eno eno = (eno) eoi;
                eno.f8667c = a;
                eno.f8668d = set;
                eno.mo5056a();
            }
        } else {
            eoj.f8712f.mo5057b(ejq);
        }
        eoj.f8711e.mo4931d();
    }
}
