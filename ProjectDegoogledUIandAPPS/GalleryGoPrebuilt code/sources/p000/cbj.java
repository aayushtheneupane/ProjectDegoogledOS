package p000;

import android.view.View;

/* renamed from: cbj */
/* compiled from: PG */
public final class cbj implements View.OnClickListener {

    /* renamed from: a */
    private final /* synthetic */ cbi f4013a;

    public cbj(cbi cbi) {
        this.f4013a = cbi;
    }

    public final void onClick(View view) {
        this.f4013a.f4012c.filter(cbe.f4006a).map(cbf.f4007a).map(cbg.f4008a).ifPresent(new cbh(view));
    }
}
