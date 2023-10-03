package p000;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import com.google.android.apps.photosgo.R;
import p003j$.util.Optional;

/* renamed from: bws */
/* compiled from: PG */
final /* synthetic */ class bws implements View.OnClickListener {

    /* renamed from: a */
    private final bwv f3778a;

    /* renamed from: b */
    private final bul f3779b;

    /* renamed from: c */
    private final dhq f3780c;

    public bws(bwv bwv, bul bul, dhq dhq) {
        this.f3778a = bwv;
        this.f3779b = bul;
        this.f3780c = dhq;
    }

    public final void onClick(View view) {
        Optional optional;
        cxi cxi;
        ceq ceq;
        bwv bwv = this.f3778a;
        bul bul = this.f3779b;
        dhq dhq = this.f3780c;
        bwx bwx = bwv.f3784a;
        String b = dhq.mo4138b();
        String c = dhq.mo4139c();
        bwx.f3789a.mo3274e();
        dku dku = bwx.f3792d;
        int b2 = ggf.m10254b(bul.f3631b);
        int i = b2 - 1;
        if (b2 != 0) {
            if (i == 0) {
                if (bul.f3631b == 1) {
                    cxi = (cxi) bul.f3632c;
                } else {
                    cxi = cxi.f5908x;
                }
                optional = dku.mo4181a(cxi, Optional.m16285of(new ComponentName(b, c)));
            } else if (i == 1) {
                if (bul.f3631b == 2) {
                    ceq = (ceq) bul.f3632c;
                } else {
                    ceq = ceq.f4197g;
                }
                optional = dku.mo4180a(Uri.parse(ceq.f4200b), ceq.f4201c, Optional.m16285of(new ComponentName(b, c)));
            } else {
                String a = ggf.m10251a(ggf.m10254b(bul.f3631b));
                StringBuilder sb = new StringBuilder(a.length() + 21);
                sb.append("Unsupported typecase ");
                sb.append(a);
                throw new IllegalArgumentException(sb.toString());
            }
            if (optional.isPresent()) {
                dku.f6745b.mo6067a((int) R.id.oneup_request_code_edit_in_external, dvg.m6742a(dku.f6744a, (Intent) optional.get()));
            } else {
                cwn.m5514b("ExternalEditorHandler: unable to construct intent for external editor", new Object[0]);
            }
        } else {
            throw null;
        }
    }
}
