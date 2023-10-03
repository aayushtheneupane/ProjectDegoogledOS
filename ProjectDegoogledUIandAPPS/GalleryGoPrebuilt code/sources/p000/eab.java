package p000;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;

/* renamed from: eab */
/* compiled from: PG */
public final class eab implements hol {

    /* renamed from: a */
    private final /* synthetic */ dzy f7761a;

    public eab(dzy dzy) {
        this.f7761a = dzy;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ hom mo2639a(hoi hoi) {
        dwd dwd = (dwd) hoi;
        dzy dzy = this.f7761a;
        if (!dzy.f7753b.getIntent().getBooleanExtra("android.intent.extra.ALLOW_MULTIPLE", false)) {
            cwn.m5514b("ExternalPickerActivity: Multi-select was not requested, but multiple photos were somehow selected; doing nothing.", new Object[0]);
            return hom.f13155a;
        } else if (dwd.mo4502a().isEmpty()) {
            cwn.m5514b("ExternalPickerActivity: No items were selected in multi-select; doing nothing.", new Object[0]);
            return hom.f13155a;
        } else {
            Intent intent = new Intent();
            intent.setData(Uri.parse(((cxi) dwd.mo4502a().iterator().next()).f5910b));
            ClipData clipData = null;
            for (cxi cxi : dwd.mo4502a()) {
                ClipData.Item item = new ClipData.Item(Uri.parse(cxi.f5910b));
                if (clipData == null) {
                    clipData = new ClipData((CharSequence) null, dzy.f7752a, item);
                } else {
                    clipData.addItem(item);
                }
            }
            intent.setClipData((ClipData) ife.m12869b((Object) clipData, (Object) "clipData cannot be null."));
            intent.addFlags(1);
            return dzy.mo4620a(intent);
        }
    }
}
