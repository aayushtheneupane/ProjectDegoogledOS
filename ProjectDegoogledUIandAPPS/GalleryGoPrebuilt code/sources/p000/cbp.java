package p000;

import android.view.LayoutInflater;
import com.google.android.apps.photosgo.editor.presets.PresetSelectionView;
import java.util.List;

/* renamed from: cbp */
/* compiled from: PG */
public final class cbp {

    /* renamed from: a */
    public final LayoutInflater f4017a;

    /* renamed from: b */
    public final gwd f4018b;

    /* renamed from: c */
    public List f4019c;

    /* renamed from: d */
    private final C0647xt f4020d;

    public cbp(PresetSelectionView presetSelectionView, hbl hbl) {
        C0607wg wgVar = new C0607wg(0);
        this.f4020d = wgVar;
        presetSelectionView.setLayoutManager(wgVar);
        this.f4017a = LayoutInflater.from(hbl);
        cbo cbo = new cbo(this);
        gwb c = gwd.m10934c();
        c.mo7127a((gwe) cbo);
        c.mo7128a(cbn.f4015a);
        gwd a = c.mo7126a();
        this.f4018b = a;
        presetSelectionView.setAdapter(a);
        C0641xn itemAnimator = presetSelectionView.getItemAnimator();
        if (itemAnimator instanceof C0674yt) {
            ((C0674yt) itemAnimator).mo10713h();
        }
    }

    /* renamed from: a */
    private final void m4003a(int i, boolean z) {
        List list = this.f4019c;
        cbc d = ((cbd) list.get(i)).mo2975d();
        d.mo2985a(z);
        list.set(i, d.mo2982a());
        this.f4018b.mo10538c(i);
    }

    /* renamed from: a */
    public final void mo2999a(car car) {
        for (int i = 0; i < this.f4019c.size(); i++) {
            cbd cbd = (cbd) this.f4019c.get(i);
            if (car.equals(cbd.mo2973b())) {
                if (!cbd.mo2974c()) {
                    m4003a(i, true);
                }
                this.f4020d.mo10470d(i);
            } else if (cbd.mo2974c()) {
                m4003a(i, false);
            }
        }
    }
}
