package p000;

import android.content.Context;
import android.os.Build;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.background.systemjob.SystemJobService;
import java.util.Iterator;
import java.util.List;

/* renamed from: aib */
/* compiled from: PG */
public final class aib {
    static {
        iol.m14236b("Schedulers");
    }

    /* renamed from: a */
    static aia m532a(Context context, aip aip) {
        int i = Build.VERSION.SDK_INT;
        ajh ajh = new ajh(context, aip);
        amc.m760a(context, SystemJobService.class, true);
        iol.m14231a();
        return ajh;
    }

    /* JADX INFO: finally extract failed */
    /* renamed from: a */
    public static void m533a(WorkDatabase workDatabase, List list) {
        if (list != null && list.size() != 0) {
            alh j = workDatabase.mo1226j();
            workDatabase.mo2845e();
            try {
                agz.m469a();
                List<alg> e = j.mo615e();
                if (e.size() > 0) {
                    long currentTimeMillis = System.currentTimeMillis();
                    for (alg alg : e) {
                        j.mo610b(alg.f713b, currentTimeMillis);
                    }
                }
                workDatabase.mo2847g();
                workDatabase.mo2846f();
                if (e.size() > 0) {
                    alg[] algArr = (alg[]) e.toArray(new alg[0]);
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        ((aia) it.next()).mo516a(algArr);
                    }
                }
            } catch (Throwable th) {
                workDatabase.mo2846f();
                throw th;
            }
        }
    }
}
