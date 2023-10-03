package p000;

import android.os.Environment;
import android.text.format.Formatter;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import p003j$.util.Optional;

/* renamed from: bsy */
/* compiled from: PG */
final /* synthetic */ class bsy implements hpr {

    /* renamed from: a */
    private final bsz f3507a;

    public bsy(bsz bsz) {
        this.f3507a = bsz;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        hlj a;
        Throwable th;
        Throwable th2;
        hlj a2;
        Throwable th3;
        int i;
        hlj a3;
        Throwable th4;
        bsz bsz = this.f3507a;
        List<cxi> list = (List) obj;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        C0309lf lfVar = new C0309lf();
        hlj a4 = hnb.m11765a("Collecting media into folders");
        try {
            a = hnb.m11765a("Grouping media");
            for (cxi cxi : list) {
                cxe cxe = cxi.f5919k;
                if (cxe == null) {
                    cxe = cxe.f5893f;
                }
                String str = cxe.f5896b;
                boolean contains = cxi.f5921m.contains("/DCIM/");
                boolean contains2 = cxi.f5921m.contains("/DCIM/Camera/");
                if (contains) {
                    Optional c = cya.m5638c(cxi.f5921m);
                    if (c.isPresent()) {
                        str = ((cxe) c.get()).f5896b;
                    }
                }
                List list2 = (List) lfVar.get(str);
                if (list2 == null) {
                    list2 = new ArrayList();
                    lfVar.put(str, list2);
                    if (!contains2) {
                        arrayList.add(str);
                    } else {
                        arrayList2.add(str);
                    }
                }
                list2.add(cxi);
            }
            if (a != null) {
                a.close();
            }
            int i2 = 0;
            arrayList.addAll(0, arrayList2);
            ArrayList arrayList3 = new ArrayList();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                List<cxi> list3 = (List) lfVar.get((String) it.next());
                if (list3 != null) {
                    cxi cxi2 = (cxi) list3.get(i2);
                    hlj a5 = hnb.m11765a("Creating single folder");
                    try {
                        a2 = hnb.m11765a("Fetching storage type");
                        if (!new File(cxi2.f5921m).getAbsolutePath().startsWith(String.valueOf(Environment.getExternalStorageDirectory().getAbsolutePath()).concat("/"))) {
                            i = 2;
                        } else {
                            i = 1;
                        }
                        if (a2 != null) {
                            a2.close();
                        }
                        a3 = hnb.m11765a("Computing folder size");
                        long j = 0;
                        for (cxi cxi3 : list3) {
                            Iterator it2 = it;
                            j += cxi3.f5920l;
                            it = it2;
                        }
                        Iterator it3 = it;
                        if (a3 != null) {
                            a3.close();
                        }
                        cxe cxe2 = cxi2.f5919k;
                        if (cxe2 == null) {
                            cxe2 = cxe.f5893f;
                        }
                        if (cxe2.f5899e) {
                            Optional c2 = cya.m5638c(cxi2.f5921m);
                            if (c2.isPresent()) {
                                cxe2 = (cxe) c2.get();
                            }
                        }
                        btk f = btl.m3569f();
                        f.mo2754a(cxe2);
                        f.f3545a = i;
                        f.mo2756a((List) list3);
                        f.mo2753a(j);
                        f.mo2755a(Formatter.formatShortFileSize(bsz.f3511d, j));
                        arrayList3.add(f.mo2752a());
                        if (a5 != null) {
                            a5.close();
                            it = it3;
                            i2 = 0;
                        } else {
                            it = it3;
                            i2 = 0;
                        }
                    } catch (Throwable th5) {
                        th2 = th5;
                        if (a5 != null) {
                            a5.close();
                        }
                        throw th2;
                    }
                } else {
                    Iterator it4 = it;
                    cwn.m5514b("DeviceFoldersDataService: Unexpected state: folder with no media.", new Object[0]);
                    it = it4;
                    i2 = 0;
                }
            }
            if (a4 != null) {
                a4.close();
            }
            return arrayList3;
            throw th3;
            throw th;
            throw th4;
        } catch (Throwable th6) {
            Throwable th7 = th6;
            if (a4 != null) {
                try {
                    a4.close();
                } catch (Throwable th8) {
                    ifn.m12935a(th7, th8);
                }
            }
            throw th7;
        }
    }
}
