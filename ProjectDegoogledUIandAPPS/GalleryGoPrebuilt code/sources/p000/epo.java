package p000;

import android.app.Activity;
import android.content.Intent;

/* renamed from: epo */
/* compiled from: PG */
public final class epo extends epq {

    /* renamed from: a */
    private final /* synthetic */ Intent f8787a;

    /* renamed from: b */
    private final /* synthetic */ Activity f8788b;

    /* renamed from: c */
    private final /* synthetic */ int f8789c;

    public epo(Intent intent, Activity activity, int i) {
        this.f8787a = intent;
        this.f8788b = activity;
        this.f8789c = i;
    }

    /* renamed from: a */
    public final void mo5134a() {
        Intent intent = this.f8787a;
        if (intent != null) {
            this.f8788b.startActivityForResult(intent, this.f8789c);
        }
    }
}
