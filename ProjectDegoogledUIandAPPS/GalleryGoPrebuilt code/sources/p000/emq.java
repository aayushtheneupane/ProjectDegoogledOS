package p000;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* renamed from: emq */
/* compiled from: PG */
final class emq extends emu {

    /* renamed from: a */
    private final ArrayList f8567a;

    /* renamed from: b */
    private final /* synthetic */ emv f8568b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public emq(emv emv, ArrayList arrayList) {
        super(emv);
        this.f8568b = emv;
        this.f8567a = arrayList;
    }

    /* renamed from: a */
    public final void mo5019a() {
        Set set;
        emv emv = this.f8568b;
        ena ena = emv.f8574a.f8639l;
        epk epk = emv.f8584k;
        if (epk != null) {
            set = new HashSet(epk.f8776a);
            Map map = emv.f8584k.f8778c;
            for (ekn ekn : map.keySet()) {
                if (!emv.f8574a.f8634g.containsKey(ekn.mo4940a())) {
                    C0652xy xyVar = (C0652xy) map.get(ekn);
                    set.addAll((Collection) null);
                }
            }
        } else {
            set = Collections.emptySet();
        }
        ena.f8611k = set;
        ArrayList arrayList = this.f8567a;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            emv emv2 = this.f8568b;
            ((ekm) arrayList.get(i)).mo4929a(emv2.f8581h, emv2.f8574a.f8639l.f8611k);
        }
    }
}
