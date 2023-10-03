package p000;

import android.content.DialogInterface;

/* renamed from: egf */
/* compiled from: PG */
final /* synthetic */ class egf implements DialogInterface.OnShowListener {

    /* renamed from: a */
    private final egg f8196a;

    public egf(egg egg) {
        this.f8196a = egg;
    }

    public final void onShow(DialogInterface dialogInterface) {
        egg egg = this.f8196a;
        ((fdi) ((fdi) egg.f8200d.mo5579a(85888).mo5513a(fej.m8698a())).mo5513a(ffh.f9451a)).mo5510a();
        try {
            fhg.m8904b(egg.f8197a);
        } catch (Exception e) {
            cwn.m5515b((Throwable) e, "Failed to reparent storage permission dialog to host.", new Object[0]);
        }
    }
}
