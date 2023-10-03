package p000;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* renamed from: ajx */
/* compiled from: PG */
final class ajx extends BroadcastReceiver {

    /* renamed from: a */
    private final /* synthetic */ ajy f654a;

    public ajx(ajy ajy) {
        this.f654a = ajy;
    }

    public final void onReceive(Context context, Intent intent) {
        if (intent != null) {
            this.f654a.mo559a(intent);
        }
    }
}
