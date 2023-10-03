package p000;

import android.os.Bundle;
import java.util.ArrayList;

/* renamed from: gkv */
/* compiled from: PG */
public final class gkv implements fwt, fwg, fwq {

    /* renamed from: a */
    public int f11553a = -1;

    /* renamed from: b */
    private final iij f11554b;

    /* renamed from: c */
    private final boolean f11555c;

    /* renamed from: d */
    private gle f11556d = gle.f11566i;

    /* renamed from: e */
    private int f11557e = 0;

    public gkv(fwc fwc, iij iij, hpy hpy) {
        new ArrayList();
        this.f11554b = iij;
        this.f11555c = ((Boolean) hpy.mo7645a(false)).booleanValue();
        fwc.mo6246a((fwt) this);
    }

    /* renamed from: a */
    public final void mo6072a(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        if (this.f11555c || !bundle.getBoolean("tiktok_accounts_disabled")) {
            this.f11553a = bundle.getInt("state_account_id", -1);
            try {
                this.f11556d = (gle) imi.m14099a(bundle, "state_account_info", (ikf) gle.f11566i, this.f11554b);
                this.f11557e = bundle.getInt("state_account_state", 0);
            } catch (ijh e) {
                throw new RuntimeException("Failed to get AccountInfo from Bundle.", e);
            }
        }
    }

    /* renamed from: b */
    public final void mo6073b(Bundle bundle) {
        bundle.putInt("state_account_id", this.f11553a);
        imi.m14107a(bundle, "state_account_info", (ikf) this.f11556d);
        bundle.putInt("state_account_state", this.f11557e);
        bundle.putBoolean("tiktok_accounts_disabled", this.f11555c);
    }
}
