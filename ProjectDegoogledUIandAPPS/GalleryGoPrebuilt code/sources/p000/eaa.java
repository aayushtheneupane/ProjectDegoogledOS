package p000;

import android.content.Intent;
import android.net.Uri;

/* renamed from: eaa */
/* compiled from: PG */
public final class eaa implements hol {

    /* renamed from: a */
    private final /* synthetic */ dzy f7760a;

    public eaa(dzy dzy) {
        this.f7760a = dzy;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ hom mo2639a(hoi hoi) {
        dxp dxp = (dxp) hoi;
        dzy dzy = this.f7760a;
        if (dzy.f7753b.getIntent().getBooleanExtra("android.intent.extra.ALLOW_MULTIPLE", false)) {
            cwn.m5514b("ExternalPickerActivity: Multi-select was requested, but SinglePhotoClickedEvent happened... Proceeding anyway", new Object[0]);
        }
        Intent intent = new Intent();
        intent.setData(Uri.parse(dxp.f7577a.f5910b));
        intent.addFlags(1);
        return dzy.mo4620a(intent);
    }
}
