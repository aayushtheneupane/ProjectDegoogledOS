package p000;

import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.List;

/* renamed from: eeq */
/* compiled from: PG */
final /* synthetic */ class eeq implements hpr {

    /* renamed from: a */
    private final edx f8113a;

    public eeq(edx edx) {
        this.f8113a = edx;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        edx edx = this.f8113a;
        PackageManager packageManager = edx.f8070b.getPackageManager();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (edw edw : (List) obj) {
            try {
                packageManager.getApplicationInfo(edw.mo4730d(), 0);
                try {
                    if (packageManager.getApplicationEnabledSetting(edw.mo4730d()) != 2) {
                        arrayList.add(edw);
                    }
                } catch (Exception e) {
                }
            } catch (PackageManager.NameNotFoundException e2) {
                arrayList2.add(edw.mo4729c());
            }
        }
        cwn.m5509a(edx.f8069a.mo4753b(arrayList2), "ShareValidator: Failed to delete app from database.", new Object[0]);
        return arrayList;
    }
}
