package p000;

import android.content.DialogInterface;
import android.view.KeyEvent;

/* renamed from: dkc */
/* compiled from: PG */
final /* synthetic */ class dkc implements DialogInterface.OnKeyListener {

    /* renamed from: a */
    private final dke f6710a;

    public dkc(dke dke) {
        this.f6710a = dke;
    }

    public final boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        dke dke = this.f6710a;
        if (i != 4) {
            return false;
        }
        dke.f6713b.mo5653m().finishAndRemoveTask();
        return true;
    }
}
