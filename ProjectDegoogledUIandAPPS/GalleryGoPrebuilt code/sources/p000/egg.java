package p000;

import android.content.DialogInterface;

/* renamed from: egg */
/* compiled from: PG */
public final class egg implements DialogInterface.OnClickListener {

    /* renamed from: a */
    public final ege f8197a;

    /* renamed from: b */
    public final hlz f8198b;

    /* renamed from: c */
    public final fge f8199c;

    /* renamed from: d */
    public fgc f8200d;

    /* renamed from: e */
    private boolean f8201e = false;

    public egg(ege ege, hlz hlz, fge fge) {
        this.f8197a = ege;
        this.f8198b = hlz;
        this.f8199c = fge;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        if (!this.f8201e) {
            if (i == -2) {
                ihg.m13040a((hoi) new egd(false), (C0140fa) this.f8197a);
            } else if (i == -1) {
                ihg.m13040a((hoi) new egd(true), (C0140fa) this.f8197a);
            }
        }
        this.f8201e = true;
        dialogInterface.dismiss();
    }
}
