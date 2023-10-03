package p000;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import com.google.android.apps.photosgo.R;
import p003j$.util.Optional;

/* renamed from: edd */
/* compiled from: PG */
final class edd implements hol {

    /* renamed from: a */
    private final /* synthetic */ edc f8034a;

    public edd(edc edc) {
        this.f8034a = edc;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ hom mo2639a(hoi hoi) {
        Intent intent;
        Optional optional;
        ecp ecp = (ecp) hoi;
        edc edc = this.f8034a;
        boolean isChecked = edc.f8018d.isChecked();
        edz edz = ecp.f7934a;
        edn edn = ((edl) ecp.f7935b).f8044a;
        edn.f8050e.mo5551a(fdu.m8653a(), (View) edn.f8046a);
        hsj hsj = new hsj();
        ije ije = edc.f8021g.f7950b;
        int size = ije.size();
        for (int i = 0; i < size; i++) {
            cyd cyd = (cyd) ije.get(i);
            if (isChecked) {
                optional = Optional.m16285of(new Uri.Builder().scheme("content").authority(String.valueOf(edc.f8019e).concat(".sharing.compress")).path(Long.toString(cyd.f5989c)).build());
            } else {
                Uri a = edc.f8026l.mo3173a(Uri.parse(cyd.f5988b));
                optional = Uri.EMPTY.equals(a) ? Optional.empty() : Optional.m16285of(a);
            }
            hsj.getClass();
            optional.ifPresent(new eda(hsj));
        }
        hso a2 = hsj.mo7905a();
        String str = (String) edc.f8030p.map(ecz.f7953a).orElse("*/*");
        if (a2.isEmpty()) {
            return hom.f13155a;
        }
        if (a2.size() == 1) {
            intent = new Intent("android.intent.action.SEND");
            intent.putExtra("android.intent.extra.STREAM", (Uri) a2.get(0));
            intent.setType(str);
            if (isChecked) {
                intent.addFlags(1);
            }
        } else {
            intent = new Intent("android.intent.action.SEND_MULTIPLE");
            intent.setType(str);
            intent.putParcelableArrayListExtra("android.intent.extra.STREAM", ife.m12899e((Iterable) a2));
            if (isChecked) {
                intent.addFlags(1);
            }
        }
        if (edz.mo4683a(edc.f8020f.mo2634k(), intent)) {
            edc.f8028n.mo2553a(edz.mo4681a(edc.f8023i), edc.f8029o);
            edj edj = edc.f8024j;
            cwn.m5509a(edj.f8041b.mo6360a(new edi(isChecked), edj.f8040a), "Could not update compression toggle preference", new Object[0]);
            edc.f8020f.mo6295S();
            return hom.f13155a;
        }
        View view = edc.f8020f.f9573L;
        if (view != null) {
            gin.m10373a(view, (int) R.string.share_error, 0).mo6715c();
        }
        return hom.f13155a;
    }
}
