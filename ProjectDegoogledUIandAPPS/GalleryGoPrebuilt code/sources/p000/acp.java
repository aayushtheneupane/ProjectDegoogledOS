package p000;

import android.content.DialogInterface;

/* renamed from: acp */
/* compiled from: PG */
final class acp implements DialogInterface.OnClickListener {

    /* renamed from: a */
    private final /* synthetic */ acq f186a;

    public acp(acq acq) {
        this.f186a = acq;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        acq acq = this.f186a;
        acq.f187Z = i;
        acq.f202ac = -1;
        dialogInterface.dismiss();
    }
}
