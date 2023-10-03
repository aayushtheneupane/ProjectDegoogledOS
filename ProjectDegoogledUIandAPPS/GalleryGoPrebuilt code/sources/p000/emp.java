package p000;

import android.app.PendingIntent;
import java.util.ArrayList;
import java.util.Map;

/* renamed from: emp */
/* compiled from: PG */
final class emp extends emu {

    /* renamed from: a */
    public final /* synthetic */ emv f8565a;

    /* renamed from: b */
    private final Map f8566b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public emp(emv emv, Map map) {
        super(emv);
        this.f8565a = emv;
        this.f8566b = map;
    }

    /* renamed from: a */
    public final void mo5019a() {
        ewc ewc;
        eqe eqe = new eqe(this.f8565a.f8577d);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (ekm ekm : this.f8566b.keySet()) {
            ekm.mo4939l();
            if (((emm) this.f8566b.get(ekm)).f8559a) {
                arrayList2.add(ekm);
            } else {
                arrayList.add(ekm);
            }
        }
        int i = -1;
        int i2 = 0;
        if (arrayList.isEmpty()) {
            int size = arrayList2.size();
            while (i2 < size) {
                i = eqe.mo5158a(this.f8565a.f8576c, (ekm) arrayList2.get(i2));
                i2++;
                if (i == 0) {
                    break;
                }
            }
        } else {
            int size2 = arrayList.size();
            while (i2 < size2) {
                i = eqe.mo5158a(this.f8565a.f8576c, (ekm) arrayList.get(i2));
                i2++;
                if (i != 0) {
                    break;
                }
            }
        }
        if (i != 0) {
            ejq ejq = new ejq(i, (PendingIntent) null);
            emv emv = this.f8565a;
            emv.f8574a.mo5036a((enc) new emn(this, emv, ejq));
            return;
        }
        emv emv2 = this.f8565a;
        if (emv2.f8579f && (ewc = emv2.f8578e) != null) {
            ewc.mo5334n();
        }
        for (ekm ekm2 : this.f8566b.keySet()) {
            epc epc = (epc) this.f8566b.get(ekm2);
            ekm2.mo4939l();
            if (eqe.mo5158a(this.f8565a.f8576c, ekm2) != 0) {
                emv emv3 = this.f8565a;
                emv3.f8574a.mo5036a((enc) new emo(emv3, epc));
            } else {
                ekm2.mo4927a(epc);
            }
        }
    }
}
