package p000;

import android.content.Intent;

/* renamed from: epp */
/* compiled from: PG */
public final class epp extends epq {

    /* renamed from: a */
    private final /* synthetic */ Intent f8790a;

    /* renamed from: b */
    private final /* synthetic */ enw f8791b;

    public epp(Intent intent, enw enw) {
        this.f8790a = intent;
        this.f8791b = enw;
    }

    /* renamed from: a */
    public final void mo5134a() {
        Intent intent = this.f8790a;
        if (intent != null) {
            this.f8791b.startActivityForResult(intent, 2);
        }
    }
}
