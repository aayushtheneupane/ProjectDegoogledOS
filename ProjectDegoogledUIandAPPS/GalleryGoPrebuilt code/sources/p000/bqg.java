package p000;

import android.content.DialogInterface;
import android.widget.CheckBox;
import java.util.List;

/* renamed from: bqg */
/* compiled from: PG */
public final class bqg implements DialogInterface.OnClickListener {

    /* renamed from: a */
    public final bqd f3361a;

    /* renamed from: b */
    public final List f3362b;

    /* renamed from: c */
    public final hlz f3363c;

    /* renamed from: d */
    public final fge f3364d;

    /* renamed from: e */
    public fgc f3365e;

    /* renamed from: f */
    public CheckBox f3366f = null;

    /* renamed from: g */
    private boolean f3367g = false;

    public bqg(bqd bqd, bqc bqc, hlz hlz, fge fge) {
        this.f3361a = bqd;
        this.f3362b = bqc.f3352b;
        this.f3363c = hlz;
        this.f3364d = fge;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        if (i == -1 && !this.f3367g) {
            ihg.m13040a((hoi) new bqj(this.f3362b), (C0140fa) this.f3361a);
        }
        this.f3367g = true;
        dialogInterface.dismiss();
    }

    /* renamed from: a */
    public final void mo2672a() {
        CheckBox checkBox = this.f3366f;
        boolean z = true;
        if (checkBox != null && !checkBox.isChecked()) {
            z = false;
        }
        C0394oj ojVar = (C0394oj) this.f3361a.f9240d;
        if (ojVar != null) {
            ojVar.mo9526a(-1).setEnabled(z);
        }
    }
}
