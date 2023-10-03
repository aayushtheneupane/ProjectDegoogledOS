package p000;

import android.content.Intent;
import android.net.Uri;

/* renamed from: bwj */
/* compiled from: PG */
public final class bwj implements hol {

    /* renamed from: a */
    private final /* synthetic */ bwh f3770a;

    public bwj(bwh bwh) {
        this.f3770a = bwh;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ hom mo2639a(hoi hoi) {
        cce cce = (cce) hoi;
        bwh bwh = this.f3770a;
        if (cce.mo3011a().isPresent()) {
            Intent intent = new Intent();
            intent.setData(Uri.parse(((cxi) cce.mo3011a().get()).f5910b));
            intent.addFlags(1);
            bwh.f3766b.setResult(-1, intent);
            bwh.f3766b.finish();
            return hom.f13155a;
        }
        bwh.mo2833c();
        return hom.f13155a;
    }
}
