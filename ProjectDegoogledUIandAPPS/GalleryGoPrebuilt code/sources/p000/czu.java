package p000;

import android.net.Uri;
import android.provider.MediaStore;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* renamed from: czu */
/* compiled from: PG */
public final class czu {

    /* renamed from: a */
    public static /* synthetic */ int f6115a;

    /* renamed from: b */
    private static final Uri f6116b = MediaStore.Files.getContentUri("external");

    /* renamed from: a */
    static bip m5774a() {
        return bip.f2457a;
    }

    /* renamed from: a */
    static ieh m5776a(hso hso, iek iek) {
        ArrayList arrayList = new ArrayList();
        hvs i = hso.listIterator();
        while (i.hasNext()) {
            czt czt = (czt) i.next();
            arrayList.add(gte.m10771a(iek.mo5933a(hmq.m11749a((Callable) new czr(czt.mo4002a()))), (icf) new czq(czt, iek), (Executor) iek));
        }
        return new hnm(ife.m12883c((Iterable) arrayList)).mo7613a(czm.f6106a, (Executor) idh.f13918a);
    }

    /* renamed from: a */
    static ieh m5780a(List list, gtt gtt) {
        new Object[1][0] = Integer.valueOf(list.size());
        if (list.isEmpty()) {
            return ife.m12820a((Object) bip.f2457a);
        }
        String[] strArr = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            strArr[i] = String.valueOf(((cyg) list.get(i)).mo3908b());
        }
        String str = " IN (?";
        for (int i2 = 1; i2 < list.size(); i2++) {
            str = String.valueOf(str).concat(", ?");
        }
        String concat = String.valueOf(str).concat(")");
        Uri uri = f6116b;
        String valueOf = String.valueOf(concat);
        ieh a = gtt.mo7043a(uri, valueOf.length() == 0 ? new String("_id") : "_id".concat(valueOf), strArr);
        Uri uri2 = MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI;
        String valueOf2 = String.valueOf(concat);
        ieh a2 = gtt.mo7043a(uri2, valueOf2.length() == 0 ? new String("image_id") : "image_id".concat(valueOf2), strArr);
        Uri uri3 = MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI;
        String valueOf3 = String.valueOf(concat);
        return gte.m10778b(a, a2, gtt.mo7043a(uri3, valueOf3.length() == 0 ? new String("video_id") : "video_id".concat(valueOf3), strArr)).mo7613a(czk.f6104a, (Executor) idh.f13918a);
    }

    /* renamed from: b */
    static ieh m5782b(List list, cyr cyr) {
        if (list.isEmpty()) {
            return ife.m12820a((Object) bip.f2457a);
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(Long.valueOf(((cyd) it.next()).f5990d));
        }
        return bip.m2623a(cyr.mo3999c(arrayList));
    }

    /* renamed from: a */
    static ieh m5775a(cjh cjh) {
        return bip.m2623a(cjh.mo3168a());
    }

    /* renamed from: a */
    static ieh m5779a(List list, efr efr, iek iek) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            File file = (File) it.next();
            arrayList.add(gte.m10772a(gte.m10771a(iek.mo5933a(hmq.m11749a((Callable) new czn(file))), (icf) new czo(efr, file), (Executor) idh.f13918a), Throwable.class, czp.f6110a, (Executor) idh.f13918a));
        }
        return gte.m10770a(ife.m12819a((Iterable) arrayList), (hpr) new czl(list), (Executor) idh.f13918a);
    }

    /* renamed from: a */
    static ieh m5781a(List list, gtt gtt, iek iek) {
        if (list.isEmpty()) {
            return ife.m12820a((Object) new ArrayList());
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(Long.valueOf(((cyd) it.next()).f5989c));
        }
        hgn hgn = new hgn();
        hgn.mo7409a("_id IN(?");
        hgn.mo7411b(String.valueOf(arrayList.get(0)));
        for (int i = 1; i < arrayList.size(); i++) {
            hgn.mo7409a(", ?");
            hgn.mo7411b(String.valueOf(arrayList.get(i)));
        }
        hgn.mo7409a(")");
        hgm a = hgn.mo7407a();
        return gtt.mo7041a(f6116b, new String[]{"_data"}, a, (String) null).mo6897b(czi.f6102a, (Executor) iek).mo6899b();
    }

    /* renamed from: a */
    static ieh m5778a(List list, dip dip, iek iek) {
        return iek.mo5933a(hmq.m11749a((Callable) new czh(list, dip)));
    }

    /* renamed from: a */
    static ieh m5777a(List list, cyr cyr) {
        if (list.isEmpty()) {
            return ife.m12820a((Object) new ArrayList());
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(Long.valueOf(((cyd) it.next()).f5990d));
        }
        return cyr.mo3998b(arrayList);
    }
}
