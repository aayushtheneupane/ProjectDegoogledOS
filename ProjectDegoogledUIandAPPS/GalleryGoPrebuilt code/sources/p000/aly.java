package p000;

import androidx.work.impl.WorkDatabase;
import java.util.LinkedList;

/* renamed from: aly */
/* compiled from: PG */
public abstract class aly implements Runnable {

    /* renamed from: a */
    private final ahx f747a = new ahx();

    /* renamed from: a */
    public abstract void mo622a();

    /* renamed from: a */
    static final void m754a(aip aip, String str) {
        WorkDatabase workDatabase = aip.f554c;
        alh j = workDatabase.mo1226j();
        akk k = workDatabase.mo1227k();
        LinkedList linkedList = new LinkedList();
        linkedList.add(str);
        while (!linkedList.isEmpty()) {
            String str2 = (String) linkedList.remove();
            int f = j.mo617f(str2);
            if (!(f == 3 || f == 4)) {
                j.mo603a(6, str2);
            }
            linkedList.addAll(k.mo577b(str2));
        }
        ahz ahz = aip.f557f;
        synchronized (ahz.f513e) {
            iol.m14231a();
            String.format("Processor cancelling %s", new Object[]{str});
            ahz.f512d.add(str);
            ait ait = (ait) ahz.f510b.remove(str);
            ahz.m517a(str, ait == null ? (ait) ahz.f511c.remove(str) : ait);
            if (ait != null) {
                synchronized (ahz.f513e) {
                    if (!(!ahz.f510b.isEmpty())) {
                        iol.m14231a();
                        ahz.f509a.startService(akh.m654a(ahz.f509a));
                    }
                }
            }
        }
        for (aia a : aip.f556e) {
            a.mo515a(str);
        }
    }

    public final void run() {
        try {
            mo622a();
            this.f747a.mo504a(aho.f494a);
        } catch (Throwable th) {
            this.f747a.mo504a(new ahl(th));
        }
    }
}
