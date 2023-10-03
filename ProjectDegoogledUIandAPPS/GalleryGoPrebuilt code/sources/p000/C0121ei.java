package p000;

import java.util.ArrayList;

/* renamed from: ei */
/* compiled from: PG */
public class C0121ei extends C0116ed {

    /* renamed from: af */
    public final ArrayList f8330af = new ArrayList();

    /* renamed from: n */
    public void mo4742n() {
        mo4714l();
        ArrayList arrayList = this.f8330af;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                C0116ed edVar = (C0116ed) this.f8330af.get(i);
                if (edVar instanceof C0121ei) {
                    ((C0121ei) edVar).mo4742n();
                }
            }
        }
    }

    /* renamed from: a */
    public final void mo4818a(C0116ed edVar) {
        this.f8330af.remove(edVar);
        edVar.f8001o = null;
    }

    /* renamed from: a */
    public void mo4690a() {
        this.f8330af.clear();
        super.mo4690a();
    }

    /* renamed from: a */
    public final void mo4694a(C0108dw dwVar) {
        super.mo4694a(dwVar);
        int size = this.f8330af.size();
        for (int i = 0; i < size; i++) {
            ((C0116ed) this.f8330af.get(i)).mo4694a(dwVar);
        }
    }

    /* renamed from: a */
    public final void mo4692a(int i, int i2) {
        super.mo4692a(i, i2);
        int size = this.f8330af.size();
        for (int i3 = 0; i3 < size; i3++) {
            ((C0116ed) this.f8330af.get(i3)).mo4692a(this.f8006t + this.f8010x, this.f8007u + this.f8011y);
        }
    }

    /* renamed from: l */
    public final void mo4714l() {
        super.mo4714l();
        ArrayList arrayList = this.f8330af;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                C0116ed edVar = (C0116ed) this.f8330af.get(i);
                edVar.mo4692a(mo4708g(), mo4710h());
                if (!(edVar instanceof C0117ee)) {
                    edVar.mo4714l();
                }
            }
        }
    }
}
