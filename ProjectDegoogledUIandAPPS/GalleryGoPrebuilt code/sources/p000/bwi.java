package p000;

import android.content.Intent;
import android.net.Uri;
import p003j$.util.Optional;

/* renamed from: bwi */
/* compiled from: PG */
public final class bwi implements hol {

    /* renamed from: a */
    private final /* synthetic */ bwh f3769a;

    public bwi(bwh bwh) {
        this.f3769a = bwh;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ hom mo2639a(hoi hoi) {
        bwp bwp = (bwp) hoi;
        bwh bwh = this.f3769a;
        if (bwp.mo2768a()) {
            Intent intent = new Intent();
            Optional b = bwp.mo2769b();
            intent.setData(b.isPresent() ? Uri.parse(((cxi) b.get()).f5910b) : Uri.EMPTY);
            intent.addFlags(1);
            bwh.f3766b.setResult(-1, intent);
            bwh.f3766b.finish();
            return hom.f13155a;
        }
        bwh.mo2833c();
        return hom.f13155a;
    }
}
