package p000;

import android.view.View;
import com.google.android.apps.photosgo.R;
import com.google.android.material.button.MaterialButton;
import java.util.Collection;
import java.util.Set;

/* renamed from: cks */
/* compiled from: PG */
public final class cks implements cqg {

    /* renamed from: a */
    public final ckn f4581a;

    /* renamed from: b */
    public final blu f4582b;

    /* renamed from: c */
    public final hlz f4583c;

    /* renamed from: d */
    public final egp f4584d;

    /* renamed from: e */
    public final grx f4585e;

    /* renamed from: f */
    public gry f4586f = null;

    /* renamed from: g */
    private final cnh f4587g;

    public cks(ckn ckn, cnh cnh, blu blu, egp egp, grx grx, hlz hlz) {
        this.f4581a = ckn;
        this.f4587g = cnh;
        this.f4582b = blu;
        this.f4584d = egp;
        this.f4585e = grx;
        this.f4583c = hlz;
        ckn.f4574a.mo64a(egp);
    }

    /* renamed from: c */
    public final dwn mo3218c() {
        return ((dwh) ife.m12898e((Object) this.f4581a.mo5659r().mo6432b((int) R.id.add_items_to_folder_main_content))).mo2635n();
    }

    /* renamed from: b */
    public final Set mo3217b() {
        return dwv.m6831a(mo3218c().f7512d.f5420a, false);
    }

    /* renamed from: a */
    public final void mo2621a() {
        boolean z = !mo3217b().isEmpty();
        View view = this.f4581a.f9573L;
        if (view != null) {
            ((MaterialButton) view.findViewById(R.id.add_items_to_folder_move_btn)).setEnabled(z);
            ((MaterialButton) view.findViewById(R.id.add_items_to_folder_copy_btn)).setEnabled(z);
        }
    }

    /* renamed from: a */
    public final void mo3216a(int i) {
        this.f4587g.mo3270a(new ckv(i, hto.m12125a((Collection) mo3217b())));
    }
}
