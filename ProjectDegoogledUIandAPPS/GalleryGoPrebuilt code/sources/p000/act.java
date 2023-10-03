package p000;

import android.content.DialogInterface;

/* renamed from: act */
/* compiled from: PG */
final class act implements DialogInterface.OnMultiChoiceClickListener {

    /* renamed from: a */
    private final /* synthetic */ acu f191a;

    public act(acu acu) {
        this.f191a = acu;
    }

    public final void onClick(DialogInterface dialogInterface, int i, boolean z) {
        if (z) {
            acu acu = this.f191a;
            acu.f193aa = acu.f192Z.add(acu.f194ab[i].toString()) | acu.f193aa;
            return;
        }
        acu acu2 = this.f191a;
        acu2.f193aa = acu2.f192Z.remove(acu2.f194ab[i].toString()) | acu2.f193aa;
    }
}
