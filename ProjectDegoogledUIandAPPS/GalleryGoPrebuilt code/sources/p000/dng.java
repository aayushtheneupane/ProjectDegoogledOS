package p000;

import android.view.View;
import p003j$.util.function.Consumer;

/* renamed from: dng */
/* compiled from: PG */
final /* synthetic */ class dng implements View.OnClickListener {

    /* renamed from: a */
    private final dnn f6863a;

    public dng(dnn dnn) {
        this.f6863a = dnn;
    }

    public final void onClick(View view) {
        dnn dnn = this.f6863a;
        dnn.mo4270a("auto-enhance", (Consumer) new dmy(dnn));
    }
}
