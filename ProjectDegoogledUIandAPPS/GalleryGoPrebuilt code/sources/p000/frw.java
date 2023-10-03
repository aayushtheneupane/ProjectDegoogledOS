package p000;

import android.os.Bundle;

/* renamed from: frw */
/* compiled from: PG */
public final class frw implements fwt, fwg, fwq {

    /* renamed from: a */
    public int f10336a;

    public frw(fwc fwc) {
        fwc.mo6246a((fwt) this);
    }

    /* renamed from: a */
    public final void mo6072a(Bundle bundle) {
        if (bundle != null) {
            this.f10336a = bundle.getInt("com.google.android.apps.photos.activityresult.SafeRequstCodeGenerator.NextRequestCode");
        } else {
            this.f10336a = 16383;
        }
    }

    /* renamed from: b */
    public final void mo6073b(Bundle bundle) {
        bundle.putInt("com.google.android.apps.photos.activityresult.SafeRequstCodeGenerator.NextRequestCode", this.f10336a);
    }
}
