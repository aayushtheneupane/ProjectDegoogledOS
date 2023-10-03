package p000;

import android.net.Uri;
import android.view.View;

/* renamed from: dlk */
/* compiled from: PG */
final /* synthetic */ class dlk implements View.OnClickListener {

    /* renamed from: a */
    private final dll f6778a;

    /* renamed from: b */
    private final dik f6779b;

    /* renamed from: c */
    private final cxi f6780c;

    public dlk(dll dll, dik dik, cxi cxi) {
        this.f6778a = dll;
        this.f6779b = dik;
        this.f6780c = cxi;
    }

    public final void onClick(View view) {
        dll dll = this.f6778a;
        dik dik = this.f6779b;
        cxi cxi = this.f6780c;
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
        dll.f6783c.startActivity(C0637xj.m15891a(dik, Uri.parse(cxi.f5910b)));
    }
}
