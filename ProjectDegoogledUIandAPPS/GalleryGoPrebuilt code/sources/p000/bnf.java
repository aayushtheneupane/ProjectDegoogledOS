package p000;

import android.database.Cursor;
import java.util.Collection;
import java.util.List;

/* renamed from: bnf */
/* compiled from: PG */
final /* synthetic */ class bnf implements hpr {

    /* renamed from: a */
    private final List f3189a;

    public bnf(List list) {
        this.f3189a = list;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        List list = this.f3189a;
        Cursor cursor = (Cursor) obj;
        if (!cursor.moveToFirst()) {
            return new C0290kn();
        }
        hrk hrk = new hrk(list.size(), list.size());
        int columnIndexOrThrow = cursor.getColumnIndexOrThrow("b");
        int columnIndexOrThrow2 = cursor.getColumnIndexOrThrow("c");
        do {
            String a = cyc.m5644a(cyc.m5643a(cursor.getInt(columnIndexOrThrow2)), cursor.getLong(columnIndexOrThrow));
            cxd cxd = (cxd) list.get(fxk.m9818a(cursor, "sq"));
            Collection collection = (Collection) hrk.f13294a.get(cxd);
            if (collection == null) {
                Collection a2 = hrk.mo7691a();
                if (a2.add(a)) {
                    hrk.f13295b++;
                    hrk.f13294a.put(cxd, a2);
                } else {
                    throw new AssertionError("New Collection violated the Collection spec");
                }
            } else if (collection.add(a)) {
                hrk.f13295b++;
            }
        } while (cursor.moveToNext());
        C0290kn knVar = new C0290kn(hrk.f13295b);
        for (cxd cxd2 : hrk.mo7786d()) {
            knVar.put(cxd2, hrk.mo7693a(cxd2));
        }
        return knVar;
    }
}
