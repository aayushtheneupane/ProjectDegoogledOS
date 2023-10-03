package p000;

import android.net.Uri;
import android.view.View;

/* renamed from: doq */
/* compiled from: PG */
final /* synthetic */ class doq implements View.OnClickListener {

    /* renamed from: a */
    private final dot f6950a;

    /* renamed from: b */
    private final dik f6951b;

    /* renamed from: c */
    private final cxi f6952c;

    public doq(dot dot, dik dik, cxi cxi) {
        this.f6950a = dot;
        this.f6951b = dik;
        this.f6952c = cxi;
    }

    public final void onClick(View view) {
        dot dot = this.f6950a;
        dik dik = this.f6951b;
        cxi cxi = this.f6952c;
        Object[] objArr = new Object[2];
        dii dii = dik.f6615g;
        if (dii == null) {
            dii = dii.f6597d;
        }
        objArr[0] = dii.f6601c;
        dii dii2 = dik.f6615g;
        if (dii2 == null) {
            dii2 = dii.f6597d;
        }
        objArr[1] = dii2.f6600b;
        dot.f6957c.startActivity(C0637xj.m15891a(dik, Uri.parse(cxi.f5910b)));
    }
}
