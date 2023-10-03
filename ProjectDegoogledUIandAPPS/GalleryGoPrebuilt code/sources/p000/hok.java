package p000;

import android.content.Context;
import android.view.View;
import com.google.android.apps.photosgo.R;

/* renamed from: hok */
/* compiled from: PG */
public final class hok {
    private hok() {
    }

    /* renamed from: a */
    public static hok m11837a() {
        return new hok();
    }

    /* renamed from: b */
    public static hlz m11840b(Context context) {
        return ((hmc) hgh.m11442a(context, hmc.class)).mo2294cJ();
    }

    /* renamed from: a */
    public static void m11839a(C0140fa faVar) {
        View view;
        m11841b(faVar);
        View a = ihg.m13027a(faVar);
        C0147fh fhVar = faVar.f9607z;
        if (fhVar == null) {
            view = faVar.mo5653m().findViewById(16908290);
        } else {
            view = fhVar instanceof C0140fa ? ihg.m13027a((C0140fa) fhVar) : fhVar.f9573L;
        }
        ((View) ife.m12898e((Object) a)).setTag(R.id.tiktok_event_parent, view);
    }

    /* renamed from: b */
    public static void m11841b(C0140fa faVar) {
        if (faVar.f9239c && ihg.m13027a(faVar) == null) {
            throw new IllegalStateException("DialogFragment is being used as a dialog. Must return a valid view in onCreateView() or a valid Dialog in onCreateDialog().");
        } else if (!faVar.f9239c && faVar.f9573L == null) {
            throw new IllegalStateException("DialogFragment not being used as a dialog. Must return a valid view in onCreateView() -- onCreateDialog() is not called.");
        }
    }

    /* renamed from: a */
    public static hos m11838a(Context context) {
        return ((hoj) hgh.m11442a(context, hoj.class)).mo2298cN();
    }
}
