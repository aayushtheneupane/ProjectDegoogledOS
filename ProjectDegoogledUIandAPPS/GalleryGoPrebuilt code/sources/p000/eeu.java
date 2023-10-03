package p000;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;

/* renamed from: eeu */
/* compiled from: PG */
final /* synthetic */ class eeu implements icf {

    /* renamed from: a */
    private final eex f8121a;

    public eeu(eex eex) {
        this.f8121a = eex;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        eex eex = this.f8121a;
        hsu a = ife.m12812a((Iterable) (List) obj, eev.f8122a);
        Map a2 = eex.mo4760a(new Intent("android.intent.action.SEND").setType("image/*"));
        Set keySet = eex.mo4760a(new Intent("android.intent.action.SEND_MULTIPLE").setType("image/*")).keySet();
        Set keySet2 = eex.mo4760a(new Intent("android.intent.action.SEND").setType("video/*")).keySet();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (ComponentName componentName : a2.keySet()) {
            ResolveInfo resolveInfo = (ResolveInfo) a2.get(componentName);
            if (a.containsKey(componentName)) {
                edw edw = (edw) a.get(componentName);
                edv k = edw.mo4736k();
                k.mo4728c(resolveInfo.loadLabel(eex.f8125b).toString());
                k.mo4725a(keySet.contains(componentName));
                k.mo4727b(keySet2.contains(componentName));
                edw a3 = k.mo4721a();
                if (!edw.equals(a3)) {
                    arrayList2.add(a3);
                }
            } else {
                edv j = edw.m7289j();
                j.mo4724a(componentName.getClassName());
                j.mo4726b(componentName.getPackageName());
                j.mo4728c(resolveInfo.loadLabel(eex.f8125b).toString());
                j.mo4722a(((Integer) eex.f8124a.getOrDefault(Long.valueOf(hzm.m12530b().mo8287a(componentName.getClassName().getBytes()).mo8294d()), 0)).intValue());
                j.mo4723a(1);
                j.mo4725a(keySet.contains(componentName));
                j.mo4727b(keySet2.contains(componentName));
                arrayList.add(j.mo4721a());
            }
        }
        ArrayList arrayList3 = new ArrayList();
        for (ComponentName componentName2 : a.keySet()) {
            if (!a2.containsKey(componentName2)) {
                arrayList3.add(componentName2.getClassName());
            }
        }
        if (arrayList3.isEmpty() && arrayList.isEmpty() && arrayList2.isEmpty()) {
            return ife.m12820a((Object) null);
        }
        hnm a4 = gte.m10769a(eex.f8126c.mo4753b(arrayList3), eex.f8126c.mo4751a((List) arrayList), eex.f8126c.mo4754c(arrayList2));
        ble ble = eex.f8127d;
        ble.getClass();
        return a4.mo7612a((Runnable) new eew(ble), (Executor) eex.f8128e);
    }
}
