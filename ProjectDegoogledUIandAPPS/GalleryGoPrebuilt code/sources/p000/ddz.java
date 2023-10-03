package p000;

import java.util.ArrayList;

/* renamed from: ddz */
/* compiled from: PG */
public final class ddz {
    static {
        try {
            ank.f1195a.mo1271a("http://ns.google.com/photos/1.0/depthmap/", "GDdepth");
        } catch (ang e) {
            cwn.m5515b((Throwable) e, "Failed to register namespace", new Object[0]);
        }
    }

    /* renamed from: a */
    public static boolean m5977a(ani ani) {
        return ani.mo1264c("http://ns.google.com/photos/1.0/depthmap/", "Near") && ani.mo1264c("http://ns.google.com/photos/1.0/depthmap/", "Far");
    }

    /* renamed from: b */
    public static void m5978b(ani ani) {
        aom aom = new aom();
        aom.mo1358a(512, true);
        anh a = ani.mo1258a(aom);
        ArrayList arrayList = new ArrayList();
        while (a.hasNext()) {
            aot aot = (aot) a.next();
            if (aot.mo1291a().equals("http://ns.google.com/photos/1.0/depthmap/")) {
                arrayList.add(aot.mo1292b());
            }
        }
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ani.mo1262b("http://ns.google.com/photos/1.0/depthmap/", (String) arrayList.get(i));
        }
    }
}
