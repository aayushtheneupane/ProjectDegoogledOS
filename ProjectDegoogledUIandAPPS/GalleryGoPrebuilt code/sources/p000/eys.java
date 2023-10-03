package p000;

import android.content.Context;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/* renamed from: eys */
/* compiled from: PG */
public final class eys implements eyi {

    /* renamed from: a */
    private final Context f9200a;

    /* renamed from: b */
    private final eks f9201b;

    /* renamed from: c */
    private final ezb f9202c;

    /* renamed from: a */
    public final eyi mo5391a(eyh eyh) {
        eks eks = this.f9201b;
        ekn a = eyh instanceof eyx ? ((eyx) eyh).mo5413a() : null;
        abj.m86a((Object) a, (Object) "Api must not be null");
        eks.f8497g.put(a, (Object) null);
        List emptyList = Collections.emptyList();
        eks.f8492b.addAll(emptyList);
        eks.f8491a.addAll(emptyList);
        return this;
    }

    public eys(Context context) {
        ezb ezb = new ezb((byte[]) null);
        this.f9200a = context;
        this.f9201b = new eks(context);
        this.f9202c = ezb;
    }

    /* renamed from: a */
    public final eym mo5392a() {
        ewd ewd;
        boolean z;
        Context context = this.f9200a;
        eks eks = this.f9201b;
        abj.m117b(!eks.f8497g.isEmpty(), (Object) "must call addApi() to add at least one API");
        ewd ewd2 = ewd.f9128a;
        if (eks.f8497g.containsKey(ewb.f9123a)) {
            ewd = (ewd) eks.f8497g.get(ewb.f9123a);
        } else {
            ewd = ewd2;
        }
        epk epk = new epk(eks.f8491a, eks.f8495e, eks.f8493c, eks.f8494d, ewd);
        Map map = epk.f8778c;
        C0290kn knVar = new C0290kn();
        C0290kn knVar2 = new C0290kn();
        ArrayList arrayList = new ArrayList();
        for (ekn ekn : eks.f8497g.keySet()) {
            Object obj = eks.f8497g.get(ekn);
            if (map.get(ekn) != null) {
                z = true;
            } else {
                z = false;
            }
            knVar.put(ekn, Boolean.valueOf(z));
            ely ely = new ely(ekn, z);
            arrayList.add(ely);
            ely ely2 = ely;
            knVar2.put(ekn.mo4940a(), ekn.mo4941b().mo4862a(eks.f8496f, eks.f8498h, epk, obj, ely2, ely2));
        }
        ena.m7836a((Iterable) knVar2.values());
        ena ena = new ena(eks.f8496f, new ReentrantLock(), eks.f8498h, epk, eks.f8499i, eks.f8502l, knVar, eks.f8500j, eks.f8501k, knVar2, arrayList, (byte[]) null, (byte[]) null);
        synchronized (ekv.f8503a) {
            ekv.f8503a.add(ena);
        }
        return new eyt(context, ena, this.f9202c);
    }
}
