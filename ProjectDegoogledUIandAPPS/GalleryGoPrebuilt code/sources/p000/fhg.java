package p000;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.DialogInterface;
import android.view.View;
import java.util.List;

/* renamed from: fhg */
/* compiled from: PG */
public final class fhg {
    /* renamed from: a */
    public static fdx m8902a(ffc ffc) {
        List a = ffc.mo5573a();
        return (fdx) a.get(a.size() - 1);
    }

    /* renamed from: a */
    public static fdi m8901a(fdr fdr, int i) {
        fgb fgb = new fgb(fdr);
        ife.m12898e((Object) ffa.f9433a);
        return new fdi(i, fgb, fdr.f9325b, ffa.f9433a);
    }

    /* renamed from: a */
    public static View m8900a(C0140fa faVar) {
        ife.m12845a(faVar.f9240d != null, (Object) "Wrap OnShowListener with SyntheticDialogs#whileDialogExists");
        return faVar.f9240d.getWindow().findViewById(16908290);
    }

    /* renamed from: b */
    public static void m8904b(C0140fa faVar) {
        View a;
        boolean z;
        fdr a2 = fdz.m8660a(m8900a(faVar), ffa.f9433a);
        ife.m12869b((Object) a2, (Object) "Dialog root must be instrumented.");
        C0147fh fhVar = faVar.f9607z;
        while (true) {
            if (fhVar != null) {
                a = fhVar.f9573L;
                if (a != null) {
                    break;
                }
                fhVar = fhVar.f9607z;
            } else {
                a = fdz.m8658a((Activity) faVar.mo5653m());
                break;
            }
        }
        fdr a3 = fdz.m8660a(a, ffa.f9433a);
        if (a3 != null) {
            z = true;
        } else {
            z = false;
        }
        ife.m12876b(z, (Object) "Parent fragment/activity must be instrumented");
        ife.m12845a(a2.mo5544e(ffa.f9433a) instanceof fdz, (Object) "Cannot reparent synthetic nodes.");
        ife.m12845a(true ^ a2.mo5542c(ffa.f9433a), (Object) "Node is already impressed.");
        a3.mo5544e(ffa.f9433a).mo5525b(a2);
    }

    /* renamed from: a */
    public static DialogInterface.OnShowListener m8899a(DialogInterface.OnShowListener onShowListener, C0140fa faVar) {
        return new fgd(faVar, onShowListener);
    }

    /* renamed from: a */
    public static ObjectAnimator m8898a(Object obj, String str) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(obj, str, new float[]{1.0f});
        ofFloat.setInterpolator(fgo.f9518a);
        ofFloat.setDuration(500);
        return ofFloat;
    }

    /* renamed from: b */
    public static ObjectAnimator m8903b(Object obj, String str) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(obj, str, new float[]{0.0f});
        ofFloat.setInterpolator(fgo.f9518a);
        ofFloat.setDuration(500);
        return ofFloat;
    }
}
